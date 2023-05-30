package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.compuconnect.crosscutting.exceptions.CompuconnectDataException;
import co.edu.uco.compuconnect.crosscutting.utils.Messages.TiempoFuncionamientoCentroInformaticaPostgresqlDAOMessage;
import co.edu.uco.compuconnect.data.dao.TiempoFuncionamientoCentroInformaticaDAO;
import co.edu.uco.compuconnect.data.dao.relational.SqlDAO;
import co.edu.uco.compuconnect.entities.TiempoFuncionamientoCentroInformaticaEntity;

public final class TiempoFuncionamientoCentroInformaticaPostgresqlDAO extends SqlDAO<TiempoFuncionamientoCentroInformaticaEntity> implements TiempoFuncionamientoCentroInformaticaDAO{
	

	
	public TiempoFuncionamientoCentroInformaticaPostgresqlDAO(final Connection connection) {
		super(connection);
		
	}
		

	@Override
	public void create(TiempoFuncionamientoCentroInformaticaEntity entity) {
	    String query = "INSERT INTO tiempo_funcionamiento_centro_informatica (identificador, periodoFuncionamiento, centroInformatica, diaSemanal, horaInicio, horaFin) VALUES (?, ?, ?, ?, ?, ?)";
	    try (PreparedStatement statement = getConnection().prepareStatement(query)) {
	        statement.setObject(1, entity.getIdentificador());
	        statement.setObject(2, entity.getPeriodoFuncionamiento().getIdentificador());
	        statement.setObject(3, entity.getCentroInfomatica().getIdentificador());
	        statement.setObject(4, entity.getDia().getIdentificador());
	        statement.setObject(5, entity.getHoraInicio());
	        statement.setObject(6, entity.getHoraFin());

	        statement.executeUpdate();
	    }catch(SQLException exception) {
	    	throw CompuconnectDataException.create(TiempoFuncionamientoCentroInformaticaPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_TECHNICAL_MESSAGE, TiempoFuncionamientoCentroInformaticaPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_USER_MESSAGE, exception);
	    }catch (Exception exception) {
	    	throw CompuconnectDataException.create(TiempoFuncionamientoCentroInformaticaPostgresqlDAOMessage.CREATE_EXCEPTION_TECHNICAL_MESSAGE, TiempoFuncionamientoCentroInformaticaPostgresqlDAOMessage.CREATE_EXCEPTION_USER_MESSAGE, exception);
	    }
	}

	@Override
	public List<TiempoFuncionamientoCentroInformaticaEntity> read(TiempoFuncionamientoCentroInformaticaEntity entity) {
	    List<TiempoFuncionamientoCentroInformaticaEntity> result = new ArrayList<>();

	    return result;
	}

	@Override
	public void update(TiempoFuncionamientoCentroInformaticaEntity entity) {
	    String query = "UPDATE tiempo_funcionamiento_centro_informatica SET periodoFuncionamiento = ?, centroInformatica = ?, diaInformatica = ?, horaInicio = ?, horaFin = ? WHERE identificador = ?";
	    try (PreparedStatement statement = getConnection().prepareStatement(query)) {
	        statement.setObject(1, entity.getPeriodoFuncionamiento().getIdentificador());
	        statement.setObject(2, entity.getCentroInfomatica().getIdentificador());
	        statement.setObject(3, entity.getDia().getIdentificador());
	        statement.setObject(4, entity.getHoraInicio());
	        statement.setObject(5, entity.getHoraFin());
	        statement.setObject(6, entity.getIdentificador());

	        statement.executeUpdate();
	    }catch(SQLException exception) {
	    	throw CompuconnectDataException.create(TiempoFuncionamientoCentroInformaticaPostgresqlDAOMessage.UPDATE_SQL_EXCEPTION_TECHNICAL_MESSAGE, TiempoFuncionamientoCentroInformaticaPostgresqlDAOMessage.UPDATE_SQL_EXCEPTION_USER_MESSAGE, exception);
	    }catch (Exception exception) {
	    	throw CompuconnectDataException.create(TiempoFuncionamientoCentroInformaticaPostgresqlDAOMessage.UPDATE_EXCEPTION_TECHNICAL_MESSAGE, TiempoFuncionamientoCentroInformaticaPostgresqlDAOMessage.UPDATE_EXCEPTION_USER_MESSAGE, exception);
	    }
	}

	@Override
	public void delete(TiempoFuncionamientoCentroInformaticaEntity entity) {
	    String query = "DELETE FROM tiempo_funcionamiento_centro_informatica WHERE identificador = ?";
	    try (PreparedStatement statement = getConnection().prepareStatement(query)) {
	        statement.setObject(1, entity.getIdentificador());

	        statement.executeUpdate();
	    }catch(SQLException exception) {
	    	throw CompuconnectDataException.create(TiempoFuncionamientoCentroInformaticaPostgresqlDAOMessage.DELETE_SQL_EXCEPTION_TECHNICAL_MESSAGE, TiempoFuncionamientoCentroInformaticaPostgresqlDAOMessage.DELETE_SQL_EXCEPTION_USER_MESSAGE, exception);
	    }catch (Exception exception) {
	    	throw CompuconnectDataException.create(TiempoFuncionamientoCentroInformaticaPostgresqlDAOMessage.DELETE_EXCEPTION_TECHNICAL_MESSAGE, TiempoFuncionamientoCentroInformaticaPostgresqlDAOMessage.DELETE_EXCEPTION_USER_MESSAGE, exception);
	    }
	}


	@Override
	protected String prepareSelect() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	protected String prepareFrom() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	protected String prepareWhere(TiempoFuncionamientoCentroInformaticaEntity entity, List<Object> parameters) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	protected String prepareOrderBy() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	protected void setParameters(PreparedStatement prepareStat, List<Object> parameters) {
		// TODO Auto-generated method stub
		
	}


	@Override
	protected List<TiempoFuncionamientoCentroInformaticaEntity> executeQuery(PreparedStatement preparedStatement) {
		// TODO Auto-generated method stub
		return null;
	}
}
