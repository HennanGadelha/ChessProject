package chess;

import boardgame.Board;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {

	private Board board;
	
	public ChessMatch() {
		
		board = new Board(8,8);
		initialSetup();
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
	
	
	private void placeNewPiece(char col, int row, ChessPiece piece) {
		
		board.placePiece(piece, new ChessPosition(col, row).toPosition());
		
	}
	
	
	private void initialSetup() {
		
		placeNewPiece('b', 6, new Rook(board, Color.WHITE));
		placeNewPiece('e', 8, new King(board, Color.BLACK));
		placeNewPiece('e', 1, new King(board, Color.WHITE));
	}
	
	
}
