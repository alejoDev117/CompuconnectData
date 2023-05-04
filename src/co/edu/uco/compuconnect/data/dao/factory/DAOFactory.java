package co.edu.uco.compuconnect.data.dao.factory;

import co.edu.uco.compuconnect.data.dao.CentroInformaticaDAO;
import co.edu.uco.compuconnect.data.dao.CoordinadorDAO;
import co.edu.uco.compuconnect.data.dao.DiaFestivoDAO;
import co.edu.uco.compuconnect.data.dao.DiaSemanalDAO;
import co.edu.uco.compuconnect.data.dao.EquipoComputoDAO;
import co.edu.uco.compuconnect.data.dao.EstadoEquipoComputoDAO;
import co.edu.uco.compuconnect.data.dao.EstadoNotificacionDAO;
import co.edu.uco.compuconnect.data.dao.EstadoPeriodoFunicionamientoDAO;
import co.edu.uco.compuconnect.data.dao.EstadoSolicitudDAO;
import co.edu.uco.compuconnect.data.dao.ExcepcionDAO;
import co.edu.uco.compuconnect.data.dao.FrecuenciaDAO;
import co.edu.uco.compuconnect.data.dao.MonitorDAO;
import co.edu.uco.compuconnect.data.dao.PeriodoFuncionamientoDAO;
import co.edu.uco.compuconnect.data.dao.ReservaDAO;
import co.edu.uco.compuconnect.data.dao.SoftwareDAO;
import co.edu.uco.compuconnect.data.dao.SolicitudDAO;
import co.edu.uco.compuconnect.data.dao.TipoIdentificacionDAO;
import co.edu.uco.compuconnect.data.dao.TipoNotificacionDAO;
import co.edu.uco.compuconnect.data.dao.TipoReservaDAO;
import co.edu.uco.compuconnect.data.dao.TipoSolicitudDAO;
import co.edu.uco.compuconnect.data.dao.TipoUsuarioDAO;
import co.edu.uco.compuconnect.data.dao.UsuarioDAO;

public abstract class DAOFactory {

	
	
	public abstract void abrirConexion();
	public abstract void cerrarConexion();
	public abstract void iniciarTransaccion();
	public abstract void confirmarTransaccion();
	public abstract void cancelarTransaccion();
	
	
	public abstract CentroInformaticaDAO getCentroInformaticaDAO();
	
	public abstract DiaFestivoDAO getDiaFestivoDAO();
	
	public abstract DiaSemanalDAO getDiaSemanalDAO();
	
	public abstract EstadoPeriodoFunicionamientoDAO getEstadoPeriodoFunicionamientoDAO();
	
	public abstract EstadoEquipoComputoDAO getEstadoEquipoComputoDAO();
	
	public abstract EstadoNotificacionDAO getEstadoNotificacionDAO();
	
	public abstract EstadoSolicitudDAO getEstadoSolicitudDAO();
	
	public abstract FrecuenciaDAO getFrecuenciaDAO();
	
	public abstract SoftwareDAO getSoftwareDAO();
	
	public abstract TipoIdentificacionDAO getTipoIdentificacionDAO();
	
	public abstract TipoNotificacionDAO getTipoNotificacionDAO();
	
	public abstract TipoReservaDAO getTipoReservaDAO();

	public abstract TipoSolicitudDAO getTipoSolicitudDAO();
	
	public abstract TipoUsuarioDAO getTipoUsuarioDAO();
	
	public abstract CoordinadorDAO getCoordinadorDAO();
	
	public abstract EquipoComputoDAO getEquipoComputoDAO();
	
	public abstract ExcepcionDAO getExcepcionDAO();
	
	public abstract MonitorDAO getMonitorDAO();
	
	public abstract PeriodoFuncionamientoDAO getPeriodoFuncionamientoDAO();
	
	public abstract ReservaDAO getReservaDAO();
	
	public abstract SolicitudDAO getSolicitudDAO();
	
	public abstract UsuarioDAO getUsuarioDAO();
	
}
