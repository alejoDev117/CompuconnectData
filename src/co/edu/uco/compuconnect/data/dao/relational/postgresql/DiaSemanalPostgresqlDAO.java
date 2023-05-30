package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.compuconnect.crosscutting.exceptions.CompuconnectDataException;
import co.edu.uco.compuconnect.crosscutting.utils.Messages.DiaSemanalPostgresqlDAOMessage;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;
import co.edu.uco.compuconnect.crosscutting.utils.UtilText;
import co.edu.uco.compuconnect.crosscutting.utils.UtilUUID;
import co.edu.uco.compuconnect.data.dao.DiaSemanalDAO;
import co.edu.uco.compuconnect.data.dao.relational.SqlDAO;
import co.edu.uco.compuconnect.entities.DiaSemanalEntity;

public final class DiaSemanalPostgresqlDAO extends SqlDAO<DiaSemanalEntity> implements DiaSemanalDAO {

    public DiaSemanalPostgresqlDAO(final Connection connection) {
       super(connection);
    }

    @Override
    public void create(DiaSemanalEntity entity) {
        String sqlStatement = "INSERT INTO dia_semanal (identificador, nombre) VALUES (?, ?)";

        try (PreparedStatement statement = getConnection().prepareStatement(sqlStatement)) {
            statement.setObject(1, entity.getIdentificador());
            statement.setString(2, entity.getNombre());
            statement.executeUpdate();
        } catch (final SQLException exception) {
        	throw CompuconnectDataException.create(DiaSemanalPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_TECHNICAL_MESSAGE, DiaSemanalPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_USER_MESSAGE,exception);
        	
        }catch(final Exception exception) {
        	throw CompuconnectDataException.create(DiaSemanalPostgresqlDAOMessage.CREATE_EXCEPTION_TECHNICAL_MESSAGE, DiaSemanalPostgresqlDAOMessage.CREATE_EXCEPTION_USER_MESSAGE,exception);
        }
    }

    @Override
    public List<DiaSemanalEntity> read(DiaSemanalEntity entity) {
    	var sqlStatement = new StringBuilder();
    	var listParameters = new ArrayList<>();
    	
    	sqlStatement.append(prepareSelect());
    	sqlStatement.append(prepareFrom());
    	sqlStatement.append(prepareWhere(entity, listParameters));
    	sqlStatement.append(prepareOrderBy());
    	
    	try (var prepareStatement = getConnection().prepareStatement(sqlStatement.toString())){
    		setParameters(prepareStatement, listParameters);
    		return executeQuery(prepareStatement);
    		
    	}catch (CompuconnectDataException exception) {
    		throw exception;
    	}catch(SQLException exception) {
    		throw CompuconnectDataException.create(DiaSemanalPostgresqlDAOMessage.READ_SQL_EXCEPTION_TECHNICAL_MESSAGE, DiaSemanalPostgresqlDAOMessage.READ_SQL_EXCEPTION_USER_MESSAGE, exception);
    	}catch(Exception exception) {
    		throw CompuconnectDataException.create(DiaSemanalPostgresqlDAOMessage.READ_EXCEPTION_TECHNICAL_MESSAGE, DiaSemanalPostgresqlDAOMessage.READ_EXCEPTION_USER_MESSAGE, exception);
    	}
    }

	@Override
	protected String prepareSelect() {
		return "SELECT identificador, nombre ";
	}

	@Override
	protected String prepareFrom() {
		return "FROM dia_semanal ";
	}

	@Override
	protected String prepareWhere(final DiaSemanalEntity entity, List<Object> parameters) {

	    final var where = new StringBuilder("");
	    parameters = UtilObject.getDefault(parameters, new ArrayList<>());
	    var setWhere = true;

	    if (!UtilObject.isNull(entity)) {

	        if (!UtilUUID.isDefault(entity.getIdentificador())) {
	            parameters.add(entity.getIdentificador());
	            where.append("WHERE identificador = ? ");
	            setWhere = false;
	        }

	        if (!UtilText.getUtilText().isEmpty(entity.getNombre())) {
	            parameters.add(entity.getNombre());
	            where.append(setWhere ? "WHERE" : "AND ").append("nombre = ? ");
	        }
	        
	    }
	    return where.toString();
	}


	@Override
	protected String prepareOrderBy() {
		return "ORDER BY nombre ASC";
	}

	@Override
	protected void setParameters(PreparedStatement prepareStat, List<Object> parameters) {
		try {
			
		if(!UtilObject.isNull(parameters) && !UtilObject.isNull(prepareStat)) {
			for(int index = 0; index < parameters.size();index++) {
				prepareStat.setObject(index + 1, parameters.get(index));
				
			}
		}
		}catch (SQLException exception) {
			throw CompuconnectDataException.create(DiaSemanalPostgresqlDAOMessage.SET_PARAMETERS_SQL_EXCEPTION_TECHNICAL_MESSAGE, DiaSemanalPostgresqlDAOMessage.SET_PARAMETERS_SQL_EXCEPTION_USER_MESSAGE, exception);
		}catch (Exception exception) {
			throw CompuconnectDataException.create(DiaSemanalPostgresqlDAOMessage.SET_PARAMETERS_EXCEPTION_TECHNICAL_MESSAGE, DiaSemanalPostgresqlDAOMessage.SET_PARAMETERS_EXCEPTION_USER_MESSAGE, exception);
		}
	}

	@Override
	protected List<DiaSemanalEntity> executeQuery(PreparedStatement preparedStatement) {
		
		List<DiaSemanalEntity> listResultSet = new ArrayList<>();		
		
		try(var resultSet = preparedStatement.executeQuery()){
			
			while(resultSet.next()) {
				var entityTmp = new DiaSemanalEntity(resultSet.getObject("identificador",UUID.class), resultSet.getString("nombre"));
				listResultSet.add(entityTmp);
			}
			
		}catch (SQLException exception) {
			throw CompuconnectDataException.create(DiaSemanalPostgresqlDAOMessage.EXCECUTE_QUERY_SQL_EXCEPTION_TECHNICAL_MESSAGE, DiaSemanalPostgresqlDAOMessage.EXCECUTE_QUERY_SQL_EXCEPTION_USER_MESSAGE, exception);
		}catch (Exception exception) {
			throw CompuconnectDataException.create(DiaSemanalPostgresqlDAOMessage.EXCECUTE_QUERY_EXCEPTION_TECHNICAL_MESSAGE, DiaSemanalPostgresqlDAOMessage.EXCECUTE_QUERY_EXCEPTION_USER_MESSAGE, exception);
	}
	
		return listResultSet;
	}
}
