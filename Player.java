package src.Mazingga;

//Record Coordinate : record yang berfungsi untuk menyimpan coordinate x dan y dari jalur yang dilewati pemain
//Kenapa record ? Memudahkan pembuatan struktur data sederhana dengan properti tetap dan imutabel.
record Coordinate(int x, int y) { }

//Class Player : class yang berfungsi untuk menyimpan data data dari pemain mulai dari mana dan maze mapnya
class Player {
    //name : variabel bertipe string yang berisi nama dari pemain
    private String name;
    //startCoordinate : array bertipe int yang berisi start atau koordinat awal dari pemain (x, y)
    private int [] startCoordinate;
    //endCoordinate : array bertipe int yang berisi finish atau koordinat akhir dari pemain (x, y)
    private int [] endCoordinate;
    //mazeMap : array 2 dimensi bertipe NodeMap yang merisi hasil penulusuran pemain dari maze yang disediakan
    private NodeMap [][] mazeMap;
    //movements : array bertipe string yang berisi pergerakan atau movement yang bisa dilakukan oleh pemain
    private String [] movements;
    //isFinish : variabel bertipe boolean yang beisi nilai true apabila pemain sudah sampai di finish dan false apabila tidak
    //default set = false
    private boolean isFinish = false;
    //path : struktur data Queue yang berfungsi untuk menyimpan data (urutan langkah) serta koordinat mana saja yang dilewati hingga mencapai finish
    private Queue path = new Queue();
    //step : variabel bertipa int yang berfungsi untuk menghitung jumlah langkah pemain
    int step = 0;


    //Constructor
    public Player(String name, NodeMap[][] mazeMap, String[] movements, int [] startCoordinate, int [] endCoordinate) {
        this.name = name;
        this.mazeMap = mazeMap;
        this.movements = movements;
        this.startCoordinate = startCoordinate;
        this.endCoordinate = endCoordinate;

        //mazeSolve -> method yang berfungsi untuk melakukan penulusuran maze map dengan menggunaakan movement pemain
        //dimulai dari start coordinat atau koordinat awal
        mazeSolve(startCoordinate[0], startCoordinate[1]);
    }

    //getName : method non-void (string) yang berfungsi untuk mengembalikan nama dari pemain
    public String getName() {
        return name;
    }

    //printMazeMap : method void yang berfungsi untuk mencetak maze yang telah ditelusuri oleh pemain
    //Serta pada method ini juga terdapat proses perubahan nilai dari koordinat pada maze
    //Dimana diganti dengan data yang terdapat di queue di koordinat yang sesuai
    public void printMazeMap (){
        int counter = 0;

        while (counter < path.getSize()){
            Node temp = path.dequeue();
            mazeMap[temp.coordinate.x()][temp.coordinate.y()].data = temp.data;
            counter++;
        }

        for (int i = 0; i < mazeMap.length; i++) {
            for (int j = 0; j < mazeMap.length; j++) {
                System.out.printf("[%s]", mazeMap[i][j].data);
            }
            System.out.println();
        }
    }

    public void mazeSolve (int x, int y){
        //If condition yang berfungsi untuk melakukan pengecekan apakah koordinat yang dilalui melebihi ukuran size atau kurang dari 0
        //Apabila benar maka akan di return (dikembalikan)
        if (x < 0 || x > mazeMap.length  - 1 || y < 0 || y > mazeMap.length - 1) {
            return;
        }

        //If condition yang berfungsi untuk melakukan pengeceakan apakah koordinat yang dilewati sudah di akhir koordinat (finish) dan pemain belum finish sebelumnya
        //Apabila iya maka :
        //-langkahnya akan bertambah
        //-Penamabahan element baru ke queue
        //-Membuat nilai isFinish yang awalnya false menjadi true
        if (x == endCoordinate[0] && y == endCoordinate[1] && !isFinish){
            step++;
            path.enqueue(Helper.formatNumber(step), new Coordinate(mazeMap.length - 1 - y, x));
            isFinish = true;
            return;
        }

        //If condition yang berfungsi untuk melakukan pengeceakan apakah koordinat yang dilewati sudah pernah dilewati atau tidak (dengan boolean) dan pemain belum finish
        //Apabila belum dilewati dan pemain belum finish maka :
        //-langkahnya akan bertambah
        //-Penamabahan element baru ke queue
        //-Membuat nilai koordinat yang ditempat sekarang yang awalnya true menjadi false (sehingga tidak bisa dilewati lagi nantinya)
        //Apabila sudah dilewati atau pemain sudah finish maka :
        //-program akan dikembalikan atau return
        if (!isFinish && mazeMap[mazeMap.length - 1 - y][x].value){
            step++;
            mazeMap[mazeMap.length - 1 - y][x].value = false;
            path.enqueue(Helper.formatNumber(step), new Coordinate(mazeMap.length - 1 - y, x));
        }else {
            return;
        }

        //Perulangan for untuk melakukan pengecekan apakah pergerakannya UP, DOWN, RIGHT, dan LEFT
        //Dan apabila terdapat pergerakan yang sesuai kemudian memanggil method mazeSolve lagi (recursive)
        for (int i = 0; i < movements.length; i++) {
            if (movements[i].equals("UP")){
                mazeSolve(x, y + 1);
            } else if (movements[i].equals("DOWN")) {
                mazeSolve(x,y - 1);
            } else if (movements[i].equals("RIGHT")) {
                mazeSolve(x + 1, y);
            } else if (movements[i].equals("LEFT")){
                mazeSolve(x - 1, y);
            }
        }
    }

    //getStep : method non-void (int) mengembalikan nilai step (langkah pemain)
    //Dan didalamnya terdapat if condition untuk mengecek apakah pemain sudah pernah finish atau belum
    //Apabila sudah maka akan mengembalikan jumlah step atau langkah
    //Apabila tidak maka akan mengembailakn nilai maksimal dari integer, kenapa??
    //Karena nantinya pada sorting nilai maksimal integer akan berada di urutan paling belakang
    //dan suatu step, menurut saya tidak mungkin bernilai nilai maksimal dari integer karena dapat menyebabkan stack overflow
    long getStep (){
        if (isFinish){
            return step;
        }else {
            return Integer.MAX_VALUE;
        }
    }

    //isFinish : method non-void (boolean) mengembalikan nilai dari variabel isFinish
    boolean isFinish (){
        return isFinish;
    }
}
