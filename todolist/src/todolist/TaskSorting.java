/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package todolist;

import java.util.ArrayList;


public class TaskSorting {

    public static void dueDateSort(int choose){
        ArrayList<Integer> temp = new ArrayList<>();
        String temphold;
        for (int i = 0; i < Todolist.taskList.size(); i++) {
            temphold = Todolist.taskList.get(i).getDuedate().replaceAll("-", "");
            temp.add(i, Integer.valueOf(temphold));
        }
        int hold;
        if (choose == 1) {
            for (int pass = 1; pass < temp.size(); pass++) 
            {
                for (int i = 0; i < temp.size() - 1; i++) 
                {
                    if (temp.get(i) > temp.get(i + 1)) 
                    {
                        hold = temp.get(i);
                        temp.set(i, temp.get(i + 1));
                        temp.set(i + 1, hold);
                        
                        Task tempTask = Todolist.taskList.get(i);
                        Todolist.taskList.set(i, Todolist.taskList.get(i+1));
                        Todolist.taskList.set(i+1, tempTask);
                    }
                }
            }
        } else if (choose == 2) {
            for (int pass = 1; pass < temp.size(); pass++) 
            {
                for (int i = 0; i < temp.size() - 1; i++) 
                {
                    if (temp.get(i) < temp.get(i + 1)) 
                    {
                        hold = temp.get(i);
                        temp.set(i, temp.get(i + 1));
                        temp.set(i + 1, hold);

                        Task tempTask = Todolist.taskList.get(i);
                        Todolist.taskList.set(i, Todolist.taskList.get(i+1));
                        Todolist.taskList.set(i+1, tempTask);
                    }
                }
            }
        }
    }
    
    public static void prioritySort(int choose){
        ArrayList<Integer> temp = new ArrayList<>();
        for (int i = 0; i < Todolist.taskList.size(); i++) 
        {
            if (Todolist.taskList.get(i).getPriority().equals("Low")) {
                temp.add(i, 1);
            } else if (Todolist.taskList.get(i).getPriority().equals("Medium")) {
                temp.add(i, 2);
            } else 
                temp.add(i, 3);
        }
        int hold;
        if (choose == 3) {
            for (int pass = 1; pass < temp.size(); pass++) 
            {
                for (int i = 0; i < temp.size() - 1; i++) 
                {
                    if (temp.get(i) < temp.get(i + 1)) 
                    {
                        hold = temp.get(i);
                        temp.set(i, temp.get(i + 1));
                        temp.set(i + 1, hold);
                        
                        Task tempTask = Todolist.taskList.get(i);
                        Todolist.taskList.set(i, Todolist.taskList.get(i+1));
                        Todolist.taskList.set(i+1, tempTask);
                    }
                }
            }
        } else if (choose == 4) {
            for (int pass = 1; pass < temp.size(); pass++) 
            {
                for (int i = 0; i < temp.size() - 1; i++)
                {
                    if (temp.get(i) > temp.get(i + 1))
                    {
                        hold = temp.get(i);
                        temp.set(i, temp.get(i + 1));
                        temp.set(i + 1, hold);

                        Task tempTask = Todolist.taskList.get(i);
                        Todolist.taskList.set(i, Todolist.taskList.get(i+1));
                        Todolist.taskList.set(i+1, tempTask);
                    }
                }
            }
        }
    }
}
