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
import co.edu.uco.compuconnect.crosscutting.utils.Messages.EquipoComputoPostgresqlDAOMessage;
import co.edu.uco.compuconnect.data.dao.EquipoComputoDAO;
import co.edu.uco.compuconnect.data.dao.relational.SqlDAO;
import co.edu.uco.compuconnect.entities.DiaSemanalEntity;
import co.edu.uco.compuconnect.entities.EquipoComputoEntity;

public final class EquipoComputoPostgresqlDAO extends SqlDAO<EquipoComputoEntity> implements EquipoComputoDAO {

   

    public EquipoComputoPostgresqlDAO(final Connection connection) {
        super(connection);
    }

    @Override
    public void create(EquipoComputoEntity entity) {
        String query = "INSERT INTO equipos_computo (identificador, nombre, estado) VALUES (?, ?, ?)";

        try (PreparedStatement statement = getConnection().prepareStatement(query)) {
            statement.setObject(1, entity.getIdentificador());
            statement.setString(2, entity.getNombre());
            statement.setObject(3, entity.getEstado().getIdentificador());
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw CompuconnectDataException.create(EquipoComputoPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_TECHNICAL_MESSAGE, EquipoComputoPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_USER_MESSAGE, exception);
        } catch (Exception exception) {
        	throw CompuconnectDataException.create(EquipoComputoPostgresqlDAOMessage.CREATE_EXCEPTION_TECHNICAL_MESSAGE, EquipoComputoPostgresqlDAOMessage.CREATE_EXCEPTION_USER_MESSAGE, exception);
        }
    }

    @Override
    public List<EquipoComputoEntity> read(EquipoComputoEntity entity) {
    	List<EquipoComputoEntity> equipos = new ArrayList<>();
        String query = "SELECT identificador, nombre, estado FROM EquipoComputo";
        return equipos;
    }

    @Override
    public void update(EquipoComputoEntity entity) {
        String query = "UPDATE equipos_computo SET nombre = ?, estado = ? WHERE identificador = ?";

        try (PreparedStatement statement = getConnection().prepareStatement(query)) {
            statement.setString(1, entity.getNombre());
            statement.setObject(2, entity.getEstado().getIdentificador());
            statement.setObject(3, entity.getIdentificador());
            statement.executeUpdate();
        } catch (SQLException exception) {
        	throw CompuconnectDataException.create(EquipoComputoPostgresqlDAOMessage.UPDATE_SQL_EXCEPTION_TECHNICAL_MESSAGE, EquipoComputoPostgresqlDAOMessage.UPDATE_SQL_EXCEPTION_USER_MESSAGE, exception);
        } catch (Exception exception) {
        	throw CompuconnectDataException.create(EquipoComputoPostgresqlDAOMessage.UPDATE_EXCEPTION_TECHNICAL_MESSAGE, EquipoComputoPostgresqlDAOMessage.UPDATE_EXCEPTION_USER_MESSAGE, exception);
        }
    }

    @Override
    public void delete(EquipoComputoEntity entity) {
        String query = "DELETE FROM equipos_computo WHERE identificador = ?";

        try (PreparedStatement statement = getConnection().prepareStatement(query)) {
            statement.setObject(1, entity.getIdentificador());
            statement.executeUpdate();
        } catch (SQLException exception) {
        	throw CompuconnectDataException.create(EquipoComputoPostgresqlDAOMessage.DELETE_SQL_EXCEPTION_TECHNICAL_MESSAGE, EquipoComputoPostgresqlDAOMessage.DELETE_SQL_EXCEPTION_USER_MESSAGE, exception);
        } catch(Exception exception) {
        	throw CompuconnectDataException.create(EquipoComputoPostgresqlDAOMessage.DELETE_EXCEPTION_TECHNICAL_MESSAGE, EquipoComputoPostgresqlDAOMessage.DELETE_EXCEPTION_USER_MESSAGE, exception);
        }
    }

	@Override
	protected String prepareSelect() {
		return "SELECT identificador, nombre, estado ";
	}

	@Override
	protected String prepareFrom() {
		return "FROM equipo_computo ";
	}

	@Override
	protected String prepareWhere(EquipoComputoEntity entity, List<Object> parameters) {
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
				where.append(setWhere ? "WHERE" : "AND ").append("nombre=? ");
				setWhere = false;
			}
			if(!UtilUUID.isDefault(entity.getEstado().getIdentificador())) {
				parameters.add(entity.getEstado().getIdentificador());
				where.append(setWhere ? "WHERE" : "AND ").append("estado =? ");
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
	protected List<EquipoComputoEntity> executeQuery(PreparedStatement preparedStatement) {
		// TODO Auto-generated method stub
		return null;
	}
}
