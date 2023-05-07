package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.util.List;

import co.edu.uco.compuconnect.data.dao.EstadoPeriodoFunicionamientoDAO;
import co.edu.uco.compuconnect.entities.EstadoPeriodoFuncionamientoEntity;

public final class EstadoPeriodoFuncionamientoPostgresqlDAO implements EstadoPeriodoFunicionamientoDAO{

	public EstadoPeriodoFuncionamientoPostgresqlDAO(final Connection connection) {
		
	}

	@Override
	public void create(EstadoPeriodoFuncionamientoEntity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<EstadoPeriodoFuncionamientoEntity> read(EstadoPeriodoFuncionamientoEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
