package callcenter
import com.pw.security.*
import groovy.sql.Sql
import org.hibernate.criterion.CriteriaSpecification

import jxl.Workbook
import jxl.WorkbookSettings
import jxl.format.Alignment
import jxl.format.Border
import jxl.format.BorderLineStyle
import jxl.format.Colour
import jxl.format.VerticalAlignment
import jxl.write.Label
import jxl.write.WritableFont
import jxl.write.WritableSheet
import jxl.write.WritableWorkbook
import telephony.BreakTime
import utilitarios.ExcelUtils
import utilitarios.Util

import java.text.DecimalFormat
import java.text.SimpleDateFormat

//import pl.touk.excel.export.WebXlsxExporter

class ReportesController {
	def dataSource
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

    def index() { }
	
	def ventasPorHora(){
		
	}

	def bitacora(){
		if(params.fechas){

			String callePrincipalEnt = ""
			String calleSecunEnt = ""
			String numeracionEntre = ""
			String referenciaEntre = ""

			//Obtenemos los datos
			Date[] fechas = Util.formatearFechasReporte(params.fechas.toString())
			ArrayList<SubSubestado> subestados = Subestado.findAllByEnableManagement(true)
			def nombresBase = params.list("nombreBase")
			def condiciones = [fechaInicio: fechas[0], fechaFin: fechas[1], subestados: subestados]
			String sql = "from Adicional a where a.clientes.fechaGestion between :fechaInicio and :fechaFin and a.clientes.subestadoGestion in (:subestados)"
			def adicionalesList = Clientes.executeQuery(sql, condiciones)
			int secuencial = 1
			//Empezamos a crear y llenar el Excel
			def webrootDir = servletContext.getRealPath(grailsApplication.config.uploadFolder)
			File file = new File(webrootDir, "temporal.xls")
			WorkbookSettings workbookSettings = new WorkbookSettings()
			workbookSettings.setLocale(new Locale("es", "ES"))
			workbookSettings.setEncoding("Cp1252")
			WritableWorkbook workbook = Workbook.createWorkbook(file, workbookSettings)
			workbook.createSheet("BitacoraAdicionales", 0)
			WritableFont cellFont = new WritableFont(WritableFont.createFont("Calibri"), 11, WritableFont.BOLD)
			WritableFont cellFont2 = new WritableFont(WritableFont.createFont("Calibri"), 11)
			WritableSheet sheet = workbook.getSheet(0)
			String[] headers = ["SEC", "ID TITULAR", "NOMBRE COMPLETO DEL TITULAR", "TIPO ID ADICIONAL", "NUMERO ID ADICIONAL", "APELLIDOS DEL ADICIONAL", "NOMBRES DEL ADICIONAL", "NOMBRES Y APELLIDOS CLIENTE ADICIONAL",
								"NOMBRE DEL PLASTICO CLIENTE ADICIONAL ", "CUPO DE TITULAR", "CUPO DE TC ADICIONAL", "PORCENTAJE", "PARENTESCO", "NACIONALIDAD", "FECHA DE NACIMIENTO", "LUGAR DE NACIMIENTO ADICIONAL (PROVINCIA Y CUIDAD)",
								"ESTADO CIVIL ADICIONAL","CEDULA CONYUGUE ADICIONAL", "FECHA NACIMIENTO CONYUGUE ADICIONAL", "RELACION LABORAL U OCUPACIÓN DEL ADICIONAL", "DIRECCION DE DOMICILIO ADICIONAL", "TELEFONO CELULAR ADICIONAL", "OPERADORA DEL ADICIONAL", "CORREO DEL ADICIONAL ", "FECHA DE VENTA", "SEXO DEL ADICIONAL", "FECHA DE VENTA",
								"MARCA (BIN)" , "TELÉFONO DOMICILIO TITULAR", "TELÉFONO OFICINA TITULAR", "CELULAR TITULAR", "DIRECCION DE ENTREGA TITULAR","NOMBRE EMPRESA TITULAR", "PROVINCIA DE ENTREGA TITULAR", "CIUDAD DE ENTREGA TITULAR", "NOMBRE VENDEDOR", "FECHA/HORA GESTION", "ID CLIENTE", "ID ADICIONAL","REGIONAL", "AGENCIA", "CODIGO CAMPAÑA", "OBSERVACIONES",
								"CREADAS NO CREADAS", "IMPUTABLE", "DETALLE IMPUTABLE", "FECHA ENVIO CREACION",'STATUS COURIER', 'CICLO COURIER','TELEFONO CONTACTADO',
								"SECTOR DOMICILIO","SECTOR TRABAJO"]
			ExcelUtils.addCells(headers, sheet, 0, Colour.GOLD, Alignment.LEFT, VerticalAlignment.CENTRE, cellFont, Border.ALL, BorderLineStyle.HAIR)
			for(int i = 0; i < adicionalesList.size(); i++){
				String[] campos = new String[headers.length]
				Adicional adi = adicionalesList.get(i)
				Clientes cli = adi.clientes
				campos[0] = secuencial++
				campos[1] = formatearCedula(cli.identificacion)
				campos[2] = cli.nombres
				campos[3] = adi.tipoIdentificacion
				campos[4] = formatearCedula(adi.cedula)
				campos[5] = adi.primerApellido.toUpperCase() + ' '+ adi.segundoApellido.toUpperCase()
				campos[6] = adi.primerNombre.toUpperCase() + ' '+ adi.segundoNombre.toUpperCase()
				campos[7] = adi.primerApellido.toUpperCase() + ' '+ adi.segundoApellido.toUpperCase()+ ' '+ adi.primerNombre.toUpperCase() + ' '+ adi.segundoNombre.toUpperCase()
				campos[8] = adi.nombreTarjeta
				campos[9] = cli.cupo
				campos[10] = adi.cupoOtorgado
				campos[11] = calcucoPorcentaje(cli.cupo, adi.cupoOtorgado) + "%"
				campos[12] = adi.parentesco
				campos[13] = adi.nacionalidad
				campos[14] = adi.fechaNacimiento
				if(adi.provinciaNacimiento.toString().equalsIgnoreCase("")){
					campos[15] = ""
				}else{
					campos[15] = adi.provinciaNacimiento + ' - ' + adi.ciudadNacimiento
				}
				campos[16] = adi.estadoCivil//formatearFecha(adi.fechaNacimiento.replace('/', '-'))
				campos[17] = adi.cedulaConyugue
				campos[18] = adi.fechaNacimientoConyugue
				campos[19] = adi.relacionLaboral
				if(adi.viveFamiliares.toString().equalsIgnoreCase("SI")){

					campos[20] = cli.callePrincipalDomic.toUpperCase()+' '+cli.numeracionDomic+' '+cli.calleTransversalDomic.toUpperCase() +' '+ cli.referenciaDomic.toUpperCase()
				}else{
					campos[20] = adi.direccionAdicional.toString().toUpperCase()
				}
				campos[21] = adi.telefonoCelular
				campos[22] = adi.operadoraAdicional
				campos[23] = adi.correo
				campos[24] = formatearFechaVenta(cli.fechaGestion.toString().substring(0,10))//cli.fechaGestion.toString().substring(0,10)
				campos[25] = adi.sexo//formatearCedula(cli.identificacion)//idTitular
				campos[26] = formatearFechaVenta(cli.fechaGestion.toString().substring(0,10))//0cli.fechaGestion.toString().substring(0,10)
				campos[27] = cli.marcaBin
				campos[28] = cli.telefonoDomContacto
				campos[29] = cli.telefonoTrabContacto
				campos[30] = cli.celularContacto
				if(cli.callePrincipalTrab != null){
					callePrincipalEnt = cli.callePrincipalTrab.toUpperCase()
				}else{
					callePrincipalEnt = ""
				}
				if(cli.numeracionTrab != null){
					numeracionEntre = cli.numeracionTrab.toUpperCase()
				}else{
					numeracionEntre = ""
				}
				if(cli.calleTransversalTrab != null){
					calleSecunEnt = cli.calleTransversalTrab.toUpperCase()
				}else{
					calleSecunEnt = ""
				}
				if(cli.referenciaTrab != null){
					referenciaEntre = cli.referenciaTrab.toUpperCase()
				}else{
					referenciaEntre = ""
				}
				campos[31] = callePrincipalEnt+' '+numeracionEntre+' '+calleSecunEnt +' '+referenciaEntre//Datos de Entrega
				campos[32] = cli.nombreEmpresa
				campos[33] = cli.provinciaDomic
				campos[34] = cli.ciudadDomic
				campos[35] = cli.nombreVendedor
				campos[36] = cli.fechaGestion.toString()
				campos[37] = cli.id
				campos[38] = adi.id
				campos[39] = Provincia.findByNombre(cli.provinciaDomic.toString()).regional
				campos[40] = Provincia.findByNombre(cli.provinciaDomic.toString()).agencia
				campos[41] = cli.codigoCampania
				campos[42] = adi.observaciones
				campos[43] = adi.creadas_nocreadas
				campos[44] = adi.imputable
				campos[45] = adi.detalle_imputable
				campos[46] = adi.fecha_envio_creacion
				campos[47] = adi.statusCourier
				campos[48] = adi.cicloCourier
				campos[49] = cli.telefonoContactado
				campos[50] = cli.sectorDomic
				campos[51] = cli.sectorTrab

				ExcelUtils.addCells(campos, sheet, i+1, Colour.WHITE, Alignment.LEFT, VerticalAlignment.CENTRE, cellFont2, null, null)
			}

			workbook.write()
			workbook.close()
			response.setHeader("Content-disposition", "filename=BitacoraAdicionales.xls")
			response.setContentType("application/octet-stream")
			response.outputStream << file.getBytes()
			return
		}
	}

