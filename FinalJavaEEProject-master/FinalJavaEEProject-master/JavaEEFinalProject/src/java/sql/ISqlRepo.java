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

/**
 *
 * @author jash1
 */
public interface ISqlRepo {
 
        public void         InsertToEmployee(Employee tmpEmployee);
        public void         InsertToTeam(Team tmpTeam);
        public void         InsertToJob(Job tmpJob);
        public void         InsertToTask(Task tmpTask);
//    
//    //basic select statements
//    public Employee     SelectEmployee(int employeeID);
//    public Team         SelectTeam(int teamID);
//    public Job          SelectJob(int jobID);
//    public Task         SelectTask(int taskID);
}
