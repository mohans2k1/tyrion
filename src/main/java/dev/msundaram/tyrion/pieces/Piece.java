package dev.msundaram.tyrion.pieces;

import dev.msundaram.tyrion.Color;

public interface Piece {
    Color getColor();

    long getBitboard();
}
