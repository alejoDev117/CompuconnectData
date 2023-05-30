package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.compuconnect.crosscutting.exceptions.CompuconnectDataException;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;
import co.edu.uco.compuconnect.crosscutting.utils.UtilText;
import co.edu.uco.compuconnect.crosscutting.utils.UtilUUID;
import co.edu.uco.compuconnect.crosscutting.utils.Messages.TipoUsuarioPostgresqlDAOMessage;
import co.edu.uco.compuconnect.data.dao.TipoUsuarioDAO;
import co.edu.uco.compuconnect.data.dao.relational.SqlDAO;
import co.edu.uco.compuconnect.entities.TipoUsuarioEntity;

public final class TipoUsuarioPostgresqlDAO extends SqlDAO<TipoUsuarioEntity> implements TipoUsuarioDAO {

    public TipoUsuarioPostgresqlDAO(final Connection connection) {
        super(connection);
    }

    @Override
    public void create(TipoUsuarioEntity entity) {
        var sqlStatement = "INSERT INTO tipo_Usuario (identificador, nombre, descripcion) " +
                "VALUES (?, ?, ?)";

        try (var preparedStatement = getConnection().prepareStatement(sqlStatement)) {
            preparedStatement.setObject(1, entity.getIdentificador());
            preparedStatement.setString(2, entity.getNombre());
            preparedStatement.setString(3, entity.getDescripcion());

            preparedStatement.executeUpdate();

        } catch (final SQLException exception) {
        	throw CompuconnectDataException.create(TipoUsuarioPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_TECHNICAL_MESSAGE, TipoUsuarioPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_USER_MESSAGE,exception);
        	
        }catch(final Exception exception) {
        	throw CompuconnectDataException.create(TipoUsuarioPostgresqlDAOMessage.CREATE_EXCEPTION_TECHNICAL_MESSAGE, TipoUsuarioPostgresqlDAOMessage.CREATE_EXCEPTION_USER_MESSAGE,exception);
        }
    }

    @Override
    public List<TipoUsuarioEntity> read(TipoUsuarioEntity entity) {
    	var sqlStatement = new StringBuilder();
    	var listParameters = new ArrayList<>();
    	
    	sqlStatement.append(prepareSelect());
    	sqlStatement.append(prepareFrom());
    	sqlStatement.append(prepareWhere(entity, listParameters));
    	sqlStatement.append(prepareOrderBy());
    	
    	try (var prepareStatement = getConnection().prepareStatement(sqlStatement.toString())){
    		setParameters(prepareStatement, listParameters);
    		return executeQuery(prepareStatement);
    		
    	}catch (CompuconnectDataException exception) {
    		throw exception;
    	}catch(SQLException exception) {
    		throw CompuconnectDataException.create(TipoUsuarioPostgresqlDAOMessage.READ_SQL_EXCEPTION_TECHNICAL_MESSAGE, TipoUsuarioPostgresqlDAOMessage.READ_SQL_EXCEPTION_USER_MESSAGE, exception);
    	}catch(Exception exception) {
    		throw CompuconnectDataException.create(TipoUsuarioPostgresqlDAOMessage.READ_EXCEPTION_TECHNICAL_MESSAGE, TipoUsuarioPostgresqlDAOMessage.READ_EXCEPTION_USER_MESSAGE, exception);
    	}
    	
    }

    @Override
    protected String prepareSelect() {
        return "SELECT identificador, nombre, descripcion ";
    }

    @Override
    protected String prepareFrom() {
        return "FROM TipoUsuario";
    }

    @Override
    protected String prepareWhere(final TipoUsuarioEntity entity, List<Object> parameters) {
        final var where = new StringBuilder("");
        parameters = UtilObject.getDefault(parameters, new ArrayList<>());

        var setWhere = true;

        if (!UtilObject.isNull(entity)) {

            if (!UtilUUID.isDefault(entity.getIdentificador())) {
                parameters.add(entity.getIdentificador());
                where.append("WHERE identificador=? ");
                setWhere = false;
            }

            if (!UtilText.getUtilText().isEmpty(entity.getNombre())) {
                parameters.add(entity.getNombre());
                where.append(setWhere ? "WHERE " : "AND ").append("nombre=? ");
                setWhere = false;
            }
            if (!UtilText.getUtilText().isEmpty(entity.getDescripcion())) {
                parameters.add(entity.getDescripcion());
                where.append("WHERE descripcion LIKE '%' || ? || '%' ");

            }

        }

        return where.toString();
    }

    @Override
    protected String prepareOrderBy() {
        return "ORDER BY nombre ASC";
    }

	@Override
	protected void setParameters(PreparedStatement prepareStat, List<Object> parameters) {
		try {
			
		if(!UtilObject.isNull(parameters) && !UtilObject.isNull(prepareStat)) {
			for(int index = 0; index < parameters.size();index++) {
				prepareStat.setObject(index + 1, parameters.get(index));
				
			}
		}
		}catch (SQLException exception) {
			throw CompuconnectDataException.create(TipoUsuarioPostgresqlDAOMessage.SET_PARAMETERS_SQL_EXCEPTION_TECHNICAL_MESSAGE, TipoUsuarioPostgresqlDAOMessage.SET_PARAMETERS_SQL_EXCEPTION_USER_MESSAGE, exception);
		}catch (Exception exception) {
			throw CompuconnectDataException.create(TipoUsuarioPostgresqlDAOMessage.SET_PARAMETERS_EXCEPTION_TECHNICAL_MESSAGE, TipoUsuarioPostgresqlDAOMessage.SET_PARAMETERS_EXCEPTION_USER_MESSAGE, exception);
		}
		
	}

	@Override
	protected List<TipoUsuarioEntity> executeQuery(PreparedStatement preparedStatement) {
		
		List<TipoUsuarioEntity> listResultSet = new ArrayList<>();		
		
		try(var resultSet = preparedStatement.executeQuery()){
			
			while(resultSet.next()) {
				var entityTmp = new TipoUsuarioEntity(resultSet.getObject("identificador",UUID.class),
						resultSet.getString("nombre"),
						resultSet.getString("descripcion"));
				listResultSet.add(entityTmp);
			}
			
		}catch (SQLException exception) {
			throw CompuconnectDataException.create(TipoUsuarioPostgresqlDAOMessage.EXCECUTE_QUERY_SQL_EXCEPTION_TECHNICAL_MESSAGE, TipoUsuarioPostgresqlDAOMessage.EXCECUTE_QUERY_SQL_EXCEPTION_USER_MESSAGE, exception);
		}catch (Exception exception) {
			throw CompuconnectDataException.create(TipoUsuarioPostgresqlDAOMessage.EXCECUTE_QUERY_EXCEPTION_TECHNICAL_MESSAGE, TipoUsuarioPostgresqlDAOMessage.EXCECUTE_QUERY_EXCEPTION_USER_MESSAGE, exception);
	}
	
		return listResultSet;
	}
		
}
