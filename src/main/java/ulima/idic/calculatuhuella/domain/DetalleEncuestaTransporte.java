package ulima.idic.calculatuhuella.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;

/**
 * The persistent class for the detalles_encuesta_transporte database table.
 * 
 */
@Entity
@Table(name = "detalles_encuesta_transporte")
@NamedQuery(name = "DetalleEncuestaTransporte.findAll", query = "SELECT d FROM DetalleEncuestaTransporte d")
public class DetalleEncuestaTransporte implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "cantidad_viajes_dia")
	private int cantidadViajesDia;

	@Column(name = "co2_dia")
	private BigDecimal co2Dia;

	@Column(name = "pregunta_id")
	private Integer preguntaId;

	@Column(name = "sector_id")
	private short sectorId = 2;

	@Column(name = "tiempo_uso_dia")
	private int tiempoUsoDia;

	@Column(name = "transporte_x_categoria_id")
	private int transporteXCategoriaId;

	// bi-directional many-to-one association to Encuesta
	@ManyToOne
	private Encuesta encuesta;

	public DetalleEncuestaTransporte() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCantidadViajesDia() {
		return this.cantidadViajesDia;
	}

	public void setCantidadViajesDia(int cantidadViajesDia) {
		this.cantidadViajesDia = cantidadViajesDia;
	}

	public BigDecimal getCo2Dia() {
		return this.co2Dia;
	}

	public void setCo2Dia(BigDecimal co2Dia) {
		this.co2Dia = co2Dia;
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

	public int getTransporteXCategoriaId() {
		return this.transporteXCategoriaId;
	}

	public void setTransporteXCategoriaId(int transporteXCategoriaId) {
		this.transporteXCategoriaId = transporteXCategoriaId;
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
		sb.append("sectorId:").append(sectorId);
		sb.append("cantidadViajesDia:").append(cantidadViajesDia);
		sb.append("transporteXCategoriaId:").append(transporteXCategoriaId);
		sb.append("}");
		return sb.toString();
	}
}