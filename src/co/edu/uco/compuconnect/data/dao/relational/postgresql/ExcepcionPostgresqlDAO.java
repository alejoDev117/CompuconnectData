package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.compuconnect.crosscutting.exceptions.CompuconnectDataException;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;
import co.edu.uco.compuconnect.crosscutting.utils.UtilUUID;
import co.edu.uco.compuconnect.crosscutting.utils.Messages.ExcepcionPostgresqlDAOMessage;
import co.edu.uco.compuconnect.crosscutting.utils.UtilDateTime;
import co.edu.uco.compuconnect.data.dao.ExcepcionAgendaDAO;
import co.edu.uco.compuconnect.data.dao.ExcepcionDAO;
import co.edu.uco.compuconnect.data.dao.relational.SqlDAO;
import co.edu.uco.compuconnect.entities.ExcepcionAgendaEntity;
import co.edu.uco.compuconnect.entities.ExcepcionEntity;

public final class ExcepcionPostgresqlDAO extends SqlDAO<ExcepcionEntity> implements ExcepcionDAO {

   

    public ExcepcionPostgresqlDAO(final Connection connection) {
      super(connection);
    }

    @Override
    public void create(ExcepcionEntity entity) {
        String sql = "INSERT INTO excepcion (identificador, fechaInicio, fechaFin, horaInicio, horaFin, frecuencia, motivo) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setObject(1, entity.getIdentificador());
            statement.setObject(2, entity.getFechaInicio());
            statement.setObject(3, entity.getFechaFin());
            statement.setObject(4, entity.getHoraInicio());
            statement.setObject(5, entity.getHoraFin());
            statement.setObject(6, entity.getFrecuencia().getIdentificador());
            statement.setString(7, entity.getMotivo());

            statement.executeUpdate();
        } catch (SQLException exception) {
        	throw CompuconnectDataException.create(ExcepcionPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_TECHNICAL_MESSAGE, ExcepcionPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_USER_MESSAGE, exception);
        } catch (Exception exception) {
        	throw CompuconnectDataException.create(ExcepcionPostgresqlDAOMessage.CREATE_EXCEPTION_TECHNICAL_MESSAGE, ExcepcionPostgresqlDAOMessage.CREATE_EXCEPTION_USER_MESSAGE, exception);
        }

    }

    @Override
    public List<ExcepcionEntity> read(ExcepcionEntity entity) {
        List<ExcepcionEntity> result = new ArrayList<>();
        String sql = "SELECT identificador, fecha_inicio, fecha_fin, hora_inicio, hora_fin, frecuencia, motivo FROM excepciones WHERE ...";
        return result;
    }

    @Override
    public void update(ExcepcionEntity entity) {
        String sql = "UPDATE excepcion SET fechaInicio = ?, fechaFin = ?, horaInicio = ?, horaFin = ?, frecuencia = ?, motivo = ? WHERE identificador = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setObject(1, entity.getFechaInicio());
            statement.setObject(2, entity.getFechaFin());
            statement.setObject(3, entity.getHoraInicio());
            statement.setObject(4, entity.getHoraFin());
            statement.setObject(5, entity.getFrecuencia().getIdentificador());
            statement.setString(6, entity.getMotivo());
            statement.setObject(7, entity.getIdentificador());

            statement.executeUpdate();
        } catch (SQLException exception) {
        	throw CompuconnectDataException.create(ExcepcionPostgresqlDAOMessage.UPDATE_SQL_EXCEPTION_TECHNICAL_MESSAGE, ExcepcionPostgresqlDAOMessage.UPDATE_SQL_EXCEPTION_USER_MESSAGE, exception);
        } catch (Exception exception) {
        	throw CompuconnectDataException.create(ExcepcionPostgresqlDAOMessage.UPDATE_EXCEPTION_TECHNICAL_MESSAGE, ExcepcionPostgresqlDAOMessage.UPDATE_EXCEPTION_USER_MESSAGE, exception);
        }
        
    	}
    
    @Override
    public void delete(ExcepcionEntity entity) {
        String sql = "DELETE FROM excepcion WHERE identificador = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setObject(1, entity.getIdentificador());
            statement.executeUpdate();
        } catch (SQLException exception) {
        	throw CompuconnectDataException.create(ExcepcionPostgresqlDAOMessage.DELETE_SQL_EXCEPTION_TECHNICAL_MESSAGE, ExcepcionPostgresqlDAOMessage.DELETE_SQL_EXCEPTION_USER_MESSAGE, exception);
        } catch (Exception exception) {
        	throw CompuconnectDataException.create(ExcepcionPostgresqlDAOMessage.DELETE_EXCEPTION_TECHNICAL_MESSAGE, ExcepcionPostgresqlDAOMessage.DELETE_EXCEPTION_USER_MESSAGE, exception);
        }
    }

	@Override
	protected String prepareSelect() {
		return "SELECT identificador, fechaInicio, fechaFin, horaInicio, horaFin, frecuencia, motivo "; 
	}

	@Override
	protected String prepareFrom() {
		return "FROM excepcion ";
	}

	@Override
	protected String prepareWhere(ExcepcionEntity entity, List<Object> parameters) {
		final var where = new StringBuilder("");
		parameters = UtilObject.getDefault(parameters, new ArrayList<>());
		var setWhere = true;
		
		if(!UtilObject.isNull(entity)) {
			if(!UtilUUID.isDefault(entity.getIdentificador())) {
				parameters.add(entity.getIdentificador());
				where.append("WHERE identificador=? ");
				setWhere = false;
			}
			if(!UtilDateTime.isDefaultDate(entity.getFechaInicio())) {
				parameters.add(entity.getFechaInicio());
				where.append(setWhere ? "WHERE" : "AND ").append("fechaInicio =? ");
				setWhere = false;
			}
			if(!UtilDateTime.isDefaultDate(entity.getFechaFin())) {
				parameters.add(entity.getFechaFin());
				where.append(setWhere ? "WHERE" : "AND ").append("fechaInicio =? ");
				setWhere = false;
			}
			if(!UtilDateTime.isDefaultLocalTime(entity.getHoraInicio())) {
				parameters.add(entity.getHoraInicio());
				where.append(setWhere ? "WHERE" : "AND ").append("horaInicio =? ");
				setWhere = false;
			}
			if(!UtilDateTime.isDefaultLocalTime(entity.getHoraFin())) {
				parameters.add(entity.getHoraFin());
				where.append(setWhere ? "WHERE" : "AND ").append("horaFin =? ");
				setWhere = false;
			}
			
		}
		return where.toString();
	}

	@Override
	protected String prepareOrderBy() {
		return "ORDER BY motivo ASC ";
	}

	@Override
	protected void setParameters(PreparedStatement prepareStat, List<Object> parameters) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected List<ExcepcionEntity> executeQuery(PreparedStatement preparedStatement) {
		// TODO Auto-generated method stub
		return null;
	}
}
