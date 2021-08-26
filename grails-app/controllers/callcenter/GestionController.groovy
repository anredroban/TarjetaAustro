package callcenter
import com.pw.security.*
import grails.converters.JSON
import groovy.json.JsonSlurper
import jxl.Cell
import jxl.Sheet
import jxl.Workbook
import jxl.WorkbookSettings
import liquibase.util.file.FilenameUtils
import telephony.LastUniqueId
import utilitarios.Util

import java.text.DecimalFormat

class GestionController {

	static beforeInterceptor = {
		String accion = actionUri;
		if(!accion.equals("/usuario/login") && !accion.equals("/usuario/logout")){
			if(!session.user){
				redirect(uri: "/usuario/login");
				return false;
			}else{
				boolean tienePermiso = utilitarios.Util.checkAccess(session.user.usuario, accion)
				if(!tienePermiso){
					render "No tienes permiso para ingresar a este sitio-> "+accion;
				}
			}
		}
	}

	/**
	 * @author Giovanny Granda
	 * Muestra en pantalla los clientes asignados
	 * @return
	 */
	def index() {
		Usuario usuario = Usuario.findById(session.user.id);
		def plataforma = 'PURE CLOUD'
//		def clientesGestionar = Clientes.executeQuery("from Clientes c where subestadoGestion.rellamar = 'SI' and usuario = :usuario order by c.intentos", [usuario: usuario])

		def clients = Clientes.withCriteria {
			eq('usuario',usuario)
			eq('isActive', true)
			notEqual('plataforma', plataforma)
			subestadoGestion {
				or{
					eq('type', Subestado.constraints.type.inList[0].toString())
					eq('type', Subestado.constraints.type.inList[1].toString())
				}
			}
			order("intentos")
		}
		def clientsNoManagement = Clientes.withCriteria {
			eq('usuario',usuario)
			eq('isActive', true)
			isNull('subestadoGestion')
			notEqual('plataforma', plataforma)
		}

		clients.each {client ->
			clientsNoManagement.add(client)
		}


		[clientesGestionar: clientsNoManagement]
	}

	/**
	 * @author Giovanny Granda
	 * Muestra la pantalla de gestion donde se hara rectificación de datos
	 * @param id
	 * @return
	 */
	def gestionCliente(long id){

		int idCliente = id
		Clientes cliente = Clientes.findById(idCliente)
		session.setAttribute("lastManageId",cliente.id)
		def lastUniqueId = LastUniqueId.findByClient(cliente)
		if(lastUniqueId != null){
			lastUniqueId.uniqueId = ""
		}
		DecimalFormat df = new DecimalFormat("0.00")
		String valorLiquido = ""
		valorLiquido = df.format(Double.parseDouble(cliente.cupo) * 0.2525)
        session.user
		[cliente: cliente,usuario: session.user, valorLiquido: valorLiquido]
	}

