public class Main {
    public static String arrayToString(int[] arr) {
        StringBuilder output = new StringBuilder();
        for (int i : arr) {
            output.append(i);
            output.append(' ');
        }
        return output.toString();
    }

    public static void main(String[] args) {
        int[] arr = {15, 5, 24, 8, 1, 3, 16, 10, 20};
        System.out.println("Array before sorting: " + arrayToString(arr));
        divideArray(arr);
        System.out.println("Array after sorting: " + arrayToString(arr));
    }

    private static void mergeArray(int[] arrLeft, int[] arrRight, int[] arr) {
        int i = 0, l = 0, r = 0;
        while (l < arrLeft.length && r < arrRight.length) {
            if (arrLeft[l] > arrRight[r]) {
                arr[i++] = arrRight[r++];
            } else {
                arr[i++] = arrLeft[l++];
            }
        }
        while (l < arrLeft.length) {
            arr[i++] = arrLeft[l++];
        }
        while (r < arrRight.length) {
            arr[i++] = arrRight[r++];
        }
    }

    public static void divideArray(int[] arr) {
        if (arr.length == 1) return;

        int midpoint = arr.length / 2;

        int[] arrLeft = new int[midpoint]; // arrLeft gets the first half of arr
        int[] arrRight = new int[arr.length - midpoint]; // arrRight gets the first half of arr

        // Professor complains about using arraycopy instead of implementing it manually
        // My IDE literally gives me a warning when I try to do it myself, I don't care
        System.arraycopy(arr, 0, arrLeft, 0, arrLeft.length);
        System.arraycopy(arr, midpoint, arrRight, 0, arrRight.length);

        divideArray(arrLeft);
        divideArray(arrRight);
        mergeArray(arrLeft, arrRight, arr);
    }
}