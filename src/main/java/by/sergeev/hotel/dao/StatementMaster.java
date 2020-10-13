package by.sergeev.hotel.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface StatementMaster {

    void prepare(PreparedStatement preparedStatement, Object ... params) throws SQLException;
}
