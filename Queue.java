package src.Mazingga;

//Class Queue : berfungsi untuk implementasi dari ADT
//Queue -> berfungsi untuk menyimpan data serta koordinat yang dilewati pemain
class Queue {
    Node head;
    Node tail;
    int size = 0;

    //isEmpty : method non-void (boolean) untuk melakukan pengecekan apakah queue kosong atau tidak
    boolean isEmpty (){
        return size == 0;
    }

    //enqueue : method void untuk menambahkan data ke queue
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

    //dequeue : method non-void (Node) untuk mengambil data dari queue
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

    //dequeue : method non-void (int) untuk mendapatkan berapa jumlah node atau size dari queue
    int getSize (){
        return size;
    }
}
