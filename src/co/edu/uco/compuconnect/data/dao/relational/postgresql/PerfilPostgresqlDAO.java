package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.util.List;

import co.edu.uco.compuconnect.data.dao.PerfilDAO;
import co.edu.uco.compuconnect.entities.PerfilEntity;

public final class PerfilPostgresqlDAO implements PerfilDAO{
	
	public PerfilPostgresqlDAO(final Connection connection) {

	}

	@Override
	public void create(PerfilEntity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<PerfilEntity> read(PerfilEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(PerfilEntity entity) {
		// TODO Auto-generated method stub
		
	}
	
}
