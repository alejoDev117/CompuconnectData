package co.edu.uco.compuconnect.data.dao;

import java.util.List;

import co.edu.uco.compuconnect.entities.DiaFestivoEntity;

public interface DiaFestivoDAO {

	void create(DiaFestivoEntity entity);
	
	List<DiaFestivoEntity> read(DiaFestivoEntity entity);
	
}
