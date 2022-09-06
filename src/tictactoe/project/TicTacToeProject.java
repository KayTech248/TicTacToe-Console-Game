/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe.project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Kay Dan
 */
public class TicTacToeProject {

    /**
     * @param args the command line arguments
     */
    static ArrayList <Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList <Integer> cpuPositions = new ArrayList<Integer>();
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        char choice = 'Y';
        while( choice == 'Y' || choice == 'y')
        {
            // TODO code application logic here
            int pos = 0;



           //2D Array for board development
           char board [][]= {{ ' ', '|', ' ','|', ' '},
                            {'-', '+', '-','+', '-'},
                            {' ', '|', ' ','|', ' '},
                            {'-', '+', '-','+', '-'},
                            {' ', '|', ' ','|', ' '}};

            System.out.println("WELCOME TO TIC TAC TOE  :)!!");
           System.out.println("XOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXO");        
            displayBoard(board);
                 System.out.println("XOXOXOXOXOXOXOXOXOXOXOXOXOXOXOXO");  

            while(true)
            {

               Scanner cin = new Scanner(System.in);
               System.out.print("Please enter your placing position[1-9]: ");
               ///pos = cin.nextInt();
               boolean flag = true;
                if(!cin.hasNextInt())
                {
                        System.out.println("Not a number....Please enter an integer [1-9]");
                }
                else
                {
                    pos = cin.nextInt();
                    while(pos < 1 || pos > 9)
                    {
                         System.out.print("Invalid position. Please enter your placing position[1-9]: ");
                         pos = cin.nextInt();
                    }               
                    //Un-accept taken positions
                    while(playerPositions.contains(pos) || cpuPositions.contains(pos))
                    {

                        System.out.println("postion  "+ pos + " is taken");
                        System.out.print("Please enter your placing position[1-9]: ");
                        pos = cin.nextInt();
                        while(pos < 1 || pos > 9)
                        {
                             System.out.print("Invalid position. Please enter your placing position[1-9]: ");
                             pos = cin.nextInt();
                        }                   
                    }

                    //When User plays
                    placeXandO(board, "USER", pos);
                    if(checkWinner().length() > 0)
                    {
                        displayBoard(board);
                        System.out.println(checkWinner());

                        break;
                    }

                    //When CPU plays
                    Random randValue = new Random();
                    //Randomize between 1 and 9
                    pos = randValue.nextInt(9) + 1;

                    //Un-accept taken positions
                    while(playerPositions.contains(pos) || cpuPositions.contains(pos))
                    {
                        pos = randValue.nextInt(9) + 1;
                    }           
                    placeXandO(board, "CPU", pos);
                    displayBoard(board);
                    if(checkWinner().length() > 0)
                    {
                        System.out.println(checkWinner());
                        break;

                    }  
                }
          
            }
            
            //Clear lists
            playerPositions.clear();
            cpuPositions.clear();
            System.out.println("");
            System.out.print("Do you want to play again[Y/N]: ");
            choice = in.next().charAt(0);
            
        }
        
    }
    public static void displayBoard(char [][] b)
    {
     
       for(int i = 0; i < 5; i++)
       {
           for(int j = 0; j < 5;j++)
           {
               System.out.print(b[i][j]);
           }
           System.out.println("");
       }
  
    }
    
    public static void placeXandO(char [][]board, String user, int pos)
    {
        char symbol = ' ';
        //allocate symbol to user and cpu
        if(user.equals("USER"))
        {
            symbol = 'X';
            playerPositions.add(pos);
        }
        else if(user.equals("CPU"))
        {
            symbol = 'O';
            cpuPositions.add(pos);
        }
        
        //Allocate Placement to Postion on Board
        switch(pos)
        {
            case 1:
                board[0][0]= symbol;
                break;
            case 2:
                board[0][2]= symbol;
                break;
            case 3:
                board[0][4]= symbol;
                break;
            case 4:
                board[2][0]= symbol;
                break;
            case 5:
                board[2][2]= symbol;
                break;
            case 6:
                board[2][4]= symbol;
                break;
            case 7:
                board[4][0]= symbol;
                break;
            case 8:
                board[4][2]= symbol;
                break;
            case 9:
                board[4][4]= symbol;
                break;                
            default:
                System.out.println("Invalid placement position");
        
        
        }
        
        //displayBoard(board);
    }
    // Check winner by comparing all possible combinations using a list
    public static String checkWinner()
    {
        //Winning rows, cols and crosses
        List topRow = Arrays.asList(1,2,3);
        List  midRow= Arrays.asList(4,5,6);
        List botRow = Arrays.asList(7,8,9);
        List leftCol = Arrays.asList(1,4,7);
        List midCol = Arrays.asList(2,5,8);
        List rightCol = Arrays.asList(3,6,9);
        List cross1 = Arrays.asList(1,5,9);
        List cross2 = Arrays.asList(3,5,7);  
        
        ArrayList <List> winningList = new ArrayList<List>();
        winningList.add(topRow);
        winningList.add(midRow);
        winningList.add(botRow);
        winningList.add(leftCol);
        winningList.add(midCol);
        winningList.add(rightCol);
        winningList.add(cross1);
        winningList.add(cross2);
        
        //iterate through the winning lists
        for(List w: winningList)
        {
            if(playerPositions.containsAll(w))
                return "You win !! :)";
            else if(cpuPositions.containsAll(w))
                return "CPU wins!! :(";
            else if(playerPositions.size() + cpuPositions.size() == 9)
                return "Draw!!";
            
        }
        return "";
    }
    
}
