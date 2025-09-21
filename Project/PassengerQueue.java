package Project;

public class PassengerQueue {
    private static class PassengerNode {
        //inner class represents a node in the queue(ll)
        //each node holds a passenger and address to the next node.
        Passenger passenger;  //passenger object stored in this node
        PassengerNode next;   //reference to the next node in the queue

        public PassengerNode(Passenger passenger) {
            this.passenger = passenger;
            this.next = null;
        }
    }
    private PassengerNode front;//the node in the queue which is responsible for deleting(at front)
    private PassengerNode rear;//the node in the queue which is responsible for insertion(at last)
    private int size;// size of the queue(number of passengers)

    public PassengerQueue() {
        front = null; //initializing front and rear to null initially
        rear = null;
        size = 0;//queue is initially empty
    }

    //ad a passenger to the end(rear) of the queue
    public void enqueue(Passenger passenger) {
        // a new node for the passenger
        PassengerNode newNode = new PassengerNode(passenger);
        if (rear == null) {
            front = rear = newNode; //if the queue is empty then front and rear point to the new node
        } else {
            rear.next = newNode; //link new node after rear
            rear = newNode; //move rear to the new node
        }
        size++;//incrementing size by 1
    }
    public Passenger dequeue() {//remove and return the passenger from front of queue
        if (isEmpty()) {
            return null;//if the queue is empty so nothing to dequeue
        }
        Passenger passenger = front.passenger;//saving passenger at the front in passenger variable to return at last
        front = front.next; //moving front to the next node
        if (front == null) {
            rear = null;//f the queue is now empty set rear to null as well
        }
        size--;
        return passenger;
    }
    public boolean isEmpty() {
        return size == 0;
    } // Check if the queue is empty

    // Method to remove a passenger from the queue by their ID
    public void removePassengerByID(int passengerID) {
        if (isEmpty()) {
            return;  // Queue is empty, cannot remove
        }
        // Handle removing the first node
        if (front.passenger.getPassengerID() == passengerID) {
            front = front.next;
            if (front == null) {
                rear = null;  // If the queue becomes empty, set rear to null
            }
            size--;
            return;
        }
        PassengerNode current = front;
        while (current.next != null) {
            if (current.next.passenger.getPassengerID() == passengerID) {
                current.next = current.next.next;  // Bypass the node to remove it
                if (current.next == null) {
                    rear = current;  // Update rear if we removed the last node
                }
                size--;
                return;
            }
            current = current.next;
        }
    }

    // Sort the queue using Heap Sort
    public void sortQueue() {
        if (isEmpty() || front == rear) {
            return; // No sorting needed for empty or single-element queue
        }

        // Convert linked list to array
        Passenger[] passengerArray = listPassengers();

        // Perform heap sort
        heapSort(passengerArray);

        // Rebuild the linked list from the sorted array
        front = null;
        rear = null;
        size = 0;
        for (Passenger passenger : passengerArray) {
            enqueue(passenger);
        }
    }

    // Heap Sort function
    private void heapSort(Passenger[] arr) {
        int n = arr.length;

        //to build a max heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // Extract elements from heap one by one
        for (int i = n - 1; i > 0; i--) {
            // Move the root (max element) to the end
            Passenger temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // Re-heapify the reduced heap
            heapify(arr, i, 0);
        }
    }

    // Heapify function to maintain the heap property
    private void heapify(Passenger[] arr, int n, int i) {
        int largest = i; // Initialize largest as root
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        // Check if left child is larger than root
        if (left < n && compareMembershipLevel(arr[left], arr[largest]) > 0) {
            largest = left;
        }

        // Check if right child is larger than the largest so far
        if (right < n && compareMembershipLevel(arr[right], arr[largest]) > 0) {
            largest = right;
        }

        // If the largest is not root, swap and continue heapifying
        if (largest != i) {
            Passenger swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Recursively heapify the affected subtree
            heapify(arr, n, largest);
        }
    }


    // Get the list of all passengers in the queue
    public Passenger[] listPassengers() {
        //  initializes an empty array of size equal to the queue size
        Passenger[] passengers = new Passenger[size];
        PassengerNode temp = front;
        int index = 0;
        while (temp != null) {
            passengers[index++] = temp.passenger;
            temp = temp.next;
        }
        return passengers;
    }

    public Passenger Binarysearch(int passengerID) {
        PassengerNode current = front;
        while (current != null) {
            // We use the BinarySearchTree search function to find the passenger by ID
            BinarySearchTree bst = new BinarySearchTree();
            bst.insert(current.passenger);  // Insert current passenger into BST
            Passenger passenger = bst.search(passengerID);  // Use the BST search function
            if (passenger != null) {
                return passenger;
            }
            current = current.next;  // Move to the next passenger in the queue
        }
        return null;  // If the passenger is not found in the entire queue
    }
    private int compareMembershipLevel(Passenger p1, Passenger p2) {
        //helper function to get priority value
        int priority1 = getMembershipPriority(p1.getMembershipLevel());
        int priority2 = getMembershipPriority(p2.getMembershipLevel());

        //compare priorities
        // Returns 1 if priority1 > priority2.
        //Returns -1 if priority1 < priority2.
        //Returns 0 if both priorities are equal.
        return Integer.compare(priority1, priority2);
    }

    private int getMembershipPriority(String membershipLevel) {
        switch (membershipLevel) {
            case "Platinum":
                return 4;
            case "Gold":
                return 3;
            case "Silver":
                return 2;
            case "None":
                return 1;
            default:
                return 0; // Default for undefined levels
        }
    }
//    Summary of Steps
//    Step	                    Array	                    Explanation
//      1	[Gold (3), Platinum (4), Silver (2), None (1)]	Initial array
//      2	[Platinum (4), Gold (3), Silver (2), None (1)]	Max heap built
//      3	[None (1), Gold (3), Silver (2), Platinum (4)]	Swap root with last, re-heapify
//      4	[Silver (2), None (1), Gold (3), Platinum (4)]	Swap root with last, re-heapify again
//      5	[None (1), Silver (2), Gold (3), Platinum (4)]	Final swap



}

