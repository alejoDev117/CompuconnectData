package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.compuconnect.data.dao.SoftwareDAO;
import co.edu.uco.compuconnect.entities.SoftwareEntity;

public final class SoftwarePostgresqlDAO implements SoftwareDAO {
    private final Connection connection;

    public SoftwarePostgresqlDAO(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(SoftwareEntity entity) {
        String query = "INSERT INTO software (identificador, nombre, version) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, entity.getIdentidicador());
            statement.setString(2, entity.getNombre());
            statement.setString(3, entity.getVersion());

            statement.executeUpdate();
        } catch (SQLException e) {
            //excepcion
        }
    }

    @Override
    public List<SoftwareEntity> read(SoftwareEntity entity) {
        List<SoftwareEntity> softwareList = new ArrayList<>();
        String query = "SELECT identificador, nombre, version FROM software WHERE identificador = ?";

        return softwareList;
    }

    @Override
    public void update(SoftwareEntity entity) {
        String query = "UPDATE software SET nombre = ?, version = ? WHERE identificador = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, entity.getNombre());
            statement.setString(2, entity.getVersion());
            statement.setObject(3, entity.getIdentidicador());

            statement.executeUpdate();
        } catch (SQLException e) {
            //excepcion
        }
    }

    @Override
    public void delete(SoftwareEntity entity) {
        String query = "DELETE FROM software WHERE identificador = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, entity.getIdentidicador());

            statement.executeUpdate();
        } catch (SQLException e) {
            //excepcion
        }
    }
}
