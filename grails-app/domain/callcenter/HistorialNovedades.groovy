package callcenter

import com.pw.security.Usuario

class HistorialNovedades {

    String cedula
    String nombre
    String estadoGestion
    Subestado subestadoGestion
    String motivoNoDesea
    Date fechaGestion
    int intentos
    String nombreVendedor
    String observacionesGestion
    String nombreBase
    Usuario usuario
    ClientesNovedades cliente

    static constraints = {
        cedula nullable: true
        nombre nullable: true
        estadoGestion nullable: true
        subestadoGestion nullable: true
        motivoNoDesea nullable: true
        fechaGestion nullable: true
        intentos nullable: true
        nombreVendedor nullable: true
        observacionesGestion nullable: true
        nombreBase nullable: true
        usuario nullable: true
        cliente nullable: true
    }

    static mapping = {
        version false
        observacionesGestion type: "text"
    }
}
