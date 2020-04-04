package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece {

	public Rook(Board board, Color color) {
		super(board, color);

	}

	@Override
	public String toString() {

		return "R";
	}

	@Override
	public boolean[][] possibleMoves() {
		// bloco de codigo temporario

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

		return positionsOnTheBoard;

	}

}
