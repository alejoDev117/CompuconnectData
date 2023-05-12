package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.compuconnect.data.dao.DetalleReservaDAO;
import co.edu.uco.compuconnect.entities.DetalleReservaEntity;

public final class DetalleReservaPostgresqlDAO implements DetalleReservaDAO {

    private final Connection connection;

    public DetalleReservaPostgresqlDAO(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(DetalleReservaEntity entity) {
        String query = "INSERT INTO detalle_reservas (identificador, reserva_id, dia_semanal_id, hora_inicio, hora_fin) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, entity.getIdentificador());
            statement.setObject(2, entity.getReserva().getIdentificador());
            statement.setObject(3, entity.getDia().getIdentificador());
            statement.setObject(4, entity.getHorainicio());
            statement.setObject(5, entity.getHorafin());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<DetalleReservaEntity> read(DetalleReservaEntity entity) {
        List<DetalleReservaEntity> detalleReservas = new ArrayList<>();
        String query = "SELECT identificador, reserva_id, dia_semanal_id, hora_inicio, hora_fin FROM detalle_reservas";
        return detalleReservas;
    }

    @Override
    public void update(DetalleReservaEntity entity) {
        String query = "UPDATE detalle_reservas SET reserva_id = ?, dia_semanal_id = ?, hora_inicio = ?, hora_fin = ? WHERE identificador = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, entity.getReserva().getIdentificador());
            statement.setObject(2, entity.getDia().getIdentificador());
            statement.setObject(3, entity.getHorainicio());
            statement.setObject(4, entity.getHorafin());
            statement.setObject(5, entity.getIdentificador());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(DetalleReservaEntity entity) {
        String query = "DELETE FROM detalle_reservas WHERE identificador = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, entity.getIdentificador());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
