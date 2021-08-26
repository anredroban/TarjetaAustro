package callcenter
import java.util.Date;
import com.pw.security.*;

class Clientes {

	//Campos de la base
	String tipoIdentificacion
	String identificacion
	String emisionEstadoCuenta
	String codigoSucursal
	String codigoOficina
	String codigoCiudad
	String codigoParroquia
	String profesion
	String origenIngresos
	String rangoIngresosMes
	String patrimonio
	String valorVivienda
	String fechaInicioResidencia
	String relacionLaboral
	String fechaIniTrabActual
	String fechaIniTrabAnterior
	String cargasFamiliares
	String binPlastico
	String afinidad
	String tipoIdenReferencia
	String identificacionReferencia
	String apellido1Referencia
	String apellido2Referencia
	String nombre1Referencia
	String nombre2Referencia
	String direccionRefencia
	String telefonoRefencia
	String parentescoRefencia

	String nombre
	String nombres
	String apellidos
	String cuenta
	String cupo
	String cupoDisponible
	String telefono1
	String email
	String producto
	String familia
	String direccion
	String telefono2
	String telefono3
	String telefono4
	String telefono5
	String telefono6
	String telefono7
	String telefono8
	String telefono9
	String telefono10
	String ciudad
	String genero
	String estadoCivil
	String nombreEmpresa //campo utilizado para el nombre de la empresa

	//Nuevos campos 20/01/2019
	String apellidoPaterno
	String apellidoMaterno
	String primerNombre
	String segundoNombre
	String nacionalidad

	/*----Encuesta----*/
	//Domicilio
	String provinciaDomic
	String ciudadDomic
	String callePrincipalDomic
	String numeracionDomic
	String calleTransversalDomic
	String sectorDomic
	String tipoVivienda
	String referenciaDomic
	String informacionConcatenadaDom
	String caracteresDom

	//Trabajo
	String provinciaTrab
	String ciudadTrab
	String callePrincipalTrab
	String numeracionTrab
	String calleTransversalTrab
	String sectorTrab
	String tipoTrab
	String referenciaTrab
	String informacionConcatenadaTrab
	String caracteresTrab
	String entrega
	String nombreContacto
	String rangoVisita
	String celularContacto
	String telefonoTrabContacto
	String telefonoDomContacto
	String estadoCtaDigital
	String facturacion
	String tipoCobro

	String nivelEstudios
	String actividadEconomica
	String fechaNacimiento
	String numeroCuenta
	String seguroDesgravamen
	String lugarNacimiento
	String marcaBin

	//Nuevo campo encuesta 20181005
	String telefonoContactado

	/*--Fin Encuesta----*/

	//Nuevos campos Gestion - Giovanny Granda
	Date fechaGestion
	int intentos
	String estadoGestion
	Subestado subestadoGestion
	String asistenciaExtraTotal
	String asistanciaExequial
	String subSubEstado
	Usuario usuario
	String nombreBase
	String nombreVendedor
	Date fechaRellamada
	String observaciones
	String motivoSubSubEstado
	String codigoAsignacion
	String registroExitoso
	String agendamientoAsesor
	boolean isActive

	String asistencia247
	String debitoAsistencia
	String cobertura247
	String tipoCobro247
	//Campo de base
	String asist247

	/*Creacion de campos solicitados por el area de Reporting 2019-05-11*/
	String codigoCampania
	String gama
	String regional
	String rangoEdad
	String rangoCupo
	String segmentacionAd1
	String segmentacionAd2
	String segmentacionAd3
	String segmentacionAd4
	String segmentacionAd5
	String easyCodeRegional
	String easyCodeEdad
	String easyCodeCupo
	String easyCodeEdadCupo
	String easyCodeGamaEdad
	String easyCodeAd1
	String easyCodeAd2
	String easyCodeAd3
	String easyCodeAd4
	String easyCodeAd5
	String prioridadCampania
	String fechaCaducidad
	String deudaProtegida
	String metaContactabilidad
	String metaEfectividadTelefonica
	String metaEfectividadReal
	String tipoGestion
	String plataforma
	String bloqueSegmentacion
	String alimentacion
	String estadoTelefonoContactado
	String nombreCampania
	String statusCourier
	String cicloCourier

