import java.util.Scanner;

class myQueue {

    private int[] arr;
    private int front;
    private int size;
    private int capacity;

    // constructor
    public myQueue(int cap) {
        capacity = cap;
        arr = new int[capacity];
        front = 0;
        size = 0;
    }

    // isEmpty
    public boolean isEmpty() {
        return size == 0;
    }

    // isFull
    public boolean isFull() {
        return size == capacity;
    }

    // Enqueue
    public void enqueue(int data) {

        if (isFull()) {
            System.out.println("Queue is FULL");
            return;
        }

        int rear = (front + size) % capacity;
        arr[rear] = data;
        size++;

        System.out.println(data + " inserted");
    }

    // Dequeue
    public int dequeue() {

        if (isEmpty()) {
            System.out.println("Queue is EMPTY");
            return -1;
        }

        int removed = arr[front];
        arr[front] = 0; // blank show karne ke liye
        front = (front + 1) % capacity;
        size--;

        return removed;
    }

    // Peek
    public int peek() {

        if (isEmpty()) {
            System.out.println("Queue is EMPTY");
            return -1;
        }

        return arr[front];
    }

    // ⭐ Display Queue (NEW OPERATION)
    public void display() {

        System.out.println("\n--- Queue Status ---");
        System.out.println("Capacity : " + capacity);
        System.out.println("Size     : " + size);
        System.out.print("Queue    : [ ");

        for (int i = 0; i < capacity; i++) {
            System.out.print(arr[i] + " ");
        }

        System.out.println("]");
        System.out.println("Front index : " + front);
    }
}

public class CircularQueue {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter queue capacity: ");
        int cap = sc.nextInt();

        myQueue q = new myQueue(cap);
        int choice;

        do {
            System.out.println("\n--- Circular Queue Menu ---");
            System.out.println("1. Enqueue");
            System.out.println("2. Dequeue");
            System.out.println("3. Peek");
            System.out.println("4. Is Empty");
            System.out.println("5. Is Full");
            System.out.println("6. Display Queue");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter value to insert: ");
                    q.enqueue(sc.nextInt());
                    break;

                case 2:
                    int removed = q.dequeue();
                    if (removed != -1)
                        System.out.println("Removed: " + removed);
                    break;

                case 3:
                    int front = q.peek();
                    if (front != -1)
                        System.out.println("Front element: " + front);
                    break;

                case 4:
                    System.out.println(q.isEmpty() ? "Queue is EMPTY" : "Queue is NOT EMPTY");
                    break;

                case 5:
                    System.out.println(q.isFull() ? "Queue is FULL" : "Queue is NOT FULL");
                    break;

                case 6:
                    q.display();
                    break;

                case 0:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice");
            }

        } while (choice != 0);

        sc.close();
    }
}
