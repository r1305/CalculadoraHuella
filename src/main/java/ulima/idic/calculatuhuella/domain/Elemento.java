package ulima.idic.calculatuhuella.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the residuos_solidos database table.
 * 
 */
public class Elemento implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private short categoriaId;

	private String nombre;

	public Elemento() {
	}

	public Elemento(int id, short categoriaId, String nombre) {
		this.id = id;
		this.categoriaId = categoriaId;
		this.nombre = nombre;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public short getCategoriaId() {
		return this.categoriaId;
	}

	public void setCategoriaId(short categoriaId) {
		this.categoriaId = categoriaId;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}