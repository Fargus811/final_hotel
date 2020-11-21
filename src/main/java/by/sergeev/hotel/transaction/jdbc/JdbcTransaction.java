package by.sergeev.hotel.transaction.jdbc;

import by.sergeev.hotel.transaction.Transaction;

public abstract class JdbcTransaction implements Transaction {

    public abstract void getConnection();

    public abstract void commit();

    public abstract void rollback();
}
