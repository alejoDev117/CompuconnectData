package co.edu.uco.compuconnect.data.dao;

import java.util.List;

import co.edu.uco.compuconnect.entities.DiaFestivoEntity;
import co.edu.uco.compuconnect.entities.DiaSemanalEntity;

public interface DiaSemanalDAO {
	
	void create(DiaSemanalEntity entity);
	
	List<DiaSemanalEntity> read(DiaSemanalEntity entity);

}
