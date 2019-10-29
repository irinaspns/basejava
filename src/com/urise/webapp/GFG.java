package com.urise.webapp;

import java.util.Arrays;

public class GFG {
    public static void main(String[] args) {
        final Integer[] arr = {37, 23, 0, 17, 12, 72, 31,
                46, 100, 88, 54};

        new GFG().sort(arr);

        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + " ");
    }

    public void sort(Integer array[]) {
        for (int i = 1; i < array.length; i++) {
            int x = array[i];

            System.out.println("x = " + x);

            // Find location to insert using binary search
            int j = Math.abs(Arrays.binarySearch(array, 0, i, x) + 1);
            System.out.println("search = " + Arrays.binarySearch(array, 0, i, x));
            System.out.println("j = " + j);

            //Shifting array to one location right
            System.arraycopy(array, j, array, j + 1, i - j);

            //Placing element at its correct location
            array[j] = x;
        }
    }
}