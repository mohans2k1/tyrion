package dev.msundaram.tyrion.pieces;

import dev.msundaram.tyrion.Board;
import dev.msundaram.tyrion.Color;
import dev.msundaram.tyrion.processors.Move;

import java.util.List;

public class Rook extends Slider implements Cloneable {
    public Rook(Color color) {
        super(color);
        if (color == Color.BLACK) {
            bitboard = 0b10000001_00000000_00000000_00000000_00000000_00000000_00000000_00000000L;
        } else {
            bitboard = 0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_10000001L;
        }
    }

    public Rook(Color color, long bitboard) {
        super(color, bitboard);
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
    public Rook clone() {
        try {
            return (Rook) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public List<Move> generateMoves(Board origin) {
        return null;
    }
}
