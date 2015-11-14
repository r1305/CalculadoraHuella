package ulima.idic.calculatuhuella.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * The persistent class for the detalles_encuesta database table.
 * 
 */
@Entity
@Table(name = "detalles_encuesta")
@NamedQuery(name = "DetalleEncuesta.findAll", query = "SELECT d FROM DetalleEncuesta d")
public class DetalleEncuesta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	// @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "area_terreno")
	private short areaTerreno;

	@Column(name = "cantidad_residentes")
	private short cantidadResidentes;

	@Column(name = "indicador_jardin")
	private String indicadorJardin = "N";

	@Column(name = "area_jardin")
	private BigDecimal areaJardin;

	@Column(name = "co2_anno_electricidad")
	private BigDecimal co2AnnoElectricidad = new BigDecimal(1.5);

	@Column(name = "co2_anno_residuos_solidos")
	private BigDecimal co2AnnoResiduosSolidos = new BigDecimal(2.1);

	@Column(name = "co2_anno_transportes")
	private BigDecimal co2AnnoTransportes = new BigDecimal(1.2);

	// bi-directional many-to-one association to Encuesta
	@JoinColumn(name = "id")
	@OneToOne
	@MapsId
	private Encuesta encuesta;

	@OneToMany(mappedBy = "detalleEncuesta", cascade = { CascadeType.PERSIST })
	private List<DetalleEncuestaPersona> detalleEncuestaPersonaList;

	public DetalleEncuesta() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public short getCantidadResidentes() {
		return this.cantidadResidentes;
	}

	public void setCantidadResidentes(short cantidadResidentes) {
		this.cantidadResidentes = cantidadResidentes;
	}

	public BigDecimal getCo2AnnoElectricidad() {
		return this.co2AnnoElectricidad;
	}

	public void setCo2AnnoElectricidad(BigDecimal co2AnnoElectricidad) {
		this.co2AnnoElectricidad = co2AnnoElectricidad;
	}

	public BigDecimal getCo2AnnoResiduosSolidos() {
		return this.co2AnnoResiduosSolidos;
	}

	public void setCo2AnnoResiduosSolidos(BigDecimal co2AnnoResiduosSolidos) {
		this.co2AnnoResiduosSolidos = co2AnnoResiduosSolidos;
	}

	public BigDecimal getCo2AnnoTransportes() {
		return this.co2AnnoTransportes;
	}

	public void setCo2AnnoTransportes(BigDecimal co2AnnoTransportes) {
		this.co2AnnoTransportes = co2AnnoTransportes;
	}

	public Encuesta getEncuesta() {
		return this.encuesta;
	}

	public void setEncuesta(Encuesta encuesta) {
		this.encuesta = encuesta;
	}

	public BigDecimal getAreaJardin() {
		return areaJardin;
	}

	public void setAreaJardin(BigDecimal areaJardin) {
		this.areaJardin = areaJardin;
	}

	public short getAreaTerreno() {
		return areaTerreno;
	}

	public void setAreaTerreno(short areaTerreno) {
		this.areaTerreno = areaTerreno;
	}

	public List<DetalleEncuestaPersona> getDetalleEncuestaPersonaList() {
		return this.detalleEncuestaPersonaList;
	}

	public void setDetalleEncuestaPersonaList(List<DetalleEncuestaPersona> detalleEncuestaPersonaList) {
		this.detalleEncuestaPersonaList = detalleEncuestaPersonaList;
		if (detalleEncuestaPersonaList != null) {
			for (DetalleEncuestaPersona detallePersona : detalleEncuestaPersonaList) {
				detallePersona.setDetalleEncuesta(this);
			}
		}
	}

	public String toString() {
		StringBuilder sb = new StringBuilder("{");
		sb.append("id:").append(id);
		sb.append(", areaTerreno:").append(areaTerreno);
		sb.append(", indicadorJardin:").append(indicadorJardin);
		sb.append(", areaJardin:").append(areaJardin);
		sb.append(", cantidadResidentes:").append(cantidadResidentes);
		sb.append(", co2AnnoTransportes:").append(co2AnnoTransportes);
		sb.append(", co2AnnoElectricidad:").append(co2AnnoElectricidad);
		sb.append(", co2AnnoResiduosSolidos:").append(co2AnnoResiduosSolidos);
		if (detalleEncuestaPersonaList != null && detalleEncuestaPersonaList.isEmpty()) {
			sb.append(", detalleEncuestaPersonaList:").append(detalleEncuestaPersonaList);
		}
		sb.append("}");
		return sb.toString();
	}

}