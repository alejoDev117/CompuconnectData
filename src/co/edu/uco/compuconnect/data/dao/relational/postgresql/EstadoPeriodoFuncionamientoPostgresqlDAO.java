package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.compuconnect.crosscutting.exceptions.CompuconnectDataException;
import co.edu.uco.compuconnect.crosscutting.utils.Messages.AgendaPostgresqlDAOMessage;
import co.edu.uco.compuconnect.crosscutting.utils.Messages.EstadoPeriodoFuncionamientoPostgresqlDAOMessage;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;
import co.edu.uco.compuconnect.crosscutting.utils.UtilUUID;
import co.edu.uco.compuconnect.data.dao.EstadoPeriodoFunicionamientoDAO;
import co.edu.uco.compuconnect.data.dao.relational.SqlDAO;
import co.edu.uco.compuconnect.entities.EstadoNotificacionEntity;
import co.edu.uco.compuconnect.entities.EstadoPeriodoFuncionamientoEntity;

public final class EstadoPeriodoFuncionamientoPostgresqlDAO extends SqlDAO<EstadoPeriodoFuncionamientoEntity> implements EstadoPeriodoFunicionamientoDAO {


    public EstadoPeriodoFuncionamientoPostgresqlDAO(final Connection connection) {
        super(connection);
    }

    @Override
    public void create(EstadoPeriodoFuncionamientoEntity entity) {
        String sqlStatement = "INSERT INTO estados_periodo_funcionamiento (identificador, nombre, descripcion) VALUES (?, ?)";

        try (PreparedStatement statement = getConnection().prepareStatement(sqlStatement)) {
            statement.setObject(1, entity.getIdentificador());
            statement.setString(2, entity.getNombre());
            statement.setString(3, entity.getDescripcion());
            statement.executeUpdate();
        } catch (final SQLException exception) {
        	throw CompuconnectDataException.create(EstadoPeriodoFuncionamientoPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_TECHNICAL_MESSAGE, EstadoPeriodoFuncionamientoPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_USER_MESSAGE,exception);
        	
        }catch(final Exception exception) {
        	throw CompuconnectDataException.create(EstadoPeriodoFuncionamientoPostgresqlDAOMessage.CREATE_EXCEPTION_TECHNICAL_MESSAGE, EstadoPeriodoFuncionamientoPostgresqlDAOMessage.CREATE_EXCEPTION_USER_MESSAGE,exception);
        }
    }

    @Override
    public List<EstadoPeriodoFuncionamientoEntity> read(EstadoPeriodoFuncionamientoEntity entity) {
    	 List<EstadoPeriodoFuncionamientoEntity> estadoList = new ArrayList<>();
	        return estadoList;
    }

	@Override
	protected String prepareSelect() {
		return "SELECT identificador, nombre, descripcion";
	}

	@Override
	protected String prepareFrom() {
		return "FROM estados_periodo_funcionamiento ";
	}

	@Override
	protected String prepareWhere(final EstadoPeriodoFuncionamientoEntity entity, List<Object> parameters) {

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

	        if (entity.getDescripcion() != null) {
	            parameters.add(entity.getDescripcion());
	            where.append(setWhere ? "WHERE" : "AND ").append("descripcion = ? ");
	        }
	    }
	    return where.toString();
	}


	@Override
	protected String prepareOrderBy() {
		return "ORDER BY nombre ASC";
	}
}
