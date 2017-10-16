class LinkedStack {
    class Node{
        Node next;
        int value;
        Node(int value){
            this.next = null;
            this.value = value;
        }
    }

    private Node first, last;
    private int size;
    LinkedStack(){
        first = null;
        last = null;
        size = 0;
    }

    int getSize(){ return this.size;}

    void push(int value){
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

    int front(){
        Node el = first;
        if(el.next == null)
            return el.value;
        while(el.next != null)
            el = el.next;
        return el.value;
    }

    int pop(){
        Node el = first;
        if(el.next == null){
            int val = first.value;
            first = last = null;
            size = 0;
            return val;
        }
        else{
            first = el.next;
            return el.value;
            /*
            while(el.next != last)
                el = el.next;
            int val = el.next.value;
            el.next = null;
            last = el;
            --size;
            return val;
            */
        }
    }

}


public class Main {
    public static void main(String[] args) {
        LinkedStack l = new LinkedStack();
        l.push(2);
        l.push(3);
        l.push(4);
        l.push(5);
        l.push(6);
        l.push(7);

        System.out.println(l.pop());
        System.out.println(l.pop());
        System.out.println(l.pop());
        System.out.println(l.pop());
        System.out.println(l.pop());
        System.out.println(l.pop());
    }
}
