package co.edu.uco.compuconnect.data.dao;

import java.util.List;

import co.edu.uco.compuconnect.entities.PersonaEncargadaEntity;

public interface PersonaEncargadaDAO {

	void create(PersonaEncargadaEntity entity);
	
	List<PersonaEncargadaEntity> read(PersonaEncargadaEntity entity);
	
	void update(PersonaEncargadaEntity entity);
	
	void delete(PersonaEncargadaEntity entity);
	
}
