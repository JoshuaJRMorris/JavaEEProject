/*
 * Josh Morris 
 * course: GEX
 * This assignment represents my own work and is in accordance with the College Academic Policy
 */
package sql;

import com.NBCC.Employee;
import com.NBCC.Job;
import com.NBCC.Task;
import com.NBCC.Team;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author jash1
 */
public class MySqlCommands implements ISqlRepo{
       @Override
    public void InsertToEmployee(Employee tmpEmployee) {
        String stmt = "";
        java.util.Date creationDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(creationDate.getTime());
        
        try{
            try (Connection conn = DatabaseConnection.initializeDatabase()) {                    
                stmt = "INSERT INTO Employee VALUES(NULL,?,?,?,?,?)";
                
                try (PreparedStatement st = conn.prepareStatement(stmt)) {
                    st.setString(1, tmpEmployee.getFirstName());
                    st.setString(2,tmpEmployee.getLastName());
                    st.setString(3,tmpEmployee.getSIN());
                    st.setDouble(4,tmpEmployee.getPayRate());
                    st.setDate(5, sqlDate);
                    
                    st.executeUpdate();
                }
            }   
        }
        catch(ClassNotFoundException | SQLException e){
        }
    }

    @Override
    public void InsertToTeam(Team tmpTeam) {
        String stmt = "";
        
        
        
        try{
            try (Connection conn = DatabaseConnection.initializeDatabase()) {                    
                stmt = "INSERT INTO Team VALUES(NULL,?, NULL)";
                
                try (PreparedStatement st = conn.prepareStatement(stmt)) {
                    st.setString(1, tmpTeam.getName());
                    
                    
                    st.executeUpdate();
                }
            }   
        }
        catch(ClassNotFoundException | SQLException e){
        }
    }

    @Override
    public void InsertToJob(Job tmpJob) {
        String stmt = "";
        
        
        try{
            try (Connection conn = DatabaseConnection.initializeDatabase()) {                    
                stmt = "INSERT INTO Job VALUES(NULL,?,?)";
                
                try (PreparedStatement st = conn.prepareStatement(stmt)) {
                    st.setString(1, tmpJob.getClientName());
                    st.setString(2,tmpJob.getDescription());
                    
                    
                    st.executeUpdate();
                }
            }   
        }
        catch(ClassNotFoundException | SQLException e){
        }
    }

    @Override
    public void InsertToTask(Task tmpTask) {
        String stmt = "";
        
        
        try{
            try (Connection conn = DatabaseConnection.initializeDatabase()) {                    
                stmt = "INSERT INTO Job VALUES(NULL,?,?,?, NULL)";
                
                try (PreparedStatement st = conn.prepareStatement(stmt)) {
                    st.setString(1, tmpTask.getName());
                    st.setString(2,tmpTask.getDescription());
                    st.setString(3, tmpTask.getLength());
                    
                    
                    st.executeUpdate();
                }
            }   
        }
        catch(ClassNotFoundException | SQLException e){
        }
    }
    
}
