package ulima.idic.calculatuhuella.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the detalles_encuestas_personas database table.
 * 
 */
@Entity
@Table(name = "detalles_encuestas_personas")
@NamedQuery(name = "DetalleEncuestaPersona.findAll", query = "SELECT d FROM DetalleEncuestaPersona d")
public class DetalleEncuestaPersona implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "indicador_edad")
	private short indicadorEdad;

	private String sexo;

	@Column(name = "cantidad_personas")
	private short cantidadPersonas;

	@ManyToOne
	@JoinColumn(name = "encuesta_id")
	private DetalleEncuesta detalleEncuesta;

	public DetalleEncuestaPersona() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public short getIndicadorEdad() {
		return this.indicadorEdad;
	}

	public void setIndicadorEdad(short indicadorEdad) {
		this.indicadorEdad = indicadorEdad;
	}

	public String getSexo() {
		return this.sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public short getCantidadPersonas() {
		return cantidadPersonas;
	}

	public void setCantidadPersonas(short cantidadPersonas) {
		this.cantidadPersonas = cantidadPersonas;
	}

	public DetalleEncuesta getDetalleEncuesta() {
		return detalleEncuesta;
	}

	public void setDetalleEncuesta(DetalleEncuesta detalleEncuesta) {
		this.detalleEncuesta = detalleEncuesta;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder("{");
		sb.append("sexo:").append(sexo);
		sb.append(", indicadorEdad:").append(indicadorEdad);
		sb.append(", cantidadPersonas:").append(cantidadPersonas);
		sb.append("}");
		return sb.toString();
	}

}