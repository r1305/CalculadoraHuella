package ulima.idic.calculatuhuella.web;

import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ulima.idic.calculatuhuella.domain.Categoria;
import ulima.idic.calculatuhuella.domain.DispositivoElectricoCO2;
import ulima.idic.calculatuhuella.domain.Distrito;
import ulima.idic.calculatuhuella.domain.DistritoCO2;
import ulima.idic.calculatuhuella.domain.Elemento;
import ulima.idic.calculatuhuella.domain.InformacionInei;
import ulima.idic.calculatuhuella.service.CalculaTuHuellaService;

// @ManagedBean
@Component("calculatuhuellaResource")
@Resource
@Path("/")
public class CalculaTuHuellaResource {
	private static Logger logger = LoggerFactory.getLogger(CalculaTuHuellaResource.class);

	// @Inject
	@Autowired
	CalculaTuHuellaService calculatuhuellaService;

	// URL
	// servicios/distritos
	@GET
	@Path("/distritos")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Distrito> listarDistritos() {
		logger.debug("Ejecutando listarDistritos");
		return calculatuhuellaService.listarDistitos();
	}	
	
	// URL
	// servicios/mapas
	@GET
	@Path("/mapas")
	@Produces(MediaType.APPLICATION_JSON)
	public List<DistritoCO2> listarDistritosCO2() {
		logger.debug("Ejecutando listarDistritosCO2");
		return calculatuhuellaService.listarDistitosCO2();
	}

	// URL
	// servicios/historicos?distritoId=1280&sectorId=1
	// servicios/historicos?distritoId=1280&sectorId=3
	/**
	 * Retorna informacion de INEI (secundaria/historica)
	 * 
	 * 
	 * @param distritoId
	 *            Distrito de consulta
	 * @param sectorId
	 *            Sector de consulta: 1: Electricidad, 2:Transportes, 3:Residuos Solidos
	 * @return
	 */
	@GET
	@Path("/historicos")
	@Produces(MediaType.APPLICATION_JSON)
	public List<InformacionInei> listarDistritoSector(@QueryParam("distritoId") int distritoId, @QueryParam("sectorId") short sectorId) {
		logger.debug("Ejecutando listarDistritoSector");
		InformacionInei informacionInei = new InformacionInei();
		informacionInei.setDistritoId(distritoId);
		informacionInei.setSectorId(sectorId);
		return calculatuhuellaService.listarDistritoSector(informacionInei);
	}

	// URL
	// servicios/detallesElectricidad?distritoId=1280
	@GET
	@Path("/detallesElectricidad")
	@Produces(MediaType.APPLICATION_JSON)
	public List<DispositivoElectricoCO2> listarDispositivosElectricosCO2(@QueryParam("distritoId") int distritoId) {
		logger.debug("Ejecutando listarDispositivosElectricosCO2");
		return calculatuhuellaService.listarDispositivosElectricosCO2(distritoId);
	}

	// URL
	// servicios/sectores/1/categorias/
	// servicios/sectores/2/categorias/
	// servicios/sectores/3/categorias?indicador=1
	@GET
	@Path("/sectores/{sectorId}/categorias")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Categoria> listarCategorias(@PathParam("sectorId") short sectorId, @QueryParam("indicador") short indicador) {
		logger.debug("Ejecutando listarDispositivosElectricosCO2");
		return calculatuhuellaService.listarCategorias(sectorId, indicador);
	}

	// URL
	// servicios/sectores/3/elementos	
	@GET
	@Path("/sectores/{sectorId}/elementos")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Elemento> listarElementos(@PathParam("sectorId") short sectorId) {
		logger.debug("Ejecutando listarDispositivosElectricosCO2");
		return calculatuhuellaService.listarElementos(sectorId);
	}

}
