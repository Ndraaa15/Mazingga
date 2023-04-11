package src.Mazingga;

//Class Node yang berfungsi untuk Queue
class Node {
    Node next; //Untuk menunjuk ke pointer selanjutnya
    String data; //Menyimpan data "01"
    Coordinate coordinate; //Untuk menyimpan koordinat yang dilewati oleh pemain
    //Constructor
    Node (String data, Coordinate coordinate){
        this.data = data;
        this.coordinate = coordinate;
    }
}

//Class Node yang berfungsi untuk NodeMap (Isi dari array 2 dimensi MazeMap)
class NodeMap {
    String data; //Menyimpan data "XX" || "00" || "01"
    boolean value; //Menyimpan nilai apakah jalur bisa dilewati atau tidak true || false
    //Constructor
    public NodeMap(String data, boolean value) {
        this.data = data;
        this.value = value;
    }
}
