package com.risonna.movebynature.criteria;

import java.util.HashMap;

public class Criteria {


    public static HashMap<Integer, Integer> findMaximax(Integer[][] matrix) {
        HashMap<Integer, Integer> rowNumAndValue = new HashMap<>();
        int maxMax = Integer.MIN_VALUE;

        for (int i = 0; i < matrix.length; i++) {
            int max = Integer.MIN_VALUE; // Change initialization to Integer.MIN_VALUE
            for (int j = 0; j < matrix[i].length; j++) {
                max = Math.max(max, matrix[i][j]); // Change to Math.max

            }
            System.out.println("max in " + i+1 + " row is " + max);
            if(maxMax == Integer.MIN_VALUE){
                maxMax = max;
                rowNumAndValue.put(i, maxMax);
                rowNumAndValue.remove(i-1);
                System.out.println("maxMax was equal to Integer.MIN_VALUE, maxMax is now " + maxMax);
            }
            if(maxMax < max) {
                System.out.println("maxMax was less than max at " + i+1 + " row");
                maxMax = max;
                rowNumAndValue.put(i, maxMax);
                rowNumAndValue.remove(i-1);
                System.out.println("maxMax is " + maxMax);
            }
        }

        return rowNumAndValue;
    }

    public static HashMap<Integer, Integer> findMaximin(Integer[][] matrix) {
        HashMap<Integer, Integer> rowNumAndValue = new HashMap<>();
        int maxMin = Integer.MAX_VALUE;

        for (int i = 0; i < matrix.length; i++) {
            int min = Integer.MAX_VALUE; // Change initialization to Integer.MIN_VALUE
            for (int j = 0; j < matrix[i].length; j++) {
                min = Math.min(min, matrix[i][j]); // Change to Math.max
            }
            if(maxMin == Integer.MAX_VALUE){
                maxMin = min;
                rowNumAndValue.put(i, maxMin);
                rowNumAndValue.remove(i-1);
            }
            if(maxMin <= min) {
                maxMin = min;
                rowNumAndValue.put(i, maxMin);
                rowNumAndValue.remove(i-1);
            }
        }

        return rowNumAndValue;
    }

    public static Integer[][] convertToRiskMatrix(Integer[][] payOffMatrix){
        Integer[][] riskMatrix = new Integer[payOffMatrix.length][payOffMatrix[0].length];

        for (int j = 0; j < payOffMatrix[0].length; j++) {
            int maxPayoff = payOffMatrix[0][j];
            for(int i = 0; i < payOffMatrix.length; i++){
                if(payOffMatrix[i][j] > maxPayoff){
                    maxPayoff = payOffMatrix[i][j];
                }

            }
            for(int i = 0; i < payOffMatrix.length; i++){
                riskMatrix[i][j] = maxPayoff - payOffMatrix[i][j];
            }
        }
        return riskMatrix;
    }

    public static HashMap<Integer, Integer> findMiniMax(Integer[][] matrix){
        HashMap<Integer, Integer> rowNumAndValue = new HashMap<>();

        int minMax = Integer.MAX_VALUE;

        for (int i = 0; i < matrix.length; i++) {
            int max = Integer.MIN_VALUE; // Change initialization to Integer.MIN_VALUE
            for (int j = 0; j < matrix[i].length; j++) {
                max = Math.max(max, matrix[i][j]); // Change to Math.max


            }
            if(minMax == Integer.MAX_VALUE){
                minMax = max;
                rowNumAndValue.put(i, minMax);
                rowNumAndValue.remove(i-1);

            }
            if(minMax >= max) {
                minMax = max;
                rowNumAndValue.put(i, minMax);
                rowNumAndValue.remove(i-1);
            }
        }

        return rowNumAndValue;
    }

    public static HashMap<Integer, Float> calculateGurvicCriterion(Integer[][] matrix, float alpha) {
        HashMap<Integer, Float> rowNumAndValue = new HashMap<>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            throw new IllegalArgumentException("Invalid matrix");
        }

        int numRows = matrix.length;
        int numCols = matrix[0].length;

        float maxGurvicCriterion = Float.MIN_VALUE;

        for (int i = 0; i < numRows; i++) {
            int minInRow = findMinInRow(matrix[i]);
            int maxInRow = findMaxInRow(matrix[i]);

            float gurvicCriterion = alpha * minInRow + (1 - alpha) * maxInRow;
            rowNumAndValue.put(i, gurvicCriterion);
            rowNumAndValue.remove(i-1);


            if (gurvicCriterion > maxGurvicCriterion) {
                maxGurvicCriterion = gurvicCriterion;
                rowNumAndValue.put(i, gurvicCriterion);
                rowNumAndValue.remove(i-1);
            }
        }

        return rowNumAndValue;
    }

    private static int findMinInRow(Integer[] row) {
        int min = Integer.MAX_VALUE;

        for (int element : row) {
            if (element < min) {
                min = element;
            }
        }

        return min;
    }

    private static int findMaxInRow(Integer[] row) {
        int max = Integer.MIN_VALUE;

        for (int element : row) {
            if (element > max) {
                max = element;
            }
        }

        return max;
    }

    public static HashMap<Integer, Float> calculateLaplaceCritetion(Integer[][] matrix){
        HashMap<Integer, Float> rowNumAndValue = new HashMap<>();
        float maxSum = Float.MIN_VALUE;

        for (int i = 0; i < matrix.length; i++) {
            float sum = 0;
            for (int j = 0; j < matrix[i].length; j++) {
                sum += (float) matrix[i][j] /matrix[i].length;

            }
            if(maxSum == Float.MIN_VALUE){
                maxSum = sum;
                rowNumAndValue.put(i, maxSum);
                rowNumAndValue.remove(i-1);
            }
            if(maxSum < sum){
                maxSum = sum;
                rowNumAndValue.put(i, maxSum);
                rowNumAndValue.remove(i-1);
            }
        }
        return rowNumAndValue;
    }

    public static HashMap<Integer, Float> calculateBayesCriterion(Integer[][] matrix, Float[] probabilities){
        HashMap<Integer, Float> rowNumAndValue = new HashMap<>();
        float maxSum = Float.MIN_VALUE;

        for (int i = 0; i < matrix.length; i++) {
            float sum = 0;
            for (int j = 0; j < matrix[i].length; j++) {
                sum += (float) matrix[i][j] * probabilities[j];
            }
            if(maxSum == Float.MIN_VALUE){
                maxSum = sum;
                rowNumAndValue.put(i, maxSum);
                rowNumAndValue.remove(i-1);
            }
            if(maxSum < sum){
                maxSum = sum;
                rowNumAndValue.put(i, maxSum);
                rowNumAndValue.remove(i-1);
            }
        }
        return rowNumAndValue;
    }

}
