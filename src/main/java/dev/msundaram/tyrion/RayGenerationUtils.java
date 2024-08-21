package dev.msundaram.tyrion;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static dev.msundaram.tyrion.Constants.FILE_A;
import static dev.msundaram.tyrion.Constants.FILE_H;

public class RayGenerationUtils {

    private static final HashMap<Integer, ArrayList<Long>> rays = new HashMap<>();

    /**
     * Generates the north ray for a given position on a chessboard.
     *
     * @param position the bitboard of origin square
     * @return the north ray for the given position
     */
    public static long northRay(long position) {
        long mask = position;
        mask |= mask << 8;
        mask |= mask << 16;
        mask |= mask << 32;
        return mask & ~position;
    }

    /**
     * Generates the south ray for a given position on a chessboard.
     *
     * @param position the bitboard of origin square
     * @return the south ray for the given position
     */
    public static long southRay(long position) {
        long mask = position;
        mask |= mask >>> 8;
        mask |= mask >>> 16;
        mask |= mask >>> 32;
        return mask & ~position;
    }

    /**
     * Generates the east ray for a given position on a chessboard.
     *
     * @param position the bitboard of origin square
     * @return the east ray for the given position
     */
    public static long eastRay(long position) {
        long mask = position;
        mask |= (mask << 1) & ~FILE_A;
        mask |= (mask << 2) & ~(FILE_A | (FILE_A << 1));
        mask |= (mask << 4) & ~(FILE_A | (FILE_A << 1) | (FILE_A << 2) | (FILE_A << 3));
        return mask & ~position;

    }

    /**
     * Generates the west ray for a given position on a chessboard.
     *
     * @param position the bitboard of origin square
     * @return the west ray for the given position
     */
    public static long westRay(long position) {
        long mask = position;
        mask |= (mask >>> 1) & ~FILE_H;
        mask |= (mask >>> 2) & ~(FILE_H | (FILE_H >>> 1));
        mask |= (mask >>> 4) & ~(FILE_H | (FILE_H >>> 1) | (FILE_H >>> 2) | (FILE_H >>> 3));
        return mask & ~position;
    }

    /**
     * Precomputes and stores the north, south, east, and west rays for all squares on a chessboard.
     */
    public static void computeRays() {
        for (int i = 0; i < 64; i++) {
            long position = 1L << i;
            rays.put(i, new ArrayList<>(Arrays.asList(
                    northRay(position),
                    northEastRay(position),
                    eastRay(position),
                    southEastRay(position),
                    southRay(position),
                    southWestRay(position),
                    westRay(position),
                    northWestRay(position))));
        }
    }

    /**
     * Generates the north-east ray for a given position on a chessboard.
     *
     * @param position the bitboard of origin square
     * @return the north-east ray for the given position
     */
    private static long northEastRay(long position) {
        long mask = position;
        mask |= (mask << 9) & ~FILE_A;
        mask |= (mask << 18) & ~(FILE_A | (FILE_A << 9));
        mask |= (mask << 36) & ~(FILE_A | (FILE_A << 9) | (FILE_A << 18));
        return mask & ~position;
    }

    /**
     * Generates the north-west ray for a given position on a chessboard.
     *
     * @param position the bitboard of the origin square
     * @return the north-west ray for the given position
     */
    private static long northWestRay(long position) {
        long mask = position;
        mask |= (mask << 7) & ~FILE_H;
        mask |= (mask << 14) & ~(FILE_H | (FILE_H << 7));
        mask |= (mask << 28) & ~(FILE_H | (FILE_H << 7) | (FILE_H << 14));
        return mask & ~position;
    }

    /**
     * Generates the south-east ray for a given position on a chessboard.
     *
     * @param position the bitboard of the origin square
     * @return the south-east ray for the given position
     */
    private static long southEastRay(long position) {
        long mask = 1L << position;
        mask |= (mask >>> 7) & ~FILE_A;
        mask |= (mask >>> 14) & ~(FILE_A | (FILE_A >>> 7));
        mask |= (mask >>> 28) & ~(FILE_A | (FILE_A >>> 7) | (FILE_A >>> 14));
        return mask & ~position;
    }

    /**
     * Generates the south-west ray for a given position on a chessboard.
     *
     * @param position the bitboard of the origin square
     * @return the south-west ray for the given position
     */
    private static long southWestRay(long position) {
        long mask = 1L << position;
        mask |= (mask >>> 9) & ~FILE_H;
        mask |= (mask >>> 18) & ~(FILE_H | (FILE_H >>> 9));
        mask |= (mask >>> 36) & ~(FILE_H | (FILE_H >>> 9) | (FILE_H >>> 18));
        return mask & ~position;
    }


    /**
     * Retrieves a ray for a given square and direction on a chessboard.
     *
     * @param square    the index of the square
     * @param direction the direction of the ray
     * @return the ray for the given square and direction
     */
    public static Long getRay(int square, Direction direction) {
        return rays.get(square).get(direction.ordinal());
    }
}
