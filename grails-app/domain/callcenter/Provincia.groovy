package callcenter

class Provincia {

    String nombre
    String regional
    String agencia


    static constraints = {
        regional nullable: true
        agencia nullable: true
    }

    static mapping = {
        version false
    }

}
