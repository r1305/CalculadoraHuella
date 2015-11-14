package ulima.idic.calculatuhuella.service;

import java.util.List;

import ulima.idic.calculatuhuella.domain.Categoria;
import ulima.idic.calculatuhuella.domain.DispositivoElectricoCO2;
import ulima.idic.calculatuhuella.domain.Distrito;
import ulima.idic.calculatuhuella.domain.DistritoCO2;
import ulima.idic.calculatuhuella.domain.Elemento;
import ulima.idic.calculatuhuella.domain.InformacionInei;

public interface CalculaTuHuellaService {
	public List<Distrito> listarDistitos();

	public List<DistritoCO2> listarDistitosCO2();

	public List<InformacionInei> listarDistritoSector(InformacionInei informacionInei);

	public List<DispositivoElectricoCO2> listarDispositivosElectricosCO2(int distritoId);

	public List<Categoria> listarCategorias(short sectorId, short indicador);

	public List<Elemento> listarElementos(short sectorId);
}
