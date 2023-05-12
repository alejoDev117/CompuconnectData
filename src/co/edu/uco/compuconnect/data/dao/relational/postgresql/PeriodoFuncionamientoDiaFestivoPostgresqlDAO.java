package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.compuconnect.data.dao.PeriodoFuncionamientoDiaFestivoDAO;
import co.edu.uco.compuconnect.entities.PeriodoFuncionamientoDiaFestivoEntity;

public final class PeriodoFuncionamientoDiaFestivoPostgresqlDAO implements PeriodoFuncionamientoDiaFestivoDAO {

    private final Connection connection;

    public PeriodoFuncionamientoDiaFestivoPostgresqlDAO(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(PeriodoFuncionamientoDiaFestivoEntity entity) {
        String sql = "INSERT INTO periodo_funcionamiento_dia_festivo (identificador, periodo_funcionamiento, dia_festivo) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, entity.getIdentificador());
            statement.setObject(2, entity.getPeriodoFuncionamiento());
            statement.setObject(3, entity.getDiaFestivo());

            statement.executeUpdate();
        } catch (SQLException e) {
            //excepcion
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
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, entity.getIdentificador());

            statement.executeUpdate();
        } catch (SQLException e) {
            //excepcion
        }
    }
}