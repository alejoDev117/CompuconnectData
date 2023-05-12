package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.compuconnect.crosscutting.utils.UtilSql;
import co.edu.uco.compuconnect.data.dao.EstadoEquipoComputoDAO;
import co.edu.uco.compuconnect.entities.EstadoEquipoComputoEntity;

public final class EstadoEquipoComputoPostgresqlDAO implements EstadoEquipoComputoDAO {

    private final Connection connection;

    public EstadoEquipoComputoPostgresqlDAO(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(EstadoEquipoComputoEntity entity) {
        String sql = "INSERT INTO EstadoEquipoComputo (identificador, nombre, descripcion) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, entity.getIdentificador());
            statement.setString(2, entity.getNombre());
            statement.setString(3, entity.getDescripcion());

            statement.executeUpdate();
            System.out.println("Registro insertado correctamente");
        } catch (SQLException e) {
            //excepción
        }
    }

    @Override
    public List<EstadoEquipoComputoEntity> read(EstadoEquipoComputoEntity entity) {
        List<EstadoEquipoComputoEntity> estadoList = new ArrayList<>();
        return estadoList;
    }
    
    public static void main(String[] args) {
        UtilSql.abrirConexion();

        try {
            Connection connection = UtilSql.conexion;

            EstadoEquipoComputoPostgresqlDAO estadoEquipoComputoPostgresqlDAO = new EstadoEquipoComputoPostgresqlDAO(connection);

            EstadoEquipoComputoEntity estadoEquipo = new EstadoEquipoComputoEntity();
            estadoEquipo.setIdentificador(UUID.randomUUID());
            estadoEquipo.setNombre("Activo");
            estadoEquipo.setDescripcion("El estado está activo");
            estadoEquipoComputoPostgresqlDAO.create(estadoEquipo);
            System.out.println("Creado correctamente");
        } finally {
            UtilSql.cerrarConexion();
        }
    }
}   