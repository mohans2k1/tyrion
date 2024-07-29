package dev.msundaram.tyrion.pieces;

import dev.msundaram.tyrion.Color;

public class Queen implements Piece {
    Color color;
    long bitboard;

    public Queen(Color color) {
        this.color = color;
        if (color == Color.WHITE) {
            bitboard = 0b00010000_00000000_00000000_00000000_00000000_00000000_00000000_00000000L;
        } else {
            bitboard = 0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_00010000L;
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
}
