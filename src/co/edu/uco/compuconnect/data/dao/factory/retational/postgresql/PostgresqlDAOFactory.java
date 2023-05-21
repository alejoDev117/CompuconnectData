package co.edu.uco.compuconnect.data.dao.factory.retational.postgresql;

import java.sql.Connection;

import co.edu.uco.compuconnect.crosscutting.utils.UtilSql;
import co.edu.uco.compuconnect.data.dao.AgendaDAO;
import co.edu.uco.compuconnect.data.dao.AgendaReservaDAO;
import co.edu.uco.compuconnect.data.dao.BuzonSolicitudDAO;
import co.edu.uco.compuconnect.data.dao.CentroInformaticaDAO;
import co.edu.uco.compuconnect.data.dao.CentroInformaticaEquipoComputoDAO;
import co.edu.uco.compuconnect.data.dao.ContenidoDAO;
import co.edu.uco.compuconnect.data.dao.CoordinadorDAO;
import co.edu.uco.compuconnect.data.dao.DestinatarioDAO;
import co.edu.uco.compuconnect.data.dao.DestinatarioNotificacionDAO;
import co.edu.uco.compuconnect.data.dao.DetalleReservaDAO;
import co.edu.uco.compuconnect.data.dao.DiaFestivoDAO;
import co.edu.uco.compuconnect.data.dao.DiaSemanalDAO;
import co.edu.uco.compuconnect.data.dao.EquipoComputoDAO;
import co.edu.uco.compuconnect.data.dao.EstadoEquipoComputoDAO;
import co.edu.uco.compuconnect.data.dao.EstadoNotificacionDAO;
import co.edu.uco.compuconnect.data.dao.EstadoPeriodoFunicionamientoDAO;
import co.edu.uco.compuconnect.data.dao.EstadoSolicitudDAO;
import co.edu.uco.compuconnect.data.dao.ExcepcionAgendaDAO;
import co.edu.uco.compuconnect.data.dao.ExcepcionDAO;
import co.edu.uco.compuconnect.data.dao.FrecuenciaDAO;
import co.edu.uco.compuconnect.data.dao.HorarioPersonaEncargadaDAO;
import co.edu.uco.compuconnect.data.dao.MonitorDAO;
import co.edu.uco.compuconnect.data.dao.NotificacionDAO;
import co.edu.uco.compuconnect.data.dao.PerfilDAO;
import co.edu.uco.compuconnect.data.dao.PeriodoFuncionamientoDAO;
import co.edu.uco.compuconnect.data.dao.PeriodoFuncionamientoDiaFestivoDAO;
import co.edu.uco.compuconnect.data.dao.PersonaEncargadaDAO;
import co.edu.uco.compuconnect.data.dao.ReservaDAO;
import co.edu.uco.compuconnect.data.dao.ReservaPerfilDAO;
import co.edu.uco.compuconnect.data.dao.RespuestaDAO;
import co.edu.uco.compuconnect.data.dao.SoftwareDAO;
import co.edu.uco.compuconnect.data.dao.SoftwareEquipoComputoDAO;
import co.edu.uco.compuconnect.data.dao.SolicitudDAO;
import co.edu.uco.compuconnect.data.dao.SolicitudPerfilDAO;
import co.edu.uco.compuconnect.data.dao.TiempoFuncionamientoCentroInformaticaDAO;
import co.edu.uco.compuconnect.data.dao.TipoIdentificacionDAO;
import co.edu.uco.compuconnect.data.dao.TipoNotificacionDAO;
import co.edu.uco.compuconnect.data.dao.TipoReservaDAO;
import co.edu.uco.compuconnect.data.dao.TipoSolicitudDAO;
import co.edu.uco.compuconnect.data.dao.TipoUsuarioDAO;
import co.edu.uco.compuconnect.data.dao.UsuarioDAO;
import co.edu.uco.compuconnect.data.dao.factory.DAOFactory;
import co.edu.uco.compuconnect.data.dao.relational.postgresql.AgendaPostgresqlDAO;
import co.edu.uco.compuconnect.data.dao.relational.postgresql.AgendaReservaPostgresqlDAO;
import co.edu.uco.compuconnect.data.dao.relational.postgresql.BuzonSolicitudPostgresqlDAO;
import co.edu.uco.compuconnect.data.dao.relational.postgresql.CentroInformaticaEquipoComputoPostgresqlDAO;
import co.edu.uco.compuconnect.data.dao.relational.postgresql.CentroInformaticaPostgresqlDAO;
import co.edu.uco.compuconnect.data.dao.relational.postgresql.ContenidoPostgresqlDAO;
import co.edu.uco.compuconnect.data.dao.relational.postgresql.CoordinadorPostgresqlDAO;
import co.edu.uco.compuconnect.data.dao.relational.postgresql.DestinatarioNotificacionPostgresqlDAO;
import co.edu.uco.compuconnect.data.dao.relational.postgresql.DestinatarioPostgresqlDAO;
import co.edu.uco.compuconnect.data.dao.relational.postgresql.DetalleReservaPostgresqlDAO;
import co.edu.uco.compuconnect.data.dao.relational.postgresql.DiaFestivoPostgresqlDAO;
import co.edu.uco.compuconnect.data.dao.relational.postgresql.DiaSemanalPostgresqlDAO;
import co.edu.uco.compuconnect.data.dao.relational.postgresql.EquipoComputoPostgresqlDAO;
import co.edu.uco.compuconnect.data.dao.relational.postgresql.EstadoEquipoComputoPostgresqlDAO;
import co.edu.uco.compuconnect.data.dao.relational.postgresql.EstadoNotificacionPostgresqlDAO;
import co.edu.uco.compuconnect.data.dao.relational.postgresql.EstadoPeriodoFuncionamientoPostgresqlDAO;
import co.edu.uco.compuconnect.data.dao.relational.postgresql.EstadoSolicitudPostgresqlDAO;
import co.edu.uco.compuconnect.data.dao.relational.postgresql.ExcepcionAgendaPostgresqlDAO;
import co.edu.uco.compuconnect.data.dao.relational.postgresql.ExcepcionPostgresqlDAO;
import co.edu.uco.compuconnect.data.dao.relational.postgresql.FrecuenciaPostgresqlDAO;
import co.edu.uco.compuconnect.data.dao.relational.postgresql.HorarioPersonaEncargadaPostgresqlDAO;
import co.edu.uco.compuconnect.data.dao.relational.postgresql.MonitorPostgresqlDAO;
import co.edu.uco.compuconnect.data.dao.relational.postgresql.NotificacionPostgresqlDAO;
import co.edu.uco.compuconnect.data.dao.relational.postgresql.PerfilPostgresqlDAO;
import co.edu.uco.compuconnect.data.dao.relational.postgresql.PeriodoFuncionamientoDiaFestivoPostgresqlDAO;
import co.edu.uco.compuconnect.data.dao.relational.postgresql.PeriodoFuncionamientoPostgresqlDAO;
import co.edu.uco.compuconnect.data.dao.relational.postgresql.PersonaEncargadaPostgresqlDAO;
import co.edu.uco.compuconnect.data.dao.relational.postgresql.ReservaPerfilPostgresqlDAO;
import co.edu.uco.compuconnect.data.dao.relational.postgresql.ReservaPostgresqlDAO;
import co.edu.uco.compuconnect.data.dao.relational.postgresql.RespuestaPostgresqlDAO;
import co.edu.uco.compuconnect.data.dao.relational.postgresql.SoftwareEquipoComputoPostgresqlDAO;
import co.edu.uco.compuconnect.data.dao.relational.postgresql.SoftwarePostgresqlDAO;
import co.edu.uco.compuconnect.data.dao.relational.postgresql.SolicitudPerfilPostgresqlDAO;
import co.edu.uco.compuconnect.data.dao.relational.postgresql.SolicitudPostgresqlDAO;
import co.edu.uco.compuconnect.data.dao.relational.postgresql.TiempoFuncionamientoCentroInformaticaPostgresqlDAO;
import co.edu.uco.compuconnect.data.dao.relational.postgresql.TipoIdentificacionPostgresqlDAO;
import co.edu.uco.compuconnect.data.dao.relational.postgresql.TipoNotificacionPostgresqlDAO;
import co.edu.uco.compuconnect.data.dao.relational.postgresql.TipoReservaPostgresqlDAO;
import co.edu.uco.compuconnect.data.dao.relational.postgresql.TipoSolicitudPostgresqlDAO;
import co.edu.uco.compuconnect.data.dao.relational.postgresql.TipoUsuarioPostgresqlDAO;
import co.edu.uco.compuconnect.data.dao.relational.postgresql.UsuarioPostgresqlDAO;

