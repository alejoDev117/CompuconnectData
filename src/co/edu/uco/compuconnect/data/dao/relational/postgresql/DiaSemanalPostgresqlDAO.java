package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.compuconnect.data.dao.DiaSemanalDAO;
import co.edu.uco.compuconnect.entities.DiaFestivoEntity;
import co.edu.uco.compuconnect.entities.DiaSemanalEntity;

public final class DiaSemanalPostgresqlDAO implements DiaSemanalDAO {

    private final Connection connection;

    public DiaSemanalPostgresqlDAO(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(DiaSemanalEntity entity) {
        String query = "INSERT INTO dia_semanal (identificador, nombre) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, entity.getIdentificador());
            statement.setString(2, entity.getNombre());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<DiaSemanalEntity> read(DiaSemanalEntity entity) {
        List<DiaSemanalEntity> diasSemanales = new ArrayList<>();
        String query = "SELECT identificador, nombre FROM dia_semanal";
        return diasSemanales;
    }
}
