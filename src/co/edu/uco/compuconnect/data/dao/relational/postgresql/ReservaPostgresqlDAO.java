package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.util.List;

import co.edu.uco.compuconnect.data.dao.ReservaDAO;
import co.edu.uco.compuconnect.entities.ReservaEntity;

public final class ReservaPostgresqlDAO implements ReservaDAO{
	
	public ReservaPostgresqlDAO(final Connection connection) {
		
	}

	@Override
	public void create(ReservaEntity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ReservaEntity> read(ReservaEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(ReservaEntity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(ReservaEntity entity) {
		// TODO Auto-generated method stub
		
	}

}
