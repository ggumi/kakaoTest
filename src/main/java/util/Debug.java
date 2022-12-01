package util;

public final class Debug {
    public static void print(int[] array) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        for (int i = 0; i < array.length; i++) {
            stringBuilder.append(array[i]);
            if (i != array.length - 1) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("}");
        System.out.println("Debug->"+stringBuilder.toString());
    }
}
