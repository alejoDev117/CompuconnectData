package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.compuconnect.data.dao.PerfilDAO;
import co.edu.uco.compuconnect.entities.PerfilEntity;

public final class PerfilPostgresqlDAO implements PerfilDAO {

    private final Connection connection;

    public PerfilPostgresqlDAO(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(PerfilEntity entity) {
        String sql = "INSERT INTO perfiles (identificador, usuario) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, entity.getIdentificador());
            statement.setObject(2, entity.getUsuario());

            statement.executeUpdate();
        } catch (SQLException e) {
            //excepcion
        }
    }

    public List<PerfilEntity> read(PerfilEntity entity) {
        List<PerfilEntity> result = new ArrayList<>();
        String sql = "SELECT identificador, usuario FROM perfiles WHERE ...";
       
        return result;
    }

    @Override
    public void delete(PerfilEntity entity) {
        String sql = "DELETE FROM perfiles WHERE identificador = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, entity.getIdentificador());

            statement.executeUpdate();
        } catch (SQLException e) {
            //excepcion
        }
    }

    public void update(PerfilEntity entity) {
        String sql = "UPDATE perfiles SET usuario = ? WHERE identificador = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, entity.getUsuario());
            statement.setObject(2, entity.getIdentificador());

            statement.executeUpdate();
        } catch (SQLException e) {
            //excepcion
        }
    }
}
