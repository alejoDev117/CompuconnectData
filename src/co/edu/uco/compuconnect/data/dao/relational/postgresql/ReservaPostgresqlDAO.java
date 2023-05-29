package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.compuconnect.crosscutting.exceptions.CompuconnectDataException;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;
import co.edu.uco.compuconnect.crosscutting.utils.UtilText;
import co.edu.uco.compuconnect.crosscutting.utils.UtilUUID;
import co.edu.uco.compuconnect.crosscutting.utils.Messages.AgendaPostgresqlDAOMessage;
import co.edu.uco.compuconnect.crosscutting.utils.Messages.ReservaPostgresqlDAOMessage;
import co.edu.uco.compuconnect.data.dao.ReservaDAO;
import co.edu.uco.compuconnect.data.dao.relational.SqlDAO;
import co.edu.uco.compuconnect.entities.ReservaEntity;
import co.edu.uco.compuconnect.entities.UsuarioEntity;

public final class ReservaPostgresqlDAO extends SqlDAO<ReservaEntity> implements ReservaDAO {

    public ReservaPostgresqlDAO(final Connection connection) {
        super(connection);
    }

    @Override
    public void create(ReservaEntity entity) {
        var sqlStatement = "INSERT INTO reserva (identificador, autor, tipo, fechaInicio, fechaFin, frecuencia, centroInformatica, descripcion, horaCreacion) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (var preparedStatement = getConnection().prepareStatement(sqlStatement)) {
            preparedStatement.setObject(1, entity.getIdentificador());
            preparedStatement.setObject(2, entity.getAutor().getIdentificador());
            preparedStatement.setString(3, entity.getTipoReserva().toString());
            preparedStatement.setObject(4, entity.getFechaInicio());
            preparedStatement.setObject(4, entity.getFechaFin());
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
		var parameters = new ArrayList<>();
		
		sqlStatement.append(prepareSelect());
		sqlStatement.append(prepareFrom());
		sqlStatement.append(prepareWhere(entity, parameters));
		sqlStatement.append(prepareOrderBy());
		
		try (var preparedStament = getConnection().prepareStatement(sqlStatement.toString())){
			
		} catch (SQLException exception) {
			
		} catch (Exception exception) {
			
		}
		
		return null;
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
        return "FROM reserva ";
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
}