	/**
	 * @author Giovanny Granda
	 * Guarda la gestion del call center
	 * @param id
	 * @return
	 */
	def guardarCliente(){
		Usuario usuario = Usuario.findById(session.user.id.toString().toLong())
		Date fechaActual = new Date()
		Estado estadoGestion = Estado.findById(params.status.toString().toLong())
		Subestado subestadoGestion = Subestado.findById(params.substatus.toString().toLong())
		//String subSubestadoGestion = SubSubestado.findById(params.subSubStatus.toString().toLong()).name
		Clientes cliente = Clientes.findById(params.idCliente.toString().toLong())
		int intentos = cliente.intentos?: 0
		boolean guardarAdicionalesVar = false

		if(cliente.registroExitoso != "SI"){

		if(subestadoGestion.enableManagement){
			if (params.provinciaDomic.toString() != ""){
				cliente.provinciaDomic = removeSpecialCharacters(Provincia.findById(params.provinciaDomic.toString().toLong()).nombre)
			}
			if(params.ciudadDomic.toString() != ""){
				cliente.ciudadDomic = removeSpecialCharacters(Ciudad.findById(params.ciudadDomic.toString().toLong()).nombre)
			}
			if(params.sectorDomic.toString() != ""){
				cliente.sectorDomic = removeSpecialCharacters(Parroquia.findById(params.sectorDomic.toString().toLong()).nombre)
			}
			//DATOS DE DOMICILIO Y TELEFONOS
			cliente.callePrincipalDomic = removeSpecialCharacters(params.callePrincipalDomic.toString())
			cliente.numeracionDomic = removeSpecialCharacters(params.numeracionDomic.toString())
			cliente.calleTransversalDomic = removeSpecialCharacters(params.calleTransversalDomic.toString())
			cliente.referenciaDomic = removeSpecialCharacters(params.referenciaDomic.toString())
			cliente.seguroDesgravamen = params.seguroDesgravamen
			cliente.telefonoDomContacto = params.telefonoDomContacto
			cliente.telefonoTrabContacto = params.telefonoTrabContacto
			cliente.celularContacto = params.celularContacto
			//DATOS DE DOMICILIO Y TELEFONOS
			cliente.entrega = params.entrega
			if (params.provinciaTrab.toString() != ""){
				cliente.provinciaTrab = removeSpecialCharacters(Provincia.findById(params.provinciaTrab.toString().toLong()).nombre)
			}
			if(params.ciudadTrab.toString() != ""){
				cliente.ciudadTrab = removeSpecialCharacters(Ciudad.findById(params.ciudadTrab.toString().toLong()).nombre)
			}
			if(params.sectorTrab.toString() != ""){
				cliente.sectorTrab = removeSpecialCharacters(Parroquia.findById(params.sectorTrab.toString().toLong()).nombre)
			}
			cliente.callePrincipalTrab = removeSpecialCharacters(params.callePrincipalTrab.toString())
			cliente.numeracionTrab = removeSpecialCharacters(params.numeracionTrab.toString())
			cliente.calleTransversalTrab = removeSpecialCharacters(params.calleTransversalTrab.toString())
			cliente.referenciaTrab = removeSpecialCharacters(params.referenciaTrab.toString())
			cliente.nombreEmpresa = params.nombreEmpresaTrabajo
			guardarAdicionalesVar = true
			cliente.registroExitoso = 'SI'
		}

		cliente.asistanciaExequial = "NO"
		cliente.asistenciaExtraTotal = "NO"
		cliente.asistencia247 = "NO"


		if(subestadoGestion.type.toString().equalsIgnoreCase("Rellamada")){
			cliente.fechaRellamada = new Date().parse('yyyy-MM-dd HH:mm:ss', params.recall.toString().replace('/','-') + ':00')
			cliente.agendamientoAsesor = params.agendamiento
		}else {
			cliente.fechaRellamada = null
		}

		cliente.estadoGestion = estadoGestion.nombre
		if(estadoGestion.nombre == "CONTACTADO"){
			cliente.telefonoContactado = params.telefonoContactado
			cliente.estadoTelefonoContactado = params.estadoTelefonoContactado
		}
		if(estadoGestion.nombre == "NO CONTACTADO"){
			cliente.telefonoContactado = ""
			cliente.estadoTelefonoContactado = ""
		}
			if (params.subSubStatus != ""){
				String nombreSubSubEstado = SubSubestado.findById(params.subSubStatus.toString().toLong()).name
				cliente.subSubEstado = nombreSubSubEstado
			}
			else
				cliente.subSubEstado = ""

			if (params.motivosSubSubEstados != ""){
				String nombreMotivoSubSubEstado = MotivoSubEstado.findById(params.motivosSubSubEstados.toString().toLong()).nombre
				cliente.motivoSubSubEstado = nombreMotivoSubSubEstado
			}
			else
				cliente.motivoSubSubEstado = ""

		cliente.subestadoGestion = subestadoGestion
		//cliente.motivoNoDesea = subSubestadoGestion
		cliente.intentos = intentos+1
		cliente.fechaGestion = fechaActual
		cliente.usuario = usuario
		cliente.nombreVendedor = usuario.nombre
		cliente.observaciones = removeSpecialCharacters(params.observacionesGestion.toString())
		if(cliente.save(flush: true) && guardarAdicionalesVar){
			//println "primero por aca"
			guardarAdicionales(cliente, usuario, params.hidenAdicionales.toString())
		}

		//Se guarda informacion en el historial
		Historial historial = new Historial()
		historial.cliente = Clientes.findById(cliente.id.toLong())
		historial.cedula = cliente.identificacion
		historial.nombre = cliente.nombres
		historial.estadoGestion = cliente.estadoGestion
		historial.subestadoGestion = cliente.subestadoGestion
		historial.motivoNoDesea = cliente.subSubEstado
		historial.fechaGestion = fechaActual
		historial.intentos = cliente.intentos
		historial.nombreVendedor = cliente.nombreVendedor
		historial.observacionesGestion = cliente.observaciones
		historial.nombreBase = cliente.nombreBase
		historial.usuario = cliente.usuario
		historial.telefonoContactado = params.telefonoContactado
		historial.plataforma = cliente.plataforma
		historial.detalleTelefono1 = cliente.telefono1 + " " + params.estadoTel1
		historial.detalleTelefono2 = cliente.telefono2 + " " + params.estadoTel2
		historial.detalleTelefono3 = cliente.telefono3 + " " + params.estadoTel3
		historial.detalleTelefono4 = cliente.telefono4 + " " + params.estadoTel4
		historial.detalleTelefono5 = cliente.telefono5 + " " + params.estadoTel5
		historial.detalleTelefono6 = cliente.telefono6 + " " + params.estadoTel6
		historial.detalleTelefono7 = cliente.telefono7 + " " + params.estadoTel7
		historial.detalleTelefono8 = cliente.telefono8 + " " + params.estadoTel8
		historial.detalleTelefono9 = cliente.telefono9 + " " + params.estadoTel9
		historial.detalleTelefono10 = cliente.telefono10 + " " + params.estadoTel10
		historial.estadoTelefonoContactado = cliente.estadoTelefonoContactado
		historial.uniqueId = null
		historial.save(flush: true)
		session.setAttribute("lastManageId","")
		redirect(uri: "/gestion/index")
		}else{
			render(view: "modelValidacion",  model: [estado:cliente.estadoGestion, subestado: cliente.subestadoGestion.nombre, idCliente: cliente.id])
		}
	}

