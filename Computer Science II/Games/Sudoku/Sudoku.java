package sudokuplayer;
import java.util.*;
import java.lang.*;

public class Sudoku {
    private char[][] board;
    
    public Sudoku(){
        board = new char[9][9];
    }
    public Sudoku(String startConfig){
        board = new char[9][9];
        int count=0;
        for(int row=0; row<9; row++){
            for(int col=0;col<9;col++){
                if(startConfig.charAt(count)=='\n') count++;
                board[row][col]=startConfig.charAt(count);
                count++;
            }
        }
    }
    public char getSquare(int row, int col){
        return board[row][col];
    }
    
    public void setSquare(int row, int col, char val){
        board[row][col]=val;
    }
    
    public boolean isValid(){
        for(int i=0; i<3;i++){
            for(int j=0; j<9;j++){
                switch(i){
                    case 0:
                        if(!colValid(j)) return false;
                    case 1:
                        if(!rowValid(j)) return false;
                    case 2:
                        if(!squareValid(j)) return false;
                }
            }
        }
        return true;
    }
    
    public boolean isSolved(){
        int count=0;
        if(isValid()){
            for(int row=0; row<9; row++){
                for(int col=0;col<9;col++){
                    if(board[row][col]==' ') return false;
                }
            }
            return true;
        }
        return false;
    }
    private boolean colValid(int col){
        ArrayList<Character>list = new ArrayList<Character>();
        for(int row=0; row<9;row++){
            if(!(list.contains(board[row][col]))){
                list.add(board[row][col]);
            }
            else{
                return false;
            }
        }
        return true;
    }
    private boolean rowValid(int row){
        ArrayList<Character>list = new ArrayList<Character>();
        for(int col=0; col<9;col++){
            if(!(list.contains(board[row][col]))){
                list.add(board[row][col]);
            }
            else{
                return false;
            }
        }
        return true;
    }
    private boolean squareValid(int square){
        int [] rowArray= {0,3,6,9};
        int [] colArray= {0,3,6,9};
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                ArrayList<Character>list = new ArrayList<Character>();
                for(int row=rowArray[i]; row<rowArray[i+1]; row++){
                    for(int col=colArray[j];col<colArray[j+1];col++){
                        if(!(list.contains(board[row][col]))){
                            list.add(board[row][col]);
                        }
                        else return false;
                    }
                }
            }
        }
        return true;
    }
}
