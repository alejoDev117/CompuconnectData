package co.edu.uco.compuconnect.data.dao;

import java.util.List;

import co.edu.uco.compuconnect.entities.SoftwareEntity;

public interface SoftwareDAO {

	void create(SoftwareEntity entity);
	
	List<SoftwareEntity> read(SoftwareEntity entity);
	
	void update(SoftwareEntity entity);
	
	void delete(SoftwareEntity entity);
	
}
