package com.risonna.movebynature;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.risonna.movebynature.criteria.Criteria;

@Named
@ViewScoped
public class NatureGameSolverBean implements Serializable {

    private int rowForMaximax;
    private int valueForMaximax;
    private int rowForMaximin;
    private int valueForMaximin;
    private int rows;
    private int cols;
    private Integer[][] matrix;
    private boolean matrixInitialized;
    private boolean resultAvailable = false;
    private String matrixValues;

    public String getMatrixValues() {
        return matrixValues;
    }

    public void setMatrixValues(String matrixValues) {
        this.matrixValues = matrixValues;
    }

    public int getRowForMaximin() {
        return rowForMaximin;
    }

    public void setRowForMaximin(int rowForMaximin) {
        this.rowForMaximin = rowForMaximin;
    }

    public int getValueForMaximin() {
        return valueForMaximin;
    }

    public void setValueForMaximin(int valueForMaximin) {
        this.valueForMaximin = valueForMaximin;
    }

    public boolean isResultAvailable() {
        return resultAvailable;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }
    public int getValueForMaximax() {
        return valueForMaximax;
    }

    public void setValueForMaximax(int valueForMaximax) {
        this.valueForMaximax = valueForMaximax;
    }

    public int getRowForMaximax() {
        return rowForMaximax;
    }

    public void setRowForMaximax(int rowForMaximax) {
        this.rowForMaximax = rowForMaximax;
    }

    public Integer[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(Integer[][] matrix) {
        this.matrix = matrix;
    }


    public boolean isMatrixInitialized() {
        return matrixInitialized;
    }

    public void initMatrix() {
        matrix = new Integer[rows][cols];
        matrixInitialized = true;
    }

    public void analyzeMatrix() {
        if (matrix != null && matrix.length > 0) {
            setMatrixValues();

            //MAXIMAX
            HashMap<Integer, Integer> rowNumAndValuesForMaximax;
            rowNumAndValuesForMaximax = Criteria.findMaximax(matrix);
            for (Map.Entry<Integer, Integer> entry : rowNumAndValuesForMaximax.entrySet()){
                rowForMaximax = entry.getKey();
                valueForMaximax = entry.getValue();
            }

            //MAXIMIN
            HashMap<Integer, Integer> rowNumAndValuesForMaximin;
            rowNumAndValuesForMaximin = Criteria.findMaximin(matrix);
            for (Map.Entry<Integer, Integer> entry : rowNumAndValuesForMaximin.entrySet()){
                rowForMaximin = entry.getKey();
                valueForMaximin = entry.getValue();
            }

            resultAvailable = true;
        }
    }


    private void setMatrixValues() {
        if (matrix != null) {
            StringBuilder sb = new StringBuilder();

            for (Integer[] row : matrix) {
                for (Integer element : row) {
                    if (element != null) {
                        sb.append(element);
                    }
                    sb.append(",");
                }
                sb.deleteCharAt(sb.length() - 1); // Remove the trailing comma
                sb.append(";");
            }

            if (sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1); // Remove the trailing semicolon
            }

            matrixValues = sb.toString();
        }
    }

}