package ulima.idic.calculatuhuella.domain;

import java.io.Serializable;

public class Categoria implements Serializable {
	private static final long serialVersionUID = 1L;

	private short id;
	/**
	 * Indicador de periodo de uso: 1: Diario, 2: Semanal
	 */
	private short indicador;
	private String nombre;

	public Categoria(short id, Short indicador, String nombre) {
		this.id = id;
		this.indicador = indicador;
		this.nombre = nombre;
	}

	public Categoria() {
	}

	public short getId() {
		return this.id;
	}

	public void setId(short id) {
		this.id = id;
	}

	public short getIndicador() {
		return indicador;
	}

	public void setIndicador(short indicador) {
		this.indicador = indicador;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder("categoria: {");
		sb.append("id:").append(id);
		sb.append("indicador:").append(indicador);
		sb.append("nombre:").append(nombre);
		sb.append("}");
		return sb.toString();
	}
}