package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.compuconnect.data.dao.TipoUsuarioDAO;
import co.edu.uco.compuconnect.entities.TipoUsuarioEntity;

public final class TipoUsuarioPostgresqlDAO implements TipoUsuarioDAO {

    private Connection connection;

    public TipoUsuarioPostgresqlDAO(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(TipoUsuarioEntity entity) {
        String query = "INSERT INTO tipo_usuario (identificador, nombre, descripcion) VALUES (?, ?, ?)";
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
    public List<TipoUsuarioEntity> read(TipoUsuarioEntity entity) {
        List<TipoUsuarioEntity> result = new ArrayList<>();
        String query = "SELECT * FROM tipo_usuario";
        return result;
    }
}