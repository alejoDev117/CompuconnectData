package co.edu.uco.compuconnect.data.dao;

import java.util.List;

import co.edu.uco.compuconnect.entities.AgendaReservaEntity;

public interface AgendaReservaDAO {

	void create(AgendaReservaEntity entity);
	
	List<AgendaReservaEntity> read(AgendaReservaEntity entity);
	
	void delete(AgendaReservaEntity entity);
	
}
