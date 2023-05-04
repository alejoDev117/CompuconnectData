package co.edu.uco.compuconnect.data.dao;

import java.util.List;

import co.edu.uco.compuconnect.entities.EstadoSolicitudEntity;

public interface EstadoSolicitudDAO {

	void create(EstadoSolicitudEntity entity);
	
	List<EstadoSolicitudEntity> read(EstadoSolicitudEntity entity);
	
}

