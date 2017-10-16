import java.util.NoSuchElementException;

class ArrayStack<T> {
    private T[] arr;
    private int index;
    private int size;
    ArrayStack(){
        size = 10;
        arr = (T[]) new Object[size];
        index = -1;
    }

    private void resize(){
        this.size *= 2;
        @SuppressWarnings("unchecked")
        T[] newArr = (T[]) new Object[size];
        for(int i = 0; i < arr.length; ++i){
            newArr[i] = arr[i];
        }
        this.arr = newArr;
    }


    T pop(){
        if(index >= 0)
            return arr[index--];
        else
            throw new NoSuchElementException();
    }

    public T peak(){
        return arr[index];
    }

    void push(T el){
        if(index < size - 1)
            arr[++index] = el;
        else {
            this.resize();
            this.push(el);
        }
    }
    int getSize(){
        return size;
    }

    boolean isEmpty(){
        return index == -1;
    }
}

class TwoStackQueue<T>{
    private ArrayStack<T> firstStack;
    private ArrayStack<T> secondStack;
    TwoStackQueue(){
        firstStack = new ArrayStack<>();
        secondStack = new ArrayStack<>();
    }

    void push(T el){
        while(!firstStack.isEmpty()){
            secondStack.push(firstStack.pop());
        }
        firstStack.push(el);
        while(!secondStack.isEmpty()){
            firstStack.push(secondStack.pop());
        }
    }

    T pop(){
        return firstStack.pop();
    }

}

public class Main {
    public static void main(String[] args) {

        TwoStackQueue<String> q = new TwoStackQueue<>();
        q.push("qwe");
        q.push("ASD");
        System.out.println(q.pop());
        System.out.println(q.pop());
    }
}
