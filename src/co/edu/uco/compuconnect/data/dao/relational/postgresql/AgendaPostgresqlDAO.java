package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    	
    	return null;
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


}
