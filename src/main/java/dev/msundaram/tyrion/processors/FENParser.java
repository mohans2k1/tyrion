package dev.msundaram.tyrion.processors;


import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FENParser {
    private static final Map<Character, Integer> pieceMap = new HashMap<>();

    static {
        pieceMap.put('K', 0);  // White King
        pieceMap.put('k', 1);  // Black King
        pieceMap.put('P', 2);  // White Pawns
        pieceMap.put('p', 3);  // Black Pawns
        pieceMap.put('R', 4);  // White Rooks
        pieceMap.put('r', 5);  // Black Rooks
        pieceMap.put('N', 6);  // White Knights
        pieceMap.put('n', 7);  // Black Knights
        pieceMap.put('B', 8);  // White Bishops
        pieceMap.put('b', 9);  // Black Bishops
        pieceMap.put('Q', 10); // White Queens
        pieceMap.put('q', 11); // Black Queens
    }

    public static List<Long> parseFEN(String fen) {
        long[] bitboards = new long[12];
        String[] sections = fen.split(" ");
        String position = sections[0];
        String[] rows = position.split("/");

        int rowIdx = 7;
        for (String row : rows) {
            int colIdx = 0;
            for (char ch : row.toCharArray()) {
                if (Character.isDigit(ch)) {
                    colIdx += Character.getNumericValue(ch);
                } else {
                    int bitboardIdx = pieceMap.get(ch);
                    int square = rowIdx * 8 + colIdx;
                    bitboards[bitboardIdx] |= (1L << square);
                    colIdx++;
                }
            }
            rowIdx--;
        }

        List<Long> bitboardList = new ArrayList<>();
        for (long bitboard : bitboards) {
            bitboardList.add(bitboard);
        }
        return bitboardList;
    }

    public static void main(String[] args) {
        String fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
        List<Long> bitboards = parseFEN(fen);

        String[] pieceNames = {
                "White King", "Black King", "White Pawns", "Black Pawns",
                "White Rooks", "Black Rooks", "White Knights", "Black Knights",
                "White Bishops", "Black Bishops", "White Queens", "Black Queens"
        };

        for (int i = 0; i < bitboards.size(); i++) {
            System.out.printf("%s: %s%n", pieceNames[i], StringUtils.leftPad(Long.toBinaryString(bitboards.get(i)), 64, "0"));
        }
    }
}
