/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package todolist;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Task {
    private String TaskName;
    private String TaskDescription;
    private String Duedate;
    private String Category;
    private String Priority;
    private boolean isComplete;
    private boolean isRecurring;
    private String recurringType;
    private List<Task> dependencies;
    private int numComplete;
    //recurring
    public Task(String TaskName, String TaskDescription, String Duedate, String Category, String Priority, boolean isComplete, boolean isRecurring, String recurringType, int numComplete){
        this.TaskName=TaskName;
        this.TaskDescription=TaskDescription;
        this.Duedate=Duedate;
        this.Category=Category;
        this.Priority=Priority;
        this.isComplete=isComplete;
        this.isRecurring=isRecurring;
        this.recurringType=recurringType;
        this.numComplete=numComplete;
    }
    
    //non-recurring
    public Task(String TaskName, String TaskDescription, String Duedate, String Category, String Priority, boolean isComplete, boolean isRecurring){
        this.TaskName=TaskName;
        this.TaskDescription=TaskDescription;
        this.Duedate=Duedate;
        this.Category=Category;
        this.Priority=Priority;
        this.isComplete=isComplete;
        this.isRecurring=isRecurring;
        this.dependencies = new ArrayList<>();
    }
    
    public String getTitleName(){
        return TaskName;
    }
    
    public String getTitleDescription(){
        return TaskDescription;
    }
    
    public String getDuedate(){
        return Duedate;
    }
    
    public String getCategory(){
        return Category;
    }
    
    public String getPriority(){
        return Priority;
    }
    
    public boolean isComplete(){
        return isComplete;
    }
    
    public boolean isRecurring(){
        return isRecurring;
    }
    
    public String getRecurringType(){
        return recurringType;
    }
    
    public int getNumRecurringComplete(){
        return numComplete;
    }
    
    public void setComplete(){
        this.isComplete=true;
    }
    
    public List<Task> getDependencies(){
        return dependencies;
    }
    
    public void addNumRecurringComplete(){
        this.numComplete++;
    }
    
    public void addDependency(Task task) {
         this.dependencies.add(task);
    }

    public void findNextDuedate(String interval, String dueDate){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate currentDate = LocalDate.parse(dueDate,format);
        LocalDate NextdueDate; 
        
        switch (interval) 
        {
            case "daily":
                NextdueDate = currentDate.plusDays(1);
                break;
            case "weekly":
                NextdueDate = currentDate.plusWeeks(1);
                break;
            default:
                NextdueDate = currentDate.plusMonths(1);
                break;
        }       
        this.Duedate= NextdueDate.toString(); 
    }
    
    public void editTaskName(String edit){
        this.TaskName=edit;
    }
    
    public void editTaskDescription(String edit){
        this.TaskDescription=edit;
    }
    
    public void editTaskDuedate(String edit){
        this.Duedate=edit;
    }
    
    public void editTaskCategory(String edit){
        this.Category=edit;
    }
    
    public void editTaskPriority(String edit){
        this.Priority=edit;
    }
    
    public List<String> getDependenciesTitles() {
        if (dependencies == null || dependencies.isEmpty()) {
            return Collections.emptyList();
        }
        List<String> titles = new ArrayList<>();
        for (Task dependency : dependencies) {
            titles.add(dependency.getTitleName());
        }
        return titles;
    }
    
}
