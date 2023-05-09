package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.util.List;

import co.edu.uco.compuconnect.data.dao.AgendaDAO;
import co.edu.uco.compuconnect.entities.AgendaEntity;

public final class AgendaPostgresqlDAO implements AgendaDAO{

	public AgendaPostgresqlDAO(final Connection connection) {
		
	}
	
	
	
	@Override
	public final void create(final AgendaEntity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public  final List<AgendaEntity> read(final AgendaEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public final void delete(final AgendaEntity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void uptade(AgendaEntity entity) {
		// TODO Auto-generated method stub
		
	}

}
