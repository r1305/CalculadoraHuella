package ulima.idic.calculatuhuella.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
import javax.persistence.Table;

/**
 * The persistent class for the distritos database table.
 * 
 */
// @XmlRootElement
@Entity
@Table(name = "distritos")
@NamedQueries({
	@NamedQuery(name = "distrito.findAll", query = "SELECT d FROM Distrito d"),
	@NamedQuery(name = "distrito.listarDistritos", query = "select d from Distrito d where d.id in :distritoIdList order by d.id")
})


@NamedNativeQuery(
	    name="distrito.listarDispositivosElectricosCO2",
	    query="select distrito_id, dispositivo_electrico_id, nombre, co2_anno from dispositivos_electricos_co2 where distrito_id :distritoid"
)

@SqlResultSetMappings ({
	@SqlResultSetMapping(name="distrito.listarDistritosCO2",
			classes = {
			 @ConstructorResult(targetClass = DistritoCO2.class,
			   columns = {@ColumnResult(name="id"), @ColumnResult(name="nombre"), @ColumnResult(name="latitud"), @ColumnResult(name="longitud"), @ColumnResult(name="co2_anno")}
			 )}
	),
	@SqlResultSetMapping(name="distrito.listarDispositivosElectricosCO2",
		classes = {
		 @ConstructorResult(targetClass = DispositivoElectricoCO2.class,
		   columns = {@ColumnResult(name="distrito_id"), @ColumnResult(name="dispositivo_electrico_id"), @ColumnResult(name="nombre"), @ColumnResult(name="co2_anno")}
		 )}
	),
	@SqlResultSetMapping(name="categoria.listarCategorias",
		classes = {
		 @ConstructorResult(targetClass = Categoria.class,
		   columns = {@ColumnResult(name="id", type= Short.class), @ColumnResult(name="indicador_periodo", type= Short.class), @ColumnResult(name="nombre")}
		)}
	),
	@SqlResultSetMapping(name="elementos.listarElementos",
		classes = {
		 @ConstructorResult(targetClass = Elemento.class,
		   columns = {@ColumnResult(name="id"), @ColumnResult(name="categoria_id", type= Short.class), @ColumnResult(name="nombre")}
		)}
	)
})
/*
@SqlResultSetMapping(name="distrito.listarDistritosCO2",
classes = {
 @ConstructorResult(targetClass = DistritoCO2.class,
   columns = {@ColumnResult(name="id"), @ColumnResult(name="nombre"), @ColumnResult(name="latitud"), @ColumnResult(name="longitud"), @ColumnResult(name="co2_anno")}
 )}
)
*/
public class Distrito implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String latitud;

	private String longitud;

	private String nombre;

	@Column(name = "provincia_id")
	private int provinciaId;

	public Distrito() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLatitud() {
		return this.latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public String getLongitud() {
		return this.longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getProvinciaId() {
		return this.provinciaId;
	}

	public void setProvinciaId(int provinciaId) {
		this.provinciaId = provinciaId;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer(Distrito.class.getSimpleName()).append(": ");
		sb.append("id:").append(id);
		sb.append("nombre:").append(nombre);
		return sb.toString();
	}
}