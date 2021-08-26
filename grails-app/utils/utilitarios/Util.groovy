package utilitarios

import callcenter.*
import com.pw.security.Permiso
import com.pw.security.Rol
import com.pw.security.Sesion
import com.pw.security.Usuario

import java.text.DecimalFormat
import java.text.SimpleDateFormat

public class Util {

	static boolean checkAccess(String username, String controlador){
		boolean accesoPermitido = isAdmin(username)?true:false;
		def usuario = Usuario.findByUsuario(username);
		def rol = Rol.findById(usuario.rolId);
		def permiso = Permiso.findByDescripcion(controlador);

		//CREAR PERMISO SI NO EXISTE
		if(permiso == null){
			def nuevoPermiso = new Permiso()
			nuevoPermiso.descripcion = controlador
			if(nuevoPermiso.save(flush:true)){
				permiso = nuevoPermiso
				println "nuevo permiso creado"
			}else
			{
				println "no se pudo crear el permiso"
			}
		}

		//BUSCA SI EL PERMISO ESTA ASIGNADO AL ROL
		for(perm in rol.permisos){
			if(perm == permiso){
				accesoPermitido = true;
				break;
			}
		}
		return accesoPermitido;
	}
	
	static boolean isAdmin(String username){
		
		boolean isAdmin = false;
		def usuario = Usuario.findByUsuario(username);
		if(usuario.rol.nombre.equalsIgnoreCase("ADMINISTRADOR"))
			isAdmin = true;
		return isAdmin;
	}
	
	static boolean isOperator(String username){
		
		boolean isAdmin = false;
		def usuario = Usuario.findByUsuario(username);
		if(usuario.rol.nombre.equalsIgnoreCase("OPERADOR"))
			isAdmin = true;
		return isAdmin;
	}
	
	static int getCantidadRellamadas(String username){
		
		Date fechaInicio = Date.parse("yyyy-MM-dd HH:mm:ss", new Date().format("yyyy-MM-dd 00:00:00"));
		Date fechaFin = Date.parse("yyyy-MM-dd HH:mm:ss", new Date().format("yyyy-MM-dd 23:59:59"));
		def usuario = Usuario.findByUsuario(username);
		Subestado subestado = Subestado.findByNombre("RELLAMADA");
		int rellamadas = Clientes.executeQuery("from Clientes where subestadoGestion = :subestado and usuario = :usuario and fechaRellamada between :fechaInicio and :fechaFin", [subestado: subestado, usuario: usuario, fechaInicio: fechaInicio, fechaFin: fechaFin]).size();
		return rellamadas;
	}
	
	static int getCantidadVentas(String username){
		
		Date fechaInicio = Date.parse("yyyy-MM-dd HH:mm:ss", new Date().format("yyyy-MM-dd 00:00:00"));
		Date fechaFin = Date.parse("yyyy-MM-dd HH:mm:ss", new Date().format("yyyy-MM-dd 23:59:59"));
		def usuario = Usuario.findByUsuario(username);
		Subestado subestado = Subestado.findByNombre("ACEPTA");
		int aceptaciones = Clientes.executeQuery("from Clientes where subestadoGestion = :subestado and usuario = :usuario and fechaGestion between :fechaInicio and :fechaFin", [subestado: subestado, usuario: usuario, fechaInicio: fechaInicio, fechaFin: fechaFin]).size();
		return aceptaciones;
	}
	
	static int getCantidadVentas(){
		Date fechaInicio = Date.parse("yyyy-MM-dd HH:mm:ss", new Date().format("yyyy-MM-dd 00:00:00"))
		Date fechaFin = Date.parse("yyyy-MM-dd HH:mm:ss", new Date().format("yyyy-MM-dd 23:59:59"))
		def subestados = Subestado.executeQuery("from Subestado where id in (1, 3)")
		def ventas = Sesion.executeQuery("from Clientes c where c.subestadoGestion in (:subestados) and c.fechaGestion between :fechaInicio and :fechaFin", [subestados: subestados, fechaInicio: fechaInicio, fechaFin: fechaFin])
		return ventas.size()
	}

	static int getCantidadAdicionales(){
		Date fechaInicio = Date.parse("yyyy-MM-dd HH:mm:ss", new Date().format("yyyy-MM-dd 00:00:00"))
		Date fechaFin = Date.parse("yyyy-MM-dd HH:mm:ss", new Date().format("yyyy-MM-dd 23:59:59"))
		def subestados = Subestado.executeQuery("from Subestado where id in (1, 3)")
		def adicionales = Adicional.executeQuery("from Adicional a where a.clientes.subestadoGestion in (:subestados) and a.clientes.fechaGestion between :fechaInicio and :fechaFin", [subestados: subestados, fechaInicio: fechaInicio, fechaFin: fechaFin])
		return adicionales.size()
	}

