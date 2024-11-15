package com.company;
import java.util.ArrayList;

public class PolynomialRegression{

    public static ArrayList<DataSet> Datos = new ArrayList<>();

    public static Modelo AnadirModelo(double[][] coeficientes, ArrayList<DataSet> datos) {
        // Extraer los coeficientes del polinomio desde la matriz de coeficientes
        double[] coefArray = new double[coeficientes.length];
        for (int i = 0; i < coeficientes.length; i++) {
            coefArray[i] = coeficientes[i][0];  // Extrae cada coeficiente desde la matriz
        }

        // Calcular el error (R²), asumiendo que el método de cálculo se ajusta a múltiples coeficientes
        double error = CoeficienteDeError.calcularR2Polynomial(datos, coefArray);
        System.out.println("ERROR CUADRATICO= "+error);
        // Crear y retornar el modelo con todos los coeficientes y el error calculado
        return new Modelo(coefArray, error);
    }



    public static double[][] polynomialRegression(ArrayList<DataSet> Datos, int grado) {


        // Inicializamos las sumas necesarias
        double[] sumX;
        double[] sumY;

        sumX=MatematicasDiscretas.sumatoriasDeX(Datos,grado);
        sumY=MatematicasDiscretas.sumatoriasDeY(Datos,grado);


        // Crear la matriz del sistema de ecuaciones normales
        double[][] A = new double[grado + 1][grado + 2];
        for (int i = 0; i <= grado; i++) {
            for (int j = 0; j <= grado; j++) {
                A[i][j] = sumX[i + j];
            }
            A[i][grado + 1] = sumY[i];
        }
        double[][] MatrizGauss=MatematicasDiscretas.gaussJordanPolynomial(A);
        int n=MatrizGauss.length;
        // Extraer los coeficientes de la matriz aumentada
        double[][] resultado = new double[n][1];
        for (int i = 0; i < n; i++) {
            resultado[i][0] = MatrizGauss[i][n];
        }


        // Resolver usando el método de Gauss-Jordan
        return resultado;
    }



    // Función para imprimir matrices (opcional para depuración)
    public static void imprimirMatriz(double[][] matriz) {
        for (double[] fila : matriz) {
            for (double elemento : fila) {
                System.out.printf("%.6f\t", elemento);
            }
            System.out.println();
        }
    }

    public static void mostrarResultados(double[][] coeficientes) {
        // Mostrar Modelo
        System.out.println("Modelo:");
        for (int i = 0; i < coeficientes.length; i++) {
            System.out.print("B"+i+" = " + coeficientes[i][0]+" ");
        }
        // Mostrar la ecuación de la regresión polinomial

        System.out.print("\nLa ecuación de la regresión es: \nY = ");
        for (int i = 0; i < coeficientes.length; i++) {
            System.out.print(coeficientes[i][0]+"*X^"+i+" + ");
        }

        double[] simulaciones = {110, 120, 130};
        System.out.println("\nPredicciones:");
        for (double simulacion : simulaciones) {
            double resultado = PolynomialRegression.predecir(coeficientes,simulacion);
            System.out.println("Cuando X = " + simulacion + ", Y = " + resultado);
        }
        System.out.println();
    }

    public static void mostrarResultadosTestData(double[][] coeficientes, ArrayList<DataSet> datos) {
        // Mostrar coeficientes
        System.out.println("Modelo:");
        for (int i = 0; i < coeficientes.length; i++) {
            System.out.print("B" + i + " = " + coeficientes[i][0] + " ");
        }

        // Mostrar la ecuación de la regresión polinomial
        System.out.print("\nLa ecuación de la regresión es: \nY = ");
        for (int i = 0; i < coeficientes.length; i++) {
            System.out.print(coeficientes[i][0] + "*X^" + i);
            if (i < coeficientes.length - 1) {
                System.out.print(" + ");
            }
        }

        // Mostrar predicciones
        System.out.println("\n\nPredicciones:");
        for (DataSet p : datos) {
            double resultado = PolynomialRegression.predecir(coeficientes, p.getX());
            System.out.println("Cuando X = " + p.getX() + ", Y = " + resultado);
        }
        System.out.println();
    }



    public static double predecir(double[][] coeficientes, double x) {
        double resultado = 0.0;  // Inicializar el resultado a 0
        for (int i = 0; i < coeficientes.length; i++) {
            // Sumar cada término del polinomio, elevando x a la potencia i
            resultado += coeficientes[i][0] * Math.pow(x, i);

        }
        return resultado;
    }



}


