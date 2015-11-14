package ulima.idic.calculatuhuella.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ulima.idic.calculatuhuella.dao.EncuestaDAO;
import ulima.idic.calculatuhuella.domain.Encuesta;

@Service
public class EncuestaServiceImpl implements EncuestaService {

	@Autowired
	EncuestaDAO encuestaDAO;

	public boolean validarEmail(String email) {
		Encuesta encuesta = encuestaDAO.buscarPorEmail(email);
		return encuesta == null;
	}

	@Transactional
	public void guardarEncuesta(Encuesta encuesta) {
		encuestaDAO.guardarEncuesta(encuesta);
	}
}
