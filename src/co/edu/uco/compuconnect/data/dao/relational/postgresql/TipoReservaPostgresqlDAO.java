package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.util.List;

import co.edu.uco.compuconnect.data.dao.TipoReservaDAO;
import co.edu.uco.compuconnect.entities.TipoReservaEntity;

public final class TipoReservaPostgresqlDAO implements TipoReservaDAO{
	
	public TipoReservaPostgresqlDAO(final Connection connection) {
		
		
	}

	@Override
	public void create(TipoReservaEntity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<TipoReservaEntity> read(TipoReservaEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
