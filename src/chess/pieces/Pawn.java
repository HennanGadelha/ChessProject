package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

	public Pawn(Board board, Color color) {
		super(board, color);

	}

	@Override
	public String toString() {

		return "P";
	}

	@Override
	public boolean[][] possibleMoves() {

		boolean positionsOnTheBoard[][] = new boolean[getBoard().getRows()][getBoard().getCollumns()];

		Position p = new Position(0, 0);

		if (getColor() == Color.WHITE) {

			p.setValues(position.getRow() - 1, position.getColumn());

			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
				positionsOnTheBoard[p.getRow()][p.getColumn()] = true;

			}

			p.setValues(position.getRow() - 2, position.getColumn());

			Position moveFirstPosition = new Position(position.getRow() - 1, position.getColumn());

			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)
					&& getBoard().positionExists(moveFirstPosition) && !getBoard().thereIsAPiece(moveFirstPosition)
					&& getMoveCount() == 0) {

				positionsOnTheBoard[p.getRow()][p.getColumn()] = true;

			}

			// verficando movimento na diagonal superior esquerda
			p.setValues(position.getRow() - 1, position.getColumn() - 1);

			if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				positionsOnTheBoard[p.getRow()][p.getColumn()] = true;

			}

			// verficando movimento na diagonal superior direita
			p.setValues(position.getRow() - 1, position.getColumn() + 1);

			if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				positionsOnTheBoard[p.getRow()][p.getColumn()] = true;

			}
		} else {

			p.setValues(position.getRow() + 1, position.getColumn());

			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)) {
				positionsOnTheBoard[p.getRow()][p.getColumn()] = true;

			}

			p.setValues(position.getRow() + 2, position.getColumn());

			Position moveFirstPosition = new Position(position.getRow() - 1, position.getColumn());

			if (getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)
					&& getBoard().positionExists(moveFirstPosition) && !getBoard().thereIsAPiece(moveFirstPosition)
					&& getMoveCount() == 0) {

				positionsOnTheBoard[p.getRow()][p.getColumn()] = true;

			}

			// verficando movimento na diagonal superior esquerda
			p.setValues(position.getRow() + 1, position.getColumn() - 1);

			if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				positionsOnTheBoard[p.getRow()][p.getColumn()] = true;

			}

			// verficando movimento na diagonal superior direita
			p.setValues(position.getRow() + 1, position.getColumn() + 1);

			if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
				positionsOnTheBoard[p.getRow()][p.getColumn()] = true;

			}

		}

		return positionsOnTheBoard;
	}

}
