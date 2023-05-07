package co.edu.uco.compuconnect.data.dao;

import java.util.List;

import co.edu.uco.compuconnect.entities.HorarioPersonaEncargadaEntity;

public interface HorarioPersonaEncargadaDAO {

	void create(HorarioPersonaEncargadaEntity entity);
	
	List<HorarioPersonaEncargadaEntity> read(HorarioPersonaEncargadaEntity entity);
	
	void update(HorarioPersonaEncargadaEntity entity);
	
	
	void delete(HorarioPersonaEncargadaEntity entity);
	
}
