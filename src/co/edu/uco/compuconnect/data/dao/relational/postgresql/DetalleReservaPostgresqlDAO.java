package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.compuconnect.crosscutting.exceptions.CompuconnectDataException;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;
import co.edu.uco.compuconnect.crosscutting.utils.UtilUUID;
import co.edu.uco.compuconnect.crosscutting.utils.Messages.DetalleReservaPostgresqlDAOMessage;
import co.edu.uco.compuconnect.data.dao.DetalleReservaDAO;
import co.edu.uco.compuconnect.data.dao.relational.SqlDAO;
import co.edu.uco.compuconnect.entities.DetalleReservaEntity;

public final class DetalleReservaPostgresqlDAO extends SqlDAO<DetalleReservaEntity> implements DetalleReservaDAO {


    public DetalleReservaPostgresqlDAO(final Connection connection) {
    	super(connection);
    }

    @Override
    public void create(DetalleReservaEntity entity) {
        String sqlStatement = "INSERT INTO detalle_reservas (identificador, reserva_id, dia_semanal_id, hora_inicio, hora_fin) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = getConnection().prepareStatement(sqlStatement)) {
            statement.setObject(1, entity.getIdentificador());
            statement.setObject(2, entity.getReserva().getIdentificador());
            statement.setObject(3, entity.getDia().getIdentificador());
            statement.setObject(4, entity.getHorainicio());
            statement.setObject(5, entity.getHorafin());
            statement.executeUpdate();
        } catch (final SQLException exception) {
        	throw CompuconnectDataException.create(DetalleReservaPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_TECHNICAL_MESSAGE, DetalleReservaPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_USER_MESSAGE,exception);
        	
        }catch(final Exception exception) {
        	throw CompuconnectDataException.create(DetalleReservaPostgresqlDAOMessage.CREATE_EXCEPTION_TECHNICAL_MESSAGE, DetalleReservaPostgresqlDAOMessage.CREATE_EXCEPTION_USER_MESSAGE,exception);
        }
    }

    @Override
    public List<DetalleReservaEntity> read(DetalleReservaEntity entity) {
        List<DetalleReservaEntity> detalleReservas = new ArrayList<>();
        String sqlStatement = "SELECT identificador, reserva_id, dia_semanal_id, hora_inicio, hora_fin FROM detalle_reservas";
        return detalleReservas;
    }

    @Override
    public void update(DetalleReservaEntity entity) {
        String sqlStatement = "UPDATE detalle_reservas SET reserva_id = ?, dia_semanal_id = ?, hora_inicio = ?, hora_fin = ? WHERE identificador = ?";

        try (PreparedStatement statement = getConnection().prepareStatement(sqlStatement)) {
            statement.setObject(1, entity.getReserva().getIdentificador());
            statement.setObject(2, entity.getDia().getIdentificador());
            statement.setObject(3, entity.getHorainicio());
            statement.setObject(4, entity.getHorafin());
            statement.setObject(5, entity.getIdentificador());
            statement.executeUpdate();
        } catch (SQLException exception) {
        	throw CompuconnectDataException.create(DetalleReservaPostgresqlDAOMessage.UPDATE_SQL_EXCEPTION_TECHNICAL_MESSAGE, DetalleReservaPostgresqlDAOMessage.UPDATE_SQL_EXCEPTION_USER_MESSAGE, exception);
        }catch(Exception exception) {
        	throw CompuconnectDataException.create(DetalleReservaPostgresqlDAOMessage.UPDATE_EXCEPTION_TECHNICAL_MESSAGE, DetalleReservaPostgresqlDAOMessage.UPDATE_EXCEPTION_USER_MESSAGE, exception);
        }
    }

    @Override
    public void delete(DetalleReservaEntity entity) {
        String sqlStatement = "DELETE FROM detalle_reservas WHERE identificador = ?";

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
		return "SELECT identificador, reserva_id, dia_semanal_id, hora_inicio, hora_fin FROM detalle_reservas";
	}

	@Override
	protected String prepareFrom() {
		return "FROM detalle_reservas ";
	}

	@Override
	protected String prepareWhere(final DetalleReservaEntity entity, List<Object> parameters) {

	    final var where = new StringBuilder("");
	    parameters = UtilObject.getDefault(parameters, new ArrayList<>());
	    var setWhere = true;

	    if (!UtilObject.isNull(entity)) {

	        if (!UtilUUID.isDefault(entity.getIdentificador())) {
	            parameters.add(entity.getIdentificador());
	            where.append("WHERE identificador = ? ");
	            setWhere = false;
	        }

	        if (!UtilObject.isNull(entity.getReserva())) {
	            parameters.add(entity.getReserva());
	            where.append(setWhere ? "WHERE" : "AND ").append("reserva_id = ? ");
	            setWhere = false;
	        }

	        if (!UtilObject.isNull(entity.getDia())) {
	            parameters.add(entity.getDia());
	            where.append(setWhere ? "WHERE" : "AND ").append("dia_semanal_id = ? ");
	            setWhere = false;
	        }

	        if (!UtilObject.isNull(entity.getHorainicio())) {
	            parameters.add(entity.getHorainicio());
	            where.append(setWhere ? "WHERE" : "AND ").append("hora_inicio = ? ");
	            setWhere = false;
	        }

	        if (!UtilObject.isNull(entity.getHorafin())) {
	            parameters.add(entity.getHorafin());
	            where.append(setWhere ? "WHERE" : "AND ").append("hora_fin = ? ");
	        }
	    }
	    return where.toString();
	}


	@Override
	protected String prepareOrderBy() {
		return "ORDER BY hora_inicio ASC";
	}
}
