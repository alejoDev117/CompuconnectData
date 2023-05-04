package co.edu.uco.compuconnect.data.dao;

import java.util.List;

import co.edu.uco.compuconnect.entities.FrecuenciaEntity;

public interface FrecuenciaDAO {

	void create(FrecuenciaEntity entity);
	
	List<FrecuenciaEntity> read(FrecuenciaEntity entity);
	
	
}
