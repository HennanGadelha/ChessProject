package chess;

import boardgame.Board;

public class ChessMatch {

	private Board board;
	
	public ChessMatch() {
		
		board = new Board(8,8);
		
		
	}
	
	
	
	
	 //liberando para o programa apenas a camada de xadrez, realizando downcast 
	public ChessPiece[][] getPieces(){
		
		ChessPiece[][] chessPiece = new ChessPiece[board.getRows()][board.getCollumns()];
		
		for(int i=0; i< board.getRows(); i++) {
			
			for(int j =0; j < board.getCollumns(); j++) {
				
				chessPiece[i][j] = (ChessPiece) board.piece(i,j);
			 }
		}
		return chessPiece; 
	}
	
	
	
	
	
	
}
