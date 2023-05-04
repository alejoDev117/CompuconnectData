package co.edu.uco.compuconnect.data.dao;

import java.util.List;

import co.edu.uco.compuconnect.entities.ReservaEntity;

public interface ReservaDAO {

	void create(ReservaEntity entity);
	
	List<ReservaEntity> read(ReservaEntity entity);
	
	void update(ReservaEntity entity);
	
	void delete(ReservaEntity entity);
	
}
