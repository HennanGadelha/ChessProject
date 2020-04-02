package boardgame;

public class Piece {

	// posi��o n�o � referente ao xadrez, mas sim a matriz. Por isso oculta�ao da visiblidade
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
