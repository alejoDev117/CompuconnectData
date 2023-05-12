package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.compuconnect.data.dao.FrecuenciaDAO;
import co.edu.uco.compuconnect.entities.FrecuenciaEntity;

public final class FrecuenciaPostgresqlDAO implements FrecuenciaDAO {

    private final Connection connection;

    public FrecuenciaPostgresqlDAO(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(FrecuenciaEntity entity) {
        String sql = "INSERT INTO frecuencias (identificador, nombre, descripcion) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, entity.getIdentificador());
            statement.setString(2, entity.getNombre());
            statement.setString(3, entity.getDescripcion());

            statement.executeUpdate();
        } catch (SQLException e) {
            //excepcion
        }
    }

    @Override
    public List<FrecuenciaEntity> read(FrecuenciaEntity entity) {
        List<FrecuenciaEntity> result = new ArrayList<>();
        return result;
    }
}
