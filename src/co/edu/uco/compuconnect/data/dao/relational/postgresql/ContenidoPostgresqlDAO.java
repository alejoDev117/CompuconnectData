package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.compuconnect.data.dao.ContenidoDAO;
import co.edu.uco.compuconnect.entities.ContenidoEntity;

public class ContenidoPostgresqlDAO implements ContenidoDAO {

    private final Connection connection;

    public ContenidoPostgresqlDAO(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(ContenidoEntity entity) {
        String sql = "INSERT INTO Contenido (identificador, descripcion) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, entity.getIdentificador());
            statement.setString(2, entity.getDescripcion());

            statement.executeUpdate();
        } catch (SQLException e) {
            //excepcion
        }
    }

    @Override
    public List<ContenidoEntity> read(ContenidoEntity entity) {
        List<ContenidoEntity> contenidoList = new ArrayList<>();
        String sql = "SELECT identificador, descripcion FROM Contenido WHERE identificador = ?";

        return contenidoList;
    }

    @Override
    public void delete(ContenidoEntity entity) {
        String sql = "DELETE FROM Contenido WHERE identificador = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, entity.getIdentificador());

            statement.executeUpdate();
        } catch (SQLException e) {
            //excepcion
        }
    }
}
