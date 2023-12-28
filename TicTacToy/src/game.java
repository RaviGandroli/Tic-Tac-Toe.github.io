import java.util.Random;
import java.util.Scanner;

class TicTacToe
{
	static char[][] board;
	public TicTacToe(int n,int m)
	{
	 board	=new char[n][m];
	//default value of char is /u0000(null character)
	initBoard(n,m);
	}
	
	void initBoard(int n, int m)
	{
		//we initialized null characters to ' '
		try {
			for(int i=0;i<board.length;i++)
			{
				for(int j=0;j<board[i].length;j++)
				{
					board[i][j]=' ';
				}
			}
		}
		catch(Exception e) {
			System.out.println("give the index in the range of 0 to "+n);
			initBoard(n, m);
		}
	}
	
	static void displayBoard(int n,int m)
	{  
		
		
		for(int i=0;i<n;i++)
		{
			for( int j=0;j<m;j++) {
				System.out.print("----");
			    }
			System.out.println();
			System.out.print("| ");
			for(int k=0;k<m;k++)
			{
				System.out.print(board[i][k]+" | ");
			}
			
			System.out.println();
		}
		for( int j=0;j<m;j++) {
			System.out.print("----");
		    }
		
		
		 System.out.println();
	}

   static void placeMark(int row,int col,char mark,int n,int m) 
    {
    	if(row>=0&&row<n && col>=0&&col<m)
    	{
    		board[row][col]= mark;
    	}
    	else 
    	{
    		System.out.println("invalid position");
//    		return;
    	}
    }
    
//   static boolean checkColWin(int n,int m)
//    {
//    	for(int j=0;j<m;j++)
//    	{
//    		if(board[0][j] != ' ' && checkC( n, j))
//            {
//	         return true;
//            }
//    	}
//    	return false;
//    }
//    
//   static boolean checkC(int n,int j) {
//	   for(int i=0;i<n-1;i++) {
//		  if( board[i][j]==board[i+1][j]) {
//			  return true;
//		  }
//	   }
//	   return false;
//   }
   
   static boolean checkColWin(int n, int m) {
	    for (int j = 0; j < m; j++) {
	        if (checkC(n, j)) {
	            return true;
	        }
	    }
	    return false;
	}

	static boolean checkC(int n, int j) {
	    char mark = board[0][j];
	    for (int i = 1; i < n; i++) {
	        if (board[i][j] != mark || mark == ' ') {
	            return false;
	        }
	    }
	    return true;
	}

//   static boolean checkRowWin(int n, int m)
//    {
//    	for(int i=0;i<n;i++)
//    	{
//    		if(board[i][0]!= ' ' &&checkR(i,m))
//            {
//	         return true;
//            }
//    	}
//    	return false;
//    }
//    
//   static boolean checkR(int i,int m) {
//	   for(int j=0;j<m-1;j++) {
//		  if( board[i][j]==board[i][j+1]) {
//			  return true;
//		  }
//	   }
//	   return false;
//   }
   
	static boolean checkRowWin(int n, int m) {
	    for (int i = 0; i < n; i++) {
	        if (checkR(i, m)) {
	            return true;
	        }
	    }
	    return false;
	}

	static boolean checkR(int i, int m) {
	    char mark = board[i][0];
	    for (int j = 1; j < m; j++) {
	        if (board[i][j] != mark || mark == ' ') {
	            return false;
	        }
	    }
	    return true;
	}

