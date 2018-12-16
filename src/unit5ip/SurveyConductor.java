/**
    *          Individual Project 5
    *          Nathaniel Kerr
    *          9/12/2016
    *          Introduction to Java Programming II 
    *          (IT152-1603B-01)
    */  

package unit5ip;

import javax.swing.JOptionPane; //for the use of JOptionPane so I may utilize the dialog boxes
import javax.swing.JPanel;
import java.util.Random;

            
class Survey{
    
    public static int respondentId = 0;     //Set to 10 merely so each of the methods can be displayed in use when the static variable changes                       
    String questions[] = new String [10];           //stores questions
    int grid[][] = new int [10][10];                //stores grid locations based off respondentId and question #
    String surveyName;  
    
    public Survey() {
        surveyName = "Public Survey";       /*This overloaded constructor will only put Public Survey
        //commentsection                        into the surveyName variable      */
    }
    
    public Survey(String tempname) {        //Overloaded Constructor with a string argument
        respondentId = 0;                   //Static int respondentId will be reset to 0
        surveyName = tempname;              //String argument is placed in the surveyName variable
    //    System.out.println(surveyName+" is the survey name.");      //debugging message
    //    System.out.println(respondentId+" is the respondent Id.");  //debugging message
    }
    
    String nameGet(){       //Getter method to return the survey name. 
        return surveyName;      //Returns a string value.
    }
    
    public int generateRespondentId() {     //Method that is run at the end of main()
        respondentId++;                     //Respondent Id variable is incremented by one
        return respondentId;                //Integer value in respondentId is returned
    }
    
    public void displaySurveyResults() {
        int counter, i, row, column;
        for(counter=0;counter<grid.length;counter++){   //this determines the amount of respondent ID's registered within the log
            if(!(grid[counter][0] != 0))                   //if there is a value in the grid element continue, otherwise break the loop
                break;
        }
        System.out.println();
        System.out.println("\t---Survey '"+surveyName+"'---\n");
        
        /*
        The loop below has multiple nested loops. The functional loop contains row; the others are for aesthetics
        */
        for(column=0;column<grid.length;column++){              
            if(column == 0){                        //condition to setup aesthetics
                System.out.print("\t\tRespondent ID answers\n\t\t");
                for(i=0;i<counter;i++){             //loop to setup aesthetics
                    System.out.print("ResID#"+i+"\t\t");
                }
                System.out.println();
                System.out.print("\t\t");
                while(i!=-1){
                    System.out.print("--------");
                    i--;
                }   
                System.out.println();
            }
                
            for(row=0;row<counter;row++) {      //nested loop to enter a complete row
                if(row == 0)
                    System.out.print("Question #"+(column+1)+"\t|");
                System.out.print("  "+grid[row][column]+"\t\t");
            }
                System.out.println();
        }
        
        System.out.println();
        //must display grid that shows results
    }
    
    public void displayQuestionStats(int question) {    //takes question number and returns currently inserted response to that question in tabular form
        int counter, i;
        System.out.println("\n|--Question #"+ question+" answers--|");  
        for(counter=0;counter<grid.length;counter++){   //this determines the amount of respondent ID's registered within the log
            if(!(grid[counter][0] != 0))
                break;
        }
        for(i=0;i<counter;i++) {            //Loop enters each respondent ID for the question
            System.out.print("\tResID#"+(i));
        }
        System.out.println();
        System.out.print(questions[question-1]+"\t");       //question element value is displayed
        for(i=0;i<counter;i++){
            System.out.print(grid[i][question-1]+"\t");     //all respondent answers are displayed
        }
        System.out.println();
        

    }
    
    public void logResponse(int xId, int y, int response){   //respondent ID is used as the x value, and the question # is used as the y value.
        grid[xId][y] = response;                            //respondent answer is stored that the value determined
//        System.out.println("\txid " +xId+"\ty "+y+" \tgrid value "+ grid[xId][y]); //debugging output
        
    }  
    
