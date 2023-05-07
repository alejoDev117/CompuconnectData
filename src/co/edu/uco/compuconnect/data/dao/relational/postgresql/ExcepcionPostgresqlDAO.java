package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.util.List;

import co.edu.uco.compuconnect.data.dao.ExcepcionAgendaDAO;
import co.edu.uco.compuconnect.data.dao.ExcepcionDAO;
import co.edu.uco.compuconnect.entities.ExcepcionAgendaEntity;
import co.edu.uco.compuconnect.entities.ExcepcionEntity;

public final class ExcepcionPostgresqlDAO implements ExcepcionDAO{
	
	public ExcepcionPostgresqlDAO(final Connection connection) {
		
	}

	@Override
	public void create(ExcepcionEntity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ExcepcionEntity> read(ExcepcionEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(ExcepcionEntity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(ExcepcionEntity entity) {
		// TODO Auto-generated method stub
		
	}


	
	

}
