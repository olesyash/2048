import java.awt.Color;
import java.util.Random;

public class GameLogic 
{
	//Define arrows
	private final int UP = 0;
	private final int LEFT = 1;
	private final int RIGHT = 2;
	private final int DOWN = 3;
	
	private final int startupTiles = 2; //Number of tiles that appear in the start of the game
	private final int WIN_NUMBER = 2048; //Winning tile  
	private final int BASIC = 2; //Minimum value for starter tiles 
	private Random randomGenerator = new Random(); //To define random location
	private boolean numbers[]; //For keeping location status
	private int n; // size of the board
	private int row, column, place, basic, value; 
	private Tile t; 
	private Tile[][] board; //Board for keeping tiles state
	private boolean full; // True if Board is full 
	public boolean win; // True if winning the game
	public boolean loose; // True if loosing the game

	// Setup variables and start the game 
	public GameLogic(int n)
	{
		this.n = n;
		this.win = false;
		this.loose = false;
		this.board = new Tile[n][n];
		this.numbers = new boolean[n*n];
		createNewTiles(startupTiles);

	}

	// Make move every step in the game using arrays (up, down, right, left) 
	public void move(int direction) //0 up, 1 left, 2 right, 3 down
	{
		Tile newTile;
		Tile merged = null;
		full = false;
		checkGameOver(); //Check if loosed the game
		if(!loose)  // If not, make a step 
		{
			switch (direction) {
			//--------------------  move up -----------------------
			case UP: 
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
									setTile(i, k, t);
									removeTile(i, k+1);				
									k--;

								} 
								//------------ Merge if possible ------------------
								if(k>=0 && board[i][k] != merged)
								{
									newTile = union(board[i][k+1], board[i][k]);
									if(newTile!= null)
									{
										setTile(i, k, newTile);
										removeTile(i, k+1);
										merged = newTile;
									}
								}

							}
						}
					}
				}
				break;
				//--------------------  move left -----------------------
			case LEFT: 

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
									setTile(k, j, t);
									removeTile(k+1, j);
									k--;
								}
								//------------ Merge if possible ------------------
								if(k>=0 && board[k][j] != merged)
								{
									newTile = union(board[k+1][j], board[k][j]);
									if(newTile!= null)
									{
										setTile(k, j, newTile);
										removeTile(k+1, j);
										merged = newTile;
									}
								}

							}
						}
					}
				}

				break;
				//--------------------  move right -----------------------	
			case RIGHT: 
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
									setTile(k, j, t);
									removeTile(k-1, j);
									k++;
								}
								//------------ Merge if possible ------------------
								if(k<n && board[k][j] != merged)
								{
									newTile = union(board[k-1][j], board[k][j]);
									if(newTile!= null)
									{
										setTile(k, j, newTile);
										removeTile(k-1, j);
										merged = newTile;
									}
								}

							}
						}
					}
				}
				break;
				
				//--------------------  move down -----------------------
			case DOWN: 
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
									setTile(i, k, t);
									removeTile(i, k-1);
									k++;

								}
								//------------ Merge if possible ------------------
								if(k<n && merged != board[i][k])
								{
									newTile = union(board[i][k-1], board[i][k]);

									if(newTile!= null)
									{
										setTile(i, k, newTile);
										removeTile(i, k-1);
										merged = newTile;
									}
								}

							}
						}
					}
				}			
				break;

			}
			createNewTiles(1);

		}

	}

	//Function to check if union is possible. If yes, returns new Tile. Check if win after union 
	private Tile union(Tile t1, Tile t2)
	{
		Tile t = null;
		if(t1 != null && t2 != null)
		{
			if(t1.getValue() == t2.getValue())
			{
				t = new Tile(t1.getValue()*2, t2.getRow(), t2.getCol());
				if(t1.getValue()*2 == WIN_NUMBER)
				{
					win = true;
				}
			}
		}
		return t;
	}

	//Function for creating new tiles by generating random place in the board (that is free) 
	private void createNewTiles(int numOfTiles)
	{
		if(!full)
		{
			for(int i=0; i < numOfTiles; i++)
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
	}

	//Function to set new tile using row, column and new tile 
	private void setTile(int col, int row, Tile newTile)
	{
		board[col][row] = newTile;
		int place = col*n + row;
		numbers[place] = true;
	}
//Function to delete tile using row and column 
	private void removeTile(int col, int row)
	{
		board[col][row] = null;
		int place = col*n + row;
		numbers[place] = false;
	}

	//Function to check if loose the game 
	private void checkGameOver()
	{
		boolean flag = true;
		//Check if there is free tile inthe board 
		for(int i = 0; i < n*n; i++)
		{
			if(numbers[i] == false)
			{
				flag = false;
				break;
			}
		}
		if(flag) // No free tiles in the board 
		{
			full = true;
			loose = true;
		}
		//Check if there is possible move (there is some neighbors with same value)
		for(int j=0; j<n; j++)
		{
			for (int i=0; i<n; i++)
			{
				if(board[i][j] != null)
				{
					if (j+1<n && board[i][j+1] != null  && board[i][j].getValue() == board[i][j+1].getValue())
						loose = false; //There is neighbor from bottom with same value, not loosing
					if(i+1<n && board[i+1][j] != null  && board[i][j].getValue() == board[i+1][j].getValue())
						loose = false;  //There is neighbor from right with same value, not loosing
				}
			}
		}
	}
	// Getter
	public Tile[][] getBoard()
	{
		return board;
	}
	
	
}

