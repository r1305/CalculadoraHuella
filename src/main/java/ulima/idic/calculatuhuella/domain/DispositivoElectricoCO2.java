package ulima.idic.calculatuhuella.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;

/**
 * The persistent class for the dispositivos_electricos_co2 database table.
 * 
 */
// @Entity
// @Table(name="dispositivos_electricos_co2")
// @NamedQuery(name="DispositivosElectricosCO2.findAll", query="SELECT d FROM DispositivosElectricosCO2 d")
public class DispositivoElectricoCO2 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "co2_anno")
	private BigDecimal co2Anno;

	@Column(name = "dispositivo_electrico_id")
	private int dispositivoElectricoId;

	@Column(name = "distrito_id")
	private int distritoId;

	private String nombre;

	public DispositivoElectricoCO2() {
	}

	public DispositivoElectricoCO2(int distritoId, int dispositivoElectricoId, String nombre, BigDecimal co2Anno) {
		this.distritoId = distritoId;
		this.dispositivoElectricoId = dispositivoElectricoId;
		this.nombre = nombre;
		this.co2Anno = co2Anno;
	}

	public int getDispositivoElectricoId() {
		return this.dispositivoElectricoId;
	}

	public void setDispositivoElectricoId(int dispositivoElectricoId) {
		this.dispositivoElectricoId = dispositivoElectricoId;
	}

	public int getDistritoId() {
		return this.distritoId;
	}

	public void setDistritoId(int distritoId) {
		this.distritoId = distritoId;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigDecimal getCo2Anno() {
		return co2Anno;
	}

	public void setCo2Anno(BigDecimal co2Anno) {
		this.co2Anno = co2Anno;
	}

}