   static boolean checkDiagWin(int n, int m) {
    	if(board[0][0]!= ' ' &&checkPD( n, m)
    			|| board[0][m-1]!=' ' && checkRD( n, m))
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
  static boolean checkPD(int n,int m){
	   for (int i = 0; i < Math.min(n, m); i++) {
	        if (board[i][i] == ' ') {
	            return false;
	            
	        }

	        if (i > 0 && board[i][i] != board[i - 1][i - 1]) {
	            return false;
	            
	        }
	    }
	   return true;
   }
  
  static boolean checkRD(int n,int m) {
	  for (int i = 0; i < Math.min(n, m); i++) {
	        int j = m - i - 1;
	        if (board[i][j] == ' ') {
	            return false;
	            
	        }

	        if (i > 0 && board[i][j] != board[i - 1][j + 1]) {
	            return false;
	           
	        }
	    }
	  return true;
  }
   
   static boolean checkDraw(int n, int m) {
	   for(int i=0;i<n;i++) {
		   for(int j=0;j<m;j++) {
			   if(board[i][j]==' ') {
				   return false;
			   }
		   }
	   }
	   return true;
   }

}

abstract class Player{
	String name;
	char mark;
	
	abstract void makeMove(int n,int m);
	
	boolean isValidMove(int row ,int col,int n,int m)
	{
		if(row>=0&&row<n && col>=0 &&col<m)
		{
			if(TicTacToe.board[row][col]==' ') {
				return true;
			}
		}
		System.out.println("..*Enter the valid move*..");
		return false;
	}
}
class HumanPlayer extends Player
{
	
	
	HumanPlayer(String name, char mark)
	{ 
		
		this.name =name;
		this.mark =mark;
	}
	
	void makeMove(int n,int m) 
	{
		int a=n;
		int b=m;
		Scanner scan=new Scanner(System.in);
		int row;
		int col;
		do {
			System.out.println("Enter the row and column");
			 row = scan.nextInt();
			 col =scan.nextInt();
		}while(!isValidMove(row, col,n,m));
		
		TicTacToe.placeMark(row, col, mark, a, b);
		
		
	}
	
	
}

class AiPlayer extends Player
{
	
	
	AiPlayer(String name, char mark)
	{
		this.name =name;
		this.mark =mark;
	}
	
	void makeMove(int n,int m) 
	{ int a=n;
	int b=m;
		
		Scanner scan=new Scanner(System.in);
		int row;
		int col;
		do {
		Random r =	new Random();
		row = r.nextInt(n);
		col = r.nextInt(m);
		}while(!isValidMove(row, col,n,m));
		
		TicTacToe.placeMark(row, col, mark,a,b);
		
		
	}
	
	
}

public class game {

	public static void main(String[] args) {
		Scanner scan =new Scanner(System.in);
		
		System.out.println("enetr the rows and columns games that u wanted to play :");
		int n=scan.nextInt();
		int m=scan.nextInt();
		
		TicTacToe t =new TicTacToe(n,m);
       
       System.out.println("enter your name");
       String name=scan.next();
    HumanPlayer p1=  new HumanPlayer(name, 'X');
   // HumanPlayer p2=  new HumanPlayer("priya", 'O');
    System.out.println("if u want to play with AI press 'A' else if u want to play with your friend press 'P'");
   Player p2=null; 
    
   char c= scan.next().charAt(0);
   if(c=='A'||c=='a') {
     p2= new AiPlayer("AI", 'O');
   }
   else if(c=='P'||c=='p') {
	   System.out.println("enter the name of your friend");
	   String name1=scan.next();
	    p2=  new HumanPlayer(name1, 'O');
   }
    System.out.println("  .....THE GAME STARTS.....");
    Player cp;
    cp=p1;
    
    while(true) {
    	System.out.println(cp.name + " turn");
        cp.makeMove(n,m);
        TicTacToe.displayBoard(n,m);
        
        if(TicTacToe.checkColWin(n,m)||TicTacToe.checkDiagWin(n,m)||TicTacToe.checkRowWin(n,m)) {
        	System.out.println(cp.name + " has won");
        	break;
        }
        else if(TicTacToe.checkDraw(n,m)) {
        	System.out.println("Game is drawed");
        	return;
        }
        else {
        	if(cp== p1) {
        		cp=p2;
        	}
        	else {
        		cp=p1;
        	}
        }
    }
    System.out.println();
    
    

	}

}
