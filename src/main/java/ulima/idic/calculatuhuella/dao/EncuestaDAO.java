package ulima.idic.calculatuhuella.dao;

import ulima.idic.calculatuhuella.domain.Encuesta;

public interface EncuestaDAO {

	@Deprecated
	public Encuesta findByEmail(String email);

	public Encuesta buscarPorEmail(String email);

	public void guardarEncuesta(Encuesta encuesta);
}
