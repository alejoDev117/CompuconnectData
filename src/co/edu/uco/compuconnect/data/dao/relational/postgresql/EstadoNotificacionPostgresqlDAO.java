package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.compuconnect.data.dao.EstadoNotificacionDAO;
import co.edu.uco.compuconnect.entities.EstadoEquipoComputoEntity;
import co.edu.uco.compuconnect.entities.EstadoNotificacionEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import co.edu.uco.compuconnect.data.dao.EstadoNotificacionDAO;
import co.edu.uco.compuconnect.entities.EstadoNotificacionEntity;

public final class EstadoNotificacionPostgresqlDAO implements EstadoNotificacionDAO {
	
	private final Connection connection;
	
	public EstadoNotificacionPostgresqlDAO(final Connection connection) {
		this.connection = connection;
	}

	@Override
	public void create(EstadoNotificacionEntity entity) {
		String query = "INSERT INTO estados_notificacion (identificador, nombre) VALUES (?, ?)";
		
		try (PreparedStatement statement = connection.prepareStatement(query)) {
			statement.setObject(1, entity.getIdentificador());
			statement.setString(2, entity.getNombre());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<EstadoNotificacionEntity> read(EstadoNotificacionEntity entity) {
		  List<EstadoNotificacionEntity> estadoList = new ArrayList<>();
	        return estadoList;	
	        }
}
