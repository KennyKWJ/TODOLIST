/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package todolist;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import static todolist.Todolist.loggedInUsername;



public class DataCollect {
    
    public static int collectNumTask(){
        String recurringFilename = loggedInUsername + "_recurring_tasks.csv";
        String nonRecurringFilename = loggedInUsername + "_non_recurring_tasks.csv";
        
        int countRecur=0, countNonRecur=0;
        
        try(BufferedReader reader1 = new BufferedReader (new FileReader(recurringFilename));
            BufferedReader reader2 = new BufferedReader (new FileReader(nonRecurringFilename));){
            
            reader1.readLine(); 
            reader2.readLine();
            String line;
            while((line = reader1.readLine()) != null)
            {
                String[] parts = line.split(",", -1);
                if (parts.length < 8) 
                {
                    continue;
                }
                countRecur+=(Integer.parseInt(parts[7])+1);     
            }
            
            while((reader2.readLine()) != null)
            {
                countNonRecur++;
            }
            
            reader1.close();
            reader2.close();
            
        }catch (FileNotFoundException e) {
            OutputScene.failToFindFile();
        } catch (IOException e) {
            OutputScene.failToLoadFile();
        }
       return countRecur+countNonRecur; 
    }
    
    public static int collectNumComplete(){
        String recurringFilename = loggedInUsername + "_recurring_tasks.csv";
        String nonRecurringFilename = loggedInUsername + "_non_recurring_tasks.csv";
        
        int countRecur=0, countNonRecur=0;
        
        try(BufferedReader reader1 = new BufferedReader (new FileReader(recurringFilename));
            BufferedReader reader2 = new BufferedReader (new FileReader(nonRecurringFilename));){
            
            reader1.readLine(); 
            reader2.readLine();
            
            String line1,line2;
            while ((line1 = reader1.readLine()) != null) 
            {
                String[] parts = line1.split(",", -1);

                countRecur+=Integer.parseInt(parts[7]);
            }
            
            while ((line2 = reader2.readLine()) != null) 
            {
                String[] parts = line2.split(",", -1);

                if(Boolean.parseBoolean(parts[5]))
                    countNonRecur++;
                
            }
            
            reader1.close();
            reader2.close();
                
        }catch (FileNotFoundException e) {
            OutputScene.failToFindFile();
        } catch (IOException e) {
            OutputScene.failToLoadFile();
        }
        return countRecur+countNonRecur;
    }
    
    public static int calculatePending(int numTask, int numComplete){
        return numTask-numComplete;
    }
    
    public static String calculateCompleteRate(int numTask, int numComplete){
        DecimalFormat df = new DecimalFormat("0.00");
        String percent = String.valueOf(df.format(100.00*numComplete/numTask));
        percent+="%";

        return percent;
    }
    
    public static int[] collectCategory(){
        int[] number = {0,0,0};
        
        String recurringFilename = loggedInUsername + "_recurring_tasks.csv";
        String nonRecurringFilename = loggedInUsername + "_non_recurring_tasks.csv";
               
        try(BufferedReader reader1 = new BufferedReader (new FileReader(recurringFilename));
            BufferedReader reader2 = new BufferedReader (new FileReader(nonRecurringFilename));){
            
            reader1.readLine(); 
            reader2.readLine();
            
            String line1,line2;
            while ((line1 = reader1.readLine()) != null) 
            {
                String[] parts = line1.split(",", -1);

                if(parts[3].equals("Homework"))
                    number[0]++;
                else if(parts[3].equals("Personal"))
                    number[1]++;
                else
                    number[2]++;
            }
            
            while ((line2 = reader2.readLine()) != null) 
            {
                String[] parts = line2.split(",", -1);

                if(parts[3].equals("Homework"))
                    number[0]++;
                else if(parts[3].equals("Personal"))
                    number[1]++;
                else
                    number[2]++;
            }
            
            reader1.close();
            reader2.close();
                
        }catch (FileNotFoundException e) {
            OutputScene.failToFindFile();
        } catch (IOException e) {
            OutputScene.failToLoadFile();
        }
        return number;
    }
}
