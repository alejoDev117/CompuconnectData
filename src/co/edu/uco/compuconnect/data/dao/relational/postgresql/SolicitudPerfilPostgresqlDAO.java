package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import co.edu.uco.compuconnect.data.dao.SolicitudPerfilDAO;
import co.edu.uco.compuconnect.entities.SolicitudPerfilEntity;

public class SolicitudPerfilPostgresqlDAO implements SolicitudPerfilDAO {

    private final Connection connection;

    public SolicitudPerfilPostgresqlDAO(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(SolicitudPerfilEntity entity) {
        String query = "INSERT INTO solicitud_perfil (identificador, solicitud_id, perfil_id) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, entity.getIdentificador());
            statement.setObject(2, entity.getSolicitud().getIdentificador());
            statement.setObject(3, entity.getPerfil().getIdentificador());

            statement.executeUpdate();
        } catch (SQLException e) {
            //excepcion
        }
    }

    @Override
    public List<SolicitudPerfilEntity> read(SolicitudPerfilEntity entity) {
        List<SolicitudPerfilEntity> solicitudPerfilList = new ArrayList<>();
        String query = "SELECT identificador, solicitud_id, perfil_id FROM solicitud_perfil WHERE identificador = ?";


        return solicitudPerfilList;
    }

    @Override
    public void delete(SolicitudPerfilEntity entity) {
        String query = "DELETE FROM solicitud_perfil WHERE identificador = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, entity.getIdentificador());

            statement.executeUpdate();
        } catch (SQLException e) {
            //excepcion
        }
    }
}
