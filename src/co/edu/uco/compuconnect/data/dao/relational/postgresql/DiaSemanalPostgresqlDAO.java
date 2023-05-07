package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.util.List;

import co.edu.uco.compuconnect.data.dao.DiaSemanalDAO;
import co.edu.uco.compuconnect.entities.DiaFestivoEntity;

public final  class DiaSemanalPostgresqlDAO implements DiaSemanalDAO{

	public DiaSemanalPostgresqlDAO(final Connection connection) {
		
	}
	@Override
	public void create(DiaFestivoEntity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<DiaFestivoEntity> read(DiaFestivoEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
