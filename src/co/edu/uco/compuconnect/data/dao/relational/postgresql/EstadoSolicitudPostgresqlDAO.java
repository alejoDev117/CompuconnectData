package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.compuconnect.crosscutting.exceptions.CompuconnectDataException;
import co.edu.uco.compuconnect.crosscutting.utils.Messages.EstadoSolicitudPostgresqlDAOMessage;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;
import co.edu.uco.compuconnect.crosscutting.utils.UtilText;
import co.edu.uco.compuconnect.crosscutting.utils.UtilUUID;
import co.edu.uco.compuconnect.data.dao.EstadoSolicitudDAO;
import co.edu.uco.compuconnect.data.dao.relational.SqlDAO;
import co.edu.uco.compuconnect.entities.EstadoEquipoComputoEntity;
import co.edu.uco.compuconnect.entities.EstadoPeriodoFuncionamientoEntity;
import co.edu.uco.compuconnect.entities.EstadoSolicitudEntity;

public final class EstadoSolicitudPostgresqlDAO extends SqlDAO<EstadoSolicitudEntity> implements EstadoSolicitudDAO {

  

    public EstadoSolicitudPostgresqlDAO(final Connection connection) {
        super(connection);
    }

    @Override
    public void create(EstadoSolicitudEntity entity) {
        String query = "INSERT INTO estado_solicitud (identificador, nombre) VALUES (?, ?)";

        try (PreparedStatement statement = getConnection().prepareStatement(query)) {
            statement.setObject(1, entity.getIdentificador());
            statement.setString(2, entity.getNombre());
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw CompuconnectDataException.create(EstadoSolicitudPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_TECHNICAL_MESSAGE, EstadoSolicitudPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_USER_MESSAGE, exception);
        } catch (Exception exception) {
            throw CompuconnectDataException.create(EstadoSolicitudPostgresqlDAOMessage.CREATE_EXCEPTION_TECHNICAL_MESSAGE, EstadoSolicitudPostgresqlDAOMessage.CREATE_EXCEPTION_USER_MESSAGE, exception);
        }
    }

    @Override
    public List<EstadoSolicitudEntity> read(EstadoSolicitudEntity entity) {
   	 List<EstadoSolicitudEntity> estadoList = new ArrayList<>();
   	 return estadoList;
    }

	@Override
	protected String prepareSelect() {
		return "SELECT identificador, nombre, descripcion ";
	}

	@Override
	protected String prepareFrom() {
		return "FROM estado_solicitud ";
	}



	@Override
	protected String prepareOrderBy() {
		return "ORDER BY nombre ASC ";
	}

	@Override
	protected String prepareWhere(EstadoSolicitudEntity entity, List<Object> parameters) {
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
				parameters.add(entity.getNombre());
				where.append(setWhere ? "WHERE" : "AND ").append("descripcion LIKE %?% ");
				setWhere = false;
			}
	
			
		
		}
		return where.toString();
	}

	@Override
	protected void setParameters(PreparedStatement prepareStat, List<Object> parameters) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected List<EstadoSolicitudEntity> executeQuery(PreparedStatement preparedStatement) {
		// TODO Auto-generated method stub
		return null;
	}
}
 