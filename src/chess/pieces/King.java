package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

	public King(Board board, Color color) {
		super(board, color);

	}

	@Override
	public String toString() {
		return "K";
	}

	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece) getBoard().piece(position);

		return p == null || p.getColor() != getColor();
	}

	@Override
	public boolean[][] possibleMoves() {
		// bloco de codigo temporario
		boolean[][] positionsOnTheBoard = new boolean[getBoard().getRows()][getBoard().getCollumns()];

		Position p = new Position(0, 0);

		// movendo acima
		p.setValues(position.getRow() - 1, position.getColumn());
		if (getBoard().positionExists(p) && canMove(p)) {
			positionsOnTheBoard[p.getRow()][p.getColumn()] = true;
		}

		// movendo abaixo
		p.setValues(position.getRow() + 1, position.getColumn());
		if (getBoard().positionExists(p) && canMove(p)) {
			positionsOnTheBoard[p.getRow()][p.getColumn()] = true;
		}

		// movendo a esquerda
		p.setValues(position.getRow(), position.getColumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			positionsOnTheBoard[p.getRow()][p.getColumn()] = true;
		}

		// movendo a direita
		p.setValues(position.getRow(), position.getColumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			positionsOnTheBoard[p.getRow()][p.getColumn()] = true;
		}

		// movendo a diagonal superrio esqueda
		p.setValues(position.getRow() - 1, position.getColumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			positionsOnTheBoard[p.getRow()][p.getColumn()] = true;
		}

		// movendo a diagonal superior direita
		p.setValues(position.getRow() - 1, position.getColumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			positionsOnTheBoard[p.getRow()][p.getColumn()] = true;
		}

		// movendo a diagonal inferior esquerda
		p.setValues(position.getRow() + 1, position.getColumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			positionsOnTheBoard[p.getRow()][p.getColumn()] = true;
		}

		// movendo a diagonal inferior direita
		p.setValues(position.getRow() + 1, position.getColumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			positionsOnTheBoard[p.getRow()][p.getColumn()] = true;
		}

		return positionsOnTheBoard;

	}

}
