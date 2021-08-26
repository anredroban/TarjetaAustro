package callcenter

class AdicionalesCreadas {

    String campania
    String fechaCreacion
    String identificacionAdicional
    String nacionalidad
    String primerApellido
    String segundoApellido
    String primerNombre
    String segundoNombre
    String identificacionTitular
    String asesor
    String estado
    Date fechaCarga
    String nombreBase

    static constraints = {
        campania nulleble: true
        fechaCreacion nulleble: true
        identificacionAdicional nulleble: true
        nacionalidad nulleble: true
        primerApellido nulleble: true
        segundoApellido nulleble: true
        primerNombre nulleble: true
        segundoNombre nulleble: true
        identificacionTitular nulleble: true
        asesor nullable: true
        estado nullable: true
        fechaCarga nullable: true
        nombreBase nullable: true
    }

    static mapping = {
        version false
    }

    static String[] getFields(){
        return [
                "campania",
                "fechaCreacion",
                "identificacionAdicional",
                "nacionalidad",
                "primerApellido",
                "segundoApellido",
                "primerNombre",
                "segundoNombre",
                "identificacionTitular",
                "asesor",
                "estado"
        ]
    }

}
