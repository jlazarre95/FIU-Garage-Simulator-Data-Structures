package GarageSim;

/**
 * Class to represent a Node object
 *
 * @param <E> data type of data stored in Node
 */
public class Node<E> {

    E data;                                 //data portion in the Node
    int extra;                              //extra place to store an int value
    Node next;                              //pointer to next Node

    Node(E data) {
        //constructs a Node object
        this.data = data;
        this.next = null;

    }

    /**
     * Finds whether or not two Nodes are equal to each other based on the data
     * contained in each Node (if x is not a Node, equals returns false)
     *
     * @param x object to be compared
     * @return true or false based on the equality or inequality of the two
     * objects
     */
    @Override
    public boolean equals(Object x) {
        //if x is not a Node, THIS and x are not equal
        if (!(x instanceof Node)) {

            return false;
            //otherwise, if the x is a Node, THIS and x are only equal if
            //they contain the same data
        } else {
            Node n2 = (Node) x;
            return this.data.equals(n2.data);
        }

    }

}