	private String calcucoPorcentaje(String variable1, String variable2){
		double data1 = 0
		double data2 = 0
		double resultado = 0
		DecimalFormat df = new DecimalFormat("#.00");
		data1 = Double.parseDouble(variable1)
		data2 = Double.parseDouble(variable2)
		resultado = ((data2 / data1)*100)
		df.format(resultado)
		//println df.format(resultado)
		return df.format(resultado)

	}

	private String formatearFechaVenta(String variable){
		String[] arrayFechas = variable.trim().split('-')
		String var1 =  arrayFechas[0]
		String var2 = arrayFechas[1]
		String var3 = arrayFechas[2]
		String resultado = var3 + '/' + var2 + '/' + var1
		//println resultado
		return resultado

	}




	def ventasPorUsuario(){
		boolean visualizarReporte = false;
		if(params.fechas){
			visualizarReporte = true;
			String[] fechas = params.fechas.trim().split('-');
			String inicio = fechas[0].replace('/', '-');
			String fin = fechas[1].replace('/', '-');
			Date fechaInicio = Date.parse("yyyy-MM-dd HH:mm:ss", inicio+" 00:00:00");
			Date fechaFin = Date.parse("yyyy-MM-dd HH:mm:ss", fin+" 23:59:59");
			Subestado subestado = Subestado.findByNombre("CU1 /Ubicado/Exitoso/Cumplió con el objetivo");
			def condiciones = [subestado:subestado, fechaInicio:fechaInicio, fechaFin:fechaFin];
			String condicionNombreBase = "";
			String base = "TODAS";
			if(params.nombrebase != ""){
				condicionNombreBase = "and nombreBase = :nombreBase";
				condiciones.put("nombreBase", params.nombrebase);
				base = params.nombrebase;
			}
			String sql = "select nombreVendedor, count(*) from Clientes where subestadoGestion = :subestado and fechaGestion between :fechaInicio and :fechaFin ${condicionNombreBase} group by nombreVendedor order by nombreVendedor";
			def ventas = Clientes.executeQuery(sql, condiciones);
			[ventas: ventas, inicio: inicio, fin:fin, base: base, visualizarReporte: visualizarReporte];
		}
	}
	def ventasPorCiudad(){

	}
	def indexReport(){

	}


