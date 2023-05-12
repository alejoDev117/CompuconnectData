package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.compuconnect.data.dao.AgendaReservaDAO;
import co.edu.uco.compuconnect.entities.AgendaReservaEntity;

public final class AgendaReservaPostgresqlDAO implements AgendaReservaDAO {

    private final Connection connection;

    public AgendaReservaPostgresqlDAO(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public final void create(final AgendaReservaEntity entity) {
        String sql = "INSERT INTO AgendaReserva (identificador, agenda, reserva) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, entity.getIdentificador());
            statement.setObject(2, entity.getAgenda());
            statement.setObject(3, entity.getReserva());

            statement.executeUpdate();
        } catch (SQLException e) {
            //excepción
        }
    }

    @Override
    public final List<AgendaReservaEntity> read(final AgendaReservaEntity entity) {
        List<AgendaReservaEntity> agendaList = new ArrayList<>();
        String sql = "SELECT campo1, campo2, campo3 FROM AgendaReserva WHERE campo1 = ?";

        return agendaList;
    }

    @Override
    public final void delete(final AgendaReservaEntity entity) {
        String sql = "DELETE FROM AgendaReserva WHERE identificador = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, entity.getIdentificador());

            statement.executeUpdate();
        } catch (SQLException e) {
            //excepción
        }
    }
}