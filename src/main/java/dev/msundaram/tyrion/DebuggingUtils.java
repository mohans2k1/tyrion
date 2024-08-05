package dev.msundaram.tyrion;

import dev.msundaram.tyrion.processors.Square;

import java.util.ArrayList;
import java.util.List;

public class DebuggingUtils {

    public static void drawBitboard(long bitboard) {
//        String bbstring = StringUtils.leftPad(Long.toBinaryString(bitboard), 64, '0');
        StringBuilder sb = new StringBuilder();

        for (int rank = 7; rank >= 0; rank--) {
            for (int file = 0; file < 8; file++) {
                int position = rank * 8 + file;
                long mask = 1L << position;
                if ((bitboard & mask) != 0) {
                    sb.append("1 ");
                } else {
                    sb.append("0 ");
                }
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    public static List<Square> listSquares(long bitboard) {
        ArrayList<Square> squares = new ArrayList<>();
        while (bitboard != 0) {
            int i = Long.numberOfTrailingZeros(bitboard);
            bitboard ^= 1L << i;
            squares.add(Square.values()[i]);
        }
        return squares;
    }

}
