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
import co.edu.uco.compuconnect.crosscutting.utils.Messages.CentroInformaticaPostgresqlDAOMessage;
import co.edu.uco.compuconnect.data.dao.CentroInformaticaDAO;
import co.edu.uco.compuconnect.data.dao.relational.SqlDAO;
import co.edu.uco.compuconnect.entities.CentroInformaticaEntity;

public final class CentroInformaticaPostgresqlDAO extends SqlDAO<CentroInformaticaEntity> implements CentroInformaticaDAO {

    public CentroInformaticaPostgresqlDAO(final Connection connection) {
        super(connection);
    }

    @Override
    public void create(CentroInformaticaEntity entity) {
        var sqlStament = "INSERT INTO \"CentroInformatica\" (identificador, nombre, ubicacion, \"poseeVideoBeam\") VALUES (?, ?, ?, ?)";

        try (var preparedStament = getConnection().prepareStatement(sqlStament)) {
            preparedStament.setObject(1, entity.getIdentificador());
            preparedStament.setString(2, entity.getNombre());
            preparedStament.setString(3, entity.getUbicacion());
            preparedStament.setBoolean(4, entity.isPoseeVideoBeam());

            preparedStament.executeUpdate();

        } catch (final SQLException exception) {
        	throw CompuconnectDataException.create(CentroInformaticaPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_TECHNICAL_MESSAGE, CentroInformaticaPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_USER_MESSAGE,exception);
        	
        }catch(final Exception exception) {
        	throw CompuconnectDataException.create(CentroInformaticaPostgresqlDAOMessage.CREATE_EXCEPTION_TECHNICAL_MESSAGE, CentroInformaticaPostgresqlDAOMessage.CREATE_EXCEPTION_USER_MESSAGE,exception);
        }
    }

    @Override
    public List<CentroInformaticaEntity> read(CentroInformaticaEntity entity) {
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
    		throw CompuconnectDataException.create(CentroInformaticaPostgresqlDAOMessage.READ_SQL_EXCEPTION_TECHNICAL_MESSAGE, CentroInformaticaPostgresqlDAOMessage.READ_SQL_EXCEPTION_USER_MESSAGE, exception);
    	}catch(Exception exception) {
    		throw CompuconnectDataException.create(CentroInformaticaPostgresqlDAOMessage.READ_EXCEPTION_TECHNICAL_MESSAGE, CentroInformaticaPostgresqlDAOMessage.READ_EXCEPTION_USER_MESSAGE, exception);
    	}
    }


    @Override
    public void update(CentroInformaticaEntity entity) {
        var sqlStament = "UPDATE \"CentroInformatica\" SET nombre = ?, ubicacion = ?, \"poseeVideoBeam\" = ? WHERE identificador = ?";

        try (var preparedStament = getConnection().prepareStatement(sqlStament)) {
        	preparedStament.setString(1, entity.getNombre());
        	preparedStament.setString(2, entity.getUbicacion());
        	preparedStament.setBoolean(3, entity.isPoseeVideoBeam());
        	preparedStament.setObject(4, entity.getIdentificador());

            preparedStament.executeUpdate();
        } catch (SQLException exception) {
        	throw CompuconnectDataException.create(CentroInformaticaPostgresqlDAOMessage.UPDATE_SQL_EXCEPTION_TECHNICAL_MESSAGE, CentroInformaticaPostgresqlDAOMessage.UPDATE_SQL_EXCEPTION_USER_MESSAGE, exception);
        }catch(Exception exception) {
        	throw CompuconnectDataException.create(CentroInformaticaPostgresqlDAOMessage.UPDATE_EXCEPTION_TECHNICAL_MESSAGE, CentroInformaticaPostgresqlDAOMessage.UPDATE_EXCEPTION_USER_MESSAGE, exception);
        }
    }

    @Override
    public void delete(CentroInformaticaEntity entity) {
        var sqlStatement = "DELETE FROM \"CentroInformatica\" WHERE identificador=?";
        
        try (var preparedStatement = getConnection().prepareStatement(sqlStatement)) {
            preparedStatement.setObject(1, entity.getIdentificador());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
        	throw CompuconnectDataException.create(CentroInformaticaPostgresqlDAOMessage.DELETE_SQL_EXCEPTION_TECHNICAL_MESSAGE, CentroInformaticaPostgresqlDAOMessage.DELETE_SQL_EXCEPTION_USER_MESSAGE, exception);
        }catch(Exception exception) {
        	throw CompuconnectDataException.create(CentroInformaticaPostgresqlDAOMessage.DELETE_EXCEPTION_TECHNICAL_MESSAGE, CentroInformaticaPostgresqlDAOMessage.DELETE_EXCEPTION_USER_MESSAGE, exception);
        }
    }


	@Override
	protected String prepareSelect() {
		return "SELECT identificador, nombre, ubicacion, \"poseeVideoBeam\"";
	}

	@Override
	protected String prepareFrom() {
		return "FROM \"CentroInformatica\"";
	}

	@Override
	protected String prepareWhere(CentroInformaticaEntity entity, List<Object> parameters) {
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
				where.append(setWhere ? "WHERE " : "AND ").append("nombre=? ");
				setWhere = false;
			}
			if(!UtilText.getUtilText().isEmpty(entity.getUbicacion())) {
				parameters.add(entity.getUbicacion());
				where.append("WHERE descripcion LIKE %?% ");
			}
			
		}
		
		return where.toString();
	}

	@Override
	protected String prepareOrderBy() {
		return  "ORDER BY nombre ASC";
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
			throw CompuconnectDataException.create(CentroInformaticaPostgresqlDAOMessage.SET_PARAMETERS_SQL_EXCEPTION_TECHNICAL_MESSAGE, CentroInformaticaPostgresqlDAOMessage.SET_PARAMETERS_SQL_EXCEPTION_USER_MESSAGE, exception);
		}catch (Exception exception) {
			throw CompuconnectDataException.create(CentroInformaticaPostgresqlDAOMessage.SET_PARAMETERS_EXCEPTION_TECHNICAL_MESSAGE, CentroInformaticaPostgresqlDAOMessage.SET_PARAMETERS_EXCEPTION_USER_MESSAGE, exception);
		}
	}

	@Override
	protected List<CentroInformaticaEntity> executeQuery(PreparedStatement preparedStatement) {
		
		List<CentroInformaticaEntity> listResultSet = new ArrayList<>();		
		
		try(var resultSet = preparedStatement.executeQuery()){
			
			while(resultSet.next()) {
				var entityTmp = new CentroInformaticaEntity(resultSet.getObject("identificador",UUID.class),
						resultSet.getString("nombre"), 
						resultSet.getString("ubicacion"), resultSet.getBoolean("poseeVideoBeam"));
				listResultSet.add(entityTmp);
			}
			
		}catch (SQLException exception) {
			throw CompuconnectDataException.create(CentroInformaticaPostgresqlDAOMessage.EXCECUTE_QUERY_SQL_EXCEPTION_TECHNICAL_MESSAGE, CentroInformaticaPostgresqlDAOMessage.EXCECUTE_QUERY_SQL_EXCEPTION_USER_MESSAGE, exception);
		}catch (Exception exception) {
			throw CompuconnectDataException.create(CentroInformaticaPostgresqlDAOMessage.EXCECUTE_QUERY_EXCEPTION_TECHNICAL_MESSAGE, CentroInformaticaPostgresqlDAOMessage.EXCECUTE_QUERY_EXCEPTION_USER_MESSAGE, exception);
	}
	
		return listResultSet;
	}
}