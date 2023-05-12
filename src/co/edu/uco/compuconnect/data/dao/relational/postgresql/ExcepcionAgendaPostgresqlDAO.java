package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.compuconnect.data.dao.ExcepcionAgendaDAO;
import co.edu.uco.compuconnect.entities.EstadoSolicitudEntity;
import co.edu.uco.compuconnect.entities.ExcepcionAgendaEntity;
import co.edu.uco.compuconnect.entities.ExcepcionEntity;

public final class ExcepcionAgendaPostgresqlDAO implements ExcepcionAgendaDAO {

    private final Connection connection;

    public ExcepcionAgendaPostgresqlDAO(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(ExcepcionAgendaEntity entity) {
        String query = "INSERT INTO excepciones_agenda (identificador, excepcion_id, agenda_id) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, entity.getIdentificador());
            statement.setObject(2, entity.getExcepcion().getIdentificador());
            statement.setObject(3, entity.getAgenda().getIdentificador());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ExcepcionAgendaEntity> read(ExcepcionAgendaEntity entity) {
    	 List<ExcepcionAgendaEntity> estadoList = new ArrayList<>();
       	 return estadoList;    
       	 }

    public void delete(ExcepcionAgendaEntity entity) {
        String sql = "DELETE FROM excepciones WHERE identificador = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, entity.getIdentificador());
            statement.executeUpdate();
        } catch (SQLException e) {
            //excepcion
        }
    }
}
