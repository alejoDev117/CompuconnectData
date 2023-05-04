package co.edu.uco.compuconnect.data.dao;

import java.util.List;

import co.edu.uco.compuconnect.entities.DetalleReservaEntity;

public interface DetalleReservaDAO {

	void create(DetalleReservaEntity entity);
	
	List<DetalleReservaEntity> read(DetalleReservaEntity entity);
	
	void update(DetalleReservaEntity entity);
	
	void delete(DetalleReservaEntity entity);
	
}
