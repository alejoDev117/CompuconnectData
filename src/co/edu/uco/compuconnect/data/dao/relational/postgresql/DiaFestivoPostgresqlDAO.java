package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.compuconnect.crosscutting.utils.UtilDateTime;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;
import co.edu.uco.compuconnect.crosscutting.utils.UtilText;
import co.edu.uco.compuconnect.crosscutting.utils.UtilUUID;
import co.edu.uco.compuconnect.data.dao.DiaFestivoDAO;
import co.edu.uco.compuconnect.data.dao.relational.SqlDAO;
import co.edu.uco.compuconnect.entities.DiaFestivoEntity;

public final class DiaFestivoPostgresqlDAO extends SqlDAO<DiaFestivoEntity> implements DiaFestivoDAO {

  
    public DiaFestivoPostgresqlDAO(final Connection connection) {
      super(connection);
    }

    @Override
    public void create(DiaFestivoEntity entity) {
        String query = "INSERT INTO dia_festivo (identificador, nombre, fecha) VALUES (?, ?, ?)";

        try (PreparedStatement statement = getConnection().prepareStatement(query)) {
            statement.setObject(1, entity.getIdentificador());
            statement.setString(2, entity.getNombre());
            statement.setObject(3, entity.getFecha());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<DiaFestivoEntity> read(DiaFestivoEntity entity) {
        List<DiaFestivoEntity> diasFestivos = new ArrayList<>();
        String query = "SELECT identificador, nombre, fecha FROM dias_festivos";
        return diasFestivos;
    }

	@Override
	protected String prepareSelect() {
		return "SELECT identificador, nombre, fecha ";
	}

	@Override
	protected String prepareFrom() {
		return "FROM dia_festivo ";
	}

	@Override
	protected String prepareWhere(DiaFestivoEntity entity, List<Object> parameters) {
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
				where.append(setWhere ? "WHERE" : "AND ").append("nombre=? ");
				setWhere = false;
			}
			if(!UtilDateTime.isDefaultDate(entity.getFecha())) {
				parameters.add(entity.getFecha());
				where.append(setWhere ? "WHERE" : "AND ").append("centroInformatica=? ");
			}
			
		
		}
		return where.toString();
	}

	@Override
	protected String prepareOrderBy() {
		return "ORDER BY nombre ASC ";
	}
}
