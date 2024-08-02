package dev.msundaram.tyrion.pieces;

import dev.msundaram.tyrion.Color;

public interface Piece extends Cloneable {
    Color getColor();

    long getBitboard();

    Piece clone();

    default long shift(int i) {
        return getBitboard() << i;
    }


}