	def estados(){

	}


	def contactabilidad(){
		boolean visualizarReporte = false;
		if(params.fechas){
			visualizarReporte = true;
			String[] fechas = params.fechas.trim().split('-');
			String inicio = fechas[0].replace('/', '-');
			String fin = fechas[1].replace('/', '-');
			Date fechaInicio = Date.parse("yyyy-MM-dd HH:mm:ss", inicio+" 00:00:00");
			Date fechaFin = Date.parse("yyyy-MM-dd HH:mm:ss", fin+" 23:59:59");
			String condicionUsuario = "";
			String condicionNombreBase = "";
			String base = "TODAS";
			String usuarioSeleccionado = "TODOS";
			Subestado subestado = Subestado.findByNombre("CU1 /Ubicado/Exitoso/Cumplió con el objetivo");
			def condicionesGestionados = [fechaInicio: fechaInicio, fechaFin: fechaFin];
			def condicionesVendidos = [fechaInicio: fechaInicio, fechaFin: fechaFin, subestado: subestado];
			if(params.nombrebase != ""){
				condicionNombreBase = "and nombreBase = :nombreBase";
				condicionesGestionados.put("nombreBase", params.nombrebase);
				condicionesVendidos.put("nombreBase", params.nombrebase);
				base = params.nombrebase;
			}
			if(params.usuario != ""){
				Usuario usuario = Usuario.findById(params.usuario);
				condicionUsuario = "and usuario = :usuario";
				condicionesGestionados.put("usuario", usuario);
				condicionesVendidos.put("usuario", usuario);
				usuarioSeleccionado = usuario.nombre;
			}
			String sqlGestionados = "select count(*) from Clientes where intentos > 0 and fechaGestion between :fechaInicio and :fechaFin ${condicionUsuario} ${condicionNombreBase}";
			String sqlContactados = "select count(*) from Clientes where estadoGestion = 'CONTACTADO' and fechaGestion between :fechaInicio and :fechaFin ${condicionUsuario} ${condicionNombreBase}";
			String sqlVendidos = "select count(*)from Clientes where subestadoGestion = :subestado and fechaGestion between :fechaInicio and :fechaFin ${condicionUsuario} ${condicionNombreBase}";
			def gestionados = Clientes.executeQuery(sqlGestionados, condicionesGestionados).get(0);
			def contactados = Clientes.executeQuery(sqlContactados, condicionesGestionados).get(0);
			def vendidos = Clientes.executeQuery(sqlVendidos, condicionesVendidos).get(0);
			[gestionados: gestionados, contactados: contactados, vendidos: vendidos, visualizarReporte: visualizarReporte, inicio: inicio, fin:fin, usuarioSeleccionado: usuarioSeleccionado, base: base];
		}
	}



