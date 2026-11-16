/**
 * Node.java
 * Your Name:        Hatice Mevlide Alpaslan
 * Your Student ID:  240446012
 */

public class Node {

    String customerName;
    Node next;

    public Node(String customerName) {

        this.customerName = customerName;

        this.next = null;
    }

    public static class Queue {

        private Node front;
        private Node rear;
        private int size;

        public Queue() {

            front = null;
            rear = null;
            size = 0;
        }

        public void enqueue(String customerName) {

            Node newNode = new Node(customerName);

            if (isEmpty()) {

                front = newNode;
                rear = newNode;

            } else {

                rear.next = newNode;
                rear = newNode;
            }

            size++;
        }

        public String dequeue() {

            if (isEmpty()) {
                return null;
            }

            String removedCustomer = front.customerName;

            front = front.next;

            if (front == null) {
                rear = null;
            }

            size--;

            return removedCustomer;
        }

        public boolean isEmpty() {
            return front == null;
        }

        public int size() {
            return size;
        }

        public void display() {

            if (isEmpty()) {
                System.out.println("Empty");
                return;
            }

            Node current = front;

            while (current != null) {

                System.out.print(current.customerName);

                if (current.next != null) {
                    System.out.print(" -> ");
                }

                current = current.next;
            }

            System.out.println();
        }
    }

    public static class Bank {

        private String[] tellers;
        private Queue waitingQueue;

        public Bank() {

            tellers = new String[3];

            // All tellers are initially available
            for (int i = 0; i < tellers.length; i++) {
                tellers[i] = null;
            }

            waitingQueue = new Queue();
        }

        public void arrive(String customerName) {

            System.out.println("\nCustomer " + customerName + " arrived.");

            // Search for an available teller
            for (int i = 0; i < tellers.length; i++) {

                if (tellers[i] == null) {

                    tellers[i] = customerName;

                    System.out.println(
                            customerName + " is assigned to Teller " + (i + 1) + "."
                    );

                    return;
                }
            }

            // All tellers are busy
            System.out.println("All tellers are busy.");

            waitingQueue.enqueue(customerName);

            System.out.println(
                    customerName + " joins the waiting queue."
            );
        }

        public void completeTransaction(int tellerNumber) {

            // Check teller number
            if (tellerNumber < 1 || tellerNumber > 3) {

                System.out.println("Invalid teller number!");

                return;
            }

            int index = tellerNumber - 1;

            // Check if teller is available
            if (tellers[index] == null) {

                System.out.println(
                        "Teller " + tellerNumber + " is already available."
                );

                return;
            }

            // Complete transaction
            System.out.println(
                    "\nTeller " + tellerNumber +
                            " completed " + tellers[index] + "'s transaction."
            );

            // Make teller available
            tellers[index] = null;

            // Assign next waiting customer
            assignNextCustomer(tellerNumber);
        }

        public void assignNextCustomer(int tellerNumber) {

            if (!waitingQueue.isEmpty()) {

                String nextCustomer = waitingQueue.dequeue();

                tellers[tellerNumber - 1] = nextCustomer;

                System.out.println(
                        nextCustomer + " is assigned to Teller " + tellerNumber + "."
                );
            }
        }

        public void displayStatus() {

            System.out.println("\n---------- BANK STATUS ----------");

            // Display tellers
            for (int i = 0; i < tellers.length; i++) {

                System.out.print("Teller " + (i + 1) + ": ");

                if (tellers[i] == null) {
                    System.out.println("Available");
                } else {
                    System.out.println(tellers[i]);
                }
            }

            // Display waiting queue
            System.out.print("Waiting Node.Queue: ");

            waitingQueue.display();

            System.out.println("---------------------------------");
        }
    }

    public static class BankDemo {

        public static void main(String[] args) {

            // Create Node.Bank object
            Bank bank = new Bank();

            // Customer arrivals
            bank.arrive("Ali");
            bank.arrive("Ayse");
            bank.arrive("Mehmet");
            bank.arrive("Zeynep");
            bank.arrive("Can");

            // Display current status
            bank.displayStatus();

            // Complete Teller 2 transaction
            bank.completeTransaction(2);

            // Display updated status
            bank.displayStatus();

            // Complete Teller 1 transaction
            bank.completeTransaction(1);

            // Display updated status
            bank.displayStatus();
        }
    }
}