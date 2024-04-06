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

            // if element breaks order, then set isSorted to false
//            if (isSorted) {
//                if ( curr.getData().compareTo(element) > 0 ) { // if the index 3 is bigger than index 4,
//                    isSorted = false; // the sort is broken
//                }
//            }
            isSorted = false;
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
            Node<T> addedNode = new Node<T>(element, currNode); // new node = (element, null)

            prevNode.setNext(addedNode);

            lengthOfList++;

            // if element breaks order, then set isSorted to false
            checkIsSorted();
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
        if (element == null) {
            return -1;
        }

        if (isSorted) { // If isSorted is true, uses the ordering of the list to increase the efficiency of the search.
            for (int i = 0; i < lengthOfList-1; i++) {
                if ( this.get(i).compareTo(element) > 0 ) { // if our list is at F, but looking for C (in sorted list),
                    return -1; // then we must've missed the element
                }
                if ( this.get(i).compareTo(element) == 0 ) {
                    return i;
                }
            }
            return -1; // nothing found
        } else { // isSorted == false
            Node<T> firstNode = dummyNode.getNext(); // use curr to check and compare all nodes in your list
            for (int i = 0; i < lengthOfList; i++) { // go through ALL nodes in your list
                if (firstNode.getData().equals(element)) { // if the elements are equal, return index
                    return i;
                }
                firstNode = firstNode.getNext();
            }
            return -1; // If element not found in the list, return -1.
        }
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
        checkIsSorted();
        return currNode.getData(); // return removed element

    }

    @Override
    public void removeDuplicates() {

    }

    @Override
    public void reverse() {
        Node<T> A = dummyNode.getNext();
        Node<T> P =  dummyNode.getNext();
        Node<T> B = null;
        while (P.getNext() != null){
            A = P.getNext();
            P.setNext(B);
            B = P;
            P = A;
        }
        P.setNext(B);
        dummyNode.setNext(A);

    }

    @Override
    public void exclusiveOr(List<T> otherList) {

    }

    @Override
    public T getMin() {
        // TODO: if it's already sorted, get first value after dummy
        // go thru all values and find least
        if (lengthOfList == 0) // if the list is empty
            return null;
        else if (lengthOfList == 1)
            return dummyNode.getNext().getData();
        else{
            if (! isSorted){
                sort();
                return dummyNode.getNext().getData();}
            else return dummyNode.getNext().getData();
        }
    }

    @Override
    public T getMax() {
        // TODO: optimization is knowing if it's sorted, you can link through to last value without stopping
        //  else go through all the values and find the largest (compare to)
        if (lengthOfList == 0) // if the list is empty
            return null;
        else if (lengthOfList == 1)
            return dummyNode.getNext().getData();
        else {
            if (! isSorted) {
                sort();
                reverse();
                return dummyNode.getNext().getData();
            }
            else {
                reverse();
                return dummyNode.getNext().getData();
            }

        }
    }

    @Override
    public boolean isSorted() {
        return isSorted;
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

    public void checkIsSorted(){ // helper method: check if the list is sorted
        if (lengthOfList == 0 || lengthOfList == -1){
            isSorted = true;
        }
        else{
            Node<T> ptrA = dummyNode.getNext();
            Node<T> ptrB = dummyNode.getNext().getNext();
            while (ptrB != null){
                if (ptrA.getData().compareTo(ptrB.getData()) > 0){
                    isSorted = false;
                    return;
                }
                ptrA = ptrA.getNext();
                ptrB = ptrB.getNext();
            }
            isSorted = true;
        }
    }

}
