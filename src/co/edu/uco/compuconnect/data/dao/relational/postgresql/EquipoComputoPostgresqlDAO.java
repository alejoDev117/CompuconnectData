package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.compuconnect.data.dao.EquipoComputoDAO;
import co.edu.uco.compuconnect.entities.DiaSemanalEntity;
import co.edu.uco.compuconnect.entities.EquipoComputoEntity;

public final class EquipoComputoPostgresqlDAO implements EquipoComputoDAO {

    private final Connection connection;

    public EquipoComputoPostgresqlDAO(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(EquipoComputoEntity entity) {
        String query = "INSERT INTO equipos_computo (identificador, nombre, estado) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, entity.getIdentificador());
            statement.setString(2, entity.getNombre());
            statement.setObject(3, entity.getEstado());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<EquipoComputoEntity> read(EquipoComputoEntity entity) {
    	List<EquipoComputoEntity> equipos = new ArrayList<>();
        String query = "SELECT identificador, nombre, estado FROM EquipoComputo";
        return equipos;
    }

    @Override
    public void update(EquipoComputoEntity entity) {
        String query = "UPDATE equipos_computo SET nombre = ?, estado = ? WHERE identificador = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, entity.getNombre());
            statement.setObject(2, entity.getEstado());
            statement.setObject(3, entity.getIdentificador());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(EquipoComputoEntity entity) {
        String query = "DELETE FROM equipos_computo WHERE identificador = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, entity.getIdentificador());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
