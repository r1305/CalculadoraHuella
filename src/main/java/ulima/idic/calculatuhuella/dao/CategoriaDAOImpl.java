package ulima.idic.calculatuhuella.dao;

import static ulima.idic.calculatuhuella.util.CalculaTuHuellaUtils.NATIVE_SQL_CATEGORIA;
import static ulima.idic.calculatuhuella.util.CalculaTuHuellaUtils.getCategoriasSQL;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import ulima.idic.calculatuhuella.domain.Categoria;

@Repository("categoriaDAO")
public class CategoriaDAOImpl {
	private static final Logger logger = LoggerFactory.getLogger(InformacionIneiDAOImpl.class);

	// @Inject
	@PersistenceContext
	private EntityManager entityManager;

	public List<Categoria> listarCategorias(short sectorId, short indicador) {
		List<Categoria> categoriaList = new ArrayList<Categoria>();
		logger.info("listarCategorias: sectorId:" + sectorId + ", indicador:" + indicador);
		Query query = entityManager.createNativeQuery(getCategoriasSQL(sectorId), NATIVE_SQL_CATEGORIA);
		categoriaList = (List<Categoria>) query.getResultList();
		logger.info("listarCategorias: categoriaList.size:" + categoriaList.size() + ", categoriaList:" + categoriaList);
		return categoriaList;
	}
}
