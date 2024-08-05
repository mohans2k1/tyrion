package dev.msundaram.tyrion.pieces;

import dev.msundaram.tyrion.Board;
import dev.msundaram.tyrion.Color;
import dev.msundaram.tyrion.Direction;
import dev.msundaram.tyrion.processors.Move;
import dev.msundaram.tyrion.processors.MoveType;
import dev.msundaram.tyrion.processors.Square;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece {
    protected Color color;
    protected long bitboard;

    public Piece(Color color) {
        this.color = color;
    }

    public Piece(Color color, long bitboard) {
        this.bitboard = bitboard;
        this.color = color;
    }

    public static long shift(long bitboard, Direction direction) {
        return shift(bitboard, direction.i);
    }

    public static long shift(long bitboard, int offset) {
        if (offset < 0) {
            return bitboard >>> (-offset);
        }
        return bitboard << offset;
    }

    public abstract Color getColor();

    public abstract long getBitboard();

    public abstract List<Move> generateMoves(Board origin);

    public long shift(Direction direction) {
        return shift(getBitboard(), direction);
    }

    public List<Move> extractMoves(long targets, int offset, MoveType moveType) {
        ArrayList<Move> moves = new ArrayList<>();
        while (targets != 0) {
            int square = Long.numberOfTrailingZeros(targets);
            targets ^= 1L << square;
            moves.add(new Move(Square.values()[square - offset], Square.values()[square], moveType, null));
        }
        return moves;
    }

    public List<Move> extractMoves(Square from, long targets, MoveType moveType) {
        ArrayList<Move> moves = new ArrayList<>();
        while (targets != 0) {
            int square = Long.numberOfTrailingZeros(targets);
            targets ^= 1L << square;
            moves.add(new Move(from, Square.values()[square], moveType, null));
        }
        return moves;
    }

    public List<Square> getOccupiedSquares(long bitboard) {
        ArrayList<Square> squares = new ArrayList<>();
        while (bitboard != 0) {
            int square = Long.numberOfTrailingZeros(bitboard);
            bitboard ^= 1L << square;
            squares.add(Square.values()[square]);
        }
        return squares;
    }
}
