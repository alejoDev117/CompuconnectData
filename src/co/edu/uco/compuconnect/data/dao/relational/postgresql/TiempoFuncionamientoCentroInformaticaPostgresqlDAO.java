package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.compuconnect.data.dao.TiempoFuncionamientoCentroInformaticaDAO;
import co.edu.uco.compuconnect.entities.TiempoFuncionamientoCentroInformaticaEntity;

public final class TiempoFuncionamientoCentroInformaticaPostgresqlDAO implements TiempoFuncionamientoCentroInformaticaDAO{
	
    private final Connection connection;
	
	public TiempoFuncionamientoCentroInformaticaPostgresqlDAO(final Connection connection) {
		this.connection = connection;
		
	}
		

	@Override
	public void create(TiempoFuncionamientoCentroInformaticaEntity entity) {
	    String query = "INSERT INTO tiempo_funcionamiento_centro_informatica (identificador, periodo_funcionamiento_id, centro_informatica_id, dia_semanal_id, hora_inicio, hora_fin) VALUES (?, ?, ?, ?, ?, ?)";
	    try (PreparedStatement statement = connection.prepareStatement(query)) {
	        statement.setObject(1, entity.getIdentificador());
	        statement.setObject(2, entity.getPeriodoFuncionamiento().getIdentificador());
	        statement.setObject(3, entity.getCentroInfomatica().getIdentificador());
	        statement.setObject(4, entity.getDia().getIdentificador());
	        statement.setObject(5, entity.getHoraInicio());
	        statement.setObject(6, entity.getHoraFin());

	        statement.executeUpdate();
	    } catch (SQLException e) {
	        //excepcion
	    }
	}

	@Override
	public List<TiempoFuncionamientoCentroInformaticaEntity> read(TiempoFuncionamientoCentroInformaticaEntity entity) {
	    List<TiempoFuncionamientoCentroInformaticaEntity> result = new ArrayList<>();

	    return result;
	}

	@Override
	public void update(TiempoFuncionamientoCentroInformaticaEntity entity) {
	    String query = "UPDATE tiempo_funcionamiento_centro_informatica SET periodo_funcionamiento_id = ?, centro_informatica_id = ?, dia_semanal_id = ?, hora_inicio = ?, hora_fin = ? WHERE identificador = ?";
	    try (PreparedStatement statement = connection.prepareStatement(query)) {
	        statement.setObject(1, entity.getPeriodoFuncionamiento().getIdentificador());
	        statement.setObject(2, entity.getCentroInfomatica().getIdentificador());
	        statement.setObject(3, entity.getDia().getIdentificador());
	        statement.setObject(4, entity.getHoraInicio());
	        statement.setObject(5, entity.getHoraFin());
	        statement.setObject(6, entity.getIdentificador());

	        statement.executeUpdate();
	    } catch (SQLException e) {
	        //excepcion
	    }
	}

	@Override
	public void delete(TiempoFuncionamientoCentroInformaticaEntity entity) {
	    String query = "DELETE FROM tiempo_funcionamiento_centro_informatica WHERE identificador = ?";
	    try (PreparedStatement statement = connection.prepareStatement(query)) {
	        statement.setObject(1, entity.getIdentificador());

	        statement.executeUpdate();
	    } catch (SQLException e) {
	        // Manejo de excepcion
	    }
	}
}
