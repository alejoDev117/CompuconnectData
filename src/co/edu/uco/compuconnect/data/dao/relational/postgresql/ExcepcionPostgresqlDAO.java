package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.compuconnect.data.dao.ExcepcionAgendaDAO;
import co.edu.uco.compuconnect.data.dao.ExcepcionDAO;
import co.edu.uco.compuconnect.entities.ExcepcionAgendaEntity;
import co.edu.uco.compuconnect.entities.ExcepcionEntity;

public final class ExcepcionPostgresqlDAO implements ExcepcionDAO {

    private final Connection connection;

    public ExcepcionPostgresqlDAO(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(ExcepcionEntity entity) {
        String sql = "INSERT INTO excepciones (identificador, fecha_inicio, fecha_fin, hora_inicio, hora_fin, frecuencia, motivo) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, entity.getIdentificador());
            statement.setObject(2, entity.getFechaInicio());
            statement.setObject(3, entity.getFechaFin());
            statement.setObject(4, entity.getHoraInicio());
            statement.setObject(5, entity.getHoraFin());
            statement.setObject(6, entity.getFrecuencia());
            statement.setString(7, entity.getMotivo());

            statement.executeUpdate();
        } catch (SQLException e) {
        	//excepcion
        }

    }

    @Override
    public List<ExcepcionEntity> read(ExcepcionEntity entity) {
        List<ExcepcionEntity> result = new ArrayList<>();
        String sql = "SELECT identificador, fecha_inicio, fecha_fin, hora_inicio, hora_fin, frecuencia, motivo FROM excepciones WHERE ...";
        return result;
    }

    @Override
    public void update(ExcepcionEntity entity) {
        String sql = "UPDATE excepciones SET fecha_inicio = ?, fecha_fin = ?, hora_inicio = ?, hora_fin = ?, frecuencia = ?, motivo = ? WHERE identificador = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, entity.getFechaInicio());
            statement.setObject(2, entity.getFechaFin());
            statement.setObject(3, entity.getHoraInicio());
            statement.setObject(4, entity.getHoraFin());
            statement.setObject(5, entity.getFrecuencia());
            statement.setString(6, entity.getMotivo());
            statement.setObject(7, entity.getIdentificador());

            statement.executeUpdate();
        } catch (SQLException e) {
            //excepcion
        }
        
    	}
    
    @Override
    public void delete(ExcepcionEntity entity) {
        String sql = "DELETE FROM excepciones WHERE identificador = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, entity.getIdentificador());
            statement.executeUpdate();
        } catch (SQLException e) {
            //excepcion
        }
    }
}
