package dev.msundaram.tyrion;

public enum Color {
    WHITE,
    BLACK;

    @Override
    public String toString() {
        return name().toLowerCase();
    }

    public Color next() {
        return this == WHITE ? BLACK : WHITE;
    }
}
