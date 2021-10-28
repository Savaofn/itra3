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

























     /*
         String[] newMas = Arrays.copyOfRange(args, 0,args.length);
        int[][] f = new int[newMas.length][newMas.length];
        int i;
        int n = 4;
        int t = (newMas.length - 1) / 2;
        newMas[n] = "0";
        int j;
        for (j = 0; j < newMas.length; j++) {
            int k=t;
            for (i = j + 1; i < newMas.length; i++) {
                if (k != 0) {
                    f[j][i] = 1;
                    newMas[i] = "1";
                    k--;
                } else {
                    f[j][i] = -1;
                    newMas[i] = "-1";
                }
            }
            if (j != 0) {
                for (i = 0; i < j; i++) {
                    if (k != 0) {
                        f[j][i] = 1;
                        newMas[i] = "1";
                        k--;
                    } else {
                        f[j][i] = -1;
                        newMas[i] = "-1";
                    }
                }
            }
        }
        System.out.println(Arrays.asList(newMas));
        for (int a = 0; a < newMas.length; a++) {  //идём по строкам
            for (int e = 0; e < newMas.length; e++) {//идём по столбцам
                System.out.print(" " + f[a][e] + " "); //вывод элемента
            }
            System.out.println();//перенос строки ради визуального сохранения табличной формы
        }








       ArrayList<String> m = new ArrayList();
        int i;
        int n = 6;
        m.add("D");
        m.add("E");
        m.add("F");
        m.add("G");
        m.add("A");
        m.add("B");
        m.add("C");
        m.set(n, "0");
        int k = (m.size() - 1) / 2;
        for (i = n + 1; i < m.size(); i++) {
            if (k != 0) {
                m.set(i, "1");
                k--;
            } else {
                m.set(i, "-1");
            }
        }
        if (n != 0) {
            for (i = 0; i < n; i++) {
                if (k != 0) {
                    m.set(i, "1");
                    k--;
                } else {
                    m.set(i, "-1");
                }
            }
        }
        System.out.println(m);
     */