package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.util.List;

import co.edu.uco.compuconnect.data.dao.ReservaPerfilDAO;
import co.edu.uco.compuconnect.entities.ReservaPerfilEntity;

public final class ReservaPerfilPostgresqlDAO implements ReservaPerfilDAO{
	
	public ReservaPerfilPostgresqlDAO(final Connection connection) {
		
		
	}

	@Override
	public void create(ReservaPerfilEntity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ReservaPerfilEntity> read(ReservaPerfilEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(ReservaPerfilEntity entity) {
		// TODO Auto-generated method stub
		
	}
}
