package Game;

public class ShowConnect4GUI {
	

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				Connect4 myGUI = new Connect4();
			}
		});
	}
}
