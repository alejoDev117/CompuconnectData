package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.util.List;

import co.edu.uco.compuconnect.data.dao.TipoUsuarioDAO;
import co.edu.uco.compuconnect.entities.TipoUsuarioEntity;

public final class TipoUsuarioPostgresqlDAO implements TipoUsuarioDAO {
	
	
	public TipoUsuarioPostgresqlDAO(final Connection connection) {
		
		
	}

	@Override
	public void create(TipoUsuarioEntity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<TipoUsuarioEntity> read(TipoUsuarioEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

}
