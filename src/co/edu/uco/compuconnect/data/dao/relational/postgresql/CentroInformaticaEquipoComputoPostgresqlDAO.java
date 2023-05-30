package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.compuconnect.crosscutting.exceptions.CompuconnectDataException;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;
import co.edu.uco.compuconnect.crosscutting.utils.UtilUUID;
import co.edu.uco.compuconnect.crosscutting.utils.Messages.CentroInformaticaEquipoComputoPostgresqlDAOMessage;
import co.edu.uco.compuconnect.data.dao.CentroInformaticaEquipoComputoDAO;
import co.edu.uco.compuconnect.data.dao.relational.SqlDAO;
import co.edu.uco.compuconnect.entities.CentroInformaticaEquipoComputoEntity;


public final class CentroInformaticaEquipoComputoPostgresqlDAO extends SqlDAO<CentroInformaticaEquipoComputoEntity> implements CentroInformaticaEquipoComputoDAO {

   

    public CentroInformaticaEquipoComputoPostgresqlDAO(final Connection connection) {
    	super(connection);
    }

    @Override
    public void create(CentroInformaticaEquipoComputoEntity entity) {
        String sqlStatement = "INSERT INTO centro_informatica_equipo_computo (identificador, centroInformatica, equipoComputo) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = getConnection().prepareStatement(sqlStatement)) {
            statement.setObject(1, entity.getIdentificador());
            statement.setObject(2, entity.getCentroInformatica().getIdentificador());
            statement.setObject(3, entity.getEquipoComputo().getIdentificador());

            statement.executeUpdate();

        } catch (SQLException exception) {
           throw CompuconnectDataException.create(CentroInformaticaEquipoComputoPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_TECHNICAL_MESSAGE, CentroInformaticaEquipoComputoPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_USER_MESSAGE, exception);
        } catch(Exception exception) {
            throw CompuconnectDataException.create(CentroInformaticaEquipoComputoPostgresqlDAOMessage.CREATE_EXCEPTION_TECHNICAL_MESSAGE, CentroInformaticaEquipoComputoPostgresqlDAOMessage.CREATE_EXCEPTION_USER_MESSAGE, exception);
        }
    }

    @Override
    public List<CentroInformaticaEquipoComputoEntity> read(CentroInformaticaEquipoComputoEntity entity) {
        List<CentroInformaticaEquipoComputoEntity> centroList = new ArrayList<>();
        String sql = "SELECT identificador, centroInformatica, equipoComputo FROM CentroInformaticaEquipoComputo WHERE identificador = ?";

        return centroList;
    }

    @Override
    public void delete(CentroInformaticaEquipoComputoEntity entity) {
        String sqlStatement = "DELETE FROM centro_informatica_equipo_computo WHERE identificador = ?";

        try (PreparedStatement statement = getConnection().prepareStatement(sqlStatement)) {
            statement.setObject(1, entity.getIdentificador());

            statement.executeUpdate();

        } catch (SQLException exception) {
           throw CompuconnectDataException.create(CentroInformaticaEquipoComputoPostgresqlDAOMessage.DELETE_SQL_EXCEPTION_TECHNICAL_MESSAGE, CentroInformaticaEquipoComputoPostgresqlDAOMessage.DELETE_SQL_EXCEPTION_USER_MESSAGE, exception);
        } catch(Exception exception) {
            throw CompuconnectDataException.create(CentroInformaticaEquipoComputoPostgresqlDAOMessage.DELETE_EXCEPTION_TECHNICAL_MESSAGE, CentroInformaticaEquipoComputoPostgresqlDAOMessage.DELETE_EXCEPTION_USER_MESSAGE, exception);
        }
    }

	@Override
	protected String prepareSelect() {
		return "SELECT identificador, centroInformatica, equipoComputo ";
	}

	@Override
	protected String prepareFrom() {
		return "FROM centro_informatica_equipo_computo ";
	}

	@Override
	protected String prepareWhere(CentroInformaticaEquipoComputoEntity entity, List<Object> parameters) {
		
		final var where = new StringBuilder("");
		parameters = UtilObject.getDefault(parameters, new ArrayList<>());
		var setWhere = true;
		
		if(!UtilObject.isNull(entity)) {
			if(!UtilUUID.isDefault(entity.getIdentificador())) {
				parameters.add(entity.getIdentificador());
				where.append("WHERE identificador=? ");
				setWhere = false;
			}
			if(!UtilUUID.isDefault(entity.getCentroInformatica().getIdentificador())) {
				parameters.add(entity.getCentroInformatica().getIdentificador());
				where.append(setWhere ? "WHERE" : "AND ").append("centroInformatica=? ");
				setWhere = false;
			}
			if(!UtilUUID.isDefault(entity.getEquipoComputo().getIdentificador())) {
				parameters.add(entity.getEquipoComputo().getIdentificador());
				where.append(setWhere ? "WHERE" : "AND ").append("equipoComputo=? ");
			}
			
		
		}
		return where.toString();
		
	}

	@Override
	protected String prepareOrderBy() {
		return "ORDER BY ASC ";
	}

	@Override
	protected void setParameters(PreparedStatement prepareStat, List<Object> parameters) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected List<CentroInformaticaEquipoComputoEntity> executeQuery(PreparedStatement preparedStatement) {
		// TODO Auto-generated method stub
		return null;
	}
}