	def retirarBaseOld(){
		boolean updateRealizado = false
		int resultado = 0
		if(params.usuario != null && params.tipo != null && params.nombrebase != null){

			String desde = params.desde
			String hasta = params.hasta

			updateRealizado = true
			Usuario usuarioAdministrador = Usuario.findById(1)
			def subestados = Subestado.executeQuery("from Subestado where type = 'Regestionable'")
			String sql = "update Clientes set usuario = :usuario where (subestadoGestion in (:subestados) or subestadoGestion is null) and usuario != :usuario and cast(codigoAsignacion as integer) between :desde and :hasta and isActive = true"


			def condiciones = [usuario: usuarioAdministrador, subestados: subestados, desde: desde.toString().toInteger(), hasta: hasta.toString().toInteger()]
			String condicionUsuario = ""
			String condicionTipo = ""
			String condicionNombreBase = ""
			String condicionReferidos = ""

			if(params.usuario != ""){
				Usuario usuarioVendedor = Usuario.findById(params.usuario)
				condicionUsuario = " and usuario = :vendedor"
				condiciones.put("vendedor", usuarioVendedor)
			}

			if(params.tipo != ""){
				if(params.tipo == "REGESTIONABLE"){
					condicionTipo = " and intentos > 0"
				}
				if(params.tipo == "RELLAMADAS"){
					condicionTipo = " and intentos > 0 and agendamientoAsesor = 'AGENDAR PARA CUALQUIERA'"
				}
				if(params.tipo == "SIN GESTION"){
					condicionTipo = " and intentos = 0"
				}
			}

			if(params.nombrebase != ""){
				condicionNombreBase = " and nombreBase = :nombreBase"
				condiciones.put("nombreBase", params.nombrebase)
			}else{
				condicionReferidos = " and nombreBase != 'BASE_REFERIDOS'"
			}

			resultado = Clientes.executeUpdate(sql+condicionUsuario+condicionTipo+condicionNombreBase+condicionReferidos, condiciones)

		}
		[updateRealizado: updateRealizado, resultado: resultado]
	}

