package ulima.idic.calculatuhuella.service;

import ulima.idic.calculatuhuella.domain.Encuesta;

public interface EncuestaService {
	public boolean validarEmail(String email);
	public void guardarEncuesta(Encuesta encuesta);
}
