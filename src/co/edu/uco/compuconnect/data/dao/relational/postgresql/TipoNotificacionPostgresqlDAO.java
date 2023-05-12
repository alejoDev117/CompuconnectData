package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.compuconnect.data.dao.TipoNotificacionDAO;
import co.edu.uco.compuconnect.entities.TipoNotificacionEntity;

public final class TipoNotificacionPostgresqlDAO implements TipoNotificacionDAO {

    private Connection connection;

    public TipoNotificacionPostgresqlDAO(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(TipoNotificacionEntity entity) {
        String query = "INSERT INTO tipo_notificacion (identificador, nombre, descripcion) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, entity.getIdentificador());
            statement.setString(2, entity.getNombre());
            statement.setString(3, entity.getDescripcion());

            statement.executeUpdate();
        } catch (SQLException e) {
            //excepcion
        }
    }

    @Override
    public List<TipoNotificacionEntity> read(TipoNotificacionEntity entity) {
        List<TipoNotificacionEntity> result = new ArrayList<>();
        String query = "SELECT * FROM tipo_notificacion";
        return result;
    }

}