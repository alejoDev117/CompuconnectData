package co.edu.uco.compuconnect.data.dao;

import java.util.List;

import co.edu.uco.compuconnect.entities.TiempoFuncionamientoCentroInformaticaEntity;

public interface TiempoFuncionamientoCentroInformaticaDAO {

	void create(TiempoFuncionamientoCentroInformaticaEntity entity);
	
	List<TiempoFuncionamientoCentroInformaticaEntity> read(TiempoFuncionamientoCentroInformaticaEntity entity);
	
	void update(TiempoFuncionamientoCentroInformaticaEntity entity);
	
	void delete(TiempoFuncionamientoCentroInformaticaEntity entity);
	
}
