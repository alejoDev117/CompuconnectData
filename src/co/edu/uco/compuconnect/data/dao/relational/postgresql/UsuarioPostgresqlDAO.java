package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.util.List;

import co.edu.uco.compuconnect.data.dao.UsuarioDAO;
import co.edu.uco.compuconnect.entities.UsuarioEntity;

public final class UsuarioPostgresqlDAO implements UsuarioDAO{
	
	
	public UsuarioPostgresqlDAO(final Connection connection) {
		
	}

	@Override
	public void create(UsuarioEntity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<UsuarioEntity> read(UsuarioEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(UsuarioEntity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(UsuarioEntity entity) {
		// TODO Auto-generated method stub
		
	}
	
	

}
