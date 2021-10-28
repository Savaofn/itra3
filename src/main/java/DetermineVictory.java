public class DetermineVictory {
    private final int[][] winLoseDrawLogicMatrix;
    private final String[] moves;

    public DetermineVictory(String[] moves) {
        this.moves = moves;
        this.winLoseDrawLogicMatrix = generateWinLoseDrawLogicMatrix(moves);
    }

    public void checkWhoWin(int playerMove, int pcMove) {
        if (this.winLoseDrawLogicMatrix[playerMove - 1][pcMove - 1] == 1) {
            System.out.println("You WIN!!!!!!!!!");
        } else if (this.winLoseDrawLogicMatrix[playerMove - 1][pcMove - 1] == -1) {
            System.out.println("You lose:(");
        } else {
            System.out.println("Draw");
        }
    }

    public static int[][] generateWinLoseDrawLogicMatrix(String[] moves) {
        int length = moves.length;
        int[][] winLoseDrawLogicMatrix = new int[length][length];
        int numberOfMoveTo = (length - 1) / 2;
        for (int j = 0; j < length; j++) {
            int k = numberOfMoveTo;
            for (int i = j + 1; i < length; i++) {
                if (k != 0) {
                    winLoseDrawLogicMatrix[j][i] = 1;
                    k--;
                } else {
                    winLoseDrawLogicMatrix[j][i] = -1;
                }
            }
            if (j != 0) {
                for (int i = 0; i < j; i++) {
                    if (k != 0) {
                        winLoseDrawLogicMatrix[j][i] = 1;
                        k--;
                    } else {
                        winLoseDrawLogicMatrix[j][i] = -1;
                    }
                }
            }
        }
        return winLoseDrawLogicMatrix;
    }

    public int[][] getWinLoseDrawLogicMatrix() {
        return winLoseDrawLogicMatrix;
    }
}