	def gerencial(){
		boolean visualizarReporte = false;
		if(params.fechas){
			visualizarReporte = true;
			String[] fechas = params.fechas.trim().split('-');
			String inicio = fechas[0].replace('/', '-');
			String fin = fechas[1].replace('/', '-');
			Date fechaInicio = Date.parse("yyyy-MM-dd HH:mm:ss", inicio+" 00:00:00");
			Date fechaFin = Date.parse("yyyy-MM-dd HH:mm:ss", fin+" 23:59:59");
			String condicionNombreBase = "";
			def estados = ["CU1":0, "CU2": 0, "CU3":0, "CU4": 0, "CU5": 0, "CU6": 0, "CU7": 0, "CU8": 0,
			"NU3-301": 0, "NU3-303": 0, "NU3-304": 0, "NU3-305": 0, "NU3-307": 0, "NU3-308": 0, "NU1": 0,
			"NU2": 0, "NU4": 0, "NU5": 0, "NU6": 0, "NU7": 0, "NU8": 0]
			String[] keysEstados = estados.keySet()
			def condiciones = [fechaInicio: fechaInicio, fechaFin: fechaFin];
			if(params.nombrebase != ""){
				condicionNombreBase = "and nombreBase = :nombreBase";
				condiciones.put("nombreBase", params.nombrebase);
			}
			String sql = "select subestadoGestion.nombre, count(*) from Clientes where fechaGestion between :fechaInicio and :fechaFin ${condicionNombreBase} group by subestadoGestion.nombre";
			def baseGestionada = Clientes.executeQuery(sql, condiciones)
			condiciones.remove("fechaInicio")
			condiciones.remove("fechaFin")
			sql = "from Clientes where intentos = 0 ${condicionNombreBase}"
			int sinGestion = Clientes.executeQuery(sql, condiciones).size()
			sql = "from Clientes where intentos >= 0 ${condicionNombreBase}"
			int baseRecibida = Clientes.executeQuery(sql, condiciones).size()
			for(int i = 0; i < keysEstados.size(); i++){
				for(int j = 0; j < baseGestionada.size(); j++){
					if(baseGestionada.get(j)[0].split('/')[0].toString().replace(' ', '').equalsIgnoreCase(keysEstados[i])){
						estados[keysEstados[i]] = baseGestionada.get(j)[1]
						break
					}
				}
			}
			int regsGestionados = 0;
			for(int i = 0; i < estados.values().size(); i++){
				regsGestionados = regsGestionados + estados.values()[i]
			}
			[estados: estados, visualizarReporte: visualizarReporte, sinGestion: sinGestion, regsGestionados: regsGestionados, baseRecibida: baseRecibida]
		}
	}

	private int getCaracteres(String texto){
		return texto.length()
	}

	def detallesGestion(){

	}




	private String formatearFecha(String fecha){
		String salida = ""
		if(fecha != null && fecha.trim() != ""){
			String[] fechaArray = fecha.toString().split(' ')
			String[] fechaDescompuesta = fechaArray[0].split('-')
			salida = fechaDescompuesta[2] + "/" + fechaDescompuesta[1] + "/" + fechaDescompuesta[0]
		}
		return salida
	}

	private String getMesGestion(String fecha){
		String salida = ""
		if(fecha != null && fecha.trim() != ""){
			String[] fechaArray = fecha.toString().split(' ')
			String[] fechaDescompuesta = fechaArray[0].split('-')
			String[] meses = ["ENERO", "FEBRERO", "MARZO", "ABRIL", "MAYO", "JUNIO", "JULIO", "AGOSTO", "SEPTIEMBRE", "OCTUBRE", "NOVIEMBRE", "DICIEMBRE"]
			salida = meses[fechaDescompuesta[1].toInteger()-1]
		}
		return salida
	}

	private String getAnioGestion(String fecha){
		String salida = ""
		if(fecha != null && fecha.trim() != ""){
			String[] fechaArray = fecha.toString().split(' ')
			String[] fechaDescompuesta = fechaArray[0].split('-')
			salida = fechaDescompuesta[0]
		}
		return salida
	}

	private String formatearCedula(String cedula){
		cedula = cedula.trim()
		if(cedula.length() > 10){
			cedula = cedula.reverse().substring(0, 10).reverse()
		}else{
			cedula = cedula.padLeft(10, '0')
		}
		return cedula
	}

	private String concatenarNombres(String nombres, String apellidos){
		String nom = ""
		String ape = ""
		if(nombres != null)
			nom = nombres
		if(apellidos != null)
			ape = apellidos
		return nom + " " + ape
	}

	private String separarNombres(String nombres, String apellidos, String tipo){
		String separador = ""
		String [] partsNombres = nombres.trim().split(' ')
		String [] partsApellidos = apellidos.trim().split(' ')
		if(nombres != null){
			for (int i= 0; i < partsNombres.length; i++) {
				for (int j = 0; j < partsApellidos.length; j++) {
					if (tipo.equalsIgnoreCase("1")) {
						separador = partsNombres[0]
					}
					if (tipo.equalsIgnoreCase("2")) {
						separador = partsNombres[i]
					}
					if (tipo.equalsIgnoreCase("3")) {
						separador = partsApellidos[0]
					}
					if (tipo.equalsIgnoreCase("4")) {
						separador = partsApellidos[j]
						if (separador == null){
							separador = ""
						}
					}
				}
			}
		}

		return separador
	}

	private String separarApellidos(String apellidos, String tipo){
		String separador1 = ""
		//String [] partsNombres = nombres.trim().split(' ')
		String [] partsApe = apellidos.trim().split(' ')
		if(apellidos != null){
			for (int i= 0; i < partsApe.length; i++) {
			//	for (int j = 0; j < partsApellidos.length; j++) {
					if (tipo.equalsIgnoreCase("1")) {
						separador1 = partsApe[i]
					}
					if (tipo.equalsIgnoreCase("2")) {
						separador1 = partsApe[i]
					}
			//	}
			}
		}

		return separador1
	}


