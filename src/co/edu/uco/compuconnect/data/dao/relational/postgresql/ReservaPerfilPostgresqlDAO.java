package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.compuconnect.crosscutting.exceptions.CompuconnectDataException;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;
import co.edu.uco.compuconnect.crosscutting.utils.UtilUUID;
import co.edu.uco.compuconnect.crosscutting.utils.Messages.ReservaPerfilPostgresqlDAOMessage;
import co.edu.uco.compuconnect.data.dao.ReservaPerfilDAO;
import co.edu.uco.compuconnect.data.dao.relational.SqlDAO;
import co.edu.uco.compuconnect.entities.ReservaPerfilEntity;

public final class ReservaPerfilPostgresqlDAO extends SqlDAO<ReservaPerfilEntity> implements ReservaPerfilDAO {

    

    public ReservaPerfilPostgresqlDAO(final Connection connection) {
        super(connection);
    }

    @Override
    public void create(ReservaPerfilEntity entity) {
        String query = "INSERT INTO reserva_perfil (identificador, reserva, perfil) VALUES (?, ?, ?)";
        try (PreparedStatement statement = getConnection().prepareStatement(query)) {
            statement.setString(1, entity.getIdentificador().toString());
            statement.setObject(2, entity.getReserva().getIdentificador());
            statement.setObject(3, entity.getPerfil().getIdentificador());
            statement.executeUpdate();
        } catch (SQLException exception) {
        	throw CompuconnectDataException.create(ReservaPerfilPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_TECHNICAL_MESSAGE, ReservaPerfilPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_USER_MESSAGE, exception);
        } catch(Exception exception) {
        	throw CompuconnectDataException.create(ReservaPerfilPostgresqlDAOMessage.CREATE_EXCEPTION_TECHNICAL_MESSAGE, ReservaPerfilPostgresqlDAOMessage.CREATE_EXCEPTION_USER_MESSAGE, exception);
        }
    }

    @Override
    public List<ReservaPerfilEntity> read(ReservaPerfilEntity entity) {
        List<ReservaPerfilEntity> reservasPerfil = new ArrayList<>();
        
        return reservasPerfil;
    }

    @Override
    public void delete(ReservaPerfilEntity entity) {
        String query = "DELETE FROM reserva_perfil WHERE id = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(query)) {
            statement.setString(1, entity.getIdentificador().toString());
            statement.executeUpdate();
        } catch (SQLException exception) {
        	throw CompuconnectDataException.create(ReservaPerfilPostgresqlDAOMessage.DELETE_SQL_EXCEPTION_TECHNICAL_MESSAGE, ReservaPerfilPostgresqlDAOMessage.DELETE_SQL_EXCEPTION_USER_MESSAGE, exception);
        } catch(Exception exception) {
        	throw CompuconnectDataException.create(ReservaPerfilPostgresqlDAOMessage.DELETE_EXCEPTION_TECHNICAL_MESSAGE, ReservaPerfilPostgresqlDAOMessage.DELETE_EXCEPTION_USER_MESSAGE, exception);
        }
    }

	@Override
	protected String prepareSelect() {
		return "SELECT identificador, reserva, perfil ";
	}

	@Override
	protected String prepareFrom() {
		return "FROM reserva_perfil ";
	}

	@Override
	protected String prepareWhere(ReservaPerfilEntity entity, List<Object> parameters) {
		final var where = new StringBuilder("");
		parameters = UtilObject.getDefault(parameters, new ArrayList<>());
		var setWhere = true;
		
		if(!UtilObject.isNull(entity)) {
			if(!UtilUUID.isDefault(entity.getIdentificador())) {
				parameters.add(entity.getIdentificador());
				where.append("WHERE identificador=? ");
				setWhere = false;
			}
			if(!UtilUUID.isDefault(entity.getReserva().getIdentificador())) {
				parameters.add(entity.getReserva().getIdentificador());
				where.append(setWhere ? "WHERE" : "AND ").append("reserva =? ");
				setWhere = false;
			}
			if(!UtilUUID.isDefault(entity.getPerfil().getIdentificador())) {
				parameters.add(entity.getPerfil().getIdentificador());
				where.append(setWhere ? "WHERE" : "AND ").append("perfil =? ");
			}
			
		
		}
		return where.toString();
	}

	@Override
	protected String prepareOrderBy() {
		return "ORDER BY reserva ASC ";
	}

	@Override
	protected void setParameters(PreparedStatement prepareStat, List<Object> parameters) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected List<ReservaPerfilEntity> executeQuery(PreparedStatement preparedStatement) {
		// TODO Auto-generated method stub
		return null;
	}
}