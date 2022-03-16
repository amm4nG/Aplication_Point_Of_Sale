package javakasir;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class JavaKasir {
    public static void main(String[] args) {
        //Deklarasi ArrayList Bertipe Object
        ArrayList<Barang> dataBarang = new ArrayList<>();
        //Deklarasi file
        String fileName = "C:\\Users\\ISMAIL\\Documents\\NetBeansProjects\\JavaKasir\\src\\javakasir\\DataBarang.txt";
        String transaksi = "C:\\Users\\ISMAIL\\Documents\\NetBeansProjects\\JavaKasir\\src\\javakasir\\DataTransaksi.txt";
        boolean run  = true;
        int id = -1; //untuk id barang auto incremment
        int i = -1; //untuk membaca setiap index pada arraylist
        
        //perulangan
        while(run){ //kurung pembuka while
            //tampilan menu
            
            System.out.println("========== M E N U ============");
            System.out.println("+ [1]. VIEW DATA BARANG       +");
            System.out.println("+ [2]. TAMBAH BARANG          +");
            System.out.println("+ [3]. TAMBAH STOCK BARANG    +");
            System.out.println("+ [4]. REMOVE DATA BARANG     +");
            System.out.println("+ [5]. PENJUALAN              +");
            System.out.println("+ [6]. EXIT                   +");
            
            //scan
            Scanner scan = new Scanner(System.in);
            System.out.print("Masukkan Pilihan Opsi : ");
            String opsi = scan.nextLine();
            System.out.println("-------------------------------");
            if (opsi.equals("1")) { //kurung pembuka opsi 1 membaca data
               
                System.out.println("id\t"+"barang\t\t"+"stock\t\t"+"terjual\t\t"+"harga\n");
                try {
                    File myFile = new File(fileName);
                    Scanner fileReader = new Scanner(myFile);

                    // cetak isi file
                    while(fileReader.hasNextLine()){
                        String data = fileReader.nextLine();
                        System.out.println(data);
                    }  
                  
                } catch (IOException e) {
                    System.out.println("Terjadi kesalahan karena: " + e.getMessage());
                }
            } //kurung penutup opsi 1
            
            else if (opsi.equals("2")) { //kurung pembuka opsi 2 menambah data
                
                System.out.print("Nama Barang   : ");
                String namaBarang = scan.nextLine();
                System.out.print("Jumlah Barang : ");
                int jumlahBarang = scan.nextInt();
                System.out.print("Harga Barang  : ");
                int hargaBarang = scan.nextInt();
                int terjual = 0;
                //konfirmasi untuk save
                System.out.println("-------------------------------");
                System.out.print("Apakah Anda Ingin Save y/t ? ");
                String save = scan.next();
                System.out.println("-------------------------------");
                if (save.equalsIgnoreCase("y")) { //kurung pembuka konfirmasi untuk save
                    id++;
                    i++;
                    System.out.println("Data Barang Berhasil Di Simpan");
                    dataBarang.add(new Barang(id, namaBarang, jumlahBarang, hargaBarang, terjual));
                    try {
                        FileWriter fileWriter = new FileWriter(fileName, true);
                        fileWriter.append(dataBarang.get(i).id +"\t"+ dataBarang.get(i).namaBarang+"\t\t"+dataBarang.get(i).stockBarang+"\t\t"+dataBarang.get(i).terjual+"\t\t"+dataBarang.get(i).hargaBarang+"\n");
                        fileWriter.close();
                    } catch (IOException e) {
                        System.out.println("Terjadi kesalahan karena: " + e.getMessage());
                    }
                } //kurung penutup konfirmasi untuk save
                else{
                    System.out.println("-------------------------------");
                    System.out.println("Penambahan Barang Di Batalkan");
                }
            } //kurung penutup opsi 2
            
            if (opsi.equals("3")) { //kurung pembuka opsi 3
                int index = -1;
                boolean ditemukan = false;
                System.out.print("Tambah Stock Barang Dengan Id : ");
                int kodeBarang = scan.nextInt();
                
                
                
                    for (int j = 0; j < dataBarang.size(); j++) { //kurung pembuka for
                        if (dataBarang.get(j).id == kodeBarang) { //kurung pembuka pencarian id
                            index = j;
                            ditemukan = true;
                        } //kurung penutup pencarian id
                    } //kurung penutup for
                            if (ditemukan == true) {
                                System.out.print("Jumlah Penambahan Stock       : ");
                                int tambahStock = scan.nextInt();
                                System.out.println("-------------------------------");
                                System.out.print("Yakin Ingin Menambah y/t ? : ");
                                String tambah = scan.next();
                                System.out.println("-------------------------------");
                                if (tambah.equalsIgnoreCase("y")) { //kurung pembuka konfirmasi tambah stock
                                    Barang updateStock = dataBarang.get(kodeBarang);
                                    updateStock.stockBarang = dataBarang.get(kodeBarang).stockBarang+tambahStock;
                                    System.out.println("Penambahan Stock Barang Berhasil");
                                    try{
                                        FileWriter fileWriter = new FileWriter(fileName, false);
                                        for(Barang barangku: dataBarang){
                                            fileWriter.append(barangku.id +"\t"+ barangku.namaBarang+"\t\t"+barangku.stockBarang+"\t\t"+barangku.terjual+"\t\t"+barangku.hargaBarang+"\n");
                                        }
                                         fileWriter.close();
                                    }catch (IOException e) {
                                            System.out.println("Terjadi kesalahan karena: " + e.getMessage());
                                    }
                            }
                                else{
                                    System.out.println("-------------------------------");
                                    System.out.println("Penambahan Stock Barang Di Batalkan");
                                }
                        }//kurung penutup konfirmasi
                            else{
                                System.out.println("-------------------------------");
                                System.out.println("Id Yang Anda Masukkan Tidak Ditemukan");
                            }
                            
                    
                
                
            } //kurung penutup opsi 3
            
            if (opsi.equals("4")) { //kurung pembuka opsi 4
                int index = -1;
                boolean ditemukan = false;
                System.out.print("Hapus Barang Dengan Id : ");
                int hapus = scan.nextInt();
                
                System.out.println("-------------------------------");
                
                    for (int j = 0; j < dataBarang.size(); j++) { //kurung pembuka for
                        if (dataBarang.get(j).id == hapus) { //kurung pembuka pencarian id
                            index = j;
                            ditemukan = true;
                        }
                    }
                    if (ditemukan == true) {
                        System.out.print("Apakah Anda Yakin Akan Menghapusnya y/t ? ");
                        String hapusBarang = scan.next();
                        if (hapusBarang.equalsIgnoreCase("y")) { //kurung pembuka konfirmasi delete
                            dataBarang.remove(hapus);
                            System.out.println("Data Barang Berhasil Di Hapus");
                            try{
                                FileWriter fileWriter = new FileWriter(fileName, false);
                                for(Barang barangku: dataBarang){
                                    fileWriter.append(barangku.id +"\t"+ barangku.namaBarang+"\t\t"+barangku.stockBarang+"\t\t"+barangku.terjual+"\t\t"+barangku.hargaBarang+"\n");
                                }
                                fileWriter.close();
                            }catch (IOException e) {
                                        System.out.println("Terjadi kesalahan karena: " + e.getMessage());
                            }
                        }else{
                            System.out.println("Penghapusan Di Batalkan");
                        }
                
                    
                    }
                    else{
                        System.out.println("-------------------------------");
                        System.out.println("Id Yang Anda Masukkan Tidak Ditemukan");
                    
                }//kurung penutup konfirmasi delete
                
            }//kurung penutup opsi 4
            if (opsi.equals("5")) { //kurung pembuka opsi 5
                System.out.println("id\t"+"barang\t\t"+"stock\t\t"+"terjual\t\t"+"harga");
                System.out.println("--------------------------------------------------------------------");
                try {
                    File myFile = new File(fileName);
                    Scanner fileReader = new Scanner(myFile);

                    // cetak isi file
                    while(fileReader.hasNextLine()){
                        String data = fileReader.nextLine();
                        System.out.println(data);
                    }  
                } catch (IOException e) {
                    System.out.println("Terjadi kesalahan karena: " + e.getMessage());
                }
                System.out.println("--------------------------------------------------------------------");
                System.out.print("Id Barang Yang Dibeli : ");
                
                int pilihBarang = scan.nextInt();
                int index = -1;
                boolean ditemukan = false;
                for (int j = 0; j < dataBarang.size(); j++) { //kurung pembuka for
                    if (dataBarang.get(j).id == pilihBarang) { //kurung pembuka pencarian id
                        index = j;
                        ditemukan = true;
                    }//kurung pembuka pencarian id
                }//kurung pembuka for
                if (ditemukan == true) {
                    System.out.println("-------------------------------");
                    System.out.print("Jumlah Barang Belanjaan : ");
                    int jumlahBelanjaan = scan.nextInt();
                    Barang updateStock = dataBarang.get(pilihBarang);
                    updateStock.stockBarang = dataBarang.get(pilihBarang).stockBarang-jumlahBelanjaan;
                    updateStock.terjual = dataBarang.get(pilihBarang).terjual+jumlahBelanjaan;
                    try{
                        FileWriter fileWriter = new FileWriter(fileName, false);
                        for(Barang barangku: dataBarang){
                            fileWriter.append(barangku.id +"\t"+ barangku.namaBarang+"\t\t"+barangku.stockBarang+"\t\t"+barangku.terjual+"\t\t"+barangku.hargaBarang+"\n");
                            }
                            fileWriter.close();
                    }catch (IOException e) {
                            System.out.println("Terjadi kesalahan karena: " + e.getMessage());
                    }    
                    //Membaca Data Belanjaan        
                    
                    try {
                        FileWriter fileWriter = new FileWriter(transaksi, true);
                        
                        fileWriter.write("\nData Belanjaan");
                        fileWriter.write("\nId Barang      : "+pilihBarang);
                        fileWriter.write("\nNama Barang    : "+dataBarang.get(index).namaBarang);
                        fileWriter.write("\nJumlah Barang  : "+jumlahBelanjaan);
                        fileWriter.write("\nHarga Barang   : "+dataBarang.get(index).hargaBarang);
                        fileWriter.write("\n-------------------------------");
                        fileWriter.write("\nTotal Bayar    : "+dataBarang.get(index).hargaBarang*jumlahBelanjaan);
                        fileWriter.close();
                    } catch (IOException e) {
                        System.out.println("Terjadi kesalahan karena: " + e.getMessage());
                    }
                    try {
                    File myFile = new File(transaksi);
                    Scanner fileReader = new Scanner(myFile);

                    // cetak isi file
                    while(fileReader.hasNextLine()){
                        String data = fileReader.nextLine();
                        System.out.println(data);
                    }  
                } catch (IOException e) {
                    System.out.println("Terjadi kesalahan karena: " + e.getMessage());
                }
                }else{
                        System.out.println("Id Yang Anda Masukkan Tidak Ditemukan");
                    }
                
                
            }//kurung penutup opsi 5
            else if (opsi.equals("6")) {
                break;
            }
            
        } //kurung penutup while
    }
    
}
