/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package todolist;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import static todolist.Todolist.taskList;


public class SectionEditProcess {
    public static void labelSelection(int choice, VBox editPage){
        Label edit;
        switch (choice) {
            case 1:
                edit= new Label("Enter the new Title:");
                break;
            case 2:
                edit = new Label("Enter the new Description:");
                break;
            case 3:
                edit = new Label("Enter the new Due Date:");
                break;
            case 4:
                edit = new Label("Enter the new Category:");
                break;
            default:
                edit = new Label("Enter the new Priority:");
                break;
        }
        editPage.getChildren().add(edit);
    }
    
    public static void editTask(int choice, int index, String edit){
        switch (choice) {
            case 1:
                if(!edit.isBlank())
                    Todolist.taskList.get(index).editTaskName(edit);
                else
                    OutputScene.editContentError();
                break;
            case 2:
                if(!edit.isBlank())
                    Todolist.taskList.get(index).editTaskDescription(edit);
                else
                    OutputScene.editContentError();
                break;
            case 3:
                if(TaskDetection.hasValidTimeFormat(edit))
                    Todolist.taskList.get(index).editTaskDuedate(edit);
                else
                    OutputScene.wrongFormat();
                break;
            case 4:
                if(TaskDetection.hasValidCategoryFormat(edit))
                    Todolist.taskList.get(index).editTaskCategory(edit);
                else
                    OutputScene.wrongFormat();
                break;
            default:
                if(TaskDetection.hasValidPriorityFormat(edit))
                    Todolist.taskList.get(index).editTaskPriority(edit);
                else
                    OutputScene.wrongFormat();
                break;
        }
        TaskStorage.saveTasks(Todolist.loggedInUsername, taskList);
    }
    
    public static String LoadContent(int choice, int index){
        String content;
        switch (choice) {
            case 1:
                content= Todolist.taskList.get(index).getTitleName();
                break;
            case 2:
                content= Todolist.taskList.get(index).getTitleDescription();
                break;
            case 3:
                content= Todolist.taskList.get(index).getDuedate();
                break;
            case 4:
                content=Todolist.taskList.get(index).getCategory();
                break;
            default:
                content= Todolist.taskList.get(index).getPriority();
                break;
        }
        return content;
    }
}
