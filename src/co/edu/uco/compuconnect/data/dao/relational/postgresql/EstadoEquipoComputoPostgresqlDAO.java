package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.compuconnect.crosscutting.exceptions.CompuconnectDataException;
import co.edu.uco.compuconnect.crosscutting.utils.Messages.EstadoEquipoComputoPostgresqlDAOMessage;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;
import co.edu.uco.compuconnect.crosscutting.utils.UtilSql;
import co.edu.uco.compuconnect.crosscutting.utils.UtilText;
import co.edu.uco.compuconnect.crosscutting.utils.UtilUUID;
import co.edu.uco.compuconnect.data.dao.EstadoEquipoComputoDAO;
import co.edu.uco.compuconnect.data.dao.relational.SqlDAO;
import co.edu.uco.compuconnect.entities.EstadoEquipoComputoEntity;

public final class EstadoEquipoComputoPostgresqlDAO extends SqlDAO<EstadoEquipoComputoEntity> implements EstadoEquipoComputoDAO {

   

    public EstadoEquipoComputoPostgresqlDAO(final Connection connection) {
       super(connection);
    }

    @Override
    public void create(EstadoEquipoComputoEntity entity) {
        String sql = "INSERT INTO estado_equipo_computo (identificador, nombre, descripcion) VALUES (?, ?, ?)";

        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setObject(1, entity.getIdentificador());
            statement.setString(2, entity.getNombre());
            statement.setString(3, entity.getDescripcion());

            statement.executeUpdate();
            
        } catch (SQLException exception) {
           throw CompuconnectDataException.create(EstadoEquipoComputoPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_TECHNICAL_MESSAGE, EstadoEquipoComputoPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_USER_MESSAGE, exception);
        } catch(Exception exception) {
        	throw CompuconnectDataException.create(EstadoEquipoComputoPostgresqlDAOMessage.CREATE_EXCEPTION_TECHNICAL_MESSAGE, EstadoEquipoComputoPostgresqlDAOMessage.CREATE_EXCEPTION_USER_MESSAGE, exception);
        }
    }

    @Override
    public List<EstadoEquipoComputoEntity> read(EstadoEquipoComputoEntity entity) {
        List<EstadoEquipoComputoEntity> estadoList = new ArrayList<>();
        return estadoList;
    }

	@Override
	protected String prepareSelect() {
		return "SELECT identificador, nombre, descripcion ";
	}

	@Override
	protected String prepareFrom() {
		return "FROM estado_equipo_computo ";
	}

	@Override
	protected String prepareWhere(EstadoEquipoComputoEntity entity, List<Object> parameters) {
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
	protected String prepareOrderBy() {
		return "ORDER BY nombre ASC ";
	}
    
	
}   

