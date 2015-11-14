package ulima.idic.calculatuhuella.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;

/**
 * The persistent class for the detalles_encuesta_residuo_solido database table.
 * 
 */
@Entity
@Table(name = "detalles_encuesta_residuo_solido")
@NamedQuery(name = "DetalleEncuestaResiduoSolido.findAll", query = "SELECT d FROM DetalleEncuestaResiduoSolido d")
public class DetalleEncuestaResiduoSolido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "cantidad_residuo_generado")
	private int cantidadResiduoGenerado;

	@Column(name = "co2_dia")
	private BigDecimal co2Dia;

	@Column(name = "pregunta_id")
	private Integer preguntaId;

	@Column(name = "residuo_solido_id")
	private int residuoSolidoId;

	@Column(name = "sector_id")
	private short sectorId = 3;

	// bi-directional many-to-one association to Encuesta
	@ManyToOne
	private Encuesta encuesta;

	public DetalleEncuestaResiduoSolido() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCantidadResiduoGenerado() {
		return this.cantidadResiduoGenerado;
	}

	public void setCantidadResiduoGenerado(int cantidadResiduoGenerado) {
		this.cantidadResiduoGenerado = cantidadResiduoGenerado;
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

	public int getResiduoSolidoId() {
		return this.residuoSolidoId;
	}

	public void setResiduoSolidoId(int residuoSolidoId) {
		this.residuoSolidoId = residuoSolidoId;
	}

	public short getSectorId() {
		return this.sectorId;
	}

	public void setSectorId(short sectorId) {
		this.sectorId = sectorId;
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
		sb.append("cantidadResiduoGenerado:").append(cantidadResiduoGenerado);
		sb.append("co2Dia:").append(co2Dia);
		sb.append("}");
		return sb.toString();
	}

}