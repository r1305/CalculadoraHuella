package ulima.idic.calculatuhuella.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import ulima.idic.calculatuhuella.domain.DispositivoElectricoCO2;
import ulima.idic.calculatuhuella.domain.Distrito;
import ulima.idic.calculatuhuella.domain.DistritoCO2;

@Repository("distritoDAO")
public class DistritoDAOImpl {

	private static final Logger logger = LoggerFactory.getLogger(DistritoDAOImpl.class);
	private static final List<Integer> DISTRITO_ID_LIST = Arrays.asList(1251, 1262, 1280, 1282, 1285);
	// private static final String SQL_DISTRITOS_CO2 ="select d.id,d.nombre, d.latitud, d.longitud, avg(de.co2_anno_electricidad + de.co2_anno_transportes + de.co2_anno_residuos_solidos) co2_anno from distritos d, encuestas e, detalles_encuesta de where d.id=e.distrito_id and e.id=de.encuesta_id
	// group by d.id";
	// private static final String SQL_DISTRITOS_CO2 ="select d.id,d.nombre, d.latitud, d.longitud, 1.5 as co2_anno from distritos d, encuestas e, detalles_encuesta de where d.id=e.distrito_id and e.id=de.encuesta_id group by d.id";
	// private static final String SQL_DISTRITOS_CO2 = "select d.id,d.nombre, d.latitud, d.longitud, 4.99 as co2_anno from distritos d where d.provincia_id=128 order by id";
	private static final String SQL_DISTRITOS_CO2 = "select id, nombre, latitud, longitud, co2_anno from distritos_co2";
	// private static final String SQL_DISPOSITIVOS_ELECTRICOS_CO2 = "select distrito_id, dispositivo_electrico_id, nombre, co2_anno from dispositivos_electricos_co2 where distrito_id :distritoId";
	private static final String SQL_DISPOSITIVOS_ELECTRICOS_CO2 = "select distrito_id, dispositivo_electrico_id, nombre, co2_anno from dispositivos_electricos_co2 where distrito_id=?";
	
	// @PersistenceContext(unitName = "calculatuhuella")
	// @Inject
	// @Produces
	@PersistenceContext
	private EntityManager entityManager;

	/*
	 * @PersistenceUnit(unitName = "calculahuella") void setEntityManager(final EntityManagerFactory em) { entityManager = em.createEntityManager(); }
	 */
	// @Transactional
	public List<Distrito> listarDistitos() {
		System.err.println("DistritoDAOImpl.listarDistitos: Iniciando");
		List<Distrito> distritoList = new ArrayList<Distrito>();
		System.err.println("DistritoDAOImpl.listarDistitos: 2...entityManager:" + entityManager);
		Query query = entityManager.createNamedQuery("distrito.listarDistritos");
		System.err.println("DistritoDAOImpl.listarDistitos: 3");
		query.setParameter("distritoIdList", DISTRITO_ID_LIST);
		System.err.println("DistritoDAOImpl.listarDistitos: 4");
		distritoList = (List<Distrito>) query.getResultList();
		System.err.println("DistritoDAOImpl.listarDistitos: 5");
		System.err.println(DistritoDAOImpl.class.getSimpleName() + ".listarDistitos:" + distritoList);
		return distritoList;
	}

	public List<DistritoCO2> listarDistitosCO2() {
		List<DistritoCO2> distritoList = new ArrayList<DistritoCO2>();
		Query query = entityManager.createNativeQuery(SQL_DISTRITOS_CO2, "distrito.listarDistritosCO2");
		distritoList = (List<DistritoCO2>) query.getResultList();
		logger.info("listarDistitosCO2: distritoList.size:" + distritoList.size() + ", distritoList:" + distritoList);
		return distritoList;
	}

	public List<DispositivoElectricoCO2> listarDispositivosElectricosCO2(int distritoId) {
		List<DispositivoElectricoCO2> dispositivoElectricoList = new ArrayList<DispositivoElectricoCO2>();
		Query query = entityManager.createNativeQuery(SQL_DISPOSITIVOS_ELECTRICOS_CO2, "distrito.listarDispositivosElectricosCO2");
		query.setParameter(1, distritoId);
		dispositivoElectricoList = (List<DispositivoElectricoCO2>) query.getResultList();
		logger.info("listarDispositivosElectricosCO2: dispositivoElectricoList.size:" + dispositivoElectricoList.size() + ", dispositivoElectricoList:" + dispositivoElectricoList);
		return dispositivoElectricoList;
	}
}
