package ulima.idic.calculatuhuella.domain;

import java.math.BigDecimal;

@SuppressWarnings("serial")
public class ResultadoEncuesta extends ResultadoOperacion {
	int encuestaId;
	private BigDecimal co2AnnoElectricidad = new BigDecimal(1.5);

	private BigDecimal co2AnnoResiduosSolidos = new BigDecimal(2.1);

	private BigDecimal co2AnnoTransportes = new BigDecimal(1.2);

	public int getEncuestaId() {
		return encuestaId;
	}

	public BigDecimal getCo2AnnoElectricidad() {
		return co2AnnoElectricidad;
	}

	public void setCo2AnnoElectricidad(BigDecimal co2AnnoElectricidad) {
		this.co2AnnoElectricidad = co2AnnoElectricidad;
	}

	public BigDecimal getCo2AnnoResiduosSolidos() {
		return co2AnnoResiduosSolidos;
	}

	public void setCo2AnnoResiduosSolidos(BigDecimal co2AnnoResiduosSolidos) {
		this.co2AnnoResiduosSolidos = co2AnnoResiduosSolidos;
	}

	public BigDecimal getCo2AnnoTransportes() {
		return co2AnnoTransportes;
	}

	public void setCo2AnnoTransportes(BigDecimal co2AnnoTransportes) {
		this.co2AnnoTransportes = co2AnnoTransportes;
	}

	public void setEncuestaId(int encuestaId) {
		this.encuestaId = encuestaId;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder("{");
		sb.append("encuestaId:").append(encuestaId);
		sb.append(", resultado:").append(resultado);
		sb.append("}");
		return sb.toString();
	}
}
