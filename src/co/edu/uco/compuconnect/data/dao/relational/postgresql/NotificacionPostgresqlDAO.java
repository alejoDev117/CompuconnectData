package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.compuconnect.data.dao.NotificacionDAO;
import co.edu.uco.compuconnect.entities.NotificacionEntity;

public final class NotificacionPostgresqlDAO implements NotificacionDAO {

    private final Connection connection;

    public NotificacionPostgresqlDAO(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(NotificacionEntity entity) {
        String sql = "INSERT INTO notificaciones (identificador, contenido, fecha, tipo_notificacion) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            // Set the values for the PreparedStatement
            statement.setObject(1, entity.getIdentificador());
            statement.setObject(2, entity.getContenido());
            statement.setDate(3, new java.sql.Date(entity.getFecha().getTime()));
            statement.setObject(4, entity.getTipo());

            statement.executeUpdate();
        } catch (SQLException e) {
            //excepcion
        }
    }

    public List<NotificacionEntity> read(NotificacionEntity entity) {
        List<NotificacionEntity> result = new ArrayList<>();
        String sql = "SELECT identificador, contenido, fecha, tipo_notificacion FROM notificaciones WHERE ...";
 
        return result;
    }

    @Override
    public void delete(NotificacionEntity entity) {
        String sql = "DELETE FROM notificaciones WHERE identificador = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, entity.getIdentificador());

            statement.executeUpdate();
        } catch (SQLException e) {
            // excepcion
        }
    }
}