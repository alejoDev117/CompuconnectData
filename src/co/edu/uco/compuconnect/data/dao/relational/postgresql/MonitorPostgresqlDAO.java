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
import co.edu.uco.compuconnect.crosscutting.utils.Messages.MonitorPostgresqlDAOMessage;
import co.edu.uco.compuconnect.data.dao.MonitorDAO;
import co.edu.uco.compuconnect.data.dao.relational.SqlDAO;
import co.edu.uco.compuconnect.entities.MonitorEntity;

public final class MonitorPostgresqlDAO extends SqlDAO<MonitorEntity> implements MonitorDAO {

   

    public MonitorPostgresqlDAO(final Connection connection) {
       super(connection);
    }

    @Override
    public void create(MonitorEntity entity) {
        String sql = "INSERT INTO monitor (identificador, nombre, tipoIdentificacion, numeroIdentificacion, correoInstitucional, numeroCelular) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setObject(1, entity.getIdentificador());
            statement.setString(2, entity.getNombre());
            statement.setObject(3, entity.getTipoIdentificacion().getIdentificador());
            statement.setString(4, entity.getIdentificacion());
            statement.setString(5, entity.getCorreoInstitucional());
            statement.setString(6, entity.getNumeroCelular());
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw CompuconnectDataException.create(MonitorPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_TECHNICAL_MESSAGE, MonitorPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_USER_MESSAGE, exception);
        }catch (Exception exception) {
        	throw CompuconnectDataException.create(MonitorPostgresqlDAOMessage.CREATE_EXCEPTION_TECHNICAL_MESSAGE, MonitorPostgresqlDAOMessage.CREATE_EXCEPTION_USER_MESSAGE, exception);
        }
    }

    @Override
    public List<MonitorEntity> read(MonitorEntity entity) {
        List<MonitorEntity> result = new ArrayList<>();
        String sql = "SELECT identificador, nombre, tipo_identificacion, numero_identificacion, correo_institucional, numero_celular FROM monitor WHERE ...";
        return result;
    }

    @Override
    public void update(MonitorEntity entity) {
        String sql = "UPDATE monitor SET nombre = ?, tipoIdentificacion = ?, numeroIdentificacion = ?, correoInstitucional = ?, numeroCelular = ? WHERE identificador = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, entity.getNombre());
            statement.setObject(2, entity.getTipoIdentificacion().getIdentificador());
            statement.setString(3, entity.getIdentificacion());
            statement.setString(4, entity.getCorreoInstitucional());
            statement.setString(5, entity.getNumeroCelular());
            statement.setObject(6, entity.getIdentificador());

            statement.executeUpdate();
        } catch (SQLException exception) {
        	throw CompuconnectDataException.create(MonitorPostgresqlDAOMessage.UPDATE_SQL_EXCEPTION_TECHNICAL_MESSAGE, MonitorPostgresqlDAOMessage.UPDATE_SQL_EXCEPTION_USER_MESSAGE, exception);
        }catch (Exception exception) {
        	throw CompuconnectDataException.create(MonitorPostgresqlDAOMessage.UPDATE_EXCEPTION_TECHNICAL_MESSAGE, MonitorPostgresqlDAOMessage.UPDATE_EXCEPTION_USER_MESSAGE, exception);
        }
    }
    
    @Override
    public void delete(MonitorEntity entity) {
        String sql = "DELETE FROM monitor WHERE identificador = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setObject(1, entity.getIdentificador());

            statement.executeUpdate();
        } catch (SQLException exception) {
        	throw CompuconnectDataException.create(MonitorPostgresqlDAOMessage.DELETE_SQL_EXCEPTION_TECHNICAL_MESSAGE, MonitorPostgresqlDAOMessage.DELETE_SQL_EXCEPTION_USER_MESSAGE, exception);
        } catch (Exception exception) {
        	throw CompuconnectDataException.create(MonitorPostgresqlDAOMessage.DELETE_EXCEPTION_TECHNICAL_MESSAGE, MonitorPostgresqlDAOMessage.DELETE_EXCEPTION_USER_MESSAGE, exception);
        }
    }

	@Override
	protected String prepareSelect() {
		return "SELECT identificador, nombre, identificacion, tipoIdentificacion, correoInstitucional, numeroCelular, tipoUsuario ";
	}

	@Override
	protected String prepareFrom() {
		return "FROM monitor ";
	}

	@Override
	protected String prepareWhere(MonitorEntity entity, List<Object> parameters) {
		
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

	@Override
	protected void setParameters(PreparedStatement prepareStat, List<Object> parameters) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected List<MonitorEntity> executeQuery(PreparedStatement preparedStatement) {
		// TODO Auto-generated method stub
		return null;
	}
}