public final class PostgresqlDAOFactory extends DAOFactory {

	private Connection connection;
	
	public PostgresqlDAOFactory() {
		abrirConexion();
	}
	
	@Override
	protected void abrirConexion() {
		UtilSql.abrirConexion(connection);
		
	}

	@Override
	public void cerrarConexion() {
		UtilSql.cerrarConexion(connection);
	
		
	}

	@Override
	public void iniciarTransaccion() {
		UtilSql.iniciarTransaccion(connection);
		
	}

	@Override
	public void confirmarTransaccion() {
		UtilSql.confirmarTransaccion(connection);
		
	}

	@Override
	public void cancelarTransaccion() {
		UtilSql.cancelarTransaccion(connection);
		
	}

	@Override
	public CentroInformaticaDAO getCentroInformaticaDAO() {
		return new CentroInformaticaPostgresqlDAO(connection);
	}

	@Override
	public DiaFestivoDAO getDiaFestivoDAO() {
		return new DiaFestivoPostgresqlDAO(connection);
	}

	@Override
	public DiaSemanalDAO getDiaSemanalDAO() {
		return new DiaSemanalPostgresqlDAO(connection);
	}

	@Override
	public EstadoPeriodoFunicionamientoDAO getEstadoPeriodoFunicionamientoDAO() {
		return new EstadoPeriodoFuncionamientoPostgresqlDAO(connection);
	}

