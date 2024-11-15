package com.company;

import java.util.ArrayList;
import java.util.Iterator;


public class MatematicasDiscretas {

    public static double SumaDeX(ArrayList<DataSet> data){
        double sumX=0;

        for (DataSet p : data) {
            sumX += p.getX();
        }
        return sumX;
    }

    public static double SumaDeY(ArrayList<DataSet> data){
        double sumY=0;

        for (DataSet p : data) {
            sumY += p.getY();
        }
        return sumY;
    }

    public static double SumaDeX2(ArrayList<DataSet> data){
        double sumX2=0;

        for (DataSet p : data) {
            sumX2 += p.getX() * p.getX();
        }
        return sumX2;
    }

    public static double SumaDeXY(ArrayList<DataSet> data){
        double sumXY=0;

        for (DataSet p : data) {
            sumXY += p.getX() * p.getY();
        }
        return sumXY;
    }

    public static double SumaDeY2(ArrayList<DataSet> data){
        double sumY2=0;

        for (DataSet p : data) {
            sumY2 += p.getY() * p.getY();
        }
        return sumY2;
    }

    public static double[] SumatoriasQuadratic(ArrayList<DataSet> data){


        double sumX = 0.0D;
        double sumY = 0.0D;
        double sumXY = 0.0D;
        double sumX2 = 0.0D;
        double sumX3 = 0.0D;
        double sumX4 = 0.0D;
        double sumX2Y = 0.0D;

        double X;
        double Y;
        for(Iterator var15 = data.iterator(); var15.hasNext(); sumX2Y += X * X * Y) {
            DataSet p = (DataSet)var15.next();
            X = p.getX();
            Y = p.getY();
            sumX += X;
            sumY += Y;
            sumXY += X * Y;
            sumX2 += X * X;
            sumX3 += X * X * X;
            sumX4 += X * X * X * X;
        }
        return new double[]{sumX, sumY, sumXY, sumX2, sumX3, sumX4, sumX2Y};
    }

    public static double[][] aumentMatrix(double[][] matrixX, double[][] matrixY) {
        int numRows = matrixX.length;
        int numCols = matrixX[0].length;
        double[][] augmentedMatrix = new double[numRows][numCols + 1];

        for(int i = 0; i < numRows; ++i) {
            for(int j = 0; j < numCols; ++j) {
                augmentedMatrix[i][j] = matrixX[i][j];
            }

            augmentedMatrix[i][numCols] = matrixY[i][0];
        }
        return augmentedMatrix;
    }

    public static void gaussJordan(double[][] matrix) {
        int numRows = matrix.length;
        int numCols = matrix[0].length;

        for(int pivot = 0; pivot < numRows; ++pivot) {
            double pivotValue = matrix[pivot][pivot];

            int i;
            for(i = pivot; i < numCols; ++i) {
                matrix[pivot][i] /= pivotValue;
            }

            for(i = 0; i < numRows; ++i) {
                if (i != pivot) {
                    double factor = matrix[i][pivot];

                    for(int j = pivot; j < numCols; ++j) {
                        matrix[i][j] -= factor * matrix[pivot][j];
                    }
                }
            }
        }

    }

    public static double PromedioDeY(ArrayList<DataSet> data){

        double sumY=0;

        for (DataSet p : data) {
            sumY += p.getY();
        }
        return sumY / data.size();
    }

    public static double[] sumatoriasDeX(ArrayList<DataSet> Datos, int grado){
        // Inicializamos las sumas necesarias
        double[] sumX = new double[2 * grado + 1];

        for (DataSet dato : Datos) {
            double x = dato.getX();
            for (int i = 0; i <= 2 * grado; i++) {
                sumX[i] += Math.pow(x, i);
            }
        }
        return sumX;
    }

    public static double[] sumatoriasDeY(ArrayList<DataSet> Datos, int grado){

        // Inicializamos las sumas necesarias
        double[] sumY = new double[grado + 1];

        for (DataSet dato : Datos) {
            double x = dato.getX();
            double y = dato.getY();


            for (int i = 0; i <= grado; i++) {
                sumY[i] += Math.pow(x, i) * y;
            }
        }
        return sumY;
    }



    public static double[][] gaussJordanPolynomial(double[][] A) {
        int n = A.length;

        // Convertir la matriz en la identidad y resolver
        for (int i = 0; i < n; i++) {
            // Hacer que el pivote sea 1
            double pivote = A[i][i];
            for (int j = 0; j < n + 1; j++) {
                A[i][j] /= pivote;
            }

            // Hacer que los demás elementos de la columna sean 0
            for (int k = 0; k < n; k++) {
                if (k != i) {
                    double factor = A[k][i];
                    for (int j = 0; j < n + 1; j++) {
                        A[k][j] -= factor * A[i][j];
                    }
                }
            }
        }
        return A;
    }



}
