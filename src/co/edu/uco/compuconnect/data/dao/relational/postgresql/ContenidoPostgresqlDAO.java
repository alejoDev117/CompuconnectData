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
import co.edu.uco.compuconnect.crosscutting.utils.Messages.ContenidoPostgresqlDAOMessage;
import co.edu.uco.compuconnect.data.dao.ContenidoDAO;
import co.edu.uco.compuconnect.data.dao.relational.SqlDAO;
import co.edu.uco.compuconnect.entities.ContenidoEntity;

public class ContenidoPostgresqlDAO extends SqlDAO<ContenidoEntity> implements ContenidoDAO {


    public ContenidoPostgresqlDAO(final Connection connection) {
    	super(connection);
    }

    @Override
    public void create(ContenidoEntity entity) {
        String sqlStatements = "INSERT INTO contenido (identificador, descripcion) VALUES (?, ?)";

        try (PreparedStatement statement = getConnection().prepareStatement(sqlStatements)) {
            statement.setObject(1, entity.getIdentificador());
            statement.setString(2, entity.getDescripcion());

            statement.executeUpdate();
        } catch (SQLException exception) {
          throw CompuconnectDataException.create(ContenidoPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_TECHNICAL_MESSAGE, ContenidoPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_USER_MESSAGE, exception);
        }catch(Exception exception) {
        	 throw CompuconnectDataException.create(ContenidoPostgresqlDAOMessage.CREATE_EXCEPTION_TECHNICAL_MESSAGE, ContenidoPostgresqlDAOMessage.CREATE_EXCEPTION_USER_MESSAGE, exception);
        }
    }

    @Override
    public List<ContenidoEntity> read(ContenidoEntity entity) {
        List<ContenidoEntity> contenidoList = new ArrayList<>();
        String sqlStatements = "SELECT identificador, descripcion FROM Contenido WHERE identificador = ?";

        return contenidoList;
    }

    @Override
    public void delete(ContenidoEntity entity) {
        String sqlStatements = "DELETE FROM Contenido WHERE identificador = ?";

        try (PreparedStatement statement = getConnection().prepareStatement(sqlStatements)) {
            statement.setObject(1, entity.getIdentificador());

            statement.executeUpdate();
        }catch (SQLException exception) {
            throw CompuconnectDataException.create(ContenidoPostgresqlDAOMessage.DELETE_SQL_EXCEPTION_TECHNICAL_MESSAGE, ContenidoPostgresqlDAOMessage.DELETE_SQL_EXCEPTION_USER_MESSAGE, exception);
          }catch(Exception exception) {
          	 throw CompuconnectDataException.create(ContenidoPostgresqlDAOMessage.DELETE_EXCEPTION_TECHNICAL_MESSAGE, ContenidoPostgresqlDAOMessage.DELETE_EXCEPTION_USER_MESSAGE, exception);
          }
    }

	@Override
	protected String prepareSelect() {
		return "SELECT identificador, descripcion ";
	}

	@Override
	protected String prepareFrom() {
		return "FROM contenido ";
	}

	@Override
	protected String prepareWhere(ContenidoEntity entity, List<Object> parameters) {
		
		final var where = new StringBuilder("");
		parameters = UtilObject.getDefault(parameters, new ArrayList<>());
		var setWhere = true;
		
		if(!UtilObject.isNull(entity)) {
			if(!UtilUUID.isDefault(entity.getIdentificador())) {
				parameters.add(entity.getIdentificador());
				where.append("WHERE identificador=? ");
				setWhere = false;
			}
			if(!UtilText.getUtilText().isEmpty(entity.getDescripcion())) {
				parameters.add(entity.getDescripcion());
				where.append(setWhere ? "WHERE" : "AND ").append("descripcion LIKE %?% ");
				setWhere = false;
			}		
		}
		return where.toString();
	}

	@Override
	protected String prepareOrderBy() {
		return "ORDER BY descripcion ASC ";
	}

	@Override
	protected void setParameters(PreparedStatement prepareStat, List<Object> parameters) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected List<ContenidoEntity> executeQuery(PreparedStatement preparedStatement) {
		// TODO Auto-generated method stub
		return null;
	}
}
