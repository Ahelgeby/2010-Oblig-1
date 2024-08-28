import java.util.Scanner;

public class Teque {
    static Node first;
    static Node last;
    static Node middle;
    static int size = 0;
    static int numberOfIterations;

    public static void main(String[] args){
        Long start = System.nanoTime();
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
        Long finish = System.nanoTime();
        Long timeElapsed = (finish - start) / 1000000;
        //System.out.println(timeElapsed);
    }
    

    //Metoden sjekker om køen allerede er tom, og setter oppretter en node som holder på elementet x på køens første og siste plass. 
    //Dersom det finnes elementer i køen opprettes det en node som får første - referansen, og kobles til det elementet som var først i køen før metodekallet via nodens "next" variabel.
    public static void pushFront(int x){
        if(first == last && first == null){
            first = last = middle = new Node<>(x);
            size ++;
            return;
        }
        else if(size == 1){
            Node node = new Node<>(x);
            node.next = first;
            first.previous = node;
            first = node;
            size++;
            return;
        }
        else{
            if(isEven(size)){
            Node node = new Node<>(x);
            node.next = first;
            first.previous = node;
            first = node;
            middle = middle.previous;
            size ++;
            return;
            }
            Node node = new Node<>(x);
            node.next = first;
            first.previous = node;
            first = node;
            size ++;
            return;
        }
    }

    //Metoden sjekker om køen er tom, og i dette tilfellet oppretter en ny node som holder på elementet x og setter dette på første og siste plass i køen. 
    //Dersom det finnes elementer i køen vil det opprettes en node som får siste referansen og kobles til elementet som tidligere var sist via nodens next referanse
    public static void pushBack(int x){
        if(first == last && first == null){
            first = last = middle = new Node<>(x);
            size ++;
            //System.out.println("la til node på pos 0 " + first.data);
            return;
        }
        else if(size == 1){
            Node node = new Node<>(x);
            node.previous = first;
            first.next = node;
            middle = last = node;
            size++;
            return;
        }else{
            if(!isEven(size)){
            Node node = new Node<>(x);
            middle = middle.next;
            last.next = node;
            node.previous = last;
            last = node;
            size++;
            //System.out.println("la til node bakerst " + node.data);
            return;
            }
            Node node = new Node<>(x);
            last.next = node;
            node.previous = last;
            last = node;
            size++;
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
    private static boolean isEven(int x){ //Brukes til å å se om indexen neste node skal settes inn på er et partall eller oddetall
        if(x % 2 == 0){             //Tar inn size på listen som x
            return true;
        }else{
            return false;
        }
    }

    public static void pushMiddle(int x){
        int index = ((size +1)/2);
        if (size == 0){
            first = middle = last = new Node<>(x);
            size++;
            return;
        }
        else if(size == 1){
            Node node = new Node<>(x);
            middle = last = node;
            first.next = node;
            middle.previous = first;
            last.previous = first;
            size++;
            return;
        }
        else if(isEven(size)){ // nytt element skal settes inn til venstre for nåværende midtpeker fordi size på innsetningstidspunktet var et partall
            // System.out.println("Size: " + size);
            Node node = new Node<>(x);
            node.previous = middle.previous;
            node.previous.next = node;
            node.next = middle;
            middle.previous = node;
            middle = node;
            size ++;
            return;
        }
        else if(!isEven(size)){ //nytt element skal settes inn til høyre for nåværende midtpeker fordi size på innsetningstidspunktet var et oddetall
            Node node = new Node<>(x);
            node.previous = middle;
            node.next = middle.next;
            middle.next = node;
            node.next.previous = node;
            size++;
            return;
        }

    }
    
    //Gjør ca det samme som retrieve, men skriver ut tallet som noden på aktuell index holder på, istedenfor å returnere node objektet
    public static void get(int index){
        // System.out.println("Size: " + size);
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
