package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.compuconnect.data.dao.MonitorDAO;
import co.edu.uco.compuconnect.entities.MonitorEntity;

public final class MonitorPostgresqlDAO implements MonitorDAO {

    private final Connection connection;

    public MonitorPostgresqlDAO(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(MonitorEntity entity) {
        String sql = "INSERT INTO monitor (identificador, nombre, tipo_identificacion, numero_identificacion, correo_institucional, numero_celular) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            // Set the values for the PreparedStatement
            statement.setObject(1, entity.getIdentificador());
            statement.setString(2, entity.getNombre());
            statement.setObject(3, entity.getTipoIdentificacion());
            statement.setString(4, entity.getIdentificacion());
            statement.setString(5, entity.getCorreoInstitucional());
            statement.setString(6, entity.getNumeroCelular());
            statement.executeUpdate();
        } catch (SQLException e) {
            // excepcion
        }
    }

    @Override
    public List<MonitorEntity> read(MonitorEntity entity) {
        List<MonitorEntity> result = new ArrayList<>();
        String sql = "SELECT identificador, nombre, tipo_identificacion, numero_identificacion, correo_institucional, numero_celular FROM monitor WHERE ...";
        return result;
    }

    @Override
    public void update(MonitorEntity entity) {
        String sql = "UPDATE monitor SET nombre = ?, tipo_identificacion = ?, numero_identificacion = ?, correo_institucional = ?, numero_celular = ? WHERE identificador = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, entity.getNombre());
            statement.setObject(2, entity.getTipoIdentificacion());
            statement.setString(3, entity.getIdentificacion());
            statement.setString(4, entity.getCorreoInstitucional());
            statement.setString(5, entity.getNumeroCelular());
            statement.setObject(6, entity.getIdentificador());

            statement.executeUpdate();
        } catch (SQLException e) {
            //excepcion
        }
    }
    
    @Override
    public void delete(MonitorEntity entity) {
        String sql = "DELETE FROM monitor WHERE identificador = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, entity.getIdentificador());

            statement.executeUpdate();
        } catch (SQLException e) {
            //excepcion
        }
    }
}