/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package todolist;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author USER
 */
public class TaskStorage {
    public static final String USERS_FILE = "users.csv";

    // Save user credentials
    public static void saveUser(String username, String password, String Gmail) {
        File file = new File(TaskStorage.USERS_FILE);
        if(!file.exists())
            createUserFile();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE, true))) {
            writer.write(username + "," + password + "," + Gmail);
            writer.newLine();
            writer.close();
        }catch (IOException e) {
            OutputScene.failToValidate();
        }
    }
    
    public static void createUserFile(){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE, true))){
            writer.close();
        }catch(IOException e){
            OutputScene.failToCreateFile();
        }
    }

    public static boolean userExists(String username) {
        File file = new File(TaskStorage.USERS_FILE);
        if(!file.exists())
            createUserFile();
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(username)) {
                    return true;
                }
            }
            reader.close();
        } catch (IOException e) {
            OutputScene.failToValidate();
        }
        return false;
    }

    public static boolean validatePassword(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(username) && parts[1].equals(password)) {
                    return true;
                }
            }
            reader.close();
        } catch (IOException e) {
            OutputScene.failToValidate();
        }
        return false;
    }
    
    public static void createFile(String username){
        String recurringFilename = username + "_recurring_tasks.csv";
        String nonRecurringFilename = username + "_non_recurring_tasks.csv";
        
        try (BufferedWriter recurringWriter = new BufferedWriter(new FileWriter(recurringFilename));
         BufferedWriter nonRecurringWriter = new BufferedWriter(new FileWriter(nonRecurringFilename))) {

        recurringWriter.write("TaskName,Description,DueDate,Category,Priority,Complete,RecurringType,numComplete\n");
        nonRecurringWriter.write("TaskName,Description,DueDate,Category,Priority,Complete,Dependencies\n");
        
        recurringWriter.close();
        nonRecurringWriter.close();
        
        }catch (IOException e){
           OutputScene.failToCreateFile();
        }
    }
    
    
    public static void saveTasks(String username, List<Task> tasks) {
    String recurringFilename = username + "_recurring_tasks.csv";
    String nonRecurringFilename = username + "_non_recurring_tasks.csv";

    try (BufferedWriter recurringWriter = new BufferedWriter(new FileWriter(recurringFilename));
         BufferedWriter nonRecurringWriter = new BufferedWriter(new FileWriter(nonRecurringFilename)) ) {

        // Write headers
        recurringWriter.write("TaskName,Description,DueDate,Category,Priority,Complete,RecurringType,numComplete\n");
        nonRecurringWriter.write("TaskName,Description,DueDate,Category,Priority,Complete,Dependencies\n");

        // Save tasks
        for (Task task : tasks) {
            if (task.isRecurring()) {
                recurringWriter.write(String.join(",",
                        task.getTitleName(),
                        task.getTitleDescription(),
                        task.getDuedate(),
                        task.getCategory(),
                        task.getPriority(),
                        String.valueOf(task.isComplete()),
                        task.getRecurringType(),
                        String.valueOf(task.getNumRecurringComplete())
                ));
                recurringWriter.newLine();
            } else {
                String dependencies = String.join(";", task.getDependenciesTitles());
                nonRecurringWriter.write(String.join(",",
                        task.getTitleName(),
                        task.getTitleDescription(),
                        task.getDuedate(),
                        task.getCategory(),
                        task.getPriority(),
                        String.valueOf(task.isComplete()),
                        dependencies
                ));
                nonRecurringWriter.newLine();
            }
        }
        recurringWriter.close();
        nonRecurringWriter.close();

    } catch (IOException e) {
        OutputScene.failToSaveData();
    }
}
  
public static List<Task> loadTasks(String username) {
    List<Task> tasks = new ArrayList<>();

    String recurringFilename = username + "_recurring_tasks.csv";
    String nonRecurringFilename = username + "_non_recurring_tasks.csv";
     
    // Load recurring tasks
    try (BufferedReader recurringReader = new BufferedReader(new FileReader(recurringFilename))) {
        recurringReader.readLine(); 
        String line;
        while ((line = recurringReader.readLine()) != null) 
        {
            String[] parts = line.split(",", -1);
            if (parts.length < 8) {
                continue;
            }
            Task recurringTask = new Task(
                    parts[0], // TaskName
                    parts[1], // Description
                    parts[2], // DueDate
                    parts[3], // Category
                    parts[4], // Priority
                    Boolean.parseBoolean(parts[5]), // Complete
                    true, // isRecurring
                    parts[6],// RecurringType
                    Integer.parseInt(parts[7]) //numComplete
            );
            tasks.add(recurringTask);
        }
        recurringReader.close();
    } catch (FileNotFoundException e) {
        OutputScene.failToFindFile();
    } catch (IOException e) {
        OutputScene.failToLoadFile();
    }
    
    HashMap<Task, String> dependencyMap = new HashMap<>();
    // Load non-recurring tasks
    try (BufferedReader nonRecurringReader = new BufferedReader(new FileReader(nonRecurringFilename))) {
        nonRecurringReader.readLine(); // Skip header
        String line;
        while ((line = nonRecurringReader.readLine()) != null) 
        {
            String[] parts = line.split(",", -1);
            if (parts.length < 6) {
                continue;
            }
            Task nonRecurringTask = new Task(
                    parts[0], // TaskName
                    parts[1], // Description
                    parts[2], // DueDate
                    parts[3], // Category
                    parts[4], // Priority
                    Boolean.parseBoolean(parts[5]), // Complete
                    false // isRecurring
            );
            if (parts.length > 6 && !parts[6].isEmpty()) {
                dependencyMap.put(nonRecurringTask, parts[6]);
            }
            tasks.add(nonRecurringTask);
        }
        nonRecurringReader.close();
        for (Task t : dependencyMap.keySet()) {
            String[] dependencyTitles = dependencyMap.get(t).split(";");
            for (String title : dependencyTitles) {
                Task dependency = findTaskByTitle(tasks, title);
                if (dependency != null) {
                    t.addDependency(dependency);
                }
            }
        }
    } catch (FileNotFoundException e) {
        OutputScene.failToFindFile();
    } catch (IOException e) {
        OutputScene.failToLoadFile();
    }
    return tasks;
}

private static Task findTaskByTitle(List<Task> tasks, String title) {
        for (Task task : tasks) {
            if (task != null && title.equals(task.getTitleName())) {
                return task;
            }
        }
        return null;
    }
}
