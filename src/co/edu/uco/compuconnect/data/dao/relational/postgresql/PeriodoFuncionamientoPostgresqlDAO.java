package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.compuconnect.crosscutting.exceptions.CompuconnectDataException;
import co.edu.uco.compuconnect.crosscutting.utils.Messages.PeriodoFuncionamientoPostgresqlDAOMessage;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;
import co.edu.uco.compuconnect.crosscutting.utils.UtilUUID;
import co.edu.uco.compuconnect.data.dao.PeriodoFuncionamientoDAO;
import co.edu.uco.compuconnect.data.dao.relational.SqlDAO;
import co.edu.uco.compuconnect.entities.PeriodoFuncionamientoEntity;

public final class PeriodoFuncionamientoPostgresqlDAO extends SqlDAO<PeriodoFuncionamientoEntity> implements PeriodoFuncionamientoDAO {


    public PeriodoFuncionamientoPostgresqlDAO(final Connection connection) {
    	super(connection);
    }

    public void create(PeriodoFuncionamientoEntity entity) {
        String sqlStatement = "INSERT INTO periodo_funcionamiento (identificador, nombre, fecha_inicio, fecha_fin, dia_festivo, estado) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = getConnection().prepareStatement(sqlStatement)) {
            statement.setObject(1, entity.getIdentificador());
            statement.setString(2, entity.getNombre());
            statement.setDate(3, new java.sql.Date(entity.getFechaInicio().getTime()));
            statement.setDate(4, new java.sql.Date(entity.getFechaFin().getTime()));
            statement.setObject(5, entity.getDiaFestivo());
            statement.setObject(6, entity.getEstado());

            statement.executeUpdate();
        } catch (final SQLException exception) {
        	throw CompuconnectDataException.create(PeriodoFuncionamientoPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_TECHNICAL_MESSAGE, PeriodoFuncionamientoPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_USER_MESSAGE,exception);
        	
        }catch(final Exception exception) {
        	throw CompuconnectDataException.create(PeriodoFuncionamientoPostgresqlDAOMessage.CREATE_EXCEPTION_TECHNICAL_MESSAGE, PeriodoFuncionamientoPostgresqlDAOMessage.CREATE_EXCEPTION_USER_MESSAGE,exception);
        }
    }

    @Override
    public List<PeriodoFuncionamientoEntity> read(PeriodoFuncionamientoEntity entity) {
        List<PeriodoFuncionamientoEntity> result = new ArrayList<>();
        String sqlStatement = "SELECT identificador, nombre, fecha_inicio, fecha_fin, dia_festivo, estado FROM periodo_funcionamiento WHERE ...";
        return result;
    }

    public void update(PeriodoFuncionamientoEntity entity) {
        String sqlStatement = "UPDATE periodo_funcionamiento SET nombre = ?, fecha_inicio = ?, fecha_fin = ?, dia_festivo = ?, estado = ? WHERE identificador = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sqlStatement)) {
            statement.setString(1, entity.getNombre());
            statement.setDate(2, new java.sql.Date(entity.getFechaInicio().getTime()));
            statement.setDate(3, new java.sql.Date(entity.getFechaFin().getTime()));
            statement.setObject(4, entity.getDiaFestivo());
            statement.setObject(5, entity.getEstado());
            statement.setObject(6, entity.getIdentificador());

            statement.executeUpdate();
        } catch (SQLException exception) {
        	throw CompuconnectDataException.create(PeriodoFuncionamientoPostgresqlDAOMessage.UPDATE_SQL_EXCEPTION_TECHNICAL_MESSAGE, PeriodoFuncionamientoPostgresqlDAOMessage.UPDATE_SQL_EXCEPTION_USER_MESSAGE, exception);
        }catch(Exception exception) {
        	throw CompuconnectDataException.create(PeriodoFuncionamientoPostgresqlDAOMessage.UPDATE_EXCEPTION_TECHNICAL_MESSAGE, PeriodoFuncionamientoPostgresqlDAOMessage.UPDATE_EXCEPTION_USER_MESSAGE, exception);
        }
    }
    public void delete(PeriodoFuncionamientoEntity entity) {
        String sqlStatement = "DELETE FROM periodo_funcionamiento WHERE identificador = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sqlStatement)) {
            statement.setObject(1, entity.getIdentificador());

            statement.executeUpdate();
        } catch (SQLException exception) {
        	throw CompuconnectDataException.create(PeriodoFuncionamientoPostgresqlDAOMessage.DELETE_SQL_EXCEPTION_TECHNICAL_MESSAGE, PeriodoFuncionamientoPostgresqlDAOMessage.DELETE_SQL_EXCEPTION_USER_MESSAGE, exception);
        }catch(Exception exception) {
        	throw CompuconnectDataException.create(PeriodoFuncionamientoPostgresqlDAOMessage.DELETE_EXCEPTION_TECHNICAL_MESSAGE, PeriodoFuncionamientoPostgresqlDAOMessage.DELETE_EXCEPTION_USER_MESSAGE, exception);
        }
    }

	@Override
	protected String prepareSelect() {
		return "SELECT identificador, nombre, fecha_inicio, fecha_fin, dia_festivo, estado ";
	}

	@Override
	protected String prepareFrom() {
		return "FROM periodo_funcionamiento ";
	}

	@Override
	protected String prepareWhere(final PeriodoFuncionamientoEntity entity, List<Object> parameters) {

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
	            setWhere = false;
	        }

	        if (entity.getFechaInicio() != null) {
	            parameters.add(entity.getFechaInicio());
	            where.append(setWhere ? "WHERE" : "AND ").append("fecha_inicio = ? ");
	            setWhere = false;
	        }

	        if (entity.getFechaFin() != null) {
	            parameters.add(entity.getFechaFin());
	            where.append(setWhere ? "WHERE" : "AND ").append("fecha_fin = ? ");
	            setWhere = false;
	        }

	        if (entity.getDiaFestivo() != null) {
	            parameters.add(entity.getDiaFestivo());
	            where.append(setWhere ? "WHERE" : "AND ").append("dia_festivo = ? ");
	            setWhere = false;
	        }

	        if (entity.getEstado() != null) {
	            parameters.add(entity.getEstado());
	            where.append(setWhere ? "WHERE" : "AND ").append("estado = ? ");
	        }
	    }
	    return where.toString();
	}


	@Override
	protected String prepareOrderBy() {
		return "ORDER BY nombre ASC";
	}
}
