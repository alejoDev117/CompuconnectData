package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.compuconnect.crosscutting.exceptions.CompuconnectDataException;
import co.edu.uco.compuconnect.crosscutting.utils.Messages.DiaSemanalPostgresqlDAOMessage;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;
import co.edu.uco.compuconnect.crosscutting.utils.UtilUUID;
import co.edu.uco.compuconnect.data.dao.DiaSemanalDAO;
import co.edu.uco.compuconnect.data.dao.relational.SqlDAO;
import co.edu.uco.compuconnect.entities.DiaSemanalEntity;

public final class DiaSemanalPostgresqlDAO extends SqlDAO<DiaSemanalEntity> implements DiaSemanalDAO {

    public DiaSemanalPostgresqlDAO(final Connection connection) {
       super(connection);
    }

    @Override
    public void create(DiaSemanalEntity entity) {
        String sqlStatement = "INSERT INTO dia_semanal (identificador, nombre) VALUES (?, ?)";

        try (PreparedStatement statement = getConnection().prepareStatement(sqlStatement)) {
            statement.setObject(1, entity.getIdentificador());
            statement.setString(2, entity.getNombre());
            statement.executeUpdate();
        } catch (final SQLException exception) {
        	throw CompuconnectDataException.create(DiaSemanalPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_TECHNICAL_MESSAGE, DiaSemanalPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_USER_MESSAGE,exception);
        	
        }catch(final Exception exception) {
        	throw CompuconnectDataException.create(DiaSemanalPostgresqlDAOMessage.CREATE_EXCEPTION_TECHNICAL_MESSAGE, DiaSemanalPostgresqlDAOMessage.CREATE_EXCEPTION_USER_MESSAGE,exception);
        }
    }

    @Override
    public List<DiaSemanalEntity> read(DiaSemanalEntity entity) {
        List<DiaSemanalEntity> diasSemanales = new ArrayList<>();
        String sqlStatement = "SELECT identificador, nombre FROM dia_semanal";
        return diasSemanales;
    }

	@Override
	protected String prepareSelect() {
		return "SELECT identificador, nombre FROM dia_semanal";
	}

	@Override
	protected String prepareFrom() {
		return "FROM dia_semanal";
	}

	@Override
	protected String prepareWhere(final DiaSemanalEntity entity, List<Object> parameters) {

	    final var where = new StringBuilder("");
	    parameters = UtilObject.getDefault(parameters, new ArrayList<>());
	    var setWhere = true;

	    if (!UtilObject.isNull(entity)) {

	        if (!UtilUUID.isDefault(entity.getIdentificador())) {
	            parameters.add(entity.getIdentificador());
	            where.append("WHERE identificador = ? ");
	            setWhere = false;
	        }

	        if (entity.getNombre() != null) {
	            parameters.add(entity.getNombre());
	            where.append(setWhere ? "WHERE" : "AND ").append("nombre = ? ");
	        }
	    }
	    return where.toString();
	}


	@Override
	protected String prepareOrderBy() {
		return "ORDER BY nombre ASC";
	}
}
