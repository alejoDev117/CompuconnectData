package co.edu.uco.compuconnect.data.dao;

import java.util.List;


import co.edu.uco.compuconnect.entities.PeriodoFuncionamientoDiaFestivoEntity;

public interface PeriodoFuncionamientoDiaFestivoDAO {

	void create(PeriodoFuncionamientoDiaFestivoEntity entity);
	
	List<PeriodoFuncionamientoDiaFestivoEntity> read(PeriodoFuncionamientoDiaFestivoEntity entity);
	
	void delete(PeriodoFuncionamientoDiaFestivoEntity entity);
	
}
