package dev.msundaram.tyrion;


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
public enum KnightDirection {
    NNW(15), NNE(17), NWW(6), NEE(10), SWW(-10), SEE(-6), SSW(-17), SSE(-15);

    public final int i;

    KnightDirection(int i) {
        this.i = i;
    }

}
