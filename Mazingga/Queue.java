package src.Mazingga;

class Queue {
    Node head;
    Node tail;
    int size = 0;

    boolean isEmpty (){
        return size == 0;
    }

    void enqueue (String data){
        Node new_node = new Node(data);
        if (isEmpty()){
            head = tail = new_node;
        }else {
            tail.next = new_node;
            tail = new_node;
        }
        size++;
    }

    String dequeue (){
        Node temp;
        if (isEmpty()){
            return "";
        }else {
            temp = head;
            head.next = head;
        }
        return temp.data;
    }
    int getSize (){
        return size;
    }
}
