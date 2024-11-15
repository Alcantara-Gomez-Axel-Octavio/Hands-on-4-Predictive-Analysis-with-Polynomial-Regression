package com.company;

import java.util.ArrayList;

public class SimpleLinearRegression {

    private double pendiente;
    private double intercept;

    public SimpleLinearRegression() {

    }

    // Método para calcular la regresión y retornar un modelo con los resultados
    public Modelo calcularModelo(ArrayList<DataSet> data) {

        calcularParametros(data);

        // Calcular error cuadrático medio
        double errorCuadraticoMedio = CoeficienteDeError.calcularErrorCuadraticoMedio(data, this);
        System.out.println("Error cuadrático medio: " + errorCuadraticoMedio);

        return new Modelo(pendiente, intercept, CoeficienteDeError.R2(data, intercept, pendiente));
    }

    public Modelo calcularModeloConDataSplitting(ArrayList<DataSet> data,ArrayList<DataSet> testdata) {

        calcularParametros(data);

        // Calcular error cuadrático medio
        double errorCuadraticoMedio = CoeficienteDeError.calcularErrorCuadraticoMedio(data, this);
        System.out.println("Error cuadrático medio: " + errorCuadraticoMedio);

        return new Modelo(pendiente, intercept, CoeficienteDeError.R2(testdata, intercept, pendiente));
    }




    // Método que realiza los cálculos de la pendiente e intercepto
    private void calcularParametros(ArrayList<DataSet> data) {
        int n = data.size();
        double sumX = MatematicasDiscretas.SumaDeX(data);
        double sumY = MatematicasDiscretas.SumaDeY(data);
        double sumXY = MatematicasDiscretas.SumaDeXY(data);
        double sumX2 = MatematicasDiscretas.SumaDeX2(data);
        double sumY2 = MatematicasDiscretas.SumaDeY2(data);

        pendiente = (n * sumXY - sumX * sumY) / (n * sumX2 - sumX * sumX);
        intercept = (sumY - pendiente * sumX) / n;

        double Correlacion= CoeficienteDeError.correlacion(n, sumXY, sumX, sumY, sumX2, sumY2);

        double r2= CoeficienteDeError.R2(data, intercept, pendiente);

        System.out.println("____________________________________________________________________________");
        System.out.println("Resultados con el data set ingresado\n");
        System.out.println("Ecuación de la regresión lineal: y = " + pendiente + "x + " + intercept);
        System.out.println("Correlacion= "+Correlacion);
        System.out.println("Coeficiente de Determinación (R2)= "+r2);
    }

    // Método para predecir un valor Y dado un valor X
    public double predict(double x) {
        return pendiente * x + intercept;
    }

    public Modelo PruebaDataSplitting(ArrayList<DataSet> data) {
        System.out.println("\n\n");

        // Creamos una instancia de SimpleLinearRegression
        SimpleLinearRegression regresion = new SimpleLinearRegression();

        // Realizamos el data splitting
        SplitResult result = DataSplitting.DataSpliting(data);

        ArrayList<DataSet> trainData = result.getTrainData();
        ArrayList<DataSet> testData = result.getTestData();

        // Calculamos el modelo basado en los datos de entrenamiento
        Modelo modelo2 = regresion.calcularModeloConDataSplitting(trainData,testData);

        // Calculamos el error en el conjunto de prueba
        double mseTest = 0;
        for (DataSet p : testData) {
            double prediccion = regresion.predict(p.getX());  // Predicción
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
            double prediccion = regresion.predict(p.getX()); // Usamos el valor X de los datos de prueba
            System.out.println("Para x = " + p.getX() + ", y real = " + p.getY() + ", predicción = " + prediccion);
        }

        return new Modelo(modelo2.getPendiente(), modelo2.getIntercept(), CoeficienteDeError.R2(testData, modelo2.getIntercept(), modelo2.getPendiente()));
    }

}














