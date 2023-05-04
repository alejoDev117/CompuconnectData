package co.edu.uco.compuconnect.data.dao;

import java.util.List;

import co.edu.uco.compuconnect.entities.PeriodoFuncionamientoEntity;

public interface PeriodoFuncionamientoDAO {

	void create(PeriodoFuncionamientoEntity entity);
	
	List<PeriodoFuncionamientoEntity> read(PeriodoFuncionamientoEntity entity);
	
	void update(PeriodoFuncionamientoEntity entity);
	
	void delete(PeriodoFuncionamientoEntity entity);
	
}
