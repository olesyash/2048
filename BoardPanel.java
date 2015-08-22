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
	private final int N = 4;
	private final int SPACE = 10;
	private final int UP = 0;
	private final int LEFT = 1;
	private final int RIGHT = 2;
	private final int DOWN = 3;
	private GameLogic gm = new GameLogic(N);

	public BoardPanel()
	{
		this.setFocusable(true);
		this.requestFocusInWindow();
		this.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent event){}
			public void keyReleased(KeyEvent e) {}
			public void keyPressed(KeyEvent event) 
			{	
				if (event.getKeyCode() == KeyEvent.VK_UP)
				{
					gm.move(UP);
				}
				else if (event.getKeyCode() == KeyEvent.VK_LEFT)
				{
					gm.move(LEFT);
				}
				else if (event.getKeyCode() == KeyEvent.VK_RIGHT)
				{
					gm.move(RIGHT);
				}
				else if (event.getKeyCode() == KeyEvent.VK_DOWN)
				{
					gm.move(DOWN);
				}
				repaint();
			}
		});
	}

	public void paintComponent(Graphics g)
	{		
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		int width = (getWidth()-(N+1)*SPACE)/N;
		int height = (getHeight()-(N+1)*SPACE)/N;
		int x, y, stringX, stringY;
		int fontsize;
		Tile t;
		Color c = new Color(179, 170, 127); //Brown dark
		g2d.setColor(c);
		g2d.fillRect(0, 0, getWidth(), getHeight());
		Color c2 = new Color(228, 219, 178, 240); //Brown
		fontsize = Math.max(width, height)/2;
		for(int i= 1; i<=N; i++)
		{
			for(int j = 1; j<=N; j++)
			{
				x = SPACE*i + width*(i-1);
				y = SPACE*j + height*(j-1);
				Font f = new Font("Dialog", Font.PLAIN, fontsize);
				g2d.setFont(f);
				FontMetrics fm = g2d.getFontMetrics();
				stringX = x + (width-fontsize/2)/2;
				stringY =  y + height -((height - fm.getAscent()/2)/2);

				t = gm.board[i-1][j-1];

				g2d.setColor(c2);
				g2d.fillRoundRect(x , y, width, height, 5, 5);

				if( t != null)
				{
					g2d.setColor(t.getColor());
					g2d.fillRoundRect(x , y, width, height, 5, 5);

					g2d.setColor(Color.black);
					g2d.drawString(""+t.getValue(), stringX, stringY);
				}
			}
		}

	}

}
