package co.edu.uco.compuconnect.data.dao;

import java.util.List;

import co.edu.uco.compuconnect.entities.TipoReservaEntity;

public interface TipoReservaDAO {
	
	void create(TipoReservaEntity entity);
	
	List<TipoReservaEntity> read(TipoReservaEntity entity);
	

}
