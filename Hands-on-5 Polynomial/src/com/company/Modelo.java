package com.company;

import java.util.ArrayList;

public class Modelo {
    private double pendiente;
    private double intercept;
    private double b0;
    private double b1;
    private double b2;
    private double error;
    private double[] coeficientes;

    // Lista para almacenar los modelos
    private ArrayList<Modelo> modelos = new ArrayList<>();

    // Constructor de la clase Modelo
    public Modelo(double pendiente, double intercept, double error) {
        this.pendiente = pendiente;
        this.intercept = intercept;
        this.error = error;
    }

    public Modelo(double b0, double b1, double b2, double error) {
        this.b0 = b0;
        this.b1 = b1;
        this.b2 = b2;
        this.error = error;
    }

    public Modelo(double[] coeficientes, double error) {
        this.coeficientes = coeficientes;
        this.error = error;
    }

    public Modelo() {

    }

    // Método para encontrar el modelo con el menor error (mejor ajuste)
    public void encontrarMejorModelo() {
        if (modelos == null || modelos.isEmpty()) {
            System.out.println("No hay modelos disponibles para comparar.");
            return;
        }

        // Inicializar el mejor modelo como el segundo en la lista
        Modelo mejorModelo = modelos.get(1);
        boolean esPrimero = true;
        // Poniendo al de mejor porcentaje
        for (Modelo modelo : modelos) {

            if(esPrimero){
                esPrimero=false;
                continue;
            }

            if (modelo.getError() > mejorModelo.getError()) {
                mejorModelo = modelo;
            }

        }


        // Mostrar el mejor modelo
        System.out.println("\n");
        System.out.println("El mejor modelo de los Data Splitting:" );
        System.out.println("F(X)= " + mejorModelo.getB0()+"*X^2 + "+mejorModelo.getB1()+"*X + "+mejorModelo.getB2()+"\nCon R2 de= "+mejorModelo.getError());
    }

    // Método para agregar modelos a la lista
    public void agregarModelo(Modelo modelo) {
        modelos.add(modelo);
    }

    // Getters para acceder a los valores del modelo
    public double getPendiente() {
        return pendiente;
    }

    public double getIntercept() {
        return intercept;
    }

    public double getB0() {
        return b0;
    }

    public double getB1() {
        return b1;
    }

    public double getB2() {
        return b2;
    }


    // Métodos para obtener coeficientes y error
    public double[] getCoeficientes() {
        return coeficientes;
    }

    public double getError() {
        return error;
    }


    // Método toString para mostrar el modelo
    @Override
    public String toString() {
        return "Modelo [pendiente=" + pendiente + ", intercept=" + intercept + ", b0=" + b0 + ", b1=" + b1 + ", b2=" + b2 + ", error=" + error + "]";
    }


}
