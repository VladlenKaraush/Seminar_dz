class CyclicArrayQueue<T> {

    private static final int capacity = 3;
    private T[] Q;
    private int N; // capacity
    private int f = 0;
    private int r = 0;
    private int count = 0;


    public CyclicArrayQueue(){
        this(capacity);
    }

    public CyclicArrayQueue(int capacity){
        N = capacity;
        Q = (T[]) new Object[N];
    }

    public int size() {
        if(r > f)
            return r - f;
        return N - f + r;
    }

    public boolean isEmpty() {
        return r == f;
    }

    public boolean isFull() {
        int diff = r - f;
        return diff == -1 || diff == (N - 1);
    }

    public void expandCapacity()
    {
        T[] larger = (T[])(new Object[Q.length *2]);

        for(int scan=0; scan < count; scan++)
        {
            larger[scan] = Q[f];
            f=(f+1) % Q.length;
        }

        f = 0;
        r = count;
        Q = larger;
        N = Q.length;
    }

    public void enqueue(T obj){
        if(isFull()){
            expandCapacity();
        }
        Q[r] = obj;
        r = (r + 1) % N;
        count++;
    }

    public T dequeue(){
        T item = null;
        if(isEmpty()){
            System.out.println("Queue is empty");
        }else{
            item = Q[f];
            Q[f] = null;
            f = (f + 1) % N;
            count--;
        }
        return item;
    }

}


public class Main {
    public static void main(String[] args) {
        CyclicArrayQueue<String> q = new CyclicArrayQueue<>();
        q.enqueue("Q");
        q.enqueue("w");
        q.enqueue("e");
        q.enqueue("r");
        q.enqueue("t");
        q.enqueue("y");
        q.enqueue("u");
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());


    }
}
