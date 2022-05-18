package Concessionari_Vehicles;

public class  Moto extends Vehicle {

    protected int anchoDeManubrio;

    public Moto(int anchoDeManubrio, String marca, String model, String matricula) {
        super(marca, model, matricula);
        this.anchoDeManubrio = anchoDeManubrio;
       
    }

    public Moto(String marca, String model, String matricula) {
        super(marca, model, matricula);
        
       
    }

    public Moto(int anchoDeManubrio) {
        
        this.anchoDeManubrio = anchoDeManubrio;
       
    }


    public Moto() {
        
       
    }
   

    @Override
    public String toString() {
        return "Moto [  " + super.toString() +" anchoDeManubrio= " + anchoDeManubrio + "]";
    }
    

    // PUBLIC es podra cridar desde altre paquet
    public void hacerWheelie() {
        System.out.println("hacerWheelie" + this);
    }

    // public void accelerar() {

    //     // this per saber quin objecte es
    //     System.out.println("Acelerar Moto " + this);
    // }

    

    @Override
    public void frenar() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void encender() {
        // TODO Auto-generated method stub
         
    }

    @Override
    public void accelerar() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int compareTo(Vehicle o) {
        // TODO Auto-generated method stub
        return 0;
    }
}
