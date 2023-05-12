package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.compuconnect.data.dao.CentroInformaticaEquipoComputoDAO;
import co.edu.uco.compuconnect.entities.CentroInformaticaEquipoComputoEntity;


public final class CentroInformaticaEquipoComputoPostgresqlDAO implements CentroInformaticaEquipoComputoDAO {

    private final Connection connection;

    public CentroInformaticaEquipoComputoPostgresqlDAO(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(CentroInformaticaEquipoComputoEntity entity) {
        String sql = "INSERT INTO CentroInformaticaEquipoComputo (identificador, centroInformatica, equipoComputo) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, entity.getIdentificador());
            statement.setObject(2, entity.getCentroInformatica());
            statement.setObject(3, entity.getEquipoComputo());

            statement.executeUpdate();

        } catch (SQLException e) {
            //excepción
        }
    }

    @Override
    public List<CentroInformaticaEquipoComputoEntity> read(CentroInformaticaEquipoComputoEntity entity) {
        List<CentroInformaticaEquipoComputoEntity> centroList = new ArrayList<>();
        String sql = "SELECT identificador, centroInformatica, equipoComputo FROM CentroInformaticaEquipoComputo WHERE identificador = ?";

        return centroList;
    }

    @Override
    public void delete(CentroInformaticaEquipoComputoEntity entity) {
        String sql = "DELETE FROM CentroInformaticaEquipoComputo WHERE identificador = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, entity.getIdentificador());

            statement.executeUpdate();
        } catch (SQLException e) {
            //excepción
        }
    }
}