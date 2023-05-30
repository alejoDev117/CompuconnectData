package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.compuconnect.crosscutting.exceptions.CompuconnectDataException;
import co.edu.uco.compuconnect.crosscutting.utils.Messages.AgendaReservaPostgresqlDAOMessage;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;
import co.edu.uco.compuconnect.crosscutting.utils.UtilUUID;
import co.edu.uco.compuconnect.data.dao.AgendaReservaDAO;
import co.edu.uco.compuconnect.data.dao.relational.SqlDAO;
import co.edu.uco.compuconnect.entities.AgendaEntity;
import co.edu.uco.compuconnect.entities.AgendaReservaEntity;
import co.edu.uco.compuconnect.entities.ReservaEntity;

public final class AgendaReservaPostgresqlDAO extends SqlDAO<AgendaReservaEntity> implements AgendaReservaDAO {

    public AgendaReservaPostgresqlDAO(final Connection connection) {
        super(connection);
    }

    @Override
    public final void create(final AgendaReservaEntity entity) {
        String sqlStatement = "INSERT INTO agenda_reserva (identificador, agenda, reserva) VALUES (?, ?, ?)";

        try (PreparedStatement statement = getConnection().prepareStatement(sqlStatement)) {
            statement.setObject(1, entity.getIdentificador());
            statement.setObject(2, entity.getAgenda().getIdentificador());
            statement.setObject(3, entity.getReserva().getIdentificador());

            statement.executeUpdate();
        } catch (SQLException exception) {
        	throw CompuconnectDataException.create(AgendaReservaPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_TECHNICAL_MESSAGE, AgendaReservaPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_USER_MESSAGE,exception);
        }catch(final Exception exception) {
        	throw CompuconnectDataException.create(AgendaReservaPostgresqlDAOMessage.CREATE_EXCEPTION_TECHNICAL_MESSAGE, AgendaReservaPostgresqlDAOMessage.CREATE_EXCEPTION_USER_MESSAGE,exception);
        }
    }

    @Override
    public final List<AgendaReservaEntity> read(final AgendaReservaEntity entity) {
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
    		throw CompuconnectDataException.create(AgendaReservaPostgresqlDAOMessage.READ_SQL_EXCEPTION_TECHNICAL_MESSAGE, AgendaReservaPostgresqlDAOMessage.READ_SQL_EXCEPTION_USER_MESSAGE, exception);
    	}catch(Exception exception) {
    		throw CompuconnectDataException.create(AgendaReservaPostgresqlDAOMessage.READ_EXCEPTION_TECHNICAL_MESSAGE, AgendaReservaPostgresqlDAOMessage.READ_EXCEPTION_USER_MESSAGE, exception);
    	}
    	
    }

    @Override
    public final void delete(final AgendaReservaEntity entity) {
        String sqlStatement = "DELETE FROM agenda_reserva WHERE identificador = ?";

        try (PreparedStatement statement = getConnection().prepareStatement(sqlStatement)) {
            statement.setObject(1, entity.getIdentificador());

            statement.executeUpdate();
        } catch (SQLException exception) {
        	throw CompuconnectDataException.create(AgendaReservaPostgresqlDAOMessage.DELETE_SQL_EXCEPTION_TECHNICAL_MESSAGE, AgendaReservaPostgresqlDAOMessage.DELETE_SQL_EXCEPTION_USER_MESSAGE, exception);
        }catch(Exception exception) {
        	throw CompuconnectDataException.create(AgendaReservaPostgresqlDAOMessage.DELETE_EXCEPTION_TECHNICAL_MESSAGE, AgendaReservaPostgresqlDAOMessage.DELETE_EXCEPTION_USER_MESSAGE, exception);
        }
    }

	@Override
	protected String prepareSelect() {
		return "SELECT identificador, agenda, reserva ";
	}

	@Override
	protected String prepareFrom() {
		return "FROM AgendaReserva ";
	}

	@Override
	protected String prepareWhere(final AgendaReservaEntity entity, List<Object> parameters) {

	    final var where = new StringBuilder("");
	    parameters = UtilObject.getDefault(parameters, new ArrayList<>());
	    var setWhere = true;

	    if (!UtilObject.isNull(entity)) {

	        if (!UtilUUID.isDefault(entity.getIdentificador())) {
	            parameters.add(entity.getIdentificador());
	            where.append("WHERE identificador = ? ");
	            setWhere = false;
	        }

	        if (!UtilUUID.isDefault(entity.getAgenda().getIdentificador())) {
	            parameters.add(entity.getAgenda());
	            where.append(setWhere ? "WHERE" : "AND ").append("agenda = ? ");
	            setWhere = false;
	        }

	        if (!UtilUUID.isDefault(entity.getReserva().getIdentificador())) {
	            parameters.add(entity.getReserva());
	            where.append(setWhere ? "WHERE" : "AND ").append("reserva = ? ");
	        }
	    }
	    return where.toString();
	}


	@Override
	protected String prepareOrderBy() {
		return "ORDER BY reserva ASC";
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
			throw CompuconnectDataException.create(AgendaReservaPostgresqlDAOMessage.SET_PARAMETERS_SQL_EXCEPTION_TECHNICAL_MESSAGE, AgendaReservaPostgresqlDAOMessage.SET_PARAMETERS_SQL_EXCEPTION_USER_MESSAGE, exception);
		}catch (Exception exception) {
			throw CompuconnectDataException.create(AgendaReservaPostgresqlDAOMessage.SET_PARAMETERS_EXCEPTION_TECHNICAL_MESSAGE, AgendaReservaPostgresqlDAOMessage.SET_PARAMETERS_EXCEPTION_USER_MESSAGE, exception);
		}
		
	}

	@Override
	protected List<AgendaReservaEntity> executeQuery(PreparedStatement preparedStatement) {
		List<AgendaReservaEntity> listResultSet = new ArrayList<>();		
		
		try(var resultSet = preparedStatement.executeQuery()){
			
			while(resultSet.next()) {
				var entityTmp = new AgendaReservaEntity(resultSet.getObject("identificador",UUID.class),
						resultSet.getObject("agenda",AgendaEntity.class), resultSet.getObject("reserva",ReservaEntity.class));
				listResultSet.add(entityTmp);
			}
			
		}catch (SQLException exception) {
			throw CompuconnectDataException.create(AgendaReservaPostgresqlDAOMessage.EXCECUTE_QUERY_SQL_EXCEPTION_TECHNICAL_MESSAGE, AgendaReservaPostgresqlDAOMessage.EXCECUTE_QUERY_SQL_EXCEPTION_USER_MESSAGE, exception);
		}catch (Exception exception) {
			throw CompuconnectDataException.create(AgendaReservaPostgresqlDAOMessage.EXCECUTE_QUERY_EXCEPTION_TECHNICAL_MESSAGE, AgendaReservaPostgresqlDAOMessage.EXCECUTE_QUERY_EXCEPTION_USER_MESSAGE, exception);
	}
	
		return listResultSet;
	}
}