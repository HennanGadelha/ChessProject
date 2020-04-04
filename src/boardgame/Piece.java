package boardgame;

public abstract class Piece {

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


	public abstract boolean[][] possibleMoves();
	
	//implementando metodo hook para acesso das sublasses
	public boolean possibleMove(Position position) {
		
		return possibleMoves()[position.getRow()][position.getColumn()];
		
	}
	
	public boolean isThereAnyPossibleMove() {

		boolean possiblePositions[][] = possibleMoves();

		for (int i = 0; i < possiblePositions.length; i++) {

			for (int j = 0; j < possiblePositions.length; j++) {

				if (possiblePositions[i][j]) {
					return true;
				}

			}

		}

		return false;

	}
	

	
	
}
