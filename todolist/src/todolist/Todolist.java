/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package todolist;

import java.io.File;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.scene.control.Label;
import javafx.geometry.*;
import javafx.scene.control.TextField;

import java.util.*;

public class Todolist extends Application {
    
    Stage window;
    static ArrayList<Task> taskList = new ArrayList<>();
    public static String loggedInUsername;
    public static boolean firstTime=false;
    
    public static Button backButton(Stage window, Scene scene) {
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> window.setScene(scene));
        return backButton;
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        // Create Registration Scene
        VBox registerLayout = new VBox(10);
        registerLayout.setAlignment(Pos.CENTER);
        Label registerLabel = new Label("=== Register ===");
        
        Label gmail = new Label("Enter Gmail :");
        TextField gmailField = new TextField();
        gmailField.setPromptText("xxxxxx@gmail.com");
        
        Label user = new Label("Enter Username :");
        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter username");
        
        Label passwordCreate = new Label("Enter Password :");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter password");
        
        Button registerButton = new Button("Register");
        Button backToLoginButton = new Button("Back to Login");

        registerLayout.getChildren().addAll(registerLabel, gmail, gmailField, user, usernameField, passwordCreate, passwordField, registerButton, backToLoginButton);
        Scene registerScene = new Scene(registerLayout, 500, 500);

        // Create Login Scene
        VBox loginLayout = new VBox(10);
        loginLayout.setAlignment(Pos.CENTER);
        Label loginLabel = new Label("=== Login ===");
        
        Label userLog = new Label("Enter Username :");
        TextField loginUsernameField = new TextField();
        loginUsernameField.setPromptText("Enter username");
        
        Label passwordLog = new Label("Enter Password :");
        PasswordField loginPasswordField = new PasswordField();
        loginPasswordField.setPromptText("Enter password");
        
        Button loginButton = new Button("Login");
        Button goToRegisterButton = new Button("Register");

        loginLayout.getChildren().addAll(loginLabel,userLog, loginUsernameField, passwordLog,loginPasswordField, loginButton, goToRegisterButton);
        Scene loginScene = new Scene(loginLayout, 500, 500);

        // Handle Login
        loginButton.setOnAction(e -> {
            String username = loginUsernameField.getText();
            String password = loginPasswordField.getText();

            if (TaskStorage.userExists(username) && TaskStorage.validatePassword(username, password)) {
                loggedInUsername = username; 
                if(!firstTime)
                    taskList = new ArrayList<>(TaskStorage.loadTasks(username)); 
                else
                {
                    TaskStorage.createFile(username);
                }
                menu(); 
            } else {
                showAlert("Login Failed", "Invalid username or password.");
            }
        });

        registerButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String Gmail = gmailField.getText();
            if(username.isEmpty() || password.isEmpty() || !Gmail.contains("@gmail.com") || Gmail.contentEquals("@gmail.com"))
            {
                showAlert("Registration Failed","You may have entered the wrong format or left some options blank.");
            }
            else if (!TaskStorage.userExists(username)) 
            {
                TaskStorage.saveUser(username, password, Gmail);
                showAlert("Registration Successful", "You can now log in.");
                firstTime = true;
                window.setScene(loginScene);
            } else 
            {
                showAlert("Registration Failed", "Username already exists.");
            }
        });

        goToRegisterButton.setOnAction(e -> window.setScene(registerScene));
        backToLoginButton.setOnAction(e -> window.setScene(loginScene));
        
        window.setScene(loginScene);
        window.setTitle("To-Do List Login");
        window.show();
    }
    
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
 
    public void closeprogram() {
        JavaMail.SendMail();
        window.close();
    }
    
    public void menu()
    {
        Scene scene1;
        Label label1 = new Label("===To do List===");
        Label label2 = new Label("Choose your command:");
        
        Button button1 = new Button("Task Creation");
        Button button2 = new Button("Task Management");
        Button button3 = new Button("Task Deletion");
        Button button4 = new Button("Task Sorting");
        Button button5 = new Button("Task Searching");
        Button button6 = new Button("Task Recurring");
        Button button7 = new Button("Task View and Task Editing");
        Button button8 = new Button("View Task Dependencies");
        Button button9 = new Button("Data Analytics");
        Button close = new Button("Save and Exit");

        VBox layout1 = new VBox(15);
        layout1.getChildren().addAll(label1, label2, button1, button2, button3, button4, button5, button6, button7, button8 , button9, close);
        layout1.setAlignment(Pos.CENTER);
        scene1 = new Scene(layout1, 500, 500);
        
        //close 
        close.setOnAction(e -> closeprogram());
        window.setOnCloseRequest(e ->  closeprogram() );
        
        window.setTitle("TO DO LIST");
        window.setScene(scene1);
        window.show();
        
        button1.setOnAction(e -> {
            taskcreation(scene1);
                });
        
        button2.setOnAction(e ->{
            if(taskList.isEmpty())
                OutputScene.warning();
            else
                taskManagement(scene1);
        });
        
        button3.setOnAction(e ->{
            if(taskList.isEmpty())
                OutputScene.warning();
            else
                taskDeletion(scene1);
        });
        
        button4.setOnAction(e -> {
            if(taskList.isEmpty())
                OutputScene.warning();
            else
                taskSorting(scene1);
        });
        
        button5.setOnAction(e ->{
            if(taskList.isEmpty())
                OutputScene.warning();
            else
                taskSearching(scene1);
        });
        
        button6.setOnAction(e ->{
            Taskrecurring(scene1);
        });
        
        button7.setOnAction(e ->{
            if(taskList.isEmpty())
                OutputScene.warning();
            else
                TaskEditing(scene1);
        });
        
        button8.setOnAction(e ->{
            if(taskList.isEmpty())
                OutputScene.warning();
            else
                viewDependencies(scene1);
        });
        
        button9.setOnAction(e ->{
            if(taskList.isEmpty())
                OutputScene.warning();
            else
                dataAnalytics(scene1);
        });
    }
    //about task creation
    public void taskcreation(Scene scene1) 
    {
        Label titlelabel = new Label("===Task Creation===");
        Label label1 = new Label("Enter task title: ");
        TextField titleInput = new TextField();
        titleInput.setPromptText("Exp: Do BlackHole Research");

        Label label2 = new Label("Enter task description: ");
        TextField descriptionInput = new TextField();
        descriptionInput.setPromptText("Exp: Record the jet direction and related data of TON618");
        
        Label label3 = new Label("Enter due date (YYYY-MM-DD): ");
        TextField dateInput = new TextField();
        dateInput.setPromptText("YYYY-MM-DD");

        Label label4 = new Label("Enter task category (Homework, Personal, Work): ");
        TextField categoryInput = new TextField();
        categoryInput.setPromptText("Homework/Personal/Work");

        Label label5 = new Label("Priority level (Low, Medium, High): ");
        TextField priorityInput = new TextField();
        priorityInput.setPromptText("Low/Medium/High");
        
        Button Finish = new Button("Finish");
        Button Back = backButton(window, scene1);
        VBox layout11 = new VBox(10);
        layout11.getChildren().addAll(titlelabel,label1,titleInput,label2,descriptionInput,label3,
                dateInput,label4,categoryInput,label5,priorityInput,Finish,Back);
        layout11.setAlignment(Pos.CENTER);
        
        Scene scene11 = new Scene(layout11, 500, 500);
        window.setScene(scene11);
        window.setTitle("Task Creation");
        
        Finish.setOnAction(e ->{
            
            String getTitle = titleInput.getText();
            String getDescription = descriptionInput.getText();
            String getDuedate = dateInput.getText();
            String getCategory = categoryInput.getText();
            String getPriority = priorityInput.getText();
            
            if(TaskDetection.hasValidCategoryFormat(getCategory)
                    && TaskDetection.hasValidPriorityFormat(getPriority)
                    && !getTitle.isBlank() 
                    && !getDescription.isBlank() 
                    && TaskDetection.hasValidTimeFormat(getDuedate))
            {        
                boolean completeStatus= false;
                boolean recurring = false;
                Task createTask = new Task(getTitle, getDescription, getDuedate, getCategory, 
                        getPriority, completeStatus, recurring);
                taskList.add(createTask);
                TaskStorage.saveTasks(loggedInUsername, taskList);
                OutputScene.createResult(getTitle);
            }
            else
            {
                OutputScene.wrongFormat();
            }
            window.setScene(scene1);
            
        });
    }
 
    //about task mangement
    public void taskManagement(Scene scene1) {

        Label markTask = new Label("=== Mark Task as Complete ===");
        Label toComplete = new Label("Enter the task number you want to mark as complete: ");
        TextField indexInput = new TextField();
        indexInput.setPromptText("Exp: 1");
        
        Button comfirm = new Button("Confirm");
        Button Back = backButton(window, scene1);
        
        VBox layout21 = new VBox(20);
        layout21.getChildren().addAll(markTask, toComplete, indexInput, comfirm, Back);
        layout21.setAlignment(Pos.CENTER);
        
        Scene scene21 = new Scene(layout21,500, 500);
        window.setScene(scene21);
        window.setTitle("Task Management");
        
        comfirm.setOnAction(e ->{
            try{
                int index = Integer.parseInt(indexInput.getText())-1;
                if(index>=taskList.size() || index<0)
                {
                    OutputScene.outOfRange();
                }
                else
                {
                    if(Todolist.taskList.get(index).isComplete())
                        OutputScene.AlreadyCompleted(Todolist.taskList.get(index).getTitleName());
                    else
                    {
                        if(taskList.get(index).isRecurring())
                        {
                            String dueDate = taskList.get(index).getDuedate();
                            String interval = taskList.get(index).getRecurringType();
                            taskList.get(index).findNextDuedate(interval, dueDate);
                            taskList.get(index).addNumRecurringComplete();
                            OutputScene.manageRecurringResult(taskList.get(index).getTitleName(), taskList.get(index).getDuedate());
                            TaskStorage.saveTasks(loggedInUsername, taskList);
                        }
                        else
                        {
                            String taskName = Todolist.taskList.get(index).getTitleName();
                            TaskViewAndEditing.taskManager.markTaskAsCompleted(taskName,index);
                            TaskStorage.saveTasks(loggedInUsername, taskList);
                        }                 
                    }
                }    
            }catch(NumberFormatException i){
                OutputScene.wrongFormat();            
            }
            window.setScene(scene1);
        });
    }
    
    //about task Deletion
    public void taskDeletion(Scene scene1) 
    {  
        Label deleteTask = new Label("=== Delete a Task ===");
        Label toDelete = new Label("Enter the task number you want to delete: ");
        TextField indexInput = new TextField();
        indexInput.setPromptText("Exp: 1");
        
        Button confirm = new Button("Confirm");
        Button Back = backButton(window, scene1);
        
        VBox layout31 = new VBox(30);
        layout31.getChildren().addAll(deleteTask , toDelete , indexInput, confirm, Back);
        layout31.setAlignment(Pos.CENTER);
        
        Scene scene31 = new Scene(layout31, 500 ,500);
        window.setScene(scene31);
        window.setTitle("Task Deletion");
        
        confirm.setOnAction(e -> 
        {
            try{
                int index = Integer.parseInt(indexInput.getText())-1;
                if(index>=taskList.size() || index<0)
                    OutputScene.outOfRange();
                else
                {
                    String temp = taskList.get(index).getTitleName();
                    TaskViewAndEditing.taskManager.removeTask(temp);
                    taskList.remove(index);
                    TaskStorage.saveTasks(loggedInUsername, taskList);
                    OutputScene.deleteResult(temp);
                }
            }catch(NumberFormatException i){
                OutputScene.wrongFormat();              
            }
            window.setScene(scene1);
        });
    }
    
    //about task sorting
    public void taskSorting(Scene scene1) 
    {
        Label taskSort = new Label("=== Sort Tasks ===");
        Label sortBy = new Label("Sort by:");
        Label choice1 = new Label("1. Due Date (Ascending)");
        Label choice2 = new Label("2. Due Date (Descending)");
        Label choice3 = new Label("3. Priority (High to Low)");
        Label choice4 = new Label("4. Priority (Low to High)");
        Label space = new Label("                                    ");
        TextField choiceInput = new TextField();
        choiceInput.setPromptText("1/2/3/4");
        
        Button confirm = new Button("Confirm");
        Button Back = backButton(window, scene1);
        
        VBox layout41 = new VBox(15);
        layout41.getChildren().addAll(taskSort, sortBy, choice1, choice2, choice3, choice4, space, choiceInput, confirm ,Back);
        layout41.setAlignment(Pos.CENTER);
        
        Scene scene41 = new Scene(layout41, 500 ,500);
        window.setScene(scene41);
        window.setTitle("Task Sorting");
        
        confirm.setOnAction(e -> 
        {
            try{
                int choice = Integer.parseInt(choiceInput.getText());
                if(choice<1 || choice>4)
                    OutputScene.outOfRange();
                else
                {
                    if (choice == 1 || choice ==2) 
                        TaskSorting.dueDateSort(choice);
                    else  //is either 3 or 4 since that the choice out of range has it specific output
                        TaskSorting.prioritySort(choice);
                    OutputScene.sortResult(choice);
                    TaskStorage.saveTasks(loggedInUsername, taskList);
                }
            }catch(NumberFormatException i){
                OutputScene.wrongFormat();
            }
            window.setScene(scene1);
        });
    }

    public void taskSearching(Scene scene1) {
       
        Label taskSearch = new Label("=== Search Tasks ===");
        Label keyword = new Label("Enter a keyword to search by title or description:");
        TextField keywordInput = new TextField();
        keywordInput.setPromptText("Exp : Assignment");
        
        Button search = new Button("Search");
        Button Back = backButton(window, scene1);
        
        VBox layout51 = new VBox(20);
        layout51.getChildren().addAll(taskSearch , keyword, keywordInput , search, Back);
        layout51.setAlignment(Pos.CENTER);

        Scene scene51 = new Scene (layout51, 500,500);
        window.setScene(scene51);
        window.setTitle("Task Searching");
        
        search.setOnAction(e ->{
            String searchword = keywordInput.getText().toLowerCase();
            if(TaskDetection.seacrhwordDetect(searchword))
                OutputScene.searchwordError(); 
            else
                OutputScene.searchResult(TaskSearching.TaskSearch(searchword));
            
            window.setScene(scene1);
        });
          
    }
    
    public void Taskrecurring(Scene scene1)
    {
        Label taskRecurring = new Label("===Add a Reccuring Task ===");
        Label taskTitle = new Label("Enter task title: ");
        TextField taskInput = new TextField();
        taskInput.setPromptText("Exp: Weekly Meeting");
        
        Label taskDescription = new Label("Enter task description: ");
        TextField descriptionInput = new TextField();
        descriptionInput.setPromptText("Exp : Report Proxima Centauri brightness data ");
        
        Label interval = new Label("Enter recurrence Interval (daily, weekly, monthly): ");
        TextField intervalInput = new TextField();
        intervalInput.setPromptText("daily/weekly,monthly");
        
        Label categoryState = new Label("Enter the Task category(Homework, Personal, Work):");
        TextField categoryInput = new TextField();
        categoryInput.setPromptText("Homework/Personal/Work");
        
        Label priorityState = new Label("Priority level (Low, Medium, High):");
        TextField priorityInput = new TextField();
        priorityInput.setPromptText("Low/Medium/High");
        
        Button create = new Button("Create");
        Button Back = backButton(window, scene1);
        
        VBox layout61 = new VBox (10);
        layout61.getChildren().addAll(taskRecurring, taskTitle, taskInput, taskDescription, descriptionInput, interval, intervalInput, 
                categoryState, categoryInput, priorityState, priorityInput, create, Back);
        layout61.setAlignment(Pos.CENTER);
        
        Scene scene61= new Scene(layout61, 500, 500);
        window.setScene(scene61);
        window.setTitle("Task Reccuring");
        
        create.setOnAction(e ->{
            String getTitle = taskInput.getText();
            String getDescription = descriptionInput.getText();
            String getInterval = intervalInput.getText().toLowerCase();
            String getDuedate = TaskDetection.findDuedate(getInterval).toString();
            String getCategory = categoryInput.getText();
            String getPriority = priorityInput.getText();
            
            if(TaskDetection.hasValidCategoryFormat(getCategory) 
                && TaskDetection.hasValidPriorityFormat(getPriority)
                && TaskDetection.hasValidIntervalFormat(getInterval)
                && !getTitle.isBlank() 
                && !getDescription.isBlank())
            {
                boolean completeStatus= false;
                boolean recurring = true;
                int numComplete=0;
                Task createTask = new Task(getTitle, getDescription, getDuedate,
                        getCategory, getPriority, completeStatus, recurring, getInterval, numComplete);
                taskList.add(createTask);
                TaskStorage.saveTasks(loggedInUsername, taskList);
                OutputScene.recurringResult(getTitle);
            }
            else
                OutputScene.wrongFormat();
            window.setScene(scene1);                   
        });
    }   
    
    public void TaskEditing(Scene scene1){
        TaskViewAndEditing.ViewTaskSetUp(window, scene1);
    }
    
    public void viewDependencies(Scene scene1) {
        Label view = new Label("=== View All Dependencies ===");

        VBox layout81 = new VBox(20);
        layout81.getChildren().add(view);
        boolean hasDependencies = false;

        for (Task task : Todolist.taskList) 
        {

            if (task != null && task.getDependencies() != null && !task.getDependencies().isEmpty()) 
            {
                List<Task> dependencies = task.getDependencies();

                Label taskDetailsLabel = new Label(task.getTitleName() + " (Due: " + task.getDuedate() + 
                        ", Priority: " + task.getPriority() + 
                        ", Complete: " + (task.isComplete() ? "Yes" : "No") + ") depends on:");
                
                layout81.getChildren().add(taskDetailsLabel);

                for (Task dependency : dependencies) 
                {
                    if (Todolist.taskList.contains(dependency)) 
                    {
                        String dependencyDetails = dependency.getTitleName() + " (Due: " + dependency.getDuedate() +
                                                       ", Priority: " + dependency.getPriority() + 
                                                       ", Complete: " + (dependency.isComplete() ? "Yes" : "No") + ")";
                        Label dependencyLabel = new Label(dependencyDetails);
                        layout81.getChildren().add(dependencyLabel);
                    }
                }
                hasDependencies = true;  
            }
        }

        if (!hasDependencies) 
        {
            Label noDependenciesLabel = new Label("No dependencies found.");
            layout81.getChildren().add(noDependenciesLabel);
        } 

        Button Back = new Button("Back");
        Back.setOnAction(c -> window.setScene(scene1));

        layout81.getChildren().add(Back);
        layout81.setAlignment(Pos.CENTER);

        Scene scene81 = new Scene(layout81, 500, 500);
        window.setScene(scene81);
        window.setTitle("View Task Dependencies");
    }
    
    public void dataAnalytics(Scene scene1){
        Label dataAnalytics = new Label("===Analytics DashBoard===");
        Button back = backButton(window, scene1);
        
        Label numTask = new Label(" Total Tasks: "+DataCollect.collectNumTask());
        Label numComplete = new Label(" Completed: "+DataCollect.collectNumComplete());
        Label numPending = new Label(" Pending: "+DataCollect.calculatePending( DataCollect.collectNumTask() , DataCollect.collectNumComplete()));
        Label rate = new Label(" Completion Rate: "+DataCollect.calculateCompleteRate( DataCollect.collectNumTask() , DataCollect.collectNumComplete()));
        int[]number = DataCollect.collectCategory();
        Label category = new Label("  Task Categories: Homework: "+ number[0]+
                                        ", Personal: "+ number[1]+
                                        ", Work: "+ number[2]);
        
        VBox layout91 = new VBox(10);
        layout91.getChildren().addAll(dataAnalytics, numTask, numComplete, numPending, rate, category, back);
        layout91.setAlignment(Pos.CENTER);
        
        Scene scene91= new Scene(layout91, 500, 500);
        window.setTitle("Data Analytics");
        window.setScene(scene91);
    }   
    
    public static void main(String[] args) {
        launch(args);
    }

}
