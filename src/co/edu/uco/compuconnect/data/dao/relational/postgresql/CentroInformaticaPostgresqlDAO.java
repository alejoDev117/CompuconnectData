package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.compuconnect.data.dao.CentroInformaticaDAO;
import co.edu.uco.compuconnect.entities.CentroInformaticaEntity;

public final class CentroInformaticaPostgresqlDAO implements CentroInformaticaDAO {

    private final Connection connection;

    public CentroInformaticaPostgresqlDAO(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(CentroInformaticaEntity entity) {
        String sql = "INSERT INTO CentroInformatica (identificador, nombre, ubicacion, posee_video_beam) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, entity.getIdentificador());
            statement.setString(2, entity.getNombre());
            statement.setString(3, entity.getUbicacion());
            statement.setBoolean(4, entity.isPoseeVideoBeam());

            statement.executeUpdate();

        } catch (SQLException e) {
            // excepcion
        }
    }

    @Override
    public List<CentroInformaticaEntity> read(CentroInformaticaEntity entity) {
        List<CentroInformaticaEntity> centroInformaticaList = new ArrayList<>();
        String sql = "SELECT identificador, nombre, ubicacion, posee_video_beam FROM CentroInformatica WHERE identificador = ?";
        return centroInformaticaList;
    }

    @Override
    public void update(CentroInformaticaEntity entity) {
        String sql = "UPDATE CentroInformatica SET nombre = ?, ubicacion = ?, posee_video_beam = ? WHERE identificador = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, entity.getNombre());
            statement.setString(2, entity.getUbicacion());
            statement.setBoolean(3, entity.isPoseeVideoBeam());

            statement.executeUpdate();
        } catch (SQLException e) {
            //excepcion
        }
    }

    @Override
    public void delete(CentroInformaticaEntity entity) {
        String sql = "DELETE FROM CentroInformatica WHERE identificador = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, entity.getIdentificador());

            statement.executeUpdate();
        } catch (SQLException e) {
            //excepcion
        }
    }
}