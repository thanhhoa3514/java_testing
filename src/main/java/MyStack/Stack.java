package MyStack;

import java.util.ArrayList;

public class Stack {

    private ArrayList<Integer> data;

    public Stack()
    {
        data=new ArrayList<>();
    }

    public void push(Integer value){
        data.addFirst(value);
    }
    public Integer pop()
    {
        if(data.isEmpty())
            throw new ArrayIndexOutOfBoundsException("Stack is empty");
        Integer v=data.getFirst();
        data.removeFirst();
        return v;
    }

    public int getSize()
    {
        return data.size();
    }
    public String toString()
    {
        return data.toString();
    }
    public ArrayList<Integer> getData()
    {
        return data;
    }
}