	@Override
	public EstadoEquipoComputoDAO getEstadoEquipoComputoDAO() {
		return new EstadoEquipoComputoPostgresqlDAO(connection);
	}

	@Override
	public EstadoNotificacionDAO getEstadoNotificacionDAO() {
		return new EstadoNotificacionPostgresqlDAO(connection);
	}

	@Override
	public EstadoSolicitudDAO getEstadoSolicitudDAO() {
		return new EstadoSolicitudPostgresqlDAO(connection);
	}

	@Override
	public FrecuenciaDAO getFrecuenciaDAO() {
		return new FrecuenciaPostgresqlDAO(connection);
	}

	@Override
	public SoftwareDAO getSoftwareDAO() {
		return new SoftwarePostgresqlDAO(connection);
	}

	@Override
	public TipoIdentificacionDAO getTipoIdentificacionDAO() {
		return new TipoIdentificacionPostgresqlDAO(connection);
	}

	@Override
	public TipoNotificacionDAO getTipoNotificacionDAO() {
		return new TipoNotificacionPostgresqlDAO(connection);	
	}

	@Override
	public TipoReservaDAO getTipoReservaDAO() {
		return new TipoReservaPostgresqlDAO(connection);
	}

	@Override
	public TipoSolicitudDAO getTipoSolicitudDAO() {
		return new TipoSolicitudPostgresqlDAO(connection);
	}

	@Override
	public TipoUsuarioDAO getTipoUsuarioDAO() {
		return new TipoUsuarioPostgresqlDAO(connection);
	}

	@Override
	public CoordinadorDAO getCoordinadorDAO() {
		return new CoordinadorPostgresqlDAO(connection);
	}

	@Override
	public EquipoComputoDAO getEquipoComputoDAO() {
		return new EquipoComputoPostgresqlDAO(connection);
	}

