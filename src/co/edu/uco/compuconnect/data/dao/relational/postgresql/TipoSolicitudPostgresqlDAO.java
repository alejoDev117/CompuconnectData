package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.compuconnect.data.dao.TipoSolicitudDAO;
import co.edu.uco.compuconnect.entities.TipoSolicitudEntity;

public final class TipoSolicitudPostgresqlDAO implements TipoSolicitudDAO {

    private Connection connection;

    public TipoSolicitudPostgresqlDAO(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(TipoSolicitudEntity entity) {
        String query = "INSERT INTO tipo_solicitud (identificador, nombre, descripcion) VALUES (?, ?, ?)";
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
    public List<TipoSolicitudEntity> read(TipoSolicitudEntity entity) {
        List<TipoSolicitudEntity> result = new ArrayList<>();
        String query = "SELECT * FROM tipo_solicitud";
        
        return result;
    }

}