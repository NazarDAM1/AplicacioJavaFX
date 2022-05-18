package Concessionari_Vehicles;

public class Cotxe extends Vehicle {

    protected boolean tieneAire;

    public Cotxe() {
        super();
    }

    public Cotxe(boolean tieneAire, String marca, String model, String matricula) {

        // el constructor de clase pare ha de estar en la primera linia SUPER
        super(marca, model, matricula);
        this.tieneAire = tieneAire;

    }

    public Cotxe(String marca, String model, String matricula) {

        
        super(marca, model, matricula);

    }

    public Cotxe(boolean tieneAire) {
        this.tieneAire = tieneAire;
    }

    public void prenderAire() {
        if (tieneAire) {
            System.out.println("Aire" + this);
        }

    }
    @Override
    public void accelerar() {

        // this per saber quin objecte es
        System.out.println("Acelerar " + this);
    }

    @Override
    public String toString() {
        return "Cotxe [  " + super.toString() +" tieneAire = " + tieneAire + "]";
    }

    @Override
    public void frenar() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void encender() {
        // TODO Auto-generated method stub
         

    }
    
    public int compareTo(Vehicle o) {
      
        return this.getMarca().compareTo(o.getMarca());
    }

}
