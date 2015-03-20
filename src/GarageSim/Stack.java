package GarageSim;

/**
 * A class used to represent a Stack with Array implementation
 */
public class Stack {

    Object items[];                             //array of items in Stack
    int top;                                    //index of top item

    public Stack() {

        items = new Object[10];                 //constructor for Stack
        top = -1;

    }

    /**
     * Finds out whether or not the "Stack" is full
     *
     * @return true or false depending on the contents of the Stack
     */
    public boolean isFull() {

        return top == items.length - 1;         //returns whether the Stack
                                                //is full or not
    }

    /**
     * Finds out whether or not the Stack is empty
     *
     * @return true or false depending on whether there are objects in the Stack
     * or not
     */
    public boolean isEmpty() {

        return top == -1;                           //returns whether the Stack
                                                    //is empty or not

    }

    private void resize() {
        //makes a "Stack" double the size of the Stack to be resized
        Object copy[] = new Object[this.items.length * 2];
        //copies contents of old Stack to new "Stack"
        System.arraycopy(this.items, 0, copy, 0, this.items.length);
        //points items to the resized "Stack"
        items = copy;

    }

    /**
     * Adds an object to the top of the Stack
     *
     * @param x object to be added
     */
    public void push(Object x) {
        //if Stack is full, double the size of the Stack
        if (this.isFull()) {
            this.resize();
        }
        //add object to top of stack
        items[++top] = x;

    }

    /**
     * Removes top object
     *
     * @return object removed from the top
     */
    public Object pop() {
        //removes top object from the stack
        Object obj = items[top];
        top--;
        return obj;

    }

    /**
     * Takes a look at the top object without actually removing it from the
     * Stack
     *
     * @return top object of Stack
     */
    public Object peek() {
        //peeks at top of Stack
        return items[top];

    }

}
