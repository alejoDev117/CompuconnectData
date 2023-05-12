package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.compuconnect.data.dao.EstadoSolicitudDAO;
import co.edu.uco.compuconnect.entities.EstadoPeriodoFuncionamientoEntity;
import co.edu.uco.compuconnect.entities.EstadoSolicitudEntity;

public final class EstadoSolicitudPostgresqlDAO implements EstadoSolicitudDAO {

    private final Connection connection;

    public EstadoSolicitudPostgresqlDAO(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(EstadoSolicitudEntity entity) {
        String query = "INSERT INTO estados_solicitud (identificador, nombre) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, entity.getIdentificador());
            statement.setString(2, entity.getNombre());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<EstadoSolicitudEntity> read(EstadoSolicitudEntity entity) {
   	 List<EstadoSolicitudEntity> estadoList = new ArrayList<>();
   	 return estadoList;
    }
}
 