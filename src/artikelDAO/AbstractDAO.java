package artikelDAO;

import org.postgresql.core.SqlCommand;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class AbstractDAO<T> {
    protected HashMap<Long, T> cache = new HashMap<>();

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/xdb?user=abis&password=abis");
    }

    protected T read(Long id) {
        T result = cache.get(id);
        if(result != null) {
            return result;
        }
        try{
            PreparedStatement findStatement = null;
            Connection db = getConnection();
            findStatement = db.prepareStatement(findStatement());
            findStatement.setLong(1, id);
            ResultSet rs = findStatement.executeQuery();

            if(!rs.next()) {
                return null;
            }
            result = doLoad(rs);
            return result;
        } catch (SQLException ex){
            return null;
        }
    }

    protected ArrayList<T>  read(String condition) throws SQLException {
        try {
            ArrayList<T> resultList = new ArrayList<>();
            PreparedStatement findStatement = null;
            Connection db = getConnection();
            findStatement = db.prepareStatement(findStatementBase() + condition);
            ResultSet rs = findStatement.executeQuery();

            while(rs.next()) {
                T result = doLoad(rs);
                resultList.add(result);
            }
            return resultList;
        } catch (SQLException ex) {
            return null;
        }
    }

    protected long create (T o) throws SQLException {
        long id = getKey(o);
        if(cache.containsKey(id)) throw new SQLException("Objekt bereits vorhanden: " + id);
        id = doInsert(o);
        cache.put(id, o);
        return id;
    }

    protected void update(T o) throws SQLException {
        long id = doUpdate(o);
        cache.put(id, o);
    }

    protected void delete (Long id) throws SQLException {
        PreparedStatement deleteStatement = null;
        Connection db = getConnection();
        deleteStatement = db.prepareStatement(deleteStatement());
        deleteStatement.setLong(1, id);
        deleteStatement.execute();
        cache.remove(id);
    }

    protected abstract Long getKey(T o);
    protected abstract String findStatementBase();
    protected abstract String findStatement();
    protected abstract String deleteStatement();
    protected abstract Long doInsert(T o) throws SQLException;
    protected abstract Long doUpdate(T o) throws SQLException;
    protected abstract T doLoad(ResultSet rs) throws SQLException;
}
