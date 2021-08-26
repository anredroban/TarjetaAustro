package callcenter

class Oficina {
    String codigo
    String nombre
    Sucursal sucursal
    static constraints = {
        codigo nullable: true
        nombre nullable: true
        sucursal nullable: true
    }
    static mapping = {
        version false
    }
}