	def indicadoresGestion(){
		boolean visibilizar = false
		if(params.fechas){
			visibilizar = true
			DecimalFormat df = new DecimalFormat("#.00")
			Date[] fechas = Util.formatearFechasReporte(params.fechas.toString())
			def nombresBase = params.list("nombreBase")
			Date fechaInicio = fechas[0]
			Date fechaFin = fechas[1]

			int Exitosas = 0
			int Contactados = 0
			int NoDesea = 0
			int exitosasPdp = 0
			int exitosasCredito = 0
			double Efectividad = 0
			double EfectividadPdp = 0
			double EfectividadCredito = 0
			double NoDeseaPorcentaje = 0

			int totalGestionados = 0
			int totalContactados = 0
			int totalNoContactados = 0
			int totalExitosos = 0
			int totalCruzadasCU2 = 0
			int totalCruzadasCU3 = 0
			int totalNoDeseaCU5 = 0
			int totalTarjetasCreadas = 0
			int totalDiferenciaTarjetas = 0
			String totalPorcentajeContactabilidad = ""
			String totalPorcentajeEfectividad = ""
			String totalPorcentajeEfectividadPdp = ""
			String totalPorcentajeEfectividadCredito = ""
			String totalPorcentajeReales = ""
			def subestadosEfectivos = Subestado.executeQuery("from Subestado where id in (1)")
			def subestadosCruzadas = Subestado.executeQuery("from Subestado where id in (2)")
			def subestadosCU3 = Subestado.executeQuery("from Subestado where id in (3)")
			def subestadosCU5 = Subestado.executeQuery("from Subestado where id in (5)")
			//def ventasPorUsuario = Clientes.executeQuery("select nombreVendedor, count(*), substring(fechaGestion,1,10) from Historial where subestadoGestion = :subestadosEfectivos and fechaGestion between :fechaInicio and :fechaFin and cliente.nombreBase in (:nombresBase) group by nombreVendedor, substring(fechaGestion,1,10) order by nombreVendedor", [fechaInicio: fechaInicio, fechaFin: fechaFin, nombresBase: nombresBase, subestadosEfectivos: subestadosEfectivos])
			//def cruzadasPorUsuario = Clientes.executeQuery("select nombreVendedor, count(*), substring(fechaGestion,1,10) from Historial where (subSubEstado = 'PDP' or subSubEstado = 'CREDITO+PDP') and fechaGestion between :fechaInicio and :fechaFin and cliente.nombreBase in (:nombresBase) group by nombreVendedor , substring(fechaGestion,1,10) order by nombreVendedor", [fechaInicio: fechaInicio, fechaFin: fechaFin, nombresBase: nombresBase])
			//def CU3PorUsuario = Clientes.executeQuery("select nombreVendedor, count(*), substring(fechaGestion,1,10) from Historial where (subSubEstado = 'CREDITO' or subSubEstado = 'TDC+CREDITO' or subSubEstado = 'CREDITO+PDP') and fechaGestion between :fechaInicio and :fechaFin and cliente.nombreBase in (:nombresBase) group by nombreVendedor, substring(fechaGestion,1,10) order by nombreVendedor", [fechaInicio: fechaInicio, fechaFin: fechaFin, nombresBase: nombresBase])
			def noDeseaPorUsuario = Clientes.executeQuery("select nombreVendedor, count(*), substring(fechaGestion,1,10) from Historial where subestadoGestion in (:subestadosCU5) and fechaGestion between :fechaInicio and :fechaFin group by nombreVendedor , substring(fechaGestion,1,10) order by nombreVendedor", [subestadosCU5: subestadosCU5, fechaInicio: fechaInicio, fechaFin: fechaFin])
			def gestionadosAgente = Clientes.executeQuery("select nombreVendedor, count(*), substring(fechaGestion,1,10) from Historial where intentos != 0 and fechaGestion between :fechaInicio and :fechaFin group by nombreVendedor, substring(fechaGestion,1,10) order by nombreVendedor", [fechaInicio: fechaInicio, fechaFin: fechaFin])
			def contactadosAgente = Clientes.executeQuery("select nombreVendedor, count(*), substring(fechaGestion,1,10) from Historial where intentos != 0 and estadoGestion = 'CONTACTADO' and fechaGestion between :fechaInicio and :fechaFin group by nombreVendedor, substring(fechaGestion,1,10) order by nombreVendedor", [fechaInicio: fechaInicio, fechaFin: fechaFin])
			def noContactadosAgente = Clientes.executeQuery("select nombreVendedor, count(*), substring(fechaGestion,1,10) from Historial where intentos != 0 and estadoGestion = 'NO CONTACTADO' and fechaGestion between :fechaInicio and :fechaFin group by nombreVendedor, substring(fechaGestion,1,10) order by nombreVendedor", [fechaInicio: fechaInicio, fechaFin: fechaFin])
			def adicionalesPorUsuario = Adicional.executeQuery("select clientes.nombreVendedor, count(*), substring(clientes.fechaGestion,1,10) from Adicional where clientes.subestadoGestion in (:subestadosEfectivos) and clientes.fechaGestion between :fechaInicio and :fechaFin group by clientes.nombreVendedor, substring(clientes.fechaGestion,1,10) order by clientes.nombreVendedor", [subestadosEfectivos: subestadosEfectivos, fechaInicio: fechaInicio, fechaFin: fechaFin])

			String[][] tablaResult = new String[gestionadosAgente.size()][12]
			//Lleno la matriz de resultados con los resultados de las onsultas anteriores
			for(int i = 0; i < tablaResult.size(); i++){
				tablaResult[i][0] = gestionadosAgente[i][0]
				tablaResult[i][1] = gestionadosAgente[i][1]
				tablaResult[i][10] = gestionadosAgente[i][2]

				//Lleno información de contactados y % de contactabilidad
				for(int j = 0; j < contactadosAgente.size(); j++){
					if(contactadosAgente[j][0] == gestionadosAgente[i][0] && contactadosAgente[j][2] == gestionadosAgente[i][2]){
						tablaResult[i][2] = contactadosAgente[j][1]
						Contactados = tablaResult[i][2].toInteger()
						break
					}
				}
				if(tablaResult[i][2] == null){
					Contactados = 0
				}
				//Lleno información de no contactados
				/*for(int j = 0; j < noContactadosAgente.size(); j++){
                    if(noContactadosAgente[j][0] == gestionadosAgente[i][0] && noContactadosAgente[j][2] == gestionadosAgente[i][2]){
                        tablaResult[i][3] = noContactadosAgente[j][1]
                        break
                    }
                }*/
				//LLeno la información de las ventas
				for(int j = 0; j < adicionalesPorUsuario.size(); j++){
					if(adicionalesPorUsuario[j][0] == gestionadosAgente[i][0] && adicionalesPorUsuario[j][2] == gestionadosAgente[i][2]){
						tablaResult[i][4] = adicionalesPorUsuario[j][1]
						Exitosas = tablaResult[i][4].toInteger()
						break
					}
				}
				if(tablaResult[i][4] == null){
					Exitosas = 0
				}
				//LLeno la información de las ventas cruzadas
				/*for(int j = 0; j < cruzadasPorUsuario.size(); j++){
					if(cruzadasPorUsuario[j][0] == gestionadosAgente[i][0] && cruzadasPorUsuario[j][2] == gestionadosAgente[i][2]){
						tablaResult[i][5] = cruzadasPorUsuario[j][1]
						exitosasPdp = tablaResult[i][5].toInteger()
						break
					}
				}*/
				if(tablaResult[i][5] == null){
					exitosasPdp = 0
				}

				//LLeno la información de las ventas CU3
				/*for(int j = 0; j < CU3PorUsuario.size(); j++){
					if(CU3PorUsuario[j][0] == gestionadosAgente[i][0] && CU3PorUsuario[j][2] == gestionadosAgente[i][2]){
						tablaResult[i][6] = CU3PorUsuario[j][1]
						exitosasCredito = tablaResult[i][6].toInteger()
						break
					}
				}*/
				if(tablaResult[i][6] == null){
					exitosasCredito = 0
				}

				//LLeno la información de los No Desea
				for(int j = 0; j < noDeseaPorUsuario.size(); j++){
					if(noDeseaPorUsuario[j][0] == gestionadosAgente[i][0] && noDeseaPorUsuario[j][2] == gestionadosAgente[i][2]){
						tablaResult[i][7] = noDeseaPorUsuario[j][1]
						NoDesea = tablaResult[i][7].toInteger()
						break
					}
				}
				if(tablaResult[i][7] == null){
					NoDesea = 0
				}

				//Pra calcular los procentajes de efectividad y no desea
				if(Exitosas != 0 && Exitosas != null){
					Efectividad = (Exitosas / Contactados) * 100
					tablaResult[i][8] = df.format(Double.parseDouble(Efectividad.toString())).replace(",", ".")
					//tablaResult[i][8] = 0
				}else{
					tablaResult[i][8] = 0
				}

				if(exitosasPdp != 0 && exitosasPdp != null){
					EfectividadPdp = (exitosasPdp / Contactados) * 100
					tablaResult[i][3] = df.format(Double.parseDouble(EfectividadPdp.toString())).replace(",", ".")
				}else{
					tablaResult[i][3] = 0
				}

				if(NoDesea != 0 && NoDesea != null){
					NoDeseaPorcentaje = (NoDesea / Contactados) * 100
					tablaResult[i][9] = df.format(Double.parseDouble(NoDeseaPorcentaje.toString())).replace(",", ".")
					//tablaResult[i][9] = 0
				}else{
					tablaResult[i][9] = 0
				}

				if(exitosasCredito != 0 && exitosasCredito != null){
					EfectividadCredito = (exitosasCredito / Contactados) * 100
					tablaResult[i][11] = df.format(Double.parseDouble(EfectividadCredito.toString())).replace(",", ".")
					//tablaResult[i][9] = 0
				}else{
					tablaResult[i][11] = 0
				}
				//println(Contactados + " " + Exitosas + " " + NoDesea)
			}

			//Recorro la matriz de resultados para obtener los totales
			for(int i = 0; i < tablaResult.size(); i++){
				totalGestionados = totalGestionados + tablaResult[i][1].toInteger()
				if(tablaResult[i][2] != null)
					totalContactados = totalContactados + tablaResult[i][2].toInteger()
				/*if(tablaResult[i][3] != null)
                    totalNoContactados = totalNoContactados + tablaResult[i][3].toInteger()*/
				if(tablaResult[i][4] != null)
					totalExitosos = totalExitosos + tablaResult[i][4].toInteger()
				if(tablaResult[i][5] != null)
					totalCruzadasCU2 = totalCruzadasCU2 + tablaResult[i][5].toInteger()
				if(tablaResult[i][6] != null)
					totalCruzadasCU3 = totalCruzadasCU3 + tablaResult[i][6].toInteger()
				if(tablaResult[i][7] != null)
					totalNoDeseaCU5 = totalNoDeseaCU5 + tablaResult[i][7].toInteger()
			}

			if(totalGestionados == 0){
				totalPorcentajeContactabilidad = "0.00"
				totalPorcentajeEfectividad = "0.00"
				totalPorcentajeEfectividadPdp = "0.00"
				totalPorcentajeEfectividadCredito = "0.00"
			}else{
				totalPorcentajeContactabilidad = df.format((totalExitosos/totalContactados)*100)
				totalPorcentajeEfectividad = df.format((totalNoDeseaCU5/totalContactados)*100)
				totalPorcentajeEfectividadPdp = df.format((totalCruzadasCU2/totalContactados)*100)
				totalPorcentajeEfectividadCredito = df.format((totalCruzadasCU3/totalContactados)*100)
			}
			[visibilizar: visibilizar, tablaResult: tablaResult, totalGestionados: totalGestionados, totalContactados: totalContactados,
			 totalNoContactados: totalNoContactados, totalExitosos:totalExitosos, totalCruzadasCU2: totalCruzadasCU2, totalCruzadasCU3: totalCruzadasCU3,
			 totalNoDeseaCU5: totalNoDeseaCU5, totalPorcentajeContactabilidad: totalPorcentajeContactabilidad, totalPorcentajeEfectividad: totalPorcentajeEfectividad,
			 totalPorcentajeEfectividadPdp: totalPorcentajeEfectividadPdp, totalPorcentajeEfectividadCredito: totalPorcentajeEfectividadCredito]
		}
	}

