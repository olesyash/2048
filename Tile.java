import java.awt.Color;


public class Tile
{
	private int value;
	private int row;
	private int column;
	private Color color;
	private final int BASIC = 2;

	Tile(int v, int r, int c)
	{
		value = v;
		row = r;
		column = c;
		switch (value) 
		{
		case BASIC:
			color = Color.white;
			break;
		case BASIC*2:
			color = Color.yellow;
			break;
		case BASIC*BASIC*BASIC:
			color = Color.orange;
			break;
		default:
			color = Color.gray;
			break;
		}

	}
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
	public void setRow(int r)
	{
		this.row = r;
	}
	public int getCol()
	{
		return column;
	}

}