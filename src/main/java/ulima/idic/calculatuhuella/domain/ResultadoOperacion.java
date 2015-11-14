package ulima.idic.calculatuhuella.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ResultadoOperacion implements Serializable {
	public static String OK = "OK";
	public static String ERROR = "ERROR";

	public static ResultadoOperacion OPERACION_OK = new ResultadoOperacion(OK);
	public static ResultadoOperacion OPERACION_ERROR_EMAIL = new ResultadoOperacion(ERROR, "El correo ya ha sido  ingresado en la encuesta");
	protected String resultado = OK;
	protected String mensaje;

	public ResultadoOperacion() {
	}

	public ResultadoOperacion(String resultado) {
		this.resultado = resultado;
	}

	public static ResultadoOperacion getResultadoError(String mensaje) {
		return new ResultadoOperacion(ERROR, mensaje);
	}

	public void setErrorOperacion(String mensaje) {
		this.resultado = ERROR;
		this.mensaje = mensaje;
	}

	public ResultadoOperacion(String resultado, String mensaje) {
		this.resultado = resultado;
		this.mensaje = mensaje;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder("{");
		sb.append("resultado:").append(resultado);
		sb.append(", mensaje:").append(mensaje);
		sb.append("}");
		return sb.toString();
	}
}
