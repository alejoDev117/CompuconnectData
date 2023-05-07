package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.util.List;

import co.edu.uco.compuconnect.data.dao.EstadoNotificacionDAO;
import co.edu.uco.compuconnect.entities.EstadoNotificacionEntity;

public final class EstadoNotificacionPostgresqlDAO implements EstadoNotificacionDAO{
	
	public EstadoNotificacionPostgresqlDAO(final Connection connection) {
		
	}

	@Override
	public void create(EstadoNotificacionEntity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<EstadoNotificacionEntity> read(EstadoNotificacionEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
