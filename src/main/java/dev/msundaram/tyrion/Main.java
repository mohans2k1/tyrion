package dev.msundaram.tyrion;

import dev.msundaram.tyrion.processors.MoveGenerator;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, I am Tyrion!");
        Board board = new Board("8/8/8/6K1/8/2k5/8/8 b - - 0 1");
        DebuggingUtils.drawBitboard(RayGenerationUtils.getRay(19, Direction.NORTHEAST));
        MoveGenerator moveGenerator = new MoveGenerator(board);
        moveGenerator.process();
        board.print();
    }
}