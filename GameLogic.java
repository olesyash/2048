import java.awt.Color;
import java.util.Random;

public class GameLogic 
{
	private final int startupTiles = 10;
	private final int WIN = 2048;
	private final int BASIC = 2;
	private Random randomGenerator = new Random();
	private boolean numbers[];
	private int n;
	private int row, column, place, basic, value;
	private Tile t;
	Tile[][] board;

	public GameLogic(int n)
	{
		this.n = n;
		board = new Tile[n][n];
		numbers = new boolean[n*n];
		for(int i=0; i < startupTiles; i++)
		{ 
			do 
			{
				basic = randomGenerator.nextInt(2);
				row = randomGenerator.nextInt(n);
				column = randomGenerator.nextInt(n);
				place  = column*n + row;
			} while(numbers[place]);

			numbers[place] = true;
			switch (basic) {
			case 0:
				value = BASIC;
				break;
			case 1:
				value = BASIC*2;
				break;
			}
			Tile t = new Tile(value, row, column);
			board[column][row] = t;
		}
	}

	public void move(int direction) //0 up, 1 left, 2 right, 3 down
	{
		Tile newTile;
		Tile merged = null;
		switch (direction) {
		case 0: //up
			for(int i=0; i < n; i++)
			{
				for(int j=0; j < n; j++)
				{
					t = null;
					t = board[i][j];
					if(t!= null)
					{
						int k = j-1;
						if(t.getRow() > 0)
						{
							while(k>=0 && board[i][k] == null)
							{
								t.setRow(k);
								board[i][k] = t;
								board[i][k+1] = null;
								k--;

							}
							if(k>=0 && board[i][k] != merged)
							{
								newTile = union(board[i][k+1], board[i][k]);
								if(newTile!= null)
								{
									board[i][k] = newTile;
									board[i][k+1] = null;
									merged = newTile;
								}
							}

						}
					}
				}
			}

			break;
		case 1: //left
	
			for(int j=0; j < n; j++)
			{
				for(int i=0; i < n; i++)
				{
					t = null;
					t = board[i][j];
					if(t!= null)
					{
						int k = i-1;
						if(t.getCol() > 0)
						{
							while(k>=0 && board[k][j] == null)
							{
								t.setCol(k);
								board[k][j] = t;
								board[k+1][j] = null;
								k--;
							}
							if(k>=0 && board[k][j] != merged)
							{
								newTile = union(board[k+1][j], board[k][j]);
								if(newTile!= null)
								{
									board[k][j] = newTile;
									board[k+1][j] = null;
									merged = newTile;
								}
							}

						}
					}
				}
			}

			break;
		case 2: //right
			for(int j=0; j < n; j++)
			{
				for(int i=n-1; i >=0 ;i--)
				{
					t = null;
					t = board[i][j];
					if(t!= null)
					{
						int k = i+1;
						if(t.getCol() < n )
						{
							while(k>0 && k<n && board[k][j] == null)
							{
								t.setCol(k);
								board[k][j] = t;
								board[k-1][j] = null;
								k++;
							}
							if(k<n && board[k][j] != merged)
							{
								newTile = union(board[k-1][j], board[k][j]);
								if(newTile!= null)
								{
									board[k][j] = newTile;
									board[k-1][j] = null;
									merged = newTile;
								}
							}

						}
					}
				}
			}
			break;
		case 3: //down
			for(int i=n-1; i>=0; i--)
			{
				for(int j=n-1; j>=0; j--)
				{
					t = null;
					t = board[i][j];
					if(t!= null)
					{
						int k = j+1;
						if(t.getRow() < n-1)
						{
							while(k<n && board[i][k] == null)
							{
								t.setRow(k);
								board[i][k] = t;
								board[i][k-1] = null;
								k++;

							}
							if(k<n && merged != board[i][k])
							{
								newTile = union(board[i][k-1], board[i][k]);
								
								if(newTile!= null)
								{
									board[i][k] = newTile;
									board[i][k-1] = null;
									merged = newTile;
								}
							}
							
					//		if(k<n && merged != board[i][k])
//								{
//									newTile = union(board[i][k-1], board[i][k]);
//									step(board[i][k-1], board[i][k], newTile);
//									if (newTile != null)
//										merged = newTile;
//								}
							

						}
					}
				}
			}			
			break;

		}
	}

	public Tile union(Tile t1, Tile t2)
	{
		Tile t = null;
		if(t1 != null && t2 != null)
		{
			if(t1.getValue() == t2.getValue())
			{
				t = new Tile(t1.getValue()*2, t2.getRow(), t2.getCol());
			}
		}
		return t;
	}
	
	public void step(Tile oldPlace, Tile newPlace, Tile newTile)
	{
		if (newTile != null)
		{
			newPlace = newTile;
			oldPlace = null;
		}
	}
}

