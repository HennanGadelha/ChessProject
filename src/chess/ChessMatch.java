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
	
	
	private void initialSetup() {
		
		board.placePiece(new Rook(board, Color.WHITE), new Position(2,1));
		board.placePiece(new King(board, Color.BLACK), new Position(0,4));
		board.placePiece(new King(board, Color.WHITE), new Position(7,4));
	}
	
	
}
