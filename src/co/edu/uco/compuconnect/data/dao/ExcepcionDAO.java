package co.edu.uco.compuconnect.data.dao;

import java.util.List;

import co.edu.uco.compuconnect.entities.ExcepcionEntity;

public interface ExcepcionDAO {

	void create(ExcepcionEntity entity);
	
	List<ExcepcionEntity> read(ExcepcionEntity entity);
	
	void update(ExcepcionEntity entity);
	
	void delete(ExcepcionEntity entity);
	
}
