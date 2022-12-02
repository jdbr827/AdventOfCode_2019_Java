package year_2022.day_2;

public enum RockPaperScissors {
    ROCK(0 ),
    PAPER(1),
    SCISSORS(2);

    private final int index;

    RockPaperScissors(int index) {
        this.index = index;
    }

    public static final int[][] payoffTable =  {
            {3, 0, 6},
            {6, 3, 0},
            {0, 6, 3}
    };

    int soloScore() {
        return this.index + 1;
    }

    int scoreVersus(RockPaperScissors other) {
        return RockPaperScissors.payoffTable[index][other.index];
    }





    //RockPaperScissors Rock = new RockPaperScissors(1);

}
