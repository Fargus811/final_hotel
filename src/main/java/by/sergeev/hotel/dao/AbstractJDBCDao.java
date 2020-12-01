package by.sergeev.hotel.dao;

import by.sergeev.hotel.pool.ProxyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Abstract dao.
 *
 * @param <T> the type parameter
 *
 * @author Daniil Sergeev
 * @version 1.0
 */
public abstract class AbstractJDBCDao<T> {

    /**
     * Try find entity by pr statement t.
     *
     * @param proxyConnection the proxy connection
     * @param query           the query
     * @param master          the master
     * @param params          the params
     * @return the t
     * @throws SQLException the sql exception
     */
    protected T tryFindEntityByPrStatement(ProxyConnection proxyConnection, String query, StatementMaster master, Object... params) throws SQLException {
        List<T> entityList = tryFindEntityListByPrStatement(proxyConnection, query, master, params);
        T entity = (entityList.isEmpty()) ? null : entityList.get(0);
        return entity;
    }

    /**
     * Try find entity list by pr statement list.
     *
     * @param proxyConnection the proxy connection
     * @param query           the query
     * @param master          the master
     * @param params          the params
     * @return the list
     * @throws SQLException the sql exception
     */
    protected List<T> tryFindEntityListByPrStatement(ProxyConnection proxyConnection, String query, StatementMaster master, Object... params) throws SQLException {
        try (PreparedStatement preparedSt = proxyConnection.prepareStatement(query)) {
            master.prepare(preparedSt, params);
            try (ResultSet rs = preparedSt.executeQuery()) {
                List<T> list = makeEntityList(rs);
                return list;
            }
        }
    }

    /**
     * Try find entity list by query list.
     *
     * @param proxyConnection the proxy connection
     * @param query           the query
     * @return the list
     * @throws SQLException the sql exception
     */
    protected List<T> tryFindEntityListByQuery(ProxyConnection proxyConnection, String query) throws SQLException {
        try (Statement st = proxyConnection.createStatement()) {
            try (ResultSet rs = st.executeQuery(query)) {
                List<T> entityList = makeEntityList(rs);
                return entityList;
            }
        }
    }

    private List<T> makeEntityList(ResultSet rs) throws SQLException {
        List<T> list = new ArrayList<T>();
        while (rs.next()) {
            T entity = makeEntity(rs);
            list.add(entity);
        }
        return list;
    }

    /**
     * Make entity t.
     *
     * @param rs the rs
     * @return the t
     * @throws SQLException the sql exception
     */
    protected abstract T makeEntity(ResultSet rs) throws SQLException;

}

