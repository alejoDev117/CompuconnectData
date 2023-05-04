package co.edu.uco.compuconnect.data.dao;

import java.util.List;

import co.edu.uco.compuconnect.entities.SolicitudEntity;

public interface SolicitudDAO {

	void create(SolicitudEntity entity);
	
	List<SolicitudEntity> read(SolicitudEntity entity);
	
	void update(SolicitudEntity entity);
	
	void delete(SolicitudEntity entity);
	
}
