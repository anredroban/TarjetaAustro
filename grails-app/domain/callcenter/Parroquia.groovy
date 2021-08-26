package callcenter

class Parroquia {

    String nombre
    String codigo
    Ciudad ciudad

    static constraints = {
        codigo nullable: true
    }

    static mapping = {
        version false
    }

}
