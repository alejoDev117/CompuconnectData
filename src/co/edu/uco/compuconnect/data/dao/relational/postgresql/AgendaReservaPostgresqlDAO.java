package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.compuconnect.crosscutting.exceptions.CompuconnectDataException;
import co.edu.uco.compuconnect.crosscutting.utils.Messages.AgendaReservaPostgresqlDAOMessage;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;
import co.edu.uco.compuconnect.data.dao.AgendaReservaDAO;
import co.edu.uco.compuconnect.data.dao.relational.SqlDAO;
import co.edu.uco.compuconnect.entities.AgendaReservaEntity;

public final class AgendaReservaPostgresqlDAO extends SqlDAO<AgendaReservaEntity> implements AgendaReservaDAO {

    public AgendaReservaPostgresqlDAO(final Connection connection) {
        super(connection);
    }

    @Override
    public final void create(final AgendaReservaEntity entity) {
        String sqlStatement = "INSERT INTO AgendaReserva (identificador, agenda, reserva) VALUES (?, ?, ?)";

        try (PreparedStatement statement = getConnection().prepareStatement(sqlStatement)) {
            statement.setObject(1, entity.getIdentificador());
            statement.setObject(2, entity.getAgenda());
            statement.setObject(3, entity.getReserva());

            statement.executeUpdate();
        } catch (SQLException exception) {
        	throw CompuconnectDataException.create(AgendaReservaPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_TECHNICAL_MESSAGE, AgendaReservaPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_USER_MESSAGE,exception);
        }catch(final Exception exception) {
        	throw CompuconnectDataException.create(AgendaReservaPostgresqlDAOMessage.CREATE_EXCEPTION_TECHNICAL_MESSAGE, AgendaReservaPostgresqlDAOMessage.CREATE_EXCEPTION_USER_MESSAGE,exception);
        }
    }

    @Override
    public final List<AgendaReservaEntity> read(final AgendaReservaEntity entity) {
        List<AgendaReservaEntity> agendaList = new ArrayList<>();
        String sqlStatement = "SELECT identificador, agenda, reserva FROM AgendaReserva WHERE identificador = ?";

        return agendaList;
    }

    @Override
    public final void delete(final AgendaReservaEntity entity) {
        String sqlStatement = "DELETE FROM AgendaReserva WHERE identificador = ?";

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

	        if (entity.getIdentificador() != null) {
	            parameters.add(entity.getIdentificador());
	            where.append("WHERE identificador = ? ");
	            setWhere = false;
	        }

	        if (entity.getAgenda() != null) {
	            parameters.add(entity.getAgenda());
	            where.append(setWhere ? "WHERE" : "AND ").append("agenda = ? ");
	            setWhere = false;
	        }

	        if (entity.getReserva() != null) {
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
}