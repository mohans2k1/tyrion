package dev.msundaram.tyrion.processors;

import dev.msundaram.tyrion.Board;
import dev.msundaram.tyrion.Color;
import dev.msundaram.tyrion.pieces.Piece;

import java.util.ArrayList;

import static dev.msundaram.tyrion.Board.*;

public class MoveGenerator implements Processor {
    private final Board board;

    public MoveGenerator(Board board) {
        this.board = board;
    }

    @Override
    public void process() {
        ArrayList<Move> moves = new ArrayList<>();
        Piece pawn = board.getTurn() == Color.WHITE ? board.getPiece(WHITE_PAWNS) : board.getPiece(BLACK_PAWNS);
        Piece knight = board.getTurn() == Color.WHITE ? board.getPiece(WHITE_KNIGHTS) : board.getPiece(BLACK_KNIGHTS);
        Piece king = board.getTurn() == Color.WHITE ? board.getPiece(WHITE_KINGS) : board.getPiece(BLACK_KINGS);
//        moves.addAll(pawn.generateMoves(board));
//        moves.addAll(knight.generateMoves(board));
        moves.addAll(king.generateMoves(board));

        for (Move move : moves) {
            System.out.println(move.toString());
        }
    }
}
