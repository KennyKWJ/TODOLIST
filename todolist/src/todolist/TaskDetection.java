/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package todolist;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskDetection {
    

    public static boolean hasValidTimeFormat(String due_date)
    {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate date = LocalDate.parse(due_date, format); 
            if(date.isBefore(LocalDate.now())||date.isEqual(LocalDate.now()))
            {
                return false;
            }
            else  
                return true; 
        } catch (DateTimeParseException e) {
            return false; 
        }
    }
    
    public static boolean hasValidCategoryFormat(String categoryState)
    {
        return (categoryState.equals("Homework") || categoryState.equals("Personal") || categoryState.equals("Work"));
    }
    
    public static boolean hasValidPriorityFormat(String priorityState)
    {
        return ((priorityState.equals("Low") || priorityState.equals("Medium") || priorityState.equals("High")));
    }
    
    public static boolean hasValidIntervalFormat(String interval)
    {
        return (interval.equals("daily") || interval.equals("weekly") || interval.equals("monthly"));
    }
    
    
    public static LocalDate findDuedate(String interval)
    {
        LocalDate currentDate = LocalDate.now();
        LocalDate dueDate;  
        
        switch (interval) 
        {
            case "daily":
                dueDate = currentDate.plusDays(1);
                break;
            case "weekly":
                dueDate = currentDate.plusWeeks(1);
                break;
            default:
                dueDate = currentDate.plusMonths(1);
                break;
        }
        
        return dueDate;      
    }

    public static Boolean seacrhwordDetect(String searchword){
       return searchword.isEmpty()||searchword.isBlank();       
    }
    
    public static ArrayList<String> detectNearDueDate(){
        ArrayList<String> collector = new ArrayList<>();
        for(int i=0;i<Todolist.taskList.size();i++)
        {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate tempDate = LocalDate.parse(Todolist.taskList.get(i).getDuedate(), format);

            String temp =Todolist.taskList.get(i).getTitleName();
            if(isDueWithin24Hours(tempDate) && !Todolist.taskList.get(i).isComplete())
            {
                collector.add(temp);
            }
        }
        return collector;
    }
    
    private static boolean isDueWithin24Hours(LocalDate dueDate){
        
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime dueDateTime = dueDate.atStartOfDay();
        
        Duration duration = Duration.between(currentDateTime, dueDateTime);
        
        return duration.toHours() <=24 && duration.isPositive();
        
    }
    
    public static boolean dependentisBeforePreceding(String dependentTaskNumberDate, String precedingTaskNumberDate)
    {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dependentDate = LocalDate.parse(dependentTaskNumberDate, format);
        LocalDate precedingDate = LocalDate.parse(precedingTaskNumberDate, format);
        
        return dependentDate.isBefore(precedingDate);
    }
   
            
}
