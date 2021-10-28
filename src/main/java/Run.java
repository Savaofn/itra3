import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Run {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {
        String[] moves = args;
        System.out.println(Arrays.asList(moves));
        checkingIsParametersAreCorrect(moves);

        DetermineVictory determineVictory = new DetermineVictory(moves);
        int[][] winLoseDrawlogicMatrix = determineVictory.getWinLoseDrawLogicMatrix();
        PrintGameRegulationsTable printTable = new PrintGameRegulationsTable(winLoseDrawlogicMatrix, moves);

        while (true) {
            PCMoveAttributes pcMoveAttributes = new PCMoveAttributes(moves.length);
            System.out.println("HMAC: " + pcMoveAttributes.getHMAC());
            printMenu(moves);
            Scanner in = new Scanner(System.in);
            System.out.print("Your move: ");
            String playerMove = in.next();
            if (playerMove.equals("0")) {
                break;
            }
            if (playerMove.equals("?")) {
                printTable.printGameRegulationsTable();
                continue;
            }
            try {
                Integer.parseInt(playerMove);
            } catch (NumberFormatException e) {
                continue;
            }
            if (playerMove.length() > 1 || Integer.valueOf(playerMove) > moves.length ||Integer.valueOf(playerMove)<0) {
                continue;
            }

            System.out.println("Your move: " + moves[Integer.valueOf(playerMove) - 1]);
            System.out.println("PC move: " + moves[pcMoveAttributes.getPcMove() - 1]);

            determineVictory.checkWhoWin(Integer.valueOf(playerMove), pcMoveAttributes.getPcMove());
            System.out.println("HMAC key: " + pcMoveAttributes.getKey());
            System.out.println();
        }
    }

    public static void printMenu(String[] moves) {
        int k = 1;
        System.out.println("Available moves:");
        for (String move : moves) {
            System.out.println(k + ". " + move);
            k++;
        }
        System.out.println("0. Exit");
        System.out.println("?. Menu");
    }

    public static void checkingIsParametersAreCorrect(String[] moves) {
        if (moves.length < 3) {
            throw new IllegalArgumentException("The number of possible moves must be >=3");
        }
        if (moves.length % 2 == 0) {
            throw new IllegalArgumentException("The number of possible moves cannot be even");
        }
        List<String> list = Arrays.asList(moves);
        Set<String> set = new HashSet<>(list);
        if (set.size() < list.size()) {
            throw new IllegalArgumentException("There should be no repetitive moves");
        }
    }
}
