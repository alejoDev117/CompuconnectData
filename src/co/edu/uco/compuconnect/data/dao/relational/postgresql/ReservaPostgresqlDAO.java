package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.compuconnect.crosscutting.exceptions.CompuconnectDataException;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;
import co.edu.uco.compuconnect.crosscutting.utils.UtilText;
import co.edu.uco.compuconnect.crosscutting.utils.UtilUUID;
import co.edu.uco.compuconnect.crosscutting.utils.Messages.ReservaPostgresqlDAOMessage;
import co.edu.uco.compuconnect.crosscutting.utils.Messages.UsuarioPostgresqlDAOMessage;
import co.edu.uco.compuconnect.crosscutting.utils.UtilDateTime;
import co.edu.uco.compuconnect.data.dao.ReservaDAO;
import co.edu.uco.compuconnect.data.dao.relational.SqlDAO;
import co.edu.uco.compuconnect.entities.AgendaEntity;
import co.edu.uco.compuconnect.entities.DetalleReservaEntity;
import co.edu.uco.compuconnect.entities.FrecuenciaEntity;
import co.edu.uco.compuconnect.entities.ReservaEntity;
import co.edu.uco.compuconnect.entities.TipoReservaEntity;
import co.edu.uco.compuconnect.entities.UsuarioEntity;

public final class ReservaPostgresqlDAO extends SqlDAO<ReservaEntity> implements ReservaDAO {

    public ReservaPostgresqlDAO(final Connection connection) {
        super(connection);
    }

    @Override
    public void create(ReservaEntity entity) {
        var sqlStatement = "INSERT INTO reserva (identificador ,autor, tipo, fecha_inicio, fecha_fin, frecuencia, descripcion, hora_creacion , agenda, detalle) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
        

        try (var preparedStatement = getConnection().prepareStatement(sqlStatement)) {
            preparedStatement.setObject(1, entity.getIdentificador());
            preparedStatement.setObject(2, entity.getAutor().getIdentificador());
            preparedStatement.setObject(3, entity.getTipoReserva().getIdentificador());
            preparedStatement.setObject(4, new java.sql.Date(entity.getFechaInicio().getTime()));
            preparedStatement.setObject(5, new java.sql.Date(entity.getFechaFin().getTime())); 
            preparedStatement.setObject(6, entity.getFrecuencia().getIdentificador());
            preparedStatement.setString(7, entity.getDescripcion());
            preparedStatement.setObject(8, new java.sql.Date(entity.getHoraCreacion().getTime()));
            preparedStatement.setObject(9, entity.getAgenda().getIdentificador());
            preparedStatement.setObject(10, entity.getDetalle().getIdentificador());

            preparedStatement.executeUpdate();

        } catch (final SQLException exception) {
        	throw CompuconnectDataException.create(ReservaPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_TECHNICAL_MESSAGE, ReservaPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_USER_MESSAGE,exception);
        	
        }catch(final Exception exception) {
        	throw CompuconnectDataException.create(ReservaPostgresqlDAOMessage.CREATE_EXCEPTION_TECHNICAL_MESSAGE, ReservaPostgresqlDAOMessage.CREATE_EXCEPTION_USER_MESSAGE,exception);
        }
    }

    @Override
    public void delete(ReservaEntity entity) {
        var sqlStatement = "DELETE FROM reserva WHERE identificador = ? ";

        try (var preparedStatement = getConnection().prepareStatement(sqlStatement)) {
            preparedStatement.setObject(1, entity.getIdentificador());

            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
        	throw CompuconnectDataException.create(ReservaPostgresqlDAOMessage.DELETE_SQL_EXCEPTION_TECHNICAL_MESSAGE, ReservaPostgresqlDAOMessage.DELETE_SQL_EXCEPTION_USER_MESSAGE, exception);
        }catch(Exception exception) {
        	throw CompuconnectDataException.create(ReservaPostgresqlDAOMessage.DELETE_EXCEPTION_TECHNICAL_MESSAGE, ReservaPostgresqlDAOMessage.DELETE_EXCEPTION_USER_MESSAGE, exception);
        }
    }
    
