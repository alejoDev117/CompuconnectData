package co.edu.uco.compuconnect.data.dao;

import java.util.List;

import co.edu.uco.compuconnect.entities.ExcepcionAgendaEntity;

public interface ExcepcionAgendaDAO {

	void create(ExcepcionAgendaEntity entity);
	
	List<ExcepcionAgendaEntity> read(ExcepcionAgendaEntity entity);
	
	void delete(ExcepcionAgendaEntity entity);
}
