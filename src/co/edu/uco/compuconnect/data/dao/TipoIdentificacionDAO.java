package co.edu.uco.compuconnect.data.dao;

import java.util.List;

import co.edu.uco.compuconnect.entities.TipoIdentificacionEntity;

public interface TipoIdentificacionDAO {

	void create(TipoIdentificacionEntity entity);
	
	List<TipoIdentificacionEntity> read(TipoIdentificacionEntity entity);
	
}
