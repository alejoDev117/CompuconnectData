package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import co.edu.uco.compuconnect.data.dao.DestinatarioDAO;
import co.edu.uco.compuconnect.entities.DestinatarioEntity;

public class DestinatarioPostgresqlDAO implements DestinatarioDAO {

    private final Connection connection;

    public DestinatarioPostgresqlDAO(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public void generate(DestinatarioEntity entity) {
        String query = "INSERT INTO destinatarios (identificador, nombre, correo) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, entity.getIdentificador());
            statement.setString(2, entity.getNombre());
            statement.setString(3, entity.getCorreoInstitucional());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<DestinatarioEntity> read(DestinatarioEntity entity) {
        List<DestinatarioEntity> destinatarios = new ArrayList<>();
        String query = "SELECT identificador, nombre, correo FROM destinatarios";

        return destinatarios;
    }

    @Override
    public void update(DestinatarioEntity entity) {
        String query = "UPDATE destinatarios SET nombre = ?, correo = ? WHERE identificador = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, entity.getNombre());
            statement.setString(2, entity.getCorreoInstitucional());
            statement.setObject(3, entity.getIdentificador());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(DestinatarioEntity entity) {
        String query = "DELETE FROM destinatarios WHERE identificador = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, entity.getIdentificador());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}