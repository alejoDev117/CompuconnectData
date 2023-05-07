package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.util.List;

import co.edu.uco.compuconnect.data.dao.ExcepcionAgendaDAO;
import co.edu.uco.compuconnect.entities.ExcepcionAgendaEntity;

public final  class ExcepcionAgendaPostgresqlDAO implements ExcepcionAgendaDAO{
	
	public ExcepcionAgendaPostgresqlDAO(final Connection connection) {
		
	}

	@Override
	public void create(ExcepcionAgendaEntity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ExcepcionAgendaEntity> read(ExcepcionAgendaEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(ExcepcionAgendaEntity entity) {
		// TODO Auto-generated method stub
		
	}
	
	

}
