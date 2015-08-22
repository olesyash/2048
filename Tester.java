import javax.swing.JFrame;


public class Tester
{
	 public static void main(String[] args)
	    {
	        JFrame frame = new JFrame("Tester");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setSize(400, 400);
	        BoardPanel bp = new BoardPanel();
	        frame.add(bp);
	        frame.setVisible(true);
	    }
}
