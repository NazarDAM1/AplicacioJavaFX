package Concessionari_Vehicles;

import java.io.Serializable;

public abstract class Vehicle implements Comparable<Vehicle>, Serializable { // classe abstracta //
    private static final long serialVersionUID = 1l;
    private String marca;
    private String model;
    private double cilindrada;
    private String matricula;

    // public void accelerar() {

    // // this per saber quin objecte es
    // System.out.println("Acelerar ");
    // System.out.println("Acelerar ");
    // }

    // public void frenar() {
    // System.out.println("Frenar " + this);
    // }

    // public void encender() {
    // System.out.println("Encendre " + this);
    // }

    public abstract void accelerar();

    public void frenar() {
    };

    public abstract void encender();

    // els constructors no shereten mai
    @Override
    public String toString() {
        return "Vehicle [marca=" + marca + ", matricula=" + matricula + ", model=" + model + "]";
    }

    public Vehicle(String marca, String model, String matricula) {
        this.marca = marca;
        this.model = model;
        this.matricula = matricula;
    }

    // si no tots els cotxes es obligatori que tinguin un valor i no es podra crear
    // objectes Cotxe sense cap valor
    public Vehicle() {

    }

    public String getMarca() {
        return this.marca;
    }

    

    // per no ficar coches amb matricula iguals a HashSet
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Vehicle other = (Vehicle) obj;
        if (matricula == null) {
            if (other.matricula != null)
                return false;
        } else if (!matricula.equals(other.matricula))
            return false;
        return true;
    }

}
