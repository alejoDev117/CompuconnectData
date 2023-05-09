package co.edu.uco.compuconnect.data.dao;

import java.util.List;

import co.edu.uco.compuconnect.entities.ContenidoEntity;


public interface ContenidoDAO {

	void create(ContenidoEntity entity);
	
	List<ContenidoEntity> read(ContenidoEntity entity);
	
	void delete(ContenidoEntity entity);
	
}
