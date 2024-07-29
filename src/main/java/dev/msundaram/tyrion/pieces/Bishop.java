package dev.msundaram.tyrion.pieces;

import dev.msundaram.tyrion.Color;

public class Bishop implements Piece {
    private final Color color;
    private final long bitboard;

    public Bishop(Color color) {
        this.color = color;
        if (color == Color.WHITE) {
            bitboard = 0b00100100_00000000_00000000_00000000_00000000_00000000_00000000_00000000L;
        } else {
            bitboard = 0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_00100100L;
        }
    }

    @Override
    public Color getColor() {
        return color;
    }

    public long getBitboard() {
        return bitboard;
    }
}
