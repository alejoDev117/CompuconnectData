package co.edu.uco.compuconnect.data.dao;

import java.util.List;

import co.edu.uco.compuconnect.entities.SoftwareEquipoComputoEntity;

public interface SoftwareEquipoComputoDAO {

	void create(SoftwareEquipoComputoEntity entity);
	
	List<SoftwareEquipoComputoEntity> read(SoftwareEquipoComputoEntity entity);
	
	void delete(SoftwareEquipoComputoEntity entity);
	
	
}
