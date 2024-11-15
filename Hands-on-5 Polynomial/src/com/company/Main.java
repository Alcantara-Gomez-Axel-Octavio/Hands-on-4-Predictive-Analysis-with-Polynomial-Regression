package com.company;

import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {

        int j=3;
        int NumeroDeDataSplitting = 2;

        if(j==1) {

            RegressionLinealSimple(NumeroDeDataSplitting);

        }
        if(j==2){

            RegressionQuadratica(NumeroDeDataSplitting);
        }
        else{
            RegressionPolynomial(NumeroDeDataSplitting);
        }


    }



    public static void RegressionLinealSimple(int NumeroDeDataSplitting){

    /* SLR */
        // Simulamos una lista de datos
        ArrayList<DataSet> data = DataSet.leerDatos();

        // Creamos una instancia de SimpleLinearRegression
        SimpleLinearRegression regresionLineal = new SimpleLinearRegression();

        // Calculamos el modelo basado en los datos
        Modelo modelo = regresionLineal.calcularModelo(data);

        // Creamos una instancia de la clase Modelo para almacenar y comparar
        Modelo comparadorDeModelos = new Modelo();

        // Agregamos el modelo generado a la lista de modelos
        comparadorDeModelos.agregarModelo(modelo);

    /* Simulaciones */
        double[] simulaciones = {20, 30, 40, 45, 50};
        for (double simulacion : simulaciones) {
            double prediccion = regresionLineal.predict(simulacion);
            System.out.println("Para x = " + simulacion + ", y = " + prediccion);
        }

    /* DataSplitting */
        for (int i = 0; i < NumeroDeDataSplitting; i++) {
            Modelo nuevoModelo = regresionLineal.PruebaDataSplitting(data);
            comparadorDeModelos.agregarModelo(nuevoModelo);  // Agregar cada modelo a la lista
        }

    /* Mostrar mejor modelo */
        comparadorDeModelos.encontrarMejorModelo();

    }



    public static void RegressionQuadratica(int NumeroDeDataSplitting){


        /* QLR */
        // Simulamos una lista de datos
        ArrayList<DataSet> data = DataSet.leerDatosQuadratic();

        // Creamos una instancia de QuadraticRegression
        QuadraticRegression regresionQuadratica = new QuadraticRegression();

        // Calculamos el modelo basado en los datos
        Modelo modelo =regresionQuadratica.quadraticRegression(data);

        // Creamos una instancia de la clase Modelo para almacenar y comparar
        Modelo comparadorDeModelos = new Modelo();

        // Agregamos el modelo generado a la lista de modelos
        comparadorDeModelos.agregarModelo(modelo);

        /* DataSplitting  */
        for (int i = 0; i < NumeroDeDataSplitting; i++) {
            Modelo nuevoModelo = regresionQuadratica.PruebaDataSplitting(data);
            comparadorDeModelos.agregarModelo(nuevoModelo);  // Agregar cada modelo a la lista
        }

        /* Mostrar mejor modelo */
        comparadorDeModelos.encontrarMejorModelo();

    }

    public static void RegressionPolynomial(int NumeroDeDataSplitting){

        int grado = 2;

        // Creamos una instancia de la clase Modelo para almacenar y comparar
        Modelo comparadorDeModelos = new Modelo();
        PolynomialRegression regresionPolynomial = new PolynomialRegression();

        ArrayList<DataSet> data = DataSet.leerDatos();

        double[][] coeficientes = PolynomialRegression.polynomialRegression(data,grado);
        Modelo modelo =PolynomialRegression.AnadirModelo(coeficientes, data);
        comparadorDeModelos.agregarModelo(modelo);
        PolynomialRegression.mostrarResultados(coeficientes);


        for (int i = 0; i < NumeroDeDataSplitting; i++) {

            // Realizamos el data splitting
            SplitResult result = DataSplitting.DataSpliting(data);

            ArrayList<DataSet> trainData = result.getTrainData();
            ArrayList<DataSet> testData = result.getTestData();

            coeficientes = PolynomialRegression.polynomialRegression(trainData,grado);
            Modelo nuevoModelo =PolynomialRegression.AnadirModelo(coeficientes, testData);
            comparadorDeModelos.agregarModelo(nuevoModelo);
            PolynomialRegression.mostrarResultadosTestData(coeficientes, testData);
        }

        /* Mostrar mejor modelo */
        comparadorDeModelos.encontrarMejorModelo();





    }

}


