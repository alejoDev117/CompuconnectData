package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.compuconnect.crosscutting.exceptions.CompuconnectDataException;
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
        var sqlStament = "INSERT INTO \"CentroInformatica\" (identificador, nombre, ubicacion, \"poseeVideoBeam\") VALUES (?, ?, ?, ?)";

        try (var preparedStament = getConnection().prepareStatement(sqlStament)) {
            preparedStament.setObject(1, entity.getIdentificador());
            preparedStament.setString(2, entity.getNombre());
            preparedStament.setString(3, entity.getUbicacion());
            preparedStament.setBoolean(4, entity.isPoseeVideoBeam());

            preparedStament.executeUpdate();

        }catch (final SQLException exception) {
			var userMessage = "se ha presentado un error tratando de registrar la informacion de un nuevo centro informatica....";
			var technicalMessage ="Se ha presentado un problema de tipo SQLException dentro del metodo create de la clase CentroInformaticaPostgresqlDAO, Por favor verifique la traza completa del error";
			
			throw CompuconnectDataException.create(technicalMessage, userMessage, exception);
		}catch (final Exception exception) {
			var userMessage = "Se ha presentado un problema inesperado tratando de registrar la informacion del nuevo estado tipo relacion institucion";
			var technicalMessage ="Se ha presentado un problema inesperado dentro del metodo create de la clase EstadoTipoRelacionInstitucionSqlServerDAO, Por favor verifique la traza completa del error";
			throw CompuconnectDataException.create(technicalMessage, userMessage, exception);
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
        var sqlStament = "UPDATE \"CentroInformatica\" SET nombre = ?, ubicacion = ?, \"poseeVideoBeam\" = ? WHERE identificador = ?";

        try (var preparedStament = getConnection().prepareStatement(sqlStament)) {
        	preparedStament.setString(1, entity.getNombre());
        	preparedStament.setString(2, entity.getUbicacion());
        	preparedStament.setBoolean(3, entity.isPoseeVideoBeam());
        	preparedStament.setObject(4, entity.getIdentificador());

            preparedStament.executeUpdate();
        }catch (final SQLException exception) {
			var userMessage = "Se ha presentado un problema tratando de modificar la informacion del nuevo estado tipo relacion institucion";
			var technicalMessage ="Se ha presentado un problema de tipo SQLException dentro del metodo update de la clase CentroInformatica, Por favor verifique la traza completa del error";
			
			throw CompuconnectDataException.create(technicalMessage, userMessage, exception);
		}catch (final Exception exception) {
			var userMessage = "Se ha presentado un problema inesperado tratando de mofificar la informacion del nuevo estado tipo relacion institucion";
			var technicalMessage ="Se ha presentado un problema inesperado dentro del metodo update de la clase CentroInformatica, Por favor verifique la traza completa del error";
			throw CompuconnectDataException.create(technicalMessage, userMessage, exception);
		}
    }

    @Override
    public void delete(CentroInformaticaEntity entity) {
    	var sqlStament = "DELETE FROM EstadoTipoRelacionInstitucion WHERE identificador=?)";

		try (var preparedStatement = getConnection().prepareStatement(sqlStament)) {
			preparedStatement.setObject(1, entity.getIdentificador());
			
			preparedStatement.executeUpdate();
		}catch (final SQLException exception) {
			var userMessage = "Se ha presentado un problema tratando de eliminar la informacion del nuevo estado tipo relacion institucion";
			var technicalMessage ="Se ha presentado un problema de tipo SQLException dentro del metodo delete de la clase EstadoTipoRelacionInstitucionSqlServerDAO, Por favor verifique la traza completa del error";
			
			throw CompuconnectDataException.create(technicalMessage, userMessage, exception);
		}catch (final Exception exception) {
			var userMessage = "Se ha presentado un problema inesperado tratando de eliminar la informacion del nuevo estado tipo relacion institucion";
			var technicalMessage ="Se ha presentado un problema inesperado dentro del metodo delete de la clase EstadoTipoRelacionInstitucionSqlServerDAO, Por favor verifique la traza completa del error";
			throw CompuconnectDataException.create(technicalMessage, userMessage, exception);
		}
    }

	@Override
	protected String prepareSelect() {
		return "SELECT identificador, nombre, ubicacion, poseeVideoBeam ";
	}

	@Override
	protected String prepareFrom() {
		return "FROM CentroInformatica";
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