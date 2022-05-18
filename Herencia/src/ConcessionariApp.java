import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;

import Concessionari_Vehicles.*;

public class ConcessionariApp {

    public static void main(String[] args) {

        Cotxe c = new Cotxe(true, "ferrari", "111", "2131asd");
        c.encender();
        c.accelerar();
        c.frenar();
        c.prenderAire();
        Cotxe c1 = new Cotxe();
        c1.prenderAire();
        Moto m = new Moto("moto", "111", "2131asd");
        Moto m3 = new Moto("moto", "111", "2131asddd");
        m.encender();
        m.accelerar();
        m.frenar();
        m.hacerWheelie();
        Moto m1 = new Moto(33, "ferrari", "111", "2131asd");
        m1.encender();
        int a = 1;
        // Vehicle v = new Vehicle("ferrari", "111", "2131asd"); // classe abstracta no
        // es podrar crear un objecte de aquesta classe (fer new de la classe)
        Vehicle v = new Cotxe(true, "BMW", "111", "2131asd");

        v.accelerar();
        v.encender();
        v.frenar();

        // UPCASTING: a la classe pare li podem assignar qualsevol classe filla

        v = c;
        if (v instanceof Cotxe) {
            ((Cotxe) v).prenderAire();
            System.out.println("AIREEE");
        }

        // conbertir la variable a moto
        ((Cotxe) v).prenderAire();
        c.prenderAire();
        v = m;

        // obligar assignar un element a subclasse de classe pare
        m = (Moto) v;
        System.out.println(m);
        // dos maneres de comrobar uana variable de la subclasse
        //
        if (v instanceof Vehicle) {
            System.out.println("Es un Vehicle");
        }

        if (v.getClass() == Cotxe.class) {
            System.out.println(" es un Cotxe");
        }

        System.out.println(v.getClass());

        ArrayList<Vehicle> Vehicle = new ArrayList<>();
        Vehicle.add(v);
        Vehicle.add(c);

        Vehicle.add(m);
        Vehicle.add(m1);
        for (Vehicle vehicle : Vehicle) {
            // if (vehicle instanceof Vehicle) {
            // ((Cotxe) vehicle).prenderAire();
            // }
            // if (vehicle instanceof Moto) {
            // ((Moto) vehicle).accelerar();
            // }
            // vehicle.accelerar();
            // vehicle.frenar();
            System.out.println(vehicle);

            // veure interficies

            VehicleAeri va = new Globo();

            // ordenar arraylist per marca

            // Collection.sort(Vehicle);
            compareTo();
            va.despegar();

            // TreeSet<Vehicle> ts =new TreeSet<>(new Comparator<vehicle>(){

            // @Override
            // public int compare (Vehicle v1, vehicle v2){
            // return (int)(v1.getPreu())
            // }
            // });

        }
        System.out.println("TreeSet");
        TreeSet<Integer> tsi = new TreeSet<>();

        tsi.add(80);
        tsi.add(30);
        tsi.add(30);// no ficara aquet element perque esta duplicat
        tsi.add(900);

        for (Integer i : tsi) {
            System.out.println(i);
        }
        System.out.println("HashSet");
        HashSet<Integer> hsi = new HashSet<>();

        hsi.add(800);
        hsi.add(30);
        hsi.add(30);// no ficara aquet element perque esta duplicat
        hsi.add(900);
        hsi.add(1);
        for (Integer i : hsi)
            System.out.println(i);

        System.out.println("\nTreeSet");

        TreeSet<Vehicle> tsv = new TreeSet<>();

        tsv.add(m);
        tsv.add(m);// duplicat no surt
        // tsv.add(v);
        tsv.add(c);
        tsv.add(new Cotxe(true, "ferrari", "111", "2131asd")); // no afegira

        for (Vehicle i : tsv)
            System.out.println(i);

        System.out.println("\nHashSet");
        HashSet<Vehicle> hsv = new HashSet<>();

        hsv.add(m);
        hsv.add(m);// duplicat no surt mateixa adreça memoria

        Vehicle m2 = m;
        hsv.add(m2); // esta mirant a la mateixa adreça de memoria que el m i per aixo no surt
        // tsv.add(v);
        hsv.add(c);
        hsv.add(new Cotxe(true, "ferrari", "111", "2131asd"));

        System.out.println(c.getClass() + " " + c.hashCode());
        c.hashCode();

        c.toString();
        for (Vehicle i : hsv)
            System.out.println(i);

        System.out.println("HashMap");

        HashMap<String, Vehicle> hmv = new HashMap<>();

        hmv.put("2131asd",c );
        hmv.put("2131asd",m ); // te la key repetida i a la key ficara el nou objecte
        // hmv.put("2131asd",c );
        hmv.put("2131asddd",m ); // com que te la key no es repetida al fica a la array da igual si el objecte esta repetit
        hmv.put("2131asddddd",m3 );
        // hmv.remove("2131asddd"); // treure el objecte amb aquesta key 

        for (Vehicle v1: hmv.values()) System.out.println(v1);
    
        for (String s :hmv.keySet()) System.out.println(s);
    


        Scanner teclat = new Scanner(System.in);
        System.out.println("Fica la matricula per consultar");
        String clau = teclat.nextLine();

        System.out.println(hmv.get(clau));

    }

    

    private static void compareTo() {
    }

}
