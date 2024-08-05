package dev.msundaram.tyrion.processors;

public enum MoveType {
    QUIET(0),
    DOUBLE_PAWN_PUSH(1),
    KING_CASTLE(2),
    QUEEN_CASTLE(3),
    CAPTURE(4),
    EP_CAPTURE(5),
    KNIGHT_PROMOTION(8),
    BISHOP_PROMOTION(9),
    ROOK_PROMOTION(10),
    QUEEN_PROMOTION(11),
    KNIGHT_PROMO_CAPTURE(12),
    BISHOP_PROMO_CAPTURE(13),
    ROOK_PROMO_CAPTURE(14),
    QUEEN_PROMO_CAPTURE(15);

    public final int i;

    MoveType(int i) {
        this.i = i;
    }
}
