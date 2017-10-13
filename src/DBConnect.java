/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Divya
 */
import java.sql.*;
import java.util.*;

public class DBConnect {
    private Connection conn;
    private Statement stmt;
    private ResultSet result;
    
    public DBConnect(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","");
            stmt=conn.createStatement();
            
            
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    public String bookAppointment(String date,String start_time_slot,int patient_id){
        int count=0;
        int booking_id=1000;
        try{
            result=stmt.executeQuery("select count(*) from booking_details where booking_date='"+date+"' and booking_start_time_slot='"+start_time_slot+"'");
            while(result.next())
            {
                count=result.getInt("count(*)");
            }
            if(count<5){
                    
                result=stmt.executeQuery("select max(booking_id) from booking_details");
                while(result.next()){
                    booking_id=result.getInt("max(booking_id)");
                }
                result=stmt.executeQuery("insert into booking_details (`booking_id`, `booking_date`, `booking_start_time_slot`, `patient_id`) VALUES ('"+booking_id+"', '"+date+"', '"+start_time_slot+"', '"+patient_id+"')");
                return Integer.toString(booking_id);
            }
            else
                return "Booking is full for the selected date and time slot";
                    
        }
        catch(Exception ex){
            return (ex.getMessage());
        }
        
    }
    
    
    public int cancelAppointment(String date,String start_time_slot,int patient_id){
        int booking_id=0;
        try{
            result=stmt.executeQuery("select booking_id from booking_details where booking_date='"+date+"' and booking_start_time_slot='"+start_time_slot+"' and patient_id='"+patient_id+"'");
            while(result.next())
            {
                booking_id=result.getInt("booking_id");
            }
            if(booking_id!=0){
                    
                result=stmt.executeQuery("delete from booking_details where booking_id='"+booking_id+"'");
                return booking_id;
                
            }
            else
                return -1;
                    
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
            return 0;
        }
        
    }
    public String viewAppointments(String date){
        try{
            result=stmt.executeQuery("select * from booking_details where booking_date='"+date+"'");
            if(result.next()){
                System.out.println("Appointments for date "+date+" are:");
                System.out.println("Start time slot\tPatient ID");
                System.out.println(result.getString("booking_start_time_slot")+"\t\t"+result.getString("patient_id"));
            }
            else
                System.out.println("There is no Appointments for date "+date);
            while(result.next()){
                System.out.println(result.getString("booking_start_time_slot")+"\t\t"+result.getString("patient_id"));
            }
            
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
        return "";
        
    }
}
