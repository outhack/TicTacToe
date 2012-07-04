import java.io.BufferedReader;
import java.io.IOException;


public class Player {
	
	private String name;
	private char symbol;
	private Game game;
	
	
	public void initName(BufferedReader br) throws IOException
	{
		String inp;
		System.out.print("name:");
		inp=br.readLine();
		inp=inp.trim();
		
		while(inp.trim().length()<=0)
		{
			System.out.println("-------------"+" ??? Hint: Enter a Name!");
			System.out.print("name:");
			inp=br.readLine();
		}
		
		this.setName(inp);
	}
	
	public void initSymbol(BufferedReader br) throws IOException
	{
		System.out.print("symbol:");
		String symbol;
		symbol=br.readLine();
		symbol.substring(0);
		symbol=symbol.trim();
		while(symbol.substring(0).length()<=0)
		{
			System.out.println("-------------"+" ??? Enter a Symbol!");
			System.out.print("symbol:");
			symbol=br.readLine();
		}
		
		this.setSymbol(Character.toUpperCase(symbol.charAt(0)));

	}
	
	public void play(int x)
	{
		int i=0;
		int j=0;
		
		if(game.getTurn()==this)
		{
			if(x==1){i=0;j=0;}
			else if(x==2){i=0;j=1;}
			else if(x==3){i=0;j=2;}
			else if(x==4){i=1;j=0;}
			else if(x==5){i=1;j=1;}
			else if(x==6){i=1;j=2;}
			else if(x==7){i=2;j=0;}
			else if(x==8){i=2;j=1;}
			else {i=2;j=2;}
			
			if(game.getGrid()[i][j]==' ')
			{
				game.setGridBox(i, j, this.symbol);
				game.incrNumOfMoves();
				game.changeTurn();
				
				if(game.getNumOfMoves()==8)
				{
					String s="";
					for(int a=0;a<game.option.length;a++)
					{
						for(int b=0;b<game.option[0].length;b++)
						{
							s=s+Character.toString(game.option[a][b]);
						}
					}
					s=s.trim();
					game.getTurn().play(Integer.parseInt(s));
					
				}
				game.alert="";
			}
			else 
			{
//				game.alert="That square is already occupied by "+game.getGrid()[i][j]+":"+i+""+j;
				game.alert=x+" is taken! ";
			}
		}
		else game.alert="Not "+name+"'s turn!";
	}
	
	public String getName()
	{
		return name;
	}
	
	public char getSymbol()
	{
		return symbol;
	}
		
	public void setName(String name)
	{
		this.name=name;
	}
	
	public void setSymbol(char symbol)
	{
		this.symbol=symbol;
	}
	
	public void setGame(Game game)
	{
		this.game=game;
	}
}
