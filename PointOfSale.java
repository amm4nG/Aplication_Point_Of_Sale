/*
NAMA    : ARMAN UMAR
NIM     : D0221049
KELAS   : INFORMATIKA D
*/

//import library
package javakasir;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PointOfSale {
    public static void main(String[] args) {
        
        //Membuat Sebuah ArrayList Bertipe Class/object
        ArrayList<Barang> dataBarang = new ArrayList<>();
        boolean run  = true;
        int id = -1; //untuk id barang auto incremment
        int i = -1;  //untuk pembacaan nilai index
        
        //deklarasi file
        String fileName = "C:\\Users\\ISMAIL\\Documents\\NetBeansProjects\\JavaKasir\\src\\javakasir\\DataBarang.txt";
        String transaksi = "C:\\Users\\ISMAIL\\Documents\\NetBeansProjects\\JavaKasir\\src\\javakasir\\DataTransaksi.txt";
        
        //perulangan program
        while(run){
            //menu kasir
            System.out.println("===============================");
            System.out.println("---------- M E N U ------------");
            System.out.println("===============================");
            System.out.println("+ [1]. VIEW DATA BARANG       +");
            System.out.println("+ [2]. TAMBAH BARANG          +");
            System.out.println("+ [3]. TAMBAH STOCK           +");
            System.out.println("+ [4]. UPDATE HARGA           +");
            System.out.println("+ [5]. HAPUS BARANG           +");
            System.out.println("+ [6]. PENJUALAN              +");
            System.out.println("+ [7]. EXIT                   +");
            System.out.println("-------------------------------");
            
            Scanner scan = new Scanner(System.in);
            System.out.print("Pilih Menu : ");
            String opsi = scan.next();
            System.out.println("-------------------------------");
            
            //section opsi ke 1
            if (opsi.equals("1")) { 
                System.out.println("-----------------------------------------------------------------");
                System.out.println("id\t"+"barang\t\t"+"stock\t\t"+"terjual\t\t"+"harga");
                System.out.println("-----------------------------------------------------------------");
                try{
                    File myFile = new File(fileName);
                    Scanner fileReader = new Scanner(myFile);
                    
                    while(fileReader.hasNextLine()){
                        String data = fileReader.nextLine();
                        System.out.println(data);
                        System.out.println("-----------------------------------------------------------------");
                    }
                    
                }catch(IOException e){
                    System.out.println("Terjadi Kesalahan karena: "+e.getMessage());
                }
                
            } 
            
            //section opsi ke 2
            else if (opsi.equals("2")) { 
                System.out.print("Nama Barang   : ");
                String namaBarang = scan.next();
                System.out.println("-------------------------------");
                System.out.print("Jumlah Barang : ");
                int jumlahBarang = scan.nextInt();
                System.out.println("-------------------------------");
                System.out.print("Harga Barang  : Rp. ");
                int hargaBarang = scan.nextInt();
                int terjual = 0;
                
                System.out.println("-------------------------------");
                System.out.print("Apakah Anda Ingin Save y/t ? ");
                String save = scan.next();
                System.out.println("-------------------------------");
                if (save.equalsIgnoreCase("y")) { 
                    id++;
                    i++;
                    dataBarang.add(new Barang(id, namaBarang, jumlahBarang, hargaBarang, terjual));
                    System.out.println("Data Barang Berhasil Di Simpan");
                    try{
                        FileWriter fileWriter = new FileWriter(fileName, true);
                        fileWriter.append(dataBarang.get(i).id+"\t"+dataBarang.get(i).namaBarang+"\t\t"+dataBarang.get(i).stockBarang+"\t\t"+dataBarang.get(i).terjual+"\t\t"+dataBarang.get(i).hargaBarang+"\n");
                        fileWriter.close();
                    }catch(IOException e){
                        System.out.println("Terjadi Kesalahan karena: "+e.getMessage());
                    }
                } 
                else{
                    System.out.println("-------------------------------");
                    System.out.println("Penambahan Barang Di Batalkan");
                    System.out.println("-------------------------------");
                }
            }
            
            //section opsi ke 3
            if (opsi.equals("3")) { 
                //pencarian id barang untuk penambahan stock
                int index = -1;
                boolean ditemukan = false;
                System.out.print("Masukkan Id Barang : ");
                int kodeBarang = scan.nextInt();
                for (int j = 0; j < dataBarang.size(); j++) {
                    if (dataBarang.get(j).id == kodeBarang) {
                        index = j;
                        ditemukan = true;
                    }
                }
                //jika id barang ditemukan
                if (ditemukan == true) { 
                    System.out.println("-------------------------------");
                    System.out.print("Jumlah Penambahan Stock : ");
                    int tambahStock = scan.nextInt();
                    System.out.println("-------------------------------");
                    System.out.print("Yakin Ingin Menambah y/t ? ");
                    String tambah = scan.next();
                    System.out.println("-------------------------------");
                    //konfirmasi penambahan stock
                    if (tambah.equalsIgnoreCase("y")) { 
                        Barang updateStock = dataBarang.get(kodeBarang);
                        updateStock.stockBarang = dataBarang.get(kodeBarang).stockBarang+tambahStock;
                        System.out.println("Penambahan Stock Barang Berhasil");
                        try{
                           FileWriter fileWriter = new FileWriter(fileName, false) ;
                           for(Barang barangku: dataBarang){
                               fileWriter.append(barangku.id+"\t"+barangku.namaBarang+"\t\t"+barangku.stockBarang+"\t\t"+barangku.terjual+"\t\t"+barangku.hargaBarang+"\n");
                           }
                           fileWriter.close();
                        }catch(IOException e){
                            System.out.println("Terjadi Kesalahan karena: "+e.getMessage());
                        }
                    }
                    else{
                        System.out.println("----------------------------------");
                        System.out.println("Penambahan Stock Barang Dibatalkan");
                        System.out.println("----------------------------------");
                    }
                } 
                //jika id barang tidak ditemukan
                else{
                    System.out.println("-------------------------------------");
                    System.out.println("Id Yang Anda Masukkan Tidak Ditemukan");
                    System.out.println("-------------------------------------");
                }
            }
            //section opsi ke 4
            if (opsi.equals("4")) { 
                //pencarian id barang untuk pengupdetan harga barang
                int index = -1;
                boolean ditemukan = false;
                System.out.print("Masukkan Id Barang : ");
                int kodeBarang = scan.nextInt();
                for (int j = 0; j < dataBarang.size(); j++) {
                    if (dataBarang.get(j).id == kodeBarang) {
                        index = j;
                        ditemukan = true;
                    }
                }
                //jika id barang ditemukan
                if (ditemukan == true) { 
                    System.out.println("-------------------------------");
                    System.out.print("Update Harga Barang : Rp. ");
                    int hargaBaru = scan.nextInt();
                    System.out.println("-------------------------------");
                    System.out.print("Yakin Ingin Mengupdate y/t ? ");
                    String tambah = scan.next();
                    System.out.println("-------------------------------");
                    //konfirmasi pengupdetan harga barang
                    if (tambah.equalsIgnoreCase("y")) { 
                        Barang updateHarga = dataBarang.get(kodeBarang);
                        updateHarga.hargaBarang = hargaBaru;
                        System.out.println("Harga Barang Berhasil Diupdate");
                        try{
                           FileWriter fileWriter = new FileWriter(fileName, false) ;
                           for(Barang barangku: dataBarang){
                               fileWriter.append(barangku.id+"\t"+barangku.namaBarang+"\t\t"+barangku.stockBarang+"\t\t"+barangku.terjual+"\t\t"+barangku.hargaBarang+"\n");
                           }
                           fileWriter.close();
                        }catch(IOException e){
                            System.out.println("Terjadi Kesalahan karena: "+e.getMessage());
                        }
                    }
                    else{
                        System.out.println("-----------------------------------");
                        System.out.println("Pengupdetan Harga Barang Dibatalkan");
                        System.out.println("-----------------------------------");
                    }
                } 
                //jika id barang tidak ditemukan
                else{
                    System.out.println("-------------------------------------");
                    System.out.println("Id Yang Anda Masukkan Tidak Ditemukan");
                    System.out.println("-------------------------------------");
                }
            }
            //section opsi ke 5
            if (opsi.equals("5")) { 
                //pencarian id barang untuk menu penghapusan
                int index = -1;
                boolean ditemukan = false;
                System.out.print("Hapus Barang Dengan Id : ");
                int hapus = scan.nextInt();
                
                for (int j = 0; j < dataBarang.size(); j++) {
                    if (dataBarang.get(j).id == hapus) {
                        index = j;
                        ditemukan = true;
                    }
                }
                //jika id barang ditemukan
                if (ditemukan == true) { 
                    System.out.println("------------------------------------------");
                    System.out.print("apakah Anda Yakin Akan Menghapusnya y/t ? ");
                    String hapusBarang = scan.next();
                    //konfirmasi penghapusan
                    if (hapusBarang.equalsIgnoreCase("y")) {
                        dataBarang.remove(hapus);   //remove
                        System.out.println("-------------------------------");
                        System.out.println("Data Barang Berhasil Di Hapus");
                        try{
                           FileWriter fileWriter = new FileWriter(fileName, false) ;
                           for(Barang barangku: dataBarang){
                               fileWriter.append(barangku.id+"\t"+barangku.namaBarang+"\t\t"+barangku.stockBarang+"\t\t"+barangku.terjual+"\t\t"+barangku.hargaBarang+"\n");
                           }
                           fileWriter.close();
                        }catch(IOException e){
                            System.out.println("Terjadi Kesalahan karena: "+e.getMessage());
                        }
                    }
                    else{
                        System.out.println("-------------------------------");
                        System.out.println("Penghapusan Di Batalakan");
                    }
                } 
                //jika id barang tidak ditemukan
                else{
                    System.out.println("-------------------------------------");
                    System.out.println("Id Yang Anda Masukkan Tidak Ditemukan");
                    System.out.println("-------------------------------------");
                }
            } 
            //section opsi ke 6
            if (opsi.equals("6")) { 
                System.out.println("-----------------------------------------------------------------");
                System.out.println("id\t"+"barang\t\t"+"stock\t\t"+"terjual\t\t"+"harga");
                System.out.println("-----------------------------------------------------------------");
                try{
                    File myFile = new File(fileName);
                    Scanner fileReader = new Scanner(myFile);
                    
                    while(fileReader.hasNextLine()){
                        String data = fileReader.nextLine();
                        System.out.println(data);
                    }
                    System.out.println("-----------------------------------------------------------------");
                }catch(IOException e){
                    System.out.println("Terjadi Kesalahan karena: "+e.getMessage());
                }
                //inputan id barang yang dibeli
                System.out.print("Id Barang Yang Dibeli   : ");
                int pilihBarang = scan.nextInt();
                //pencarian id barang untuk menu penjualan
                int index = -1;
                boolean ditemukan = false;
                for (int j = 0; j < dataBarang.size(); j++) {
                    if (dataBarang.get(j).id == pilihBarang) {
                        index = j;
                        ditemukan = true;
                    }
                }
                //jika id barang ditemukan
                if (ditemukan == true) { 
                    System.out.println("-------------------------------");
                    //penginputan jumlah belanjaan
                    System.out.print("Jumlah Barang Belanjaan : ");
                    int jumlahBelanjaan = scan.nextInt();
                    //kondisi jika jumlah belanjaan yang di inputkan lebih dari jumlah stock barang
                    if (jumlahBelanjaan > dataBarang.get(pilihBarang).stockBarang) {
                        System.out.println("-------------------------------");
                        System.out.println("Jumlah Stock Barang Tidak Cukup");
                    }
                    //kondisi jika jumlah belanjaan tidak melebihi stock barang
                    else{
                    Barang update = dataBarang.get(pilihBarang);
                    update.stockBarang = dataBarang.get(pilihBarang).stockBarang-jumlahBelanjaan;
                    update.terjual = dataBarang.get(pilihBarang).terjual+jumlahBelanjaan;
                    try{
                        FileWriter fileWriter = new FileWriter(fileName, false) ;
                        for(Barang barangku: dataBarang){
                            fileWriter.append(barangku.id+"\t"+barangku.namaBarang+"\t\t"+barangku.stockBarang+"\t\t"+barangku.terjual+"\t\t"+barangku.hargaBarang+"\n");
                        }
                        fileWriter.close();
                    }catch(IOException e){
                            System.out.println("Terjadi Kesalahan karena: "+e.getMessage());
                    }
                    //penulisan struck data belanjaan kedalam file DataTransaksi.txt
                    try{
                        FileWriter fileWriter = new FileWriter(transaksi, true);
                        fileWriter.write("-------------------------------");
                        fileWriter.write("\nData Belanjaan");
                        fileWriter.write("\n-------------------------------");
                        fileWriter.write("\nId Barang     : "+pilihBarang);
                        fileWriter.write("\nNama Barang   : "+dataBarang.get(index).namaBarang);
                        fileWriter.write("\nJumlah Barang : "+jumlahBelanjaan);
                        fileWriter.write("\nHarga Barang  : "+dataBarang.get(index).hargaBarang);
                        fileWriter.write("\n-------------------------------");
                        fileWriter.write("\nTotal Bayar   : "+dataBarang.get(index).hargaBarang*jumlahBelanjaan);
                        fileWriter.write("\n-------------------------------");
                        fileWriter.close();
                    }catch(IOException e){
                        System.out.println("Terjadi Kesalahan karena: "+e.getMessage());
                    }
                    try{
                    File myFile = new File(transaksi);
                    Scanner fileReader = new Scanner(myFile);
                        System.out.println("-------------------------------");
                        System.out.println("Pembelian Anda Success");
                    while(fileReader.hasNextLine()){
                        String data = fileReader.nextLine();
                        System.out.println(data);
                    }
        
                    }catch(IOException e){
                        System.out.println("Terjadi Kesalahan karena: "+e.getMessage());
                    }
                    }
                }
                //jika id barang tidak ditemukan
                else{
                    System.out.println("-------------------------------------");
                    System.out.println("Id Yang Anda Masukkan Tidak Ditemukan");
                    System.out.println("-------------------------------------");
                }
            }
            //section opsi ke 7
            else if (opsi.equals("7")) {
                //mengakhiri program atau keluar dari perulangan while
                break;
            }
            
        } 
    }
}
