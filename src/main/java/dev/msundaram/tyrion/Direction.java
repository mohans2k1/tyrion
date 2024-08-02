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


/*
        noNoWe    noNoEa
            +15  +17
             |     |
noWeWe  +6 __|     |__+10  noEaEa
              \   /
               >0<
           __ /   \ __
soWeWe -10   |     |   -6  soEaEa
             |     |
            -17  -15
        soSoWe    soSoEa
 */
public enum Direction {
    N(8), NE(9), E(1), SE(-7), S(-8), SW(-9), W(-1), NW(7);

    public final int i;

    Direction(int i) {
        this.i = i;
    }

}

