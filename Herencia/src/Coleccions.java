import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

public class Coleccions {

    public static void main(String[] args) {

        // provemListes();
        provemSets();

    }

    static void provemListes() {
        // ArrayList<Integer> enters = new ArrayList<>();
        // LinkedList<Integer> enters = new LinkedList<>();

        List<Integer> enters = new LinkedList<>();

        // afegir objecte integer
        enters.add(1);
        enters.add(4);
        enters.add(5);
        enters.add(6);
        enters.add(7);
        // afegir un int primitiu java el convertiex automaticament a objecte envoltori
        // Integer
        // enters.add(new Integer(5));
        enters.add(1, 2);
        enters.add(2, 3);

        // aqui estem esborrant el primer index que te un 8
        // enters.remove(new Integer(8));

        // enters.remove(new Integer(8));

        // enters.remove(new Integer(6));

        // esborrar la posicio de memoria amb el index 1
        // enters.remove(1);

        // if (!enters.isEmpty()) {
        // for (Integer Enter : enters) {
        // System.out.println(Enter);
        // }
        // }

        // if (!enters.isEmpty()) {
        // for (Integer i : enters) {
        // if (i == 4) // aqui estem esborrant el primer index (primera posicio trobada
        // ) que te un 3
        // // error perque esta esborrant la posicio que a la mateixa vegada esta
        // llegint
        // enters.remove(i);

        // }
        // }

        Iterator<Integer> it = enters.iterator();

        if (!enters.isEmpty()) {
            for (Integer Enter : enters) {
                System.out.println(Enter);
            }
        }
        System.out.println("array es de " + enters.size());

        System.out.println("Array despres de esborrar");

        while (it.hasNext()) {
            Integer i = it.next();

            if (i.equals(new Integer(3)))
                it.remove();

        }
        if (!enters.isEmpty()) {
            for (Integer Enter : enters) {
                System.out.println(Enter);
            }
        }

        System.out.println("array es de " + enters.size());
    }

    static void provemSets() {


        System.out.println(" LinkedHashSet ");
        LinkedHashSet<Integer> ls = new LinkedHashSet();
        ls.add(7);
        ls.add(3);
        ls.add(4);
        ls.add(5);
        ls.add(6);
        ls.add(7); // no fica dades repetides

        for (Integer i:ls){
            System.out.println(i);
        }
        System.out.println(" TreeSet ");
        TreeSet<Integer> ts = new TreeSet(); // ordena la array
        
        ts.add(3);
        ts.add(8);
        ts.add(5);
        ts.add(6);
        ts.add(7);


        for (Integer i:ts){
            System.out.println(i);
        }

        System.out.println(" TreeSet de Strings array ordenada ");
        TreeSet<String> tss = new TreeSet(); // ordena la array per lletra i tambe no duplica dades 
        
        tss.add("provem");
        tss.add("el");
        tss.add("tree");
        tss.add("Set");
        tss.add("tree");
        tss.add("tree");


        for (String i:tss){
            System.out.println(i);
        }
    }

}
