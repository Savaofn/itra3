import de.vandermeer.asciitable.AsciiTable;
import org.apache.commons.lang3.ArrayUtils;
import java.util.ArrayList;
import java.util.List;

public class PrintGameRegulationsTable {
    private final int[][] WinLoseDrawLogicMatrix;
    AsciiTable table = new AsciiTable();
    private final String[] availableMoves;
    private final String rendTable;

    public PrintGameRegulationsTable(int[][] WinLoseDrawLogicMatrix, String[] availableMoves) {
        this.WinLoseDrawLogicMatrix = WinLoseDrawLogicMatrix;
        this.availableMoves = availableMoves;
        this.rendTable = generateTable(WinLoseDrawLogicMatrix);
    }

    public String generateTable(int[][] logicMatrix) {
        table.addRule();
        String[] tableHead = new String[]{"PC/USER"};
        tableHead = ArrayUtils.addAll(tableHead, availableMoves);
        table.addRow(tableHead);
        table.addRule();
        for (int i = 0; i < availableMoves.length; i++) {
            List<String> tableLine = new ArrayList<>();
            tableLine.add(availableMoves[i]);
            for (int j = 0; j < availableMoves.length; j++) {
                tableLine.add(String.valueOf(logicMatrix[i][j]).replace("-1", "LOSE").replace("1", "WIN").replace("0", "DRAW"));
            }
            table.addRow(tableLine);
            table.addRule();
        }
        String rendTable = table.render();
        return rendTable;
    }

    public void printGameRegulationsTable() {
        System.out.println(rendTable);
    }
}
