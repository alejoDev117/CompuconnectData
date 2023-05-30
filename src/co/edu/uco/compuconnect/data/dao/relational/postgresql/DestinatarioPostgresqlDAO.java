package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.compuconnect.crosscutting.exceptions.CompuconnectDataException;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;
import co.edu.uco.compuconnect.crosscutting.utils.UtilText;
import co.edu.uco.compuconnect.crosscutting.utils.UtilUUID;
import co.edu.uco.compuconnect.crosscutting.utils.Messages.DestinatarioPostgresqlDAOMessage;
import co.edu.uco.compuconnect.data.dao.DestinatarioDAO;
import co.edu.uco.compuconnect.data.dao.relational.SqlDAO;
import co.edu.uco.compuconnect.entities.DestinatarioEntity;

public class DestinatarioPostgresqlDAO extends SqlDAO<DestinatarioEntity> implements DestinatarioDAO {


    public DestinatarioPostgresqlDAO(final Connection connection) {
       super(connection);
    }

    @Override
    public void generate(DestinatarioEntity entity) {
        String query = "INSERT INTO destinatario (identificador, nombre, correo) VALUES (?, ?, ?)";

        try (PreparedStatement statement = getConnection().prepareStatement(query)) {
            statement.setObject(1, entity.getIdentificador());
            statement.setString(2, entity.getNombre());
            statement.setString(3, entity.getCorreoInstitucional());
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw CompuconnectDataException.create(DestinatarioPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_TECHNICAL_MESSAGE, DestinatarioPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_USER_MESSAGE, exception);
        }catch (Exception exception) {
        	throw CompuconnectDataException.create(DestinatarioPostgresqlDAOMessage.CREATE_EXCEPTION_TECHNICAL_MESSAGE, DestinatarioPostgresqlDAOMessage.CREATE_EXCEPTION_USER_MESSAGE, exception);
        }
    }

    @Override
    public List<DestinatarioEntity> read(DestinatarioEntity entity) {
        List<DestinatarioEntity> destinatarios = new ArrayList<>();
        String query = "SELECT identificador, nombre, correo FROM destinatarios";

        return destinatarios;
    }

    @Override
    public void update(DestinatarioEntity entity) {
        String query = "UPDATE destinatario SET nombre = ?, correo = ? WHERE identificador = ?";

        try (PreparedStatement statement = getConnection().prepareStatement(query)) {
            statement.setString(1, entity.getNombre());
            statement.setString(2, entity.getCorreoInstitucional());
            statement.setObject(3, entity.getIdentificador());
            statement.executeUpdate();
        } catch (SQLException exception) {
        	throw CompuconnectDataException.create(DestinatarioPostgresqlDAOMessage.UPDATE_SQL_EXCEPTION_TECHNICAL_MESSAGE, DestinatarioPostgresqlDAOMessage.UPDATE_SQL_EXCEPTION_USER_MESSAGE, exception);
        } catch (Exception exception) {
        	throw CompuconnectDataException.create(DestinatarioPostgresqlDAOMessage.UPDATE_EXCEPTION_TECHNICAL_MESSAGE, DestinatarioPostgresqlDAOMessage.UPDATE_EXCEPTION_USER_MESSAGE, exception);
        }
    }

    @Override
    public void delete(DestinatarioEntity entity) {
        String query = "DELETE FROM destinatario WHERE identificador = ?";

        try (PreparedStatement statement = getConnection().prepareStatement(query)) {
            statement.setObject(1, entity.getIdentificador());
            statement.executeUpdate();
        } catch (SQLException exception) {
        	throw CompuconnectDataException.create(DestinatarioPostgresqlDAOMessage.DELETE_SQL_EXCEPTION_TECHNICAL_MESSAGE, DestinatarioPostgresqlDAOMessage.DELETE_SQL_EXCEPTION_USER_MESSAGE, exception);
        } catch (Exception exception) {
        	throw CompuconnectDataException.create(DestinatarioPostgresqlDAOMessage.DELETE_EXCEPTION_TECHNICAL_MESSAGE, DestinatarioPostgresqlDAOMessage.DELETE_EXCEPTION_USER_MESSAGE, exception);
        }
    }

	@Override
	protected String prepareSelect() {
		return "SELECT identificador, correoInstitucional, nombre ";
	}

	@Override
	protected String prepareFrom() {
		return "FROM destinatario ";
	}

	@Override
	protected String prepareWhere(DestinatarioEntity entity, List<Object> parameters) {
		final var where = new StringBuilder("");
		parameters = UtilObject.getDefault(parameters, new ArrayList<>());
		var setWhere = true;
		
		if(!UtilObject.isNull(entity)) {
			if(!UtilUUID.isDefault(entity.getIdentificador())) {
				parameters.add(entity.getIdentificador());
				where.append("WHERE identificador=? ");
				setWhere = false;
			}
			if(!UtilText.getUtilText().isEmpty(entity.getCorreoInstitucional())) {
				parameters.add(entity.getCorreoInstitucional());
				where.append(setWhere ? "WHERE" : "AND ").append("correoInstitucional =? ");
				setWhere = false;
			}
			if(!UtilText.getUtilText().isEmpty(entity.getNombre())) {
				parameters.add(entity.getNombre());
				where.append(setWhere ? "WHERE" : "AND ").append("nombre =? ");
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
		// TODO Auto-generated method stub
		
	}

	@Override
	protected List<DestinatarioEntity> executeQuery(PreparedStatement preparedStatement) {
		// TODO Auto-generated method stub
		return null;
	}
}