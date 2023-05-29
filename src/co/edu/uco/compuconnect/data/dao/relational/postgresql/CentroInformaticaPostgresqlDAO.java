package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.compuconnect.crosscutting.exceptions.CompuconnectDataException;
import co.edu.uco.compuconnect.crosscutting.utils.Messages.CentroInformaticaPostgresqlDAOMessage;
import co.edu.uco.compuconnect.crosscutting.utils.UtilBoolean;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;
import co.edu.uco.compuconnect.crosscutting.utils.UtilText;
import co.edu.uco.compuconnect.crosscutting.utils.UtilUUID;
import co.edu.uco.compuconnect.data.dao.CentroInformaticaDAO;
import co.edu.uco.compuconnect.data.dao.relational.SqlDAO;
import co.edu.uco.compuconnect.entities.CentroInformaticaEntity;

public final class CentroInformaticaPostgresqlDAO extends SqlDAO<CentroInformaticaEntity> implements CentroInformaticaDAO {

    public CentroInformaticaPostgresqlDAO(final Connection connection) {
        super(connection);
    }

    @Override
    public void create(CentroInformaticaEntity entity) {
        var sqlStament = "INSERT INTO \"centro_informatica\" (identificador, nombre, ubicacion, \"poseeVideoBeam\") VALUES (?, ?, ?, ?)";

        try (var preparedStament = getConnection().prepareStatement(sqlStament)) {
            preparedStament.setObject(1, entity.getIdentificador());
            preparedStament.setString(2, entity.getNombre());
            preparedStament.setString(3, entity.getUbicacion());
            preparedStament.setBoolean(4, entity.isPoseeVideoBeam());

            preparedStament.executeUpdate();

        }catch (final SQLException exception) {
		
        	throw CompuconnectDataException.create(CentroInformaticaPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_TECHNICAL_MESSAGE, CentroInformaticaPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_USER_MESSAGE, exception);
		}catch (final Exception exception) {
			throw CompuconnectDataException.create(CentroInformaticaPostgresqlDAOMessage.CREATE_EXCEPTION_TECHNICAL_MESSAGE, CentroInformaticaPostgresqlDAOMessage.CREATE_EXCEPTION_USER_MESSAGE, exception);
		}
    }

    @Override
    public List<CentroInformaticaEntity> read(CentroInformaticaEntity entity) {
        var sqlStatement = new StringBuilder();
        var parameters = new ArrayList<Object>();
        List<CentroInformaticaEntity> entities = new ArrayList<>();

        sqlStatement.append(prepareSelect());
        sqlStatement.append(prepareFrom());
        sqlStatement.append(prepareWhere(entity, parameters));
        sqlStatement.append(prepareOrderBy());

        return entities;
    }


    @Override
    public void update(CentroInformaticaEntity entity) {
        var sqlStament = "UPDATE \"centro_informatica\" SET nombre = ?, ubicacion = ?, \"poseeVideoBeam\" = ? WHERE identificador = ?";

        try (var preparedStament = getConnection().prepareStatement(sqlStament)) {
        	preparedStament.setString(1, entity.getNombre());
        	preparedStament.setString(2, entity.getUbicacion());
        	preparedStament.setBoolean(3, entity.isPoseeVideoBeam());
        	preparedStament.setObject(4, entity.getIdentificador());

            preparedStament.executeUpdate();
        }catch (final SQLException exception) {
			
			throw CompuconnectDataException.create(CentroInformaticaPostgresqlDAOMessage.UPDATE_SQL_EXCEPTION_TECHNICAL_MESSAGE, CentroInformaticaPostgresqlDAOMessage.UPDATE_SQL_EXCEPTION_USER_MESSAGE, exception);
		}catch (final Exception exception) {	
			throw CompuconnectDataException.create(CentroInformaticaPostgresqlDAOMessage.UPDATE_EXCEPTION_TECHNICAL_MESSAGE, CentroInformaticaPostgresqlDAOMessage.UPDATE_EXCEPTION_USER_MESSAGE, exception);
		}
    }

    @Override
    public void delete(CentroInformaticaEntity entity) {
    	var sqlStament = "DELETE FROM centro_informatica WHERE identificador=?)";

		try (var preparedStatement = getConnection().prepareStatement(sqlStament)) {
			preparedStatement.setObject(1, entity.getIdentificador());
			
			preparedStatement.executeUpdate();
		}catch (final SQLException exception) {		
			throw CompuconnectDataException.create(CentroInformaticaPostgresqlDAOMessage.DELETE_SQL_EXCEPTION_TECHNICAL_MESSAGE, CentroInformaticaPostgresqlDAOMessage.DELETE_SQL_EXCEPTION_USER_MESSAGE, exception);
		}catch (final Exception exception) {
			throw CompuconnectDataException.create(CentroInformaticaPostgresqlDAOMessage.DELETE_EXCEPTION_TECHNICAL_MESSAGE, CentroInformaticaPostgresqlDAOMessage.DELETE_EXCEPTION_USER_MESSAGE, exception);
		}
    }

	@Override
	protected String prepareSelect() {
		return "SELECT identificador, nombre, ubicacion, poseeVideoBeam ";
	}

	@Override
	protected String prepareFrom() {
		return "FROM centro_informatica ";
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
}