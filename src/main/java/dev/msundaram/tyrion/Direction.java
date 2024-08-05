package dev.msundaram.tyrion;


/*
   noWe         nort         noEa
          +7    +8    +9
              \  |  /
  west    -1 <-  0 -> +1    east
              /  |  \
          -9    -8    -7
  soWe         sout         soEa
* */
public enum Direction {
    NORTH(8), NORTHEAST(9), EAST(1), SOUTHEAST(-7), SOUTH(-8), SOUTHWEST(-9), WEST(-1), NORTHWEST(7);

    public final int i;

    Direction(int i) {
        this.i = i;
    }

}

