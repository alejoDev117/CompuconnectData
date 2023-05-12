package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.compuconnect.data.dao.TipoIdentificacionDAO;
import co.edu.uco.compuconnect.entities.TipoIdentificacionEntity;

public final class TipoIdentificacionPostgresqlDAO implements TipoIdentificacionDAO {

    private Connection connection;

    public TipoIdentificacionPostgresqlDAO(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(TipoIdentificacionEntity entity) {
        String query = "INSERT INTO tipo_identificacion (identificador, nombre, descripcion) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, entity.getIdentificador());
            statement.setString(2, entity.getNombre());
            statement.setString(3, entity.getDescripcion());

            statement.executeUpdate();
        } catch (SQLException e) {
            // Manejo de excepcion
        }
    }

    @Override
    public List<TipoIdentificacionEntity> read(TipoIdentificacionEntity entity) {
        List<TipoIdentificacionEntity> result = new ArrayList<>();
        String query = "SELECT * FROM tipo_identificacion";
        return result;
    }

}
