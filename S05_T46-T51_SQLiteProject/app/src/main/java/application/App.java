/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package application;

import java.sql.DriverManager;
import java.sql.SQLException;

public class App {
    public String getGreeting() {
        return "Hello World! from S05_T46-T51";
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        System.out.println(new App().getGreeting());
        
        Class.forName("org.sqlite.JDBC");
        
        // Find the database
        String dbUrl = "jdbc:sqlite:people.db";
        var conn = DriverManager.getConnection(dbUrl);
        
        System.out.println(conn);
        
        var stmt = conn.createStatement();
        
        String sql = "create table if not exists user(id integer primary key, name text not null)";
        stmt.execute(sql);
        
        sql = "insert into user(id, name) values(0, 'Bob')";
        stmt.execute(sql);
        
        sql = "insert into user(id, name) values(1, 'Mary')";
        stmt.execute(sql);

        sql = "select id, name from user";
        var rs = stmt.executeQuery(sql);
        
        while (rs.next()) {
        	int id = rs.getInt("id");
        	String name = rs.getString("name");
        	
        	System.out.println(id + ": " + name);
        }
        
        sql = "drop table user";
        stmt.execute(sql);
        
        // Close open database resources
        stmt.close();        
        conn.close();
    }
}
