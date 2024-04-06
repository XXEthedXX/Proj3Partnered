// Written By Ethan Balikalaba (balik012) and

public class LinkedList<T extends Comparable<T>> implements List<T> {
    private boolean isSorted = true; // an empty list is sorted
    private Node<T> dummyNode; // of the list
    private int lengthOfList;


    public LinkedList() { // ONLY constructor, should initialize list to empty
        // (no data, point to 1st) --> (null,null)
        // (dummyNode) --> (firstNode)
        // Node<T> firstNode = new Node<T>(null,null);
        dummyNode = new Node<T>(null, null);
        lengthOfList = 0;
    }


    @Override
    public boolean add(T element) {
        if (element == null) { // if null, then return false
            return false;
        } else { // otherwise, keep looking until empty 'next' and create node there
            Node<T> curr = dummyNode;
            while (curr.getNext() != null) {
                curr = curr.getNext();
            }

            Node<T> addToList = new Node<>(element,null);
            curr.setNext(addToList);
            lengthOfList++; // increment length of list
            // TODO: if element breaks order, then set isSorted to false
            isSorted = false; // TODO: broken for now
            return true;

        }
    }

    @Override
    public boolean add(int index, T element) { // add element at selected index assuming,
        if (element != null && index >= 0 && index < lengthOfList) {// element is not null, and is within bounds
            Node<T> prevNode = dummyNode; // index to replace
            for (int iterations = 0; iterations < index; iterations++) { // loop through to GET current / node to replace
                prevNode = prevNode.getNext();
            }

            Node<T> currNode = prevNode.getNext(); // ephemeral
//            Node<T> restOfNodes = new Node<T>(currNode.getData(),currNode.getNext()); // unchanging
            Node<T> addedNode = new Node<T>(element, currNode); // new node = (element, null)

            prevNode.setNext(addedNode);

            lengthOfList++;
            // TODO: if element breaks order, then set isSorted to false
            isSorted = false; // TODO: broken for now
            return true;
        }
        return false; // else it's null, or out of bounds
    }

    @Override
    public void clear() { // remove all elements and update isSorted to true
        dummyNode.setNext(null);
        lengthOfList = 0;
        isSorted = true;
    }

    @Override
    public T get(int index) { // return element at a given index
        // check to see if index is within bounds, else return null
        if (index < 0 || index >= lengthOfList) {
            return null;
        }

        //        System.out.println("Index: "+index);
        //        System.out.println("SizeOfList: "+lengthOfList);
        Node<T> curr = dummyNode;

        for (int iterations = 0; iterations < index+1; iterations++) {
            curr = curr.getNext(); // loop through until you find the node
        }
        return curr.getData();
    }

    @Override
    public int indexOf(T element) { // return 1st index of element in list.
        if (element == null) { return -1; }
        if (isSorted) { // If isSorted is true, uses the ordering of the list to increase the efficiency of the search.
            // TODO: if sorted, and go past value, then obv not in and can exit/return early
            for (int i = 0; i < lengthOfList-1; i++) {
                if ( this.get(i).compareTo(element) > 0 ) { // if f, but looking for c (in sorted list),
                    return -1; // then missed your (element) exit
                } else if ( this.get(i).compareTo(element) == 0 ) {
                    return i;
                }
            }
            // if you go through whole list and cannot find element, return -1
            return -1;
        } else { // notSorted
            for (int i = 0; i < lengthOfList-1; i++) {
                if ( this.get(i).compareTo(element) == 0 ) {
                    return i;
                }
            }
        }


        // If element not found in the list, return -1.
        // If isSorted is true, uses the ordering of the list to increase the efficiency of the search.
        return 0;
    }

    @Override
    public boolean isEmpty() { // Return true if the list is empty and false otherwise.
        if (dummyNode.getNext() == null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int size() { // if not null, then it counts as part of length of list
        return lengthOfList;
    }

    @Override
    public void sort() { // Sort in Asc (S to L) using bubble sort
        if (!isSorted) { // If list isn't sorted than fix it
            // Bubble Sort!!! 🧼🧼🧼
            for (int i = 0; i < lengthOfList - 1; i++) { // for length of list,

                for (int j = 0; j < lengthOfList - i - 1; j++) { // look at 1st index, and find biggest, throw at last index

                    if ( this.get(j).compareTo(this.get(j+1)) > 0 ) { // curr is bigger than next, || given (C,A,B) at (0,1,2)

                        Node<T> curr = dummyNode; // node code
                        for (int iterations = 0; iterations < j+1; iterations++) {
                            curr = curr.getNext(); // loop through until you find the node
                        }
                        // swap places by changing data
                        T placeholder = curr.getData(); // hold biggest data
                        Node<T> nextNode = curr.getNext(); // find next
                        curr.setData(nextNode.getData()); // j becomes j+1
                        nextNode.setData(placeholder); // j+1 becomes old j (aka biggest aka placeholder)

                    } // otherwise continue

                }

            }
            isSorted = true; // update isSorted to reflect new sorted linked list
        }
        // optimized by checking if list is sorted
        // no need to do anything
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index >= lengthOfList) { // if index is OOB, return null
            return null;
        } // otherwise, remove node at selected index and return it

        Node<T> prevNode = dummyNode; // index to replace
        for (int iterations = 0; iterations < index; iterations++) { // loop through to GET current / node to replace
            prevNode = prevNode.getNext();
        }

        Node<T> currNode = prevNode.getNext(); // (1) --> (2) --> (3) where prevNode is 1,
        Node<T> nextNode = currNode.getNext(); // and nextNode is 3
        prevNode.setNext(nextNode); // set 1's next node to 3 and it's descendants

        // TODO: MUST is list still sorted after removal, update isSorted accordingly.
        lengthOfList--;
        return currNode.getData(); // return removed element

    }

    @Override
    public void removeDuplicates() {

    }

    @Override
    public void reverse() {

    }

    @Override
    public void exclusiveOr(List<T> otherList) {
        Node
    }

    @Override
    public T getMin() {
        // TODO: if it's already sorted, get first value after dummy
        // go thru all values and find least
        return null;
    }

    @Override
    public T getMax() {
        // TODO: optimization is knowing if it's sorted, you can link through to last value without stopping
        //  else go through all the values and find the largest (compare to)
        return null;
    }

    @Override
    public boolean isSorted() {
        return false;
    }

    @Override
    public String toString() {
        // for length of list, iterate through everything, add to list, and print it all
        Node<T> curr = dummyNode;

        String myString = "";
        for (int i=0; i < lengthOfList; i++) { // loop thru to add stuff to the list

            for (int iterations = 0; iterations < i+1; iterations++) {
                curr = curr.getNext(); // loop through until you find the curr node
            }

            myString = myString + "Index: " + i + "|| ";
            myString += "Data: " + curr.getData() + "\n";
        }

        return myString;
    }

}
