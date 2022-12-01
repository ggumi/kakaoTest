package kakao;

import java.util.*;

public class Solution5 {
    public static void main(String[] args) {
        Solution5 solution5 = new Solution5();
//        solution5.solution(new String[] {
//                "UPDATE 1 1 menu",
//                "UPDATE 1 2 category",
//                "UPDATE 2 1 bibimbap",
//                "UPDATE 2 2 korean",
//                "UPDATE 2 3 rice",
//                "UPDATE 3 1 ramyeon",
//                "UPDATE 3 2 korean",
//                "UPDATE 3 3 noodle",
//                "UPDATE 3 4 instant",
//                "UPDATE 4 1 pasta",
//                "UPDATE 4 2 italian",
//                "UPDATE 4 3 noodle",
//                "MERGE 1 2 1 3",
//                "MERGE 1 3 1 4",
//                "UPDATE korean hansik",
//                "UPDATE 1 3 group",
//                "UNMERGE 1 4",
//                "PRINT 1 3",
//                "PRINT 1 4"});
        solution5.solution(new String[] {
                "UPDATE 1 1 a",
                "UPDATE 1 2 b",
                "UPDATE 2 1 c",
                "UPDATE 2 2 d",
                "MERGE 1 1 1 2",
                "MERGE 2 2 2 1",
                "MERGE 2 1 1 1",
                "PRINT 1 1",
                "UNMERGE 2 2",
                "PRINT 1 1"});
    }

    private static class Table {
        private String[][] array = new String[51][51];
        private Map<Position, Range> cellLinkMap = new LinkedHashMap<>();

        public String getValue(Position position) {
            return optNullReplaceEmpty(array[position.row][position.cell]);
        }

        private String optNullReplaceEmpty(String value) {
            if (value == null) {
                return "EMPTY";
            }
            return value;
        }

        private void doMerge(Position from, Position to) {
            Range range = new Range(from, to);
            for (int i = range.from.row; i <= range.to.row; i++) {
                for (int j = range.from.cell; j <= range.to.cell; j++) {
                    Position position = new Position(i, j);
                    if (cellLinkMap.containsKey(position)) {
                        range.merge(cellLinkMap.get(position));
                    }
                    cellLinkMap.put(position, range);
                    doUpdateCell(position, getValue(from));
                }
            }
            System.out.println(cellLinkMap);
        }

        private void doUnMerge(Position target) {
            Range range = cellLinkMap.get(target);
            Position from = range.from;
            Position to = range.to;
            for (int i = from.row; i <= to.row; i++) {
                for (int j = from.cell; j <= to.cell; j++) {
                    Position position = new Position(i, j);
                    if (!position.equals(target)) {
                        doUpdateCell(position, null);
                    }
                    cellLinkMap.remove(position);
                }
            }
            System.out.println(cellLinkMap);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("{\n");
            for (String[] str : array) {
                sb.append("{");
                for (String s : str) {
                    sb.append((s == null ? "" : s) + " , ");
                }
                sb.append("}\n");
            }
            sb.append("}");
            return sb.toString();
        }

        private void doPrint(Position position, List<String> prints) {
            prints.add(getValue(position));
        }

        private void doUpdateCell(Position position, String value) {
            Range range = cellLinkMap.get(position);
            if (range == null) {
                setValue(position, value);
            } else {
                doUpdateRange(range.from, range.to, value);
            }
        }

        private void setValue(Position position, String value) {
            array[position.row][position.cell] = value;
        }

        private void doUpdateRange(Position from, Position to, String value) {
            for (int i = from.row; i <= to.row; i++) {
                for (int j = from.cell; j <= to.cell; j++) {
                    setValue(new Position(i, j), value);
                }
            }
        }

        private void doUpdateReplaceAll(String from, String to) {
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array[i].length; j++) {
                    Position position = new Position(i, j);
                    if (getValue(position).equals(from)) {
                        setValue(position, to);
                    }
                }
            }
        }
    }

    private static class Range {
        private Position from;
        private Position to;

        public Range(Position from, Position to) {
            if (from.isLessThan(to)) {
                this.from = from;
                this.to = to;
            } else {
                this.from = to;
                this.to = from;
            }
        }

        public void merge(Range range) {
            this.from = new Position(Math.min(range.from.row, from.row), Math.min(range.from.cell, from.cell));
            this.to = new Position(Math.max(range.to.row, to.row), Math.max(range.to.cell, to.cell));
        }

        @Override
        public String toString() {
            return "Range {" + from + " , " + to + "}";
        }
    }

    private static class Position {
        private int row;
        private int cell;

        public Position(int row, int cell) {
            this.row = row;
            this.cell = cell;
        }

        public Position(String row, String cell) {
            this.row = getAsNumber(row);
            this.cell = getAsNumber(cell);
        }

        private int getAsNumber(String str) {
            return Integer.parseInt(str);
        }

        @Override
        public String toString() {
            return "{ " + row + " , " + cell + " }";
        }

        public boolean isLessThan(Position position) {
            return row < position.row || cell < position.cell;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;
            Position position = (Position) object;
            return row == position.row && cell == position.cell;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, cell);
        }
    }

    public String[] solution(String[] commands) {
        String[] answer = {};
        List<String> answers = new ArrayList<>();
        Table table = new Table();
        for (int i = 0; i < commands.length; i++) {
            String[] splits = commands[i].split(" ");
            String command = splits[0];
            switch (command) {
                case "UPDATE": {
                    if (splits.length == 4) {
                        Position position = new Position(splits[1], splits[2]);
                        table.doUpdateCell(position, splits[3]);
                    } else {
                        table.doUpdateReplaceAll(splits[1], splits[2]);
                    }
                } break;
                case "PRINT": {
                    Position position = new Position(splits[1], splits[2]);
                    table.doPrint(position, answers);
                } break;
                case "MERGE": {
                    Position fromPosition = new Position(splits[1], splits[2]);
                    Position toPosition = new Position(splits[3], splits[4]);
                    table.doMerge(fromPosition, toPosition);
                } break;
                case "UNMERGE": {
                    Position position = new Position(splits[1], splits[2]);
                    table.doUnMerge(position);
                } break;
            }
        }
        System.out.println(answers);
        return answers.toArray(answer);
    }
}