    public void enterQuestions() {
        int counter, i;
        String tempName = "";       //initializing variable to temporarily store a string
        for(counter=0;counter<questions.length;counter++){  //loop to enter each question
            if(counter == 0){                                   //if this is the first iteration of the loop
                while(tempName.isEmpty()){
                    tempName = JOptionPane.showInputDialog("Do you want to autogenerate questions?");
                }
            }
            
            if(tempName.equalsIgnoreCase("no")){      //if no value was entered or no, then questions will be entered manually
                questions[counter] = JOptionPane.showInputDialog("Please enter question "+(counter+1));
                System.out.println("question #" +(counter+1)+" is: What is " +questions[counter]); //print the entered values
            }
            
            else if(tempName.equalsIgnoreCase("yes")){
                questions[counter] = "Q"+(counter+1)+"?";   //autogenerate questions if "no" was not entered
                System.out.println("question #" +(counter+1)+" is: What is " +questions[counter]); //print the entered values
            }
            else{
                JOptionPane.showMessageDialog(null, "Please enter yes or no", "Input Error", 0);
                tempName = "";
                counter--;
            } 
            
        }
        
        
    }
    public int topRatedQuestion(){
        int i, t, qNum=0, counter;
        int qRate[] = new int[10];
        for(i=0;i<10;i++)   //since qRate is a static array this ensures it is cleared before the algorithm continues
            qRate[i] = 0;
        for(counter=0;counter<grid.length;counter++){   //this determines the amount of respondent ID's registered within the log
            if(!(grid[counter][0] != 0))
                break;
        }    
        for(i=0;i<10;i++){
            for(t=0;t<counter;t++) {
                qRate[i] += grid[t][i];
            }
        }
        t=0; //reset variable for different use
        
        for(i=0;i<10;i++){
            if(qRate[i]>t){
                t=qRate[i];
                qNum = i;
            }
        }
            
    return (qNum+1);     //the variable needs to increment, becuase question #'s have been incremented to make 1-10 instead of 0-9
        
    }
    public int lowRatedQuestion() {
        int i, t, qNum=0, counter;
        int qRate[] = new int[10];
        for(i=0;i<10;i++)   //since qRate is a static array this ensures it is cleared before the algorithm continues
            qRate[i] = 0;
        
        for(counter=0;counter<grid.length;counter++){   //this determines the amount of respondent ID's registered within the log
            if(!(grid[counter][0] != 0))
                break;
        }    
        for(i=0;i<10;i++){
            for(t=0;t<counter;t++) {
                qRate[i] += grid[t][i];
            }
        }
        
        t=100; //reset variable for different use. Since the numbers will progress downward, we will start at a high value.
        
        for(i=0;i<10;i++){      //another loop to sort through our array of summed scores
            if(qRate[i]<t){     //this makes the only difference from topRatedQuestion method. The variable returned will be the lowest.
                t=qRate[i];
                qNum = i;
            }
        }
            
        return (qNum+1);   //the variable needs to increment, becuase question #'s have been incremented to make 1-10 instead of 0-9
    }
    public void presentQuestion(int qNum) {
        System.out.println("\nQuestion #"+qNum+" value :"+questions[qNum]);
    }
    
    public void presentQuestion(int qNum, int rID){
        System.out.println("\nQuestion #"+qNum+" value :"+questions[qNum]+ "and your respondent ID is:"+rID);
    }
    
}

