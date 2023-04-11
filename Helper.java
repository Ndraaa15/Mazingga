package src.Mazingga;

//Class Helper : class yang berisi method untuk mempermudah eksekusi program dan agar program yang terlihat di main lebih rapi
class Helper {
    //getMovements : method non-void (String []) yang berfungsi untuk membuat array dari movement / pergerakan dari setiap pemain
    static String [] getMovements (String command){
        String [] commands = command.split(" ");
        String [] movements = new String[commands.length - 1];
        int counter = 0;
        for (int i = 1; i < commands.length; i++) {
            movements[counter] = commands[i];
            counter++;
        }
        return movements;
    }

    //getName : method non-void (String) yang berfungsi untuk membuat nama dari pemain
    static String getName (String command){
        String [] commands = command.split(" ");
        return commands[0];
    }

    //createMazeMap : method non-void (NodeMap [][]) yang berfungsi untuk membuat maze map dari setiap pemain
    //Dimana method ini juga menggunakan validPathSplit untuk membuat jalur mana saja yang bisa dilewati
    static NodeMap[][] createMazeMap (int sizeMaze, NodeMap [][]mazeMap, String [] validPathSplit){
        //Perulangan for untuk membuat NodeMap yang baru pada array 2 dimensi
        //Dimana di setiap NodeMap default diberikan argument "XX" dan false
        //data  : "XX"  -> tampilan pada output
        //value : false -> apalah jalur tersebut bisa dilewati atau tidak
        for (int i = 0; i < sizeMaze ; i++) {
            for (int j = 0; j < sizeMaze; j++) {
                mazeMap[sizeMaze - j - 1][i] = new NodeMap ("XX" , false);
            }
        }

        //Melakukan perulangan for untuk membuat jalur yang bisa dilewati
        //Dengan mengubah value yang awalnya false menjadi true dan data yang awalnya "XX" menjadi "00"
        for (String s : validPathSplit) {
            //validCoordinate : array bertipe string yang menyimpan koordinat (x, y) yang bisa dilewati
            String[] validCoordinate = s.split(",");

            //Melakukan parsing dari string menjadi integer
            int x = Integer.parseInt(validCoordinate[0]);
            int y = Integer.parseInt(validCoordinate[1]);

            //Melakukan perubahan data dan value pada array 2 dimensi sebelumnya
            mazeMap[sizeMaze - 1- y][x].value = true;
            mazeMap[sizeMaze - 1 -y][x].data = "00";
        }
        return mazeMap;
    }

    //formatNumber : method yang berfungsi untuk melakukan formatting number
    //Apabila number <= 9 maka akan mencetak 01, 02, ... , 09
    //Apabile number > 9 maka akan mencetak 10, 11, 12, ...
    static String formatNumber (Integer numberPath){
        return numberPath <= 9 ? "0" + numberPath : numberPath.toString();
    }

    //getStartCoordinate : method non-void (int []) yang berfungsi untuk mencapatkan koordinat (x, y) awal / start
    static int [] getStartCoordinate (String [] validPathSplit){
        String [] coordinate  = validPathSplit[0].split(",");
        int [] startCoordinate = new int[2];
        startCoordinate[0] = Integer.parseInt(coordinate[0]);
        startCoordinate[1] = Integer.parseInt(coordinate[1]);

        return startCoordinate;
    }

    //getEndCoordinate : method non-void (int[]) yang berfungsi untuk mencapatkan koordinat (x, y) akhir / finish
    static int [] getEndCoordinate (String [] validPathSplit){
        String [] coordinate  = validPathSplit[validPathSplit.length - 1].split(",");
        int [] endCoordinate = new int[2];
        endCoordinate[0] = Integer.parseInt(coordinate[0]);
        endCoordinate[1] = Integer.parseInt(coordinate[1]);

        return endCoordinate;
    }

    //sortingPlayers : method non-void (Player[]) yang berfungsi untuk melakukan pengurutan pemain
    //Pengurutan pemain dilakukan dari jumlah langkah paling sedikit hingga paling banyak ke finish
    //Apabila terdapat pemain dengan jumlah langkah yang sama maka akan diurutkan berdasarkan namanya (ascending)
    static Player [] sortingPlayers (Player [] players){
        Player temp;
        for (int i = 0; i < players.length - 1; i++) {
            for (int j = 0; j < players.length - 1; j++) {
                if (players[j].getStep() > players[j + 1].getStep()){
                    //BubbleSort -> Algoritma sortirng, pada kasus ini pengurutan dari langkah yang paling sedikit ke paling banyak
                    temp = players[j];
                    players[j] = players[j + 1];
                    players[j + 1] = temp;
                }else if (players[j].getStep() == players[j + 1].getStep()){
                    //Apabila jumlah langkahnya sama maka dilakukan pengurutan berdasarkan nama pemain (ascending)
                    //Untuk logic yang digunakan disini dengan memandingkan ascii dari setiap character pada nama
                    //Apabila ascii yang dihasilkan lebih kecil maka nama akan berada diurutan lebih awal
                    int counter = 0;
                    int shortIndex;
                    int ctr = 0;

                    //Mencari nama dengan jumlah character paling sedikit
                    if (players[j].getName().length() > players[j + 1].getName().length()){
                        shortIndex = players[j + 1].getName().length();
                    }else {
                        shortIndex = players[j].getName().length();
                    }

                    //Menggunakan perulangan while untuk pengecakan ascii setiap character
                    while (counter < shortIndex){
                        int a = players[j].getName().charAt(counter);
                        int b = players[j + 1].getName().charAt(counter);
                        if (a > b){
                            //Hampir sama dengan bubble sort
                            temp = players[j];
                            players[j] = players[j + 1];
                            players[j + 1] = temp;
                        }else {
                            ctr++;
                            counter++;
                        }
                    }

                    //Apabila sampai akhir ascii yang dibandingkan masih sama maka selanjutnya digunakan compareTo ()
                    //compareTo berfungsi untuk membandingkan lesikonitas dari kedua nama
                    //Apabila leksikonitas yang dihasilkan lebih kecil dari 0 maka :
                    //-Nama atau object yang memanggil method compareTo ini memiliki nilai lebih kecil
                    //Apabila leksikonitas yang dihasilkan lebih besar dari 0 maka :
                    //-Nama atau object yang memanggil method compareTo ini memiliki nilai lebih besar
                    if (ctr == shortIndex){
                        if (players[j].getName().compareTo(players[j + 1].getName()) > 0){
                            //Hampir sama dengan bubble sort
                            temp = players[j];
                            players[j] = players[j + 1];
                            players[j + 1] = temp;
                        }
                    }
                }
            }
        }
        return players;
    }
}
