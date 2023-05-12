package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.compuconnect.data.dao.DestinatarioNotificacionDAO;
import co.edu.uco.compuconnect.entities.DestinatarioNotificacionEntity;

public final class DestinatarioNotificacionPostgresqlDAO implements DestinatarioNotificacionDAO {

    private final Connection connection;

    public DestinatarioNotificacionPostgresqlDAO(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(DestinatarioNotificacionEntity entity) {
        String query = "INSERT INTO destinatarios_notificaciones (destinatario_id, notificacion_id, estado_notificacion_id) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, entity.getDestinatario().getIdentificador());
            statement.setObject(2, entity.getNotificacion().getIdentificador());
            statement.setObject(3, entity.getEstado().getIdentificador());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<DestinatarioNotificacionEntity> read(DestinatarioNotificacionEntity entity) {
        List<DestinatarioNotificacionEntity> destinatariosNotificaciones = new ArrayList<>();
        String query = "SELECT id, destinatario_id, notificacion_id, estado_notificacion_id FROM destinatarios_notificaciones";

        return destinatariosNotificaciones;
    }

    @Override
    public void delete(DestinatarioNotificacionEntity entity) {
        String query = "DELETE FROM destinatarios_notificaciones WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, entity.getIdentificador());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}