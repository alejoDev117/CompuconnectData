package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.util.List;

import co.edu.uco.compuconnect.data.dao.CoordinadorDAO;
import co.edu.uco.compuconnect.entities.CoordinadorEntity;

public final class CoordinadorPostgresqlDAO implements CoordinadorDAO{

	public CoordinadorPostgresqlDAO(final Connection connection) {
		
	}
	
	
	@Override
	public void create(CoordinadorEntity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CoordinadorEntity> read(CoordinadorEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(CoordinadorEntity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(CoordinadorEntity entity) {
		// TODO Auto-generated method stub
		
	}

}
