package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.compuconnect.data.dao.SoftwareEquipoComputoDAO;
import co.edu.uco.compuconnect.entities.SoftwareEquipoComputoEntity;

public final class SoftwareEquipoComputoPostgresqlDAO implements SoftwareEquipoComputoDAO {
    private final Connection connection;

    public SoftwareEquipoComputoPostgresqlDAO(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(SoftwareEquipoComputoEntity entity) {
        String query = "INSERT INTO software_equipo (identificador, software, equipo_computo) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, entity.getIdentificador());
            statement.setObject(2, entity.getSoftware());
            statement.setObject(3, entity.getEquipoComputo());

            statement.executeUpdate();
        } catch (SQLException e) {
            // Manejo de excepciones
        }
    }

    @Override
    public List<SoftwareEquipoComputoEntity> read(SoftwareEquipoComputoEntity entity) {
        List<SoftwareEquipoComputoEntity> softwareEquipo = new ArrayList<>();
        return softwareEquipo;
    }

    @Override
    public void delete(SoftwareEquipoComputoEntity entity) {
        String query = "DELETE FROM software_equipo WHERE identificador = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, entity.getIdentificador());

            statement.executeUpdate();
        } catch (SQLException e) {
            //excepcion
        }
    }
}