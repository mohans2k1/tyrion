package dev.msundaram.tyrion.processors;

import dev.msundaram.tyrion.Board;
import dev.msundaram.tyrion.Color;
import dev.msundaram.tyrion.Direction;
import dev.msundaram.tyrion.pieces.Piece;
import dev.msundaram.tyrion.pieces.PieceType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static dev.msundaram.tyrion.Constants.RANK_3;
import static dev.msundaram.tyrion.Constants.RANK_7;

public class MoveGenerator implements Processor {
    private final Board origin;

    public MoveGenerator(Board origin) {
        this.origin = origin;
    }

    @Override
    public void process() {
        ArrayList<Move> moves = generateMoves(origin);
    }

    private ArrayList<Move> generateMoves(Board origin) {
        ArrayList<Move> moves = new ArrayList<>();
        moves.addAll(generatePawnMoves(origin));
        return moves;
    }

    private Collection<? extends Move> generatePawnMoves(Board origin) {
        ArrayList<Move> moves = new ArrayList<>();
        moves.addAll(generateSinglePawnPushes(origin));
        moves.addAll(generateDoublePawnPushes(origin));
        moves.addAll(generatePawnCaptures(origin));
        return moves;
    }

    private List<Move> generatePawnCaptures(Board origin) {
        return null;
    }

    private List<Move> generateSinglePawnPushes(Board origin) {
        Piece piece = origin.getTurn() == Color.WHITE ? origin.getPiece(PieceType.WHITE_PAWNS) : origin.getPiece(PieceType.BLACK_PAWNS);
        if (piece.getBitboard() == 0) return null;
        long ourPieces = piece.getBitboard() & RANK_7;
        long singlePushes = (ourPieces << Direction.N.i) & ~origin.getAllPieces();
        long secondPushes = ((singlePushes & RANK_3) << Direction.N.i) & ~origin.getAllPieces();

        return null;
    }

}
