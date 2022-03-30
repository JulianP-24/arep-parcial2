package edu.escuelaing.arep;

public class Funciones {
    private Double logNum;
    private Double logeu;
    private final Double euler = 2.718281828;

    public Funciones() {
    }

    public Double exp(Double numero) {
        return Math.exp(numero);
    }

    public Double ln(Double numero) {
        logNum = Math.log(numero);
        logeu = Math.log(euler);
        return logNum / logeu;
    }
}

