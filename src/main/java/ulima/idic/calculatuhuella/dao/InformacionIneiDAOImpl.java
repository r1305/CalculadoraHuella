package ulima.idic.calculatuhuella.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import ulima.idic.calculatuhuella.domain.InformacionInei;

@Repository("informacionIneiDAO")
public class InformacionIneiDAOImpl {
	private static final Logger logger = LoggerFactory.getLogger(InformacionIneiDAOImpl.class);

	// @Inject
	@PersistenceContext
	private EntityManager entityManager;

	public List<InformacionInei> listarDistritoSector(InformacionInei informacionInei) {
		List<InformacionInei> informacionIneiList = new ArrayList<InformacionInei>();
		Query query = entityManager.createNamedQuery("informacionSecundaria.listarDistritoSector");
		query.setParameter("distritoId", informacionInei.getDistritoId());
		query.setParameter("sectorId", informacionInei.getSectorId());
		informacionIneiList = query.getResultList();
		logger.info("listarDistritoSector: informacionIneiList.size:" + informacionIneiList.size() + ", informacionIneiList:" + informacionIneiList);
		return informacionIneiList;
	}
}
