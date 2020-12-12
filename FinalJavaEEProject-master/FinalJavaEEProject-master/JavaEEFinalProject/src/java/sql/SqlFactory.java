/*
 * Josh Morris 
 * course: GEX
 * This assignment represents my own work and is in accordance with the College Academic Policy
 */
package sql;

/**
 *
 * @author jash1
 */
public class SqlFactory {
    public static ISqlRepo createRepo(){
        return new MySqlCommands();
    }
}
