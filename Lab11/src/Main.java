public class Main {
    public static void main(String[] args) {
        String[] arr1 = {"War and Peace", "1990", "Brave New World"};
        String[] arr2 = {"Introduction to Programming", "2021", "Java Reference", "2019"};

        System.out.println("First array: " + arrayToString(arr1));
        System.out.println();
        System.out.println("Second array: " + arrayToString(arr2));
        System.out.println();

        String[] combined = concatArray(arr1, arr2);
        System.out.println("Combined array: " + arrayToString(combined));
        System.out.println();

        divideArray(combined);
        System.out.println("Sorted array: " + arrayToString(combined));
    }

    public static String arrayToString(Object[] arr) {
        StringBuilder output = new StringBuilder();
        for (Object i : arr) {
            output.append(i.toString());
            output.append(", ");
        }
        if (!output.isEmpty()) {
            output.setLength(output.length() - 2);
        }
        return output.toString();
    }

    public static String[] concatArray(String[] arr1, String[] arr2) {
        String[] arr = new String[arr1.length + arr2.length];
        int i = 0;
        for (String s : arr1) {
            arr[i] = s;
            i++;
        }
        for (String s : arr2) {
            arr[i] = s;
            i++;
        }

        return arr;
    }

    public static void divideArray(String[] arr) {
        if (arr.length == 1) return;

        int midpoint = arr.length / 2;

        String[] arrLeft = new String[midpoint]; // arrLeft gets the first half of arr
        String[] arrRight = new String[arr.length - midpoint]; // arrRight gets the first half of arr

        // My IDE complains when I do manual array copying, but I shall do it anyway
        for (int i = 0; i < midpoint; i++) {
            arrLeft[i] = arr[i];
        }
        for (int i = midpoint; i < arr.length; i++) {
            arrRight[i - midpoint] = arr[i];
        }

        divideArray(arrLeft);
        divideArray(arrRight);
        mergeArray(arrLeft, arrRight, arr);
    }

    private static void mergeArray(String[] arrLeft, String[] arrRight, String[] arr) {
        int index = 0, leftIndex = 0, rightIndex = 0;
        while (leftIndex < arrLeft.length && rightIndex < arrRight.length) {
            if (arrLeft[leftIndex].compareTo(arrRight[rightIndex]) < 0) { // if left is less than right
                arr[index++] = arrLeft[leftIndex++];
            } else {
                arr[index++] = arrRight[rightIndex++];
            }
        }
        while (leftIndex < arrLeft.length) { // Add the rest of arrLeft if it wasn't already
            arr[index++] = arrLeft[leftIndex++];
        }
        while (rightIndex < arrRight.length) { // Add the rest of arrRight if it wasn't already
            arr[index++] = arrRight[rightIndex++];
        }
    }
}