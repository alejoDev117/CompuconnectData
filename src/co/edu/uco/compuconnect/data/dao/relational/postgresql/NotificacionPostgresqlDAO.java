package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.compuconnect.crosscutting.exceptions.CompuconnectDataException;
import co.edu.uco.compuconnect.crosscutting.utils.UtilDateTime;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;
import co.edu.uco.compuconnect.crosscutting.utils.UtilText;
import co.edu.uco.compuconnect.crosscutting.utils.UtilUUID;
import co.edu.uco.compuconnect.crosscutting.utils.Messages.NotificacionPostgresqlDAOMessage;
import co.edu.uco.compuconnect.data.dao.NotificacionDAO;
import co.edu.uco.compuconnect.data.dao.relational.SqlDAO;
import co.edu.uco.compuconnect.entities.NotificacionEntity;

public final class NotificacionPostgresqlDAO extends SqlDAO<NotificacionEntity> implements NotificacionDAO {

   

    public NotificacionPostgresqlDAO(final Connection connection) {
        super(connection);
    }

    @Override
    public void create(NotificacionEntity entity) {
        String sql = "INSERT INTO notificacion (identificador, contenido, fecha, tipoNotificacion) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            // Set the values for the PreparedStatement
            statement.setObject(1, entity.getIdentificador());
            statement.setObject(2, entity.getContenido().getIdentificador());
            statement.setObject(3, entity.getFecha());
            statement.setObject(4, entity.getTipo().getIdentificador());

            statement.executeUpdate();
        } catch (SQLException exception) {
           throw CompuconnectDataException.create(NotificacionPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_TECHNICAL_MESSAGE, NotificacionPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_USER_MESSAGE, exception);
        } catch (Exception exception) {
        	throw CompuconnectDataException.create(NotificacionPostgresqlDAOMessage.CREATE_EXCEPTION_TECHNICAL_MESSAGE,NotificacionPostgresqlDAOMessage.CREATE_EXCEPTION_USER_MESSAGE , exception);
        }
    }

    public List<NotificacionEntity> read(NotificacionEntity entity) {
        List<NotificacionEntity> result = new ArrayList<>();
        String sql = "SELECT identificador, contenido, fecha, tipoNotificacion FROM notificaciones WHERE ...";
 
        return result;
    }

    @Override
    public void delete(NotificacionEntity entity) {
        String sql = "DELETE FROM notificacion WHERE identificador = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setObject(1, entity.getIdentificador());

            statement.executeUpdate();
        } catch (SQLException exception) {
        	throw CompuconnectDataException.create(NotificacionPostgresqlDAOMessage.DELETE_SQL_EXCEPTION_TECHNICAL_MESSAGE,NotificacionPostgresqlDAOMessage.DELETE_SQL_EXCEPTION_USER_MESSAGE , exception);
        } catch (Exception exception) {
        	throw CompuconnectDataException.create(NotificacionPostgresqlDAOMessage.DELETE_EXCEPTION_TECHNICAL_MESSAGE,NotificacionPostgresqlDAOMessage.DELETE_EXCEPTION_USER_MESSAGE , exception);
        }
    }

	@Override
	protected String prepareSelect() {
		return "SELECT identificador, contenido, fecha, tipoNotificacion";
	}

	@Override
	protected String prepareFrom() {
		return "FROM notificacion ";
	}

	@Override
	protected String prepareWhere(NotificacionEntity entity, List<Object> parameters) {
		final var where = new StringBuilder("");
		parameters = UtilObject.getDefault(parameters, new ArrayList<>());
		var setWhere = true;
		
		if(!UtilObject.isNull(entity)) {
			if(!UtilUUID.isDefault(entity.getIdentificador())) {
				parameters.add(entity.getIdentificador());
				where.append("WHERE identificador=? ");
				setWhere = false;
			}
			if(!UtilUUID.isDefault(entity.getContenido().getIdentificador())) {
				parameters.add(entity.getContenido().getIdentificador());
				where.append(setWhere ? "WHERE" : "AND ").append("contenido =? ");
				setWhere = false;
			}
			if(!UtilDateTime.isDefaultDate(entity.getFecha())) {
				parameters.add(entity.getFecha());
				where.append(setWhere ? "WHERE" : "AND ").append("fecha =? ");
			}
			
		
		}
		return where.toString();
	}

	@Override
	protected String prepareOrderBy() {
		return "ORDER BY contenido ASC ";
	}
}