	def retirarBase(){
		boolean updateRealizado = false
		int resultado = 0
		if(params.usuario != null && params.tipo != null && params.nombrebase != null){

			String desde = params.desde
			String hasta = params.hasta

			updateRealizado = true
			Usuario usuarioAdministrador = Usuario.findById(1)

			def subestados
			if(params.tipo != "RELLAMADAS"){
				subestados = Subestado.executeQuery("from Subestado where type = 'Regestionable'")
			}else {
				subestados = Subestado.executeQuery("from Subestado where type = 'Rellamada'")
			}

			String sql = "update Clientes set usuario = :usuario where (subestadoGestion in (:subestados) or subestadoGestion is null) and usuario != :usuario and isActive = true"


			def condiciones = [usuario: usuarioAdministrador, subestados: subestados]
			String condicionUsuario = ""
			String condicionTipo = ""
			String condicionNombreBase = ""
			String condicionDesde = ""
			String condicionHasta = ""

			if(params.desde != ""){
				condicionDesde = " and cast(codigoAsignacion as integer) >= :desde"
				condiciones.put("desde", desde.toString().toInteger())
			}

			if(params.hasta != ""){
				condicionHasta = " and cast(codigoAsignacion as integer) <= :hasta"
				condiciones.put("hasta", hasta.toString().toInteger())
			}

			if(params.usuario != ""){
				Usuario usuarioVendedor = Usuario.findById(params.usuario)
				condicionUsuario = " and usuario = :vendedor"
				condiciones.put("vendedor", usuarioVendedor)
			}

			if(params.tipo != ""){
				if(params.tipo == "REGESTIONABLE"){
					condicionTipo = " and intentos > 0"
				}
				if(params.tipo == "RELLAMADAS"){
					condicionTipo = " and intentos > 0 and agendamientoAsesor = 'AGENDAR PARA CUALQUIERA'"
				}
				if(params.tipo == "SIN GESTION"){
					condicionTipo = " and intentos = 0"
				}
			}

			if(params.nombrebase != ""){
				condicionNombreBase = " and nombreBase = :nombreBase"
				condiciones.put("nombreBase", params.nombrebase)
			}

			//Util.saveLog(session.user.id, "Se ha retirado la base ${params.nombrebase}")

			resultado = Clientes.executeUpdate(sql+condicionUsuario+condicionTipo+condicionNombreBase+condicionDesde+condicionHasta, condiciones)

		}
		[updateRealizado: updateRealizado, resultado: resultado]
	}

	def retirarBaseNovedades(){
		boolean updateRealizado = false
		int resultado = 0
		if(params.usuario != null && params.tipo != null && params.nombrebase != null){

			updateRealizado = true
			Usuario usuarioAdministrador = Usuario.findById(1)
			def subestados = Subestado.executeQuery("from Subestado where type = 'Regestionable'")
			String sql = "update ClientesNovedades set usuario = :usuario where (subestadoGestion in (:subestados) or subestadoGestion is null) and usuario != :usuario"


			def condiciones = [usuario: usuarioAdministrador, subestados: subestados]
			String condicionUsuario = ""
			String condicionTipo = ""
			String condicionNombreBase = ""

			if(params.usuario != ""){
				Usuario usuarioVendedor = Usuario.findById(params.usuario)
				condicionUsuario = " and usuario = :vendedor"
				condiciones.put("vendedor", usuarioVendedor)
			}

			if(params.tipo != ""){
				if(params.tipo == "REGESTIONABLE"){
					condicionTipo = " and intentos > 0"
				}
				if(params.tipo == "SIN GESTION"){
					condicionTipo = " and intentos = 0"
				}
			}

			if(params.nombrebase != ""){
				condicionNombreBase = " and nombreBase = :nombreBase"
				condiciones.put("nombreBase", params.nombrebase)
			}

			resultado = ClientesNovedades.executeUpdate(sql+condicionUsuario+condicionTipo+condicionNombreBase, condiciones)

		}
		[updateRealizado: updateRealizado, resultado: resultado]
	}

