package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.util.List;

import co.edu.uco.compuconnect.data.dao.AgendaReservaDAO;
import co.edu.uco.compuconnect.entities.AgendaReservaEntity;

public final class AgendaReservaPostgresqlDAO  implements AgendaReservaDAO{

	
	public AgendaReservaPostgresqlDAO(final Connection connection) {
		
	}
	
	
	@Override
	public final void create(final AgendaReservaEntity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public final List<AgendaReservaEntity> read(final AgendaReservaEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public final void delete(final AgendaReservaEntity entity) {
		// TODO Auto-generated method stub
		
	}

}
