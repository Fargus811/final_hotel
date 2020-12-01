package by.sergeev.hotel.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * The interface Statement master.
 *
 * @author Daniil Sergeev
 * @version 1.0
 */
@FunctionalInterface
public interface StatementMaster {

    /**
     * Prepare.
     *
     * @param preparedStatement the prepared statement
     * @param params            the params
     * @throws SQLException the sql exception
     */
    void prepare(PreparedStatement preparedStatement, Object... params) throws SQLException;
}
