import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Game {

	static String version="1.0";
	private Player turn;
	private int turnHelp=2;
	private char[][]grid={{' ',' ',' '},{' ',' ',' '},{' ',' ',' '}};
	char[][]option=new char[3][3];
	private int numOfMoves=0;
	private Player player1;
	private Player player2;
	private Player winner;
	private Player loser;
	String alert="";
	private BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	private String inp;
	private boolean bool=true;
	
	public Player getWinner()
	{
		return winner;
	}
	
	public Player getLoser()
	{
		return loser;
	}
	
	public int getTurnNum()
	{
		return (turnHelp % 2)+1;
	}
		
	public void setPlayer1(Player p1)
	{
		player1=p1;
	}
	
	public void setPlayer2(Player p2)
	{
		player2=p2;
	}
	
	public void changeTurn()
	{
		turnHelp++;
		if(getTurnNum()==1)
		{
			turn=player1;
		}
		else turn=player2;
	}
	
	public boolean draw()
	{
		if(numOfMoves==9 && !gameHasWinner())
		{
			return true;
		}
		return false;
	}
	
	public boolean gameHasEnded()
	{
		if(draw() || gameHasWinner())
		{
			printGrid();
			return true;
		}
		return false;
	}
	
	public boolean gameHasWinner()
	{
		
		if(grid[0][0]==grid[0][1]&&grid[0][1]==grid[0][2]&&grid[0][0]!=' ')
		{
			if(player1.getSymbol()==grid[0][0])
			{
				winner=player1;
				loser=player2;
			}
			else if(player2.getSymbol()==grid[0][0])
			{
				winner=player2;
				loser=player1;
			}
			return true;
		}
		else if(grid[1][0]==grid[1][1]&&grid[1][1]==grid[1][2]&&grid[1][0]!=' ')
		{
			if(player1.getSymbol()==grid[1][0])
			{
				winner=player1;
				loser=player2;
			}
			else if(player2.getSymbol()==grid[1][0])
			{
				winner=player2;
				loser=player1;
			}
			return true;
		}
		else if(grid[2][0]==grid[2][1]&&grid[2][1]==grid[2][2]&&grid[2][0]!=' ')
		{
			if(player1.getSymbol()==grid[2][0])
			{
				winner=player1;
				loser=player2;
			}
			else if(player2.getSymbol()==grid[2][0])
			{
				winner=player2;
				loser=player1;
			}
			return true;
		}
		else if(grid[0][0]==grid[1][0]&&grid[1][0]==grid[2][0]&&grid[0][0]!=' ')
		{
			if(player1.getSymbol()==grid[0][0])
			{
				winner=player1;
				loser=player2;
			}
			else if(player2.getSymbol()==grid[0][0])
			{
				winner=player2;
				loser=player1;
			}
			return true;
		}
		else if(grid[0][1]==grid[1][1]&&grid[1][1]==grid[2][1]&&grid[0][1]!=' ')
		{
			if(player1.getSymbol()==grid[0][1])
			{
				winner=player1;
				loser=player2;
			}
			else if(player2.getSymbol()==grid[0][1])
			{
				winner=player2;
				loser=player1;
			}
			return true;
		}
		else if(grid[0][2]==grid[1][2]&&grid[1][2]==grid[2][2]&&grid[0][2]!=' ')
		{
			if(player1.getSymbol()==grid[0][2])
			{
				winner=player1;
				loser=player2;
			}
			else if(player2.getSymbol()==grid[0][2])
			{
				winner=player2;
				loser=player1;
			}
			return true;
		}
		else if(grid[0][0]==grid[1][1]&&grid[1][1]==grid[2][2]&&grid[0][0]!=' ')
		{
			if(player1.getSymbol()==grid[0][0])
			{
				winner=player1;
				loser=player2;
			}
			else if(player2.getSymbol()==grid[0][0])
			{
				winner=player2;
				loser=player1;
			}
			return true;
		}
		else if(grid[0][2]==grid[1][1]&&grid[1][1]==grid[2][0]&&grid[0][2]!=' ')
		{
			if(player1.getSymbol()==grid[0][2])
			{
				winner=player1;
				loser=player2;
			}
			else if(player2.getSymbol()==grid[0][2])
			{
				winner=player2;
				loser=player1;
			}
			return true;
		}
		else
			return false;
	}
	
	public void setGridBox(int i,int j,char symbol)
	{
		grid[i][j]=symbol;
	}
	
	public char[][] getGrid()
	{
		return grid;
	}
	
	public void printGrid()
	{
		if(grid[0][0]==' ') option[0][0]='1';
		if(grid[0][1]==' ') option[0][1]='2';
		if(grid[0][2]==' ') option[0][2]='3';
		if(grid[1][0]==' ') option[1][0]='4';
		if(grid[1][1]==' ') option[1][1]='5';
		if(grid[1][2]==' ') option[1][2]='6';
		if(grid[2][0]==' ') option[2][0]='7';
		if(grid[2][1]==' ') option[2][1]='8';
		if(grid[2][2]==' ') option[2][2]='9';
		
		System.out.println("Options");
		for(int i=0;i<option.length;i++)
		{
			System.out.print("| ");
			for(int j=0;j<option[0].length;j++)
			{
				System.out.print(option[i][j]+" | ");
			}
			
			System.out.println();
		}
		System.out.println("Board");
		
		for(int i=0;i<grid.length;i++)
		{
			System.out.print("| ");
			for(int j=0;j<grid[0].length;j++)
			{
				System.out.print(grid[i][j]+" | ");
			}
			
			System.out.println();
		}
	}

	public int getNumOfMoves()
	{
		return numOfMoves;
	}
	
	public Player getTurn()
	{
		return turn;
	}
	
	public void setTurn(Player p)
	{
		turn=p;
	}
	
	public void incrNumOfMoves()
	{
		numOfMoves++;
	}
	
	public void play() throws IOException
	{
		int optionNum=0;
		
		
		while(!gameHasEnded())
		{
			printGrid();
			bool=true;
			
			//Enter box number
			while(bool)
			{
				try
				{
					if(this.alert.compareTo("")==0)
					{
						
					}
					else System.out.println("------------- "+this.alert);
					
					System.out.print(turn.getName()+">");
					inp=br.readLine();
					inp=inp.trim();
					optionNum=Integer.parseInt(inp);
					if(optionNum<1 || optionNum>9)
					{
						System.out.println("**Enter a number! 1 - 9 **");
					}
					else bool=false;
				}
				catch(NumberFormatException nFE)
				{
					System.out.println("**Enter a number! 1 - 9 **");
				}
			}
			
			//Update option box
			for(int i=0;i<option.length;i++)
			{
				for(int j=0;j<option[0].length;j++)
				{
					if(option[i][j]==Character.forDigit(optionNum,10))
					{
						option[i][j]=' ';
					}
				}
			}
			
			//Play
			turn.play(optionNum);
		}
		
		
	}
}
