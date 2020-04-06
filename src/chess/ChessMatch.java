package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {

	
	private int turn;
	private Color currentPlayer;
	private Board board;

	
	
	public int getTurn() {
		
		return turn;
	}
	
	
	public Color getCurrentPlayer() {
		return currentPlayer;
	}




	public ChessMatch() {

		board = new Board(8, 8);
		turn = 1;
		currentPlayer = Color.WHITE;
		initialSetup();
	}

	// liberando para o programa apenas a camada de xadrez, realizando downcast
	public ChessPiece[][] getPieces() {

		ChessPiece[][] chessPiece = new ChessPiece[board.getRows()][board.getCollumns()];

		for (int i = 0; i < board.getRows(); i++) {

			for (int j = 0; j < board.getCollumns(); j++) {

				chessPiece[i][j] = (ChessPiece) board.piece(i, j);
			}
		}
		return chessPiece;
	}

	private void validateSourcePosition(Position position) {

		if (!board.thereIsAPiece(position)) {

			throw new ChessException("Nenhuma peça na posiçao de origem");

		}
		
		if(currentPlayer != ((ChessPiece)board.piece(position)).getColor()) {
			
			throw new ChessException("A peca escolhida nao e sua");
			
		}

		if (!board.piece(position).isThereAnyPossibleMove()) {

			throw new ChessException("Nao e um movimendo possivel para esta peca");

		}

	}

	public void validateTargetPosition(Position source, Position target) {

		if (!board.piece(source).possibleMove(target)) {

			throw new ChessException("A peca escolhida nao pode se mover para posicao de destino");
		}

	}

	private Piece makeMove(Position source, Position target) {

		Piece movingPiece = board.removePiece(source);
		Piece capturedPiece = board.removePiece(target);

		board.placePiece(movingPiece, target);

		return capturedPiece;

	}

	
	public boolean[][] possibleMoves(ChessPosition sourcePosition){
		
		Position position = sourcePosition.toPosition();
		validateSourcePosition(position);
		
		return board.piece(position).possibleMoves();
		
	}
	
	
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {

		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();

		validateSourcePosition(source);

		validateTargetPosition(source, target);

		Piece capturedPiece = makeMove(source, target);
		
		nextTurn();

		return (ChessPiece) capturedPiece;
	}

	
	
	public void nextTurn() {
		
		turn++;
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
		
	}
	
	
	private void placeNewPiece(char col, int row, ChessPiece piece) {

		board.placePiece(piece, new ChessPosition(col, row).toPosition());

	}

	private void initialSetup() {

		placeNewPiece('c', 1, new Rook(board, Color.WHITE));
		placeNewPiece('c', 2, new Rook(board, Color.WHITE));
		placeNewPiece('d', 2, new Rook(board, Color.WHITE));
		placeNewPiece('e', 2, new Rook(board, Color.WHITE));
		placeNewPiece('e', 1, new Rook(board, Color.WHITE));
		placeNewPiece('d', 1, new King(board, Color.WHITE));

		placeNewPiece('c', 7, new Rook(board, Color.BLACK));
		placeNewPiece('c', 8, new Rook(board, Color.BLACK));
		placeNewPiece('d', 7, new Rook(board, Color.BLACK));
		placeNewPiece('e', 7, new Rook(board, Color.BLACK));
		placeNewPiece('e', 8, new Rook(board, Color.BLACK));
		placeNewPiece('d', 8, new King(board, Color.BLACK));

	}

}
