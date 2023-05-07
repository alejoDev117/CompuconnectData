package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.util.List;

import co.edu.uco.compuconnect.data.dao.TipoNotificacionDAO;
import co.edu.uco.compuconnect.entities.TipoNotificacionEntity;

public final  class TipoNotificacionPostgresqlDAO implements TipoNotificacionDAO{
	
	public TipoNotificacionPostgresqlDAO(final Connection connection) {
		
		
	}

	@Override
	public void create(TipoNotificacionEntity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<TipoNotificacionEntity> read(TipoNotificacionEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
