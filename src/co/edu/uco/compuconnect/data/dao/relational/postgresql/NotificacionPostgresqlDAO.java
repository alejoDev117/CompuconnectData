package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.util.List;

import co.edu.uco.compuconnect.data.dao.NotificacionDAO;
import co.edu.uco.compuconnect.entities.NotificacionEntity;

public final class NotificacionPostgresqlDAO implements NotificacionDAO{
	
	public NotificacionPostgresqlDAO(final Connection connection) {
		
	}

	@Override
	public void create(NotificacionEntity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<NotificacionEntity> read(NotificacionEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(NotificacionEntity entity) {
		// TODO Auto-generated method stub
		
	}
	

}
