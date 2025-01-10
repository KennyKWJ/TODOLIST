/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package todolist;


import java.util.*;

/**
 *
 * @author USER
 */
public class TaskDependencies {
    private static Map<String, Task> tasks = new LinkedHashMap<>();
    //private List<Task> dependencies;

    public TaskDependencies() {
        this.tasks = new HashMap<>();
    }
    
    public boolean taskIsCompletedBeforeDepend(int index){
        return (!tasks.containsKey(Todolist.taskList.get(index).getTitleName()) && Todolist.taskList.get(index).isComplete());
        
    }
    
    public void addTask(int dependentTaskNumber, int precedingTaskNumber) 
    {
        if (!tasks.containsKey(Todolist.taskList.get(dependentTaskNumber).getTitleName()) &&
                !Todolist.taskList.get(dependentTaskNumber).isRecurring()) 
        {
            tasks.put(Todolist.taskList.get(dependentTaskNumber).getTitleName(), Todolist.taskList.get(dependentTaskNumber));
        } 
        if(!tasks.containsKey(Todolist.taskList.get(precedingTaskNumber).getTitleName()) &&
                !Todolist.taskList.get(precedingTaskNumber).isRecurring())
        {
            tasks.put(Todolist.taskList.get(precedingTaskNumber).getTitleName(), Todolist.taskList.get(precedingTaskNumber));
        }
    }
    
   public void addDependency(String dependentTaskName, String dependencyTaskName) {
    Task dependentTask = tasks.get(dependentTaskName);
    Task dependencyTask = tasks.get(dependencyTaskName);

    if (dependentTask != null && dependencyTask != null) {
        Set<Task> visited = new HashSet<>();
        if (wouldCreateCycle(dependencyTask, dependentTask, visited)) {
            OutputScene.cycleDependencyError();
        } else {
            dependentTask.addDependency(dependencyTask);
            tasks.putIfAbsent(dependentTaskName, dependentTask);
            tasks.putIfAbsent(dependencyTaskName, dependencyTask);

            // Persist changes
            TaskStorage.saveTasks(Todolist.loggedInUsername, new ArrayList<>(tasks.values()));
            OutputScene.dependencyResult(dependentTaskName, dependencyTaskName);
        }
    } else {
        OutputScene.wrongFormat();
    }
}

    public boolean wouldCreateCycle(Task task, Task dependency, Set<Task> visited) {
        
        if (visited.contains(task)) 
        {
            return true; 
        }

        visited.add(task);

        for (Task dependentTask : task.getDependencies()) 
        {
            if (dependentTask == dependency || wouldCreateCycle(dependentTask, dependency, visited)) 
            {
                return true; 
            }
        }
        visited.remove(task);
        return false;
}

  public void markTaskAsCompleted(String taskName, int index) {
    Task task = tasks.get(taskName);
    if (task != null) {
        String unmetDependencies = getUnmetDependencies(task);
        if (!unmetDependencies.isEmpty()) {
            OutputScene.dependencyManageError(taskName, unmetDependencies);
        } else {
            task.setComplete();
            OutputScene.manageResult(taskName);
        }
    } else 
    {
        Task selectedTask = Todolist.taskList.get(index);
        String unmetDependencies = getUnmetDependencies(selectedTask);
        if (!unmetDependencies.isEmpty()) {
            OutputScene.dependencyManageError(selectedTask.getTitleName(), unmetDependencies);
        } else {
            selectedTask.setComplete();
            TaskStorage.saveTasks(Todolist.loggedInUsername, Todolist.taskList);
            OutputScene.manageResult(selectedTask.getTitleName());
        }
    }
}
    public Map<String, Task> getTasks() {
        return tasks;
    }
    
    public String getUnmetDependencies(Task task) {
        if (task.getDependencies() == null || task.getDependencies().isEmpty()) 
        {
            return ""; 
        }
        List<String> unmetDependencies = new ArrayList<>();
        for (Task dependency : task.getDependencies())
        {
            if (!dependency.isComplete()) 
            {
                unmetDependencies.add(dependency.getTitleName());
            }
        }
            return String.join(", ", unmetDependencies);
        }
    
    public void removeTask(String taskName) {
        Task taskToRemove = tasks.get(taskName);

            for (Task task : tasks.values()) 
            {
                if (task.getDependencies().contains(taskToRemove)) 
                {
                    task.getDependencies().remove(taskToRemove);
                }
            }
            tasks.remove(taskName);
        
        
    }
}
