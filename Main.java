/*
 * Author:	James Wambugu
 * Project:	Simple TIC-TAC-TOE game
 * Version: 1.0
 * Description:	This is a first implementation of TIC-TAC-TOE game which uses System
 * 				input from a single source for Player1(Human) vs Player2(Human).
 * 				I know it's a bit impractical but future versions will include an 
 * 				AI vs Human and AI vs AI implementation with a GUI.
 * 				Networking features may also be included to allow users to play over a network.
 * 				
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main
{
	private static int maxRounds;
	private static int currentRound=1;
	private static Object[][]result;
	private static Player player1=new Player();
	private static Player player2=new Player();
	private static Game[]game;
	private static String inp;
	private static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	private static boolean boo=false;
	private static boolean gameOver=false;
	
	
	public static void main(String[]args) throws IOException
	{
		//Welcome
		System.out.println("WELCOME TO TIC-TAC-TOE, v"+Game.version);
		System.out.println();
		//-------------------------------------------------------
		
		//Player Initialization
		System.out.println("Enter Player Names and Symbols");
			//Player1
		System.out.println(">Player1<");
		player1.initName(br);		//Initialize Player1.name
		player1.initSymbol(br);		//Initialize Player1.symbol
		System.out.println();
	
			//Player 2
		System.out.println(">Player2<");
		player2.initName(br);		//Initialize Player2.name
		player2.initSymbol(br);		//Initialize Player2.symbol
		System.out.println();
		//-------------------------------------------------------
		
			//The code in the 2 succeeding while statement ensures that the players
			//have different symbols and names. Symbols can be any character except for
			//whitespace characters.
		while(player2.getSymbol()==player1.getSymbol())
		{
			System.out.println("------------- ("+player1.getSymbol()+") is Player1's symbol. Pick a different Symbol!");
			player2.initSymbol(br);
		}
		System.out.println();
		
		if(player1.getName().equals(player2.getName()))
		{
			player2.setName(player2.getName()+"_2"); //Distinguish Player names
		}
		//-------------------------------------------------------
		
			//Print Player1 vs Player2
		System.out.println(player1.getName()+"("+player1.getSymbol()+")"+" vs "+player2.getName()+"("+player2.getSymbol()+")");
		System.out.println();
		//-------------------------------------------------------
		
		
		//Begin Game
		while(!gameOver)
		{			
			//Match Rounds Initialization.
			boo=false;	//ensures boo is set to false before execution of the while statement.
			while(!boo)
			{
				try
				{
					System.out.print("How many Rounds?");
					inp=br.readLine();
					maxRounds=Integer.parseInt(inp);
					if(maxRounds>0)
					{
						if(maxRounds==1)
						{
							System.out.println(maxRounds+" Round it is!");
						}
						else
						{
							System.out.println(maxRounds+" Rounds it is!");
						}
						boo=true;
					}
					else System.out.println("-------------"+" Enter 1 or more!");
				}
				catch(NumberFormatException nFE)
				{
					System.out.println("-------------"+" Please enter a number!");
				}
			}
			System.out.println();
			//-------------------------------------------------------
			
			//Start Match
			//A Match consists of 1 game or a series of maxRounds games.
			game=new Game[maxRounds];	//declare maxRounds games.
			result=new Object[maxRounds+1][2];	//Will Hold Match data.
			result[0][0]="ROUND";
			result[0][1]="WINNER";
			
			for(int i=0;i<game.length;i++)
			{
				game[i]=new Game(); //Initialize game[i] Game Object
				player1.setGame(game[i]); //Associate player1 with game[i]
				player2.setGame(game[i]); //Associate player2 with game[i]
				game[i].setPlayer1(player1); //Associate game[i] with player1
				game[i].setPlayer2(player2); //Associate game[i] with player2
				game[i].setTurn(player1); //Initialize turn to player1
				
				//Print Round, Play game[i]
				System.out.println("Round "+currentRound);
				game[i].play();
				System.out.println();
				//-------------------------------------------------------
				
				//Print Round results, Increment round by 1 if round<maxRounds == game.length
				if(game[i].gameHasWinner())
				{
					System.out.println("-------------"+" :)  -"+game[i].getWinner().getName()+"("+game[i].getWinner().getSymbol()+")"+" won Round ("+currentRound+" of "+maxRounds+")");
					System.out.println("-------------"+" :(  -"+game[i].getLoser().getName()+"("+game[i].getLoser().getSymbol()+")"+" lost");
				}
				else if(game[i].draw()) System.out.println("-------------"+" Round "+currentRound+" is a Draw!");
				currentRound++;
				System.out.println();
				//-------------------------------------------------------
				
			}//End of Match
			//-------------------------------------------------------
			
			//Populate and print the result array with Match results
			for(int i=1;i<result.length;i++)
			{
				result[i][0]=i;
				if(game[i-1].getWinner()!=null)
				{
					result[i][1]=game[i-1].getWinner().getName();
				}
				else result[i][1]="Draw";
			}
			
			print2DArray(result);
			System.out.println();
			//-------------------------------------------------------
			
		
			while(!gameOver)
			{
				//This code prompts the user to decide
				//whether or not to continue playing the game.
				//If yes(Y) then the user re-enters the number of rounds
				//they want to play else no(N) the program stops execution.
				System.out.print("Play Again?(Y/N):");
				inp=br.readLine();
				inp=inp.toUpperCase();
				if(inp.compareToIgnoreCase("Y")==0){
					reset();
					break;
				}
				if(inp.compareToIgnoreCase("N")==0){
					gameOver=true;
				}
				//-------------------------------------------------------
			}
			System.out.println();
		}
		
		//Bye.
		System.out.println("THANKS FOR PLAYING TIC-TAC-TOE v"+Game.version);
		//-------------------------------------------------------
	}
	
	//Resets variables for this class to their initial values
	public static void reset()
	{
		//Resets initial values that may have been changed in the last Match.
		currentRound=1;
		boo=false;
		gameOver=false;
		//-------------------------------------------------------
	}
	
	//Prints the content of an Object object array
	//In this application it is used to print the results of a Match
	public static void print2DArray(Object[][]arr)
	{
		//Prints an the contents of an Object array
		//In this application it is used to print the 
		//results of a Match.
		for(int i=0;i<arr.length;i++)
		{
			for(int j=0;j<arr[0].length;j++)
			{
				System.out.print(arr[i][j]+ "	| ");
			}
			System.out.println();
		}
		//-------------------------------------------------------
	}

}
