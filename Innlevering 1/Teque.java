import java.util.Scanner;

public class Teque {
    static Node first;
    static Node last;
    static int size;
    static int numberOfIterations;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        numberOfIterations = Integer.parseInt(sc.nextLine());
        for(int i= 0; i<numberOfIterations; i++){
            String[] line = sc.nextLine().split(" ");
            if(line[0].equals("push_front")){
                pushFront(Integer.parseInt(line[1]));
            }else if(line[0].equals("push_back")){
                pushBack(Integer.parseInt(line[1]));
            }else if(line[0].equals("push_middle")){
                pushMiddle(Integer.parseInt(line[1]));
            }else if(line[0].equals("get")){
                get(Integer.parseInt(line[1]));
            }
        }
    }
    

    public Teque(){
        first = last = null;
        size = 0;
    }
    //Metoden sjekker om køen allerede er tom, og setter oppretter en node som holder på elementet x på køens første og siste plass. 
    //Dersom det finnes elementer i køen opprettes det en node som får første - referansen, og kobles til det elementet som var først i køen før metodekallet via nodens "next" variabel.
    public static void pushFront(int x){
        if(first == last && first == null){
            first = last = new Node<>(x);
            size ++;
            //System.out.println("La til node i pos 0" + first.data);
            return;
        }
        else{
            Node node = new Node<>(x);
            node.next = first;
            first.previous = node;
            first = node;
            size ++;
            //System.out.println("La til node forran" + node.data);
            return;
        }
    }

    //Metoden sjekker om køen er tom, og i dette tilfellet oppretter en ny node som holder på elementet x og setter dette på første og siste plass i køen. 
    //Dersom det finnes elementer i køen vil det opprettes en node som får siste referansen og kobles til elementet som tidligere var sist via nodens next referanse
    public static void pushBack(int x){
        if(first == last && first == null){
            first = last = new Node<>(x);
            size ++;
            //System.out.println("la til node på pos 0 " + first.data);
            return;
        }
        else{
            Node node = new Node<>(x);
            last.next = node;
            node.previous = last;
            last = node;
            size++;
            //System.out.println("la til node bakerst " + node.data);
            return;
        }
    }

    //Hjelpemetode for å få tak i elementene på en en gitt index i køen, brukes i pushMiddle. Metoden tar inn en index, starter med det første elementet i køen og oppdaterer pekeren til neste node "index" ganger 
    //Til den kommer frem til den aktuelle noden 

    private static Node retrieve(int index){
        Node pointer = first;
        for (int i = 0; i <index  ; i++){
            pointer = pointer.next;
        }
        return pointer;
    }
    //Sjekker om køen er tom, hvis dette er tilfellet fungerer dette likt som pushFront og pushBack
    //Deretter sjekkes det om det er nøyaktig ett element i køen, hvis dette er tilfellet settes elementet sist i køen i henhold til formelen "Size + 1 / 2" = index 1. I dette tilfellet er det det samme som å sette
    //Noe på siste plass i køen. 
    //Dersom det er mer enn ett element i køen bruker metoden retrieve(index) for å hente de aktuelle nodene i køen som ligger hhv. forran og bak der noden skal settes inn, og kobler riktige referanser.
    public static void pushMiddle(int x){
        if(first == last && first == null){
            first = last = new Node<>(x);
            size ++;
            //System.out.println("la til node i først " + first.data);
            return;
        }
        else if(first == last && first != null){
            pushBack(x);
            return;
        }
        else{
            Node node = new Node<> (x);
            int index = ((size +1) /2);
            Node temp = retrieve(index);
            node.previous = temp.previous;
            node.next = temp;
            node.previous.next = node;
            node.next.previous = node;
            size++;
            //System.out.println("la til node i midten " + node.data);
            return;

        }

        }
    
    //Gjør ca det samme som retrieve, men skriver ut tallet som noden på aktuell index holder på, istedenfor å returnere node objektet
    public static void get(int index){
        Node pointer = first;
        for (int i = 0; i<index ;i++){
            pointer = pointer.next;
        }
        System.out.println(pointer.data);
    }





    //Indre klasse for å holde styr på data og lenkeliste-strukturen. 
    private static class Node<E>{
        E data; 
        Node next;
        Node previous;
        public Node(E x){
            data = x;
            next = null;
            previous = null;

        }
    }
    
}
