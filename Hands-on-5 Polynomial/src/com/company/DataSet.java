package com.company;

import java.util.ArrayList;

public class DataSet {
    private double X;
    private double Y;
    private double X2;
    private double X3;
    private double X4;

    public DataSet(double X, double Y) {
        this.X = X;
        this.Y = Y;
    }

    public DataSet(double X, double X2, double Y) {
        this.X = X;
        this.X2 = X2;
        this.Y = Y;
    }

    public DataSet(double X, double X2, double X3, double X4, double Y) {
        this.X = X;
        this.X2 = X2;
        this.X3 = X3;
        this.X4 = X4;
        this.Y = Y;
    }

    public double getX() {
        return X;
    }

    public double getX2() {
        return X2;
    }

    public double getY() {
        return Y;
    }

    // Simulación de lectura de datos
    public static ArrayList<DataSet> leerDatos() {
        ArrayList<DataSet> data = new ArrayList<>();
            data.add(new DataSet(108, 95));
            data.add(new DataSet(115, 96));
            data.add(new DataSet(106, 95));
            data.add(new DataSet(97, 97));
            data.add(new DataSet(95, 93));
            data.add(new DataSet(91, 94));
            data.add(new DataSet(97, 95));
            data.add(new DataSet(83, 93));
            data.add(new DataSet(83, 92));
            data.add(new DataSet(78, 86));
            data.add(new DataSet(54, 73));
            data.add(new DataSet(67, 80));
            data.add(new DataSet(56, 65));
            data.add(new DataSet(53, 69));
            data.add(new DataSet(61, 77));
            data.add(new DataSet(115, 96));
            data.add(new DataSet(81, 87));
            data.add(new DataSet(78, 89));
            data.add(new DataSet(30, 60));
            data.add(new DataSet(45, 63));
            data.add(new DataSet(99, 95));
            data.add(new DataSet(32, 61));
            data.add(new DataSet(25, 55));
            data.add(new DataSet(28, 56));
            data.add(new DataSet(90, 94));
            data.add(new DataSet(89, 93));
        return data;
    }

    public static ArrayList<DataSet> leerDatosQuadratic() {
        ArrayList<DataSet> data = new ArrayList<>();
        data.add(new DataSet(108, 95));
        data.add(new DataSet(115, 96));
        data.add(new DataSet(106, 95));
        data.add(new DataSet(97, 97));
        data.add(new DataSet(95, 93));
        data.add(new DataSet(91, 94));
        data.add(new DataSet(97, 95));
        data.add(new DataSet(83, 93));
        data.add(new DataSet(83, 92));
        data.add(new DataSet(78, 86));
        data.add(new DataSet(54, 73));
        data.add(new DataSet(67, 80));
        data.add(new DataSet(56, 65));
        data.add(new DataSet(53, 69));
        data.add(new DataSet(61, 77));
        data.add(new DataSet(115, 96));
        data.add(new DataSet(81, 87));
        data.add(new DataSet(78, 89));
        data.add(new DataSet(30, 60));
        data.add(new DataSet(45, 63));
        data.add(new DataSet(99, 95));
        data.add(new DataSet(32, 61));
        data.add(new DataSet(25, 55));
        data.add(new DataSet(28, 56));
        data.add(new DataSet(90, 94));
        data.add(new DataSet(89, 93));
        return data;
    }

}

