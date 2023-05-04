package co.edu.uco.compuconnect.data.dao;

import java.util.List;

import co.edu.uco.compuconnect.entities.NotificacionEntity;

public interface NotificacionDAO {

	void create(NotificacionEntity entity);
	
	List<NotificacionEntity> read(NotificacionEntity entity);
	
	void delete(NotificacionEntity entity);
	
}
