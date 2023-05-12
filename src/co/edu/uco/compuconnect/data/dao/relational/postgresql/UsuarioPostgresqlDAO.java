package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.compuconnect.data.dao.UsuarioDAO;
import co.edu.uco.compuconnect.entities.UsuarioEntity;

public final class UsuarioPostgresqlDAO implements UsuarioDAO {

    private Connection connection;

    public UsuarioPostgresqlDAO(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(UsuarioEntity entity) {
        String query = "INSERT INTO usuario (identificador, tipo_usuario, nombre, tipo_identificacion, identificacion, correo_institucional) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, entity.getIdentificador());
            statement.setObject(2, entity.getTipoUsuario());
            statement.setString(3, entity.getNombre());
            statement.setObject(4, entity.getTipoIdentificacion());
            statement.setString(5, entity.getIdentificacion());
            statement.setString(6, entity.getCorreoInstitucional());

            statement.executeUpdate();
        } catch (SQLException e) {
            //excepcion
        }
    }

    @Override
    public void update(UsuarioEntity entity) {
        String query = "UPDATE usuario SET tipo_usuario = ?, nombre = ?, tipo_identificacion = ?, identificacion = ?, correo_institucional = ? WHERE identificador = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, entity.getTipoUsuario());
            statement.setString(2, entity.getNombre());
            statement.setObject(3, entity.getTipoIdentificacion());
            statement.setString(4, entity.getIdentificacion());
            statement.setString(5, entity.getCorreoInstitucional());
            statement.setObject(6, entity.getIdentificador());

            statement.executeUpdate();
        } catch (SQLException e) {
            // Manejo de excepciones
        }
    }

    @Override
    public List<UsuarioEntity> read(UsuarioEntity entity) {
        List<UsuarioEntity> result = new ArrayList<>();
        
        return result;
    }
    
    @Override
    public void delete(UsuarioEntity entity) {
        String query = "DELETE FROM usuario WHERE identificador = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setObject(1, entity.getIdentificador());

            statement.executeUpdate();
        } catch (SQLException e) {
            //excepcion
        }
    }
}
