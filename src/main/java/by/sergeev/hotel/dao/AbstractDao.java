package by.sergeev.hotel.dao;

import by.sergeev.hotel.pool.ProxyConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDao<T> {

    protected T tryFindEntityByPrStatement(ProxyConnection proxyConnection, String query, StatementMaster master, Object... params) throws SQLException {
        List<T> entityList = tryFindEntityListByPrStatement(proxyConnection, query, master, params);
        T entity = (entityList.isEmpty()) ? null : entityList.get(0);
        return entity;
    }

    protected List<T> tryFindEntityListByPrStatement(ProxyConnection proxyConnection, String query, StatementMaster master, Object... params) throws SQLException {
        try (PreparedStatement preparedSt = proxyConnection.prepareStatement(query)) {
            master.prepare(preparedSt, params);
            try (ResultSet rs = preparedSt.executeQuery()) {
                List<T> list = makeEntityList(rs);
                return list;
            }
        }
    }

    protected List<T> tryFindEntityListByQuery(ProxyConnection proxyConnection, String query) throws SQLException {
        try (Statement st = proxyConnection.createStatement()) {
            List<T> entityList = takeEntityListByQuery(query, st);
            return entityList;
        }
    }

    private List<T> takeEntityListByQuery(String query, Statement st) throws SQLException {
        try (ResultSet rs = st.executeQuery(query)) {
            List<T> entityList = makeEntityList(rs);
            return entityList;
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

    protected abstract T makeEntity(ResultSet rs) throws SQLException;


}

