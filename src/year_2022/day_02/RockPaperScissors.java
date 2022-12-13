package year_2022.day_02;

public enum RockPaperScissors {
    ROCK(0 ),
    PAPER(1),
    SCISSORS(2);

    private final int index;
    RockPaperScissors(int index) {
        this.index = index;
    }

    static final RockPaperScissors[] ordering = {ROCK, PAPER, SCISSORS};

    public static final RPSResult[][] payoffTable =  {
            {RPSResult.DRAW, RPSResult.LOSS, RPSResult.WIN},
            {RPSResult.WIN, RPSResult.DRAW, RPSResult.LOSS},
            {RPSResult.LOSS, RPSResult.WIN, RPSResult.DRAW}
    };

    int soloScore() {
        return this.index + 1;
    }

    int scoreVersus(RockPaperScissors other) {
        return RockPaperScissors.payoffTable[index][other.index].score();
    }

    RockPaperScissors toGetResult(RPSResult result) {
        return ordering[(index + result.offset + 3) % 3];
    }
}

enum RPSResult {
    LOSS(0, -1),
    DRAW(1, 0),
    WIN(2, 1);

    private final int index;
    final int offset;

    int score() {
        return 3*index;
    }
    RPSResult(int index, int offset) {
        this.index = index;
        this.offset = offset;
    }
}
