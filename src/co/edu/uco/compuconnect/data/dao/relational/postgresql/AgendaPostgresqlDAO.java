package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.compuconnect.data.dao.AgendaDAO;
import co.edu.uco.compuconnect.entities.AgendaEntity;
import co.edu.uco.compuconnect.entities.PeriodoFuncionamientoEntity;

public final class AgendaPostgresqlDAO implements AgendaDAO {

    private final Connection connection;

    public AgendaPostgresqlDAO(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(final AgendaEntity entity) {
        String sql = "INSERT INTO agenda (identificador, periodo_funcionamiento, centro_informatica, nombre) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, entity.getIdentificador());
            statement.setObject(2, entity.getPeriodoFuncionamiento().toString());
            statement.setObject(3, entity.getCentroInformatica());
            statement.setString(4, entity.getNombre());

            statement.executeUpdate();
        } catch (SQLException e) {
        	//excepcion
        	
        }
    }

    @Override
    public List<AgendaEntity> read(final AgendaEntity entity) {
        List<AgendaEntity> agendaList = new ArrayList<>();
        String sql = "SELECT identificador, periodo_funcionamiento, centro_informatica, nombre FROM agenda WHERE nombre = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, entity.getNombre());

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                UUID identificador = UUID.fromString(resultSet.getString("identificador"));
                PeriodoFuncionamientoEntity periodoFuncionamiento = PeriodoFuncionamientoEntity.getDefaultObject();
                String centroInformatica = resultSet.getString("centro_informatica");
                String nombre = resultSet.getString("nombre");

                AgendaEntity agendaEntity = AgendaEntity.getDefaultObject();
                agendaList.add(agendaEntity);
            }
        } catch (SQLException e) {
            //excepcion
        }

        return agendaList;
    }

    @Override
    public void delete(final AgendaEntity entity) {
        String sql = "DELETE FROM agenda WHERE identificador = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, entity.getIdentificador());

            statement.executeUpdate();
        } catch (SQLException e) {
        	//excepcion
        }
    }

    @Override
    public void uptade(AgendaEntity entity) {
        String sql = "UPDATE agenda SET periodo_funcionamiento = ?, centro_informatica = ?, nombre = ? WHERE identificador = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, entity.getPeriodoFuncionamiento().toString());
            statement.setObject(2, entity.getCentroInformatica());
            statement.setString(3, entity.getNombre());
            statement.setObject(4, entity.getIdentificador());

            statement.executeUpdate();
        } catch (SQLException e) {
        	//excepcion
        }
    }


}
