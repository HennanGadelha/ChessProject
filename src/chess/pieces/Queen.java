package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Queen extends ChessPiece {

	public Queen(Board board, Color color) {
		super(board, color);

	}

	@Override
	public String toString() {
		return "Q";
	}

	@Override
	public boolean[][] possibleMoves() {

		boolean positionsOnTheBoard[][] = new boolean[getBoard().getRows()][getBoard().getCollumns()];

		Position positionRow = new Position(0, 0);

		// acima
		positionRow.setValues(position.getRow() - 1, position.getColumn());

		while (getBoard().positionExists(positionRow) && !getBoard().thereIsAPiece(positionRow)) {

			positionsOnTheBoard[positionRow.getRow()][positionRow.getColumn()] = true;
			positionRow.setRow(positionRow.getRow() - 1);

		}

		if (getBoard().positionExists(positionRow) && isThereOpponentPiece(positionRow)) {

			positionsOnTheBoard[positionRow.getRow()][positionRow.getColumn()] = true;
		}

		// esquerda

		positionRow.setValues(position.getRow(), position.getColumn() - 1);
		while (getBoard().positionExists(positionRow) && !getBoard().thereIsAPiece(positionRow)) {

			positionsOnTheBoard[positionRow.getRow()][positionRow.getColumn()] = true;
			positionRow.setColumn(positionRow.getColumn() - 1);

		}

		if (getBoard().positionExists(positionRow) && isThereOpponentPiece(positionRow)) {

			positionsOnTheBoard[positionRow.getRow()][positionRow.getColumn()] = true;
		}

		// direita

		positionRow.setValues(position.getRow(), position.getColumn() + 1);
		while (getBoard().positionExists(positionRow) && !getBoard().thereIsAPiece(positionRow)) {

			positionsOnTheBoard[positionRow.getRow()][positionRow.getColumn()] = true;
			positionRow.setColumn(positionRow.getColumn() + 1);

		}

		if (getBoard().positionExists(positionRow) && isThereOpponentPiece(positionRow)) {

			positionsOnTheBoard[positionRow.getRow()][positionRow.getColumn()] = true;
		}

		// baixo

		positionRow.setValues(position.getRow() + 1, position.getColumn());

		while (getBoard().positionExists(positionRow) && !getBoard().thereIsAPiece(positionRow)) {

			positionsOnTheBoard[positionRow.getRow()][positionRow.getColumn()] = true;
			positionRow.setRow(positionRow.getRow() + 1);

		}

		if (getBoard().positionExists(positionRow) && isThereOpponentPiece(positionRow)) {

			positionsOnTheBoard[positionRow.getRow()][positionRow.getColumn()] = true;
		}

		// movimentos nas diagonais

		// diagonal superior esquerda
		positionRow.setValues(position.getRow() - 1, position.getColumn() - 1);

		while (getBoard().positionExists(positionRow) && !getBoard().thereIsAPiece(positionRow)) {

			positionsOnTheBoard[positionRow.getRow()][positionRow.getColumn()] = true;
			positionRow.setValues(positionRow.getRow() - 1, positionRow.getColumn() - 1);
		}

		if (getBoard().positionExists(positionRow) && isThereOpponentPiece(positionRow)) {

			positionsOnTheBoard[positionRow.getRow()][positionRow.getColumn()] = true;
		}

		// diagonal superior direita

		positionRow.setValues(position.getRow() - 1, position.getColumn() + 1);
		while (getBoard().positionExists(positionRow) && !getBoard().thereIsAPiece(positionRow)) {

			positionsOnTheBoard[positionRow.getRow()][positionRow.getColumn()] = true;
			positionRow.setValues(positionRow.getRow() - 1, positionRow.getColumn() + 1);
		}

		if (getBoard().positionExists(positionRow) && isThereOpponentPiece(positionRow)) {

			positionsOnTheBoard[positionRow.getRow()][positionRow.getColumn()] = true;
		}

		// diagonal inferior direita

		positionRow.setValues(position.getRow() + 1, position.getColumn() + 1);
		while (getBoard().positionExists(positionRow) && !getBoard().thereIsAPiece(positionRow)) {

			positionsOnTheBoard[positionRow.getRow()][positionRow.getColumn()] = true;
			positionRow.setValues(positionRow.getRow() + 1, positionRow.getColumn() + 1);

		}

		if (getBoard().positionExists(positionRow) && isThereOpponentPiece(positionRow)) {

			positionsOnTheBoard[positionRow.getRow()][positionRow.getColumn()] = true;
		}

		// diagonal inferior esquerda

		positionRow.setValues(position.getRow() + 1, position.getColumn() - 1);

		while (getBoard().positionExists(positionRow) && !getBoard().thereIsAPiece(positionRow)) {

			positionsOnTheBoard[positionRow.getRow()][positionRow.getColumn()] = true;
			positionRow.setValues(positionRow.getRow() + 1, positionRow.getColumn() - 1);

		}

		if (getBoard().positionExists(positionRow) && isThereOpponentPiece(positionRow)) {

			positionsOnTheBoard[positionRow.getRow()][positionRow.getColumn()] = true;
		}

		return positionsOnTheBoard;

	}

}
