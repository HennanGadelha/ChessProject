package boardgame;

public class Piece {

	// posição não é referente ao xadrez, mas sim a matriz. Por isso ocultaçao da visiblidade
	protected Position position;
	private Board board;
	
	
	public Piece(Board board) {
		
		this.board = board;
		this.position = null;
	}


	//protegendo classe tabuleiro para evitar que classes de outros pacotes a chame
	protected Board getBoard() {
		return board;
	}



	

	
	
}
