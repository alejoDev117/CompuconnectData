package co.edu.uco.compuconnect.data.dao;

import java.util.List;

import co.edu.uco.compuconnect.entities.RespuestaEntity;

public interface RespuestaDAO {

	void create(RespuestaEntity entity);
	
	List<RespuestaEntity> read(RespuestaEntity entity);
	
	void delete(RespuestaEntity entity);
	
	
}
