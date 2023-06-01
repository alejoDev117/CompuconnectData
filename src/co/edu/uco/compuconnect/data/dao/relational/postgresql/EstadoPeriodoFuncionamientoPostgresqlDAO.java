package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.compuconnect.crosscutting.exceptions.CompuconnectDataException;
import co.edu.uco.compuconnect.crosscutting.utils.Messages.EstadoPeriodoFuncionamientoPostgresqlDAOMessage;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;
import co.edu.uco.compuconnect.crosscutting.utils.UtilText;
import co.edu.uco.compuconnect.crosscutting.utils.UtilUUID;
import co.edu.uco.compuconnect.data.dao.EstadoPeriodoFunicionamientoDAO;
import co.edu.uco.compuconnect.data.dao.relational.SqlDAO;
import co.edu.uco.compuconnect.entities.EstadoPeriodoFuncionamientoEntity;


public final class EstadoPeriodoFuncionamientoPostgresqlDAO extends SqlDAO<EstadoPeriodoFuncionamientoEntity> implements EstadoPeriodoFunicionamientoDAO {


    public EstadoPeriodoFuncionamientoPostgresqlDAO(final Connection connection) {
        super(connection);
    }

    @Override
    public void create(EstadoPeriodoFuncionamientoEntity entity) {
        String sqlStatement = "INSERT INTO estados_periodo_funcionamiento (identificador, nombre, descripcion) VALUES (?, ?)";

        try (PreparedStatement statement = getConnection().prepareStatement(sqlStatement)) {
            statement.setObject(1, entity.getIdentificador());
            statement.setString(2, entity.getNombre());
            statement.setString(3, entity.getDescripcion());
            statement.executeUpdate();
        } catch (final SQLException exception) {
        	throw CompuconnectDataException.create(EstadoPeriodoFuncionamientoPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_TECHNICAL_MESSAGE, EstadoPeriodoFuncionamientoPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_USER_MESSAGE,exception);
        	
        }catch(final Exception exception) {
        	throw CompuconnectDataException.create(EstadoPeriodoFuncionamientoPostgresqlDAOMessage.CREATE_EXCEPTION_TECHNICAL_MESSAGE, EstadoPeriodoFuncionamientoPostgresqlDAOMessage.CREATE_EXCEPTION_USER_MESSAGE,exception);
        }
    }

    @Override
    public List<EstadoPeriodoFuncionamientoEntity> read(EstadoPeriodoFuncionamientoEntity entity) {
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
    		throw CompuconnectDataException.create(EstadoPeriodoFuncionamientoPostgresqlDAOMessage.READ_SQL_EXCEPTION_TECHNICAL_MESSAGE, EstadoPeriodoFuncionamientoPostgresqlDAOMessage.READ_SQL_EXCEPTION_USER_MESSAGE, exception);
    	}catch(Exception exception) {
    		throw CompuconnectDataException.create(EstadoPeriodoFuncionamientoPostgresqlDAOMessage.READ_EXCEPTION_TECHNICAL_MESSAGE, EstadoPeriodoFuncionamientoPostgresqlDAOMessage.READ_EXCEPTION_USER_MESSAGE, exception);
    	}
    }

	@Override
	protected String prepareSelect() {
		return "SELECT identificador, nombre, descripcion ";
	}

	@Override
	protected String prepareFrom() {
		return "FROM estados_periodo_funcionamiento ";
	}

	@Override
	protected String prepareWhere(final EstadoPeriodoFuncionamientoEntity entity, List<Object> parameters) {

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
	            setWhere = false;
	        }

	        if (!UtilText.getUtilText().isEmpty(entity.getDescripcion())) {
	            parameters.add(entity.getDescripcion());
	            where.append(setWhere ? "WHERE" : "AND ").append("descripcion =? ");
	            setWhere = false;
	        }
	    }
	    return where.toString();
	}




	@Override
	protected String prepareOrderBy() {
		return "ORDER BY nombre ASC ";
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
			throw CompuconnectDataException.create(EstadoPeriodoFuncionamientoPostgresqlDAOMessage.SET_PARAMETERS_SQL_EXCEPTION_TECHNICAL_MESSAGE, EstadoPeriodoFuncionamientoPostgresqlDAOMessage.SET_PARAMETERS_SQL_EXCEPTION_USER_MESSAGE, exception);
		}catch (Exception exception) {
			throw CompuconnectDataException.create(EstadoPeriodoFuncionamientoPostgresqlDAOMessage.SET_PARAMETERS_EXCEPTION_TECHNICAL_MESSAGE, EstadoPeriodoFuncionamientoPostgresqlDAOMessage.SET_PARAMETERS_EXCEPTION_USER_MESSAGE, exception);
		}
		
	}

	@Override
	protected List<EstadoPeriodoFuncionamientoEntity> executeQuery(PreparedStatement preparedStatement) {
		
		List<EstadoPeriodoFuncionamientoEntity> listResultSet = new ArrayList<>();		
		
		try(var resultSet = preparedStatement.executeQuery()){
			
			while(resultSet.next()) {
				var entityTmp = new EstadoPeriodoFuncionamientoEntity(resultSet.getObject("identificador",UUID.class), resultSet.getString("nombre"), resultSet.getString("descripcion"));
				listResultSet.add(entityTmp);
			}
			
		}catch (SQLException exception) {
			throw CompuconnectDataException.create(EstadoPeriodoFuncionamientoPostgresqlDAOMessage.EXCECUTE_QUERY_SQL_EXCEPTION_TECHNICAL_MESSAGE, EstadoPeriodoFuncionamientoPostgresqlDAOMessage.EXCECUTE_QUERY_SQL_EXCEPTION_USER_MESSAGE, exception);
		}catch (Exception exception) {
			throw CompuconnectDataException.create(EstadoPeriodoFuncionamientoPostgresqlDAOMessage.EXCECUTE_QUERY_EXCEPTION_TECHNICAL_MESSAGE, EstadoPeriodoFuncionamientoPostgresqlDAOMessage.EXCECUTE_QUERY_EXCEPTION_USER_MESSAGE, exception);
	}
	
		return listResultSet;
	}
	
}
