import java.util.Scanner;

public class Teque {
    static Node first;
    static Node last;
    static Node middle;
    static int size = 0;
    static int numberOfIterations;

    //Main metoden leser fra inputfilene og avgjør antall metodekall som skal utføres med "numberOfIterations".
    // Strengen i inputfilen splittes i 2 og kaller på en av de fire metodene basert på første element som forekommer av .split(), og sender inn split[1] som argument for metoden.

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
        sc.close();
        Long finish = System.nanoTime();
        Long timeElapsed = (finish - start) / 1000000;
        System.out.println(timeElapsed);
    }
    

    //Vil sette inn et element fremst i listen, spesielle caser på 0,1 elementer i listen som krever spesialhåndtering av nodereferanser.
    //Utover dette vil det forårsake en venstreforskyvning av middle referansen dersom det skal settes inn et nytt element fremst, når size er et partall.
    //Dersom ingen av casene inntreffer foregår innsetting som normalt. 

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
            if(isEven(size)){           //Dersom det er et partall antall elementer i listen ved innsetting må middle referansen flyttes ett hakk til venstre
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

    //Setter et element bakerst inn i listen, spesiell edge-case på 0,1 elementer som krever spesielhåndtering av nodereferanser.
    //Utover dette vil det skape en høyreforskyvning av "middle" noden dersom det er et oddetall antall elementer i listen ved innsetting.
    //Dersom det er et partall antall noder i listen vil det ikke forårsake noen forskyvning. 

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
            if(!isEven(size)){              //Dersom size er et oddetall vil det være behov for å flytte middle referansen ett hakk til høyre
                Node node = new Node<>(x);
                middle = middle.next;
                last.next = node;
                node.previous = last;
                last = node;
                size++;
                return;
            }
            Node node = new Node<>(x);      //Dersom ingen av casene over inntreffer vil innsetting foregå som normalt. 
            last.next = node;
            node.previous = last;
            last = node;
            size++;
            return;
        }
    }

    private static boolean isEven(int x){ //Brukes til å å se om indexen neste node skal settes inn på er et partall eller oddetall
        if(x % 2 == 0){                   //Tar inn size på listen som x
            return true;
        }else{
            return false;
        }
    }

    //vil sette inn et element i midten av listen, noen edge cases som 0,1 og 2 elementer krever spesialhåndtering av nodereferanser.
    //Utover dette vil det skape en venstreforskyvning dersom det skal settes noe inn i midten når size er et partall, og en høyreforskyvning når size er et oddetall.
    public static void pushMiddle(int x){               
        int index = ((size +1)/2);
        if (size == 0){
            first = middle = last = new Node<>(x);
            size++;
            return;
        }
        else if(size == 1){
            Node node = new Node<>(x);
            first.next = node;
            node.previous = first;
            middle = last = node;
            size++;
            return;
        }
        else if(size == 2){
            Node node = new Node<>(x);
            first.next = node;
            node.previous = first;
            node.next = last;
            last.previous = node;
            middle = node;
            size++;
            return;
        }
        else if(isEven(size)){ // nytt element skal settes inn til venstre for nåværende midtpeker fordi size på innsetningstidspunktet var et partall
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
            middle = node;
            size++;
            return;
        }

    }
    
    public static void get(int index){
        if(index > ((size +1)/2)){
            int breakpoint = index-((size+1)/2);
            Node pointer = middle;
            for(int i = 0; i<breakpoint -1;i++){
                pointer = pointer.next;
            }
            System.out.println(pointer.data);
        }else{
            Node pointer = first;
            for(int i = 0; i<index; i++){
                pointer=pointer.next;
            }
            System.out.println(pointer.data);
        }
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
