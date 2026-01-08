import java.util.Scanner;

class Stack {
    private int size;
    private int stack[];
    private int top;

    public Stack(int size) {
        this.size = size;
        stack = new int[size];
        top = -1;
    }

    void push(int x) {
        if (isFull()) {
            System.out.println("Stack is in overflow");
            return;
        }
        stack[++top] = x;
    }

    int pop() {
        if (isEmpty()) {
            System.out.println("Stack is in underflow");
            return -1;
        }
        int n = stack[top];
        stack[top--] = -1;
        return n;
    }

    int peek() {
        if (isEmpty()) {
            System.out.println("Stack is in underflow");
            return -1;
        }
        int n = stack[top];
        return n;
    }

    boolean isEmpty() {
        return top == -1 ? true : false;
    }

    boolean isFull() {
        return top == size - 1 ? true : false;
    }

    void printElements() {
        if (top >= 0) {
            for (int i = 0; i <= top; i++) {
                System.out.print(stack[i]);
                if (i != top) {
                    System.out.print(",");
                }
            }
            System.out.println();
        }
    }
}


public class Main {
    public static void main(String[] args) {

//      Stack By Me
        Stack stack = new Stack(5);
        Scanner sc = new Scanner(System.in);

        // PUSH
        for (int i = 0; i < 5; i++) {
            System.out.print("Enter element to push: ");
            stack.push(sc.nextInt());
            stack.printElements();
        }

        // PEEK
        System.out.println("Top element: " + stack.peek());

        // POP
        for (int i = 0; i < 5; i++) {
            System.out.println("Popped: " + stack.pop());
            stack.printElements();
        }

////      Queue By Me
//
//        Queue queue = new Queue(5);
//        Scanner scan = new Scanner(System.in);
//
//        // INSERT
//        for (int i = 0; i < 5; i++) {
//            System.out.print("Enter element to insert: ");
//            queue.insert(scan.nextInt());
//            queue.print();
//        }
//
//        // DELETE
//        for (int i = 0; i < 5; i++) {
//            System.out.println("Deleted: " + queue.delete());
//            queue.print();
//        }

    }
}

class Queue {
    private int size;
    private int queue[];
    private int front = -1;
    private int rear = -1;
    public Queue(int size) {
        this.size = size;
        queue = new int[size];
        front = -1;
        rear = -1;
    }

    boolean isFull() {
        return rear == size - 1 ? true : false;
    }

    boolean isEmpty() {
        return front == -1 || front > rear ? true : false;
    }

    void insert(int x) {
        if (isFull()) {
            System.out.println("Queue is in overflow");
        } else {
            if (front == -1) {
                front = 0;
            }
            queue[++rear] = x;
        }
    }

    int delete() {
        if (isEmpty()) {
            System.out.println("Queue is in underflow");
            return -1;
        }
        int n = queue[front];
        queue[front++] = -1;
        return n;
    }

    void print(){
        System.out.println("front:"+front);
        System.out.println("rear:"+rear);
    }
}