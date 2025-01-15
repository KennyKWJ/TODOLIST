/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package todolist;

import java.time.LocalDate;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class OutputScene {
    //no task 
    public static void warning() {
        Stage windowtemp = new Stage();
        windowtemp.initModality(Modality.APPLICATION_MODAL);
        windowtemp.setTitle("Warning!");
        windowtemp.setMinWidth(200);
        
        Label warning1 = new Label("You haven't create any task, you can't do others actions.");
        Label space = new Label("                                         ");
        Button closeButton = new Button("OK");
        closeButton.setOnAction(e -> windowtemp.close());
        
        VBox layout = new VBox();
        layout.getChildren().addAll(warning1, space, closeButton);
        layout.setAlignment(Pos.CENTER);
        
        Scene warningScene = new Scene(layout,350,100);
        windowtemp.setScene(warningScene);
        windowtemp.showAndWait();         
    }
    
    //datatype invalid detect
    public static void wrongFormat()
    {
        Stage windowtemp = new Stage();
        windowtemp.setTitle("ERROR!");
        windowtemp.setMinWidth(200);
        windowtemp.initModality(Modality.APPLICATION_MODAL);
        
        Label invalid = new Label("Error! You may have entered the wrong format or left some options blank.");
        Button OK = new Button("OK");
        OK.setOnAction(o ->windowtemp.close());
        windowtemp.setOnCloseRequest(c -> windowtemp.close() );
        
        VBox page = new VBox(10);
        page.getChildren().addAll(invalid, OK);
        page.setAlignment(Pos.CENTER);
        
        Scene pageFinal = new Scene(page, 500,150);
        windowtemp.setScene(pageFinal);
        windowtemp.showAndWait(); 
    }
    
    //the value is not in the range
    public static void outOfRange()
    {
        Stage windowtemp = new Stage();
        windowtemp.setTitle("ERROR!");
        windowtemp.setMinWidth(200);
        windowtemp.initModality(Modality.APPLICATION_MODAL);
        
        Label invalid = new Label("Error! The entered value is out of the range!");
        Button OK = new Button("OK");
        OK.setOnAction(o -> windowtemp.close());
        windowtemp.setOnCloseRequest(c -> windowtemp.close() );      
        
        VBox page = new VBox(15);
        page.getChildren().addAll(invalid, OK);
        page.setAlignment(Pos.CENTER);
                    
        Scene pageFinal = new Scene(page, 500, 150);
        windowtemp.setScene(pageFinal);
        windowtemp.showAndWait(); 
    }
    
    //result of sorting
    public static void sortResult(int choice)
    {
        Stage windowtemp = new Stage();
        windowtemp.setTitle("Sort Result");
        windowtemp.setMinWidth(200);
        windowtemp.initModality(Modality.APPLICATION_MODAL);
        
        Label choice1 = new Label("Tasks sorted by Due Date (Ascending)!");
        Label choice2 = new Label("Tasks sorted by Due Date (Descending)!");
        Label choice3 = new Label("Tasks sorted by Priority (High to Low)!");
        Label choice4 = new Label("Tasks sorted by Priority (Low to High)!");
        
        Button OK = new Button("OK");
        OK.setOnAction(o -> windowtemp.close());
        windowtemp.setOnCloseRequest(c -> windowtemp.close() );   

        VBox page = new VBox(15);
        if(choice ==1)
            page.getChildren().addAll(choice1, OK);
        else if(choice ==2)
            page.getChildren().addAll(choice2, OK);
        else if (choice ==3)
            page.getChildren().addAll(choice3, OK);
        else
            page.getChildren().addAll(choice4, OK);
        
        page.setAlignment(Pos.CENTER);
                    
        Scene pageFinal = new Scene(page, 500,150);
        windowtemp.setScene(pageFinal);
        windowtemp.showAndWait();
    }
    
    //result of delete
    public static void deleteResult(String title)
    {
        Stage windowtemp = new Stage();
        windowtemp.initModality(Modality.APPLICATION_MODAL);
        windowtemp.setTitle("Attention");
        windowtemp.setMinWidth(200);
                    
        Label finish = new Label("Task " + "\"" + title + "\"" + " deleted successfully!");
        Button OK = new Button("OK");
        OK.setOnAction(o ->  windowtemp.close());
        windowtemp.setOnCloseRequest(c -> windowtemp.close() );
        VBox page = new VBox(15);
        page.getChildren().addAll(finish, OK);
        page.setAlignment(Pos.CENTER);
                    
        Scene pageFinal = new Scene(page, 500,150);
        windowtemp.setScene(pageFinal);    
        windowtemp.showAndWait();
    }
    
    //complete task result
    public static void manageResult(String title)
    {
        Stage windowtemp = new Stage();
        windowtemp.initModality(Modality.APPLICATION_MODAL);
        windowtemp.setTitle("Attention");
        windowtemp.setMinWidth(200);
                    
        Label finish = new Label("Task " + "\"" + title + "\"" + " marked as complete!");
        Button OK = new Button("OK");
        OK.setOnAction(o -> windowtemp.close());
        windowtemp.setOnCloseRequest(c -> windowtemp.close() );
        VBox page = new VBox(15);
        page.getChildren().addAll(finish, OK);
        page.setAlignment(Pos.CENTER);
                    
        Scene pageFinal = new Scene(page, 500,150);
        windowtemp.setScene(pageFinal);   
        windowtemp.showAndWait();
    }
    
    //create task result
    public static void createResult(String title)
    {
        Stage windowtemp = new Stage();
        windowtemp.initModality(Modality.APPLICATION_MODAL);
        windowtemp.setTitle("Attention");
        windowtemp.setMinWidth(200);
  
        Label finish = new Label("Task " + "\"" + title + "\"" + " added successfully!");
        Button OK = new Button("OK");
        OK.setOnAction(o -> windowtemp.close());
        windowtemp.setOnCloseRequest(c -> windowtemp.close() );   
        VBox page = new VBox(15);
        page.getChildren().addAll(finish, OK);
        page.setAlignment(Pos.CENTER);
        Scene pageFinal = new Scene(page, 500,150);
        windowtemp.setScene(pageFinal);
        windowtemp.showAndWait();
    }
    
    //create recurring task result
    public static void recurringResult(String title)
    {
        Stage windowtemp = new Stage();
        windowtemp.initModality(Modality.APPLICATION_MODAL);
        windowtemp.setTitle("Attention");
        windowtemp.setMinWidth(200);
  
        Label finish = new Label("Recurring Task " + "\"" + title + "\"" + " created successfully!");
        Button OK = new Button("OK");
        OK.setOnAction(o -> windowtemp.close());
        windowtemp.setOnCloseRequest(c -> windowtemp.close() );   
        VBox page = new VBox(15);
        page.getChildren().addAll(finish, OK);
        page.setAlignment(Pos.CENTER);
        Scene pageFinal = new Scene(page, 500,150);
        windowtemp.setScene(pageFinal);
        windowtemp.showAndWait();
    }
    
    //only for recurring task 
    public static void manageRecurringResult(String title , String nextDueDate)
    {
        Stage windowtemp = new Stage();
        windowtemp.initModality(Modality.APPLICATION_MODAL);
        windowtemp.setTitle("Attention");
        windowtemp.setMinWidth(200);
                    
        Label finish = new Label("Task " + "\"" + title + "\"" + " marked as complete!");
        Label recurring = new Label("Next due date: " + nextDueDate);
        Button OK = new Button("OK");
        OK.setOnAction(o -> windowtemp.close());
        windowtemp.setOnCloseRequest(c -> windowtemp.close() );
        VBox page = new VBox(15);
        page.getChildren().addAll(finish, recurring, OK);
        page.setAlignment(Pos.CENTER);
                    
        Scene pageFinal = new Scene(page, 500,150);
        windowtemp.setScene(pageFinal);   
        windowtemp.showAndWait();
    }
    
    //search word output
    public static void searchResult(VBox newPage){
        Stage windowtemp = new Stage();
        windowtemp.setTitle("Result");
        windowtemp.setMinWidth(200);
        windowtemp.initModality(Modality.APPLICATION_MODAL);
            
            
        Button OK = new Button("OK");
        OK.setOnAction(o -> windowtemp.close());
        windowtemp.setOnCloseRequest(c -> windowtemp.close() );
            
        newPage.getChildren().add(OK);
        newPage.setAlignment(Pos.CENTER);
            
        Scene pageFinal = new Scene(newPage, 500,500);
        windowtemp.setScene(pageFinal);
        windowtemp.showAndWait(); 
    }
    
    //search no found
    public static void searchwordError(){
        Stage windowtemp = new Stage();
        windowtemp.setTitle("ERROR!");
        windowtemp.setMinWidth(200);
        windowtemp.initModality(Modality.APPLICATION_MODAL);
        
        Label invalid = new Label("Error! You Need at least enter one character to serach(Not include spacebar).");
        Button OK = new Button("OK");
        OK.setOnAction(o ->windowtemp.close());
        windowtemp.setOnCloseRequest(c -> windowtemp.close() );
        
        VBox page = new VBox(15);
        page.getChildren().addAll(invalid, OK);
        page.setAlignment(Pos.CENTER);
        
        Scene pageFinal = new Scene(page, 500,150);
        windowtemp.setScene(pageFinal);
        windowtemp.showAndWait(); 
    }
    
    //leave blank 
    public static void editContentError(){
        Stage windowtemp = new Stage();
        windowtemp.setTitle("ERROR!");
        windowtemp.setMinWidth(200);
        windowtemp.initModality(Modality.APPLICATION_MODAL);
        
        Label invalid = new Label("Error! You Need at least enter one character to edit(Not include spacebar).");
        Button OK = new Button("OK");
        OK.setOnAction(o ->windowtemp.close());
        windowtemp.setOnCloseRequest(c -> windowtemp.close() );
        
        VBox page = new VBox(15);
        page.getChildren().addAll(invalid, OK);
        page.setAlignment(Pos.CENTER);
        
        Scene pageFinal = new Scene(page, 500,150);
        windowtemp.setScene(pageFinal);
        windowtemp.showAndWait(); 
    }
    
    //editing task
    public static void EditResult(String before, String after){
        Stage windowtemp = new Stage();
        windowtemp.setTitle("Attention");
        windowtemp.setMinWidth(200);
        windowtemp.initModality(Modality.APPLICATION_MODAL);
        
        Label updated = new Label( "\"" + before + "\"" + " has been updated to " + "\"" + after + "\"");
        Button OK = new Button("OK");
        OK.setOnAction(o ->windowtemp.close());
        windowtemp.setOnCloseRequest(c -> windowtemp.close() );
        
        VBox page = new VBox(15);
        page.getChildren().addAll(updated, OK);
        page.setAlignment(Pos.CENTER);
        
        Scene pageFinal = new Scene(page, 500,150);
        windowtemp.setScene(pageFinal);
        windowtemp.showAndWait(); 
    }
    
    //prevent cyclic task dependency
    public static void cycleDependencyError(){
        Stage windowtemp = new Stage();
        windowtemp.setTitle("ERROR!");
        windowtemp.setMinWidth(200);
        windowtemp.initModality(Modality.APPLICATION_MODAL);
        
        Label invalid = new Label("Error! Adding this dependency creates a cycle. Dependency not added.");
        Button OK = new Button("OK");
        OK.setOnAction(o ->windowtemp.close());
        windowtemp.setOnCloseRequest(c -> windowtemp.close() );
        
        VBox page = new VBox(15);
        page.getChildren().addAll(invalid, OK);
        page.setAlignment(Pos.CENTER);
        
        Scene pageFinal = new Scene(page, 500,150);
        windowtemp.setScene(pageFinal);
        windowtemp.showAndWait(); 
    }
    
    //denpendency created
    public static void dependencyResult(String dependentTaskName, String dependencyTaskName){
        Stage windowtemp = new Stage();
        windowtemp.setTitle("Attention");
        windowtemp.setMinWidth(200);
        windowtemp.initModality(Modality.APPLICATION_MODAL);
        
        Label dependency = new Label("Task "+ "\""+ dependentTaskName + "\""+" now depends on Task "+ "\"" + dependencyTaskName + "\".");
        Button OK = new Button("OK");
        OK.setOnAction(o ->windowtemp.close());
        windowtemp.setOnCloseRequest(c -> windowtemp.close() );
        
        VBox page = new VBox(15);
        page.getChildren().addAll(dependency, OK);
        page.setAlignment(Pos.CENTER);
        
        Scene pageFinal = new Scene(page, 500,150);
        windowtemp.setScene(pageFinal);
        windowtemp.showAndWait(); 
    }
    
    //not valid condition to do denpendency 
    public static void eitherRecurringOrCompleted(){
        Stage windowtemp = new Stage();
        windowtemp.setTitle("ERROR!");
        windowtemp.setMinWidth(200);
        windowtemp.initModality(Modality.APPLICATION_MODAL);
        
        Label error = new Label("The Task is recurring or is already completed. Task Dependency is not allowed.");
        Button OK = new Button("OK");
        OK.setOnAction(o ->windowtemp.close());
        windowtemp.setOnCloseRequest(c -> windowtemp.close() );
        
        VBox page = new VBox(15);
        page.getChildren().addAll(error, OK);
        page.setAlignment(Pos.CENTER);
        
        Scene pageFinal = new Scene(page, 500,150);
        windowtemp.setScene(pageFinal);
        windowtemp.showAndWait(); 
    }
    
    //denpendency task complete section
    public static void dependencyManageError(String taskName, String unmetDependencies){
        Stage windowtemp = new Stage();
        windowtemp.setTitle("ERROR!");
        windowtemp.setMinWidth(200);
        windowtemp.initModality(Modality.APPLICATION_MODAL);
        
        Label error = new Label("Warning: " + taskName + " cannot be marked as complete because it depends on \"" + unmetDependencies + "\".");
        Label order = new Label("Please complete \"" + unmetDependencies + "\" first.");
        Button OK = new Button("OK");
        OK.setOnAction(o ->windowtemp.close());
        windowtemp.setOnCloseRequest(c -> windowtemp.close() );
        
        VBox page = new VBox(15);
        page.getChildren().addAll(error, order, OK);
        page.setAlignment(Pos.CENTER);
        
        Scene pageFinal = new Scene(page, 500,150);
        windowtemp.setScene(pageFinal);
        windowtemp.showAndWait(); 
    }
    
    //prevent forever loop complete
    public static void AlreadyCompleted(String taskName){
        Stage windowtemp = new Stage();
        windowtemp.setTitle("Attention");
        windowtemp.setMinWidth(200);
        windowtemp.initModality(Modality.APPLICATION_MODAL);
        
        Label remind = new Label("Attention: Task " + taskName + "is already marked as done.");
        Button OK = new Button("OK");
        OK.setOnAction(o ->windowtemp.close());
        windowtemp.setOnCloseRequest(c -> windowtemp.close() );
        
        VBox page = new VBox(15);
        page.getChildren().addAll(remind, OK);
        page.setAlignment(Pos.CENTER);
        
        Scene pageFinal = new Scene(page, 500,150);
        windowtemp.setScene(pageFinal);
        windowtemp.showAndWait(); 
    }
    
    public static void selfDependencyError(){
        Stage windowtemp = new Stage();
        windowtemp.setTitle("ERROR!");
        windowtemp.setMinWidth(200);
        windowtemp.initModality(Modality.APPLICATION_MODAL);
        
        Label remind = new Label("You can't create Task Dependency on same Task.");
        Button OK = new Button("OK");
        OK.setOnAction(o ->windowtemp.close());
        windowtemp.setOnCloseRequest(c -> windowtemp.close() );
        
        VBox page = new VBox(15);
        page.getChildren().addAll(remind, OK);
        page.setAlignment(Pos.CENTER);
        
        Scene pageFinal = new Scene(page, 500,150);
        windowtemp.setScene(pageFinal);
        windowtemp.showAndWait(); 
    }
    
    public static void GmailNotFound(){
        Stage windowtemp = new Stage();
        windowtemp.setTitle("ERROR!");
        windowtemp.setMinWidth(200);
        windowtemp.initModality(Modality.APPLICATION_MODAL);
        
        Label error = new Label("We try to send a gmail,but there is an error.");
        Button OK = new Button("OK");
        OK.setOnAction(o ->windowtemp.close());
        windowtemp.setOnCloseRequest(c -> windowtemp.close() );
        
        VBox page = new VBox(15);
        page.getChildren().addAll(error, OK);
        page.setAlignment(Pos.CENTER);
        
        Scene pageFinal = new Scene(page, 500,150);
        windowtemp.setScene(pageFinal);
        windowtemp.showAndWait(); 
    }
    
    public static void fileOpenError(){
        Stage windowtemp = new Stage();
        windowtemp.setTitle("ERROR!");
        windowtemp.setMinWidth(200);
        windowtemp.initModality(Modality.APPLICATION_MODAL);
        
        Label error = new Label("There is an Error to read the file.");
        Button OK = new Button("OK");
        OK.setOnAction(o ->windowtemp.close());
        windowtemp.setOnCloseRequest(c -> windowtemp.close() );
        
        VBox page = new VBox(15);
        page.getChildren().addAll(error, OK);
        page.setAlignment(Pos.CENTER);
        
        Scene pageFinal = new Scene(page, 500,150);
        windowtemp.setScene(pageFinal);
        windowtemp.showAndWait(); 
    }
    
    public static void failToSendGmail(){
        Stage windowtemp = new Stage();
        windowtemp.setTitle("ERROR!");
        windowtemp.setMinWidth(200);
        windowtemp.initModality(Modality.APPLICATION_MODAL);
        
        Label remind = new Label("Failed to send email. Check console for details.");
        Button OK = new Button("OK");
        OK.setOnAction(o ->windowtemp.close());
        windowtemp.setOnCloseRequest(c -> windowtemp.close() );
        
        VBox page = new VBox(15);
        page.getChildren().addAll(remind, OK);
        page.setAlignment(Pos.CENTER);
        
        Scene pageFinal = new Scene(page, 300,150);
        windowtemp.setScene(pageFinal);
        windowtemp.showAndWait(); 
    }
    
    public static void successToSendGmail(){
        Stage windowtemp = new Stage();
        windowtemp.setTitle("Attention");
        windowtemp.setMinWidth(200);
        windowtemp.initModality(Modality.APPLICATION_MODAL);
        
        Label remind = new Label("Remember to check your email.");
        Button OK = new Button("OK");
        OK.setOnAction(o ->windowtemp.close());
        windowtemp.setOnCloseRequest(c -> windowtemp.close() );
        
        VBox page = new VBox(15);
        page.getChildren().addAll(remind, OK);
        page.setAlignment(Pos.CENTER);
        
        Scene pageFinal = new Scene(page, 300,150);
        windowtemp.setScene(pageFinal);
        windowtemp.showAndWait(); 
    }
    
    public static void failToValidate(){
        Stage windowtemp = new Stage();
        windowtemp.setTitle("ERROR!");
        windowtemp.setMinWidth(200);
        windowtemp.initModality(Modality.APPLICATION_MODAL);
        
        Label error = new Label("Fail to validate your input data.");
        Button OK = new Button("OK");
        OK.setOnAction(o ->windowtemp.close());
        windowtemp.setOnCloseRequest(c -> windowtemp.close() );
        
        VBox page = new VBox(15);
        page.getChildren().addAll(error, OK);
        page.setAlignment(Pos.CENTER);
        
        Scene pageFinal = new Scene(page, 500,150);
        windowtemp.setScene(pageFinal);
        windowtemp.showAndWait(); 
    }
    
    public static void failToSaveData(){
        Stage windowtemp = new Stage();
        windowtemp.setTitle("ERROR!");
        windowtemp.setMinWidth(200);
        windowtemp.initModality(Modality.APPLICATION_MODAL);
        
        Label error = new Label("Fail to save your data.");
        Button OK = new Button("OK");
        OK.setOnAction(o ->windowtemp.close());
        windowtemp.setOnCloseRequest(c -> windowtemp.close() );
        
        VBox page = new VBox(15);
        page.getChildren().addAll(error, OK);
        page.setAlignment(Pos.CENTER);
        
        Scene pageFinal = new Scene(page, 500,150);
        windowtemp.setScene(pageFinal);
        windowtemp.showAndWait(); 
    }
    
    public static void failToLoadFile(){
        Stage windowtemp = new Stage();
        windowtemp.setTitle("ERROR!");
        windowtemp.setMinWidth(200);
        windowtemp.initModality(Modality.APPLICATION_MODAL);
        
        Label error = new Label("Fail to load the file.");
        Button OK = new Button("OK");
        OK.setOnAction(o ->windowtemp.close());
        windowtemp.setOnCloseRequest(c -> windowtemp.close() );
        
        VBox page = new VBox(15);
        page.getChildren().addAll(error, OK);
        page.setAlignment(Pos.CENTER);
        
        Scene pageFinal = new Scene(page, 500,150);
        windowtemp.setScene(pageFinal);
        windowtemp.showAndWait();
    }
    
    public static void failToFindFile(){
        Stage windowtemp = new Stage();
        windowtemp.setTitle("ERROR!");
        windowtemp.setMinWidth(200);
        windowtemp.initModality(Modality.APPLICATION_MODAL);
        
        Label error = new Label("Fail to find the file.");
        Button OK = new Button("OK");
        OK.setOnAction(o ->windowtemp.close());
        windowtemp.setOnCloseRequest(c -> windowtemp.close() );
        
        VBox page = new VBox(15);
        page.getChildren().addAll(error, OK);
        page.setAlignment(Pos.CENTER);
        
        Scene pageFinal = new Scene(page, 500,150);
        windowtemp.setScene(pageFinal);
        windowtemp.showAndWait();
    }
    
    public static void failToCreateFile(){
        Stage windowtemp = new Stage();
        windowtemp.setTitle("ERROR!");
        windowtemp.setMinWidth(200);
        windowtemp.initModality(Modality.APPLICATION_MODAL);
        
        Label error = new Label("Fail to create the file.");
        Button OK = new Button("OK");
        OK.setOnAction(o ->windowtemp.close());
        windowtemp.setOnCloseRequest(c -> windowtemp.close() );
        
        VBox page = new VBox(15);
        page.getChildren().addAll(error, OK);
        page.setAlignment(Pos.CENTER);
        
        Scene pageFinal = new Scene(page, 500,150);
        windowtemp.setScene(pageFinal);
        windowtemp.showAndWait();
    }
    
    public static void dependenciesError(String task1, String task2){
        Stage windowtemp = new Stage();
        windowtemp.setTitle("ERROR!");
        windowtemp.setMinWidth(200);
        windowtemp.initModality(Modality.APPLICATION_MODAL);
        
        Label error = new Label("The due date of " +"\""+task1+"\" " +"is before the due date of "+"\""+task2+"\".");
        Button OK = new Button("OK");
        OK.setOnAction(o ->windowtemp.close());
        windowtemp.setOnCloseRequest(c -> windowtemp.close() );
        
        VBox page = new VBox(15);
        page.getChildren().addAll(error, OK);
        page.setAlignment(Pos.CENTER);
        
        Scene pageFinal = new Scene(page, 500,150);
        windowtemp.setScene(pageFinal);
        windowtemp.showAndWait();
    }
}
