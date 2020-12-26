package co.com.abc.dto;

public enum Convenio {

	AGUA(1), GAS(2);
	
	private int idConvenio;
	
	private Convenio(int idConvenio) {
		this.idConvenio = idConvenio;
	}
	
	public int getIdConvenio() {
		return this.idConvenio;
	}
	
	public static Convenio getConvenio(int idConvenio) {
		switch (idConvenio) {
		case 1:
			return Convenio.AGUA;
		case 2:
			return Convenio.GAS;
		default:
			return null;
		}
	}
}
