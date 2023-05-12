package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.compuconnect.data.dao.ReservaPerfilDAO;
import co.edu.uco.compuconnect.entities.ReservaPerfilEntity;

public final class ReservaPerfilPostgresqlDAO implements ReservaPerfilDAO {

    private Connection connection;

    public ReservaPerfilPostgresqlDAO(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(ReservaPerfilEntity entity) {
        String query = "INSERT INTO reserva_perfil (id, reserva_id, perfil_id) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, entity.getIdentificador().toString());
            statement.setString(2, entity.getReserva().getIdentificador().toString());
            statement.setString(3, entity.getPerfil().getIdentificador().toString());
            statement.executeUpdate();
        } catch (SQLException e) {
        	//excepcion
        }
    }

    @Override
    public List<ReservaPerfilEntity> read(ReservaPerfilEntity entity) {
        List<ReservaPerfilEntity> reservasPerfil = new ArrayList<>();
        
        return reservasPerfil;
    }

    @Override
    public void delete(ReservaPerfilEntity entity) {
        String query = "DELETE FROM reserva_perfil WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, entity.getIdentificador().toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            //excepción
        }
    }
}