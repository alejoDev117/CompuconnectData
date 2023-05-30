package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.compuconnect.crosscutting.exceptions.CompuconnectDataException;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;
import co.edu.uco.compuconnect.crosscutting.utils.UtilUUID;
import co.edu.uco.compuconnect.crosscutting.utils.Messages.AgendaPostgresqlDAOMessage;
import co.edu.uco.compuconnect.data.dao.AgendaDAO;
import co.edu.uco.compuconnect.data.dao.relational.SqlDAO;
import co.edu.uco.compuconnect.entities.AgendaEntity;
import co.edu.uco.compuconnect.entities.CentroInformaticaEntity;
import co.edu.uco.compuconnect.entities.PeriodoFuncionamientoEntity;

public final class AgendaPostgresqlDAO extends SqlDAO<AgendaEntity> implements  AgendaDAO {


   
    public AgendaPostgresqlDAO(final Connection connection) {
		super(connection);
	}

	@Override
    public void create(final AgendaEntity entity) {
        String sqlStatement = "INSERT INTO agenda (identificador,\"periodoFuncionamiento\", \"centroInformatica\", nombre) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = getConnection().prepareStatement(sqlStatement)) {
            statement.setObject(1, entity.getIdentificador());
            statement.setObject(2, entity.getPeriodoFuncionamiento().getIdentificador());
            statement.setObject(3, entity.getCentroInformatica().getIdentificador());
            statement.setString(4, entity.getNombre());

            statement.executeUpdate();
        } catch (final SQLException exception) {
        	throw CompuconnectDataException.create(AgendaPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_TECHNICAL_MESSAGE, AgendaPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_USER_MESSAGE,exception);
        	
        }catch(final Exception exception) {
        	throw CompuconnectDataException.create(AgendaPostgresqlDAOMessage.CREATE_EXCEPTION_TECHNICAL_MESSAGE, AgendaPostgresqlDAOMessage.CREATE_EXCEPTION_USER_MESSAGE,exception);
        }
    }

    @Override
    public List<AgendaEntity> read(final AgendaEntity entity) {
    	var sqlStatement = new StringBuilder();
    	var listParameters = new ArrayList<>();
    	
    	sqlStatement.append(prepareSelect());
    	sqlStatement.append(prepareFrom());
    	sqlStatement.append(prepareWhere(entity, listParameters));
    	sqlStatement.append(prepareOrderBy());
    	
    	try (var prepareStatement = getConnection().prepareStatement(sqlStatement.toString())){
    		setParameters(prepareStatement, listParameters);
    		return excecuteQuery(prepareStatement);
    		
    	}catch (CompuconnectDataException exception) {
    		throw exception;
    	}catch(SQLException exception) {
    		throw CompuconnectDataException.create(AgendaPostgresqlDAOMessage.READ_SQL_EXCEPTION_TECHNICAL_MESSAGE, AgendaPostgresqlDAOMessage.READ_SQL_EXCEPTION_USER_MESSAGE, exception);
    	}catch(Exception exception) {
    		throw CompuconnectDataException.create(AgendaPostgresqlDAOMessage.READ_EXCEPTION_TECHNICAL_MESSAGE, AgendaPostgresqlDAOMessage.READ_EXCEPTION_USER_MESSAGE, exception);
    	}
    	
    }

    @Override
    public void delete(final AgendaEntity entity) {
        String sqlStatement = "DELETE FROM agenda WHERE identificador = ?";

        try (PreparedStatement statement = getConnection().prepareStatement(sqlStatement)) {
            statement.setObject(1, entity.getIdentificador());

            statement.executeUpdate();
        } catch (SQLException exception) {
        	throw CompuconnectDataException.create(AgendaPostgresqlDAOMessage.DELETE_SQL_EXCEPTION_TECHNICAL_MESSAGE, AgendaPostgresqlDAOMessage.DELETE_SQL_EXCEPTION_USER_MESSAGE, exception);
        }catch(Exception exception) {
        	throw CompuconnectDataException.create(AgendaPostgresqlDAOMessage.DELETE_EXCEPTION_TECHNICAL_MESSAGE, AgendaPostgresqlDAOMessage.DELETE_EXCEPTION_USER_MESSAGE, exception);
        }
    }

    @Override
    public void uptade(AgendaEntity entity) {
        String sqlStatement = "UPDATE agenda SET periodoFuncionamiento= ?, centroInformatica= ?,nombre = ? WHERE identificador = ?";

        try (PreparedStatement statement = getConnection().prepareStatement(sqlStatement)) {
            statement.setObject(1, entity.getPeriodoFuncionamiento().toString());
            statement.setObject(2, entity.getCentroInformatica());
            statement.setString(3, entity.getNombre());
            statement.setObject(4, entity.getIdentificador());	

            statement.executeUpdate();
        } catch (SQLException exception) {
        	throw CompuconnectDataException.create(AgendaPostgresqlDAOMessage.UPDATE_SQL_EXCEPTION_TECHNICAL_MESSAGE, AgendaPostgresqlDAOMessage.UPDATE_SQL_EXCEPTION_USER_MESSAGE, exception);
        }catch(Exception exception) {
        	throw CompuconnectDataException.create(AgendaPostgresqlDAOMessage.UPDATE_EXCEPTION_TECHNICAL_MESSAGE, AgendaPostgresqlDAOMessage.UPDATE_EXCEPTION_USER_MESSAGE, exception);
        }
    }

	@Override
	protected String prepareSelect() {
		return "SELECT identificador, periodoFuncionamiento, centroInformatica, nombre ";
	}

	@Override
	protected String prepareFrom() {
		return "FROM agenda ";
	}

	@Override
	protected String prepareWhere(final AgendaEntity entity, List<Object> parameters) {
		
		final var where = new StringBuilder("");
		parameters = UtilObject.getDefault(parameters, new ArrayList<>());
		var setWhere = true;
		
		if(!UtilObject.isNull(entity)) {
			
			if(!UtilUUID.isDefault(entity.getIdentificador())) {
				parameters.add(entity.getIdentificador());
				where.append("WHERE identificador=? ");
				setWhere = false;
			}
			if(!UtilUUID.isDefault(entity.getPeriodoFuncionamiento().getIdentificador())) {
				parameters.add(entity.getPeriodoFuncionamiento().getIdentificador());
				where.append(setWhere ? "WHERE" : "AND ").append("periodoFuncionamiento=? ");
				setWhere = false;
			}
			if(!UtilUUID.isDefault(entity.getCentroInformatica().getIdentificador())) {
				parameters.add(entity.getCentroInformatica().getIdentificador());
				where.append(setWhere ? "WHERE" : "AND ").append("periodoFuncionamiento=? ");
			}
			
		
		}
		return where.toString();
	}

	@Override
	protected String prepareOrderBy() {
		return "ORDER BY nombre ASC";
	}

	@Override
	protected void setParameters(final PreparedStatement prepareStatement, final List<Object> parameters) {
		try {
			
		if(!UtilObject.isNull(parameters) && !UtilObject.isNull(prepareStatement)) {
			for(int index = 0; index < parameters.size();index++) {
				prepareStatement.setObject(index + 1, parameters.get(index));
				
			}
		}
		}catch (SQLException exception) {
			throw CompuconnectDataException.create(AgendaPostgresqlDAOMessage.SET_PARAMETERS_SQL_EXCEPTION_TECHNICAL_MESSAGE, AgendaPostgresqlDAOMessage.SET_PARAMETERS_SQL_EXCEPTION_USER_MESSAGE, exception);
		}catch (Exception exception) {
			throw CompuconnectDataException.create(AgendaPostgresqlDAOMessage.SET_PARAMETERS_EXCEPTION_TECHNICAL_MESSAGE, AgendaPostgresqlDAOMessage.SET_PARAMETERS_EXCEPTION_USER_MESSAGE, exception);
		}
		
	}

	@Override
	protected List<AgendaEntity> excecuteQuery(PreparedStatement prepareStatement) {
		
		List<AgendaEntity> listResultSet = new ArrayList<>();		
		
		try(var resultSet = prepareStatement.executeQuery()){
			
			while(resultSet.next()) {
				var entityTmp = new AgendaEntity(resultSet.getObject("identificador",UUID.class)
						,resultSet.getObject("periodoFuncionamiento",PeriodoFuncionamientoEntity.class),
						resultSet.getObject("centroInformatica",CentroInformaticaEntity.class)
						,resultSet.getString("nombre"));
				listResultSet.add(entityTmp);
			}
			
		}catch (SQLException exception) {
			throw CompuconnectDataException.create(AgendaPostgresqlDAOMessage.EXCECUTE_QUERY_SQL_EXCEPTION_TECHNICAL_MESSAGE, AgendaPostgresqlDAOMessage.EXCECUTE_QUERY_SQL_EXCEPTION_USER_MESSAGE, exception);
		}catch (Exception exception) {
			throw CompuconnectDataException.create(AgendaPostgresqlDAOMessage.EXCECUTE_QUERY_EXCEPTION_TECHNICAL_MESSAGE, AgendaPostgresqlDAOMessage.EXCECUTE_QUERY_EXCEPTION_USER_MESSAGE, exception);
	}
	
		return null;
	}
		
}
