package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.compuconnect.crosscutting.exceptions.CompuconnectDataException;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;
import co.edu.uco.compuconnect.crosscutting.utils.UtilText;
import co.edu.uco.compuconnect.crosscutting.utils.UtilUUID;
import co.edu.uco.compuconnect.crosscutting.utils.Messages.UsuarioPostgresqlDAOMessage;
import co.edu.uco.compuconnect.data.dao.UsuarioDAO;
import co.edu.uco.compuconnect.data.dao.relational.SqlDAO;
import co.edu.uco.compuconnect.entities.UsuarioEntity;

public final class UsuarioPostgresqlDAO extends SqlDAO<UsuarioEntity> implements UsuarioDAO {

    public UsuarioPostgresqlDAO(final Connection connection) {
        super(connection);
    }

    @Override
    public void create(UsuarioEntity entity) {
        var sqlStatement = "INSERT INTO Usuario (identificador, tipo_usuario, nombre, tipo_identificacion, identificacion, correo_institucional) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (var preparedStatement = getConnection().prepareStatement(sqlStatement)) {
            preparedStatement.setObject(1, entity.getIdentificador());
            preparedStatement.setObject(2, entity.getTipoUsuario().toString());
            preparedStatement.setString(3, entity.getNombre());
            preparedStatement.setObject(4, entity.getTipoIdentificacion());
            preparedStatement.setString(5, entity.getIdentificacion());
            preparedStatement.setString(6, entity.getCorreoInstitucional());

            preparedStatement.executeUpdate();

        } catch (final SQLException exception) {
        	throw CompuconnectDataException.create(UsuarioPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_TECHNICAL_MESSAGE, UsuarioPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_USER_MESSAGE,exception);
        	
        }catch(final Exception exception) {
        	throw CompuconnectDataException.create(UsuarioPostgresqlDAOMessage.CREATE_EXCEPTION_TECHNICAL_MESSAGE, UsuarioPostgresqlDAOMessage.CREATE_EXCEPTION_USER_MESSAGE,exception);
        }
    }

    @Override
    public List<UsuarioEntity> read(UsuarioEntity entity) {
        var sqlStatement = new StringBuilder();
        var parameters = new ArrayList<>();

        sqlStatement.append(prepareSelect());
        sqlStatement.append(prepareFrom());
        sqlStatement.append(prepareWhere(entity, parameters));
        sqlStatement.append(prepareOrderBy());

        try (var preparedStatement = getConnection().prepareStatement(sqlStatement.toString())) {

        } catch (SQLException exception) {

        } catch (Exception exception) {

        }

        return null;
    }
    
    
    @Override
    public void update(UsuarioEntity entity) {
        var sqlStatement = "UPDATE Usuario SET tipo_usuario = ?, nombre = ?, tipo_identificacion = ?, " +
                "identificacion = ?, correo_institucional = ? WHERE identificador = ?";

        try (var preparedStatement = getConnection().prepareStatement(sqlStatement)) {
            preparedStatement.setObject(1, entity.getTipoUsuario());
            preparedStatement.setString(2, entity.getNombre());
            preparedStatement.setObject(3, entity.getTipoIdentificacion());
            preparedStatement.setString(4, entity.getIdentificacion());
            preparedStatement.setString(5, entity.getCorreoInstitucional());
            preparedStatement.setObject(6, entity.getIdentificador());

            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
        	throw CompuconnectDataException.create(UsuarioPostgresqlDAOMessage.UPDATE_SQL_EXCEPTION_TECHNICAL_MESSAGE, UsuarioPostgresqlDAOMessage.UPDATE_SQL_EXCEPTION_USER_MESSAGE, exception);
        }catch(Exception exception) {
        	throw CompuconnectDataException.create(UsuarioPostgresqlDAOMessage.UPDATE_EXCEPTION_TECHNICAL_MESSAGE, UsuarioPostgresqlDAOMessage.UPDATE_EXCEPTION_USER_MESSAGE, exception);
        }
    }

    @Override
    public void delete(UsuarioEntity entity) {
        var sqlStatement = "DELETE FROM Usuario WHERE identificador = ?";

        try (var preparedStatement = getConnection().prepareStatement(sqlStatement)) {
            preparedStatement.setObject(1, entity.getIdentificador());

            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
        	throw CompuconnectDataException.create(UsuarioPostgresqlDAOMessage.DELETE_SQL_EXCEPTION_TECHNICAL_MESSAGE, UsuarioPostgresqlDAOMessage.DELETE_SQL_EXCEPTION_USER_MESSAGE, exception);
        }catch(Exception exception) {
        	throw CompuconnectDataException.create(UsuarioPostgresqlDAOMessage.DELETE_EXCEPTION_TECHNICAL_MESSAGE, UsuarioPostgresqlDAOMessage.DELETE_EXCEPTION_USER_MESSAGE, exception);
        }
    }

    @Override
    protected String prepareSelect() {
        return "SELECT identificador, tipo_usuario, nombre, tipo_identificacion, identificacion, correo_institucional ";
    }

    @Override
    protected String prepareFrom() {
        return "FROM Usuario";
    }
    @Override
    protected String prepareWhere(final UsuarioEntity entity, List<Object> parameters) {
        final var where = new StringBuilder("");
        parameters = UtilObject.getDefault(parameters, new ArrayList<>());

        var setWhere = true;

        if (!UtilObject.isNull(entity)) {

            if (!UtilUUID.isDefault(entity.getIdentificador())) {
                parameters.add(entity.getIdentificador());
                where.append("WHERE identificador = ? ");
                setWhere = false;
            }

            if (!UtilObject.isNull(entity.getTipoUsuario())) {
                parameters.add(entity.getTipoUsuario());
                where.append(setWhere ? "WHERE " : "AND ").append("tipo_usuario = ? ");
                setWhere = false;
            }

            if (!UtilText.getUtilText().isEmpty(entity.getNombre())) {
                parameters.add(entity.getNombre());
                where.append(setWhere ? "WHERE " : "AND ").append("nombre = ? ");
                setWhere = false;
            }

            if (!UtilObject.isNull(entity.getTipoIdentificacion())) {
                parameters.add(entity.getTipoIdentificacion());
                where.append(setWhere ? "WHERE " : "AND ").append("tipo_identificacion = ? ");
                setWhere = false;
            }

            if (!UtilText.getUtilText().isEmpty(entity.getIdentificacion())) {
                parameters.add(entity.getIdentificacion());
                where.append(setWhere ? "WHERE " : "AND ").append("identificacion = ? ");
                setWhere = false;
            }

            if (!UtilText.getUtilText().isEmpty(entity.getCorreoInstitucional())) {
                parameters.add(entity.getCorreoInstitucional());
                where.append(setWhere ? "WHERE " : "AND ").append("correo_institucional = ? ");
                setWhere = false;
            }
        }

        return where.toString();
    }

    @Override
    protected String prepareOrderBy() {
        return "ORDER BY nombre ASC";
    }
}