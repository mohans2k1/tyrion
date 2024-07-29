package dev.msundaram.tyrion.pieces;

import dev.msundaram.tyrion.Color;

public class Pawn implements Piece {
    Color color;
    long bitboard;

    public Pawn(Color color) {
        this.color = color;

        if (color == Color.WHITE) {
            bitboard = 0b00000000_11111111_00000000_00000000_00000000_00000000_00000000_00000000L;
        } else {
            bitboard = 0b00000000_00000000_00000000_00000000_00000000_00000000_11111111_00000000L;
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
