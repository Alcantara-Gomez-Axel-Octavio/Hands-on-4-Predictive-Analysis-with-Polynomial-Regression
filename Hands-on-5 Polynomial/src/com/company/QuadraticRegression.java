package com.company;

import java.util.ArrayList;

public class QuadraticRegression {

    private double b0;
    private double b1;
    private double b2;

    public Modelo quadraticRegression(ArrayList<DataSet> datos) {
        calcularModelo(datos);
        return new Modelo(b0, b1, b2, CoeficienteDeError.calcularR2( datos ,b2, b1, b0)); // Ahora incluye el error en el modelo
    }

    public Modelo quadraticRegressionConDataSplitting(ArrayList<DataSet> datos,ArrayList<DataSet> testData) {
        calcularModelo(datos);
        return new Modelo(b0, b1, b2, CoeficienteDeError.calcularR2( testData ,b2, b1, b0)); // Ahora incluye el error en el modelo
    }

    public void calcularModelo(ArrayList<DataSet> datos) {
        double[] sumatorias = MatematicasDiscretas.SumatoriasQuadratic(datos);

        // Acceder a los valores retornados
        double sumX = sumatorias[0];
        double sumY = sumatorias[1];
        double sumXY = sumatorias[2];
        double sumX2 = sumatorias[3];
        double sumX3 = sumatorias[4];
        double sumX4 = sumatorias[5];
        double sumX2Y = sumatorias[6];
        double n = datos.size();

        // Matrices para el cálculo del modelo
        double[][] matrizX = {
                {sumX4, sumX3, sumX2},
                {sumX3, sumX2, sumX},
                {sumX2, sumX, n}
        };

        double[][] matrizY = {
                {sumX2Y},
                {sumXY},
                {sumY}
        };

        // Matriz aumentada y método de Gauss-Jordan
        double[][] matrizAumentada = MatematicasDiscretas.aumentMatrix(matrizX, matrizY);
        MatematicasDiscretas.gaussJordan(matrizAumentada);

        // Coeficientes
        b0 = matrizAumentada[0][3];
        b1 = matrizAumentada[1][3];
        b2 = matrizAumentada[2][3];

        imprimirResultados(datos);
    }

    private void imprimirResultados(ArrayList<DataSet> datos) {

        System.out.println("________________________________________________________________________________________________");
        System.out.println("La ecuación de la regresión cuadrática es: Y = " + b0 + " * X^2 + " + b1 + " * X + " + b2);
        System.out.println("Simulaciones:");

        // Valores de simulación
        double[] simulaciones = {110, 120, 130};
        for (double simulacion : simulaciones) {
            double resultado = predecir(simulacion);
            System.out.println("Cuando X = " + simulacion + ", Y = " + resultado);
        }

        double R2 = CoeficienteDeError.calcularR2( datos ,b2, b1, b0);
        System.out.println("Correlacion= "+CoeficienteDeError.correlacionCuadratica(datos ,b2, b1, b0));
        System.out.println("Coeficiente de Determinación (R2)= " + R2);
    }

    public double predecir(double x) {
        return (b0 * x * x) + (b1 * x) + b2;
    }

    /*
    public static void imprimirMatriz(double[][] matriz) {
        for (double[] fila : matriz) {
            for (double valor : fila) {
                System.out.printf("%.12f\t", valor);
            }
            System.out.println();
        }
    }
     */


    public Modelo PruebaDataSplitting(ArrayList<DataSet> data) {
        System.out.println("\n\n");

        // Creamos una instancia de SimpleLinearRegression
        QuadraticRegression regresion = new QuadraticRegression();

        // Realizamos el data splitting
        SplitResult result = DataSplitting.DataSpliting(data);

        ArrayList<DataSet> trainData = result.getTrainData();
        ArrayList<DataSet> testData = result.getTestData();

        // Calculamos el modelo basado en los datos de entrenamiento
        Modelo modelo2 = regresion.quadraticRegressionConDataSplitting(trainData, testData);

        // Calculamos el error en el conjunto de prueba
        double mseTest = 0;
        for (DataSet p : testData) {
            double prediccion = regresion.predecir(p.getX());  // Predicción
            double error = p.getY() - prediccion;             // Error entre real y predicho
            mseTest += Math.pow(error, 2);                    // Sumar el cuadrado del error
        }
        mseTest /= testData.size();  // Promediar el error cuadrático

        // Imprimimos los resultados de este split
        System.out.println("________________________________________________________");
        System.out.println("Resultados de la prueba con Data Splitting\n");
        System.out.println("Error Cuadrático Medio en Prueba (MSE Con el Test Data): " + mseTest);
        System.out.println("Error Medio en Entrenamiento (R2 con el Test Data): " + modelo2.getError());
        System.out.println("________________________________________________________");

        // Imprimimos predicciones sobre los datos de prueba
        for (DataSet p : testData) {
            double prediccion = regresion.predecir(p.getX()); // Usamos el valor X de los datos de prueba
            System.out.println("Para x = " + p.getX() + ", y real = " + p.getY() + ", predicción = " + prediccion);
        }

        return new Modelo(modelo2.getB0(), modelo2.getB1(),modelo2.getB2(), CoeficienteDeError.calcularR2(testData,modelo2.getB2(), modelo2.getB1(),modelo2.getB0()));
    }
}
