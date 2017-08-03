package Game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Connect4 extends JFrame implements ActionListener, Connect4_Interface{
	private JPanel jpMain;
	private JPanel jpBoard;
	private JPanel jpIO;
	private JLabel displayOut;
	private JButton [][] board;// = new JButton[6][7];
	private String currPlayer = "1";


	public Connect4(){
		this.setTitle("CONNECT 4");
		jpMain = new JPanel();
		jpMain.setLayout(new BorderLayout());

		jpIO = new JPanel();
		displayOut = new JLabel();
		updateOut("Let's play ! Player "+currPlayer+" goes first");
		jpIO.add(displayOut);
		jpMain.add(jpIO, BorderLayout.NORTH);

		jpBoard = new JPanel();
		jpBoard.setLayout(new GridLayout(6,7));
		displayBoard();

		jpMain.add(jpBoard, BorderLayout.CENTER);
		
		add(jpMain);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(700, 600);
		this.setVisible(true);

	}

	public void updateOut(String msg){
		if(currPlayer.equalsIgnoreCase("1")){
			displayOut.setText("<HTML><H1 color=blue>"+msg+"</H1></HTML>");
		}
		else if(currPlayer.equalsIgnoreCase("2")){
			displayOut.setText("<HTML><H1 color=orange>"+msg+"</H1></HTML>");

		}
		else{
			displayOut.setText("<HTML><H1 color=purple>"+msg+"</H1></HTML>");
		}
	}
	public void playAnotherGame(){
		int yesNo = JOptionPane.showConfirmDialog(null, "Do you want to play another game?");
		if(yesNo == 0){
			clearBoard();
			updateOut(currPlayer+" goes first!");
		}
		else{
			updateOut("Thanks for playing");
			JOptionPane.showMessageDialog(null, "GOODBYE  ( O _ O )");
			System.exit(EXIT_ON_CLOSE);
		}
		System.out.println(yesNo);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btnClicked = (JButton) e.getSource();
		btnClicked.setText(currPlayer);
		if(currPlayer.equalsIgnoreCase("1")){
			btnClicked.setBackground(Color.cyan);
		}
		else{
			btnClicked.setBackground(Color.orange);
		}
		btnClicked.setEnabled(false);
		if(isWinner(currPlayer) || isFull()){
			displayWinner();
			playAnotherGame();
		}
		updateCurrPlayer();
		updateOut(currPlayer + " goes");

	}
	@Override
	public void displayBoard() {
		board = new JButton[6][7];
		for(int row=0; row<board.length; row++){
			for(int col=0; col<board[row].length; col++){
				board[row][col] = new JButton();
				board[row][col].setFont(new Font(Font.SANS_SERIF,Font.BOLD,60));
				board[row][col].setBorder(new LineBorder(Color.MAGENTA));
				board[row][col].addActionListener(this);
				board[row][col].setEnabled(true);
				jpBoard.add(board[row][col]);
			}
		}
	}
	@Override
	public void clearBoard() {
		for(int row=0; row<board.length; row++){
			for(int col=0; col<board[row].length; col++){
				board[row][col].setText("");
				board[row][col].setBackground(null);
				board[row][col].setEnabled(true);
			}
		}

	}
	@Override
	public void displayWinner() {
		if(this.isWinner("1")){
			updateOut("Player 1 is the winner");
		}
		else if(this.isWinner("2")){
			updateOut("Player 2 is the winner");
		}
		else{
			updateOut("DRAW!!!");
		}
	}

	@Override
	public void updateCurrPlayer() {
		if(currPlayer.equalsIgnoreCase("1")){
			currPlayer = "2";
		}
		else{
			currPlayer = "1";
		}

	}
	
	public boolean isWinner(String player) {
		//check horizontally
		for(int row=0; row<board.length; row++){
			int rowCount=0;//row match counter, resets on next row
			for(int col=0; col<board[row].length; col++){
				if(! (board[row][col].getText().contains(player))){
					//reset counter
					rowCount = 0;
				}
				else if(board[row][col].getText().contains(player)){
					rowCount++;//increment counter
					if(rowCount == 4){
						return true;//found 4 in same row
					}
				}
			}
		}
		//check vertically
		for(int col=0; col < (board.length + 1); col++){
			int colCount=0;
			for(int row = 0; row < (board[0].length- 1); row++){
				if(! (board[row][col].getText().contains(player))){
					colCount = 0;
				}
				else if(board[row][col].getText().contains(player)){
					colCount++;
					if(colCount == 4){
						return true;//found 4 in same column
					}
				}
			}
		}   
		
		//check main diagonal [0][0],[1][1],[2][2]
		int diagCount=0;
		for(int i=0; i<board.length; i++){
			if(! (board[i][i].getText().contains(player))){
				//reset counter
				diagCount = 0;
			}
			else if(board[i][i].getText().contains(player)){
				diagCount++;
				if(diagCount == 4){
					return true;//found 4 same across main diagonal
				}
			}	
		}
		//check main diagonal [0][1] [1][2] [2][3] [3][4] [4][5] [5][6]
		int diagCount1=0;
		int row = 0;
		int col = 1;
		while( (row <= 5) && (col <= 6)){
			if(! (board[row][col].getText().contains(player))){
				diagCount1 = 0;
			}
			else if(board[row][col].getText().contains(player)){
				diagCount1++;
				if(diagCount1 == 4){
					return true;
				}
			}
			row++;
			col++;
		}

		//check main diagonal [0][2] [1][3] [2][4] [3][5] [4][6] 
		int diagCount2 = 0;
		int row2 = 0;
		int col2 = 2;
		while( (row2 <= 4) && (col2 <= 6)){
			if(! (board[row2][col2].getText().contains(player))){
				diagCount2 = 0;
			}
			else if(board[row2][col2].getText().contains(player)){
				diagCount2++;
				if(diagCount2 == 4){
					return true;
				}
			}
			row2++;
			col2++;
		}

		//check main diagonal [0][3] [1][4] [2][5] [3][6]
		int diagCount3 = 0;
		int row3 = 0;
		int col3 = 3;
		while( (row3 <= 3) && (col3 <= 6)){
			if(! (board[row3][col3].getText().contains(player))){
				diagCount3 = 0;
			}
			else if(board[row3][col3].getText().contains(player)){
				diagCount3++;
				if(diagCount3 == 4){
					return true;
				}
			}
			row3++;
			col3++;
		}

		//check main diagonal [1][0] [2][1] [3][2] [4][3] [5][4]
		int diagCount4 = 0;
		int row4 = 1;
		int col4 = 0;
		while( (row4 <= 5) && (col4 <= 4)){
			if(! (board[row4][col4].getText().contains(player))){
				diagCount4 = 0;
			}
			else if(board[row4][col4].getText().contains(player)){
				diagCount4++;
				if(diagCount4 == 4){
					return true;
				}
			}
			row4++;
			col4++;
		}

		//check main diagonal [2][0] [3][1] [4][2] [5][3]
		int diagCount5 = 0;
		int row5 = 2;
		int col5 = 0;
		while( (row5 <= 5) && (col5 <= 3)){
			if(! (board[row5][col5].getText().contains(player))){
				diagCount5 = 0;
			}
			else if(board[row5][col5].getText().contains(player)){
				diagCount5++;
				if(diagCount5 == 4){
					return true;
				}
			}
			row5++;
			col5++;
		}
		
		//check secondary diagonal [5][0] [4][1] [3][2] [2][3] [1][4] [0][5]
		int diag2Count1 = 0;
		int otherRow1 = 5;
		int otherCol1 = 0;
		while( (otherRow1 >= 0) && (otherCol1 <= 5)){
			if(! (board[otherRow1][otherCol1].getText().contains(player))){
				diag2Count1 = 0;
			}
			else if(board[otherRow1][otherCol1].getText().contains(player)){
				diag2Count1++;
				if(diag2Count1 == 4){
					return true;//found 4 same across secondary diagonal
				}
			}
			otherRow1--;
			otherCol1++;
		}

		//check downward diagonal [5][1] [4][2] [3][3] [2][4] [1][5] [0][6]
		int diag2Count2 = 0;
		int otherRow2 = 5;
		int otherCol2 = 1;
		while( (otherRow2 >= 0) && (otherCol2 <= 6)){
			if(! (board[otherRow2][otherCol2].getText().contains(player))){
				diag2Count2 = 0;
			}
			else if(board[otherRow2][otherCol2].getText().contains(player)){
				diag2Count2++;
				if(diag2Count2 == 4){
					return true;//found 4 same across secondary diagonal
				}
			}
			otherRow2--;
			otherCol2++;
		}


		//check upward diagonal [5][2] [4][3] [3][4] [2][5] [1][6]
		int diag2Count3 = 0;
		int otherRow3 = 5;
		int otherCol3 = 2;
		while( (otherRow3 >= 1) && (otherCol3 <= 6)){
			if(! (board[otherRow3][otherCol3].getText().contains(player))){
				diag2Count3 = 0;
			}
			else if(board[otherRow3][otherCol3].getText().contains(player)){
				diag2Count3++;
				if(diag2Count3 == 4){
					return true;//found 4 same across secondary diagonal
				}
			}
			otherRow3--;
			otherCol3++;
		}


		//check secondary diagonal [5][3] [4][4] [3][5] [2][6]
		int diag2Count4 = 0;
		int otherRow4 = 5;
		int otherCol4 = 3;
		while( (otherRow4 >= 2) && (otherCol4 <= 6)){
			if(! (board[otherRow4][otherCol4].getText().contains(player))){
				diag2Count4 = 0;
			}
			else if(board[otherRow4][otherCol4].getText().contains(player)){
				diag2Count4++;
				if(diag2Count4 == 4){
					return true;//found 4 same across secondary diagonal
				}
			}
			otherRow4--;
			otherCol4++;
		}

		//check secondary diagonal [4][0] [3][1] [2][2] [1][3]
		int diag2Count5 = 0;
		int otherRow5 = 4;
		int otherCol5 = 0;
		while( (otherRow5 >= 1) && (otherCol5 <= 3)){
			if(! (board[otherRow5][otherCol5].getText().contains(player))){
				diag2Count4 = 0;
			}
			else if(board[otherRow5][otherCol5].getText().contains(player)){
				diag2Count5++;
				if(diag2Count5 == 4){
					return true;//found 4 same across secondary diagonal
				}
			}
			otherRow5--;
			otherCol5++;
		}

		//check secondary diagonal [3][0] [2][1] [1][2] [0][3]
		int diag2Count6 = 0;
		int otherRow6 = 3;
		int otherCol6 = 0;
		while( (otherRow6 >= 0) && (otherCol6 <= 3)){
			if(! (board[otherRow6][otherCol6].getText().contains(player))){
				diag2Count6 = 0;
			}
			else if(board[otherRow6][otherCol6].getText().contains(player)){
				diag2Count6++;
				if(diag2Count6 == 4){
					return true;//found 4 same across secondary diagonal
				}
			}
			otherRow6--;
			otherCol6++;
		}

		return false;
	}
	
	@Override
	public boolean isFull() {
		for(int row=0; row<board.length; row++){
			for(int col=0; col<board[row].length; col++){
				String text = board[row][col].getText();
				if( !(text.contains("1")) && !(text.contains("2")) ){
					return false;
				}
			}
		}
		return true;
	}

}
