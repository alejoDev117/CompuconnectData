package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.compuconnect.data.dao.RespuestaDAO;
import co.edu.uco.compuconnect.entities.RespuestaEntity;

public final class RespuestaPostgresqlDAO implements RespuestaDAO {
    private final Connection connection;

    public RespuestaPostgresqlDAO(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(RespuestaEntity entity) {
        String query = "INSERT INTO respuestas (identificador, autor, observacion, fecha) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, entity.getIdentificador());
            statement.setObject(2, entity.getAutor());
            statement.setString(3, entity.getObservacion());
            statement.setDate(4, new java.sql.Date(entity.getFecha().getTime()));

            statement.executeUpdate();
        } catch (SQLException e) {
            //excepcion
        }
    }

    @Override
    public List<RespuestaEntity> read(RespuestaEntity entity) {
        List<RespuestaEntity> respuestas = new ArrayList<>();

        return respuestas;
    }

    @Override
    public void delete(RespuestaEntity entity) {
        String query = "DELETE FROM respuestas WHERE identificador = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, entity.getIdentificador());

            statement.executeUpdate();
        } catch (SQLException e) {
        	//excepcion
        }
    }
}
