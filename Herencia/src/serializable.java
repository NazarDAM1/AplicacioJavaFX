
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;

import Concessionari_Vehicles.*;

public class serializable {

    public static void main(String[] args) throws IOException, ClassNotFoundException {



       

        File fichero = new File("src/File/Consessionari.dat");
        FileInputStream flujoFichero = new FileInputStream(fichero);
        ObjectInputStream deserializador = new ObjectInputStream(flujoFichero);


        HashSet<Vehicle> hsv = new HashSet<>();
        hsv=(HashSet<Vehicle>)deserializador.readObject();


        // Cotxe c = new Cotxe(true, "ferrari", "111", "2131asd");
        // Moto m = new Moto("moto", "111", "2131asd");
        // Vehicle v = new Cotxe(true, "BMW", "111", "2131asd"); // cada index sera diferent
        // Vehicle v = new Cotxe(true, "BMW", "111", "2131asd");

        // hsv.add(m);
        // hsv.add(c);
        // hsv.add(v);

        for (Vehicle i : hsv){ 
            System.out.println(i +  i.getMarca());
            if (i.getMarca().equals("moto")){ // == nomes per numeros i booleans
                System.out.println("marca moto");
            }else{
                System.out.println("No es moto");
            }
            
        };
        


       

        File fichero1 = new File("src/File/Consessionari.dat"); 
        // File fichero = new File("");
        // System.out.println(fichero.getAbsolutePath());
        FileOutputStream flujoFichero1 = new FileOutputStream(fichero1);
        ObjectOutputStream serializador = new ObjectOutputStream(flujoFichero1);

        serializador.writeObject(hsv);
        serializador.close(); 
        



    }

}
