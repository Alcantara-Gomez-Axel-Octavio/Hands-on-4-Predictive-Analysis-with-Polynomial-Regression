package com.company;

import java.util.ArrayList;
import java.util.Collections;

public class DataSplitting {

    // Método para dividir los datos en un 70% de entrenamiento y 30% de prueba
    public static SplitResult DataSpliting(ArrayList<DataSet> data) {
        ArrayList<DataSet> trainData = new ArrayList<>();
        ArrayList<DataSet> testData = new ArrayList<>();

        // Barajar los datos para una distribución aleatoria
        Collections.shuffle(data);

        // Calcular el índice para dividir los datos en 70% y 30%
        int trainSize = (int) (data.size() * 0.7);

        // Separar los datos en entrenamiento (70%) y prueba (30%)
        for (int i = 0; i < data.size(); i++) {
            if (i < trainSize) {
                trainData.add(data.get(i));
            } else {
                testData.add(data.get(i));
            }
        }
        return new SplitResult(trainData, testData);
    }



}
