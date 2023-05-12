package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.compuconnect.data.dao.HorarioPersonaEncargadaDAO;
import co.edu.uco.compuconnect.entities.HorarioPersonaEncargadaEntity;

public final class HorarioPersonaEncargadaPostgresqlDAO implements HorarioPersonaEncargadaDAO {

    private final Connection connection;

    public HorarioPersonaEncargadaPostgresqlDAO(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(HorarioPersonaEncargadaEntity entity) {
        String sql = "INSERT INTO horarios_persona_encargada (identificador, tiempo_funcionamiento, persona_encargada, hora_inicio, hora_fin) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, entity.getIdentificador());
            statement.setObject(2, entity.getTiempoFuncionamiento());
            statement.setObject(3, entity.getPersonaEncargada());
            statement.setObject(4, entity.getHoraInicio());
            statement.setObject(5, entity.getHoraFin());

            statement.executeUpdate();
        } catch (SQLException e) {
            //excepcion
        }
    }

    @Override
    public List<HorarioPersonaEncargadaEntity> read(HorarioPersonaEncargadaEntity entity) {
        List<HorarioPersonaEncargadaEntity> result = new ArrayList<>();
        return result;
    }

    @Override
    public void update(HorarioPersonaEncargadaEntity entity) {
        String sql = "UPDATE horarios_persona_encargada SET tiempo_funcionamiento = ?, persona_encargada = ?, hora_inicio = ?, hora_fin = ? WHERE identificador = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            // Set the new values for the PreparedStatement
            statement.setObject(1, entity.getTiempoFuncionamiento());
            statement.setObject(2, entity.getPersonaEncargada());
            statement.setObject(3, entity.getHoraInicio());
            statement.setObject(4, entity.getHoraFin());
            statement.setObject(5, entity.getIdentificador());

            statement.executeUpdate();
        } catch (SQLException e) {
            //excepcion
        }
    }
    @Override
    public void delete(HorarioPersonaEncargadaEntity entity) {
        String sql = "DELETE FROM horarios_persona_encargada WHERE identificador = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, entity.getIdentificador());

            statement.executeUpdate();
        } catch (SQLException e) {
            //excepcion
        }
    }
}