package dev.msundaram.tyrion.pieces;

import dev.msundaram.tyrion.Board;
import dev.msundaram.tyrion.Color;
import dev.msundaram.tyrion.Direction;
import dev.msundaram.tyrion.processors.Move;
import dev.msundaram.tyrion.processors.MoveType;

import java.util.ArrayList;
import java.util.List;

import static dev.msundaram.tyrion.Constants.*;

public class Pawn extends Piece implements Cloneable {

    public Pawn(Color color) {
        super(color);
        if (color == Color.BLACK) {
            bitboard = 0b00000000_11111111_00000000_00000000_00000000_00000000_00000000_00000000L;
        } else {
            bitboard = 0b00000000_00000000_00000000_00000000_00000000_00000000_11111111_00000000L;
        }
    }

    public Pawn(Color color, long bitboard) {
        super(color, bitboard);
    }


    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public long getBitboard() {
        return bitboard;
    }

    @Override
    public Pawn clone() {
        try {
            return (Pawn) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public List<Move> generateMoves(Board origin) {
        if (bitboard == 0) return null;
        long prePromotionRank = color == Color.WHITE ? RANK_7 : RANK_2;
        long doublePawnPushPosition = color == Color.WHITE ? RANK_3 : RANK_6;
        Direction up = color == Color.WHITE ? Direction.NORTH : Direction.SOUTH;
        long enemyKing = origin.getPiece(color == Color.WHITE ? Board.BLACK_KINGS : Board.WHITE_KINGS).getBitboard();


        long pawnsExceptPromotions = bitboard & ~prePromotionRank;
        long emptySquares = ~origin.getAllPieces();
        long singlePushes = shift(pawnsExceptPromotions, up) & emptySquares;
        long secondPushes = shift(singlePushes & doublePawnPushPosition, up) & emptySquares;
//        long enPassant = shift(singlePushes & RANK_4, Direction.NORTH) & origin.getEnPassant();

        long enemyPieces = origin.getEnemyPieces();
        long capturableSquares = enemyPieces & ~enemyKing;
        long westCaptures = shift(pawnsExceptPromotions, up.i + Direction.WEST.i) & capturableSquares;
        long eastCaptures = shift(pawnsExceptPromotions, up.i + Direction.EAST.i) & capturableSquares;


        List<Move> moves = new ArrayList<>();
        moves.addAll(extractMoves(singlePushes, up.i, MoveType.QUIET));
        moves.addAll(extractMoves(secondPushes, up.i + up.i, MoveType.DOUBLE_PAWN_PUSH));
        moves.addAll(extractMoves(westCaptures, up.i + Direction.WEST.i, MoveType.CAPTURE));
        moves.addAll(extractMoves(eastCaptures, up.i + Direction.EAST.i, MoveType.CAPTURE));

        return moves;
    }
}
