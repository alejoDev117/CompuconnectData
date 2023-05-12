package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.compuconnect.data.dao.CoordinadorDAO;
import co.edu.uco.compuconnect.entities.CoordinadorEntity;

public final class CoordinadorPostgresqlDAO implements CoordinadorDAO {

    private final Connection connection;

    public CoordinadorPostgresqlDAO(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(CoordinadorEntity entity) {
        String query = "INSERT INTO coordinadores (identificador, nombre, tipo_identificacion, numero_identificacion, correo_institucional, numero_celular) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, entity.getIdentificador());
            statement.setString(2, entity.getNombre());
            statement.setObject(3, entity.getTipoIdentificacion());
            statement.setString(4, entity.getIdentificacion());
            statement.setString(5, entity.getCorreoInstitucional());
            statement.setString(6, entity.getNumeroCelular());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<CoordinadorEntity> read(CoordinadorEntity entity) {
        List<CoordinadorEntity> coordinadores = new ArrayList<>();
        String query = "SELECT identificador, nombre, tipo_identificacion, numero_identificacion, correo_institucional, numero_celular FROM coordinadores";
        return coordinadores;
    }

    @Override
    public void update(CoordinadorEntity entity) {
        String query = "UPDATE coordinadores SET nombre = ?, tipo_identificacion = ?, numero_identificacion = ?, correo_institucional = ?, numero_celular = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, entity.getNombre());
            statement.setObject(2, entity.getTipoIdentificacion());
            statement.setString(3, entity.getIdentificacion());
            statement.setString(4, entity.getCorreoInstitucional());
            statement.setString(5, entity.getNumeroCelular());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(CoordinadorEntity entity) {
        String query = "DELETE FROM coordinadores WHERE identificador = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, entity.getIdentificador());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
