package callcenter

import com.pw.security.Usuario

class GestionNovedadesController {

    static beforeInterceptor = {
        String accion = actionUri
        if(!accion.equals("/usuario/login") && !accion.equals("/usuario/logout")){
            if(!session.user){
                redirect(uri: "/usuario/login")
                return false;
            }else{
                boolean tienePermiso = utilitarios.Util.checkAccess(session.user.usuario, accion)
                if(!tienePermiso){
                    render "No tienes permiso para ingresar a este sitio-> "+accion
                }
            }
        }
    }

    /**
     * @author Giovanny Granda
     * Muestra en pantalla los clientes asignados
     * @return
     */
    def index() {
        Usuario usuario = Usuario.findById(session.user.id)

        def clients = ClientesNovedades.withCriteria {
            eq('usuario',usuario)
            subestadoGestion {
                or{
                    eq('type', Subestado.constraints.type.inList[0].toString())
                    eq('type', Subestado.constraints.type.inList[1].toString())
                }
            }
            order("intentos")
        }
        def clientsNoManagement = ClientesNovedades.withCriteria {
            eq('usuario',usuario)
            isNull('subestadoGestion')
        }

        clients.each {client ->
            clientsNoManagement.add(client)
        }

        [clientesGestionar: clientsNoManagement]
    }

    /**
     * @author Giovanny Granda
     * Muestra la pantalla de gestion donde se hara rectificación de datos
     * @param id
     * @return
     */
    def gestionCliente(long id){

        int idCliente = id
        ClientesNovedades cliente = ClientesNovedades.findById(idCliente)
        [cliente: cliente]
    }

    def guardarCliente(){
        Usuario usuario = Usuario.findById(session.user.id.toString().toLong())
        Date fechaActual = new Date()
        Estado estadoGestion = Estado.findById(params.status.toString().toLong())
        Subestado subestadoGestion = Subestado.findById(params.substatus.toString().toLong())
        String subSubestadoGestion = params.subSubStatus
        ClientesNovedades cliente = ClientesNovedades.findById(params.idCliente.toString().toLong())
        int intentos = cliente.intentos?: 0

        if(subestadoGestion.enableManagement){
            if (params.provinciaDomic.toString() != ""){
                cliente.provinciaDomic = removeSpecialCharacters(Provincia.findById(params.provinciaDomic.toString().toLong()).nombre)
            }
            if(params.ciudadDomic.toString() != ""){
                cliente.ciudadDomic = removeSpecialCharacters(Ciudad.findById(params.ciudadDomic.toString().toLong()).nombre)
            }
            cliente.sectorDomic = removeSpecialCharacters(params.sectorDomic.toString())
            cliente.callePrincipalDomic = removeSpecialCharacters(params.callePrincipalDomic.toString())
            cliente.numeracionDomic = removeSpecialCharacters(params.numeracionDomic.toString())
            cliente.calleTransversalDomic = removeSpecialCharacters(params.calleTransversalDomic.toString())
            cliente.tipoVivienda = params.tipoVivienda
            cliente.referenciaDomic = removeSpecialCharacters(params.referenciaDomic.toString())

            if(params.provinciaTrab.toString() != ""){
                cliente.provinciaTrab = removeSpecialCharacters(Provincia.findById(params.provinciaTrab.toString().toLong()).nombre)
            }
            if(params.ciudadTrab.toString() != ""){
                cliente.ciudadTrab = removeSpecialCharacters(Ciudad.findById(params.ciudadTrab.toString().toLong()).nombre)
            }
            cliente.sectorTrab = removeSpecialCharacters(params.sectorTrab.toString())
            cliente.callePrincipalTrab = removeSpecialCharacters(params.callePrincipalTrab.toString())
            cliente.numeracionTrab = removeSpecialCharacters(params.numeracionTrab.toString())
            cliente.calleTransversalTrab = removeSpecialCharacters(params.calleTransversalTrab.toString())
            cliente.tipoTrab = params.tipoTrab
            cliente.referenciaTrab = removeSpecialCharacters(params.referenciaTrab.toString())
            cliente.entrega = params.entrega
            cliente.nombreContacto = removeSpecialCharacters(params.nombreContacto.toString())
            cliente.rangoVisita = params.rangoVisita.toString()
            cliente.facturacion = params.facturacion
            cliente.estadoCtaDigital = params.estadoCtaDigital
            cliente.telefonoDomContacto = params.telefonoDomContacto
            cliente.telefonoTrabContacto = params.telefonoTrabContacto
            cliente.celularContacto = params.celularContacto
        }

        cliente.tipoCobro = params.tipoCobro


        if(subestadoGestion.type.toString().equalsIgnoreCase("Rellamada")){
            cliente.fechaRellamada = new Date().parse('yyyy-MM-dd HH:mm:ss', params.recall.toString().replace('/','-') + ':00')
        }else {
            cliente.fechaRellamada = null
        }

        cliente.estadoGestion = estadoGestion.nombre
        cliente.subestadoGestion = subestadoGestion
        cliente.motivoNoDesea = subSubestadoGestion
        cliente.intentos = intentos+1
        cliente.fechaGestion = fechaActual
        cliente.usuario = usuario
        cliente.nombreVendedor = usuario.nombre
        cliente.observaciones = removeSpecialCharacters(params.observaciones.toString())

        //Se guarda informacion en el historial
        HistorialNovedades historial = new HistorialNovedades()
        historial.cliente = ClientesNovedades.findById(cliente.id.toLong())
        historial.cedula = cliente.idTitular
        historial.nombre = cliente.nombreCompletoTitular
        historial.estadoGestion = cliente.estadoGestion
        historial.subestadoGestion = cliente.subestadoGestion
        historial.motivoNoDesea = cliente.motivoNoDesea
        historial.fechaGestion = fechaActual
        historial.intentos = cliente.intentos
        historial.nombreVendedor = cliente.nombreVendedor
        historial.observacionesGestion = cliente.observaciones
        historial.nombreBase = cliente.nombreBase
        historial.usuario = cliente.usuario
        historial.save(flush: true)
        redirect(uri: "/gestionNovedades/index")
    }

    private removeSpecialCharacters(value){
        if(value != null){
            def newValue = value.replace("-"," ").replace("!","").replace("@","").replace("#","").replace("\$","")
                    .replace("&","").replace("/","").replace("(","").replace(")","").replace("=","")
                    .replace("?","").replace("¿","").replace("ç","").replace("{","").replace("}","")
                    .replace("\\","").replace("á","a").replace("é","e").replace("í","i").replace("ó","o")
                    .replace("ú","u").replace("\"","").replace("Á","A").replace("É","E").replace("Í","I")
                    .replace("Ó","O").replace("Ú","U").replace("\'","").replace("  "," ").replace("  "," ")
                    .replace("  "," ").replace("%","").replace(".","").replace(",","").replace("º","")
                    .replace("ª","").replace("|","").replace("\$","").replace("¬","").replace("%","")
                    .replace("*","").replace("+","").replace("_","").replace("Ñ", "N").replace("ñ", "n")
            return newValue
        }
    }

}
