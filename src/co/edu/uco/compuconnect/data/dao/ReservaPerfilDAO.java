package co.edu.uco.compuconnect.data.dao;

import java.util.List;

import co.edu.uco.compuconnect.entities.ReservaPerfilEntity;

public interface ReservaPerfilDAO {

	void create(ReservaPerfilEntity entity);
	
	List<ReservaPerfilEntity> read(ReservaPerfilEntity entity);
	
	void delete(ReservaPerfilEntity entity);
	
	
}
