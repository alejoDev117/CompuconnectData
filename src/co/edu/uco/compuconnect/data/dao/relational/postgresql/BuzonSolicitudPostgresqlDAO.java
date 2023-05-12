package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.compuconnect.data.dao.BuzonSolicitudDAO;
import co.edu.uco.compuconnect.entities.AgendaReservaEntity;
import co.edu.uco.compuconnect.entities.BuzonSolicitudEntity;

public final class BuzonSolicitudPostgresqlDAO implements BuzonSolicitudDAO {

    private final Connection connection;

    public BuzonSolicitudPostgresqlDAO(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(BuzonSolicitudEntity entity) {
        String sql = "INSERT INTO BuzonSolicitud (identificador, solicitud, respuesta) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, entity.getIdentificador());
            statement.setObject(2, entity.getSolicitud());
            statement.setObject(3, entity.getRespuesta());

            statement.executeUpdate();
        } catch (SQLException e) {
            //excepción
        }
    }

    @Override
    public List<BuzonSolicitudEntity> read(BuzonSolicitudEntity entity) {
        List<BuzonSolicitudEntity> buzonList = new ArrayList<>();
        String sql = "SELECT campo1, campo2, campo3 FROM BuzonSolicitud WHERE campo1 = ?";
        return buzonList;
    }

    @Override
    public void update(BuzonSolicitudEntity entity) {
        String sql = "UPDATE BuzonSolicitud SET respuesta = ? WHERE solicitud = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(2, entity.getRespuesta());
            statement.setObject(3, entity.getSolicitud());

            statement.executeUpdate();
        } catch (SQLException e) {
            //excepción
        }
    }

    @Override
    public void delete(BuzonSolicitudEntity entity) {
        String sql = "DELETE FROM BuzonSolicitud WHERE campo1 = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, entity.getIdentificador());

            statement.executeUpdate();
        } catch (SQLException e) {
            //excepción
        }
    }
}