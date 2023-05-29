package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.compuconnect.crosscutting.exceptions.CompuconnectDataException;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;
import co.edu.uco.compuconnect.crosscutting.utils.UtilUUID;
import co.edu.uco.compuconnect.crosscutting.utils.Messages.DestinatarioNotificacionPostgresqlDAOMessage;
import co.edu.uco.compuconnect.data.dao.DestinatarioNotificacionDAO;
import co.edu.uco.compuconnect.data.dao.relational.SqlDAO;
import co.edu.uco.compuconnect.entities.DestinatarioNotificacionEntity;

public final class DestinatarioNotificacionPostgresqlDAO extends SqlDAO<DestinatarioNotificacionEntity> implements DestinatarioNotificacionDAO {

    public DestinatarioNotificacionPostgresqlDAO(final Connection connection) {
        super(connection);
    }

    @Override
    public void create(DestinatarioNotificacionEntity entity) {
        String query = "INSERT INTO destinatario_notificacion (identificador, destinatario, notificacion, estado) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = getConnection().prepareStatement(query)) {
        	statement.setObject(1, entity.getIdentificador());
            statement.setObject(2, entity.getDestinatario().getIdentificador());
            statement.setObject(3, entity.getNotificacion().getIdentificador());
            statement.setObject(4, entity.getEstado().getIdentificador());
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw CompuconnectDataException.create(DestinatarioNotificacionPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_TECHNICAL_MESSAGE, DestinatarioNotificacionPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_USER_MESSAGE, exception);
        }catch (Exception exception) {
            throw CompuconnectDataException.create(DestinatarioNotificacionPostgresqlDAOMessage.CREATE_EXCEPTION_TECHNICAL_MESSAGE, DestinatarioNotificacionPostgresqlDAOMessage.CREATE_EXCEPTION_USER_MESSAGE, exception);
        }
    }

    @Override
    public List<DestinatarioNotificacionEntity> read(DestinatarioNotificacionEntity entity) {
        List<DestinatarioNotificacionEntity> destinatariosNotificaciones = new ArrayList<>();
        String query = "SELECT id, destinatario_id, notificacion_id, estado_notificacion_id FROM destinatarios_notificaciones";

        return destinatariosNotificaciones;
    }

    @Override
    public void delete(DestinatarioNotificacionEntity entity) {
        String query = "DELETE FROM destinatario_notificacion WHERE identificador = ?";

        try (PreparedStatement statement = getConnection().prepareStatement(query)) {
            statement.setObject(1, entity.getIdentificador());
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw CompuconnectDataException.create(DestinatarioNotificacionPostgresqlDAOMessage.DELETE_SQL_EXCEPTION_TECHNICAL_MESSAGE, DestinatarioNotificacionPostgresqlDAOMessage.DELETE_SQL_EXCEPTION_USER_MESSAGE, exception);
        } catch (Exception exception) {
            throw CompuconnectDataException.create(DestinatarioNotificacionPostgresqlDAOMessage.DELETE_EXCEPTION_TECHNICAL_MESSAGE, DestinatarioNotificacionPostgresqlDAOMessage.DELETE_EXCEPTION_USER_MESSAGE, exception);
        }
    }

	@Override
	protected String prepareSelect() {
		return "SELECT identificador, destinatario, notificacion, estado ";
	}

	@Override
	protected String prepareFrom() {
		return "FROM destinatario_notificacion ";
	}

	@Override
	protected String prepareWhere(DestinatarioNotificacionEntity entity, List<Object> parameters) {
		
		
		final var where = new StringBuilder("");
		parameters = UtilObject.getDefault(parameters, new ArrayList<>());
		var setWhere = true;
		
		if(!UtilObject.isNull(entity)) {
			if(!UtilUUID.isDefault(entity.getIdentificador())) {
				parameters.add(entity.getIdentificador());
				where.append("WHERE identificador=? ");
				setWhere = false;
			}
			if(!UtilUUID.isDefault(entity.getDestinatario().getIdentificador())) {
				parameters.add(entity.getDestinatario().getIdentificador());
				where.append(setWhere ? "WHERE" : "AND ").append("destinatario =? ");
				setWhere = false;
			}
			if(!UtilUUID.isDefault(entity.getNotificacion().getIdentificador())) {
				parameters.add(entity.getNotificacion().getIdentificador());
				where.append(setWhere ? "WHERE" : "AND ").append("notificacion =? ");
			}
			
		
		}
		return where.toString();
	}

	@Override
	protected String prepareOrderBy() {
		return "ORDER BY ASC";
	}
}