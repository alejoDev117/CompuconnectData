package co.edu.uco.compuconnect.data.dao;

import java.util.List;

import co.edu.uco.compuconnect.entities.MonitorEntity;

public interface MonitorDAO {

	void create(MonitorEntity entity);
	
	List<MonitorEntity> read(MonitorEntity entity);
	
	void update(MonitorEntity entity);
	
	void delete(MonitorEntity entity);
	
}
