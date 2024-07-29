package dev.msundaram.tyrion.pieces;

import dev.msundaram.tyrion.Color;

public class Rook implements Piece {
    Color color;
    long bitboard;

    public Rook(Color color) {
        this.color = color;
        if (color == Color.WHITE) {
            bitboard = 0b10000001_00000000_00000000_00000000_00000000_00000000_00000000_00000000L;
        } else {
            bitboard = 0b00000000_00000000_00000000_00000000_00000000_00000000_00000000_10000001L;
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
