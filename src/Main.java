/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Divya
 */
import java.util.*;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int choice;
        Scanner in=new Scanner(System.in);
        String booking_date;
        String start_time_slot;
        int patient_id;
        DBConnect connect=new DBConnect();
        System.out.println("1.Book Appointment\n2.Cancel Appointment\n3.View Appointments\n4.Exit");
        System.out.println("Enter yout choice");
        choice=in.nextInt();
        String choice1=in.nextLine();
        switch(choice){
            case 1:
                System.out.println("Enter the date (yyyy-mm-dd)");
                booking_date=in.nextLine();
                System.out.println("Enter the start time slot (9AM-12PM and 3PM-8PM) Ex-10:00 or 11:15");
                start_time_slot=in.nextLine();
                System.out.println("Enter Patient id");
                patient_id=in.nextInt();
                System.out.println(connect.bookAppointment(booking_date, start_time_slot, patient_id));
                break;
            case 2:
                System.out.println("Enter the date (yyyy-mm-dd)");
                booking_date=in.nextLine();
                System.out.println("Enter the start time slot (9AM-12PM and 3PM-8PM) Ex-10:00 or 11:15");
                start_time_slot=in.nextLine();
                System.out.println("Enter Patient id");
                patient_id=in.nextInt();
                System.out.println(connect.cancelAppointment(booking_date, start_time_slot, patient_id));
                break;
            case 3:
                System.out.println("Enter the date (yyyy-mm-dd)");
                booking_date=in.nextLine();
                System.out.println(connect.viewAppointments(booking_date));
                break;
            case 4:
                System.out.println("Thank you");
                break;
            default:
                System.out.println("Please enter a valid choice");
                
        }
        
    }
}
