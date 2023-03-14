package com.Modelo;

public enum EnumTipoDoc {	
	
	CI("Cedula de identidad", "C"),
	DNI("Dni", "D"),
	PASAPORTE("Pasaporte", "P"),
	OTRO("Otro", "O");
	
	private final String descripcion;
	private final String asChar;
	
	private EnumTipoDoc(String descripcion, String asChar) {
		this.descripcion = descripcion;
		this.asChar = asChar;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getAsChar() {
		return asChar;
	}
        
	public static EnumTipoDoc getTipoDocPorChar(String name) {
        for (EnumTipoDoc tipoDoc : EnumTipoDoc.values()) {
            if (tipoDoc.equals(name))
                return tipoDoc;
    	}
        return null;
    }
}
