package callcenter

import com.pw.security.Rol
import com.pw.security.Usuario

class AsignarNovedadesController {

    //Evita que ingrese mediante url
    static beforeInterceptor = {
        String accion = actionUri;
        if (!accion.equals("/usuario/login") && !accion.equals("/usuario/logout")) {
            if (!session.user) {
                redirect(uri: "/usuario/login");
                return false;
            } else {
                boolean tienePermiso = utilitarios.Util.checkAccess(session.user.usuario, accion)
                if (!tienePermiso) {
                    render "No tienes permiso para ingresar a este sitio-> " + accion;
                }
            }
        }
    }

    def index() {
        boolean baseAsignada = false
        if (params.consultants && params.dbName && params.tipoRegx && params.allocationNumber) {
            String nombreBase = params.dbName
            String tipoRegistros = params.tipoRegx
            String subestado = params.subestadosRegestionables
            String intentosDesde = params.intentosDesde
            String intentosHasta = params.intentosHasta
            String sql = ""
            Rol rol = Rol.findByNombre("OPERADOR")
            def condiciones = [nombreBase: nombreBase, rol: rol, offset: 0, max: params.allocationNumber.toString().toInteger()]

            if(tipoRegistros.equalsIgnoreCase("SIN GESTION")){
                sql = "from ClientesNovedades where usuario.rol != :rol and intentos = 0 and nombreBase = :nombreBase"
            }else{
                String condicionIntentosDesde = ""
                String condicionIntentosHasta = ""
                String condicionSubestado = ""
                if(intentosDesde != ""){
                    condicionIntentosDesde = "and intentos >= :intentosDesde"
                    condiciones.put("intentosDesde", intentosDesde.toInteger())
                }
                if(intentosHasta != ""){
                    condicionIntentosHasta = "and intentos <= :intentosHasta"
                    condiciones.put("intentosHasta", intentosHasta.toInteger())
                }
                if(subestado != ""){
                    condicionSubestado = "and motivoNoDesea = :subestado"
                    condiciones.put("subestado", subestado)
                }
                sql = "from ClientesNovedades where subestadoGestion.type = 'Regestionable' and usuario.rol != :rol and nombreBase = :nombreBase and intentos != 0 ${condicionIntentosDesde} ${condicionIntentosHasta}  ${condicionSubestado} order by intentos"
            }

            def consultants = params.list('consultants')

            int registrosAsignados = 0

            for (int i = 0; i < consultants.size(); i++) {
                Usuario usuario = Usuario.findById(consultants.get(i).toString().toInteger())

                def clientes = ClientesNovedades.executeQuery(sql, condiciones)

                for (int j = 0; j < clientes.size(); j++) {
                    ClientesNovedades cliente = ClientesNovedades.findById(clientes.get(j).id)
                    cliente.usuario = usuario
                    cliente.nombreVendedor = usuario.nombre
                    cliente.save(flush: true)
                    registrosAsignados++
                }
            }
            baseAsignada = true
            [baseAsignada: baseAsignada, registrosAsignados: registrosAsignados]
        }
    }
}
