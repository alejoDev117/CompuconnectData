package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.util.List;

import co.edu.uco.compuconnect.data.dao.TipoSolicitudDAO;
import co.edu.uco.compuconnect.entities.TipoSolicitudEntity;

public final class TipoSolicitudPostgresqlDAO implements TipoSolicitudDAO{
	
	
	public TipoSolicitudPostgresqlDAO(final Connection connection) {
		
		
	}

	@Override
	public void create(TipoSolicitudEntity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<TipoSolicitudEntity> read(TipoSolicitudEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
