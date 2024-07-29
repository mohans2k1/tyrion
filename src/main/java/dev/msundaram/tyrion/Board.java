package dev.msundaram.tyrion;

import dev.msundaram.tyrion.pieces.*;

import java.util.ArrayList;

public class Board {
    private static final int WHITE_PAWNS = 0;
    private static final int WHITE_ROOKS = 1;
    private static final int WHITE_KNIGHTS = 2;
    private static final int WHITE_BISHOPS = 3;
    private static final int WHITE_QUEENS = 4;
    private static final int WHITE_KINGS = 5;
    private static final int BLACK_PAWNS = 6;
    private static final int BLACK_ROOKS = 7;
    private static final int BLACK_KNIGHTS = 8;
    private static final int BLACK_BISHOPS = 9;
    private static final int BLACK_QUEENS = 10;
    private static final int BLACK_KINGS = 11;

    private final ArrayList<Piece> pieces = new ArrayList<>();
    private final Color turn;
    private final int moveCount;
    private final boolean castle_white_kingside;
    private final boolean castle_white_queenside;
    private final boolean castle_black_kingside;
    private final boolean castle_black_queenside;

    public Board() {
        pieces.add(new Pawn(Color.WHITE));
        pieces.add(new Rook(Color.WHITE));
        pieces.add(new Knight(Color.WHITE));
        pieces.add(new Bishop(Color.WHITE));
        pieces.add(new Queen(Color.WHITE));
        pieces.add(new King(Color.WHITE));
        pieces.add(new Pawn(Color.BLACK));
        pieces.add(new Rook(Color.BLACK));
        pieces.add(new Knight(Color.BLACK));
        pieces.add(new Bishop(Color.BLACK));
        pieces.add(new Queen(Color.BLACK));
        pieces.add(new King(Color.BLACK));

        turn = Color.WHITE;
        moveCount = 0;
        castle_white_kingside = castle_white_queenside = castle_black_kingside = castle_black_queenside = true;
    }


    public void print() {
        // Initialize the board with empty spaces
        char[][] board = new char[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = '.';
            }
        }

        // Define piece characters
        char[] pieceChars = {
                'P', 'R', 'N', 'B', 'Q', 'K',   // White pieces
                'p', 'r', 'n', 'b', 'q', 'k'    // Black pieces
        };

        // Fill the board with pieces
        for (int pieceType = 0; pieceType < 12; pieceType++) {
            long bitboard = pieces.get(pieceType).getBitboard();
            for (int square = 0; square < 64; square++) {
                if ((bitboard & (1L << square)) != 0) {
                    int row = 7 - (square / 8);
                    int col = square % 8;
                    board[row][col] = pieceChars[pieceType];
                }
            }
        }

        // Print the board
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

}
