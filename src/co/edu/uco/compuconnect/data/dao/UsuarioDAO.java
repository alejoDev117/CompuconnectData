package co.edu.uco.compuconnect.data.dao;

import java.util.List;

import co.edu.uco.compuconnect.entities.UsuarioEntity;

public interface UsuarioDAO {

	void create(UsuarioEntity entity);
	
	List<UsuarioEntity> read(UsuarioEntity entity);
	
	void update(UsuarioEntity entity);
	
	void delete(UsuarioEntity entity);
	
}
