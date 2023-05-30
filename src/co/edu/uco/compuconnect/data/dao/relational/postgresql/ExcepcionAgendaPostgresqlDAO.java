package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.compuconnect.crosscutting.exceptions.CompuconnectDataException;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;
import co.edu.uco.compuconnect.crosscutting.utils.UtilUUID;
import co.edu.uco.compuconnect.crosscutting.utils.Messages.ExcepcionAgendaPostgresqlDAOMessage;
import co.edu.uco.compuconnect.data.dao.ExcepcionAgendaDAO;
import co.edu.uco.compuconnect.data.dao.relational.SqlDAO;
import co.edu.uco.compuconnect.entities.EstadoSolicitudEntity;
import co.edu.uco.compuconnect.entities.ExcepcionAgendaEntity;
import co.edu.uco.compuconnect.entities.ExcepcionEntity;

public final class ExcepcionAgendaPostgresqlDAO extends SqlDAO<ExcepcionAgendaEntity> implements ExcepcionAgendaDAO {

    

    public ExcepcionAgendaPostgresqlDAO(final Connection connection) {
        super(connection);
    }

    @Override
    public void create(ExcepcionAgendaEntity entity) {
        String query = "INSERT INTO excepcion_agenda (identificador, excepcion, agenda) VALUES (?, ?, ?)";

        try (PreparedStatement statement = getConnection().prepareStatement(query)) {
            statement.setObject(1, entity.getIdentificador());
            statement.setObject(2, entity.getExcepcion().getIdentificador());
            statement.setObject(3, entity.getAgenda().getIdentificador());
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw CompuconnectDataException.create(ExcepcionAgendaPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_TECHNICAL_MESSAGE, ExcepcionAgendaPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_USER_MESSAGE, exception);
        } catch (Exception exception) {
        	throw CompuconnectDataException.create(ExcepcionAgendaPostgresqlDAOMessage.CREATE_EXCEPTION_TECHNICAL_MESSAGE, ExcepcionAgendaPostgresqlDAOMessage.CREATE_EXCEPTION_USER_MESSAGE, exception);
        }
    }

    @Override
    public List<ExcepcionAgendaEntity> read(ExcepcionAgendaEntity entity) {
    	 List<ExcepcionAgendaEntity> estadoList = new ArrayList<>();
       	 return estadoList;    
       	 }

    public void delete(ExcepcionAgendaEntity entity) {
        String sql = "DELETE FROM excepcion_agenda WHERE identificador = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setObject(1, entity.getIdentificador());
            statement.executeUpdate();
        } catch (SQLException exception) {
        	throw CompuconnectDataException.create(ExcepcionAgendaPostgresqlDAOMessage.DELETE_SQL_EXCEPTION_TECHNICAL_MESSAGE, ExcepcionAgendaPostgresqlDAOMessage.DELETE_SQL_EXCEPTION_USER_MESSAGE, exception);
        } catch (Exception exception) {
        	throw CompuconnectDataException.create(ExcepcionAgendaPostgresqlDAOMessage.DELETE_EXCEPTION_TECHNICAL_MESSAGE, ExcepcionAgendaPostgresqlDAOMessage.DELETE_EXCEPTION_USER_MESSAGE, exception);
        }
    }

	@Override
	protected String prepareSelect() {
		return "SELECT identificador, excepcion, agenda ";
	}

	@Override
	protected String prepareFrom() {
		return "FROM excepcion_agenda ";
	}

	@Override
	protected String prepareWhere(ExcepcionAgendaEntity entity, List<Object> parameters) {
		final var where = new StringBuilder("");
		parameters = UtilObject.getDefault(parameters, new ArrayList<>());
		var setWhere = true;
		
		if(!UtilObject.isNull(entity)) {
			if(!UtilUUID.isDefault(entity.getIdentificador())) {
				parameters.add(entity.getIdentificador());
				where.append("WHERE identificador=? ");
				setWhere = false;
			}
			if(!UtilUUID.isDefault(entity.getExcepcion().getIdentificador())) {
				parameters.add(entity.getExcepcion().getIdentificador());
				where.append(setWhere ? "WHERE" : "AND ").append("excepcion =? ");
				setWhere = false;
			}
			if(!UtilUUID.isDefault(entity.getAgenda().getIdentificador())) {
				parameters.add(entity.getAgenda().getIdentificador());
				where.append(setWhere ? "WHERE" : "AND ").append("agenda =? ");
			}
			
		
		}
		return where.toString();
	}

	@Override
	protected String prepareOrderBy() {
		return "ORDER BY  ASC ";
	}

	@Override
	protected void setParameters(PreparedStatement prepareStat, List<Object> parameters) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected List<ExcepcionAgendaEntity> executeQuery(PreparedStatement preparedStatement) {
		// TODO Auto-generated method stub
		return null;
	}
}
