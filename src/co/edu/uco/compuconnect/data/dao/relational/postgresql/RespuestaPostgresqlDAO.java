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
import co.edu.uco.compuconnect.crosscutting.utils.Messages.RespuestaPostgresqlDAOMessage;
import co.edu.uco.compuconnect.data.dao.RespuestaDAO;
import co.edu.uco.compuconnect.data.dao.relational.SqlDAO;
import co.edu.uco.compuconnect.entities.RespuestaEntity;

public final class RespuestaPostgresqlDAO extends SqlDAO<RespuestaEntity> implements RespuestaDAO {
   

    public RespuestaPostgresqlDAO(final Connection connection) {
        super(connection);
    }

    @Override
    public void create(RespuestaEntity entity) {
        String query = "INSERT INTO respuesta (identificador, autor, observacion, fecha) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = getConnection().prepareStatement(query)) {
            statement.setObject(1, entity.getIdentificador());
            statement.setObject(2, entity.getAutor().getIdentificador());
            statement.setString(3, entity.getObservacion());
            statement.setObject(4, entity.getFecha());

            statement.executeUpdate();
        } catch (SQLException exception) {
            throw CompuconnectDataException.create(RespuestaPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_TECHNICAL_MESSAGE, RespuestaPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_USER_MESSAGE, exception);
        } catch (Exception exception) {
        	throw CompuconnectDataException.create(RespuestaPostgresqlDAOMessage.CREATE_EXCEPTION_TECHNICAL_MESSAGE, RespuestaPostgresqlDAOMessage.CREATE_EXCEPTION_USER_MESSAGE, exception);
        }
    }

    @Override
    public List<RespuestaEntity> read(RespuestaEntity entity) {
        List<RespuestaEntity> respuestas = new ArrayList<>();

        return respuestas;
    }

    @Override
    public void delete(RespuestaEntity entity) {
        String query = "DELETE FROM respuesta WHERE identificador = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(query)) {
            statement.setObject(1, entity.getIdentificador());

            statement.executeUpdate();
        } catch (SQLException exception) {
            throw CompuconnectDataException.create(RespuestaPostgresqlDAOMessage.DELETE_SQL_EXCEPTION_TECHNICAL_MESSAGE, RespuestaPostgresqlDAOMessage.DELETE_SQL_EXCEPTION_USER_MESSAGE, exception);
        } catch (Exception exception) {
        	throw CompuconnectDataException.create(RespuestaPostgresqlDAOMessage.DELETE_EXCEPTION_TECHNICAL_MESSAGE, RespuestaPostgresqlDAOMessage.DELETE_EXCEPTION_USER_MESSAGE, exception);
        }
    }

	@Override
	protected String prepareSelect() {
		return "SELECT identificador, autor, observacion, fecha ";
	}

	@Override
	protected String prepareFrom() {
		return "FROM respuesta ";
	}

	@Override
	protected String prepareWhere(RespuestaEntity entity, List<Object> parameters) {
		final var where = new StringBuilder("");
		parameters = UtilObject.getDefault(parameters, new ArrayList<>());
		var setWhere = true;
		
		if(!UtilObject.isNull(entity)) {
			if(!UtilUUID.isDefault(entity.getIdentificador())) {
				parameters.add(entity.getIdentificador());
				where.append("WHERE identificador=? ");
				setWhere = false;
			}
			if(!UtilUUID.isDefault(entity.getAutor().getIdentificador())) {
				parameters.add(entity.getAutor().getIdentificador());
				where.append(setWhere ? "WHERE" : "AND ").append("autor =? ");
				setWhere = false;
			}
			if(!UtilText.getUtilText().isEmpty(entity.getObservacion())) {
				parameters.add(entity.getObservacion());
				where.append(setWhere ? "WHERE" : "AND ").append("observacion =? ");
			}
			
		
		}
		return where.toString();
	}
	

	@Override
	protected String prepareOrderBy() {
		return "ORDER BY observacion ASC ";
	}

	@Override
	protected void setParameters(PreparedStatement prepareStat, List<Object> parameters) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected List<RespuestaEntity> executeQuery(PreparedStatement preparedStatement) {
		// TODO Auto-generated method stub
		return null;
	}
}
