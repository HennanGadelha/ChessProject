package application;

import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		ChessMatch chessMatch = new ChessMatch();

		while (true) {

			try {
				UI.clearScreen();
				UI.printBoard(chessMatch.getPieces());
				System.out.println();

				System.out.print("posicao de origem: ");
				ChessPosition source = UI.readChessPosition(sc);
				
				
				boolean possibleMoves[][] = chessMatch.possibleMoves(source);
				
				UI.clearScreen();
				UI.printBoard(chessMatch.getPieces(),  possibleMoves);

				System.out.println();

				System.out.print("Destino: ");

				ChessPosition target = UI.readChessPosition(sc);

				ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
			} catch (ChessException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				sc.nextLine();
				e.printStackTrace();
			} catch (InputMismatchException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				sc.nextLine();
				e.printStackTrace();
			}

		}

	}

}
