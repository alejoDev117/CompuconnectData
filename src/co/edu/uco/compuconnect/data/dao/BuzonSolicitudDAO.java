package co.edu.uco.compuconnect.data.dao;

import java.util.List;

import co.edu.uco.compuconnect.entities.BuzonSolicitudEntity;

public interface BuzonSolicitudDAO {

	void create(BuzonSolicitudEntity entity);
	
	List<BuzonSolicitudEntity> read(BuzonSolicitudEntity entity);
	
	void update(BuzonSolicitudEntity entity);
	
	void delete(BuzonSolicitudEntity entity);
	
	
}
