package ulima.idic.calculatuhuella.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;

/**
 * The persistent class for the informacion_inei database table.
 * 
 */
@Entity
@Table(name = "informacion_inei")
@NamedQueries({
	@NamedQuery(name = "informacionSecundaria.findAll", query = "SELECT i FROM InformacionInei i"),
	@NamedQuery(name = "informacionSecundaria.listarDistritoSector", query = "select i from InformacionInei i where i.distritoId = :distritoId and i.sectorId = :sectorId order by i.anno")
})
public class InformacionInei implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private int anno;

	@Column(name = "co2_anno")
	private BigDecimal co2Anno;

	@Column(name = "distrito_id")
	private int distritoId;

	@Column(name = "indicador_fuente")
	private short indicadorFuente;

	@Column(name = "sector_id")
	private short sectorId;

	public InformacionInei() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getAnno() {
		return this.anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

	public BigDecimal getCo2Anno() {
		return this.co2Anno;
	}

	public void setCo2Anno(BigDecimal co2Anno) {
		this.co2Anno = co2Anno;
	}

	public int getDistritoId() {
		return this.distritoId;
	}

	public void setDistritoId(int distritoId) {
		this.distritoId = distritoId;
	}

	public short getIndicadorFuente() {
		return this.indicadorFuente;
	}

	public void setIndicadorFuente(short indicadorFuente) {
		this.indicadorFuente = indicadorFuente;
	}

	public short getSectorId() {
		return this.sectorId;
	}

	public void setSectorId(short sectorId) {
		this.sectorId = sectorId;
	}

}