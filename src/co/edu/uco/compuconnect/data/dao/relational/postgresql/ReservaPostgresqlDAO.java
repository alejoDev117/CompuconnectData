package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.compuconnect.crosscutting.exceptions.CompuconnectDataException;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;
import co.edu.uco.compuconnect.crosscutting.utils.UtilText;
import co.edu.uco.compuconnect.crosscutting.utils.UtilUUID;
import co.edu.uco.compuconnect.crosscutting.utils.Messages.ReservaPostgresqlDAOMessage;
import co.edu.uco.compuconnect.data.dao.ReservaDAO;
import co.edu.uco.compuconnect.data.dao.relational.SqlDAO;
import co.edu.uco.compuconnect.entities.CentroInformaticaEntity;
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
        var sqlStatement = "INSERT INTO reserva (identificador, autor, tipo, fecha_inicio, fecha_fin, frecuencia, centro_informatica, descripcion, hora_creacion) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (var preparedStatement = getConnection().prepareStatement(sqlStatement)) {
            preparedStatement.setObject(1, entity.getIdentificador());
            preparedStatement.setObject(2, entity.getAutor().getIdentificador());
            preparedStatement.setString(3, entity.getTipoReserva().toString());
            preparedStatement.setObject(4, entity.getFechaInicio());
            preparedStatement.setObject(5, entity.getFechaFin());
            preparedStatement.setObject(6, entity.getFrecuencia().getIdentificador());
            preparedStatement.setObject(7, entity.getCentroInformatica().getIdentificador());
            preparedStatement.setString(8, entity.getDescripcion());
            preparedStatement.setObject(9, entity.getHoraCreacion());

            preparedStatement.executeUpdate();

        } catch (final SQLException exception) {
        	throw CompuconnectDataException.create(ReservaPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_TECHNICAL_MESSAGE, ReservaPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_USER_MESSAGE,exception);
        	
        }catch(final Exception exception) {
        	throw CompuconnectDataException.create(ReservaPostgresqlDAOMessage.CREATE_EXCEPTION_TECHNICAL_MESSAGE, ReservaPostgresqlDAOMessage.CREATE_EXCEPTION_USER_MESSAGE,exception);
        }
    }

    @Override
    public void delete(ReservaEntity entity) {
        var sqlStatement = "DELETE FROM reserva WHERE identificador = ?";

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
        var sqlStatement = "UPDATE reserva SET  fechaInicio = ?, fechaFin = ? " +
                ", descripcion = ?  WHERE identificador = ?";

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
        return "SELECT identificador, autor, tipoReserva, fechaInicio, fechaFin, frecuencia, centroInformatica, descripcion, horaCreacion ";
    }

    @Override
    protected String prepareFrom() {
        return "FROM reserva r JOIN usuario u ON u.identificador = r.autor JOIN tipo_reserva tr ON tr.identificador = r.tipo JOIN frecuencia f "
        		+ "ON f.identificador = r.frecuencia JOIN centro_informatica ci ON ci.identificador = r.centro_informatica ";
    }

    @Override
    protected String prepareWhere(final ReservaEntity entity, List<Object> parameters) {
        final var where = new StringBuilder("");
        parameters = UtilObject.getDefault(parameters, new ArrayList<>());

        var setWhere = true;

        if (!UtilObject.isNull(entity)) {

            if (!UtilUUID.isDefault(entity.getIdentificador())) {
                parameters.add(entity.getIdentificador());
                where.append("WHERE identificador = ? ");
                setWhere = false;
            }

            if (!UtilObject.isNull(entity.getAutor())) {
                parameters.add(entity.getAutor());
                where.append(setWhere ? "WHERE " : "AND ").append("autor = ? ");
                setWhere = false;
            }

            if (!UtilObject.isNull(entity.getTipoReserva())) {
                parameters.add(entity.getTipoReserva());
                where.append(setWhere ? "WHERE " : "AND ").append("tipoReserva = ? ");
                setWhere = false;
            }

            if (!UtilObject.isNull(entity.getFechaInicio())) {
                parameters.add(entity.getFechaInicio());
                where.append(setWhere ? "WHERE " : "AND ").append("fechaInicio = ? ");
                setWhere = false;
            }

            if (!UtilObject.isNull(entity.getFechaFin())) {
                parameters.add(entity.getFechaFin());
                where.append(setWhere ? "WHERE " : "AND ").append("fechaFin = ? ");
                setWhere = false;
            }

            if (!UtilObject.isNull(entity.getFrecuencia())) {
                parameters.add(entity.getFrecuencia());
                where.append(setWhere ? "WHERE " : "AND ").append("frecuencia = ? ");
                setWhere = false;
            }

            if (!UtilObject.isNull(entity.getCentroInformatica())) {
                parameters.add(entity.getCentroInformatica());
                where.append(setWhere ? "WHERE " : "AND ").append("centroInformatica = ? ");
                setWhere = false;
            }

            if (!UtilText.getUtilText().isEmpty(entity.getDescripcion())) {
                parameters.add(entity.getDescripcion());
                where.append(setWhere ? "WHERE " : "AND ").append("descripcion LIKE ? ");
                setWhere = false;
            }

            if (!UtilObject.isNull(entity.getHoraCreacion())) {
                parameters.add(entity.getHoraCreacion());
                where.append(setWhere ? "WHERE " : "AND ").append("horaCreacion = ? ");
                setWhere = false;
            }
        }

        return where.toString();
    }

    @Override
    protected String prepareOrderBy() {
        return "ORDER BY fechaInicio ASC";
    }

	@Override
	protected void setParameters(PreparedStatement prepareStat, List<Object> parameters) {
		try {
			
		if(!UtilObject.isNull(parameters) && !UtilObject.isNull(prepareStat)) {
			for(int index = 0; index < parameters.size();index++) {
				prepareStat.setObject(index + 1, parameters.get(index));
				
			}
		}
		}catch (SQLException exception) {
			throw CompuconnectDataException.create(ReservaPostgresqlDAOMessage.SET_PARAMETERS_SQL_EXCEPTION_TECHNICAL_MESSAGE, ReservaPostgresqlDAOMessage.SET_PARAMETERS_SQL_EXCEPTION_USER_MESSAGE, exception);
		}catch (Exception exception) {
			throw CompuconnectDataException.create(ReservaPostgresqlDAOMessage.SET_PARAMETERS_EXCEPTION_TECHNICAL_MESSAGE, ReservaPostgresqlDAOMessage.SET_PARAMETERS_EXCEPTION_USER_MESSAGE, exception);
		}
		
	}

	@Override
	protected List<ReservaEntity> executeQuery(PreparedStatement preparedStatement) {
		List<ReservaEntity> listResultSet = new ArrayList<>();		
		
		try(var resultSet = preparedStatement.executeQuery()){
			
			while(resultSet.next()) {
				var entityTmp = new ReservaEntity(resultSet.getObject("identificador",UUID.class)
						, resultSet.getObject("autor",UsuarioEntity.class),
						resultSet.getObject("tipoReserva",TipoReservaEntity.class), 
						resultSet.getDate("fechaInicio"), resultSet.getDate("fechaFin"), 
						resultSet.getObject("frecuencia",FrecuenciaEntity.class),
						resultSet.getObject("centroInformatica",CentroInformaticaEntity.class),
						resultSet.getString("descripcion"),
						resultSet.getDate("horaCreacion"));
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