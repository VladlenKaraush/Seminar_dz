import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

class Queue<T>{
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
    Queue(){
        first = null;
        last = null;
        size = 0;
    }

    int getSize(){ return this.size;}

    boolean isEmpty(){
        return size == 0;
    }

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

public class Main {
    static int calc(int a, int b, String s){
        switch (s){
            case "+":
                return a + b;
            case "-":
                return b - a;
            case "*":
                return a * b;
            case "/":
                return b / a;
        }
        return -1;
    }

    static int priority(char c){
        switch(c){
            case '*':
                return 3;
            case '/':
                return 3;
            case '+':
                return 2;
            case '-':
                return 2;
            case '(':
                return 1;
        }
        return 0;
    }

    static String spaces(String s){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length(); ++i){
            if(s.charAt(i) == ' ')
                continue;
            if(Character.isDigit(s.charAt(i))){
                while(i< s.length() && Character.isDigit(s.charAt(i))){
                    sb.append(s.charAt(i));
                    ++i;
                }
                --i;
                sb.append(" ");
            }
            else{
                sb.append(s.charAt(i)).append(" ");
            }


        }
        return sb.toString();
    }

    static int solve(String ss){
        String s = spaces(ss);
        Queue<String> q = new Queue<>();
        ArrayStack<Character> stack = new ArrayStack<>();
        String[] tokens = s.split(" ");
        for(int i = 0; i < tokens.length; ++i){
            tokens[i] = tokens[i].trim();
        }
        for(String str: tokens){
            if(Character.isDigit(str.charAt(0))) {
                q.push(str);
            }
            else if(str.charAt(0) == '('){
                    stack.push(str.charAt(0));
                }
            else if(str.charAt(0) == ')'){
                Character top = stack.pop();
                while(top != '('){
                    q.push(String.valueOf(top));
                    top = stack.pop();
                }
            }
            else{
                while(!stack.isEmpty() && priority(stack.peak()) >= priority(str.charAt(0))){
                    q.push(String.valueOf(stack.pop()));
                }
                stack.push(str.charAt(0));
            }
        }

        while(!stack.isEmpty()){
            q.push(String.valueOf(stack.pop()));
        }


        StringBuilder sb = new StringBuilder();
        while(!q.isEmpty()){
            sb.append(q.pop()).append(" ");
        }

        ArrayStack<Integer> st = new ArrayStack<>();
        String[] el = sb.toString().split(" ");
        for(String elem:el){
            try{
                int num = Integer.parseInt(elem);
                st.push(num);
            }
            catch(NumberFormatException e){
                st.push(calc(st.pop(), st.pop(), elem));
            }
        }


        return st.pop();
    }

    public static void main(String[] args) {
        String s = new Scanner(System.in).nextLine();
        System.out.println(solve(s));
    }
}
