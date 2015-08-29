import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class BoardPanel extends JPanel
{
	//Define variables 
	private final int N = 4;
	private final int SPACE = 10;
	private int counterWin = 0;
	private GameLogic gm = new GameLogic(N);
	private int width, height, x, y, stringX, stringY, fontsize;
	private Tile t;
	
	//Define arrows 
	private final int UP = 0;
	private final int LEFT = 1;
	private final int RIGHT = 2;
	private final int DOWN = 3;


	public BoardPanel()
	{
		this.setFocusable(true);
		this.requestFocusInWindow();
		this.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent event){}
			public void keyReleased(KeyEvent e) {}
			public void keyPressed(KeyEvent event) 
			{	
				// Pressed arrow up
				if (event.getKeyCode() == KeyEvent.VK_UP)
					gm.move(UP);
				// Pressed arrow left
				else if (event.getKeyCode() == KeyEvent.VK_LEFT)
					gm.move(LEFT);
				// Pressed arrow right
				else if (event.getKeyCode() == KeyEvent.VK_RIGHT)
					gm.move(RIGHT);
				// Pressed arrow down
				else if (event.getKeyCode() == KeyEvent.VK_DOWN)
					gm.move(DOWN);
				
				//If winning first time, show info message, otherwise keep playing
				if(gm.win && counterWin == 0)
				{
					counterWin = 1;
					repaint();
					JOptionPane.showMessageDialog(null, "You win!", "", JOptionPane.INFORMATION_MESSAGE);
				}
				//If loose show message and close the game
				if(gm.loose)
				{
					JOptionPane.showMessageDialog(null, "Game Over!", "", JOptionPane.INFORMATION_MESSAGE);
					System.exit(0);
				}
				repaint();
			}
		});
	}

	public void paintComponent(Graphics g)
	{		
		//Define variables
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		//Calculate width and height of each tile from the size of window
		width = (getWidth()-(N+1)*SPACE)/N;
		height = (getHeight()-(N+1)*SPACE)/N;
		
		//Draw board without tiles
		Color c = new Color(179, 170, 127); //Brown dark
		g2d.setColor(c);
		g2d.fillRect(0, 0, getWidth(), getHeight());
		Color c2 = new Color(228, 219, 178, 240); //Brown
		
		//Define font size depends on size of tiles
		fontsize = Math.max(width, height)/3;
		
		
		//Draw board tiles with values
		for(int i= 1; i<=N; i++)
		{
			for(int j = 1; j<=N; j++)
			{
				//Define font of the string value
				Font f = new Font("Dialog", Font.PLAIN, fontsize);
				g2d.setFont(f);
				FontMetrics fm = g2d.getFontMetrics();
				
				//calculate (x,y) of string value depends on tile size
				x = SPACE*i + width*(i-1);
				y = SPACE*j + height*(j-1);
				stringY =  y + height -((height - fm.getAscent()/2)/2);

				t = gm.getBoard()[i-1][j-1];

				//Draw all tiles
				g2d.setColor(c2);
				g2d.fillRoundRect(x , y, width, height, 5, 5);

				//Draw tiles with values
				if( t != null)
				{
					g2d.setColor(t.getColor());
					g2d.fillRoundRect(x , y, width, height, 5, 5);

					stringX = x + ((width - fm.stringWidth(t.getValue() + ""))/2); //location of string depends on it's value
					g2d.setColor(Color.black);
					g2d.drawString(""+t.getValue(), stringX, stringY);
				}
			}
		}
	}

}