	static int getAdicionalesMes(){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		Date fechaInicio = Date.parse("yyyy-MM-dd HH:mm:ss", cal.getTime().format("yyyy-MM-dd 00:00:00"))
		//Date fechaInicio = Configuracion.findById(1).fechaInicioGestion
		Date fechaFin = Date.parse("yyyy-MM-dd HH:mm:ss", new Date().format("yyyy-MM-dd 23:59:59"))
		def subestados = Subestado.executeQuery("from Subestado where id in (1, 3)")
		def adicionales = Adicional.executeQuery("from Adicional a where a.clientes.subestadoGestion in (:subestados) and a.clientes.fechaGestion between :fechaInicio and :fechaFin", [subestados: subestados, fechaInicio: fechaInicio, fechaFin: fechaFin])
		return adicionales.size()
	}



	static String getContactabilidadMensual(){
		String salida = "0%"
		Date fechaInicio = Configuracion.findById(1).fechaInicioGestion
		Date fechaFin = Date.parse("yyyy-MM-dd HH:mm:ss", new Date().format("yyyy-MM-dd 23:59:59"))
		int contactados = Clientes.executeQuery("select count(*) from Clientes where estadoGestion = 'CONTACTADO' and fechaGestion between :fechaInicio and :fechaFin", [fechaInicio: fechaInicio, fechaFin: fechaFin]).get(0)
		int gestionados = Clientes.executeQuery("select count(*) from Clientes where fechaGestion between :fechaInicio and :fechaFin", [fechaInicio: fechaInicio, fechaFin: fechaFin]).get(0)
		DecimalFormat df = new DecimalFormat("#.00")
		if(gestionados != 0)
			salida = (df.format((contactados/gestionados)*100)).toString()+"%"
		return salida

	}

	static int[] getCantidadAsistencias(){
		int cantidadExequial = 0
		int cantidadExtratotal = 0
		int cantidad247 = 0
		int[] salida = new int[4]
		Date fechaInicio = Date.parse("yyyy-MM-dd HH:mm:ss", new Date().format("yyyy-MM-dd 00:00:00"))
		Date fechaFin = Date.parse("yyyy-MM-dd HH:mm:ss", new Date().format("yyyy-MM-dd 23:59:59"))
		def subestados = Subestado.findAllByEnableManagement2(true)
		def ventas = Clientes.executeQuery("select asistanciaExequial, asistenciaExtraTotal,asistencia247 from Clientes c where c.subestadoGestion in (:subestados) and c.fechaGestion between :fechaInicio and :fechaFin", [subestados: subestados, fechaInicio: fechaInicio, fechaFin: fechaFin])
		for(int i = 0; i < ventas.size(); i++){
			if(ventas[i][0] == "SI")
				cantidadExequial++
			if(ventas[i][1] == "SI")
				cantidadExtratotal++
			if(ventas[i][2] == "SI")
				cantidad247++
		}
		salida[0] = cantidadExequial
		salida[1] = cantidadExtratotal
		salida[2] = cantidad247
		salida[3] = cantidadExequial+cantidadExtratotal+cantidad247
		return salida
	}

	static int[] getCantidadAsistenciasMes(){
		int cantidadExequial = 0
		int cantidadExtratotal = 0
		int cantidad247 = 0
		int[] salida = new int[4]
		Date fechaInicio = Configuracion.findById(1).fechaInicioGestion
		Date fechaFin = Date.parse("yyyy-MM-dd HH:mm:ss", new Date().format("yyyy-MM-dd 23:59:59"))
		def subestados = Subestado.findAllByEnableManagement2(true)
		def ventas = Clientes.executeQuery("select asistanciaExequial, asistenciaExtraTotal,asistencia247 from Clientes c where c.subestadoGestion in (:subestados) and c.fechaGestion between :fechaInicio and :fechaFin", [subestados: subestados, fechaInicio: fechaInicio, fechaFin: fechaFin])
		for(int i = 0; i < ventas.size(); i++){
			if(ventas[i][0] == "SI")
				cantidadExequial++
			if(ventas[i][1] == "SI")
				cantidadExtratotal++
			if(ventas[i][2] == "SI")
				cantidad247++
		}
		salida[0] = cantidadExequial
		salida[1] = cantidadExtratotal
		salida[2] = cantidad247
		salida[3] = cantidadExequial+cantidadExtratotal+cantidad247
		return salida
	}
	
