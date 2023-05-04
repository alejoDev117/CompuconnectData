package co.edu.uco.compuconnect.data.dao;

import java.util.List;

import co.edu.uco.compuconnect.entities.TipoSolicitudEntity;

public interface TipoSolicitudDAO {
	
	void create(TipoSolicitudEntity entity);
	
	List<TipoSolicitudEntity> read(TipoSolicitudEntity entity);
	

}
