package callcenter

class ActividadEconomica {
    String codigo
    String nombre
    static constraints = {
        codigo nullable: true
        nombre nullable: true
    }
    static mapping = {
        version false
    }
}
