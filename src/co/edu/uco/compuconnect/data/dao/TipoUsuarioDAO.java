package co.edu.uco.compuconnect.data.dao;

import java.util.List;

import co.edu.uco.compuconnect.entities.TipoUsuarioEntity;

public interface TipoUsuarioDAO {

	void create(TipoUsuarioEntity entity);
	
	List<TipoUsuarioEntity> read(TipoUsuarioEntity entity);
	
}
