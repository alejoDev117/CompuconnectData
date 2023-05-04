package co.edu.uco.compuconnect.data.dao;

import java.util.List;

import co.edu.uco.compuconnect.entities.SolicitudPerfilEntity;

public interface SolicitudPerfilDAO {

	void create(SolicitudPerfilEntity entity);
	
	List<SolicitudPerfilEntity> read(SolicitudPerfilEntity entity);
	
	void delete(SolicitudPerfilEntity entity);
	
}
