package by.sergeev.hotel.transaction;

public interface Transaction {

    void commit();

    void rollback();
}
