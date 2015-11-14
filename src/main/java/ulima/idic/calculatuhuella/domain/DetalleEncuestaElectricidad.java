package ulima.idic.calculatuhuella.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;

/**
 * The persistent class for the detalles_encuesta_electricidad database table.
 * 
 */
@Entity
@Table(name = "detalles_encuesta_electricidad")
@NamedQuery(name = "DetalleEncuestaElectricidad.findAll", query = "SELECT d FROM DetalleEncuestaElectricidad d")
public class DetalleEncuestaElectricidad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "cantidad_dispositivos")
	private int cantidadDispositivos;

	@Column(name = "co2_dia")
	private BigDecimal co2Dia;

	@Column(name = "dispositivo_electrico_id")
	private int dispositivoElectricoId;

	@Column(name = "pregunta_id")
	private Integer preguntaId;

	@Column(name = "sector_id")
	private short sectorId = 1;

	@Column(name = "tiempo_uso_dia")
	private int tiempoUsoDia;

	// bi-directional many-to-one association to Encuesta
	@ManyToOne
	private Encuesta encuesta;

	public DetalleEncuestaElectricidad() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCantidadDispositivos() {
		return this.cantidadDispositivos;
	}

	public void setCantidadDispositivos(int cantidadDispositivos) {
		this.cantidadDispositivos = cantidadDispositivos;
	}

	public BigDecimal getCo2Dia() {
		return this.co2Dia;
	}

	public void setCo2Dia(BigDecimal co2Dia) {
		this.co2Dia = co2Dia;
	}

	public int getDispositivoElectricoId() {
		return this.dispositivoElectricoId;
	}

	public void setDispositivoElectricoId(int dispositivoElectricoId) {
		this.dispositivoElectricoId = dispositivoElectricoId;
	}

	public Integer getPreguntaId() {
		return this.preguntaId;
	}

	public void setPreguntaId(Integer preguntaId) {
		this.preguntaId = preguntaId;
	}

	public short getSectorId() {
		return this.sectorId;
	}

	public void setSectorId(short sectorId) {
		this.sectorId = sectorId;
	}

	public int getTiempoUsoDia() {
		return this.tiempoUsoDia;
	}

	public void setTiempoUsoDia(int tiempoUsoDia) {
		this.tiempoUsoDia = tiempoUsoDia;
	}

	public Encuesta getEncuesta() {
		return this.encuesta;
	}

	public void setEncuesta(Encuesta encuesta) {
		this.encuesta = encuesta;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder("{");
		sb.append("id:").append(id);
		sb.append(", cantidadDispositivos:").append(cantidadDispositivos);
		sb.append(", dispositivoElectricoId:").append(dispositivoElectricoId);
		sb.append(", tiempoUsoDia:").append(tiempoUsoDia);
		sb.append(", co2Dia:").append(co2Dia);
		sb.append("}");
		return sb.toString();
	}

}