package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.compuconnect.crosscutting.exceptions.CompuconnectDataException;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;
import co.edu.uco.compuconnect.crosscutting.utils.UtilUUID;
import co.edu.uco.compuconnect.crosscutting.utils.Messages.PerfilPostgresqlDAOMessage;
import co.edu.uco.compuconnect.data.dao.PerfilDAO;
import co.edu.uco.compuconnect.data.dao.relational.SqlDAO;
import co.edu.uco.compuconnect.entities.PerfilEntity;

public final class PerfilPostgresqlDAO extends SqlDAO<PerfilEntity> implements PerfilDAO {



    public PerfilPostgresqlDAO(final Connection connection) {
        super(connection);
    }

    @Override
    public void create(PerfilEntity entity) {
        String sql = "INSERT INTO perfil (identificador, usuario) VALUES (?, ?)";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setObject(1, entity.getIdentificador());
            statement.setObject(2, entity.getUsuario().getIdentificador());

            statement.executeUpdate();
        } catch (SQLException exception) {
           throw CompuconnectDataException.create(PerfilPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_TECHNICAL_MESSAGE, PerfilPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_USER_MESSAGE, exception);
        } catch (Exception exception) {
        	throw CompuconnectDataException.create(PerfilPostgresqlDAOMessage.CREATE_EXCEPTION_TECHNICAL_MESSAGE, PerfilPostgresqlDAOMessage.CREATE_EXCEPTION_USER_MESSAGE, exception);
        }
    }

    public List<PerfilEntity> read(PerfilEntity entity) {
        List<PerfilEntity> result = new ArrayList<>();
        String sql = "SELECT identificador, usuario FROM perfiles WHERE ...";
       
        return result;
    }

    @Override
    public void delete(PerfilEntity entity) {
        String sql = "DELETE FROM perfiles WHERE identificador = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setObject(1, entity.getIdentificador());

            statement.executeUpdate();
        } catch (SQLException exception) {
            throw CompuconnectDataException.create(PerfilPostgresqlDAOMessage.DELETE_SQL_EXCEPTION_TECHNICAL_MESSAGE, PerfilPostgresqlDAOMessage.DELETE_SQL_EXCEPTION_USER_MESSAGE, exception);
         } catch (Exception exception) {
         	throw CompuconnectDataException.create(PerfilPostgresqlDAOMessage.DELETE_EXCEPTION_TECHNICAL_MESSAGE, PerfilPostgresqlDAOMessage.DELETE_EXCEPTION_USER_MESSAGE, exception);
         }
    }

    public void update(PerfilEntity entity) {
        String sql = "UPDATE perfil SET usuario = ? WHERE identificador = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setObject(1, entity.getUsuario().getIdentificador());
            statement.setObject(2, entity.getIdentificador());

            statement.executeUpdate();
        } catch (SQLException exception) {
            throw CompuconnectDataException.create(PerfilPostgresqlDAOMessage.UPDATE_SQL_EXCEPTION_TECHNICAL_MESSAGE, PerfilPostgresqlDAOMessage.UPDATE_SQL_EXCEPTION_USER_MESSAGE, exception);
         } catch (Exception exception) {
         	throw CompuconnectDataException.create(PerfilPostgresqlDAOMessage.UPDATE_EXCEPTION_TECHNICAL_MESSAGE, PerfilPostgresqlDAOMessage.UPDATE_EXCEPTION_USER_MESSAGE, exception);
         }
    }

	@Override
	protected String prepareSelect() {
		return "SELECT identificador, usuario ";
	}

	@Override
	protected String prepareFrom() {
		return "FROM perfil ";
	}

	@Override
	protected String prepareWhere(PerfilEntity entity, List<Object> parameters) {
		final var where = new StringBuilder("");
		parameters = UtilObject.getDefault(parameters, new ArrayList<>());
		
		
		if(!UtilObject.isNull(entity)) {
			if(!UtilUUID.isDefault(entity.getIdentificador())) {
				parameters.add(entity.getIdentificador());
				where.append("WHERE identificador=? ");
				
			}
		
		}
		return where.toString();
	}

	@Override
	protected String prepareOrderBy() {
		return "ORDER BY  ASC ";
	}
}
