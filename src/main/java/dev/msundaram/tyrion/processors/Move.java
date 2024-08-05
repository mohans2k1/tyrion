package dev.msundaram.tyrion.processors;

import dev.msundaram.tyrion.pieces.PromotedPieceType;

public class Move {
    private final Square from;
    private final Square to;
    private final MoveType moveType;
    private final PromotedPieceType promotedPiece;

    public Move(Square from, Square to, MoveType moveType, PromotedPieceType promotedPiece) {
        this.from = from;
        this.to = to;
        this.moveType = moveType;
        this.promotedPiece = promotedPiece;
    }

    public Square getFrom() {
        return from;
    }

    public Square getTo() {
        return to;
    }

    public MoveType getMoveType() {
        return moveType;
    }

    public PromotedPieceType getPromotedPiece() {
        return promotedPiece;
    }

    public String toString() {
        return from.toString() + " -> " + to.toString() + " " + moveType;
    }
}
