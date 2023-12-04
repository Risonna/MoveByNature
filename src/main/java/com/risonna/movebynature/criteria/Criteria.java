package com.risonna.movebynature.criteria;

import java.util.HashMap;

public class Criteria {


    public static HashMap<Integer, Integer> findMaximax(Integer[][] matrix) {
        HashMap<Integer, Integer> rowNumAndValue = new HashMap<>();
        int maxMax = matrix[0][0];

        for (int i = 0; i < matrix.length; i++) {
            int max = Integer.MIN_VALUE; // Change initialization to Integer.MIN_VALUE
            for (int j = 0; j < matrix[i].length; j++) {
                max = Math.max(max, matrix[i][j]); // Change to Math.max


            }
            if(maxMax < max) {
                maxMax = max;
                rowNumAndValue.put(i, maxMax);
                rowNumAndValue.remove(i-1);
            }
        }

        return rowNumAndValue;
    }

    public static HashMap<Integer, Integer> findMaximin(Integer[][] matrix) {
        HashMap<Integer, Integer> rowNumAndValue = new HashMap<>();
        int maxMin = matrix[0][0];

        for (int i = 0; i < matrix.length; i++) {
            int min = Integer.MAX_VALUE; // Change initialization to Integer.MIN_VALUE
            for (int j = 0; j < matrix[i].length; j++) {
                min = Math.min(min, matrix[i][j]); // Change to Math.max
            }
            if(maxMin < min) {
                maxMin = min;
                rowNumAndValue.put(i, maxMin);
                rowNumAndValue.remove(i-1);
            }
        }

        return rowNumAndValue;
    }
}
