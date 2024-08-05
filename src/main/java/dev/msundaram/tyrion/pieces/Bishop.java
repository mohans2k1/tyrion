package dev.msundaram.tyrion.pieces;

import dev.msundaram.tyrion.Board;
import dev.msundaram.tyrion.Color;
import dev.msundaram.tyrion.processors.Move;

import java.util.List;

public class Bishop extends Slider implements Cloneable {

    public Bishop(Color color) {
        super(color);
        if (color == Color.BLACK) {
            bitboard = 0b00100100_00000000_00000000_00000000_00000000_00000000_00000000_00000000L;
        } else {
            bitboard = 0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_00100100L;
        }
    }

    public Bishop(Color color, long bitboard) {
        super(color, bitboard);
    }

    @Override
    public Color getColor() {
        return color;
    }

    public long getBitboard() {
        return bitboard;
    }

    @Override
    public Bishop clone() {
        try {
            return (Bishop) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public List<Move> generateMoves(Board origin) {
        return null;
    }
}
