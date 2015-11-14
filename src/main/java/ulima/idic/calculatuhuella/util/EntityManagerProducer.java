package ulima.idic.calculatuhuella.util;

// @ApplicationScoped
// @Named("entityManager")
public class EntityManagerProducer {

	// @PersistenceUnit(unitName = "calculatuhuella")
	// private EntityManagerFactory emf;

	// @Produces // you can also make this @RequestScoped
	/*
	 * public EntityManager create() { return emf.createEntityManager(); }
	 */
	//@Named("entityManager")
	/*
	public EntityManager createEntityManager() {
		System.err.println(EntityManagerProducer.class.getSimpleName() + ":createEntityManager");
		return Persistence.createEntityManagerFactory("calculatuhuella").createEntityManager();
	}

	public void closeEntityManager(@Disposes EntityManager em) {
		System.err.println(EntityManagerProducer.class.getSimpleName() + ":closeEntityManager");
		if (em.isOpen()) {
			em.close();
		}
	}
	*/
}
