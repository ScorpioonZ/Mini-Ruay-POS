package RuayRuayMain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CSDB {
    
    public static void execute(String insertSql, Object... params) {
        System.out.println("CSDB.execute()");
        try (CSMariaDBConn connDB = new CSMariaDBConn();
        	Connection con = connDB.getConnection();
             PreparedStatement pstmt = con.prepareStatement(insertSql)) {
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }

            pstmt.executeUpdate();
            System.out.println("SQL executed successfully: " + insertSql);

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            System.err.println("Error executing SQL: " + ex.getMessage());
        }
    }
}


