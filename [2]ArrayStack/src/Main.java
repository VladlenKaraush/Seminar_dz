import java.util.NoSuchElementException;

class ArrayStack {
    private int[] arr;
    private int index;
    private int size;
    ArrayStack(){
        size = 10;
        arr = new int[size];
        index = -1;
    }

    private void resize(){
        this.size *= 2;
        int[] newArr = new int[size];
        for(int i = 0; i < arr.length; ++i){
            newArr[i] = arr[i];
        }
        this.arr = newArr;
    }

    public int pop(){
        if(index >= 0)
            return arr[index--];
        else
            throw new NoSuchElementException();
    }

    public int peak(){
        return arr[index];
    }

    public void push(int el){
        if(index < size - 1)
            arr[++index] = el;
        else {
            this.resize();
            this.push(el);
        }
    }
}





public class Main {
    public static void main(String[] args) {
        ArrayStack l = new ArrayStack();
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
