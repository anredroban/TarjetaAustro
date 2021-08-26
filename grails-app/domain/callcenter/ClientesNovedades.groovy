package callcenter

import com.pw.security.Usuario

class ClientesNovedades {

    //Datos Base
    String fecha
    String idTitular
    String nombreCompletoTitular
    String cantidadTarjetasAdi
    String ciudadTrabajo
    String provinciaTrabajo
    String direccionTrabajo
    String ciudadDomicilio
    String provinciaDomicilio
    String direccionDomicilio
    String celular
    String telefonoTrabajo
    String telefonoCasa
    String proceso
    String lugarEntrega
    String estado
    String errorInformacion
    String revisionDireccionDomicilio
    String revisionDireccionTrabajo
    String callCenter

    //Datos gestión
    String provinciaDomic
    String ciudadDomic
    String callePrincipalDomic
    String numeracionDomic
    String calleTransversalDomic
    String sectorDomic
    String tipoVivienda
    String referenciaDomic

    String provinciaTrab
    String ciudadTrab
    String callePrincipalTrab
    String numeracionTrab
    String calleTransversalTrab
    String sectorTrab
    String tipoTrab
    String referenciaTrab
    String entrega
    String nombreContacto
    String rangoVisita
    String celularContacto
    String telefonoTrabContacto
    String telefonoDomContacto
    String estadoCtaDigital
    String facturacion
    String tipoCobro


    //Datos típicos
    Date fechaGestion
    int intentos
    String estadoGestion
    Subestado subestadoGestion
    String motivoNoDesea
    Usuario usuario
    String nombreBase
    String nombreVendedor
    Date fechaRellamada
    String observaciones


    static constraints = {
        fecha nullable: true
        idTitular nullable: true
        nombreCompletoTitular nullable: true
        cantidadTarjetasAdi nullable: true
        ciudadTrabajo nullable: true
        provinciaTrabajo nullable: true
        direccionTrabajo nullable: true
        ciudadDomicilio nullable: true
        provinciaDomicilio nullable: true
        direccionDomicilio nullable: true
        celular nullable: true
        telefonoTrabajo nullable: true
        telefonoCasa nullable: true
        proceso nullable: true
        lugarEntrega nullable: true
        estado nullable: true
        errorInformacion nullable: true
        revisionDireccionDomicilio nullable: true
        revisionDireccionTrabajo nullable: true
        callCenter nullable: true

        provinciaDomic nullable: true
        ciudadDomic nullable: true
        callePrincipalDomic nullable: true
        numeracionDomic nullable: true
        calleTransversalDomic nullable: true
        sectorDomic nullable: true
        tipoVivienda nullable: true
        referenciaDomic nullable: true
        provinciaDomic nullable: true
        ciudadDomic nullable: true
        callePrincipalDomic nullable: true
        numeracionDomic nullable: true
        calleTransversalDomic nullable: true
        sectorDomic nullable: true
        tipoVivienda nullable: true
        referenciaDomic nullable: true
        provinciaTrab nullable: true
        ciudadTrab nullable: true
        callePrincipalTrab nullable: true
        numeracionTrab nullable: true
        calleTransversalTrab nullable: true
        sectorTrab nullable: true
        referenciaTrab nullable: true
        entrega nullable: true
        nombreContacto nullable: true
        rangoVisita nullable: true
        celularContacto nullable: true
        telefonoTrabContacto nullable: true
        telefonoDomContacto nullable: true
        estadoCtaDigital nullable: true
        facturacion nullable: true
        tipoCobro nullable: true

        fechaGestion nullable: true
        intentos nullable: true
        estadoGestion nullable: true
        subestadoGestion nullable: true
        motivoNoDesea nullable: true
        usuario nullable: true
        nombreBase nullable: true
        nombreVendedor nullable: true
        fechaRellamada nullable: true
        observaciones nullable: true
        tipoTrab nullable: true
    }

    static mapping = {
        version false
        observaciones type: 'text'
    }

    static String[] getFields(){
        String[] fields = [
                "fecha",
                "idTitular",
                "nombreCompletoTitular",
                "cantidadTarjetasAdi",
                "ciudadTrabajo",
                "provinciaTrabajo",
                "direccionTrabajo",
                "ciudadDomicilio",
                "provinciaDomicilio",
                "direccionDomicilio",
                "celular",
                "telefonoTrabajo",
                "telefonoCasa",
                "proceso",
                "lugarEntrega",
                "estado",
                "errorInformacion",
                "revisionDireccionDomicilio",
                "revisionDireccionTrabajo",
                "callCenter"
        ]
        return fields
    }

}