	def tiemposBreak() {
		if (params.fechas) {
			Date[] fechas = Util.formatearFechasReporte(params.fechas.toString())
			ArrayList<SubSubestado> subestados = Subestado.executeQuery("from Subestado where id in (1)")
			def nombresBase = params.list("nombreBase")
			def condiciones = [fechaInicio: fechas[0], fechaFin: fechas[1]]
			String sqlPrincipales = "from BreakTime where dateBreak between :fechaInicio and :fechaFin"
			def principalesList = BreakTime.executeQuery(sqlPrincipales, condiciones)
			//Empezamos a crear y llenar el Excel
			def webrootDir = servletContext.getRealPath(grailsApplication.config.uploadFolder)
			File file = new File(webrootDir, "temporal.xls")
			WorkbookSettings workbookSettings = new WorkbookSettings()
			workbookSettings.setLocale(new Locale("es", "ES"))
			workbookSettings.setEncoding("UTF-8")
			WritableWorkbook workbook = Workbook.createWorkbook(file)
			workbook.createSheet("Clientes Efectivos", 0)
			WritableFont cellFont = new WritableFont(WritableFont.createFont("Calibri"), 11, WritableFont.BOLD)
			cellFont.setColour(Colour.WHITE);
			WritableFont cellFont2 = new WritableFont(WritableFont.createFont("Calibri"), 11)
			WritableSheet sheetPrincipales = workbook.getSheet(0)
			String[] headersPrincipales = ["FECHA/HORA", "TIEMPO", "OPCION", "NOMBRE USUARIO"]
			ExcelUtils.addCells(headersPrincipales, sheetPrincipales, 0, Colour.GREEN, Alignment.LEFT, VerticalAlignment.CENTRE, cellFont, Border.ALL, BorderLineStyle.THIN)
			for (int i = 0; i < principalesList.size(); i++) {
				String[] campos = new String[headersPrincipales.length]
				BreakTime cli = principalesList.get(i)
				campos[0] = cli.dateBreak.toString()
				campos[1] = cli.timeBreak.toString()
				campos[2] = cli.typeBreak
				campos[3] = cli.user.nombre
				ExcelUtils.addCells(campos, sheetPrincipales, i + 1, Colour.WHITE, Alignment.LEFT, VerticalAlignment.CENTRE, cellFont2, null, null)
			}
			workbook.write()
			workbook.close()
			response.setHeader("Content-disposition", "filename=tiemposBreakAsesores.xls")
			response.setContentType("application/octet-stream")
			response.outputStream << file.getBytes()
			return
		}
	}

