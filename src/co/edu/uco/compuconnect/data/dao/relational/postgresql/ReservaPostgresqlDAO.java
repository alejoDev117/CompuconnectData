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
        var sqlStatement = "INSERT INTO Reserva (identificador, autor, tipo, fecha_inicio, fecha_fin, frecuencia, centro_informatica, descripcion, hora_creacion) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (var preparedStatement = getConnection().prepareStatement(sqlStatement)) {
            preparedStatement.setObject(1, entity.getIdentificador());
            preparedStatement.setObject(2, entity.getAutor().getIdentificador());
            preparedStatement.setString(3, entity.getTipoReserva().toString());
            preparedStatement.setDate(4, new java.sql.Date(entity.getFechaInicio().getTime()));
            preparedStatement.setDate(5, new java.sql.Date(entity.getFechaFin().getTime()));
            preparedStatement.setObject(6, entity.getFrecuencia().getIdentificador());
            preparedStatement.setObject(7, entity.getCentroInformatica().getIdentificador());
            preparedStatement.setString(8, entity.getDescripcion());
            preparedStatement.setObject(9, entity.getHoraCreacion());

            preparedStatement.executeUpdate();

        } catch (final SQLException exception) {
            var mensajeUsuario = "Se ha producido un problema al intentar generar la reserva";
            var mensajeTecnico = "Se ha producido un problema de tipo SQLException en el método generar de la clase ReservaPostgresqlDAO. Por favor, verifica la traza completa del error.";

            throw CompuconnectDataException.create(mensajeTecnico, mensajeUsuario, exception);
        } catch (final Exception exception) {
            var mensajeUsuario = "Se ha producido un problema inesperado al intentar generar la reserva";
            var mensajeTecnico = "Se ha producido un problema inesperado en el método generar de la clase ReservaPostgresqlDAO. Por favor, verifica la traza completa del error.";
            throw CompuconnectDataException.create(mensajeTecnico, mensajeUsuario, exception);
        }
    }

    @Override
    public void delete(ReservaEntity entity) {
        var sqlStatement = "DELETE FROM Reserva WHERE identificador = ?";

        try (var preparedStatement = getConnection().prepareStatement(sqlStatement)) {
            preparedStatement.setObject(1, entity.getIdentificador());

            preparedStatement.executeUpdate();
        } catch (final SQLException exception) {
            var mensajeUsuario = "Se ha producido un problema al intentar cancelar la reserva";
            var mensajeTecnico = "Se ha producido un problema de tipo SQLException en el método cancelar de la clase ReservaPostgresqlDAO. Por favor, verifica la traza completa del error";

            throw CompuconnectDataException.create(mensajeTecnico, mensajeUsuario, exception);
        } catch (final Exception exception) {
            var mensajeUsuario = "Se ha producido un problema inesperado al intentar cancelar la reserva";
            var mensajeTecnico = "Se ha producido un problema inesperado en el método cancelar de la clase ReservaPostgresqlDAO. Por favor, verifica la traza completa del error";
            throw CompuconnectDataException.create(mensajeTecnico, mensajeUsuario, exception);
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
        var sqlStatement = "UPDATE Reserva SET autor = ?, tipo = ?, fecha_inicio = ?, fecha_fin = ?, frecuencia = ?, " +
                "centro_informatica = ?, descripcion = ?, hora_creacion = ? WHERE identificador = ?";

        try (var preparedStatement = getConnection().prepareStatement(sqlStatement)) {
            preparedStatement.setObject(1, entity.getAutor().getIdentificador());
            preparedStatement.setString(2, entity.getTipoReserva().toString());
            preparedStatement.setObject(3, entity.getFechaInicio().getTime());
            preparedStatement.setObject(4, entity.getFechaFin().getTime());
            preparedStatement.setObject(5, entity.getFrecuencia().getIdentificador());
            preparedStatement.setObject(6, entity.getCentroInformatica().getIdentificador());
            preparedStatement.setString(7, entity.getDescripcion());
            preparedStatement.setObject(8, entity.getHoraCreacion().getTime());
            preparedStatement.setObject(9, entity.getIdentificador());

            preparedStatement.executeUpdate();
        } catch (final SQLException exception) {
            var mensajeUsuario = "Se ha producido un problema al intentar modificar la reserva";
            var mensajeTecnico = "Se ha producido un problema de tipo SQLException en el método modificar de la clase ReservaPostgresqlDAO. Por favor, verifica la traza completa del error";

            throw CompuconnectDataException.create(mensajeTecnico, mensajeUsuario, exception);
        } catch (final Exception exception) {
            var mensajeUsuario = "Se ha producido un problema inesperado al intentar modificar la reserva";
            var mensajeTecnico = "Se ha producido un problema inesperado en el método modificar de la clase ReservaPostgresqlDAO. Por favor, verifica la traza completa del error";
            throw CompuconnectDataException.create(mensajeTecnico, mensajeUsuario, exception);
        }
    }
    
    @Override
    protected String prepareSelect() {
        return "SELECT identificador, autor, tipoReserva, fechaInicio, fechaFin, frecuencia, centroInformatica, descripcion, horaCreacion ";
    }

    @Override
    protected String prepareFrom() {
        return "FROM Reserva";
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