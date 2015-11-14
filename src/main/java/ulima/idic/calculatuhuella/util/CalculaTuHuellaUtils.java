package ulima.idic.calculatuhuella.util;

public final class CalculaTuHuellaUtils {
	public final static String SQL_CATEGORIAS_ELECTRICIDAD = "select id, indicador_periodo, nombre from categorias_dispositivos_electricos order by indicador_periodo,id";
	public final static String SQL_CATEGORIAS_TRANSPORTE = "select id, 0 as indicador_periodo, nombre from categorias_transportes order by id";
	public final static String SQL_CATEGORIAS_RESIDUO = "select id, indicador_periodo, nombre from categorias_residuos_solidos order by indicador_periodo,id";
	public final static String SQL_ELEMENTOS_ELECTRICIDAD = "select id, categoria_id, nombre from dispositivos_electricos order by categoria_id, nombre;";
	public final static String SQL_ELEMENTOS_TRANSPORTE = "select id, categoria_id, nombre from transportes_categorias";
	public final static String SQL_ELEMENTOS_RESIDUO = "select id, categoria_id, nombre from residuos_solidos order by categoria_id, nombre;";
	public final static String NATIVE_SQL_CATEGORIA = "categoria.listarCategorias";
	public final static String NATIVE_SQL_ELEMENTO = "elementos.listarElementos";

	public static String getCategoriasSQL(int sectorId) {
		String sql = "";
		switch (sectorId) {
		case 1: {
			sql = SQL_CATEGORIAS_ELECTRICIDAD;
			break;
		}
		case 2: {
			sql = SQL_CATEGORIAS_TRANSPORTE;
			break;
		}
		case 3: {
			sql = SQL_CATEGORIAS_RESIDUO;
			break;
		}
		}
		return sql;
	}

	public static String getElementosSQL(int sectorId) {
		String sql = "";
		switch (sectorId) {
		case 1: {
			sql = SQL_ELEMENTOS_ELECTRICIDAD;
			break;
		}
		case 2: {
			sql = SQL_ELEMENTOS_TRANSPORTE;
			break;
		}
		case 3: {
			sql = SQL_ELEMENTOS_RESIDUO;
			break;
		}
		}
		return sql;
	}

}