public class SurveyConductor {

   
    @SuppressWarnings("static-access")
    public static void main(String[] args) {
       
        Random randInt = new Random();       //Utilized for respondentId automated responses
        final JPanel stderr = new JPanel();  //Utilized for error messages
        int counter=0, i, t=0, c;      //loop control variables
        String tempString=""; //Made empty for conditional statements;
        //String questions[][] = new String [10][10];

        Survey defaultSurvey = new Survey();    //Creating the survey that will have the default name of "Public Survey"
        System.out.println("RespondentId Logged on: "+Survey.respondentId+"\nSurvey Title: "+defaultSurvey.nameGet());
        JOptionPane.showMessageDialog(null, "Respondent Id is " + Survey.respondentId + " just to start");
        
        while(tempString.isEmpty()){ //This prevents the user from accidentally entering an empty value
            tempString = JOptionPane.showInputDialog("Please enter a name for your new survey:");   //This variable will store the users input    
        }
        
        Survey respondentSurvey = new Survey(tempString);  //User input is sent to a new object. 
        respondentSurvey.respondentId = 0;                 //ensure respondentId starts at 0
        
        System.out.println("\nPlease Enter Survey Questions");
        respondentSurvey.enterQuestions();      //enterQuestions() method call
        
        /*
        LogResponse section below. Automated system ready for quick testing.
        */
        while(counter == 0){
            for(i=0;i<10;i++) {
                while(t==0)     //Only perform automated check on the first iteration
                t = Integer.parseInt(JOptionPane.showInputDialog("Enter answer to question " +(i+1)+" or type 2016 to automate"));

                if(t==2016){ //If you've chosen not to generate answers
                    c = randInt.nextInt(5)+1;                  //algorithm for a semi-random number for automated system
                    respondentSurvey.logResponse(respondentSurvey.respondentId, i, c);
                    
                    if(i == 0 && Survey.respondentId == 0){
                        System.out.println("Automating responses...");  
                    }
                }
                else if(t<1 || t>5){
                    JOptionPane.showMessageDialog(null, "Please enter a value between 1 and 5", "Input Error", 2);
                    i--;
                    t=0;
                }
                else{               //Manual entry of answers
                    respondentSurvey.logResponse(respondentSurvey.respondentId, i, t);
                    t = 0;
                }    
            }
            if(Survey.respondentId != 9)
                counter = JOptionPane.showConfirmDialog(null, "Do you want to continue as respondent #"+(Survey.respondentId+1)+"?");
            else
                counter = 1;
            if(counter == 0)
                respondentSurvey.generateRespondentId(); //generateRespondentID method is run 
        }       
        
        respondentSurvey.displaySurveyResults();
        
        counter = 0;
        tempString = "";
        
        while(counter == 0){    //this loop is a control for a user to utilize the displayQuestionStats method
                                    //multiple times
            while(tempString.isEmpty()){       //a control in case "ok" is clicked without entering a value
            tempString = JOptionPane.showInputDialog("Please enter a question number to view respondent answers (1-10)");
            }
            t = Integer.parseInt(tempString);
            if(t > 10 || t < 1){
                JOptionPane.showMessageDialog(null, "Please enter a value between 1 and 10", "Input Error", 2);
                tempString = "";
            }
            else{
                respondentSurvey.displayQuestionStats(t);        /*displayQuestionStats method is called with 
                //commentsection//                                   atemporary integer value given as an argument    */

                counter = JOptionPane.showConfirmDialog(null, "Do you want to enter another value?"); /*hitting "yes" makes counter = 0, continuing loop.
                //commentsection//                                                                        Other options do not and result in the loop ending.*/
                tempString = "";         //maintains nested loop 
            }    
            
        }  
        
        
        t = 0;              
        counter = 0;        //resetting variables to recycle
        tempString = "";
        
        
        
        
        while(counter == 0){
            while(tempString.isEmpty()){
                tempString = JOptionPane.showInputDialog("Would you like to review your ID and a specific question?");
            }
            
            if(tempString.equalsIgnoreCase("yes")){
                
                while(t == 0){
                    t = Integer.parseInt(JOptionPane.showInputDialog("Please enter the question number."));
                }

                respondentSurvey.presentQuestion((t-1), respondentSurvey.respondentId);
                counter = 1;
            }

            else if(tempString.equalsIgnoreCase("no")){
                tempString = "";
                while(tempString.isEmpty()) {
                    tempString = JOptionPane.showInputDialog("Very well. Would you like to view a question without your ID?");
                }

                if(tempString.equalsIgnoreCase("yes")){ //not to be confused, the same variable is used once it passes the threshold and recycled in theses nested conditional statements
                   
                    while(t == 0){
                        t = Integer.parseInt(JOptionPane.showInputDialog("Please enter the question number."));
                    }
                   respondentSurvey.presentQuestion((t-1)); 
                   counter = 1;
                }
                else if(tempString.equalsIgnoreCase("no")){
                   counter = 1;
                }
                else{
                    JOptionPane.showMessageDialog(stderr, "Please try again", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }

            else{
                JOptionPane.showMessageDialog(stderr, "Please try again", "Error", JOptionPane.ERROR_MESSAGE);
                counter = 0;
                t=0;
                tempString = "";
            }
        }    
        JOptionPane.showMessageDialog(null,"Thank you for participating!\nHere are the question ratings!", "Survey Complete", 1);
        System.out.println("\nQuestion #"+respondentSurvey.topRatedQuestion()+" is the highest rated!\nQuestion #"+respondentSurvey.lowRatedQuestion()+" is the lowest rated!");
    }    
    
    
}
