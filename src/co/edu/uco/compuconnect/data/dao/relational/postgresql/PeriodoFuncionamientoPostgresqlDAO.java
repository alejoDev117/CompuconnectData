package co.edu.uco.compuconnect.data.dao.relational.postgresql;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import co.edu.uco.compuconnect.crosscutting.exceptions.CompuconnectDataException;
import co.edu.uco.compuconnect.crosscutting.utils.Messages.PeriodoFuncionamientoPostgresqlDAOMessage;
import co.edu.uco.compuconnect.crosscutting.utils.UtilObject;
import co.edu.uco.compuconnect.crosscutting.utils.UtilUUID;
import co.edu.uco.compuconnect.data.dao.PeriodoFuncionamientoDAO;
import co.edu.uco.compuconnect.data.dao.relational.SqlDAO;
import co.edu.uco.compuconnect.entities.DiaFestivoEntity;
import co.edu.uco.compuconnect.entities.EstadoPeriodoFuncionamientoEntity;
import co.edu.uco.compuconnect.entities.PeriodoFuncionamientoEntity;


public final class PeriodoFuncionamientoPostgresqlDAO extends SqlDAO<PeriodoFuncionamientoEntity> implements PeriodoFuncionamientoDAO {

    public PeriodoFuncionamientoPostgresqlDAO(final Connection connection) {
        super(connection);
    }

