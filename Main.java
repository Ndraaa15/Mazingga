package src.Mazingga;

import java.util.*;

/*
 *225150200111006_Gede Indra Adi Brata_1
 *225150200111007_Achmad Fauzi Aranda_2
 *225150207111015_Gratia Yudika Morado Silalahi_3
*/

public class Main {
    //Class Main -> tempat dimana semua program di jalankan
    public static void main(String[] args) {
        //Scanner yang berfungsi untuk menerima input atau masukkan dengan variabel "sc"
        Scanner sc = new Scanner(System.in);

        //sizeMaze : variabel bertipe integer yang berfungsi untuk menerima masukkan berupa panjang dan lebar maze (persegi)
        int sizeMaze = sc.nextInt(); sc.nextLine();

        //validPath : variabel bertipe string yang berfungsi untuk menerima masukkan berupa jalur yang bisa dilalui oleh pemain / player
        String validPath = sc.nextLine();

        //validPathSplit : array bertipe string yang berisi koordinat dari jalur yang bisa dilalui setelah melakukan split (" ")
        //Contoh : 2,1 0,2 0,3
        //element 0 -> 2,1
        //element 1 -> 0,2
        //element 2 -> 0,3
        String [] validPathSplit = validPath.split(" ");

        //startCoordinate : array bertipe integer yang berisi koordinat awal atau start dari pemain
        //Contoh : 2,1
        //element 0 -> 2
        //element 1 -> 1
        int [] startCoordinate = Helper.getStartCoordinate(validPathSplit);

        //endCoordinate : array bertipa integer yang berisi koordinat akhir atau finish dari pemain
        //Contoh : 1,5
        //element 0 -> 1
        //element 1 -> 5
        int [] endCoordinate = Helper.getEndCoordinate(validPathSplit);

        //totalPlayer : variabel bertipe integer yang berfungsi untuk menerima masukkan berupa jumlah pemain yang akan berusaha menyelesaikan maze
        int totalPlayer = sc.nextInt(); sc.nextLine();

        //players : array bertipe Player (class) yang berfungsi untuk menyimpan pemain
        Player [] players = new Player[totalPlayer];

        //Perulangan for berfungsi untuk menerima masukkan sebanyak jumlah pemain yang ada
        for (int i = 0; i < totalPlayer; i++) {
            //command : variabel bertipe string yang berfungsi menerima masukkan berupa nama pemain serta pergerakannya (movement)
            String command = sc.nextLine();

            //movements : array bertipe string yang berfungsi untuk menyimpan pergerakan yang dilakukan oleh pemain
            //Contoh : Anni UP LEFT RIGHT DOWN
            //element 0 -> UP
            //element 1 -> LEFT
            //element 2 -> RIGHT
            //element 3 -> DOWN
            String [] movements = Helper.getMovements(command);

            //name : variabel yang bertipa string yang berfungsi untuk menyimpan nama dari player
            //Contoh : Anni UP LEFT RIGHT DOWN
            //name -> Anni
            String name = Helper.getName(command);

            //Pembuatan player ke-i dengan cara pemaanggilan contructor Player dengan beberapa argument
            //Argument 1 -> name (nama dari pemain)
            //Argument 2 -> MazeMap (MazeMap adalah array 2 dimensi yang isinya diisi dengan NodeMap)
            //Argument 3 -> movements / pergerakan (pergerakan yang bisa dilakukan oleh pemain)
            //Argument 4 -> startCoordinate (koordinat awal atau start pemain)
            //Argument 5 -> endCoordinate (koordinat akhir atau finish pemain)
            players[i] = new Player(name, Helper.createMazeMap(sizeMaze, new NodeMap[sizeMaze][sizeMaze], validPathSplit), movements, startCoordinate, endCoordinate);
        }

        /*sortedPlayers : array bertipe Player yang berfungsi untuk menyimpan data pemain yang telah di urutkan atau sorting
          berdasarkan jumlah langkah menuju koordinat akhir / finish dan namanya
        */
        Player [] sortedPlayers = Helper.sortingPlayers(players);

        //Perulangan for yang berfungsi untuk mencetak hasil penulusuran maze dari setiap pemain yang sudah diurutkan
        for (int i = 0; i < totalPlayer; i++) {
            //Mencetak nama pemain
            System.out.println(sortedPlayers[i].getName());

            //Pemanggilan method printMazeMap yang berfungsi untuk mencetak maze map dari setiap pemain
            players[i].printMazeMap();
        }

        //Mencetak pemenang dari permainan mazingga yang berisi nama serta jumlah langkah untuk menuju finish
        System.out.printf("\nWINNER %s %d steps\n", sortedPlayers[0].getName(), sortedPlayers[0].getStep());

        //Perulangan for untuk mencetak sisa pemain yang mengikuti permainan
        //Terdapat 2 kondisi apakah pemain dapat menyelesaikan mazenya atau tidak apabila bisa maka akan mencetak nama beserta langkahnya
        //Apabila tidak maka akan mencetak (nama + " failed to solve the maze:(");
        for (int i = 1; i < totalPlayer; i++) {
            if (sortedPlayers[i].isFinish()){
                System.out.printf("%s %d steps\n", sortedPlayers[i].getName(), sortedPlayers[i].getStep());
            }else {
                System.out.println(sortedPlayers[i].getName() + " failed to solve the maze:(");
            }
        }
    }
}
