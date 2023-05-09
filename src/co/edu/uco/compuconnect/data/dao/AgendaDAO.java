package co.edu.uco.compuconnect.data.dao;

import java.util.List;

import co.edu.uco.compuconnect.entities.AgendaEntity;

public interface AgendaDAO {

	void create(AgendaEntity entity);
	
	List<AgendaEntity> read(AgendaEntity entity);
	
	void uptade(AgendaEntity entity);
	
	void delete(AgendaEntity entity);
	
	
}