	def baseGestionada(){
		if(params.fechas){
			Date[] fechas = Util.formatearFechasReporte(params.fechas.toString())
			def nombresBase = params.list("nombreBase")
			def subestados = params.list("subestados")
			def condiciones = [fechaInicio: fechas[0], fechaFin: fechas[1], nombresBase: nombresBase]
			String sql = "from Clientes where fechaGestion between :fechaInicio and :fechaFin and nombreBase in (:nombresBase)"
			def base = Clientes.executeQuery(sql, condiciones)

			//Empezamos a crear y llenar el Excel
			def webrootDir = servletContext.getRealPath(grailsApplication.config.uploadFolder)
			File file = new File(webrootDir, "temporal.xls")
			WorkbookSettings workbookSettings = new WorkbookSettings()
			workbookSettings.setLocale(new Locale("es", "ES"))
			workbookSettings.setEncoding("Cp1252")
			WritableWorkbook workbook = Workbook.createWorkbook(file, workbookSettings)
			workbook.createSheet("baseGestionada", 0)
			WritableSheet sheet = workbook.getSheet(0)
			String[] headers = [
					"CEDULA",
					"NOMBRES",
					"ESTADO",
					"SUBESTADO",
					"SUBSUBESTADO",
					"FECHA GESTION",
					"NOMBRE BASE",
					"NOMBRE VENDEDOR",
					"INTENTOS",
					"OBSERVACIONES",
					"TELEFONO CONTACTADO"
			]
			ExcelUtils.addCells(headers, sheet, 0, Colour.GRAY_25, Alignment.LEFT, VerticalAlignment.CENTRE, null, Border.ALL, BorderLineStyle.HAIR)

			for(int i = 0; i < base.size(); i++){
				String[] campos = new String[headers.length]
				Clientes c = base.get(i)

				campos[0] = c.identificacion
				campos[1] = c.nombres + " " + c.apellidos
				campos[2] = c.estadoGestion
				campos[3] = c.subestadoGestion.nombre
				campos[4] = c.subSubEstado
				campos[5] = c.fechaGestion.toString()
				campos[6] = c.nombreBase
				campos[7] = c.nombreVendedor
				campos[8] = c.intentos
				campos[9] = c.observaciones
				campos[10] = c.telefonoContactado
				ExcelUtils.addCells(campos, sheet, i+1, Colour.WHITE, Alignment.LEFT, VerticalAlignment.CENTRE, null, null, null)
			}
			workbook.write()
			workbook.close()
			response.setHeader("Content-disposition", "filename=baseGestionadaDiferido.xls")
			response.setContentType("application/octet-stream")
			response.outputStream << file.getBytes()
			return

		}
	}

