package src.Mazingga;

class Queue {
    Node head;
    Node tail;
    int size = 0;

    boolean isEmpty (){
        return size == 0;
    }

    void enqueue (String data, Coordinate coordinate){
        Node new_node = new Node(data, coordinate);
        if (isEmpty()){
            head = tail = new_node;
        }else {
            tail.next = new_node;
            tail = new_node;
        }
        size++;
    }

    Node dequeue (){
        Node temp;
        if (isEmpty()){
            return null;
        }else {
            temp = head;
            head = head.next;
        }
        return temp;
    }


    int getSize (){
        return size;
    }

    void  printQueue (){
        Node temp = head;
        while (temp != null){
            System.out.println(temp.coordinate.x() + " " + temp.coordinate.y() + " " + temp.data);
            temp = temp.next;
        }
    }
}
