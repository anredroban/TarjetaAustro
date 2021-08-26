package callcenter

class ConfiguracionController {

    //Evita que ingrese mediante url
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

    def cambiarInicioGestion(){
        if(params.nuevaFecha){
            Configuracion conf = Configuracion.findById(1)
            conf.fechaInicioGestion = new Date().parse('yyyy-MM-dd HH:mm:ss', params.nuevaFecha.toString().replace('/','-') + ' 00:00:00')
            conf.save(flush: true)
            redirect(uri: "/usuario/dashboard")
        }
    }

    def deshabilitarBases(){

    }

}
