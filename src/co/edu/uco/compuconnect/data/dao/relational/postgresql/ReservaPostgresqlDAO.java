package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.compuconnect.data.dao.ReservaDAO;
import co.edu.uco.compuconnect.entities.ReservaEntity;

public final class ReservaPostgresqlDAO implements ReservaDAO {

    private Connection connection;

    public ReservaPostgresqlDAO(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(ReservaEntity entity) {
        String query = "INSERT INTO reservas (identificador, descripcion, tipo_reserva, fecha_inicio, fecha_fin, frecuencia, centro_informatica, hora_creacion, autor) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, entity.getIdentificador());
            statement.setString(2, entity.getDescripcion());
            statement.setObject(3, entity.getTipoReserva());
            statement.setDate(4, new java.sql.Date(entity.getFechaInicio().getTime()));
            statement.setDate(5, new java.sql.Date(entity.getFechaFin().getTime()));
            statement.setObject(6, entity.getFrecuencia());
            statement.setObject(7, entity.getCentroInformatica());
            statement.setTimestamp(8, new java.sql.Timestamp(entity.getHoraCreacion().getTime()));
            statement.setObject(9, entity.getAutor());

            statement.executeUpdate();
        } catch (SQLException e) {
            //excepcion
        }
    }

    @Override
    public List<ReservaEntity> read(ReservaEntity entity) {
        List<ReservaEntity> reservas = new ArrayList<>();
        String query = "SELECT * FROM reservas WHERE identificador = ?";
        
        return reservas;
    }
    
    @Override
    public void update(ReservaEntity entity) {
        String query = "UPDATE reservas SET descripcion = ?, tipo_reserva = ?, fecha_inicio = ?, fecha_fin = ?, " +
                "frecuencia = ?, centro_informatica = ?, hora_creacion = ?, autor = ? WHERE identificador = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, entity.getDescripcion());
            statement.setObject(2, entity.getTipoReserva());
            statement.setDate(3, new java.sql.Date(entity.getFechaInicio().getTime()));
            statement.setDate(4, new java.sql.Date(entity.getFechaFin().getTime()));
            statement.setObject(5, entity.getFrecuencia());
            statement.setObject(6, entity.getCentroInformatica());
            statement.setTimestamp(7, new java.sql.Timestamp(entity.getHoraCreacion().getTime()));
            statement.setObject(8, entity.getAutor());
            statement.setObject(9, entity.getIdentificador());

            statement.executeUpdate();
        } catch (SQLException e) {
            //excepcion
        }
    }

    @Override
    public void delete(ReservaEntity entity) {
        String query = "DELETE FROM reservas WHERE identificador = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, entity.getIdentificador());

            statement.executeUpdate();
        } catch (SQLException e) {
            //excepcion
        }
    }
}