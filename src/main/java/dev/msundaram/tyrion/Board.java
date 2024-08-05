package dev.msundaram.tyrion;

import dev.msundaram.tyrion.pieces.*;
import dev.msundaram.tyrion.processors.Square;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Board {

    public static final int WHITE_PAWNS = 0;
    public static final int WHITE_ROOKS = 1;
    public static final int WHITE_KNIGHTS = 2;
    public static final int WHITE_BISHOPS = 3;
    public static final int WHITE_QUEENS = 4;
    public static final int WHITE_KINGS = 5;
    public static final int BLACK_PAWNS = 6;
    public static final int BLACK_ROOKS = 7;
    public static final int BLACK_KNIGHTS = 8;
    public static final int BLACK_BISHOPS = 9;
    public static final int BLACK_QUEENS = 10;
    public static final int BLACK_KINGS = 11;
    private static final Map<Character, Integer> pieceMap = new HashMap<>();

    static {
        pieceMap.put('P', 0);  // White King
        pieceMap.put('R', 1);  // Black King
        pieceMap.put('N', 2);  // White Pawns
        pieceMap.put('B', 3);  // Black Pawns
        pieceMap.put('Q', 4);  // White Rooks
        pieceMap.put('K', 5);  // Black Rooks
        pieceMap.put('p', 6);  // White Knights
        pieceMap.put('r', 7);  // Black Knights
        pieceMap.put('n', 8);  // White Bishops
        pieceMap.put('b', 9);  // Black Bishops
        pieceMap.put('q', 10); // White Queens
        pieceMap.put('k', 11); // Black Queens
    }

    private final ArrayList<Piece> pieces = new ArrayList<>();
    private final Color turn;
    private final int moveCount;
    private final boolean castle_white_kingside;
    private final boolean castle_white_queenside;
    private final boolean castle_black_kingside;
    private final boolean castle_black_queenside;
    private final Square enPassant;
    private final long allPieces;
    private final long whitePieces;
    private final long blackPieces;
    private final int halfMoveCount;

    public Board(String FEN) {
        String[] parts = FEN.split(" ");
        String position = parts[0];
        String turn = parts[1];
        String castle = parts[2];
        String enPassant = parts[3];
        halfMoveCount = Integer.parseInt(parts[4]);
        moveCount = Integer.parseInt(parts[5]);

        long[] bitboards = new long[12];


        String[] rows = position.split("/");
        int rowIdx = 7;
        for (String row : rows) {
            int colIdx = 0;
            for (char ch : row.toCharArray()) {
                if (Character.isDigit(ch)) {
                    colIdx += Character.getNumericValue(ch);
                } else {
                    int bitboardIdx = pieceMap.get(ch);
                    int square = rowIdx * 8 + colIdx;
                    bitboards[bitboardIdx] |= (1L << square);
                    colIdx++;
                }
            }
            rowIdx--;
        }


        pieces.add(new Pawn(Color.WHITE, bitboards[0]));
        pieces.add(new Rook(Color.WHITE, bitboards[1]));
        pieces.add(new Knight(Color.WHITE, bitboards[2]));
        pieces.add(new Bishop(Color.WHITE, bitboards[3]));
        pieces.add(new Queen(Color.WHITE, bitboards[4]));
        pieces.add(new King(Color.WHITE, bitboards[5]));
        pieces.add(new Pawn(Color.BLACK, bitboards[6]));
        pieces.add(new Rook(Color.BLACK, bitboards[7]));
        pieces.add(new Knight(Color.BLACK, bitboards[8]));
        pieces.add(new Bishop(Color.BLACK, bitboards[9]));
        pieces.add(new Queen(Color.BLACK, bitboards[10]));
        pieces.add(new King(Color.BLACK, bitboards[11]));


        long temp = 0L;
        long temp2 = 0L;
        long temp3 = 0L;
        for (Piece piece : pieces) {
            temp |= piece.getBitboard();
            temp2 |= piece.getColor() == Color.WHITE ? piece.getBitboard() : 0;
            temp3 |= piece.getColor() == Color.BLACK ? piece.getBitboard() : 0;

        }
        allPieces = temp;
        whitePieces = temp2;
        blackPieces = temp3;
        this.turn = turn.equals("w") ? Color.WHITE : Color.BLACK;
        this.enPassant = enPassant.equals("-") ? null : Square.valueOf(enPassant);
        this.castle_white_kingside = castle.contains("K");
        this.castle_white_queenside = castle.contains("Q");
        this.castle_black_kingside = castle.contains("k");
        this.castle_black_queenside = castle.contains("q");
        Knight.computeMoves();
        King.computeMoves();
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

    public Color getTurn() {
        return turn;
    }

    public long getAllPieces() {
        return allPieces;
    }

    public long getWhitePieces() {
        return whitePieces;
    }

    public long getBlackPieces() {
        return blackPieces;
    }

    public Piece getPiece(int pieceType) {
        return pieces.get(pieceType);
    }

    public long getEnemyPieces() {
        return getTurn() == Color.WHITE ? blackPieces : whitePieces;
    }
}
