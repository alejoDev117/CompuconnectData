package co.edu.uco.compuconnect.data.dao;

import java.util.List;

import co.edu.uco.compuconnect.entities.EquipoComputoEntity;

public interface EquipoComputoDAO {

	void create(EquipoComputoEntity entity);
	
	List<EquipoComputoEntity> read(EquipoComputoEntity entity);
	
	void update(EquipoComputoEntity entity);
	
	void delete(EquipoComputoEntity entity);
	
}
