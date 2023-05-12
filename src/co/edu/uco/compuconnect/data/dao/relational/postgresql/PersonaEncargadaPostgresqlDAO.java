package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.compuconnect.data.dao.PersonaEncargadaDAO;
import co.edu.uco.compuconnect.entities.PersonaEncargadaEntity;

public class PersonaEncargadaPostgresqlDAO implements PersonaEncargadaDAO {

    private Connection connection;

    public PersonaEncargadaPostgresqlDAO(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(PersonaEncargadaEntity entity) {
        String sql = "INSERT INTO persona_encargada (identificador, nombre, correo_institucional, numero_celular) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, entity.getIdentificador());
            statement.setString(2, entity.getNombre());
            statement.setString(3, entity.getCorreoInstitucional());
            statement.setString(4, entity.getNumeroCelular());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<PersonaEncargadaEntity> read(PersonaEncargadaEntity entity) {
        List<PersonaEncargadaEntity> personasEncargadas = new ArrayList<>();
        String sql = "SELECT * FROM persona_encargada";
        return personasEncargadas;
    }

    @Override
    public void update(PersonaEncargadaEntity entity) {
        String sql = "UPDATE persona_encargada SET nombre = ?, correo_institucional = ?, numero_celular = ? WHERE identificador = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, entity.getNombre());
            statement.setString(2, entity.getCorreoInstitucional());
            statement.setString(3, entity.getNumeroCelular());
            statement.setObject(4, entity.getIdentificador());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(PersonaEncargadaEntity entity) {
        String sql = "DELETE FROM persona_encargada WHERE identificador = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, entity.getIdentificador());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}