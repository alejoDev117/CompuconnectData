package co.edu.uco.compuconnect.data.dao;

import java.util.List;

import co.edu.uco.compuconnect.entities.PerfilEntity;

public interface PerfilDAO {

	void create(PerfilEntity entity);
	
	List<PerfilEntity> read(PerfilEntity entity);
	
	void delete(PerfilEntity entity);
	
}
