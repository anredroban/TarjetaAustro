package callcenter

import com.pw.security.Usuario

class Adicional {

    Clientes clientes
    Usuario usuario
    String tipoIdentificacion
    String cedula
    String nacionalidad
    String provinciaNacimiento
    String ciudadNacimiento
    String primerApellido
    String segundoApellido
    String primerNombre
    String segundoNombre
    String nombreTarjeta
    String cupoOtorgado
    String fechaNacimiento
    String sexo
    String estadoCivil
    String parentesco
    String relacionLaboral
    String viveFamiliares
    String direccionAdicional
    String telefonoCelular
    String operadoraAdicional
    String correo
    String observaciones
    Boolean tarjetaCreada
    String cedulaConyugue
    String fechaNacimientoConyugue
    String nombreEmpresaTrabajo
    String creadas_nocreadas
    String imputable
    String detalle_imputable
    String fecha_envio_creacion
    String statusCourier
    String cicloCourier


    static constraints = {
        segundoApellido nullable: true
        segundoNombre nullable: true
        tipoIdentificacion nullable: true
        provinciaNacimiento nullable: true
        ciudadNacimiento nullable: true
        relacionLaboral nullable: true
        viveFamiliares nullable: true
        direccionAdicional nullable: true
        telefonoCelular nullable: true
        operadoraAdicional nullable: true
        correo nullable: true
        cedulaConyugue nullable: true
        fechaNacimientoConyugue nullable: true
        nombreEmpresaTrabajo nullable: true
        sexo inList: ['MASCULINO', 'FEMENINO']
        creadas_nocreadas nullable: true
        imputable nullable: true
        detalle_imputable nullable: true
        fecha_envio_creacion nullable: true
        statusCourier nullable: true
        cicloCourier nullable: true
        //estadoCivil inList: ['SOLTERO', 'CASADO', 'VIUDO', 'DIVORCIADO', 'UNION DE HECHO', 'UNION LEGALIZADA', 'UNION LIBRE']
        //parentesco inList:  ['CONYUGUE', 'PADRES', 'HERMANOS', 'HIJOS', 'HIJASTROS', 'TIOS', 'PRIMOS', 'SOBRINOS', 'ABUELOS', 'NIETOS', 'CUÑADOS']
    }

    static mapping = {
        version false
        tarjetaCreada defaultValue: "false"
    }
}
