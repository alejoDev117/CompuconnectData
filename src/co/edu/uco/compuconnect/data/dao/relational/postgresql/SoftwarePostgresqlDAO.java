package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.util.List;

import co.edu.uco.compuconnect.data.dao.SoftwareDAO;
import co.edu.uco.compuconnect.entities.SoftwareEntity;

public final class SoftwarePostgresqlDAO implements SoftwareDAO{
	
	public SoftwarePostgresqlDAO(final Connection connection) {

	}

	@Override
	public void create(SoftwareEntity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<SoftwareEntity> read(SoftwareEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(SoftwareEntity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(SoftwareEntity entity) {
		// TODO Auto-generated method stub
		
	}
	
	
}
