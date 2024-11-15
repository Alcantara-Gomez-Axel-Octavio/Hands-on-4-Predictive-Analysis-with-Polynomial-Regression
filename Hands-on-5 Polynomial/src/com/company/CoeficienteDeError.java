package com.company;

import java.util.ArrayList;
import java.util.Iterator;

public class CoeficienteDeError {

    public static double calcularErrorCuadraticoMedio(ArrayList<DataSet> data, SimpleLinearRegression modelo) {
        double error = 0;
        for (DataSet p: data) {
            double prediccion = modelo.predict(p.getX());
            error += Math.pow(prediccion - p.getY(), 2);
        }
        return error / data.size();
    }

    public static double correlacion(double n,double sumXY, double sumX, double sumY, double sumX2, double sumY2){
        double Correlacion=(n * sumXY - sumX * sumY) /
                Math.sqrt((n * sumX2 - sumX * sumX) * (n * sumY2 - sumY * sumY));

        return Correlacion;
    }
    public static double correlacionCuadratica(ArrayList<DataSet> datos, double a, double b, double c) {
        double sumX = 0.0;
        double sumY = 0.0;
        double sumX2 = 0.0;
        double sumY2 = 0.0;
        double sumXY = 0.0;

        int n = datos.size();

        // Calcular las sumatorias necesarias
        for (DataSet data : datos) {
            double x = data.getX();
            double yReal = data.getY();

            // Predicción de y usando el modelo cuadrático
            double yPredicho = a * x * x + b * x + c;

            sumX += x;
            sumY += yPredicho; // Aquí usamos y predicho
            sumX2 += x * x;
            sumY2 += yPredicho * yPredicho;
            sumXY += x * yPredicho;
        }

        // Aplicamos la fórmula de correlación
        double correlacion = (n * sumXY - sumX * sumY) /
                Math.sqrt((n * sumX2 - sumX * sumX) * (n * sumY2 - sumY * sumY));

        return correlacion;
    }


    public static double R2(ArrayList<DataSet> data, double B0, double B1) {
        double sumSST = 0; //SquaredTotal
        double sumSquaredResidual = 0;
        double meanY = 0;

        // Calcular la media de Y
        for (DataSet p : data) {
            meanY += p.getY();
        }
        meanY /= data.size();

        for (DataSet p : data) {
            // Total sum of squares
            sumSST += Math.pow(p.getY() - meanY, 2);
            // Residual sum of squares
            sumSquaredResidual += Math.pow(p.getY() - (B0 + B1 * p.getX()), 2);
        }

        return 1 - (sumSquaredResidual / sumSST);
    }





    public static double error(double a, double b, double c, ArrayList<DataSet> Datos) {
        double SSE = 0.0D;
        double SST = 0.0D;
        double sumaY = 0.0D;

        Iterator var20;
        DataSet p;
        double x;
        double y;

        for(var20 = Datos.iterator(); var20.hasNext(); SSE += (y - a * x * x - b * x - c) * (y - a * x * x - b * x - c)) {
            p = (DataSet)var20.next();
            x = p.getX();
            y = p.getY();
        }

        for(var20 = Datos.iterator(); var20.hasNext(); sumaY += p.getY()) {
            p = (DataSet)var20.next();
        }

        double promedioY = sumaY / 7.0D;

        for(var20 = Datos.iterator(); var20.hasNext(); SST += (y - promedioY) * (y - promedioY)) {
            p = (DataSet)var20.next();
            x = p.getX();
            y = p.getY();
        }

        double error = 1.0D - SSE / SST;
        return error;
    }

    public static double calcularR2(ArrayList<DataSet> data, double b0, double b1, double b2) {
        double ssTotal = 0;
        double ssResiduos = 0;
        double mediaY = MatematicasDiscretas.PromedioDeY(data);
        double r2 = 0;

        for (int i = 0; i < data.size(); i++) {
            double X = data.get(i).getX();
            double Y = data.get(i).getY();

            double prediccion = b0 + b1 * X + b2 * Math.pow(X, 2);
            ssTotal += Math.pow(Y - mediaY, 2);
            ssResiduos += Math.pow(Y - prediccion, 2);
        }

        r2 = 1 - (ssResiduos / ssTotal);

        return r2;
    }


    public static double calcularR2Polynomial(ArrayList<DataSet> data, double[] coeficientes) {
        double ssTotal = 0;
        double ssResiduos = 0;
        double mediaY = MatematicasDiscretas.PromedioDeY(data);
        double r2;

        // Recorrer los datos
        for (int i = 0; i < data.size(); i++) {
            double X = data.get(i).getX();
            double Y = data.get(i).getY();

            // Calcular la predicción usando el polinomio de cualquier grado
            double prediccion = 0;
            for (int j = 0; j < coeficientes.length; j++) {
                prediccion += coeficientes[j] * Math.pow(X, j);
            }

            // Suma de los cuadrados totales (total sum of squares)
            ssTotal += Math.pow(Y - mediaY, 2);

            // Suma de los residuos (residual sum of squares)
            ssResiduos += Math.pow(Y - prediccion, 2);
        }

        // Cálculo del R²
        r2 = 1 - (ssResiduos / ssTotal);

        return r2;
    }



}

