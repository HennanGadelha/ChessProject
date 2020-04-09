package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import chess.pieces.Rook;

public class ChessMatch {

	private int turn;
	private Color currentPlayer;
	private Board board;
	private boolean check;
	private boolean checkMate;
	
	
	private List<Piece> piecesOnTheBoard = new ArrayList<>();
	private List<Piece> capturedPieces = new ArrayList<>();

	public int getTurn() {

		return turn;
	}

	public Color getCurrentPlayer() {
		return currentPlayer;
	}
	
	public boolean getCheck() {
		
		return check;
	}
	
	public boolean getcheckMate() {
		
		return checkMate;
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

		if (currentPlayer != ((ChessPiece) board.piece(position)).getColor()) {

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

		ChessPiece movingPiece = (ChessPiece)board.removePiece(source);
		movingPiece.increaseMoveCount();
		
		Piece capturedPiece = board.removePiece(target);

		board.placePiece(movingPiece, target);

		if (capturedPiece != null) {

			piecesOnTheBoard.remove(capturedPiece);
			capturedPieces.add(capturedPiece);

		}

		return capturedPiece;

	}

	// metodo para evitar que o rei entre em cheque

	private void undoMove(Position source, Position target, Piece capturedPiece) {

		ChessPiece piece = (ChessPiece)board.removePiece(target);
		piece.decreaseMoveCount();
		
		board.placePiece(piece, source);

		if (capturedPiece != null) {

			board.placePiece(capturedPiece, target);
			capturedPieces.remove(capturedPiece);
			piecesOnTheBoard.add(capturedPiece);

		}

	}

	public boolean[][] possibleMoves(ChessPosition sourcePosition) {

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
		
		if(testCheck(currentPlayer)) {
			
			undoMove(source, target, capturedPiece);
			
			throw new ChessException("Voce nao pode se colocar em cheque");
			
		}
	
		check = (testCheck(opponent(currentPlayer))) ? true : false;
	
		if (testcheckMate(opponent(currentPlayer))) {

			checkMate = true;

		} else {

			nextTurn();
		}

		return (ChessPiece) capturedPiece;
	}

	public void nextTurn() {

		turn++;
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;

	}

	private Color opponent(Color color) {

		return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;

	}

	private void placeNewPiece(char col, int row, ChessPiece piece) {

		board.placePiece(piece, new ChessPosition(col, row).toPosition());
		piecesOnTheBoard.add(piece);

	}

	private ChessPiece king(Color color) {

		List<Piece> pieces = piecesOnTheBoard.stream().filter(p -> ((ChessPiece)p).getColor() == color)
				.collect(Collectors.toList());

		for (Piece p : pieces) {

			if (p instanceof King) {
				return (ChessPiece) p;
			}

		}

		throw new IllegalStateException("Nao existe o rei da cor " + color);

	}
	
	
	private boolean testCheck(Color color) {

		Position kingPosition = king(color).getChessPosition().toPosition();
		List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(p -> ((ChessPiece) p).getColor() == opponent(color))
				.collect(Collectors.toList());
		
		
		for(Piece p :  opponentPieces ) {
			
			boolean possibleMovesOpponent[][] = p.possibleMoves();
			
			if(possibleMovesOpponent[kingPosition.getRow()][kingPosition.getColumn()]) {
				return true;
			}
			
		}
		
		return false;

	}
	
	private boolean testcheckMate(Color color) {

		if (!testCheck(color)) {
			return false;
		}

		List<Piece> pieces = piecesOnTheBoard.stream().filter(p -> ((ChessPiece) p).getColor() == color)
				.collect(Collectors.toList());

		for (Piece p : pieces) {

			boolean possibleMoves[][] = p.possibleMoves();

			for (int i = 0; i < board.getRows(); i++) {

				for (int j = 0; j < board.getCollumns(); j++) {

					if (possibleMoves[i][j]) {

						Position source = ((ChessPiece) p).getChessPosition().toPosition();
						Position target = new Position(i, j);
						Piece capturedPiece = makeMove(source, target);

						boolean testCheck = testCheck(color);
						undoMove(source, target, capturedPiece);

						if (!testCheck) {

							return false;

						}

					}

				}
			}

		}

		return true;
	}
	
	private void initialSetup() {

        placeNewPiece('a', 1, new Rook(board, Color.WHITE));
        placeNewPiece('d', 5, new Knight(board, Color.WHITE));
        placeNewPiece('g', 1, new Knight(board, Color.WHITE));
        placeNewPiece('c', 1, new Bishop(board, Color.WHITE));
        placeNewPiece('f', 1, new Bishop(board, Color.WHITE));
        placeNewPiece('e', 1, new King(board, Color.WHITE));
        placeNewPiece('h', 1, new Rook(board, Color.WHITE));
        placeNewPiece('a', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('b', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('c', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('d', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('e', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('f', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('g', 2, new Pawn(board, Color.WHITE));
        placeNewPiece('h', 2, new Pawn(board, Color.WHITE));

        placeNewPiece('a', 8, new Rook(board, Color.BLACK));
        placeNewPiece('b', 8, new Knight(board, Color.BLACK));
        placeNewPiece('g', 8, new Knight(board, Color.BLACK));
        placeNewPiece('c', 8, new Bishop(board, Color.BLACK));
        placeNewPiece('f', 8, new Bishop(board, Color.BLACK));
        placeNewPiece('e', 8, new King(board, Color.BLACK));
        placeNewPiece('h', 8, new Rook(board, Color.BLACK));
        placeNewPiece('a', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('b', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('c', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('d', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('e', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('f', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('g', 7, new Pawn(board, Color.BLACK));
        placeNewPiece('h', 7, new Pawn(board, Color.BLACK));

	}

}