	def cargarBase(){

	}

	def saveFile(){
		String[] formFields = Clientes.getFields()
		def file = request.getFile('file')
		Cell[] cells
		String[] headers
		if(file.empty){
			flash.message = "Por favor selecciona un archivo"
		}else{
			def webrootDir = servletContext.getRealPath(grailsApplication.config.uploadFolder) //app directory
			File fileDest = new File(webrootDir,file.getOriginalFilename())
			if(fileDest.mkdirs()){
				println "directory created"
			}else{
				println "directory not created or already exists"
			}
			file.transferTo(fileDest)

			//Reading Excel
			String ext = FilenameUtils.getExtension(fileDest.path)
			if(ext.equalsIgnoreCase("xls") || ext.equalsIgnoreCase("xlsx")){
				try {
					WorkbookSettings ws = new WorkbookSettings()
					ws.setEncoding("Cp1252")
					Workbook workbook = Workbook.getWorkbook(fileDest, ws)
					Sheet sheet = workbook.getSheet(0)
					cells = sheet.getRow(0)
					workbook.close()
				}catch (IOException ex){
					flash.error = "Problemas al cargar el archivo"
					render(view: "index")
				}
				headers = new String[cells.length]
				for(int i = 0; i < cells.length; i++){
					headers[i] = cells[i].getContents()
				}
				render(view: "sortExcel", model: [headers: headers, formFields:formFields, filePath:fileDest.path])
			}else{
				flash.error = "El archivo debe ser una hoja de cálculo de Excel"
				render(view: "index")
			}
		}
	}

	/**
	 * Status
	 * @return
	 */
	def getSubStatusByStatus(){
		if(params.id) {
			def status = Estado.findById(params.id)
			def subStatus = Subestado.findAllByEstado(status)
			def array = [subStatus.id, subStatus.nombre, subStatus.type, subStatus.enableManagement]
			render array as JSON
		}
	}

	/**
	 *
	 */
	def saveAditional(){

		if(request.xhr){
			def aditional = new Adicional()
			aditional.tipoIdentificacion = params.tipoIdentificacion
			aditional.cedula = params.cedula
			aditional.primerNombre = params.primerNombre
			aditional.segundoNombre = params.segundoNombre
			aditional.primerApellido = params.primerApellido
			aditional.segundoApellido = params.segundoApellido
			aditional.nombreTarjeta = params.nombreTarjeta
			aditional.cupoOtorgado = params.cupoOtorgado
			aditional.fechaNacimiento = params.fechaNacimiento
			aditional.estadoCivil = params.estadoCivil
			aditional.nacionalidad = params.nacionalidad
			aditional.parentesco = params.parentesco
			aditional.sexo = params.sexo
			aditional.observaciones = params.observaciones
			aditional.usuario = Usuario.findById(params.usuario.id.toString().toLong())
			aditional.clientes = Clientes.findById(params.clientes.id.toString().toLong())
			//println "paso por aqui"

			if(aditional.save()){
				render true
			}else{
				render false
			}
		}
	}

