package ulima.idic.calculatuhuella.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * The persistent class for the encuestas database table.
 * 
 */
// TODO:
@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Entity(name = "Encuesta")
@Table(name = "encuestas")
@NamedQuery(name = "Encuesta.buscarPorCorreo", query = "SELECT e FROM Encuesta e where e.email = :email and e.anno = :anno")
public class Encuesta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int anno = Calendar.getInstance().get(Calendar.YEAR);

	@Column(name = "distrito_id")
	private int distritoId;

	private String email;

	@Column(name = "indicador_fuente")
	private short indicadorFuente;

	// bi-directional many-to-one association to DetalleEncuesta
	@OneToOne(mappedBy = "encuesta", cascade = { CascadeType.PERSIST })
	private DetalleEncuesta detalleEncuesta;

	// bi-directional many-to-one association to DetalleEncuestaElectricidad
	@OneToMany(mappedBy = "encuesta", cascade = { CascadeType.PERSIST })
	private List<DetalleEncuestaElectricidad> detalleEncuestaElectricidadList;

	// bi-directional many-to-one association to DetalleEncuestaResiduoSolido
	@OneToMany(mappedBy = "encuesta", cascade = { CascadeType.PERSIST })
	private List<DetalleEncuestaResiduoSolido> detalleEncuestaResiduoSolidoList;

	// bi-directional many-to-one association to DetalleEncuestaTransporte
	@OneToMany(mappedBy = "encuesta", cascade = { CascadeType.PERSIST })
	private List<DetalleEncuestaTransporte> detalleEncuestaTransporteList;

	public Encuesta() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAnno() {
		return this.anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

	public int getDistritoId() {
		return this.distritoId;
	}

	public void setDistritoId(int distritoId) {
		this.distritoId = distritoId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		if (email != null) {
			email = email.toLowerCase();
		}
		this.email = email;
	}

	public short getIndicadorFuente() {
		return this.indicadorFuente;
	}

	public void setIndicadorFuente(short indicadorFuente) {
		this.indicadorFuente = indicadorFuente;
	}

	public DetalleEncuesta getDetalleEncuesta() {
		return this.detalleEncuesta;
	}

	public void setDetalleEncuesta(DetalleEncuesta detalleEncuesta) {
		this.detalleEncuesta = detalleEncuesta;
		if (detalleEncuesta != null) {
			detalleEncuesta.setEncuesta(this);
		}
	}

	public List<DetalleEncuestaElectricidad> getDetalleEncuestaElectricidadList() {
		return this.detalleEncuestaElectricidadList;
	}

	public void setDetalleEncuestaElectricidadList(List<DetalleEncuestaElectricidad> detalleEncuestaElectricidadList) {
		this.detalleEncuestaElectricidadList = detalleEncuestaElectricidadList;
		if (detalleEncuestaElectricidadList != null) {
			for (DetalleEncuestaElectricidad detalleElectricidad : detalleEncuestaElectricidadList) {
				detalleElectricidad.setEncuesta(this);
			}
		}
	}

	public DetalleEncuestaElectricidad addDetalleEncuestaElectricidadList(DetalleEncuestaElectricidad detalleEncuestaElectricidadList) {
		getDetalleEncuestaElectricidadList().add(detalleEncuestaElectricidadList);
		detalleEncuestaElectricidadList.setEncuesta(this);

		return detalleEncuestaElectricidadList;
	}

	public DetalleEncuestaElectricidad removeDetalleEncuestaElectricidadList(DetalleEncuestaElectricidad detalleEncuestaElectricidadList) {
		getDetalleEncuestaElectricidadList().remove(detalleEncuestaElectricidadList);
		detalleEncuestaElectricidadList.setEncuesta(null);

		return detalleEncuestaElectricidadList;
	}

	public List<DetalleEncuestaResiduoSolido> getDetalleEncuestaResiduoSolidoList() {
		return this.detalleEncuestaResiduoSolidoList;
	}

	public void setDetalleEncuestaResiduoSolidoList(List<DetalleEncuestaResiduoSolido> detalleEncuestaResiduoSolidoList) {
		this.detalleEncuestaResiduoSolidoList = detalleEncuestaResiduoSolidoList;
		if (detalleEncuestaResiduoSolidoList != null) {
			for (DetalleEncuestaResiduoSolido detalleResiduoSolido : detalleEncuestaResiduoSolidoList) {
				detalleResiduoSolido.setEncuesta(this);
			}
		}
	}

	public DetalleEncuestaResiduoSolido addDetalleEncuestaResiduoSolidoList(DetalleEncuestaResiduoSolido detalleEncuestaResiduoSolidoList) {
		getDetalleEncuestaResiduoSolidoList().add(detalleEncuestaResiduoSolidoList);
		detalleEncuestaResiduoSolidoList.setEncuesta(this);

		return detalleEncuestaResiduoSolidoList;
	}

	public DetalleEncuestaResiduoSolido removeDetalleEncuestaResiduoSolidoList(DetalleEncuestaResiduoSolido detalleEncuestaResiduoSolidoList) {
		getDetalleEncuestaResiduoSolidoList().remove(detalleEncuestaResiduoSolidoList);
		detalleEncuestaResiduoSolidoList.setEncuesta(null);

		return detalleEncuestaResiduoSolidoList;
	}

	public List<DetalleEncuestaTransporte> getDetalleEncuestaTransporteList() {
		return this.detalleEncuestaTransporteList;
	}

	public void setDetalleEncuestaTransporteList(List<DetalleEncuestaTransporte> detalleEncuestaTransporteList) {
		this.detalleEncuestaTransporteList = detalleEncuestaTransporteList;
		// Para registrar detalle (JPA)
		if (detalleEncuestaTransporteList != null) {
			for (DetalleEncuestaTransporte detalleTransporte : detalleEncuestaTransporteList) {
				detalleTransporte.setEncuesta(this);
			}
		}
	}

	public DetalleEncuestaTransporte addDetalleEncuestaTransporteList(DetalleEncuestaTransporte detalleEncuestaTransporteList) {
		getDetalleEncuestaTransporteList().add(detalleEncuestaTransporteList);
		detalleEncuestaTransporteList.setEncuesta(this);

		return detalleEncuestaTransporteList;
	}

	public DetalleEncuestaTransporte removeDetalleEncuestaTransporteList(DetalleEncuestaTransporte detalleEncuestaTransporteList) {
		getDetalleEncuestaTransporteList().remove(detalleEncuestaTransporteList);
		detalleEncuestaTransporteList.setEncuesta(null);

		return detalleEncuestaTransporteList;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder("{");
		sb.append("id:").append(id);
		sb.append(", anno:").append(anno);
		sb.append(", email:").append(email);
		sb.append(", distritoId:").append(distritoId);
		if (detalleEncuesta != null) {
			sb.append(", detalleEncuesta:").append(detalleEncuesta);
		}
		if (detalleEncuestaTransporteList != null && !detalleEncuestaTransporteList.isEmpty()) {
			sb.append(", detalleEncuestaTransporteList:").append(detalleEncuestaTransporteList);
		}
		if (detalleEncuestaElectricidadList != null && !detalleEncuestaElectricidadList.isEmpty()) {
			sb.append(", detalleEncuestaElectricidadList:").append(detalleEncuestaElectricidadList);
		}
		if (detalleEncuestaResiduoSolidoList != null && !detalleEncuestaResiduoSolidoList.isEmpty()) {
			sb.append(", detalleEncuestaResiduoSolidoList:").append(detalleEncuestaResiduoSolidoList);
		}
		sb.append("}");
		return sb.toString();
	}

}