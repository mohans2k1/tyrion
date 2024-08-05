package dev.msundaram.tyrion.pieces;

import dev.msundaram.tyrion.Board;
import dev.msundaram.tyrion.Color;
import dev.msundaram.tyrion.KnightDirection;
import dev.msundaram.tyrion.processors.Move;
import dev.msundaram.tyrion.processors.MoveType;
import dev.msundaram.tyrion.processors.Square;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static dev.msundaram.tyrion.Board.BLACK_KINGS;
import static dev.msundaram.tyrion.Board.WHITE_KINGS;
import static dev.msundaram.tyrion.Constants.*;

public class Knight extends Piece implements Cloneable {

    private static final HashMap<Integer, Long> knightMoves = new HashMap<>();

    public Knight(Color color) {
        super(color);
        if (color == Color.BLACK) {
            bitboard = 0b01000010_00000000_00000000_00000000_00000000_00000000_00000000_00000000L;
        } else {
            bitboard = 0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_01000010L;
        }
    }

    public Knight(Color color, long bitboard) {
        super(color, bitboard);
    }

    public static void computeMoves() {
        for (int i = 0; i < 64; i++) {
            long position = 1L << i;
            knightMoves.put(i, generatePossibleMovesFrom(position));
        }
    }

    private static Long generatePossibleMovesFrom(long position) {
        List<KnightDirection> validDirections = new ArrayList<>(Arrays.asList(KnightDirection.values()));
        if ((position & FILE_A) != 0) {
            validDirections.removeAll(List.of(KnightDirection.NWW, KnightDirection.NNW, KnightDirection.SSW, KnightDirection.SWW));
        }
        if ((position & FILE_B) != 0) {
            validDirections.removeAll(List.of(KnightDirection.NWW, KnightDirection.SWW));
        }
        if ((position & FILE_H) != 0) {
            validDirections.removeAll(List.of(KnightDirection.NEE, KnightDirection.NNE, KnightDirection.SSE, KnightDirection.SEE));
        }
        if ((position & FILE_G) != 0) {
            validDirections.removeAll(List.of(KnightDirection.NEE, KnightDirection.SEE));
        }
        if ((position & RANK_8) != 0) {
            validDirections.removeAll(List.of(KnightDirection.NEE, KnightDirection.NNE, KnightDirection.NNW, KnightDirection.NWW));
        }
        if ((position & RANK_1) != 0) {
            validDirections.removeAll(List.of(KnightDirection.SEE, KnightDirection.SSW, KnightDirection.SSE, KnightDirection.SWW));
        }

        Long destinations = validDirections
                .stream()
                .map(knightDirection -> shift(position, knightDirection.i))
                .reduce(0L, (targets, target) -> targets | target);
        return destinations;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public long getBitboard() {
        return bitboard;
    }

    @Override
    public Knight clone() {
        try {
            return (Knight) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public List<Move> generateMoves(Board board) {
        List<Square> knightSquares = getOccupiedSquares(bitboard);
        List<Move> moves = new ArrayList<>();
        for (Square square : knightSquares) {
            long destinations = getPossibleMovesFrom(square);
            long enemyKing = board.getPiece(color == Color.WHITE ? BLACK_KINGS : WHITE_KINGS).getBitboard();
            long valid_pieces = board.getEnemyPieces() & ~enemyKing;
            long empty_squares = ~board.getAllPieces();

            moves.addAll(extractMoves(square, (destinations & valid_pieces), MoveType.CAPTURE));
            moves.addAll(extractMoves(square, (destinations & empty_squares), MoveType.QUIET));
        }
        return moves;

    }

    public long getPossibleMovesFrom(Square square) {
        Long l = knightMoves.get(square.i);
        return l;
    }
}
