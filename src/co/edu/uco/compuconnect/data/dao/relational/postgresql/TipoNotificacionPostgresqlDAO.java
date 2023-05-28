package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.compuconnect.crosscutting.exceptions.CompuconnectDataException;
import co.edu.uco.compuconnect.crosscutting.utils.Messages.TipoNotificacionPostgresqlDAOMessage;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;
import co.edu.uco.compuconnect.crosscutting.utils.UtilText;
import co.edu.uco.compuconnect.crosscutting.utils.UtilUUID;
import co.edu.uco.compuconnect.data.dao.TipoNotificacionDAO;
import co.edu.uco.compuconnect.data.dao.relational.SqlDAO;
import co.edu.uco.compuconnect.entities.TipoNotificacionEntity;

public final class TipoNotificacionPostgresqlDAO extends SqlDAO<TipoNotificacionEntity> implements TipoNotificacionDAO {


    public TipoNotificacionPostgresqlDAO(final Connection connection) {
        super(connection);
    }

    @Override
    public void create(TipoNotificacionEntity entity) {
        String sqlStatement = "INSERT INTO tipo_notificacion (identificador, nombre, descripcion) VALUES (?, ?, ?)";
        try (PreparedStatement statement = getConnection().prepareStatement(sqlStatement)) {
            statement.setObject(1, entity.getIdentificador());
            statement.setString(2, entity.getNombre());
            statement.setString(3, entity.getDescripcion());

            statement.executeUpdate();
        } catch (final SQLException exception) {
        	throw CompuconnectDataException.create(TipoNotificacionPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_TECHNICAL_MESSAGE, TipoNotificacionPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_USER_MESSAGE,exception);
        	
        }catch(final Exception exception) {
        	throw CompuconnectDataException.create(TipoNotificacionPostgresqlDAOMessage.CREATE_EXCEPTION_TECHNICAL_MESSAGE, TipoNotificacionPostgresqlDAOMessage.CREATE_EXCEPTION_USER_MESSAGE,exception);
        }
    }

    @Override
    public List<TipoNotificacionEntity> read(TipoNotificacionEntity entity) {
        List<TipoNotificacionEntity> result = new ArrayList<>();
        String query = "SELECT * FROM tipo_notificacion";
        return result;
    }

	@Override
	protected String prepareSelect() {
		return "SELECT identificador, nombre, descripcion ";
	}

	@Override
	protected String prepareFrom() {
		return "FROM tipo_notificacion";
	}

	@Override
    protected String prepareWhere(final TipoNotificacionEntity entity, List<Object> parameters) {
        final var where = new StringBuilder("");
        parameters = UtilObject.getDefault(parameters, new ArrayList<>());

        var setWhere = true;

        if (!UtilObject.isNull(entity)) {

            if (!UtilUUID.isDefault(entity.getIdentificador())) {
                parameters.add(entity.getIdentificador());
                where.append("WHERE identificador=? ");
                setWhere = false;
            }

            if (!UtilText.getUtilText().isEmpty(entity.getNombre())) {
                parameters.add(entity.getNombre());
                where.append(setWhere ? "WHERE " : "AND ").append("nombre=? ");
                setWhere = false;
            }
            if (!UtilText.getUtilText().isEmpty(entity.getDescripcion())) {
                parameters.add(entity.getDescripcion());
                where.append("WHERE descripcion LIKE '%' || ? || '%' ");

            }

        }

        return where.toString();
    }


	@Override
    protected String prepareOrderBy() {
        return "ORDER BY nombre ASC";
    }

}