	@Override
	public ExcepcionDAO getExcepcionDAO() {
		return new ExcepcionPostgresqlDAO(connection);
	}

	@Override
	public MonitorDAO getMonitorDAO() {
		return new MonitorPostgresqlDAO(connection);
	}

	@Override
	public PeriodoFuncionamientoDAO getPeriodoFuncionamientoDAO() {
		return new PeriodoFuncionamientoPostgresqlDAO(connection);
	}

	@Override
	public ReservaDAO getReservaDAO() {
		return new ReservaPostgresqlDAO(connection);
	}

	@Override
	public SolicitudDAO getSolicitudDAO() {
		return new SolicitudPostgresqlDAO(connection);
	}

	@Override
	public UsuarioDAO getUsuarioDAO() {
		return new UsuarioPostgresqlDAO(connection);
	}

	@Override
	public AgendaDAO getAgendaDAO() {
		return new AgendaPostgresqlDAO(connection);
	}

	@Override
	public DetalleReservaDAO getDetalleReservaDAO() {
		return new DetalleReservaPostgresqlDAO(connection);
	}

	@Override
	public NotificacionDAO getNotificacionDAO() {
		return new NotificacionPostgresqlDAO(connection);
	}

	@Override
	public PerfilDAO getPerfilDAO() {
		return new PerfilPostgresqlDAO(connection);
	}

	@Override
	public BuzonSolicitudDAO getBuzonSolicitudDAO() {
		return new BuzonSolicitudPostgresqlDAO(connection);
	}

	@Override
	public RespuestaDAO getRespuestaDAO() {
		return new RespuestaPostgresqlDAO(connection);
	}

	@Override
	public TiempoFuncionamientoCentroInformaticaDAO getTiempoFuncionamientoCentroInformaticaDAO() {
		return new TiempoFuncionamientoCentroInformaticaPostgresqlDAO(connection);
	}

	@Override
	public SoftwareEquipoComputoDAO getSoftwareEquipoComputoDAO() {
		return new SoftwareEquipoComputoPostgresqlDAO(connection);
	}

	@Override
	public DestinatarioNotificacionDAO getDestinatarioNotificacionDAO() {
		return new DestinatarioNotificacionPostgresqlDAO(connection);
	}

	@Override
	public ExcepcionAgendaDAO getExcepcionAgendaDAO() {
		return new ExcepcionAgendaPostgresqlDAO(connection);
	}

	@Override
	public AgendaReservaDAO getAgendaReservaDAO() {
		return new AgendaReservaPostgresqlDAO(connection);
	}

	@Override
	public CentroInformaticaEquipoComputoDAO getCentroInformaticaEquipoComputoDAO() {
		return new CentroInformaticaEquipoComputoPostgresqlDAO(connection);
	}

	@Override
	public PeriodoFuncionamientoDiaFestivoDAO getPeriodoFuncionamientoDiaFestivoDAO() {
		return new PeriodoFuncionamientoDiaFestivoPostgresqlDAO(connection);
	}

	@Override
	public ReservaPerfilDAO getReservaPerfil() {
		return new ReservaPerfilPostgresqlDAO(connection);
	}

	@Override
	public SolicitudPerfilDAO getSolicitudPerfilDAO() {
		return new SolicitudPerfilPostgresqlDAO(connection);
	}

	@Override
	public HorarioPersonaEncargadaDAO getHorarioPersonaEncargadaDAO() {
		return new HorarioPersonaEncargadaPostgresqlDAO(connection);
	}

	@Override
	public ContenidoDAO getContenidoDAO() {
		return new ContenidoPostgresqlDAO(connection);
	}

	@Override
	public DestinatarioDAO getDestinatarioDAO() {
		return new DestinatarioPostgresqlDAO(connection);
	}

	@Override
	public PersonaEncargadaDAO getPersonaEncargadaDAO() {
		return new PersonaEncargadaPostgresqlDAO(connection);
	}

	@Override
	public ReservaPerfilDAO getReservaPerfilDAO() {
		return new ReservaPerfilPostgresqlDAO(connection);
	}

}
