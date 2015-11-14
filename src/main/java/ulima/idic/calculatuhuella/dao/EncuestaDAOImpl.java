package ulima.idic.calculatuhuella.dao;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import ulima.idic.calculatuhuella.domain.Encuesta;
import ulima.idic.calculatuhuella.web.EncuestaController;

@Repository
public class EncuestaDAOImpl implements EncuestaDAO {
	private static Logger logger = LoggerFactory.getLogger(EncuestaController.class);
	@PersistenceContext
	private EntityManager entityManager;

	public Encuesta findByEmail(String email) {
		Encuesta encuesta = null;
		logger.info("EncuestaDAOImpl.buscarPorEmail: email:" + email);
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Encuesta> query = builder.createQuery(Encuesta.class);
		Root<Encuesta> encuestaRoot = query.from(Encuesta.class);
		query.where(builder.equal(encuestaRoot.get("email"), email));
		query.where(builder.equal(encuestaRoot.get("anno"), Calendar.getInstance().get(Calendar.YEAR)));
		List<Encuesta> resultList = entityManager.createQuery(query).getResultList();
		if (resultList != null && !resultList.isEmpty()) {
			encuesta = resultList.get(0);
		}
		logger.info("EncuestaDAOImpl.buscarPorEmail: encuesta:" + encuesta);
		return encuesta;
	}

	public Encuesta buscarPorEmail(String email) {
		Encuesta encuesta = null;
		TypedQuery<Encuesta> query = entityManager.createNamedQuery("Encuesta.buscarPorCorreo", Encuesta.class);
		query.setParameter("email", email);
		query.setParameter("anno", Calendar.getInstance().get(Calendar.YEAR));
		List<Encuesta> encuestaList = query.getResultList();
		if (encuestaList != null && !encuestaList.isEmpty()) {
			encuesta = encuestaList.get(0);
			logger.info("EncuestaDAOImpl.buscarPorEmail: Se encontro encuesta:" + encuesta.getId() + " con email:" + email);
		}
		// TODO: org.hibernate.LazyInitializationException
		// logger.info("EncuestaDAOImpl.buscarPorEmail: encuesta:" + encuesta);
		return encuesta;
	}

	public void guardarEncuesta(Encuesta encuesta) {
		entityManager.persist(encuesta);
	}
}
