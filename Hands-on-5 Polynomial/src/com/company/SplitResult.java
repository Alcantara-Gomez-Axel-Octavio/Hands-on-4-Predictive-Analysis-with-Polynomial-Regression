package com.company;

import java.util.ArrayList;

public class SplitResult {
    private ArrayList<DataSet> trainData; // Lista para los datos de entrenamiento
    private ArrayList<DataSet> testData;  // Lista para los datos de prueba

    // Constructor que recibe ambas listas
    public SplitResult(ArrayList<DataSet> trainData, ArrayList<DataSet> testData) {
        this.trainData = trainData; // Inicializa los datos de entrenamiento
        this.testData = testData;    // Inicializa los datos de prueba
    }

    // Método para obtener los datos de entrenamiento
    public ArrayList<DataSet> getTrainData() {
        return trainData;
    }

    // Método para obtener los datos de prueba
    public ArrayList<DataSet> getTestData() {
        return testData;
    }
}

