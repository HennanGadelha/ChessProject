package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Knight extends ChessPiece {

	public Knight(Board board, Color color) {
		super(board, color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "N";
	}
	
	
	private boolean canMove(Position p) {
		
		
		ChessPiece piece = (ChessPiece)getBoard().piece(p);
		return piece == null || piece.getColor() != getColor();
		
		
	}

	@Override
	public boolean[][] possibleMoves() {

		boolean positionsOnTheBoard[][] = new boolean[getBoard().getRows()][getBoard().getCollumns()];

		Position p = new Position(0, 0);

		p.setValues(position.getRow() - 1, position.getColumn() - 2);
		if (getBoard().positionExists(p) && canMove(p)) {
			positionsOnTheBoard[p.getRow()][p.getColumn()] = true;
		}

		p.setValues(position.getRow() - 2, position.getColumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			positionsOnTheBoard[p.getRow()][p.getColumn()] = true;
		}

		p.setValues(position.getRow() - 2 , position.getColumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			positionsOnTheBoard[p.getRow()][p.getColumn()] = true;
		}

		p.setValues(position.getRow() - 1, position.getColumn() + 2);
		if (getBoard().positionExists(p) && canMove(p)) {
			positionsOnTheBoard[p.getRow()][p.getColumn()] = true;
		}

		p.setValues(position.getRow() + 1, position.getColumn() + 2);
		if (getBoard().positionExists(p) && canMove(p)) {
			positionsOnTheBoard[p.getRow()][p.getColumn()] = true;
		}

		p.setValues(position.getRow() + 2, position.getColumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			positionsOnTheBoard[p.getRow()][p.getColumn()] = true;
		}

		p.setValues(position.getRow() + 2, position.getColumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			positionsOnTheBoard[p.getRow()][p.getColumn()] = true;
		}

		p.setValues(position.getRow() + 1, position.getColumn() - 2);
		if (getBoard().positionExists(p) && canMove(p)) {
			positionsOnTheBoard[p.getRow()][p.getColumn()] = true;
		}

		return positionsOnTheBoard;
	}



}