    @Override
    public List<ReservaEntity> read(ReservaEntity entity) {
    	var sqlStatement = new StringBuilder();
    	var listParameters = new ArrayList<>();
    	
    	sqlStatement.append(prepareSelect());
    	sqlStatement.append(prepareFrom());
    	sqlStatement.append(prepareWhere(entity, listParameters));
    	sqlStatement.append(prepareOrderBy());

    	
    	try (var prepareStatement = getConnection().prepareStatement(sqlStatement.toString())){
    		setParameters(prepareStatement, listParameters);
    		return executeQuery(prepareStatement);
    		
    	}catch (CompuconnectDataException exception) {
    		throw exception;
    	}catch(SQLException exception) {
    		throw CompuconnectDataException.create(ReservaPostgresqlDAOMessage.READ_SQL_EXCEPTION_TECHNICAL_MESSAGE, ReservaPostgresqlDAOMessage.READ_SQL_EXCEPTION_USER_MESSAGE, exception);
    	}catch(Exception exception) {
    		throw CompuconnectDataException.create(ReservaPostgresqlDAOMessage.READ_EXCEPTION_TECHNICAL_MESSAGE, ReservaPostgresqlDAOMessage.READ_EXCEPTION_USER_MESSAGE, exception);
    	}
    }

    @Override
    public void update(ReservaEntity entity) {
        var sqlStatement = "UPDATE reserva SET  fecha_inicio = ?, fecha_fin = ? " +
                ", descripcion = ?  WHERE identificador = ? ";

        try (var preparedStatement = getConnection().prepareStatement(sqlStatement)) {
            preparedStatement.setObject(1, entity.getFechaInicio().getTime());
            preparedStatement.setObject(2, entity.getFechaFin().getTime());
            preparedStatement.setString(3, entity.getDescripcion());
            preparedStatement.setObject(4, entity.getIdentificador());
          

            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
        	throw CompuconnectDataException.create(ReservaPostgresqlDAOMessage.UPDATE_SQL_EXCEPTION_TECHNICAL_MESSAGE, ReservaPostgresqlDAOMessage.UPDATE_SQL_EXCEPTION_USER_MESSAGE, exception);
        }catch(Exception exception) {
        	throw CompuconnectDataException.create(ReservaPostgresqlDAOMessage.UPDATE_EXCEPTION_TECHNICAL_MESSAGE, ReservaPostgresqlDAOMessage.UPDATE_EXCEPTION_USER_MESSAGE, exception);
        }
    }
    
    @Override
    protected String prepareSelect() {
        return "SELECT r.identificador, u.nombre , tr.identificador, tr.nombre , tr.descripcion , r.fecha_inicio, r.fecha_fin, f.identificador, f.nombre , f.descripcion , dr.identificador, r.descripcion, r.hora_creacion ";
    }

    @Override
    protected String prepareFrom() {
	        return "FROM reserva r JOIN usuario u ON u.identificador = r.autor JOIN tipo_reserva tr ON tr.identificador = r.tipo JOIN frecuencia f "
	        		+ "ON f.identificador = r.frecuencia JOIN detalle_reserva dr ON dr.identificador = r.detalle JOIN dia_semanal ds ON"
	        		+ " ds.identificador = dr.dia_semanal_id JOIN agenda a ON a.identificador = r.agenda ";
    }

    @Override
    protected String prepareWhere(final ReservaEntity entity, List<Object> parameters) {
        final var where = new StringBuilder("");
        parameters = UtilObject.getDefault(parameters, new ArrayList<>());

        var setWhere = true;

        if (!UtilObject.isNull(entity)) {

            if (!UtilUUID.isDefault(entity.getIdentificador())) {
                parameters.add(entity.getIdentificador());
                where.append("WHERE r.identificador = ? ");
                setWhere = false;
            }

            if (!UtilUUID.isDefault(entity.getAgenda().getIdentificador())) {
                parameters.add(entity.getAgenda().getIdentificador());
                where.append(setWhere ? "WHERE " : "AND ").append("r.agenda = ? ");
                setWhere = false;
            }
         /*   if (!entity.getFechaInicio().equals(UtilDateTime.getDefaultValueDate())) {
                parameters.add(entity.getFechaInicio());
                where.append(setWhere ? "WHERE " : "AND ").append("r.fecha_inicio >= ? ");
                setWhere = false;
            }

            if (!entity.getFechaFin().equals(UtilDateTime.getDefaultValueDate())) {
                parameters.add(entity.getFechaFin());
                where.append(setWhere ? "WHERE " : "AND ").append("r.fecha_fin <= ? ");
                setWhere = false;
            }*/

            if (!UtilText.getUtilText().isEmpty(entity.getDetalle().getDia().getNombre())) {
                parameters.add(entity.getDetalle().getDia().getNombre());
                where.append(setWhere ? "WHERE " : "AND ").append("ds.nombre = ? ");
                setWhere = false;
            }

            if (!entity.getDetalle().getHorainicio().equals(UtilDateTime.getDefaultValueLocaltime())) {
                parameters.add(entity.getDescripcion());
                where.append(setWhere ? "WHERE " : "AND ").append("dr.hora_inicio >= ? ");
                setWhere = false;
            }

            if (!entity.getDetalle().getHorainicio().equals(UtilDateTime.getDefaultValueLocaltime())) {
                parameters.add(entity.getDescripcion());
                where.append(setWhere ? "WHERE " : "AND ").append("dr.hora_fin <= ? ");
                setWhere = false;
            }
        }

        return where.toString();
    }

