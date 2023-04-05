package src.Mazingga;

class Node {
    Node next;
    String data;
    Coordinate coordinate;
    Node (String data, Coordinate coordinate){
        this.data = data;
        this.coordinate = coordinate;
    }
}