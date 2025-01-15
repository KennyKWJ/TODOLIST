/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package todolist;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import static todolist.Todolist.taskList;


public class TaskViewAndEditing {
    public static TaskDependencies taskManager = new TaskDependencies();
    public static Button backButton(Stage window, Scene scene) {
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> window.setScene(scene));
        return backButton;
    }
    
    public static void ViewTaskSetUp(Stage window, Scene scene1){
        Label view = new Label("===View All Task===");
        VBox List = new VBox(20);
        List.getChildren().add(view);
        TaskViewAndEditing.prepareAllTask(List);
        
        Button next = new Button("Next");
        Button Back = new Button("Back");
        Back.setOnAction(c ->window.setScene(scene1));
        
        List.getChildren().add(next);
        List.getChildren().add(Back); 
        List.setAlignment(Pos.TOP_CENTER);
        
        Scene scene71 = new Scene(List,500,500);
        window.setScene(scene71);
        window.setTitle("View All Task");
        
        next.setOnAction(e ->{
            TaskViewAndEditing.Edit(window,scene1,scene71);
        });
    }
    
    public static VBox prepareAllTask(VBox List){
        int count=0;

        for (int i = 0; i < Todolist.taskList.size(); i++) 
        {
            count++;
            if (Todolist.taskList.get(i).isComplete()==true ) 
            {
                Label counterYes = new Label(count + ". [Complete] " + Todolist.taskList.get(i).getTitleName() + " - Due: " + Todolist.taskList.get(i).getDuedate() + " - Category: " + Todolist.taskList.get(i).getCategory() + " - Priority: " + Todolist.taskList.get(i).getPriority());
                List.getChildren().add(counterYes);
            } 
            else if(!Todolist.taskList.get(i).isComplete()==true && Todolist.taskList.get(i).isRecurring())
            {
                Label counterNoYes = new Label(count + ".[Recur] [Incomplete] " + Todolist.taskList.get(i).getTitleName() + " - Due: " + Todolist.taskList.get(i).getDuedate() + " - Category: " + Todolist.taskList.get(i).getCategory() + " - Priority: " + Todolist.taskList.get(i).getPriority());
                List.getChildren().add(counterNoYes);
            }
            else
            {
                Label counterNoYes = new Label(count + ". [Incomplete] " + Todolist.taskList.get(i).getTitleName() + " - Due: " + Todolist.taskList.get(i).getDuedate() + " - Category: " + Todolist.taskList.get(i).getCategory() + " - Priority: " + Todolist.taskList.get(i).getPriority());
                List.getChildren().add(counterNoYes);
            }
        }
        return List;
    }
    
    public static void Edit(Stage window, Scene scene1, Scene scene71){
        Label edit = new Label("Enter the Task number you want to edit: ");
        TextField NumberTaskInput = new TextField();
        
        Button confirm = new Button("Confirm");
        Button Back = new Button("Back");
        Back.setOnAction(e ->{
            window.setScene(scene71);
        });
        
        VBox inputLayout = new VBox(20);
        inputLayout.getChildren().addAll(edit, NumberTaskInput, confirm, Back);
        inputLayout.setAlignment(Pos.CENTER);
        
        Scene scene72 = new Scene(inputLayout,500, 500);
        window.setScene(scene72);
        window.setTitle("Enter Number Task");
        
        confirm.setOnAction(e ->{
            String indexInput = NumberTaskInput.getText();
            try{
                int index = Integer.parseInt(indexInput)-1;
                if(index>=Todolist.taskList.size() || index<0)
                    OutputScene.outOfRange();
                else
                {
                    TaskViewAndEditing.sectionEdit(window, index, scene1, scene71, scene72);
                }
            }catch(NumberFormatException i){
                OutputScene.wrongFormat();              
            }
        });
    }
    
    public static void sectionEdit(Stage window, int index, Scene scene1, Scene scene71, Scene scene72){
        Label section = new Label("What would you like to edit?");
        Label title = new Label("1. Title");
        Label description = new Label("2. Description");
        Label dueDate = new Label("3. Due Date");
        Label category = new Label("4. Category");
        Label priority = new Label("5. Priority");
        Label taskdepend = new Label("6. Set Task Dependency");
        Label cancel = new Label("7. Cancel");
        TextField choiceInput = new TextField();
        choiceInput.setPromptText("1-7");
        
        Button OK = new Button("OK");
        
        VBox layoutSection = new VBox(20);
        layoutSection.getChildren().addAll(section,title, description, dueDate, category, priority, taskdepend, cancel, choiceInput, OK);
        layoutSection.setAlignment(Pos.CENTER);
        
        Scene scene73 = new Scene(layoutSection, 500,500);
        window.setScene(scene73);
        window.setTitle("Section Edit");
        OK.setOnAction(e ->{
            try
            {
                int choice = Integer.parseInt(choiceInput.getText());
                if(choice>7 || choice<0)
                    OutputScene.outOfRange();
                else
                {
                    TaskViewAndEditing.editTask(window,scene1 ,scene71, scene72, scene73, index, choice);
                }
            }catch(NumberFormatException i)
            {
                OutputScene.wrongFormat();
            }
        });
    }
   
    public static void editTask(Stage window,Scene scene1, Scene scene71, Scene scene72, Scene scene73, int index, int choice){
        
        if (choice == 6)
        {
            Label taskdepend1 = new Label("Enter task number that depends on another task: ");
            TextField task1 = new TextField();
            task1.setPromptText("Exp : 1");
            
            Label taskdepend2 = new Label("Enter the task number it depends on: ");
            TextField task2 = new TextField();
            task2.setPromptText("Exp : 2");
            
            Button confirm = new Button("Confirm");
            Button back = backButton(window, scene73);
            
            VBox layoutD = new VBox(10);
            layoutD.getChildren().addAll(taskdepend1, task1, taskdepend2, task2, confirm, back);
            layoutD.setAlignment(Pos.CENTER);
            
            Scene sceneD = new Scene(layoutD, 500,500);
            window.setScene(sceneD);
            window.setTitle("Task Dependencies");
            
            confirm.setOnAction(e ->{
                try{
                    int dependentTaskNumber = Integer.parseInt(task1.getText())-1;
                    int precedingTaskNumber = Integer.parseInt(task2.getText())-1;
                    String dependentTaskNumberDate = Todolist.taskList.get(dependentTaskNumber).getDuedate();
                    String precedingTaskNumberDate = Todolist.taskList.get(precedingTaskNumber).getDuedate();
                    
                    if(dependentTaskNumber<0 || dependentTaskNumber>=Todolist.taskList.size() ||
                            precedingTaskNumber<0 || precedingTaskNumber>=Todolist.taskList.size())
                    {
                        OutputScene.outOfRange();
                    }
                    else if(dependentTaskNumber==precedingTaskNumber)
                    {
                        OutputScene.selfDependencyError();
                    }
                    else if(Todolist.taskList.get(dependentTaskNumber).isRecurring() || Todolist.taskList.get(precedingTaskNumber).isRecurring()
                                || taskManager.taskIsCompletedBeforeDepend(dependentTaskNumber) || taskManager.taskIsCompletedBeforeDepend(precedingTaskNumber)  )
                    {
                        OutputScene.eitherRecurringOrCompleted();
                    }
                    else if(dependentTaskNumber<0 || dependentTaskNumber>=Todolist.taskList.size()
                            || precedingTaskNumber<0 || precedingTaskNumber>=Todolist.taskList.size() )
                    {
                        OutputScene.outOfRange();
                    }
                    else if(TaskDetection.dependentisBeforePreceding(dependentTaskNumberDate, precedingTaskNumberDate))
                    {
                        OutputScene.dependenciesError(Todolist.taskList.get(dependentTaskNumber).getTitleName(),
                                Todolist.taskList.get(precedingTaskNumber).getTitleName());
                    }
                    else
                    {
                        taskManager.addTask(dependentTaskNumber, precedingTaskNumber);

                        Task dependentTask = Todolist.taskList.get(dependentTaskNumber);
                        Task precedingTask = Todolist.taskList.get(precedingTaskNumber);

                        taskManager.addDependency(dependentTask.getTitleName(),precedingTask.getTitleName());
                    }
                    TaskStorage.saveTasks(Todolist.loggedInUsername, taskList);
                    ViewTaskSetUp(window, scene1); 
          
                }catch(NumberFormatException i){
                    OutputScene.wrongFormat();
                }catch(Exception i){
                    OutputScene.outOfRange();
                }
                
            });
            
        }
        else if(choice==7)
        {
            window.setScene(scene72);
        }
        else
        {
            VBox editPage = new VBox(20);
        
            SectionEditProcess.labelSelection(choice,editPage);
            TextField input = new TextField();

            Button edit = new Button("Edit");
            Button back = backButton(window, scene73);

            editPage.getChildren().addAll(input,edit,back);
            editPage.setAlignment(Pos.CENTER);

            Scene layout74 = new Scene(editPage, 500, 500);
            window.setScene(layout74);
            window.setTitle("Task Editing");
            
            edit.setOnAction(e ->{
                String content = input.getText();
                String before = SectionEditProcess.LoadContent(choice, index);
                SectionEditProcess.editTask(choice, index, content);
                String after = SectionEditProcess.LoadContent(choice, index);
                if(!before.contentEquals(after))
                {
                    OutputScene.EditResult(before, after);
                    ViewTaskSetUp(window, scene1);
                }
                else
                    ViewTaskSetUp(window, scene1);   
            });  
        }
    }
}
