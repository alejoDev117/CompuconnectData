package co.edu.uco.compuconnect.data.dao;

import java.util.List;

import co.edu.uco.compuconnect.entities.CentroInformaticaEntity;

public interface CentroInformaticaDAO {

	void create(CentroInformaticaEntity entity);
	
	List<CentroInformaticaEntity> read(CentroInformaticaEntity entity);
	
	void update(CentroInformaticaEntity entity);
	
	void delete(CentroInformaticaEntity entity);
	
	
}
