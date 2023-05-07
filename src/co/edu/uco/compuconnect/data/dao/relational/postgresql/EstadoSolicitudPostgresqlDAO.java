package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.util.List;

import co.edu.uco.compuconnect.data.dao.EstadoSolicitudDAO;
import co.edu.uco.compuconnect.entities.EstadoSolicitudEntity;

public final class EstadoSolicitudPostgresqlDAO implements EstadoSolicitudDAO{
	
	public EstadoSolicitudPostgresqlDAO(final Connection connection) {
		
		
	}

	@Override
	public void create(EstadoSolicitudEntity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<EstadoSolicitudEntity> read(EstadoSolicitudEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
