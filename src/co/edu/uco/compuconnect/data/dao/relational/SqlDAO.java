package co.edu.uco.compuconnect.data.dao.relational;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import co.edu.uco.compuconnect.crosscutting.exceptions.CompuconnectDataException;
import co.edu.uco.compuconnect.crosscutting.utils.Messages.SqlDAOMessage;
import co.edu.uco.compuconnect.crosscutting.utils.UtilSql;

public abstract class SqlDAO<E> {
	
	private Connection connection;
	
	protected SqlDAO(final Connection connection) {
		setConnection(connection);
	}

	protected final Connection getConnection() {
		return connection;
	}

	private final void setConnection(final Connection connection) {
		if(!UtilSql.connectionIsOpen(connection)) {
			throw CompuconnectDataException.create(SqlDAOMessage.SET_CONNECTION_EXCEPTION_TECHNICAL_MESSAGE, SqlDAOMessage.SET_CONNECTION_EXCEPTION_USER_MESSAGE);
		}
		
		this.connection = connection;
	}
	
	protected abstract String prepareSelect();
	
	protected abstract String prepareFrom();
	
	protected abstract String prepareWhere(E entity, List<Object> parameters);
	
	protected abstract String prepareOrderBy();
	
	protected abstract void setParameters(PreparedStatement prepareStat, List<Object> parameters);
	
	protected abstract List<E>executeQuery(PreparedStatement preparedStatement);
	

}

