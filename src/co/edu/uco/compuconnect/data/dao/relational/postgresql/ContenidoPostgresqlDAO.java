package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.util.List;

import co.edu.uco.compuconnect.data.dao.ContenidoDAO;
import co.edu.uco.compuconnect.entities.ContenidoEntity;

public class ContenidoPostgresqlDAO implements ContenidoDAO{
	
	public ContenidoPostgresqlDAO(final Connection connection) {
		
	}

	@Override
	public void create(ContenidoEntity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ContenidoEntity> read(ContenidoEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(ContenidoEntity entity) {
		// TODO Auto-generated method stub
		
	}

}
