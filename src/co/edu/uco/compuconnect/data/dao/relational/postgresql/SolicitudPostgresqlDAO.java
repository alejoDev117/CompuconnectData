package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.util.List;

import co.edu.uco.compuconnect.data.dao.SolicitudDAO;
import co.edu.uco.compuconnect.entities.SolicitudEntity;

public final  class SolicitudPostgresqlDAO implements SolicitudDAO{
	
	
	public SolicitudPostgresqlDAO(final Connection connection) {
		
		
	}

	@Override
	public void create(SolicitudEntity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<SolicitudEntity> read(SolicitudEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(SolicitudEntity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(SolicitudEntity entity) {
		// TODO Auto-generated method stub
		
	}
	
	

}
