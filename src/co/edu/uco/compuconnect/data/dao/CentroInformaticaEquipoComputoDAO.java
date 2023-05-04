package co.edu.uco.compuconnect.data.dao;

import java.util.List;

import co.edu.uco.compuconnect.entities.CentroInformaticaEquipoComputoEntity;

public interface CentroInformaticaEquipoComputoDAO {

	void create(CentroInformaticaEquipoComputoEntity entity);
	
	List<CentroInformaticaEquipoComputoEntity> read(CentroInformaticaEquipoComputoEntity entity);
	
	void delete(CentroInformaticaEquipoComputoEntity entity);
	
	
}
