package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.compuconnect.data.dao.EstadoPeriodoFunicionamientoDAO;
import co.edu.uco.compuconnect.entities.EstadoNotificacionEntity;
import co.edu.uco.compuconnect.entities.EstadoPeriodoFuncionamientoEntity;

public final class EstadoPeriodoFuncionamientoPostgresqlDAO implements EstadoPeriodoFunicionamientoDAO {

    private final Connection connection;

    public EstadoPeriodoFuncionamientoPostgresqlDAO(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(EstadoPeriodoFuncionamientoEntity entity) {
        String query = "INSERT INTO estados_periodo_funcionamiento (identificador, nombre) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, entity.getIdentificador());
            statement.setString(2, entity.getNombre());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<EstadoPeriodoFuncionamientoEntity> read(EstadoPeriodoFuncionamientoEntity entity) {
    	 List<EstadoPeriodoFuncionamientoEntity> estadoList = new ArrayList<>();
	        return estadoList;
    }
}
