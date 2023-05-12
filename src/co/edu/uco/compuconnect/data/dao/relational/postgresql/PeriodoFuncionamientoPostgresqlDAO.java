package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.compuconnect.data.dao.PeriodoFuncionamientoDAO;
import co.edu.uco.compuconnect.entities.PeriodoFuncionamientoEntity;

public final class PeriodoFuncionamientoPostgresqlDAO implements PeriodoFuncionamientoDAO {

    private final Connection connection;

    public PeriodoFuncionamientoPostgresqlDAO(final Connection connection) {
        this.connection = connection;
    }

    public void create(PeriodoFuncionamientoEntity entity) {
        String sql = "INSERT INTO periodo_funcionamiento (identificador, nombre, fecha_inicio, fecha_fin, dia_festivo, estado) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, entity.getIdentificador());
            statement.setString(2, entity.getNombre());
            statement.setDate(3, new java.sql.Date(entity.getFechaInicio().getTime()));
            statement.setDate(4, new java.sql.Date(entity.getFechaFin().getTime()));
            statement.setObject(5, entity.getDiaFestivo());
            statement.setObject(6, entity.getEstado());

            statement.executeUpdate();
        } catch (SQLException e) {
        	//excepcion
        }
    }

    @Override
    public List<PeriodoFuncionamientoEntity> read(PeriodoFuncionamientoEntity entity) {
        List<PeriodoFuncionamientoEntity> result = new ArrayList<>();
        String sql = "SELECT identificador, nombre, fecha_inicio, fecha_fin, dia_festivo, estado FROM periodo_funcionamiento WHERE ...";
        return result;
    }

    public void update(PeriodoFuncionamientoEntity entity) {
        String sql = "UPDATE periodo_funcionamiento SET nombre = ?, fecha_inicio = ?, fecha_fin = ?, dia_festivo = ?, estado = ? WHERE identificador = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, entity.getNombre());
            statement.setDate(2, new java.sql.Date(entity.getFechaInicio().getTime()));
            statement.setDate(3, new java.sql.Date(entity.getFechaFin().getTime()));
            statement.setObject(4, entity.getDiaFestivo());
            statement.setObject(5, entity.getEstado());
            statement.setObject(6, entity.getIdentificador());

            statement.executeUpdate();
        } catch (SQLException e) {
            //excepcion
        }
    }
    public void delete(PeriodoFuncionamientoEntity entity) {
        String sql = "DELETE FROM periodo_funcionamiento WHERE identificador = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, entity.getIdentificador());

            statement.executeUpdate();
        } catch (SQLException e) {
            //excepcion
        }
    }
}
