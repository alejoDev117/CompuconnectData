package co.edu.uco.compuconnect.data.dao;

import java.util.List;

import co.edu.uco.compuconnect.entities.EstadoPeriodoFuncionamientoEntity;

public interface EstadoPeriodoFunicionamientoDAO {

	void create(EstadoPeriodoFuncionamientoEntity entity);
	
	List<EstadoPeriodoFuncionamientoEntity> read(EstadoPeriodoFuncionamientoEntity entity);
}
