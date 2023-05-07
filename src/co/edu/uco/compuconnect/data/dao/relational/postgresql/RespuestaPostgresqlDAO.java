package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.util.List;

import co.edu.uco.compuconnect.data.dao.RespuestaDAO;
import co.edu.uco.compuconnect.entities.RespuestaEntity;

public final  class RespuestaPostgresqlDAO implements RespuestaDAO{
	
	public RespuestaPostgresqlDAO(final Connection connection) {

	}

	@Override
	public void create(RespuestaEntity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<RespuestaEntity> read(RespuestaEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(RespuestaEntity entity) {
		// TODO Auto-generated method stub
		
	}
	
	
}
