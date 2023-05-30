package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.compuconnect.crosscutting.exceptions.CompuconnectDataException;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;
import co.edu.uco.compuconnect.crosscutting.utils.UtilUUID;
import co.edu.uco.compuconnect.crosscutting.utils.Messages.PeriodoFuncionamientoDiaFestivoPostgresqlDAOMessage;
import co.edu.uco.compuconnect.data.dao.PeriodoFuncionamientoDiaFestivoDAO;
import co.edu.uco.compuconnect.data.dao.relational.SqlDAO;
import co.edu.uco.compuconnect.entities.PeriodoFuncionamientoDiaFestivoEntity;

public final class PeriodoFuncionamientoDiaFestivoPostgresqlDAO extends SqlDAO<PeriodoFuncionamientoDiaFestivoEntity> implements PeriodoFuncionamientoDiaFestivoDAO {

  

    public PeriodoFuncionamientoDiaFestivoPostgresqlDAO(final Connection connection) {
       super(connection);
    }

    @Override
    public void create(PeriodoFuncionamientoDiaFestivoEntity entity) {
        String sql = "INSERT INTO periodo_funcionamiento_dia_festivo (identificador, periodoFuncionamiento, diaFestivo) VALUES (?, ?, ?)";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setObject(1, entity.getIdentificador());
            statement.setObject(2, entity.getPeriodoFuncionamiento().getIdentificador());
            statement.setObject(3, entity.getDiaFestivo().getIdentificador());

            statement.executeUpdate();
        } catch (SQLException exception) {
            throw CompuconnectDataException.create(PeriodoFuncionamientoDiaFestivoPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_TECHNICAL_MESSAGE, PeriodoFuncionamientoDiaFestivoPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_USER_MESSAGE, exception);
        }catch (Exception exception) {
        	throw CompuconnectDataException.create(PeriodoFuncionamientoDiaFestivoPostgresqlDAOMessage.CREATE_EXCEPTION_TECHNICAL_MESSAGE, PeriodoFuncionamientoDiaFestivoPostgresqlDAOMessage.CREATE_EXCEPTION_USER_MESSAGE, exception);
        }
    }

    @Override
    public List<PeriodoFuncionamientoDiaFestivoEntity> read(PeriodoFuncionamientoDiaFestivoEntity entity) {
        List<PeriodoFuncionamientoDiaFestivoEntity> result = new ArrayList<>();
        return result;
    }


    @Override
    public void delete(PeriodoFuncionamientoDiaFestivoEntity entity) {
        String sql = "DELETE FROM periodo_funcionamiento_dia_festivo WHERE identificador = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setObject(1, entity.getIdentificador());

            statement.executeUpdate();
        } catch (SQLException exception) {
            throw CompuconnectDataException.create(PeriodoFuncionamientoDiaFestivoPostgresqlDAOMessage.DELETE_SQL_EXCEPTION_TECHNICAL_MESSAGE, PeriodoFuncionamientoDiaFestivoPostgresqlDAOMessage.DELETE_SQL_EXCEPTION_USER_MESSAGE, exception);
        }catch (Exception exception) {
        	throw CompuconnectDataException.create(PeriodoFuncionamientoDiaFestivoPostgresqlDAOMessage.DELETE_EXCEPTION_TECHNICAL_MESSAGE, PeriodoFuncionamientoDiaFestivoPostgresqlDAOMessage.DELETE_EXCEPTION_USER_MESSAGE, exception);
        }
    }

	@Override
	protected String prepareSelect() {
		return "SELECT identificador, periodoFuncionamiento, diaFestivo ";
	}

	@Override
	protected String prepareFrom() {
		return "FROM periodo_funiconamiento_dia_festivo ";
	}

	@Override
	protected String prepareWhere(PeriodoFuncionamientoDiaFestivoEntity entity, List<Object> parameters) {
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
			if(!UtilUUID.isDefault(entity.getDiaFestivo().getIdentificador())) {
				parameters.add(entity.getDiaFestivo().getIdentificador());
				where.append(setWhere ? "WHERE" : "AND ").append("diaFestivo =? ");
			}
			
		
		}
		return where.toString();
	
	}

	@Override
	protected String prepareOrderBy() {
		return "ORDER BY  ASC ";
	}

	@Override
	protected void setParameters(PreparedStatement prepareStat, List<Object> parameters) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected List<PeriodoFuncionamientoDiaFestivoEntity> executeQuery(PreparedStatement preparedStatement) {
		// TODO Auto-generated method stub
		return null;
	}
}