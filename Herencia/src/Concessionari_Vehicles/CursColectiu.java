package Concessionari_Vehicles;

import java.time.LocalDate;

public class CursColectiu extends Curs {

   
    private int preu;
    private LocalDate data;
    
    public CursColectiu(int id, String nom,  String dni_monitor, int preu,LocalDate data) {
        super(id, nom, dni_monitor);
      
        this.preu = preu;
        this.data = data;
    }

  
    public LocalDate getData() {
        return data;
    }


    public void setData(LocalDate data) {
        this.data = data;
    }



    public int getPreuFinal() {
        return preu;
    }

    public void setPreuFinal(int preu) {
        this.preu = preu;
    }


    

}
