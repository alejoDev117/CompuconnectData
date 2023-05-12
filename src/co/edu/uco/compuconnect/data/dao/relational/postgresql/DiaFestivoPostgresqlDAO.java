package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.compuconnect.data.dao.DiaFestivoDAO;
import co.edu.uco.compuconnect.entities.DiaFestivoEntity;

public final class DiaFestivoPostgresqlDAO implements DiaFestivoDAO {

    private final Connection connection;

    public DiaFestivoPostgresqlDAO(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(DiaFestivoEntity entity) {
        String query = "INSERT INTO dias_festivos (identificador, nombre, fecha) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, entity.getIdentificador());
            statement.setString(2, entity.getNombre());
            statement.setObject(3, entity.getFecha());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<DiaFestivoEntity> read(DiaFestivoEntity entity) {
        List<DiaFestivoEntity> diasFestivos = new ArrayList<>();
        String query = "SELECT identificador, nombre, fecha FROM dias_festivos";
        return diasFestivos;
    }
}