	/**
	 * make by someone
	 * @param value
	 * @return
	 */
	private removeSpecialCharacters(String value){
		if(value != null){
			def newValue = value.toUpperCase().replace("-"," ").replace("!","").replace("@","").replace("#","").replace("\$","")
					.replace("&","").replace("/","").replace("(","").replace(")","").replace("=","")
					.replace("?","").replace("¿","").replace("ç","").replace("{","").replace("}","")
					.replace("\\","").replace("\"","").replace("Á","A").replace("É","E").replace("Í","I")
					.replace("Ó","O").replace("Ú","U").replace("\'","").replace("  "," ").replace("  "," ")
					.replace("  "," ").replace(".","").replace(",","").replace("º","")
					.replace("ª","").replace("|","").replace("\$","").replace("¬","")
					.replace("*","").replace("+","").replace("_","").replace("Ñ", "N")
			return newValue
		}
	}

	/**
	 *
	 * @param cliente
	 * @param entradaJson
	 * @author Giovanny Granda Vera
	 * Toma una entrada en String donde están los adicionales del cliente dado, la transforma a JSON y guarda en la tabla
	 * de adicionales
	 */
	private guardarAdicionales(Clientes cliente, Usuario usuario, String entradaString){
		if(entradaString != ""){
			def jsonSlurper = new JsonSlurper()
			def object = jsonSlurper.parseText(entradaString)
			for (int i = 0; i < object.size(); i++){
				Adicional adicional = new Adicional()
				adicional.clientes = cliente
				adicional.usuario = usuario
				adicional.tipoIdentificacion = object[i].tipoIdentificacion
				adicional.cedula = object[i].cedula
				adicional.nacionalidad = removeSpecialCharacters(object[i].nacionalidad.toString())
				if(object[i].provinciaNacimiento.toString() != ""){
					adicional.provinciaNacimiento = removeSpecialCharacters(Provincia.findById(object[i].provinciaNacimiento.toString().toLong()).nombre)
				} else{
					adicional.provinciaNacimiento = ""
				}
				if(object[i].ciudadNacimiento.toString() != ""){
					adicional.ciudadNacimiento = removeSpecialCharacters(Ciudad.findById(object[i].ciudadNacimiento.toString().toLong()).nombre)
				}else{
					adicional.ciudadNacimiento = ""
				}
				//adicional.ciudadNacimiento = removeSpecialCharacters(Ciudad.findById(object[i].ciudadNacimiento.toString().toLong()).nombre)
				adicional.primerApellido = removeSpecialCharacters(object[i].primerApellido.toString())
				adicional.segundoApellido = removeSpecialCharacters(object[i].segundoApellido.toString())
				adicional.primerNombre = removeSpecialCharacters(object[i].primerNombre.toString())
				adicional.segundoNombre = removeSpecialCharacters(object[i].segundoNombre.toString())
				adicional.nombreTarjeta = removeSpecialCharacters(object[i].nombreTarjeta.toString())
				adicional.cupoOtorgado = removeSpecialCharacters(object[i].cupoOtorgado.toString())
				adicional.fechaNacimiento = object[i].fechaNacimiento
				adicional.sexo = object[i].sexo
				adicional.estadoCivil = object[i].estadoCivil
				adicional.parentesco = object[i].parentesco
				adicional.relacionLaboral = object[i].relacionLaboral
				adicional.telefonoCelular = object[i].telefonoCelular
				adicional.operadoraAdicional = object[i].operadoraAdicional
				adicional.correo = object[i].correo
				adicional.viveFamiliares = object[i].viveFamiliares
				adicional.direccionAdicional = removeSpecialCharacters(object[i].direccionAdicional.toString())//object[i].direccionAdicional
				adicional.cedulaConyugue = object[i].cedulaConyugue
				adicional.fechaNacimientoConyugue = object[i].fechaNacimientoConyugue
				adicional.tarjetaCreada = false
				adicional.observaciones = removeSpecialCharacters(object[i].observaciones.toString())
				//adicional.save(flush: true)
				//println "entre a adicional"
				def adicionalEnBase = Adicional.findByClientesAndCedula(cliente, adicional.cedula)
				if(!adicionalEnBase)
					adicional.save(flush: true)
			}
		}
	}

}
