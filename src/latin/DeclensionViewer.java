package latin;

import javax.swing.JFrame;

public class DeclensionViewer 
{
	public static void main(String[] args) 
	{
		JFrame frame = new DeclinerFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setResizable(false);
	}

}
