package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.compuconnect.crosscutting.exceptions.CompuconnectDataException;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;
import co.edu.uco.compuconnect.crosscutting.utils.UtilUUID;
import co.edu.uco.compuconnect.crosscutting.utils.Messages.HorarioPersonaEncargadaPostgresqlDAOMessage;
import co.edu.uco.compuconnect.data.dao.HorarioPersonaEncargadaDAO;
import co.edu.uco.compuconnect.data.dao.relational.SqlDAO;
import co.edu.uco.compuconnect.entities.HorarioPersonaEncargadaEntity;

public final class HorarioPersonaEncargadaPostgresqlDAO extends SqlDAO<HorarioPersonaEncargadaEntity> implements HorarioPersonaEncargadaDAO {


    public HorarioPersonaEncargadaPostgresqlDAO(final Connection connection) {
       super(connection);
    }

    @Override
    public void create(HorarioPersonaEncargadaEntity entity) {
        String sql = "INSERT INTO horario_persona_encargada (identificador, tiempoFuncionamiento, personaEncargada, horaInicio, horaFin) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setObject(1, entity.getIdentificador());
            statement.setObject(2, entity.getTiempoFuncionamiento().getIdentificador());
            statement.setObject(3, entity.getPersonaEncargada().getIdentificador());
            statement.setObject(4, entity.getHoraInicio());
            statement.setObject(5, entity.getHoraFin());

            statement.executeUpdate();
        } catch (SQLException exception) {
            throw CompuconnectDataException.create(HorarioPersonaEncargadaPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_TECHNICAL_MESSAGE, HorarioPersonaEncargadaPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_USER_MESSAGE,exception);
        } catch (Exception exception) {
        	throw CompuconnectDataException.create(HorarioPersonaEncargadaPostgresqlDAOMessage.CREATE_EXCEPTION_TECHNICAL_MESSAGE, HorarioPersonaEncargadaPostgresqlDAOMessage.CREATE_EXCEPTION_USER_MESSAGE, exception);
        }
    }

    @Override
    public List<HorarioPersonaEncargadaEntity> read(HorarioPersonaEncargadaEntity entity) {
        List<HorarioPersonaEncargadaEntity> result = new ArrayList<>();
        return result;
    }

    @Override
    public void update(HorarioPersonaEncargadaEntity entity) {
        String sql = "UPDATE horario_persona_encargada SET tiempoFuncionamiento = ?, persona_Encargada = ?, horaInicio = ?, horaFin = ? WHERE identificador = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setObject(1, entity.getTiempoFuncionamiento().getIdentificador());
            statement.setObject(2, entity.getPersonaEncargada().getIdentificador());
            statement.setObject(3, entity.getHoraInicio());
            statement.setObject(4, entity.getHoraFin());
            statement.setObject(5, entity.getIdentificador());

            statement.executeUpdate();
        } catch (SQLException exception) {
            throw CompuconnectDataException.create(HorarioPersonaEncargadaPostgresqlDAOMessage.UPDATE_SQL_EXCEPTION_TECHNICAL_MESSAGE, HorarioPersonaEncargadaPostgresqlDAOMessage.UPDATE_SQL_EXCEPTION_USER_MESSAGE,exception);
        } catch (Exception exception) {
        	throw CompuconnectDataException.create(HorarioPersonaEncargadaPostgresqlDAOMessage.UPDATE_EXCEPTION_TECHNICAL_MESSAGE, HorarioPersonaEncargadaPostgresqlDAOMessage.UPDATE_EXCEPTION_USER_MESSAGE, exception);
        	
        }
    }
    @Override
    public void delete(HorarioPersonaEncargadaEntity entity) {
        String sql = "DELETE FROM horario_persona_encargada WHERE identificador = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setObject(1, entity.getIdentificador());

            statement.executeUpdate();
        }  catch (SQLException exception) {
            throw CompuconnectDataException.create(HorarioPersonaEncargadaPostgresqlDAOMessage.DELETE_SQL_EXCEPTION_TECHNICAL_MESSAGE, HorarioPersonaEncargadaPostgresqlDAOMessage.DELETE_SQL_EXCEPTION_USER_MESSAGE,exception);
        } catch (Exception exception) {
        	throw CompuconnectDataException.create(HorarioPersonaEncargadaPostgresqlDAOMessage.DELETE_EXCEPTION_TECHNICAL_MESSAGE, HorarioPersonaEncargadaPostgresqlDAOMessage.DELETE_EXCEPTION_USER_MESSAGE, exception);
        }
    }

	@Override
	protected String prepareSelect() {
		return "SELECT identificador, tiempoFuncionamiento, personaEncargada, horaInicio, horaFin ";
	}

	@Override
	protected String prepareFrom() {
		return "FROM horario_persona_encargada ";
	}

	@Override
	protected String prepareWhere(HorarioPersonaEncargadaEntity entity, List<Object> parameters) {
		final var where = new StringBuilder("");
		parameters = UtilObject.getDefault(parameters, new ArrayList<>());
		var setWhere = true;
		
		if(!UtilObject.isNull(entity)) {
			if(!UtilUUID.isDefault(entity.getIdentificador())) {
				parameters.add(entity.getIdentificador());
				where.append("WHERE identificador=? ");
				setWhere = false;
			}
			if(!UtilUUID.isDefault(entity.getTiempoFuncionamiento().getIdentificador())) {
				parameters.add(entity.getTiempoFuncionamiento().getIdentificador());
				where.append(setWhere ? "WHERE" : "AND ").append("tiempoFuncionamiento =? ");
				setWhere = false;
			}
			if(!UtilUUID.isDefault(entity.getPersonaEncargada().getIdentificador())) {
				parameters.add(entity.getPersonaEncargada().getIdentificador());
				where.append(setWhere ? "WHERE" : "AND ").append("personaEncargada =? ");
			}
			
		
		}
		return where.toString();
	}

	@Override
	protected String prepareOrderBy() {
		return "ORDER BY personaEncargada ASC ";
	}

	@Override
	protected void setParameters(PreparedStatement prepareStat, List<Object> parameters) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected List<HorarioPersonaEncargadaEntity> executeQuery(PreparedStatement preparedStatement) {
		// TODO Auto-generated method stub
		return null;
	}
}