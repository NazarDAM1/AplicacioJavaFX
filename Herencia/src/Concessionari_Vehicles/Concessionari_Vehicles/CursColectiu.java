package Concessionari_Vehicles;

public class CursColectiu extends Curs {

    private int idDia;
    private int preuFinal;
    private String dia;

    
    public CursColectiu(int id, String nom,  String dni_monitor, String dia, int idDia, int preuFinal) {
        super(id, nom, dni_monitor);
        this.idDia = idDia;
        this.dia = dia;
        this.preuFinal = preuFinal;
    }

    public int getIdDia() {
        return idDia;
    }

    public void setIdDia(int idDia) {
        this.idDia = idDia;
    }

    public int getPreuFinal() {
        return preuFinal;
    }

    public void setPreuFinal(int preuFinal) {
        this.preuFinal = preuFinal;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    

}