    @Override
    public void create(PeriodoFuncionamientoEntity entity) {
        String sqlStatement = "INSERT INTO periodo_funcionamiento (identificador, nombre, fecha_inicio, fecha_fin, estado, dia_festivo) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = getConnection().prepareStatement(sqlStatement)) {
            statement.setObject(1, entity.getIdentificador());
            statement.setString(2, entity.getNombre());
            statement.setObject(3, entity.getFechaInicio());
            statement.setObject(4, entity.getFechaFin());
            statement.setObject(5, entity.getEstado().getIdentificador());
            statement.setObject(6, entity.getDiaFestivo().getIdentificador());

            statement.executeUpdate();
        } catch (final SQLException exception) {
            throw CompuconnectDataException.create(
                    PeriodoFuncionamientoPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_TECHNICAL_MESSAGE,
                    PeriodoFuncionamientoPostgresqlDAOMessage.CREATE_SQL_EXCEPTION_USER_MESSAGE, exception);

        } catch (final Exception exception) {
            throw CompuconnectDataException.create(
                    PeriodoFuncionamientoPostgresqlDAOMessage.CREATE_EXCEPTION_TECHNICAL_MESSAGE,
                    PeriodoFuncionamientoPostgresqlDAOMessage.CREATE_EXCEPTION_USER_MESSAGE, exception);
        }
    }

    @Override
    public List<PeriodoFuncionamientoEntity> read(PeriodoFuncionamientoEntity entity) {
        var sqlStatement = new StringBuilder();
        var listParameters = new ArrayList<>();

        sqlStatement.append(prepareSelect());
        sqlStatement.append(prepareFrom());
        sqlStatement.append(prepareWhere(entity, listParameters));
        sqlStatement.append(prepareOrderBy());

        try (var prepareStatement = getConnection().prepareStatement(sqlStatement.toString())) {
            setParameters(prepareStatement, listParameters);
            return executeQuery(prepareStatement);

        } catch (CompuconnectDataException exception) {
            throw exception;
        } catch (SQLException exception) {
            throw CompuconnectDataException.create(
                    PeriodoFuncionamientoPostgresqlDAOMessage.READ_SQL_EXCEPTION_TECHNICAL_MESSAGE,
                    PeriodoFuncionamientoPostgresqlDAOMessage.READ_SQL_EXCEPTION_USER_MESSAGE, exception);
        } catch (Exception exception) {
            throw CompuconnectDataException.create(
                    PeriodoFuncionamientoPostgresqlDAOMessage.READ_EXCEPTION_TECHNICAL_MESSAGE,
                    PeriodoFuncionamientoPostgresqlDAOMessage.READ_EXCEPTION_USER_MESSAGE, exception);
        }

    }

    @Override
    public void update(PeriodoFuncionamientoEntity entity) {
        String sqlStatement = "UPDATE periodo_funcionamiento SET nombre = ?, fecha_inicio = ?, fecha_fin = ?, estado = ?, dia_festivo = ? WHERE identificador = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sqlStatement)) {
            statement.setString(1, entity.getNombre());
            statement.setDate(2, new java.sql.Date(entity.getFechaInicio().getTime()));
            statement.setDate(3, new java.sql.Date(entity.getFechaFin().getTime()));
            statement.setObject(4, entity.getEstado().getIdentificador());
            statement.setObject(5, entity.getDiaFestivo().getIdentificador());
            statement.setObject(6, entity.getIdentificador());

            statement.executeUpdate();
        } catch (SQLException exception) {
            throw CompuconnectDataException.create(
                    PeriodoFuncionamientoPostgresqlDAOMessage.UPDATE_SQL_EXCEPTION_TECHNICAL_MESSAGE,
                    PeriodoFuncionamientoPostgresqlDAOMessage.UPDATE_SQL_EXCEPTION_USER_MESSAGE, exception);
        } catch (Exception exception) {
            throw CompuconnectDataException.create(
                    PeriodoFuncionamientoPostgresqlDAOMessage.UPDATE_EXCEPTION_TECHNICAL_MESSAGE,
                    PeriodoFuncionamientoPostgresqlDAOMessage.UPDATE_EXCEPTION_USER_MESSAGE, exception);
        }
    }

    @Override
    public void delete(PeriodoFuncionamientoEntity entity) {
        String sqlStatement = "DELETE FROM periodo_funcionamiento WHERE identificador = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sqlStatement)) {
            statement.setObject(1, entity.getIdentificador());

            statement.executeUpdate();
        } catch (SQLException exception) {
            throw CompuconnectDataException.create(
                    PeriodoFuncionamientoPostgresqlDAOMessage.DELETE_SQL_EXCEPTION_TECHNICAL_MESSAGE,
                    PeriodoFuncionamientoPostgresqlDAOMessage.DELETE_SQL_EXCEPTION_USER_MESSAGE, exception);
        } catch (Exception exception) {
            throw CompuconnectDataException.create(
                    PeriodoFuncionamientoPostgresqlDAOMessage.DELETE_EXCEPTION_TECHNICAL_MESSAGE,
                    PeriodoFuncionamientoPostgresqlDAOMessage.DELETE_EXCEPTION_USER_MESSAGE, exception);
        }
    }

    @Override
    protected String prepareSelect() {
        return "SELECT pf.identificador, pf.nombre, pf.fecha_inicio, pf.fecha_fin, df.identificador, epf.identificador, epf.nombre, epf.descripcion ";
    }

    @Override
    protected String prepareFrom() {
        return "FROM periodo_funcionamiento pf JOIN estados_periodo_funcionamiento epf ON epf.identificador = pf.estado JOIN dia_festivo df ON df.identificador = pf.dia_festivo ";
    }

    @Override
    protected String prepareWhere(final PeriodoFuncionamientoEntity entity, List<Object> parameters) {

        final var where = new StringBuilder("");
        parameters = UtilObject.getDefault(parameters, new ArrayList<>());
        var setWhere = true;

        if (!UtilObject.isNull(entity)) {

            if (!UtilUUID.isDefault(entity.getIdentificador())) {
                parameters.add(entity.getIdentificador());
                where.append("WHERE identificador = ? ");
                setWhere = false;
            }

            if (entity.getNombre() != null) {
                parameters.add(entity.getNombre());
                where.append(setWhere ? "WHERE" : "AND ").append("nombre = ? ");
                setWhere = false;
            }

            if (entity.getFechaInicio() != null) {
                parameters.add(entity.getFechaInicio());
                where.append(setWhere ? "WHERE" : "AND ").append("fecha_inicio = ? ");
                setWhere = false;
            }

            if (entity.getFechaFin() != null) {
                parameters.add(entity.getFechaFin());
                where.append(setWhere ? "WHERE" : "AND ").append("fecha_fin = ? ");
                setWhere = false;
            }

            if (entity.getDiaFestivo() != null) {
                parameters.add(entity.getDiaFestivo().getIdentificador());
                where.append(setWhere ? "WHERE" : "AND ").append("dia_festivo = ? ");
                setWhere = false;
            }

            if (entity.getEstado() != null) {
                parameters.add(entity.getEstado().getIdentificador());
                where.append(setWhere ? "WHERE" : "AND ").append("estado = ? ");
            }
        }
        return where.toString();
    }

    @Override
    protected String prepareOrderBy() {
        return "ORDER BY nombre ASC";
    }

    @Override
    protected void setParameters(PreparedStatement prepareStat, List<Object> parameters) {
        try {

            if (!UtilObject.isNull(parameters) && !UtilObject.isNull(prepareStat)) {
                for (int index = 0; index < parameters.size(); index++) {
                    prepareStat.setObject(index + 1, parameters.get(index));

                }
            }
        } catch (SQLException exception) {
            throw CompuconnectDataException.create(
                    PeriodoFuncionamientoPostgresqlDAOMessage.SET_PARAMETERS_SQL_EXCEPTION_TECHNICAL_MESSAGE,
                    PeriodoFuncionamientoPostgresqlDAOMessage.SET_PARAMETERS_SQL_EXCEPTION_USER_MESSAGE, exception);
        } catch (Exception exception) {
            throw CompuconnectDataException.create(
                    PeriodoFuncionamientoPostgresqlDAOMessage.SET_PARAMETERS_EXCEPTION_TECHNICAL_MESSAGE,
                    PeriodoFuncionamientoPostgresqlDAOMessage.SET_PARAMETERS_EXCEPTION_USER_MESSAGE, exception);
        }
    }

    @Override
    protected List<PeriodoFuncionamientoEntity> executeQuery(PreparedStatement preparedStatement) {

        List<PeriodoFuncionamientoEntity> listResultSet = new ArrayList<>();

        try (var resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                var entityTmp = new PeriodoFuncionamientoEntity(resultSet.getObject("identificador", UUID.class),
                        resultSet.getString("nombre"), resultSet.getDate("fecha_inicio"),
                        resultSet.getDate("fecha_fin"), DiaFestivoEntity.getDefaultObject(),
                        EstadoPeriodoFuncionamientoEntity.create()
                                .setIdentificador(resultSet.getObject("identificador", UUID.class))
                                .setNombre(resultSet.getString("nombre"))
                                .setDescripcion(resultSet.getString("descripcion")));
                listResultSet.add(entityTmp);
            }

        } catch (SQLException exception) {
            throw CompuconnectDataException.create(
                    PeriodoFuncionamientoPostgresqlDAOMessage.EXCECUTE_QUERY_SQL_EXCEPTION_TECHNICAL_MESSAGE,
                    PeriodoFuncionamientoPostgresqlDAOMessage.EXCECUTE_QUERY_SQL_EXCEPTION_USER_MESSAGE, exception);
        } catch (Exception exception) {
            throw CompuconnectDataException.create(
                    PeriodoFuncionamientoPostgresqlDAOMessage.EXCECUTE_QUERY_EXCEPTION_TECHNICAL_MESSAGE,
                    PeriodoFuncionamientoPostgresqlDAOMessage.EXCECUTE_QUERY_EXCEPTION_USER_MESSAGE, exception);
        }

        return listResultSet;
    }
}
