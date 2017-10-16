class LinkedQueue<T>{
    class Node{
        Node next;
        T value;
        Node(T value){
            this.next = null;
            this.value = value;
        }
    }

    private Node first, last;
    private int size;
    LinkedQueue(){
        first = null;
        last = null;
        size = 0;
    }

    int getSize(){ return this.size;}

    void push(T value){
        size++;
        if(first == null){
            first = new Node(value);
            last = first;
        }
        else{
            Node el = new Node(value);
            el.next = first;
            first = el;

        }
    }

    void clear(){
        int num = size;
        for(int i = 0; i < num; ++i){
            this.pop();
        }
        size = 0;
    }

    T front(){
        Node el = first;
        if(el.next == null)
            return el.value;
        while(el.next != null)
            el = el.next;
        return el.value;
    }

    T pop(){
        Node el = first;
        if(el.next == null){
            T val = first.value;
            first = last = null;
            size = 0;
            return val;
        }
        else{
            while(el.next != last)
                el = el.next;
            T val = el.next.value;
            el.next = null;
            last = el;
            --size;
            return val;
        }
    }

}


public class Main {
    public static void main(String[] args) {
        LinkedQueue<String> q = new LinkedQueue<>();
        q.push("QWE");
        q.push("ASD");
        System.out.println(q.pop());
        System.out.println(q.pop());
    }
}
