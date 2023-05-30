package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.compuconnect.crosscutting.exceptions.CompuconnectDataException;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;
import co.edu.uco.compuconnect.crosscutting.utils.UtilUUID;
import co.edu.uco.compuconnect.crosscutting.utils.Messages.BuzonSolicitudPostgresqlDAOMessage;
import co.edu.uco.compuconnect.data.dao.BuzonSolicitudDAO;
import co.edu.uco.compuconnect.data.dao.relational.SqlDAO;
import co.edu.uco.compuconnect.entities.AgendaReservaEntity;
import co.edu.uco.compuconnect.entities.BuzonSolicitudEntity;

public final class BuzonSolicitudPostgresqlDAO extends SqlDAO<BuzonSolicitudEntity>  implements BuzonSolicitudDAO {



    public BuzonSolicitudPostgresqlDAO(final Connection connection) {
        super(connection);
    }

    @Override
    public void create(BuzonSolicitudEntity entity) {
        String sqlStatement = "INSERT INTO buzon_solicitud (identificador, solicitud, respuesta) VALUES (?, ?, ?)";

        try (PreparedStatement statement = getConnection().prepareStatement(sqlStatement)) {
            statement.setObject(1, entity.getIdentificador());
            statement.setObject(2, entity.getSolicitud().getIdentificador());
            statement.setObject(3, entity.getRespuesta().getIdentificador());

            statement.executeUpdate();
        } catch (SQLException exception) {
        	throw CompuconnectDataException.create(BuzonSolicitudPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_TECHNICAL_MESSAGE, BuzonSolicitudPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_USER_MESSAGE, exception);
        }catch (Exception exception) {
        	throw CompuconnectDataException.create(BuzonSolicitudPostgresqlDAOMessage.CREATE_EXCEPTION_TECHNICAL_MESSAGE, BuzonSolicitudPostgresqlDAOMessage.CREATE_EXCEPTION_USER_MESSAGE, exception);
        }
    }

    @Override
    public List<BuzonSolicitudEntity> read(BuzonSolicitudEntity entity) {
        List<BuzonSolicitudEntity> buzonList = new ArrayList<>();
        String sql = "SELECT campo1, campo2, campo3 FROM BuzonSolicitud WHERE campo1 = ?";
        return buzonList;
    }

    @Override
    public void update(BuzonSolicitudEntity entity) {
        String sql = "UPDATE BuzonSolicitud SET respuesta =?,solicitud=? WHERE identificador=?";

        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setObject(1, entity.getRespuesta().getIdentificador());
            statement.setObject(2, entity.getSolicitud().getIdentificador());
            statement.setObject(3, entity.getIdentificador());

            statement.executeUpdate();
        } catch (SQLException exception) {
        	throw CompuconnectDataException.create(BuzonSolicitudPostgresqlDAOMessage.UPDATE_SQL_EXCEPTION_TECHNICAL_MESSAGE, BuzonSolicitudPostgresqlDAOMessage.UPDATE_SQL_EXCEPTION_USER_MESSAGE, exception);
        }catch (Exception exception) {
          	throw CompuconnectDataException.create(BuzonSolicitudPostgresqlDAOMessage.UPDATE_EXCEPTION_TECHNICAL_MESSAGE, BuzonSolicitudPostgresqlDAOMessage.UPDATE_EXCEPTION_USER_MESSAGE, exception);
        }
        }

    @Override
    public void delete(BuzonSolicitudEntity entity) {
        String sql = "DELETE FROM buzon_solicitud WHERE identificador = ?";

        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setObject(1, entity.getIdentificador());

            statement.executeUpdate();
        } catch (SQLException exception) {
            throw CompuconnectDataException.create(BuzonSolicitudPostgresqlDAOMessage.DELETE_SQL_EXCEPTION_TECHNICAL_MESSAGE, BuzonSolicitudPostgresqlDAOMessage.DELETE_SQL_EXCEPTION_USER_MESSAGE, exception);
        }catch(Exception exception) {
            throw CompuconnectDataException.create(BuzonSolicitudPostgresqlDAOMessage.DELETE_EXCEPTION_TECHNICAL_MESSAGE, BuzonSolicitudPostgresqlDAOMessage.DELETE_EXCEPTION_USER_MESSAGE, exception);
        }
    }

	@Override
	protected String prepareSelect() {
		return "SELECT identificador, solicitud, respuesta ";
	}

	@Override
	protected String prepareFrom() {
		return "FROM buzon_solicitud ";	
		
	}

	@Override
	protected String prepareWhere(BuzonSolicitudEntity entity, List<Object> parameters) {
		
		
		final var where = new StringBuilder("");
		parameters = UtilObject.getDefault(parameters, new ArrayList<>());
		var setWhere = true;
		
		if(!UtilObject.isNull(entity)) {
			if(!UtilUUID.isDefault(entity.getIdentificador())) {
				parameters.add(entity.getIdentificador());
				where.append("WHERE identificador=? ");
				setWhere = false;
			}
			if(!UtilUUID.isDefault(entity.getSolicitud().getIdentificador())) {
				parameters.add(entity.getSolicitud().getIdentificador());
				where.append(setWhere ? "WHERE" : "AND ").append("solicitud=? ");
				setWhere = false;
			}
			if(!UtilUUID.isDefault(entity.getRespuesta().getIdentificador())) {
				parameters.add(entity.getRespuesta().getIdentificador());
				where.append(setWhere ? "WHERE" : "AND ").append("respuesta=? ");
			}
			
		
		}
		return where.toString();
	}

	@Override
	protected String prepareOrderBy() {
		return "ORDER BY ASC";
	}

	@Override
	protected void setParameters(PreparedStatement prepareStat, List<Object> parameters) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected List<BuzonSolicitudEntity> executeQuery(PreparedStatement preparedStatement) {
		// TODO Auto-generated method stub
		return null;
	}
}