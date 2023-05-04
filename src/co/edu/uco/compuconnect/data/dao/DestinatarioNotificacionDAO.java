package co.edu.uco.compuconnect.data.dao;

import java.util.List;

import co.edu.uco.compuconnect.entities.DestinatarioNotificacionEntity;

public interface DestinatarioNotificacionDAO {

	void create(DestinatarioNotificacionEntity entity);
	
	List<DestinatarioNotificacionEntity> read(DestinatarioNotificacionEntity entity);
	
	void delete(DestinatarioNotificacionEntity entity);
	
	
}
