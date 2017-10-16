import java.util.NoSuchElementException;
import java.util.Scanner;

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
}


public class Main {
    private static int validate(){
        ArrayStack<Character> arrayStack = new ArrayStack<>();
        String s =  new Scanner(System.in).nextLine();
        for(char c: s.toCharArray()){
            switch (c){
                case '(':
                    arrayStack.push(c);
                    break;
                case '[':
                    arrayStack.push(c);
                    break;
                case '{':
                    arrayStack.push(c);
                    break;
                case ')':
                    try{
                        char ch = arrayStack.pop();
                        if(ch != '(')
                            return 1;
                    }
                    catch (NoSuchElementException e){
                        return 1;
                    }
                    break;
                case ']':
                    try{
                        char ch = arrayStack.pop();
                        if(ch != '[')
                            return 1;
                    }
                    catch (NoSuchElementException e){
                        return 1;
                    }
                    break;
                case '}':
                    try{
                        char ch = arrayStack.pop();
                        if(ch != '{')
                            return 1;
                    }
                    catch (NoSuchElementException e){
                        return 1;
                    }
                    break;
                    default:
                        return 1;

            }
        }
        if(arrayStack.getSize() > 0)
            return 1;
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(validate());
    }

}
