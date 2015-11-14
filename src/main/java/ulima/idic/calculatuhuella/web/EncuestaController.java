package ulima.idic.calculatuhuella.web;

import static ulima.idic.calculatuhuella.domain.ResultadoOperacion.OPERACION_ERROR_EMAIL;
import static ulima.idic.calculatuhuella.domain.ResultadoOperacion.OPERACION_OK;
import static ulima.idic.calculatuhuella.domain.ResultadoOperacion.getResultadoError;

import java.io.Serializable;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ulima.idic.calculatuhuella.domain.DetalleEncuesta;
import ulima.idic.calculatuhuella.domain.DetalleEncuestaElectricidad;
import ulima.idic.calculatuhuella.domain.DetalleEncuestaResiduoSolido;
import ulima.idic.calculatuhuella.domain.DetalleEncuestaTransporte;
import ulima.idic.calculatuhuella.domain.Encuesta;
import ulima.idic.calculatuhuella.domain.ResultadoEncuesta;
import ulima.idic.calculatuhuella.domain.ResultadoOperacion;
import ulima.idic.calculatuhuella.service.CalculaTuHuellaServiceImpl;
import ulima.idic.calculatuhuella.service.EncuestaService;

@SuppressWarnings("serial")
@RestController
@RequestMapping("/")
@Scope("session")
public class EncuestaController implements Serializable {
	private static Logger logger = LoggerFactory.getLogger(EncuestaController.class);

	// @Autowired
	Encuesta encuesta = new Encuesta();

	@Autowired
	EncuestaService encuestaService;

	@Autowired
	CalculaTuHuellaServiceImpl calculatuhuellaService;

	@RequestMapping(value = "/encuesta", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public ResultadoOperacion iniciarEncuesta(@RequestBody Encuesta nuevaEncuesta) {
		ResultadoOperacion resultado = OPERACION_OK;
		logger.debug("nuevaEncuesta:" + nuevaEncuesta);
		try {
			if (!encuestaService.validarEmail(nuevaEncuesta.getEmail())) {
				resultado = OPERACION_ERROR_EMAIL;
			}
		} catch (Exception exc) {
			resultado = getResultadoError(exc.getMessage());
			logger.error("ERROR: EncuestaController.iniciarEncuesta", exc);
		}
		encuesta = nuevaEncuesta;
		return resultado;
	}

	@RequestMapping(value = "/detalle", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public void agregarDetalleEncuesta(@RequestBody DetalleEncuesta detalleEncuesta) {
		logger.info("Ejecutando agregarDetalleEncusta. detalleEncuesta:" + detalleEncuesta);
		encuesta.setDetalleEncuesta(detalleEncuesta);
	}

	@RequestMapping(value = "/transporte", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public void agregarDetalleEncuestaTransporte(@RequestBody ArrayList<DetalleEncuestaTransporte> detalleEncuestaTransporteList) {
		logger.info("Ejecutando agregarDetalleEncustaTransporte. encuesta:" + encuesta);
		encuesta.setDetalleEncuestaTransporteList(detalleEncuestaTransporteList);
	}

	@RequestMapping(value = "/electricidad", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public void agregarDetalleEncuestaElectricidad(@RequestBody ArrayList<DetalleEncuestaElectricidad> detalleEncuestaElectricidadList) {
		logger.info("Ejecutando agregarDetalleEncustaElectricidad. encuesta:" + encuesta);
		encuesta.setDetalleEncuestaElectricidadList(detalleEncuestaElectricidadList);
	}

	@RequestMapping(value = "/encuesta", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResultadoEncuesta finalizarEncuesta(@RequestBody ArrayList<DetalleEncuestaResiduoSolido> detalleEncuestaResiduoSolidoList) {
		ResultadoEncuesta resultadoEncuesta = new ResultadoEncuesta();
		logger.info("Ejecutando finalizarEncuesta. encuesta:" + encuesta);
		try {
			encuesta.setDetalleEncuestaResiduoSolidoList(detalleEncuestaResiduoSolidoList);
			encuestaService.guardarEncuesta(encuesta);
			resultadoEncuesta.setEncuestaId(encuesta.getId());
			resultadoEncuesta.setCo2AnnoElectricidad(encuesta.getDetalleEncuesta().getCo2AnnoElectricidad());
			resultadoEncuesta.setCo2AnnoTransportes(encuesta.getDetalleEncuesta().getCo2AnnoTransportes());
			resultadoEncuesta.setCo2AnnoResiduosSolidos(encuesta.getDetalleEncuesta().getCo2AnnoResiduosSolidos());
		} catch (Exception exc) {
			resultadoEncuesta.setErrorOperacion(exc.getMessage());
			logger.error("ERROR: EncuestaController.finalizarEncuesta", exc);
		}
		logger.info("Ejecutando finalizarEncuesta. resultadoEncuesta:" + resultadoEncuesta);
		return resultadoEncuesta;
	}
}
