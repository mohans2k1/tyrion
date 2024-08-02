package dev.msundaram.tyrion.pieces;

import dev.msundaram.tyrion.Color;

public class Knight implements Piece {
    Color color;
    long bitboard;

    public Knight(Color color) {
        this.color = color;
        if (color == Color.WHITE) {
            bitboard = 0b01000010_00000000_00000000_00000000_00000000_00000000_00000000_00000000L;
        } else {
            bitboard = 0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_01000010L;
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
    public Knight clone() {
        try {
            return (Knight) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
