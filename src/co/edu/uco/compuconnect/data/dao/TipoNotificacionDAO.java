package co.edu.uco.compuconnect.data.dao;

import java.util.List;

import co.edu.uco.compuconnect.entities.TipoNotificacionEntity;

public interface TipoNotificacionDAO {

	void create(TipoNotificacionEntity entity);
	
	List<TipoNotificacionEntity> read(TipoNotificacionEntity entity);
	
}
