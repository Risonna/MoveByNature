package com.risonna.movebynature;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.risonna.movebynature.criteria.Criteria;

@Named
@ViewScoped
public class NatureGameSolverBean implements Serializable {

    private int rowForSavage;
    private int valueForSavage;
    private int rowForMaximax;
    private int valueForMaximax;
    private int rowForMaximin;
    private int valueForMaximin;
    private int rows;
    private int cols;
    private float coeffGurvic;
    private Integer[][] matrix;
    private Integer[][] riskMatrix;
    private boolean matrixInitialized;
    private boolean resultAvailable = false;
    private String matrixValues;

    private float valueForGurvic;
    private int rowForGurvic;
    private int rowForLaplace;
    private float valueForLaplace;
    private Float[] probabilitiesArray;
    private int rowForBayes;
    private float valueForBayes;

    public int getRowForSavage() {
        return rowForSavage;
    }

    public void setRowForSavage(int rowForSavage) {
        this.rowForSavage = rowForSavage;
    }

    public int getValueForSavage() {
        return valueForSavage;
    }

    public void setValueForSavage(int valueForSavage) {
        this.valueForSavage = valueForSavage;
    }
    public Integer[][] getRiskMatrix(){
        return riskMatrix;
    }
    public void setRiskMatrix(Integer[][] riskMatrix){
        this.riskMatrix = riskMatrix;
    }
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
        probabilitiesArray = new Float[cols];

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
                System.out.println("row for maximax is " + rowForMaximax + " value for maximax is " + valueForMaximax);
            }

            //MAXIMIN
            HashMap<Integer, Integer> rowNumAndValuesForMaximin;
            rowNumAndValuesForMaximin = Criteria.findMaximin(matrix);
            for (Map.Entry<Integer, Integer> entry : rowNumAndValuesForMaximin.entrySet()){
                rowForMaximin = entry.getKey();
                valueForMaximin = entry.getValue();
            }

            //GET THE RISK MATRIX
            setRiskMatrix(Criteria.convertToRiskMatrix(matrix));

            //SAVAGE ON RISK MATRIX
            HashMap<Integer, Integer> rowNumAndValuesForSavage;
            rowNumAndValuesForSavage = Criteria.findMiniMax(riskMatrix);
            for(Map.Entry<Integer, Integer> entry : rowNumAndValuesForSavage.entrySet()){
                rowForSavage = entry.getKey();
                valueForSavage = entry.getValue();
            }

            //SOLVE GURVIC CRITERION
            HashMap<Integer, Float> rowNumAndValuesForGurvic;
            rowNumAndValuesForGurvic = Criteria.calculateGurvicCriterion(matrix, coeffGurvic);
            for(Map.Entry<Integer, Float> entry : rowNumAndValuesForGurvic.entrySet()){
                rowForGurvic = entry.getKey();
                valueForGurvic = entry.getValue();
            }

            //SOLVE LAPLACE CRITERION
            HashMap<Integer, Float> rowNumAndValuesForLaplace;
            rowNumAndValuesForLaplace = Criteria.calculateLaplaceCritetion(matrix);
            for(Map.Entry<Integer, Float> entry : rowNumAndValuesForLaplace.entrySet()){
                rowForLaplace = entry.getKey();
                valueForLaplace = entry.getValue();
            }

            //SOLVE BAYES CRITERION
            HashMap<Integer, Float> rowNumAndValuesForBayes;
            rowNumAndValuesForBayes = Criteria.calculateBayesCriterion(matrix, probabilitiesArray);
            for(Map.Entry<Integer, Float> entry : rowNumAndValuesForBayes.entrySet()){
                rowForBayes = entry.getKey();
                valueForBayes = entry.getValue();
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

    public float getCoeffGurvic() {
        return coeffGurvic;
    }

    public void setCoeffGurvic(float coeffGurvic) {
        this.coeffGurvic = coeffGurvic;
    }

    public float getValueForGurvic() {
        return valueForGurvic;
    }

    public void setValueForGurvic(float valueForGurvic) {
        this.valueForGurvic = valueForGurvic;
    }

    public int getRowForGurvic() {
        return rowForGurvic;
    }

    public void setRowForGurvic(int rowForGurvic) {
        this.rowForGurvic = rowForGurvic;
    }

    public int getRowForLaplace() {
        return rowForLaplace;
    }

    public void setRowForLaplace(int rowForLaplace) {
        this.rowForLaplace = rowForLaplace;
    }

    public float getValueForLaplace() {
        return valueForLaplace;
    }

    public void setValueForLaplace(float valueForLaplace) {
        this.valueForLaplace = valueForLaplace;
    }


    public Float[] getProbabilitiesArray() {
        return probabilitiesArray;
    }

    public void setProbabilitiesArray(Float[] probabilitiesArray) {
        this.probabilitiesArray = probabilitiesArray;
    }

    public int getRowForBayes() {
        return rowForBayes;
    }

    public void setRowForBayes(int rowForBayes) {
        this.rowForBayes = rowForBayes;
    }

    public float getValueForBayes() {
        return valueForBayes;
    }

    public void setValueForBayes(float valueForBayes) {
        this.valueForBayes = valueForBayes;
    }
}