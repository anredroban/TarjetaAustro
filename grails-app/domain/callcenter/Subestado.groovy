package callcenter

class Subestado {
	
	String nombre
	boolean enableManagement
	boolean enableManagement2
	String type
	boolean isActive
	Estado estado
	String alias

    static constraints = {
		enableManagement nullable: true
		enableManagement2 nullable: true
		type nullable: true
		type inList: ['Rellamada','Regestionable','Exitoso','No Exitoso','']
		isActive nullable: true
		alias nullable: true
    }
	
	static mapping = {
		version false
	}
	
}
