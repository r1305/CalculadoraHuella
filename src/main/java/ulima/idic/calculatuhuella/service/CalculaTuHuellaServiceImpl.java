package ulima.idic.calculatuhuella.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ulima.idic.calculatuhuella.dao.CalculaTuHuellaDAOImpl;
import ulima.idic.calculatuhuella.dao.CategoriaDAOImpl;
import ulima.idic.calculatuhuella.dao.DistritoDAOImpl;
import ulima.idic.calculatuhuella.dao.InformacionIneiDAOImpl;
import ulima.idic.calculatuhuella.domain.Categoria;
import ulima.idic.calculatuhuella.domain.DispositivoElectricoCO2;
import ulima.idic.calculatuhuella.domain.Distrito;
import ulima.idic.calculatuhuella.domain.DistritoCO2;
import ulima.idic.calculatuhuella.domain.Elemento;
import ulima.idic.calculatuhuella.domain.InformacionInei;

@Service("calculatuhuellaService")
public class CalculaTuHuellaServiceImpl implements CalculaTuHuellaService {

	@Autowired
	CalculaTuHuellaDAOImpl calculatuhuellaDAO;

	@Autowired
	DistritoDAOImpl distritoDAO;

	@Autowired
	InformacionIneiDAOImpl informacionIneiDAO;

	@Autowired
	CategoriaDAOImpl categoriaDAO;

	public List<Distrito> listarDistitos() {
		return distritoDAO.listarDistitos();
	}

	public List<DistritoCO2> listarDistitosCO2() {
		return distritoDAO.listarDistitosCO2();
	}

	public List<InformacionInei> listarDistritoSector(InformacionInei informacionInei) {
		return informacionIneiDAO.listarDistritoSector(informacionInei);
	}

	public List<DispositivoElectricoCO2> listarDispositivosElectricosCO2(int distritoId) {
		return distritoDAO.listarDispositivosElectricosCO2(distritoId);
	}

	// Comun
	public List<Categoria> listarCategorias(short sectorId, short indicador) {
		return categoriaDAO.listarCategorias(sectorId, indicador);
	}

	public List<Elemento> listarElementos(short sectorId) {
		return calculatuhuellaDAO.listarElementos(sectorId);
	}
}
