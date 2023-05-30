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
import co.edu.uco.compuconnect.crosscutting.utils.Messages.UsuarioPostgresqlDAOMessage;
import co.edu.uco.compuconnect.data.dao.UsuarioDAO;
import co.edu.uco.compuconnect.data.dao.relational.SqlDAO;
import co.edu.uco.compuconnect.entities.TipoIdentificacionEntity;
import co.edu.uco.compuconnect.entities.TipoUsuarioEntity;
import co.edu.uco.compuconnect.entities.UsuarioEntity;

public final class UsuarioPostgresqlDAO extends SqlDAO<UsuarioEntity> implements UsuarioDAO {

    public UsuarioPostgresqlDAO(final Connection connection) {
        super(connection);
    }

    @Override
    public void create(UsuarioEntity entity) {
        var sqlStatement = "INSERT INTO usuario (identificador, tipoUsuario, nombre, tipoIdentificacion, identificacion, correoInstitucional) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (var preparedStatement = getConnection().prepareStatement(sqlStatement)) {
            preparedStatement.setObject(1, entity.getIdentificador());
            preparedStatement.setObject(2, entity.getTipoUsuario().getIdentificador());
            preparedStatement.setString(3, entity.getNombre());
            preparedStatement.setObject(4, entity.getTipoIdentificacion().getIdentificador());
            preparedStatement.setString(5, entity.getIdentificacion());
            preparedStatement.setString(6, entity.getCorreoInstitucional());

            preparedStatement.executeUpdate();

        } catch (final SQLException exception) {
        	throw CompuconnectDataException.create(UsuarioPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_TECHNICAL_MESSAGE, UsuarioPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_USER_MESSAGE,exception);
        	
        }catch(final Exception exception) {
        	throw CompuconnectDataException.create(UsuarioPostgresqlDAOMessage.CREATE_EXCEPTION_TECHNICAL_MESSAGE, UsuarioPostgresqlDAOMessage.CREATE_EXCEPTION_USER_MESSAGE,exception);
        }
    }

    @Override
    public List<UsuarioEntity> read(UsuarioEntity entity) {
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
    		throw CompuconnectDataException.create(UsuarioPostgresqlDAOMessage.READ_SQL_EXCEPTION_TECHNICAL_MESSAGE, UsuarioPostgresqlDAOMessage.READ_SQL_EXCEPTION_USER_MESSAGE, exception);
    	}catch(Exception exception) {
    		throw CompuconnectDataException.create(UsuarioPostgresqlDAOMessage.READ_EXCEPTION_TECHNICAL_MESSAGE, UsuarioPostgresqlDAOMessage.READ_EXCEPTION_USER_MESSAGE, exception);
    	}
    }
    
    
    @Override
    public void update(UsuarioEntity entity) {
        var sqlStatement = "UPDATE usuario SET  nombre = ?, tipoIdentificacion = ?, " +
                "identificacion = ?, correoInstitucional = ? WHERE identificador = ?";

        try (var preparedStatement = getConnection().prepareStatement(sqlStatement)) {
            preparedStatement.setObject(1, entity.getTipoUsuario());
            preparedStatement.setString(2, entity.getNombre());
            preparedStatement.setObject(3, entity.getTipoIdentificacion());
            preparedStatement.setString(4, entity.getIdentificacion());
            preparedStatement.setString(5, entity.getCorreoInstitucional());
            preparedStatement.setObject(6, entity.getIdentificador());

            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
        	throw CompuconnectDataException.create(UsuarioPostgresqlDAOMessage.UPDATE_SQL_EXCEPTION_TECHNICAL_MESSAGE, UsuarioPostgresqlDAOMessage.UPDATE_SQL_EXCEPTION_USER_MESSAGE, exception);
        }catch(Exception exception) {
        	throw CompuconnectDataException.create(UsuarioPostgresqlDAOMessage.UPDATE_EXCEPTION_TECHNICAL_MESSAGE, UsuarioPostgresqlDAOMessage.UPDATE_EXCEPTION_USER_MESSAGE, exception);
        }
    }

    @Override
    public void delete(UsuarioEntity entity) {
        var sqlStatement = "DELETE FROM usuario WHERE identificador = ?";

        try (var preparedStatement = getConnection().prepareStatement(sqlStatement)) {
            preparedStatement.setObject(1, entity.getIdentificador());

            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
        	throw CompuconnectDataException.create(UsuarioPostgresqlDAOMessage.DELETE_SQL_EXCEPTION_TECHNICAL_MESSAGE, UsuarioPostgresqlDAOMessage.DELETE_SQL_EXCEPTION_USER_MESSAGE, exception);
        }catch(Exception exception) {
        	throw CompuconnectDataException.create(UsuarioPostgresqlDAOMessage.DELETE_EXCEPTION_TECHNICAL_MESSAGE, UsuarioPostgresqlDAOMessage.DELETE_EXCEPTION_USER_MESSAGE, exception);
        }
    }

    @Override
    protected String prepareSelect() {
        return "SELECT identificador, tipoUsuario, nombre, tipoIdentificacion, identificacion, correoInstitucional ";
    }

    @Override
    protected String prepareFrom() {
        return "FROM usuario";
    }
    @Override
    protected String prepareWhere(final UsuarioEntity entity, List<Object> parameters) {
        final var where = new StringBuilder("");
        parameters = UtilObject.getDefault(parameters, new ArrayList<>());

        var setWhere = true;

        if (!UtilObject.isNull(entity)) {

            if (!UtilUUID.isDefault(entity.getIdentificador())) {
                parameters.add(entity.getIdentificador());
                where.append("WHERE identificador = ? ");
                setWhere = false;
            }

            if (!UtilObject.isNull(entity.getTipoUsuario())) {
                parameters.add(entity.getTipoUsuario());
                where.append(setWhere ? "WHERE " : "AND ").append("tipoUsuario = ? ");
                setWhere = false;
            }

            if (!UtilText.getUtilText().isEmpty(entity.getNombre())) {
                parameters.add(entity.getNombre());
                where.append(setWhere ? "WHERE " : "AND ").append("nombre = ? ");
                setWhere = false;
            }

            if (!UtilObject.isNull(entity.getTipoIdentificacion())) {
                parameters.add(entity.getTipoIdentificacion());
                where.append(setWhere ? "WHERE " : "AND ").append("tipoIdentificacion = ? ");
                setWhere = false;
            }

            if (!UtilText.getUtilText().isEmpty(entity.getIdentificacion())) {
                parameters.add(entity.getIdentificacion());
                where.append(setWhere ? "WHERE " : "AND ").append("identificacion = ? ");
                setWhere = false;
            }

            if (!UtilText.getUtilText().isEmpty(entity.getCorreoInstitucional())) {
                parameters.add(entity.getCorreoInstitucional());
                where.append(setWhere ? "WHERE " : "AND ").append("correoInstitucional = ? ");
                setWhere = false;
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
			throw CompuconnectDataException.create(UsuarioPostgresqlDAOMessage.SET_PARAMETERS_SQL_EXCEPTION_TECHNICAL_MESSAGE, UsuarioPostgresqlDAOMessage.SET_PARAMETERS_SQL_EXCEPTION_USER_MESSAGE, exception);
		}catch (Exception exception) {
			throw CompuconnectDataException.create(UsuarioPostgresqlDAOMessage.SET_PARAMETERS_EXCEPTION_TECHNICAL_MESSAGE, UsuarioPostgresqlDAOMessage.SET_PARAMETERS_EXCEPTION_USER_MESSAGE, exception);
		}
	}

	@Override
	protected List<UsuarioEntity> executeQuery(PreparedStatement preparedStatement) {
		List<UsuarioEntity> listResultSet = new ArrayList<>();		
		
		try(var resultSet = preparedStatement.executeQuery()){
			
			while(resultSet.next()) {
				var entityTmp = new UsuarioEntity(resultSet.getObject("identificador",UUID.class),
						resultSet.getObject("tipoUsuario",TipoUsuarioEntity.class), 
						resultSet.getString("nombre"),
						resultSet.getObject("tipoIdentificacion",TipoIdentificacionEntity.class), 
						resultSet.getString("identificacion"),
						resultSet.getString("correoInstitucional"));
				listResultSet.add(entityTmp);
			}
			
		}catch (SQLException exception) {
			throw CompuconnectDataException.create(UsuarioPostgresqlDAOMessage.EXCECUTE_QUERY_SQL_EXCEPTION_TECHNICAL_MESSAGE, UsuarioPostgresqlDAOMessage.EXCECUTE_QUERY_SQL_EXCEPTION_USER_MESSAGE, exception);
		}catch (Exception exception) {
			throw CompuconnectDataException.create(UsuarioPostgresqlDAOMessage.EXCECUTE_QUERY_EXCEPTION_TECHNICAL_MESSAGE, UsuarioPostgresqlDAOMessage.EXCECUTE_QUERY_EXCEPTION_USER_MESSAGE, exception);
	}
	
		return listResultSet;
	}
}