	static constraints = {

		tipoIdentificacion nullable: true
		emisionEstadoCuenta nullable: true
		codigoSucursal nullable: true
		codigoOficina nullable: true
		codigoCiudad nullable: true
		codigoParroquia nullable: true
		profesion nullable: true
		origenIngresos nullable: true
		rangoIngresosMes nullable: true
		patrimonio nullable: true
		valorVivienda nullable: true
		fechaInicioResidencia nullable: true
		relacionLaboral nullable: true
		fechaIniTrabActual nullable: true
		fechaIniTrabAnterior nullable: true
		cargasFamiliares nullable: true
		binPlastico nullable: true
		afinidad nullable: true
		tipoIdenReferencia nullable: true
		identificacionReferencia nullable: true
		apellido1Referencia nullable: true
		apellido2Referencia nullable: true
		nombre1Referencia nullable: true
		nombre2Referencia nullable: true
		direccionRefencia nullable: true
		telefonoRefencia nullable: true
		parentescoRefencia nullable: true


		//Campos de la base
		cuenta nullable: true
		identificacion nullable: true
		nombres nullable: true
		nombre nullable: true
		apellidos nullable: true
		email nullable: true
		producto nullable: true
		familia nullable: true
		cupo nullable: true
		cupoDisponible nullable: true
		direccion nullable: true
		telefono1 nullable: true
		telefono2 nullable: true
		telefono3 nullable: true
		telefono4 nullable: true
		telefono5 nullable: true
		telefono6 nullable: true
		telefono7 nullable: true
		telefono8 nullable: true
		telefono9 nullable: true
		telefono10 nullable: true
		genero nullable: true
		estadoCivil nullable: true
		ciudad  nullable: true
		fechaGestion nullable: true
		intentos nullable: true
		estadoGestion nullable: true
		subestadoGestion nullable: true
		subSubEstado nullable: true
		usuario nullable: true
		nombreBase nullable: true
		nombreVendedor nullable: true
		fechaRellamada nullable: true
		observaciones nullable: true
		provinciaDomic nullable: true
		ciudadDomic nullable: true
		callePrincipalDomic nullable: true
		numeracionDomic nullable: true
		calleTransversalDomic nullable: true
		sectorDomic nullable: true
		tipoVivienda nullable: true
		referenciaDomic nullable: true
		informacionConcatenadaDom nullable: true
		caracteresDom nullable: true
		provinciaTrab nullable: true
		ciudadTrab nullable: true
		callePrincipalTrab nullable: true
		numeracionTrab nullable: true
		calleTransversalTrab nullable: true
		sectorTrab nullable: true
		tipoTrab nullable: true
		referenciaTrab nullable: true
		informacionConcatenadaTrab nullable: true
		nombreEmpresa nullable: true
		caracteresTrab nullable: true
		entrega nullable: true
		nombreContacto nullable: true
		rangoVisita nullable: true
		celularContacto nullable: true
		telefonoTrabContacto nullable: true
		telefonoDomContacto nullable: true
		estadoCtaDigital nullable: true
		facturacion nullable: true
		tipoCobro nullable: true
		asistanciaExequial nullable: true
		asistenciaExtraTotal nullable: true
		plataforma nullable: true
		bloqueSegmentacion nullable: true

		telefonoContactado nullable: true

		tipoVivienda inList: ['EDIFICIO', 'CASA', 'DEPARTAMENTO', 'CONJUNTO']
		entrega inList: ['DOMICILIO', 'TRABAJO']
		rangoVisita inList: ['MANANA', 'TARDE']

		nacionalidad nullable: true
		apellidoPaterno nullable: true
		apellidoMaterno nullable: true
		primerNombre nullable: true
		segundoNombre nullable: true

		fechaNacimiento nullable: true
		nivelEstudios nullable: true
		actividadEconomica nullable: true
		numeroCuenta nullable: true

		codigoAsignacion nullable: true

		asistencia247 nullable: true
		debitoAsistencia nullable: true

		cobertura247 nullable: true
		tipoCobro247 nullable: true
		asist247 nullable: true

		seguroDesgravamen nullable: true
		lugarNacimiento nullable: true

		marcaBin nullable: true
		registroExitoso nullable: true

		motivoSubSubEstado nullable: true
		agendamientoAsesor nullable: true
		codigoCampania nullable: true

		/*Creacion de campos solicitados por el area de Reporting 2019-05-11*/
		gama nullable: true
		regional nullable: true
		rangoEdad nullable: true
		rangoCupo nullable: true
		segmentacionAd1 nullable: true
		segmentacionAd2 nullable: true
		segmentacionAd3 nullable: true
		segmentacionAd4 nullable: true
		segmentacionAd5 nullable: true
		easyCodeRegional nullable: true
		easyCodeEdad nullable: true
		easyCodeCupo nullable: true
		easyCodeEdadCupo nullable: true
		easyCodeGamaEdad nullable: true
		easyCodeAd1 nullable: true
		easyCodeAd2 nullable: true
		easyCodeAd3 nullable: true
		easyCodeAd4 nullable: true
		easyCodeAd5 nullable: true
		prioridadCampania nullable: true
		fechaCaducidad nullable: true
		deudaProtegida nullable: true
		metaContactabilidad nullable: true
		metaEfectividadTelefonica nullable: true
		metaEfectividadReal nullable: true
		tipoGestion nullable: true
		alimentacion nullable: true
		estadoTelefonoContactado nullable: true
		nombreCampania nullable: true
		statusCourier nullable: true
		cicloCourier nullable: true
	}
	
	static mapping = {
		version false
		observaciones type: 'text'
	}

	static String[] getFields(){
		String[] fields = [ "codigoCampania"
						   , "nombreCampania"
				           , "cuenta"
						   , "identificacion"
						   , "nombre"
						   , "email"
						   , "producto"
						   , "cupo"
						   , "cupoDisponible"
                           , "marcaBin"
							, "telefono1"
							, "telefono2"
							, "telefono3"
							, "telefono4"
							, "telefono5"
							, "telefono6"
							, "telefono7"
							, "telefono8"
							, "telefono9"
							, "telefono10"
						   , "numeroCuenta"
						   , "codigoAsignacion"
							, "gama"
							, "regional"
							, "rangoEdad"
							, "rangoCupo"
							, "segmentacionAd1"
							, "segmentacionAd2"
							, "segmentacionAd3"
							, "segmentacionAd4"
							, "segmentacionAd5"
							, "easyCodeRegional"
							, "easyCodeEdad"
							, "easyCodeCupo"
							, "easyCodeEdadCupo"
							, "easyCodeGamaEdad"
							, "easyCodeAd1"
							, "easyCodeAd2"
							, "easyCodeAd3"
							, "easyCodeAd4"
							, "easyCodeAd5"
							, "prioridadCampania"
							, "fechaCaducidad"
							, "deudaProtegida"
							, "metaContactabilidad"
							, "metaEfectividadTelefonica"
							, "metaEfectividadReal"
							, "tipoGestion"]
		return fields
	}
}
