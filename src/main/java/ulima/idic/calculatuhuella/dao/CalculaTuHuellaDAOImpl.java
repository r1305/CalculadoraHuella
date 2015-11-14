package ulima.idic.calculatuhuella.dao;

import static ulima.idic.calculatuhuella.util.CalculaTuHuellaUtils.NATIVE_SQL_ELEMENTO;
import static ulima.idic.calculatuhuella.util.CalculaTuHuellaUtils.getElementosSQL;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import ulima.idic.calculatuhuella.domain.Elemento;

@Repository("calculatuhuellaDAO")
public class CalculaTuHuellaDAOImpl {
	private static final Logger logger = LoggerFactory.getLogger(CalculaTuHuellaDAOImpl.class);

	//@Inject
	@PersistenceContext
	private EntityManager entityManager;

	public List<Elemento> listarElementos(short sectorId) {
		List<Elemento> elementoList = new ArrayList<Elemento>();
		logger.info("listarelementos: sectorId:" + sectorId);
		Query query = entityManager.createNativeQuery(getElementosSQL(sectorId), NATIVE_SQL_ELEMENTO);
		elementoList = (List<Elemento>) query.getResultList();
		logger.info("listarelementos: elementoList.size:" + elementoList.size() + ", elementoList:" + elementoList);
		return elementoList;
	}

}
