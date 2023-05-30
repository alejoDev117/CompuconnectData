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
import co.edu.uco.compuconnect.crosscutting.utils.Messages.PersonaEncargadaPostgresqlDAOMessage;
import co.edu.uco.compuconnect.data.dao.PersonaEncargadaDAO;
import co.edu.uco.compuconnect.data.dao.relational.SqlDAO;
import co.edu.uco.compuconnect.entities.PersonaEncargadaEntity;

public class PersonaEncargadaPostgresqlDAO extends SqlDAO<PersonaEncargadaEntity> implements PersonaEncargadaDAO {


    public PersonaEncargadaPostgresqlDAO(final Connection connection) {
       super(connection);
    }

    @Override
    public void create(PersonaEncargadaEntity entity) {
        String sql = "INSERT INTO persona_encargada (identificador, nombre, correoInstitucional, numeroCelular) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setObject(1, entity.getIdentificador());
            statement.setString(2, entity.getNombre());
            statement.setString(3, entity.getCorreoInstitucional());
            statement.setString(4, entity.getNumeroCelular());
            statement.executeUpdate();
        } catch (SQLException exception) {
          throw CompuconnectDataException.create(PersonaEncargadaPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_TECHNICAL_MESSAGE, PersonaEncargadaPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_USER_MESSAGE, exception);
        } catch (Exception exception) {
        	throw CompuconnectDataException.create(PersonaEncargadaPostgresqlDAOMessage.CREATE_EXCEPTION_TECHNICAL_MESSAGE, PersonaEncargadaPostgresqlDAOMessage.CREATE_EXCEPTION_USER_MESSAGE, exception);
        }
    }

    @Override
    public List<PersonaEncargadaEntity> read(PersonaEncargadaEntity entity) {
        List<PersonaEncargadaEntity> personasEncargadas = new ArrayList<>();
        String sql = "SELECT * FROM persona_encargada";
        return personasEncargadas;
    }

    @Override
    public void update(PersonaEncargadaEntity entity) {
        String sql = "UPDATE persona_encargada SET nombre = ?, correoInstitucional = ?, numeroCelular = ? WHERE identificador = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, entity.getNombre());
            statement.setString(2, entity.getCorreoInstitucional());
            statement.setString(3, entity.getNumeroCelular());
            statement.setObject(4, entity.getIdentificador());
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw CompuconnectDataException.create(PersonaEncargadaPostgresqlDAOMessage.UPDATE_SQL_EXCEPTION_TECHNICAL_MESSAGE, PersonaEncargadaPostgresqlDAOMessage.UPDATE_SQL_EXCEPTION_USER_MESSAGE, exception);
          } catch (Exception exception) {
          	throw CompuconnectDataException.create(PersonaEncargadaPostgresqlDAOMessage.UPDATE_EXCEPTION_TECHNICAL_MESSAGE, PersonaEncargadaPostgresqlDAOMessage.UPDATE_EXCEPTION_USER_MESSAGE, exception);
          }
    }

    @Override
    public void delete(PersonaEncargadaEntity entity) {
        String sql = "DELETE FROM persona_encargada WHERE identificador = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setObject(1, entity.getIdentificador());
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw CompuconnectDataException.create(PersonaEncargadaPostgresqlDAOMessage.DELETE_SQL_EXCEPTION_TECHNICAL_MESSAGE, PersonaEncargadaPostgresqlDAOMessage.DELETE_SQL_EXCEPTION_USER_MESSAGE, exception);
          } catch (Exception exception) {
          	throw CompuconnectDataException.create(PersonaEncargadaPostgresqlDAOMessage.DELETE_EXCEPTION_TECHNICAL_MESSAGE, PersonaEncargadaPostgresqlDAOMessage.DELETE_EXCEPTION_USER_MESSAGE, exception);
          }
    }

	@Override
	protected String prepareSelect() {
		return "SELECT identificador, nombre, correoInstitucional ";
	}

	@Override
	protected String prepareFrom() {
		return "FROM persona_encargada ";
	}

	@Override
	protected String prepareWhere(PersonaEncargadaEntity entity, List<Object> parameters) {
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
				where.append(setWhere ? "WHERE" : "AND ").append("nombre =? ");
				setWhere = false;
			}
			if(!UtilText.getUtilText().isEmpty(entity.getNombre())) {
				parameters.add(entity.getCorreoInstitucional());
				where.append(setWhere ? "WHERE" : "AND ").append("correoInstitucional =? ");
			}
			
		
		}
		return where.toString();
	}
	

	@Override
	protected String prepareOrderBy() {
		return "ORDER BY nombre ASC ";
	}
}