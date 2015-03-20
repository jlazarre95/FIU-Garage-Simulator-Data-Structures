package GarageSim;

/**
 * A class to represent an Output-Restricted Dequeue (shortened to "Deque" or
 * "DQ") using LinkedList implementation
 */
public class Deque {

    int length;                                 //size of Deque
    Node head;                                  //first node of Deque
    Node iterator;                              //pointer to traverse Deque

    public Deque() {

        this.head = null;                       //constructor for Deque
        this.length = 0;

    }

    /**
     * Sets iterator to a "preHead" which is the Node pointing to the head of
     * the DQ - this is used for traversing the DQ
     *
     */
    public void setIterator() {

        Node preHead = new Node(null);          //preHead before the first Node
        preHead.next = head;
        iterator = preHead;                     //iterator points to preHead

    }

    /**
     * Points iterator to next Node in DQ or null if at last Node in the DQ
     * Precondition: Assumes iterator has been set using setIterator()!
     */
    public void next() {
        //points iterator to next Node
        iterator = iterator.next;

    }

    /**
     * Append a Node to the North end of the DQ
     *
     * @param f node to be added
     */
    public void appendFirstNode(Node f) {
        //makes f Node the head of the DQ
        Node temp = head;
        head = f;
        f.next = temp;
        setIterator();
        length++;

    }

    /**
     * Appends a Node to the South end of the DQ
     *
     * @param e node to be added
     */
    public void appendEndNode(Node e) {

        Node pointer;
        pointer = head;
        //if Deque is empty, make Node n the first node...
        if (this.isEmpty()) {
            head = e;
        } //...otherwise, traverse the Deque and find the last node...
        else {
            while (!(pointer.next == null)) {
                pointer = pointer.next;
            }
            //... and make the last node point to Node n
            pointer.next = e;
        }

        length++;
    }

    /**
     * Removes Node from North end of DQ
     *
     * @return removed node
     */
    public Node removeNode() {
        //the Node pointed to by head.next now becomes the new Node
        Node temp = head;
        head = head.next;
        temp.next = null;
        length--;
        return temp;

    }

    /**
     * Finds whether the DQ contains a specific object
     *
     * @param x object to be searched for in DQ
     * @return true or false depending on whether the object was found
     */
    public boolean contains(Object x) {

        setIterator();
        next();
        //while not done searching the DQ...
        while (!(iterator == null)) {
            //...traverse the DQ until the object (downcasted to Node) is found 
            if (iterator.equals((Node) x)) {
                return true;
            }
            next();
        }
        //return false if object not found
        return false;

    }

    /**
     * Finds out whether or not the DQ is empty
     *
     * @return true or false depending on whether there are objects in the DQ or
     * not
     */
    public boolean isEmpty() {

        return head == null;                    //return whether the Deque is
                                                //empty or not

    }

}
