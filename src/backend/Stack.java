package backend;

/**
 * @author ROXAS, Johan Rickardo & LACANILAO, Marvin Patrick
 * A stack implementation that implements the StackInterface.
 * @param <T> The type of elements stored in the stack.
 */
public class Stack<T> implements StackInterface<T> {
    /**
     * The last and top element of the stack.
     */
    private Node<T> top;

    /**
     * The number of elements stored in the stack.
     */
    private int count;

    /**
     * Constructs a Stack with null values.
     */
    public Stack() {
        top = null;
        count = 0;
    } // end of Stack default constructor

    /**
     * Inserts a given Node in the Stack and sets itself as the top node.
     * @param item given data to be inserted.
     */
    @Override
    public void push(T item) {
        Node<T> node = new Node<>(item);
        if (!isEmpty()) {
            node.setNext(top);
        } // end of if-else
        top = node;
        count++;
    } // end of push method

    /**
     * Removes an element from the Stack and returns the removed element.
     * @return The element being removed.
     * @throws StackUnderflowException if stack is empty.
     */
    @Override
    public T pop() throws StackUnderflowException {
        T topElement = null;
        if (!isEmpty()) {
            topElement = top.getData();
            if (count == 1) {
                top = null;
            } else {
                top = top.getNext();
            } // end of if-else (count)
            count--;
        } else {
            throw new StackUnderflowException();
        } // end of if-else (isEmpty)
        return topElement;
    } // end of pop method

    /**
     * Returns the top element of the stack without deleting the element.
     * @return The data of the top element.
     * @throws StackUnderflowException if stack is empty.
     */
    @Override
    public T peek() throws StackUnderflowException {
        if (!isEmpty()) {
            return top.getData(); // returns if stack and top is not null
        } else {
            throw new StackUnderflowException(); // if stack is empty
        } // end of if-else (isEmpty)
        return null; // no element will return
    } // end of peek method

    /**
     * Represents the size or length of the stack using the stack's count.
     * @return Integer representation of the stack's size.
     */
    @Override
    public int size() {
        return this.count;
    } // end of size method

    /**
     * Checks the stack's size if it is empty or not.
     * @return true if the stack is empty, false if otherwise.
     */
    @Override
    public boolean isEmpty() {
        if (count == 0)
            return true;
        return false;
    } // end of isEmpty method

    /**
     * TODO: Documentation
     * @param operator1
     * @param operator2
     * @return True if operator1 has precedence over operator2, false if otherwise.
     */
    public boolean checkPrecedence(char operator1, char operator2) {
        boolean result = false;


        return result;
    } // end of checkPrecedence method

    /**
     * TODO: Documentation
     * @return
     */
    public String convertToPostfix() {

    } // end of convertToPostfix method
} // end of class Stack
