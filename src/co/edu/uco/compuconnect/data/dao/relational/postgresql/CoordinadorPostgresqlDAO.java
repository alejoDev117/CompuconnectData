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
import co.edu.uco.compuconnect.crosscutting.utils.Messages.CoordinadorPostgresqlDAOMessage;
import co.edu.uco.compuconnect.data.dao.CoordinadorDAO;
import co.edu.uco.compuconnect.data.dao.relational.SqlDAO;
import co.edu.uco.compuconnect.entities.CoordinadorEntity;

public final class CoordinadorPostgresqlDAO extends SqlDAO<CoordinadorEntity> implements CoordinadorDAO {

 

    public CoordinadorPostgresqlDAO(final Connection connection) {
        super(connection);
    }

    @Override
    public void create(CoordinadorEntity entity) {
        String query = "INSERT INTO coordinador (identificador, nombre, tipoIdentificacion, numeroIdentificacion, correoInstitucional, numeroCelular, tipoUsuario) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = getConnection().prepareStatement(query)) {
            statement.setObject(1, entity.getIdentificador());
            statement.setString(2, entity.getNombre());
            statement.setObject(3, entity.getTipoIdentificacion().getIdentificador());
            statement.setString(4, entity.getIdentificacion());
            statement.setString(5, entity.getCorreoInstitucional());
            statement.setString(6, entity.getNumeroCelular());
            statement.setObject(7, entity.getTipoUsuario().getIdentificador());
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw CompuconnectDataException.create(CoordinadorPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_TECHNICAL_MESSAGE, CoordinadorPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_USER_MESSAGE, exception);
        }catch(Exception exception) {
        	throw CompuconnectDataException.create(CoordinadorPostgresqlDAOMessage.CREATE_EXCEPTION_TECHNICAL_MESSAGE, CoordinadorPostgresqlDAOMessage.CREATE_EXCEPTION_USER_MESSAGE, exception);
        }
    }

    @Override
    public List<CoordinadorEntity> read(CoordinadorEntity entity) {
        List<CoordinadorEntity> coordinadores = new ArrayList<>();
        String query = "SELECT identificador, nombre, tipo_identificacion, numero_identificacion, correo_institucional, numero_celular FROM coordinadores";
        return coordinadores;
    }

    @Override
    public void update(CoordinadorEntity entity) {
        String query = "UPDATE coordinador SET nombre = ?, tipoIdentificacion = ?, numeroIdentificacion = ?, correoInstitucional = ?, numeroCelular = ? WHERE identificador = ? ";

        try (PreparedStatement statement = getConnection().prepareStatement(query)) {
            statement.setString(1, entity.getNombre());
            statement.setObject(2, entity.getTipoIdentificacion().getIdentificador());
            statement.setString(3, entity.getIdentificacion());
            statement.setString(4, entity.getCorreoInstitucional());
            statement.setString(5, entity.getNumeroCelular());
            statement.setObject(5, entity.getIdentificador());
            statement.executeUpdate();
        } catch (SQLException exception) {
        	throw CompuconnectDataException.create(CoordinadorPostgresqlDAOMessage.UPDATE_SQL_EXCEPTION_TECHNICAL_MESSAGE, CoordinadorPostgresqlDAOMessage.UPDATE_SQL_EXCEPTION_USER_MESSAGE, exception);
        }catch (Exception exception) {
        	throw CompuconnectDataException.create(CoordinadorPostgresqlDAOMessage.UPDATE_EXCEPTION_TECHNICAL_MESSAGE, CoordinadorPostgresqlDAOMessage.UPDATE_EXCEPTION_USER_MESSAGE, exception);
        }
    }

    @Override
    public void delete(CoordinadorEntity entity) {
        String query = "DELETE FROM coordinador WHERE identificador = ?";

        try (PreparedStatement statement = getConnection().prepareStatement(query)) {
            statement.setObject(1, entity.getIdentificador());
            statement.executeUpdate();
        } catch (SQLException exception) {
        	throw CompuconnectDataException.create(CoordinadorPostgresqlDAOMessage.DELETE_SQL_EXCEPTION_TECHNICAL_MESSAGE, CoordinadorPostgresqlDAOMessage.DELETE_SQL_EXCEPTION_USER_MESSAGE, exception);
        }catch (Exception exception) {
        	throw CompuconnectDataException.create(CoordinadorPostgresqlDAOMessage.DELETE_EXCEPTION_TECHNICAL_MESSAGE, CoordinadorPostgresqlDAOMessage.DELETE_EXCEPTION_USER_MESSAGE, exception);
        }
    }

	@Override
	protected String prepareSelect() {
		return "SELECT identificador, nombre, tipoIdentificacion, identificacion, correoInstitucional, numeroCelular, tipoUsuario";
	}

	@Override
	protected String prepareFrom() {
		return "FROM coordinador ";
	}

	@Override
	protected String prepareWhere(CoordinadorEntity entity, List<Object> parameters) {
		
		final var where = new StringBuilder("");
		parameters = UtilObject.getDefault(parameters, new ArrayList<>());
		var setWhere = true;
		
		if(!UtilObject.isNull(entity)) {
			if(!UtilUUID.isDefault(entity.getIdentificador())) {
				parameters.add(entity.getIdentificador());
				where.append("WHERE identificador=? ");
				setWhere = false;
			}
			if(!UtilText.getUtilText().isEmpty(entity.getNombre())) {
				parameters.add(entity.getNombre());
				where.append(setWhere ? "WHERE" : "AND ").append("nombre LIKE %?% ");
				setWhere = false;
			}
			if(!UtilUUID.isDefault(entity.getTipoIdentificacion().getIdentificador())) {
				parameters.add(entity.getTipoIdentificacion().getIdentificador());
				where.append(setWhere ? "WHERE" : "AND ").append("tipoIdentificacion =? ");
			}
			if(!UtilUUID.isDefault(entity.getTipoUsuario().getIdentificador())) {
				parameters.add(entity.getTipoUsuario().getIdentificador());
				where.append(setWhere ? "WHERE" : "AND ").append("tipoUsuario =? ");
			}
			if(!UtilText.getUtilText().isEmpty(entity.getCorreoInstitucional())) {
				parameters.add(entity.getCorreoInstitucional());
				where.append(setWhere ? "WHERE" : "AND ").append("correoInstitucional LIKE %?% ");
			}
			
		
		}
		return where.toString();
	}

	@Override
	protected String prepareOrderBy() {
		return "ORDER BY nombre ASC ";
	}
}
