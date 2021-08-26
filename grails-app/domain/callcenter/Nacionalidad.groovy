package callcenter

class Nacionalidad {

    String nombre
    String codigo

    static constraints = {
        codigo nullable: true
    }
    static mapping = {
        version false
    }
}
