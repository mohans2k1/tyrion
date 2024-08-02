package dev.msundaram.tyrion.pieces;

import dev.msundaram.tyrion.Color;

public class King implements Piece {
    Color color;
    long bitboard;

    public King(Color color) {
        this.color = color;
        if (color == Color.WHITE) {
            bitboard = 0b00001000_00000000_00000000_00000000_00000000_00000000_00000000_00000000L;
        } else {
            bitboard = 0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_00001000L;
        }
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
}
