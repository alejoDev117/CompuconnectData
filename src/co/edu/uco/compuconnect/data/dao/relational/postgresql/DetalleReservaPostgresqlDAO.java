package co.edu.uco.compuconnect.data.dao.relational.postgresql;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import co.edu.uco.compuconnect.crosscutting.exceptions.CompuconnectDataException;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;
import co.edu.uco.compuconnect.crosscutting.utils.UtilUUID;
import co.edu.uco.compuconnect.crosscutting.utils.Messages.DetalleReservaPostgresqlDAOMessage;
import co.edu.uco.compuconnect.data.dao.DetalleReservaDAO;
import co.edu.uco.compuconnect.data.dao.relational.SqlDAO;
import co.edu.uco.compuconnect.entities.DetalleReservaEntity;
import co.edu.uco.compuconnect.entities.DiaSemanalEntity;
import co.edu.uco.compuconnect.entities.ReservaEntity;

public final class DetalleReservaPostgresqlDAO extends SqlDAO<DetalleReservaEntity> implements DetalleReservaDAO {


    public DetalleReservaPostgresqlDAO(final Connection connection) {
    	super(connection);
    }

    @Override
    public void create(DetalleReservaEntity entity) {
        String sqlStatement = "INSERT INTO detalle_reserva (identificador,  dia_semanal_id, hora_inicio, hora_fin) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = getConnection().prepareStatement(sqlStatement)) {
            statement.setObject(1, entity.getIdentificador());
            statement.setObject(2, entity.getDia().getIdentificador());
            statement.setObject(3, java.sql.Time.valueOf(entity.getHorainicio()));
            statement.setObject(4, java.sql.Time.valueOf(entity.getHorafin()));
            statement.executeUpdate();
        } catch (final SQLException exception) {
        	throw CompuconnectDataException.create(DetalleReservaPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_TECHNICAL_MESSAGE, DetalleReservaPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_USER_MESSAGE,exception);
        	
        }catch(final Exception exception) {
        	throw CompuconnectDataException.create(DetalleReservaPostgresqlDAOMessage.CREATE_EXCEPTION_TECHNICAL_MESSAGE, DetalleReservaPostgresqlDAOMessage.CREATE_EXCEPTION_USER_MESSAGE,exception);
        }
    }

    @Override
    public List<DetalleReservaEntity> read(DetalleReservaEntity entity) {
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
    		throw CompuconnectDataException.create(DetalleReservaPostgresqlDAOMessage.READ_SQL_EXCEPTION_TECHNICAL_MESSAGE, DetalleReservaPostgresqlDAOMessage.READ_SQL_EXCEPTION_USER_MESSAGE, exception);
    	}catch(Exception exception) {
    		throw CompuconnectDataException.create(DetalleReservaPostgresqlDAOMessage.READ_EXCEPTION_TECHNICAL_MESSAGE, DetalleReservaPostgresqlDAOMessage.READ_EXCEPTION_USER_MESSAGE, exception);
    	}
    }

    @Override
    public void update(DetalleReservaEntity entity) {
        String sqlStatement = "UPDATE detalle_reserva SET reserva_id = ?, dia_semanal_id = ?, hora_inicio = ?, hora_fin = ? WHERE identificador = ?";

        try (PreparedStatement statement = getConnection().prepareStatement(sqlStatement)) {
            statement.setObject(1, entity.getDia().getIdentificador());
            statement.setObject(2, entity.getHorainicio());
            statement.setObject(3, entity.getHorafin());
            statement.setObject(4, entity.getIdentificador());
            statement.executeUpdate();
        } catch (SQLException exception) {
        	throw CompuconnectDataException.create(DetalleReservaPostgresqlDAOMessage.UPDATE_SQL_EXCEPTION_TECHNICAL_MESSAGE, DetalleReservaPostgresqlDAOMessage.UPDATE_SQL_EXCEPTION_USER_MESSAGE, exception);
        }catch(Exception exception) {
        	throw CompuconnectDataException.create(DetalleReservaPostgresqlDAOMessage.UPDATE_EXCEPTION_TECHNICAL_MESSAGE, DetalleReservaPostgresqlDAOMessage.UPDATE_EXCEPTION_USER_MESSAGE, exception);
        }
    }

    @Override
    public void delete(DetalleReservaEntity entity) {
        String sqlStatement = "DELETE FROM detalle_reserva WHERE identificador = ?";

        try (PreparedStatement statement = getConnection().prepareStatement(sqlStatement)) {
            statement.setObject(1, entity.getIdentificador());
            statement.executeUpdate();
        } catch (SQLException exception) {
        	throw CompuconnectDataException.create(DetalleReservaPostgresqlDAOMessage.DELETE_SQL_EXCEPTION_TECHNICAL_MESSAGE, DetalleReservaPostgresqlDAOMessage.DELETE_SQL_EXCEPTION_USER_MESSAGE, exception);
        }catch(Exception exception) {
        	throw CompuconnectDataException.create(DetalleReservaPostgresqlDAOMessage.DELETE_EXCEPTION_TECHNICAL_MESSAGE, DetalleReservaPostgresqlDAOMessage.DELETE_EXCEPTION_USER_MESSAGE, exception);
        }
    }

	@Override
	protected String prepareSelect() {
		return "SELECT dr.identificador, dr.reserva_id, dr.dia_semanal_id , d.nombre , dr.hora_inicio, dr.hora_fin ";
	}

	@Override
	protected String prepareFrom() {
		return "FROM detalle_reserva dr JOIN reserva r ON r.identificador = dr.reserva_id JOIN dia_semanal d ON d.identificador = dr.dia_semanal_id ";
	}

	@Override
	protected String prepareWhere(final DetalleReservaEntity entity, List<Object> parameters) {

	    final var where = new StringBuilder("");
	    parameters = UtilObject.getDefault(parameters, new ArrayList<>());
	    var setWhere = true;

	    if (!UtilObject.isNull(entity)) {

	        if (!UtilUUID.isDefault(entity.getIdentificador())) {
	            parameters.add(entity.getIdentificador());
	            where.append("WHERE r.identificador = ? ");
	            setWhere = false;
	        }


	        if (!UtilObject.isNull(entity.getDia().getIdentificador())) {
	            parameters.add(entity.getDia().getIdentificador());
	            where.append(setWhere ? "WHERE" : "AND ").append("d.identificador = ? ");
	            setWhere = false;
	        }

	        if (!UtilObject.isNull(entity.getHorainicio())) {
	            parameters.add(entity.getHorainicio());
	            where.append(setWhere ? "WHERE" : "AND ").append("dr.hora_inicio = ? ");
	            setWhere = false;
	        }

	        if (!UtilObject.isNull(entity.getHorafin())) {
	            parameters.add(entity.getHorafin());
	            where.append(setWhere ? "WHERE" : "AND ").append("dr.hora_fin = ? ");
	        }
	    }
	    return where.toString();
	}


	@Override
	protected String prepareOrderBy() {
		return "ORDER BY dr.identificador ASC ";
	}

	@Override
	protected void setParameters(PreparedStatement preparedStatement, List<Object> parameters) {
	    try {
	        if (!UtilObject.isNull(parameters) && !UtilObject.isNull(preparedStatement)) {
	            for (int index = 0; index < parameters.size(); index++) {
	                Object param = parameters.get(index);
	                if (param instanceof UUID) {
	                    preparedStatement.setObject(index + 1, param, Types.OTHER);
	                } else if (param instanceof LocalDate) {
	                    LocalDate localDate = (LocalDate) param;
	                    preparedStatement.setDate(index + 1, java.sql.Date.valueOf(localDate));
	                } else if (param instanceof LocalTime) {
	                    LocalTime localTime = (LocalTime) param;
	                    int secondsOfDay = localTime.toSecondOfDay();
	                    preparedStatement.setTime(index + 1, new java.sql.Time(secondsOfDay * 1000));
	                } else {
	                    preparedStatement.setObject(index + 1, param);
	                }
	            }
	        }
	    } catch (SQLException exception) {
	        throw CompuconnectDataException.create(DetalleReservaPostgresqlDAOMessage.SET_PARAMETERS_SQL_EXCEPTION_TECHNICAL_MESSAGE, DetalleReservaPostgresqlDAOMessage.SET_PARAMETERS_SQL_EXCEPTION_USER_MESSAGE, exception);
	    } catch (Exception exception) {
	        throw CompuconnectDataException.create(DetalleReservaPostgresqlDAOMessage.SET_PARAMETERS_EXCEPTION_TECHNICAL_MESSAGE, DetalleReservaPostgresqlDAOMessage.SET_PARAMETERS_EXCEPTION_USER_MESSAGE, exception);
	    }
	}


	@Override
	protected List<DetalleReservaEntity> executeQuery(PreparedStatement preparedStatement) {
	    List<DetalleReservaEntity> listResultSet = new ArrayList<>();

	    try (var resultSet = preparedStatement.executeQuery()) {

	        while (resultSet.next()) {
	            var entityTmp = new DetalleReservaEntity(
	                    resultSet.getObject("dr.identificador", UUID.class),
	                    DiaSemanalEntity.create().setIdentificador(resultSet.getObject("d.identificador", UUID.class)),
	                    resultSet.getTime("dr.hora_inicio").toLocalTime(),
	                    resultSet.getTime("dr.hora_fin").toLocalTime()
	            );
	            listResultSet.add(entityTmp);
	        }

	    } catch (SQLException exception) {
	        throw CompuconnectDataException.create(
	                DetalleReservaPostgresqlDAOMessage.EXCECUTE_QUERY_SQL_EXCEPTION_TECHNICAL_MESSAGE,
	                DetalleReservaPostgresqlDAOMessage.EXCECUTE_QUERY_SQL_EXCEPTION_USER_MESSAGE,
	                exception
	        );
	    } catch (Exception exception) {
	        throw CompuconnectDataException.create(
	                DetalleReservaPostgresqlDAOMessage.EXCECUTE_QUERY_EXCEPTION_TECHNICAL_MESSAGE,
	                DetalleReservaPostgresqlDAOMessage.EXCECUTE_QUERY_EXCEPTION_USER_MESSAGE,
	                exception
	        );
	    }

	    return listResultSet;
	}

}
