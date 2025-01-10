/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package todolist;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;


public class TaskSearching {
    
    public static VBox TaskSearch(String searchword){
        VBox newPage = new VBox(15);
        int count = 0;
        for (int i = 0; i < Todolist.taskList.size(); i++) 
        {
            String titleLower = Todolist.taskList.get(i).getTitleName().toLowerCase();
            String descriptionLower = Todolist.taskList.get(i).getTitleDescription().toLowerCase();
            if (titleLower.contains(searchword) || descriptionLower.contains(searchword)) 
            {
                count++;
                if (Todolist.taskList.get(i).isComplete()==true) 
                {
                    Label counterYes = new Label(count + ". [Complete] " + Todolist.taskList.get(i).getTitleName() + " - Due: " + Todolist.taskList.get(i).getDuedate() + " - Category: " + Todolist.taskList.get(i).getCategory() + " - Priority: " + Todolist.taskList.get(i).getPriority());
                    newPage.getChildren().add(counterYes);
                } 
                else
                {
                    Label counterNo = new Label(count + ". [Incomplete] " + Todolist.taskList.get(i).getTitleName() + " - Due: " + Todolist.taskList.get(i).getDuedate() + " - Category: " + Todolist.taskList.get(i).getCategory() + " - Priority: " + Todolist.taskList.get(i).getPriority());
                    newPage.getChildren().add(counterNo);
                }
            }

            if (count == 0) 
            {
                Label counterZero = new Label("The searchword or keyword " + searchword + " does not exist.");
                newPage.getChildren().add(counterZero);
            }
        }
        return newPage;
    }
}
