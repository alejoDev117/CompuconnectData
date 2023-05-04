package co.edu.uco.compuconnect.data.dao;

import java.util.List;

import co.edu.uco.compuconnect.entities.EstadoNotificacionEntity;

public interface EstadoNotificacionDAO {
	
	void create(EstadoNotificacionEntity entity);
	
	List<EstadoNotificacionEntity> read(EstadoNotificacionEntity entity);
	

}
