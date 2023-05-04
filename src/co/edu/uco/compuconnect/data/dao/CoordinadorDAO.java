package co.edu.uco.compuconnect.data.dao;

import java.util.List;

import co.edu.uco.compuconnect.entities.CoordinadorEntity;

public interface CoordinadorDAO {

	void create(CoordinadorEntity entity);
	
	List<CoordinadorEntity> read(CoordinadorEntity entity);
	
	void update(CoordinadorEntity entity);
	
	void delete(CoordinadorEntity entity);
}
