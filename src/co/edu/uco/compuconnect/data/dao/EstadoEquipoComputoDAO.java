package co.edu.uco.compuconnect.data.dao;

import java.util.List;

import co.edu.uco.compuconnect.entities.EstadoEquipoComputoEntity;

public interface EstadoEquipoComputoDAO {
	
	void create(EstadoEquipoComputoEntity entity);
	
	List<EstadoEquipoComputoEntity> read(EstadoEquipoComputoEntity entity);
	

}
