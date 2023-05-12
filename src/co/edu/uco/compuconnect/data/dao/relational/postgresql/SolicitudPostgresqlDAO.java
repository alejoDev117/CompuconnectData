package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.security.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.compuconnect.data.dao.SolicitudDAO;
import co.edu.uco.compuconnect.entities.SolicitudEntity;

public final class SolicitudPostgresqlDAO implements SolicitudDAO {

    private final Connection connection;

    public SolicitudPostgresqlDAO(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(SolicitudEntity entity) {
        String query = "INSERT INTO solicitud (identificador, autor_id, descripcion, hora_creacion, tipo_solicitud_id, estado_solicitud_id) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, entity.getIdentificador());
            statement.setObject(2, entity.getAutor().getIdentificador());
            statement.setString(3, entity.getDescripcion());
            statement.setObject(4, entity.getHoraCreacion());
            statement.setObject(5, entity.getTipoSolicitud().getIdentificador());
            statement.setObject(6, entity.getEstadoSolicitud().getIdentificador());

            statement.executeUpdate();
        } catch (SQLException e) {
            //excepcion
        }
    }

    @Override
    public List<SolicitudEntity> read(SolicitudEntity entity) {
        List<SolicitudEntity> solicitudList = new ArrayList<>();
        String query = "SELECT identificador, autor_id, descripcion, hora_creacion, tipo_solicitud_id, estado_solicitud_id FROM solicitud WHERE identificador = ?";
       
        return solicitudList;
    }

    @Override
    public void update(SolicitudEntity entity) {
        String query = "UPDATE solicitud SET autor_id = ?, descripcion = ?, hora_creacion = ?, tipo_solicitud_id = ?, estado_solicitud_id = ? WHERE identificador = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, entity.getAutor().getIdentificador());
            statement.setString(2, entity.getDescripcion());
            statement.setObject(3, entity.getHoraCreacion());
            statement.setObject(4, entity.getTipoSolicitud().getIdentificador());
            statement.setObject(5, entity.getEstadoSolicitud().getIdentificador());
            statement.setObject(6, entity.getIdentificador());

            statement.executeUpdate();
        } catch (SQLException e) {
        	//excepcion
        }
    }
        public void delete(SolicitudEntity entity) {
            String query = "DELETE FROM solicitud WHERE identificador = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setObject(1, entity.getIdentificador());

                statement.executeUpdate();
            } catch (SQLException e) {
                // Manejo de excepciones
            }
        }
   }
