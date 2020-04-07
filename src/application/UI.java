package application;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

public class UI {

	// https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

	public static void clearScreen() {
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}

	public static ChessPosition readChessPosition(Scanner sc) {

		try {

			String s = sc.nextLine();
			char collumn = s.charAt(0);
			int row = Integer.parseInt(s.substring(1));

			return new ChessPosition(collumn, row);
		} catch (RuntimeException e) {

			throw new InputMismatchException("Erro ao ler posi�ao. insira uma posi�ao entre a1 e h8");
		}

	}

	public static void printMatch(ChessMatch chessMatch, List<ChessPiece> captured) {

		printBoard(chessMatch.getPieces());

		System.out.println();
		printCapturedPieces(captured);
		System.out.println();
		System.out.println("Turno " + chessMatch.getTurn());
		System.out.println("Esperando jogador: " + chessMatch.getCurrentPlayer());
		if(chessMatch.getCheck()) {
			System.out.println("Voce esta em cheque");
		}

	}

	public static void printBoard(ChessPiece[][] pieces) {

		for (int i = 0; i < pieces.length; i++) {

			System.out.print((8 - i) + " ");

			for (int j = 0; j < pieces.length; j++) {

				printPiece(pieces[i][j], false);
			}
			System.out.println();

		}

		System.out.print("  a b c d e f g h");

	}

	public static void printBoard(ChessPiece[][] pieces, boolean possibleMoves[][]) {

		for (int i = 0; i < pieces.length; i++) {

			System.out.print((8 - i) + " ");

			for (int j = 0; j < pieces.length; j++) {

				printPiece(pieces[i][j], possibleMoves[i][j]);
			}
			System.out.println();

		}

		System.out.print("  a b c d e f g h");

	}

	// criando metodo auxiliar para imprimir uma pe�a
	public static void printPiece(ChessPiece piece, boolean background) {

		if (background) {
			System.out.print(ANSI_BLUE_BACKGROUND);
		}

		// corpo a ser apagado
		if (piece == null) {
			System.out.print("-" + ANSI_RESET);
		} else {
			if (piece.getColor() == Color.WHITE) {
				System.out.print(ANSI_WHITE + piece + ANSI_RESET);
			} else {
				System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
			}
		}
		System.out.print(" ");
		// corpo a ser apagado
	}

	private static void printCapturedPieces(List<ChessPiece> captured) {

		List<ChessPiece> capturedWhite = captured.stream().filter(piece -> piece.getColor() == Color.WHITE)
				.collect(Collectors.toList());
		List<ChessPiece> caputeredBlack = captured.stream().filter(piece -> piece.getColor() == Color.BLACK)
				.collect(Collectors.toList());

		System.out.println("Pecas capturadas: ");
		System.out.print("Brancas: ");

		System.out.print(ANSI_WHITE);
		System.out.println(Arrays.toString(capturedWhite.toArray()));
		System.out.print(ANSI_RESET);

		System.out.print("Pretas: ");
		System.out.print(ANSI_YELLOW);
		System.out.println(Arrays.toString(caputeredBlack.toArray()));
		System.out.print(ANSI_RESET);
	}

}