	static int getContactados(){
		
		Date fechaInicio = Date.parse("yyyy-MM-dd HH:mm:ss", new Date().format("yyyy-MM-dd 00:00:00"))
		Date fechaFin = Date.parse("yyyy-MM-dd HH:mm:ss", new Date().format("yyyy-MM-dd 23:59:59"))
		int contactadosQuito = Clientes.executeQuery("from Clientes where estadoGestion = :estado and fechaGestion between :fechaInicio and :fechaFin", [estado: "CONTACTADO", fechaInicio: fechaInicio, fechaFin: fechaFin]).size()
		return contactadosQuito
	}

	static int getContactadosMes(){

		Date fechaInicio = Configuracion.findById(1).fechaInicioGestion
		Date fechaFin = Date.parse("yyyy-MM-dd HH:mm:ss", new Date().format("yyyy-MM-dd 23:59:59"))
		int contactadosQuito = Clientes.executeQuery("from Clientes where estadoGestion = :estado and fechaGestion between :fechaInicio and :fechaFin", [estado: "CONTACTADO", fechaInicio: fechaInicio, fechaFin: fechaFin]).size()
		return contactadosQuito
	}

	static ArrayList<Usuario> getOperadores(){
		Rol rol = Rol.findByNombre("OPERADOR")
		def operadores = Usuario.executeQuery("from Usuario where estado = 'ACTIVO' and rol = :rol", [rol: rol])
		return operadores
	}
	
	static int getOperadoresLogueados(){

		Date fechaInicio = Date.parse("yyyy-MM-dd HH:mm:ss", new Date().format("yyyy-MM-dd 00:00:00"))
		Date fechaFin = Date.parse("yyyy-MM-dd HH:mm:ss", new Date().format("yyyy-MM-dd 23:59:59"))
		Rol rol = Rol.findByNombre("OPERADOR")
		def usuarios = Sesion.executeQuery("select distinct s.usuario, max(s.fechaInicio) from Sesion s where s.fechaInicio between :fechaInicio and :fechaFin and s.usuario.rol = :rol and s.usuario.estado = 'ACTIVO' group by s.usuario", [fechaInicio: fechaInicio, fechaFin: fechaFin, rol: rol])
		int contador = 0
		for(int i = 0; i < usuarios.size(); i++){
			Sesion sesion = Sesion.findByUsuarioAndFechaInicio(usuarios[i][0], usuarios[i][1])
			if (sesion.fechaFin == null)
				contador++
		}

		return contador
	}

	static ArrayList<Usuario> getOperDisponiblesAsignacion(){
		ArrayList<Usuario> users = new ArrayList<>()
		Date fechaInicio = Date.parse("yyyy-MM-dd HH:mm:ss", new Date().format("yyyy-MM-dd 00:00:00"))
		Date fechaFin = Date.parse("yyyy-MM-dd HH:mm:ss", new Date().format("yyyy-MM-dd 23:59:59"))
		Rol rol = Rol.findByNombre("OPERADOR")
		def usuarios = Sesion.executeQuery("select distinct s.usuario, max(s.fechaInicio) from Sesion s where s.fechaInicio between :fechaInicio and :fechaFin and s.usuario.rol = :rol and s.usuario.estado = 'ACTIVO' group by s.usuario", [fechaInicio: fechaInicio, fechaFin: fechaFin, rol: rol])
		for(int i = 0; i < usuarios.size(); i++){
			Sesion sesion = Sesion.findByUsuarioAndFechaInicio(usuarios[i][0], usuarios[i][1])
			if (sesion.fechaFin == null)
				users.add(sesion.usuario)
		}
		return users
	}

	static ArrayList<SubSubestado> getSubestadosRegestionables(){
		Subestado subestado = Subestado.findById(8)
		def subestados = SubSubestado.executeQuery("from SubSubestado where subestado = :subestado", [subestado: subestado])
		return  subestados
	}

	static ArrayList<String> getNombresBase(){
		def bases = Clientes.executeQuery("select distinct nombreBase from Clientes where isActive = true")
	}
	static ArrayList<String> getCodigoCampania(){
		/*	def results = Clientes.withCriteria {
                projections{
                    distinct("nombreBase")
                }
                //eq('isActive', true)
            }
            return results*/
		def bases = Clientes.executeQuery("select distinct codigoCampania from Clientes where isActive = true")
	}

