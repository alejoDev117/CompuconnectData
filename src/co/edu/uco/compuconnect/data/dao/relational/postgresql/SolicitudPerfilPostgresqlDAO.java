package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.util.List;

import co.edu.uco.compuconnect.data.dao.SolicitudPerfilDAO;
import co.edu.uco.compuconnect.entities.SolicitudPerfilEntity;

public class SolicitudPerfilPostgresqlDAO implements SolicitudPerfilDAO{
	
	public SolicitudPerfilPostgresqlDAO(final Connection connection) {
		
	}

	@Override
	public void create(SolicitudPerfilEntity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<SolicitudPerfilEntity> read(SolicitudPerfilEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(SolicitudPerfilEntity entity) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	

}
