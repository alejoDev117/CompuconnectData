package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.compuconnect.data.dao.TipoReservaDAO;
import co.edu.uco.compuconnect.entities.TipoReservaEntity;

public final class TipoReservaPostgresqlDAO implements TipoReservaDAO {

    private Connection connection;

    public TipoReservaPostgresqlDAO(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(TipoReservaEntity entity) {
        String query = "INSERT INTO tipo_reserva (identificador, nombre, descripcion) VALUES (?, ?, ?)";
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
    public List<TipoReservaEntity> read(TipoReservaEntity entity) {
        List<TipoReservaEntity> result = new ArrayList<>();
        String query = "SELECT * FROM tipo_reserva";
        return result;
    }

}
