package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.util.List;

import co.edu.uco.compuconnect.data.dao.FrecuenciaDAO;
import co.edu.uco.compuconnect.entities.FrecuenciaEntity;

public final class FrecuenciaPostgresqlDAO implements FrecuenciaDAO{
	
	public FrecuenciaPostgresqlDAO(final Connection connection) {
		
		
	}

	@Override
	public void create(FrecuenciaEntity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<FrecuenciaEntity> read(FrecuenciaEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
