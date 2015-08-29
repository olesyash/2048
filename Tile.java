import java.awt.Color;

// Class Tile define's one Tile with value, color and location(row,column)
public class Tile
{
	private int value;  // The number that written in tile
	private int row; 
	private int column;
	private Color color;
	private final int BASIC = 2; //Minimum value

	Tile(int v, int r, int col)
	{
		value = v;
		row = r;
		column = col;
		
		// --------- Define color for each value -------------
		if(value == BASIC) 						//2
			color = Color.decode("#E0E0E0"); 
		else if(value == Math.pow(BASIC, 2))	//4
			color = Color.white;
		else if(value == Math.pow(BASIC, 3))	//8	
			color = Color.decode("#FFFF99");
		else if(value == Math.pow(BASIC, 4))	//16
			color = Color.decode("#FFFF5C");
		else if(value == Math.pow(BASIC, 5))	//32
			color = Color.decode("#FFCC66");
		else if(value == Math.pow(BASIC, 6))	//64
			color = Color.decode("#FF9966");
		else if(value == Math.pow(BASIC, 7))	//128
			color = Color.yellow;
		else if(value == Math.pow(BASIC, 8))	//256
			color = Color.decode("#FFA3A3");
		else if(value == Math.pow(BASIC, 9))	//512
			color = Color.decode("#CCFFCC");
		else if(value == Math.pow(BASIC, 10))	//1024
			color = Color.decode("#FFCCCC");
		else if(value == Math.pow(BASIC, 11))	//2048
			color = Color.decode("#FFFF00");
		else if(value == Math.pow(BASIC, 12))	//4096
			color = Color.decode("#FFD800");
		else color = Color.white;
	}
	
//----------------- Setters and Getters --------------------
	public int getValue()
	{
		return value;
	}
	public Color getColor()
	{
		return color;
	}
	public int getRow()
	{
		return row;
	}
	public void setCol(int r)
	{
		this.column = r;
	}
	public void setRow(int r)
	{
		this.row = r;
	}
	public int getCol()
	{
		return column;
	}

}