package co.edu.uco.compuconnect.data.dao;

import java.util.List;


import co.edu.uco.compuconnect.entities.DestinatarioEntity;


public interface DestinatarioDAO {
	
	void generate(DestinatarioEntity entity);
		
	List<DestinatarioEntity> read(DestinatarioEntity entity);
		
	void update(DestinatarioEntity entity);
		
	void delete(DestinatarioEntity entity);

}