    @Override
    protected String prepareOrderBy() {
        return "ORDER BY r.identificador ASC ";
    }

    @Override
    protected void setParameters(PreparedStatement preparedStatement, List<Object> parameters) {
        try {
            if (!UtilObject.isNull(parameters) && !UtilObject.isNull(preparedStatement)) {
                for (int index = 0; index < parameters.size(); index++) {
                    Object param = parameters.get(index);
                    if (param instanceof java.util.Date) {
                        preparedStatement.setObject(index + 1, new java.sql.Timestamp(((java.util.Date) param).getTime()), Types.TIMESTAMP);
                    } else {
                        preparedStatement.setObject(index + 1, param);
                    }
                }
            }
        } catch (SQLException exception) {
            throw CompuconnectDataException.create(UsuarioPostgresqlDAOMessage.SET_PARAMETERS_SQL_EXCEPTION_TECHNICAL_MESSAGE, UsuarioPostgresqlDAOMessage.SET_PARAMETERS_SQL_EXCEPTION_USER_MESSAGE, exception);
        } catch (Exception exception) {
            throw CompuconnectDataException.create(UsuarioPostgresqlDAOMessage.SET_PARAMETERS_EXCEPTION_TECHNICAL_MESSAGE, UsuarioPostgresqlDAOMessage.SET_PARAMETERS_EXCEPTION_USER_MESSAGE, exception);
        }
    }
	@Override
	protected List<ReservaEntity> executeQuery(PreparedStatement preparedStatement) {
		List<ReservaEntity> listResultSet = new ArrayList<>();		
		
		try(var resultSet = preparedStatement.executeQuery()){
			
			while(resultSet.next()) {
				var entityTmp = new ReservaEntity(resultSet.getObject("identificador",UUID.class),
						UsuarioEntity.create().setIdentificador(resultSet.getObject("identificador", UUID.class)),
						TipoReservaEntity.create().setIdentificador(resultSet.getObject("identificador", UUID.class)).setNombre(resultSet.getString("nombre")).setDescripcion(resultSet.getString("descripcion")),
						resultSet.getTimestamp("fecha_inicio"),
						resultSet.getTimestamp("fecha_fin"),
						FrecuenciaEntity.create().setIdentificador(resultSet.getObject("identificador", UUID.class)).setNombre(resultSet.getString("nombre")).setDescripcion(resultSet.getString("descripcion")),
						resultSet.getString("descripcion"),
						resultSet.getTimestamp("hora_creacion"),
						AgendaEntity.create().setIdentificador(resultSet.getObject("identificador", UUID.class)).setNombre(resultSet.getString("nombre")),
						DetalleReservaEntity.create().setIdentificador(resultSet.getObject("identificador", UUID.class)));
				listResultSet.add(entityTmp);
			}
			
		}catch (SQLException exception) {
			throw CompuconnectDataException.create(ReservaPostgresqlDAOMessage.EXCECUTE_QUERY_SQL_EXCEPTION_TECHNICAL_MESSAGE, ReservaPostgresqlDAOMessage.EXCECUTE_QUERY_SQL_EXCEPTION_USER_MESSAGE, exception);
		}catch (Exception exception) {
			throw CompuconnectDataException.create(ReservaPostgresqlDAOMessage.EXCECUTE_QUERY_EXCEPTION_TECHNICAL_MESSAGE, ReservaPostgresqlDAOMessage.EXCECUTE_QUERY_EXCEPTION_USER_MESSAGE, exception);
	}
	
		return listResultSet;
	
		
	}
}