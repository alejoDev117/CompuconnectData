package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.util.List;

import co.edu.uco.compuconnect.data.dao.EstadoEquipoComputoDAO;
import co.edu.uco.compuconnect.entities.EstadoEquipoComputoEntity;

public final  class EstadoEquipoComputoPostgresqlDAO implements EstadoEquipoComputoDAO{

	public EstadoEquipoComputoPostgresqlDAO(final Connection connection) {
		
	}
	
	
	@Override
	public void create(EstadoEquipoComputoEntity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<EstadoEquipoComputoEntity> read(EstadoEquipoComputoEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
