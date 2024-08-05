package dev.msundaram.tyrion.pieces;

import dev.msundaram.tyrion.Board;
import dev.msundaram.tyrion.Color;
import dev.msundaram.tyrion.Direction;
import dev.msundaram.tyrion.processors.Square;

import java.util.Map;

public abstract class Slider extends Piece {

    public static Map<Square, Map<Direction, Long>> lines;

    public Slider(Color color) {
        super(color);
    }

    public Slider(Color color, long bitboard) {
        super(color, bitboard);
    }

    public static void computeMoves(Board board) {
    }
}
