package boardgame;

public class Board {
	
	private int rows;
	private int collumns;
	private Piece[][] pieces;
	
	public Board(int rows, int collumns) {
		
		if(rows < 1  || collumns < 1) {
			
			throw new BoardException("O tabuleiro tem que ter pelo menos uma coluna e uma linha");
		}
		
		
		
		this.rows = rows;
		this.collumns = collumns;
		pieces = new Piece[rows][collumns];
		
	}

	public int getRows() {
		return rows;
	}


	public int getCollumns() {
		return collumns;
	}

	
	public Piece piece(int row, int collumn) {
		
		if(!positionExists(row, collumn)) {
			
			throw new BoardException("Posição inexistente");
		}
		
		return pieces[row][collumn]; 
	}
	
	public Piece piece(Position position) {

		
		if(!positionExists(position)) {
			
			throw new BoardException("Posição inexistente");
		}
		
		return pieces[position.getRow()][position.getColumn()]; 
	}
	
	public void placePiece(Piece piece, Position position) {
		
		if(thereIsAPiece(position)) {
			
			throw new BoardException("Já há uma peça nessa posição");
			
		}
		
		 pieces[position.getRow()][position.getColumn()] = piece;
		 piece.position  = position;
		
	}
	
	
	private boolean positionExists(int row, int col) {
		
		return row >= 0 && row < rows && col >= 0 &&  col < collumns; 
	}
	
	public boolean positionExists(Position position) {
		
		return positionExists(position.getRow(), position.getColumn());
		
	}
	
	public boolean thereIsAPiece(Position position) {
			
		if(!positionExists(position)) {
			
			throw new BoardException("Posição inexistente");
		}
		
		return piece(position) != null;
	}
	
	
	
	

}
