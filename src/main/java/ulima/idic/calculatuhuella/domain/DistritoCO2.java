package ulima.idic.calculatuhuella.domain;

import java.math.BigDecimal;

// public class DistritoCO2 implements Serializable {
public class DistritoCO2 extends Distrito {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * private int id;
	 * 
	 * private String latitud;
	 * 
	 * private String longitud;
	 * 
	 * private String nombre;
	 */
	BigDecimal co2Anno;

	public DistritoCO2(int id, String nombre, String latitud, String longitud, BigDecimal co2Anno) {
		this.setId(id);
		this.setNombre(nombre);
		this.setLatitud(latitud);
		this.setLongitud(longitud);
		this.co2Anno = co2Anno;
	}

	public BigDecimal getCo2Anno() {
		return co2Anno;
	}

	public void setCo2Anno(BigDecimal co2Anno) {
		this.co2Anno = co2Anno;
	}
}
