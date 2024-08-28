public class Test {
    public static void main(String[] args){
        Teque teque = new Teque();
        teque.pushBack(9);
        teque.pushFront(3);
        teque.pushMiddle(5);
        teque.get(0);
        teque.get(1);
        teque.get(2);
        teque.pushMiddle(1);
        teque.get(1);
        teque.get(2);
    }
    
}
