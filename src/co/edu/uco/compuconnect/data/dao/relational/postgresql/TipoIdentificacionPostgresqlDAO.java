package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.util.List;

import co.edu.uco.compuconnect.data.dao.TipoIdentificacionDAO;
import co.edu.uco.compuconnect.entities.TipoIdentificacionEntity;

public final class TipoIdentificacionPostgresqlDAO implements TipoIdentificacionDAO{
	
	
	public TipoIdentificacionPostgresqlDAO(final Connection connection) {
		
	}

	@Override
	public void create(TipoIdentificacionEntity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<TipoIdentificacionEntity> read(TipoIdentificacionEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
