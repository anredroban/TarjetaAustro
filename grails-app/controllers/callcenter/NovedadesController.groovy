package callcenter

import com.pw.security.Usuario

class NovedadesController {

    static beforeInterceptor = {
        String accion = actionUri;
        if(!accion.equals("/usuario/login") && !accion.equals("/usuario/logout")){
            if(!session.user){
                redirect(uri: "/usuario/login");
                return false;
            }else{
                boolean tienePermiso = utilitarios.Util.checkAccess(session.user.usuario, accion)
                if(!tienePermiso){
                    render "No tienes permiso para ingresar a este sitio-> "+accion;
                }
            }
        }
    }

    def index() {
        Usuario usuario = Usuario.findById(session.user.id.toString().toLong())
        def clients = Clientes.withCriteria {
            eq('usuario',usuario)
            subestadoGestion {
                or{
                    eq('type', Subestado.constraints.type.inList[0].toString())
                    eq('type', Subestado.constraints.type.inList[1].toString())
                }
            }
            order("intentos")
        }
        def clientsNoManagement = Clientes.withCriteria {
            eq('usuario',usuario)
            isNull('subestadoGestion')
        }

        clients.each {client ->
            clientsNoManagement.add(client)
        }
        [clientesGestionar: clientsNoManagement]
    }
}
