    package Project;

    // BST to manage passengers by their unique IDs
    public class BinarySearchTree {
        // Inner class to represent a node in the tree
        private static class PassengerNode {
            // to store passenger data stored in the node
            Passenger passenger;
            //reference variables
            PassengerNode left; // reference variable of left node
            PassengerNode right;// reference variable of right node
            public PassengerNode(Passenger passenger) {
                this.passenger = passenger;
                this.left = null; //initially we set root's left null
                this.right = null;  //initially we set root's right null
            }
        }
        // The root of the tree
        private PassengerNode root;
        // Constructor
        public BinarySearchTree() {
            root = null; //initially we set root null
        }
        // helper method to insert a passenger into the tree by data
        // it will call the recursive helper method to perform the insertion
        public void insert(Passenger passenger) {
            root = insertRecursively(root, passenger);
        }
        // recursive insert method by 3 cases
        private PassengerNode insertRecursively(PassengerNode node, Passenger passenger) {
            if (node == null) {
                return new PassengerNode(passenger);  //placing new passenger on root
            }// if data is smaller than  node we will insert at left of root
            if (passenger.getPassengerID() < node.passenger.getPassengerID()) {
                node.left = insertRecursively(node.left, passenger); //insert at left of root
            } // if data is bigger than node we will insert at right of root
            else if (passenger.getPassengerID() > node.passenger.getPassengerID()) {
                node.right = insertRecursively(node.right, passenger); //insert at right of root
            }
            return node; // Return the unchanged node
        }
        // helper method to search a passenger in tree by ID
        public Passenger search(int passengerID) {
            return searchRecursively(root, passengerID);// search at the root with id
        }

        // Recursive search method
        private Passenger searchRecursively(PassengerNode node, int passengerID) {
            if (node == null) {
                return null;  // it will return null if tree is empty
            }
    // If the ID is at root
            if (node.passenger.getPassengerID() == passengerID) {
                return node.passenger;  // Return the passenger if found
            }

            if (passengerID < node.passenger.getPassengerID()) {
                // If the ID is smaller thn search in the left subtree
                return searchRecursively(node.left, passengerID);  // Search in the left subtree
            } else {
                // If the ID is bigger thn search in the left subtree
                return searchRecursively(node.right, passengerID);  // Search in the right subtree
            }
        }

        // helper method to delete a passenger from the tree
        public void delete(int passengerID) {
            root = deleteRecursively(root, passengerID);
        }

        // Recursive delete method
        private PassengerNode deleteRecursively(PassengerNode node, int passengerID) {
            if (node == null) {
                return null;// Passenger not found, return null
            }
            // If the ID is smaller thn delete in the left subtree
            if (passengerID < node.passenger.getPassengerID()) {
                node.left = deleteRecursively(node.left, passengerID);
            }// If the ID is larger thn delete in the right subtree
            else if (passengerID > node.passenger.getPassengerID()) {
                node.right = deleteRecursively(node.right, passengerID);
            }// Node to delete found
            else {
                // Case 1: root have node has no children
                if (node.left == null && node.right == null) {
                    return null; // Remove the node by returning null
                }

                // Case 2: root have one child
                if (node.left == null) {
                    return node.right;// Return right child of root
                } else if (node.right == null) {
                    return node.left; // Return the left child of root
                }

                // Case 3: root have two children
                // Get the inorder successor (smallest in the right subtree)
                node.passenger = findMin(node.right).passenger;
                // Delete the inorder successor in the right subtree
                node.right = deleteRecursively(node.right, node.passenger.getPassengerID());
            }
    // Return the updated node
            return node;
        }
        // Helper method to find the minimum value node in a subtree
        private PassengerNode findMin(PassengerNode node) {
            while (node.left != null) {
                node = node.left;// Traverse the left(last) node
            }// ret the leftmost node
            return node;
        }
    }
