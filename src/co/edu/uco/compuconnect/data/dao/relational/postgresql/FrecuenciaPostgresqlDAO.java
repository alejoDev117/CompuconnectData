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
import co.edu.uco.compuconnect.data.dao.FrecuenciaDAO;
import co.edu.uco.compuconnect.data.dao.relational.SqlDAO;
import co.edu.uco.compuconnect.entities.CentroInformaticaEntity;
import co.edu.uco.compuconnect.entities.FrecuenciaEntity;

public final class FrecuenciaPostgresqlDAO extends SqlDAO<FrecuenciaEntity> implements FrecuenciaDAO {

    public FrecuenciaPostgresqlDAO(final Connection connection) {
        super(connection);
    }

    @Override
    public void create(FrecuenciaEntity entity) {
        var sqlStatement = "INSERT INTO Frecuencia (identificador, nombre, descripcion) VALUES (?, ?, ?)";

        try (var preparedStatement = getConnection().prepareStatement(sqlStatement)) {
            preparedStatement.setObject(1, entity.getIdentificador());
            preparedStatement.setString(2, entity.getNombre());
            preparedStatement.setString(3, entity.getDescripcion());

            preparedStatement.executeUpdate();

        } catch (final SQLException exception) {
            var mensajeUsuario = "Se ha producido un problema al intentar registrar la información de la nueva frecuencia";
            var mensajeTecnico = "Se ha producido un problema de tipo SQLException en el método create de la clase FrecuenciaPostgresqlDAO. Por favor, verifica la traza completa del error.";

            throw CompuconnectDataException.create(mensajeTecnico, mensajeUsuario, exception);
        } catch (final Exception exception) {
            var messageUser = "Se ha producido un problema inesperado al intentar registrar la información de la nueva frecuencia";
            var messageTecnh = "Se ha producido un problema inesperado en el método create de la clase FrecuenciaPostgresqlDAO. Por favor, verifica la traza completa del error.";
            throw CompuconnectDataException.create(messageUser, messageTecnh, exception);
        }
    }

    @Override
    public List<FrecuenciaEntity> read(FrecuenciaEntity entity) {
    	var sqlStatement = new StringBuilder();
		var parameters = new ArrayList<>();
		
		sqlStatement.append(prepareSelect());
		sqlStatement.append(prepareFrom());
		sqlStatement.append(prepareWhere(entity, parameters));
		sqlStatement.append(prepareOrderBy());
		
		try (var preparedStament = getConnection().prepareStatement(sqlStatement.toString())){
			
		} catch (SQLException exception) {
			
		} catch (Exception exception) {
			
		}
		
		return null;
    }
    
    @Override
	protected String prepareSelect() {
		return "SELECT identificador, nombre, descripcion ";
	}

	@Override
	protected String prepareFrom() {
		return "FROM Frecuencia";
	}

	@Override
	protected String prepareWhere(FrecuenciaEntity entity, List<Object> parameters) {
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
			if(!UtilText.getUtilText().isEmpty(entity.getDescripcion())) {
				parameters.add(entity.getDescripcion());
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