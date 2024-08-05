package dev.msundaram.tyrion.pieces;

import dev.msundaram.tyrion.Board;
import dev.msundaram.tyrion.Color;
import dev.msundaram.tyrion.processors.Move;

import java.util.List;

public class Queen extends Slider implements Cloneable {

    public Queen(Color color) {
        super(color);
        if (color == Color.BLACK) {
            bitboard = 0b00010000_00000000_00000000_00000000_00000000_00000000_00000000_00000000L;
        } else {
            bitboard = 0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_00010000L;
        }
    }

    public Queen(Color color, long bitboard) {
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
    public Queen clone() {
        try {
            return (Queen) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public List<Move> generateMoves(Board origin) {
        return null;
    }
}
