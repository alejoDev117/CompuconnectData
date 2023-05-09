package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.util.List;

import co.edu.uco.compuconnect.data.dao.DiaSemanalDAO;
import co.edu.uco.compuconnect.entities.DiaFestivoEntity;
import co.edu.uco.compuconnect.entities.DiaSemanalEntity;

public final  class DiaSemanalPostgresqlDAO implements DiaSemanalDAO{

	public DiaSemanalPostgresqlDAO(final Connection connection) {
		
	}
	@Override
	public void create(DiaSemanalEntity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<DiaSemanalEntity> read(DiaSemanalEntity entity) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
