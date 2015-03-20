package GarageSim;

/**
 * Class to represent a LinkedList data structure of Node objects
 */
public class LinkedList {

    int length;                                 //size of LinkedList(LL)
    Node head;                                  //first node of LL
    Node iterator;                              //pointer to traverse LL

    public LinkedList() {

        this.head = null;                       //constructor for LL
        this.length = 0;

    }

    /**
     * Sets iterator to a "preHead" which is the Node pointing to the head of
     * the LL - this is used for traversing the list
     *
     */
    public void setIterator() {

        Node preHead = new Node(null);          //preHead before the first Node
        preHead.next = head;
        iterator = preHead;                     //iterator points to preHead

    }

    /**
     * Points iterator to next Node in LL or null if at last Node in the list
     * Precondition: Assumes iterator has been set using setIterator()!
     */
    public void next() {
        //points iterator to next Node
        iterator = iterator.next;

    }

    /**
     * Adds a node to the end of the LL
     *
     * @param n node to be added to the end of the LL
     */
    public void appendNode(Node n) {

        Node pointer;
        pointer = head;
        //if LL is empty, make Node n the first node...
        if (this.isEmpty()) {
            head = n;
        } //...otherwise, traverse the LL and find the last node...
        else {
            while (!(pointer.next == null)) {
                pointer = pointer.next;
            }
            //... and make the last node point to Node n
            pointer.next = n;
        }

        length++;
    }

    /**
     * Removes a specific node from the LL by using two pointers: "iterator" and
     * "pointer" -- pointer used as the leading pointer and iterator used as the
     * trailing pointer
     *
     * @param r node to be searched for and then removed
     * @return the node removed and retrieved from the LL
     */
    public Node removeNode(Node r) {

        setIterator();
        Node pointer = head;
        //while not at the end of the LL...
        while (!(pointer == null)) {
            //if the Node to be removed is found...
            if (pointer.equals(r)) {
                //...make head point to the next node if the first node
                //is being removed...
                if (pointer.equals(head)) {
                    head = head.next;
                }
                //...then remove node and return it 
                iterator.next = pointer.next;
                pointer.next = null;
                length--;
                return pointer;

            }
            //if node not found yet, then point to next Node and check again
            next();
            pointer = pointer.next;

        }

        return null;

    }

    /**
     * Finds whether the LL contains a specific object
     *
     * @param x object to be searched for in LL
     * @return true or false depending on whether the object was found
     */
    public boolean contains(Object x) {

        setIterator();
        next();
        //while not done searching the list...
        while (!(iterator == null)) {
            //...traverse the LL until the object (downcasted to Node) is found 
            if (iterator.equals((Node) x)) {
                return true;
            }
            next();
        }
        //return false if object not found
        return false;

    }

    /**
     * Removes head Node from LL
     *
     * @return removed node
     */

    public Node removeFirstNode() {
        //make head point to next Node after head and remove old head Node
        Node temp = head;
        head = head.next;
        temp.next = null;
        length--;

        return temp;

    }

    /**
     * Finds if the LL is empty or not
     *
     * @return true or false depending on whether the LL is empty or not
     */
    public boolean isEmpty() {

        return length == 0;                    //return whether the LL is
                                                //empty or not

    }

}