	def bitacoraGestion(){
		if(params.nombreBase){
			//Obtenemos los datos
			ArrayList<SubSubestado> subestados = Subestado.executeQuery("from Subestado where id in (1,2,3)")
			def nombresBase = params.list("nombreBase")
			def condiciones = [nombresBase: nombresBase]
			String sqlPrincipales = "from Clientes where codigoCampania in (:nombresBase) order by subestadoGestion"
			def principalesList = Clientes.executeQuery(sqlPrincipales, condiciones)

			//Empezamos a crear y llenar el Excel
			def webrootDir = servletContext.getRealPath(grailsApplication.config.uploadFolder)
			File file = new File(webrootDir, "temporal.xls")
			WorkbookSettings workbookSettings = new WorkbookSettings()
			workbookSettings.setLocale(new Locale("es", "ES"))
			workbookSettings.setEncoding("Cp1252")
			workbookSettings.setUseTemporaryFileDuringWrite(true)
			WritableWorkbook workbook = Workbook.createWorkbook(file, workbookSettings)
			workbook.createSheet("GESTION", 0)
			WritableFont cellFont = new WritableFont(WritableFont.TAHOMA, 10)
			WritableFont cellFont2 = new WritableFont(WritableFont.createFont("Calibri"), 11)
			WritableSheet sheetPrincipales = workbook.getSheet(0)
			String[] headersPrincipales = ['CODIGOCAMPANA',	'AGENTE',	'USUARIO ID',	'TIPO_GESTION',	'IDENTIFICACION',	'NOMBRECLIENTE',
										   'FECHAGESTION',	'ESTATUS',	'ULTIMOTELEFONOCONTACTO',	'OBSERVACIONMOTIVONODESEA',	'NUMEROINTENTOS',
										   'ESTADO_GESTION',	'NOMBRE BASE',	'TELEFONO1',	'TELEFONO2',	'TELEFONO3',	'TELEFONO4',
										   'TELEFONO5',	'ADICIONALES',	'GAMA',	'REGIONAL',	'RANGO EDAD',	'RANGO CUPO',	'CADUCIDAD',	'ID SISTEMA', 'ALIMENTACION']
			ExcelUtils.addCells(headersPrincipales, sheetPrincipales, 0, Colour.GOLD, Alignment.LEFT, VerticalAlignment.CENTRE, cellFont, Border.ALL, BorderLineStyle.HAIR)
			for (int i = 0; i < principalesList.size(); i++){
				String[] campos = new String[headersPrincipales.length]
				Clientes princ = principalesList.get(i)
				campos[0] = princ.codigoCampania
				campos[1] = princ.nombreVendedor
				if (princ.usuario == null){
					campos[2] = princ.usuario
				}else{
					campos[2] = princ.usuario.id
				}
				campos[3] = princ.tipoGestion
				campos[4] = princ.identificacion
				campos[5] = princ.nombres
				if (princ.fechaGestion == null){
					campos[6] = princ.fechaGestion
				}else{
					campos[6] = princ.fechaGestion.toString().substring(0,10).replace("/","-")
				}
				if (princ.subestadoGestion == null){
					campos[7] = princ.subestadoGestion
				}else{
					campos[7] = princ.subestadoGestion.nombre
				}
				campos[8] = princ.telefonoContactado
				campos[9] = princ.subSubEstado
				campos[10] = princ.intentos
				campos[11] = princ.estadoGestion
				campos[12] = princ.nombreBase
				campos[13] = princ.telefono1
				campos[14] = princ.telefono2
				campos[15] = princ.telefono3
				campos[16] = princ.telefono4
				campos[17] = princ.telefono5
				campos[18] = ''
				campos[19] = princ.gama
				campos[20] = princ.regional
				campos[21] = princ.rangoEdad
				campos[22] = princ.rangoCupo
				campos[23] = princ.fechaCaducidad
				campos[24] = princ.id
				campos[25] = princ.alimentacion
				ExcelUtils.addCells(campos, sheetPrincipales, i+1, Colour.WHITE, Alignment.LEFT, VerticalAlignment.CENTRE, cellFont2, null, null)
			}

			workbook.write()
			workbook.close()
			response.setHeader("Content-disposition", "filename=bitacoraGestion.xls")
			response.setContentType("application/octet-stream")
			response.outputStream << file.getBytes()
			return
		}
	}

}
