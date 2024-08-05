package dev.msundaram.tyrion.pieces;

import dev.msundaram.tyrion.Board;
import dev.msundaram.tyrion.Color;
import dev.msundaram.tyrion.Direction;
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

public class King extends Piece implements Cloneable {
    private static final HashMap<Integer, Long> kingMoves = new HashMap<>();

    public King(Color color) {
        super(color);
        if (color == Color.BLACK) {
            bitboard = 0b00001000_00000000_00000000_00000000_00000000_00000000_00000000_00000000L;
        } else {
            bitboard = 0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_00001000L;
        }
    }

    public King(Color color, long bitboard) {
        super(color, bitboard);
    }

    public static void computeMoves() {
        for (int i = 0; i < 64; i++) {
            long position = 1L << i;
            kingMoves.put(i, generatePossibleMovesFrom(position));
        }
    }

    private static Long generatePossibleMovesFrom(long position) {
        List<Direction> validDirections = new ArrayList<>(Arrays.asList(Direction.values()));
        if ((position & FILE_A) != 0) {
            validDirections.removeAll(List.of(Direction.NORTHWEST, Direction.SOUTHWEST, Direction.WEST));
        }

        if ((position & FILE_H) != 0) {
            validDirections.removeAll(List.of(Direction.EAST, Direction.NORTHEAST, Direction.SOUTHEAST));
        }

        if ((position & RANK_8) != 0) {
            validDirections.removeAll(List.of(Direction.NORTH, Direction.NORTHEAST, Direction.NORTHWEST));
        }

        if ((position & RANK_1) != 0) {
            validDirections.removeAll(List.of(Direction.SOUTH, Direction.SOUTHEAST, Direction.SOUTHWEST));
        }
        return validDirections
                .stream()
                .map(direction -> shift(position, direction.i))
                .reduce(0L, (targets, target) -> targets | target);
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
    public King clone() {
        try {
            return (King) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public List<Move> generateMoves(Board board) {
        Square square = getOccupiedSquares(bitboard).get(0);
        List<Move> moves = new ArrayList<>();
        long destinations = getPossibleMovesFrom(square);
        long enemyKing = board.getPiece(color == Color.WHITE ? BLACK_KINGS : WHITE_KINGS).getBitboard();
        long valid_pieces = board.getEnemyPieces() & ~enemyKing;
        long empty_squares = ~board.getAllPieces();

        moves.addAll(extractMoves(square, (destinations & valid_pieces), MoveType.CAPTURE));
        moves.addAll(extractMoves(square, (destinations & empty_squares), MoveType.QUIET));
        return moves;

    }

    public long getPossibleMovesFrom(Square square) {
        return kingMoves.get(square.i);
    }
}