	static ArrayList<String> getBasesNoHabiles(){
		def bases = Clientes.executeQuery("select distinct nombreBase from Clientes where isActive = false")
	}

	static ArrayList<String> getNombresBaseNovedades(){
		def results = ClientesNovedades.withCriteria {
			projections{
				distinct("nombreBase")
			}
		}
		return results
	}
	
	static ArrayList<Rol> getRoles(){
		def roles = Rol.executeQuery("from Rol where nombre != 'ADMINISTRADOR'");
		return roles;
	}	
	
	static double redondearDecimales(double valorInicial, int numeroDecimales) {
		double parteEntera, resultado;
		resultado = valorInicial;
		parteEntera = Math.floor(resultado);
		resultado=(resultado-parteEntera)*Math.pow(10, numeroDecimales);
		resultado=Math.round(resultado);
		resultado=(resultado/Math.pow(10, numeroDecimales))+parteEntera;
		return resultado;
	}
	
	/**
	 * Search the extension and locate in main.gsp
	 * @author Esteban Preciado
	 */
	def static getExtension(String usuario){
		def extent = Usuario.findByUsuario(usuario).extent
		return extent
	}

	/**
	 * @author Giovanny Granda Vera
	 */
	public static ArrayList<String> getProductos(long idCliente){
		Clientes cliente = Clientes.findById(idCliente)
		ArrayList<String> productos = new ArrayList<>()
		productos.add(cliente.productoConvertirCupo)
		productos.add(cliente.productoConvertirCosto)
		productos.add("CHIP MASTERCARD BLACK LANPASS FYBECA")
		productos.add("CHIP MASTERCARD PLATINIUM LANPASS FYBECA")
		productos.add("CHIP MASTERCARD PREMIUM FYBECA")
		productos.add("CHIP MASTERCARD PRESTIGE LANPASS FYBECA")
		productos.add("CHIP VISA INFINITE")
		productos.add("CHIP VISA PLATINUM SUPERMAXI LANPASS FYBECA")
		productos.add("CHIP VISA PREMIUM SUPERMAXI FYBECA")
		productos.add("CHIP VISA PRESTIGE LANPASS SPMX FYBECA")
		return productos
	}

	/**
	 * @author Giovanny Granda
	 * Solo deja ver los primeros y últimos dígitos de un número de tarjeta
	 */
	static String hideCardNumber(String cardNumber){
		String result = ""
		if(cardNumber.length() > 4)
			result  = cardNumber.take(4) + "xxxxxxxxxx" + cardNumber.reverse().take(4).reverse()
		else
			result = cardNumber
		return result
	}

	static Date[] formatearFechasReporte(String fechas){
		String[] arrayFechas = fechas.trim().split('-')
		String inicio = arrayFechas[0].replace('/', '-')
		String fin = arrayFechas[1].replace('/', '-')
		Date fechaInicio = Date.parse("yyyy-MM-dd HH:mm:ss", inicio+" 00:00:00")
		Date fechaFin = Date.parse("yyyy-MM-dd HH:mm:ss", fin+" 23:59:59")
		Date[] Arrayfechas = new Date[2]
		Arrayfechas[0] = fechaInicio
		Arrayfechas[1] = fechaFin
		return Arrayfechas
	}

	static Date obtenerPrimerDiaMes(){
		Date fechaActual = new Date()
		String fechaSinDia = fechaActual.format("yyyy-MM-dd").toString().substring(0, 8)
		String fecha = fechaSinDia+"01 00:00:00"
		return Date.parse("yyyy-MM-dd HH:mm:ss", fecha)
	}
	/**
	 * @author Andres Redroban
	 * Solo deja ver los primeros y últimos dígitos de un número de tarjeta
	 */
	static String functionAsterisk(String id){
		String result = id
		int valor = 1
		for (int l = 0; l < valor; l++) {
			for(int i = 0; i < result.length(); i++){
				if(result.length() < 8){
					result  = "0" + result
				}
				else{
					if(result.length() > 8){
						break
					}
				}
			}
			if (result.length() == 8) {
				break;
			}
			valor = valor + 1
			println(result)
		}

		return result

	}

	static String getFechaInicialMes(){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		Date fechaInicio = Date.parse("yyyy-MM-dd HH:mm:ss", cal.getTime().format("yyyy-MM-dd 00:00:00"))
		SimpleDateFormat objSDF = new SimpleDateFormat('yyyy/MM/dd')
		String fecha = objSDF.format(fechaInicio)
		return fecha
	}
}
