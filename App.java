import java.util.Scanner;

class Driver {
    public int no_sim;
    public boolean sim_truk;
    public boolean sim_bus;

    public Driver(int no_sim, boolean sim_truk, boolean sim_bus) {
        this.no_sim = no_sim;
        this.sim_truk = sim_truk;
        this.sim_bus = sim_bus;
    }
}

class Penumpang {
    public String nama;

    public Penumpang(String nama) {
        this.nama = nama;
    }
}

class Kendaraan {
    public String merk;
    public String warna;
    public String platNomor;
    public int jumlahPenumpang;
    public int maxPenumpang;

    public Driver supir;
    public String[] penumpangList;

    public Kendaraan(String pn, String m, int max) {
        this.merk = m;
        this.platNomor = pn;
        this.jumlahPenumpang = 0;
        this.maxPenumpang = max;
        this.penumpangList = new String[max]; // Menginisialisasi array dengan ukuran maksimum

        System.out.println("Halo saya objek dari kelas Kendaraan dengan plat nomor : " + this.platNomor);
        cekPenumpang();
    }

    public void cekPenumpang() {
        System.out.println("Penumpang saat ini: " + this.jumlahPenumpang);
        System.out.println("Daftar Penumpang:");
        for (String nama : penumpangList) {
            if (nama != null) {
                System.out.println("*" + nama);
            }
        }
    }

    public void penumpangNaik(int naik) {
        Scanner scanner = new Scanner(System.in); // Tambahkan scanner untuk input nama
        System.out.println("ada penumpang mau naik: " + naik);
        int current = this.jumlahPenumpang;
        if (current + naik > this.maxPenumpang) {
            System.out.println("maaf penumpang melebihi kapasitas");
        } else {
            for (int i = 0; i < naik; i++) {
                System.out.print("Masukkan nama penumpang: ");
                String nama = scanner.nextLine();
                this.penumpangList[current + i] = nama; // Tambahkan penumpang ke dalam array
            }
            this.jumlahPenumpang += naik;
            System.out.println("penumpang berhasil naik");
        }
        cekPenumpang();
    }

    public void penumpangTurun(int turun) {
        System.out.println("ada penumpang mau turun: " + turun);
        int current = this.jumlahPenumpang;
        if (current - turun < 0) {
            System.out.println("maaf penumpang ghoib yang turun");
        } else {
            this.jumlahPenumpang -= turun;
            for (int i = 0; i < turun; i++) {
                this.penumpangList[i] = null; // Hapus penumpang dari array
            }
            System.out.println("penumpang berhasil turun");
        }
        cekPenumpang();
    }

    public void maju() {
        System.out.println(this.merk + " " + this.platNomor + " Maju");
    }

    public void mundur() {
        System.out.println(this.merk + " " + this.platNomor + " Mundur");
    }

    public void belok() {
        System.out.println(this.merk + " " + this.platNomor + " Belok");
    }

    public void berhenti() {
        System.out.println(this.merk + " " + this.platNomor + " Berhenti");
    }

    public void showSIM() {
        System.out.println(this.supir.no_sim);
    }

}

class Truk extends Kendaraan {
    int kapasitasMuatan;
    public boolean sim_bus;

    public Truk(String pn, String m, int max) {
        super(pn, m, max);
    }

    public void setKapasitas(int kapasitas) {
        this.kapasitasMuatan = kapasitas;
    }

    public void muatBarang(int berat, int kapasitasMuatan) {
        System.out.println("Muatan yang ingin dinaikan: " + berat + " kg");
        if (berat <= kapasitasMuatan) {
            System.out.println("Barang berhasil dimuat ke dalam truk.");
        } else {
            System.out.println("Kapasitas muatan truk tidak mencukupi. Silahkan turunkan muatan terlebih dahulu");
        }
    }

    public void setDriver(Driver supir) {
        if (supir.sim_truk) {
            this.supir = supir;
            System.out.println("Driver memiliki SIM truk yang valid.");
        } else {
            System.out.println("Maaf, driver tidak memiliki SIM truk.");
        }
    }
}

class Bus extends Kendaraan {
    boolean toiletDigunakan;
    public boolean sim_bus;

    public Bus(String pn, String m, int max) {
        super(pn, m, max);
        this.toiletDigunakan = false;
    }

    public void toiletDipakai() {
        toiletDigunakan = true;
        System.out.println("Toilet lagi dipakai");
    }

    public void toiletKosong() {
        toiletDigunakan = false;
        System.out.println("Toilet lagi kosong");
    }

    public void cekToilet() {
        if (toiletDigunakan) {
            System.out.println("Toilet sedang digunakan.");
        } else {
            System.out.println("Toilet sedang tidak digunakan.");
        }
    }
}

class Motor extends Kendaraan {
    int standar;

    public Motor(String pn, String m, int max) {
        super(pn, m, max);
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        int pilihan = 0;
        Truk t1 = new Truk("N 1234 YY", "HINO", 3);

        Driver s1 = new Driver(1234, false, true);
        t1.supir = s1;

        Scanner scanner = new Scanner(System.in);
        while (pilihan != 4) {
            // Menampilkan menu
            System.out.println("Menu:");
            System.out.println("1. Naik");
            System.out.println("2. Turun");
            System.out.println("3. Cek Penumpang");
            System.out.println("4. Keluar");

            // Meminta input dari pengguna
            System.out.print("Pilih menu (masukkan angka): ");
            pilihan = scanner.nextInt();

            // Proses pemilihan menu
            switch (pilihan) {
                case 1:
                    System.out.println("Berapa jumlah penumpang naik?");
                    System.out.print("(masukkan angka): ");
                    int naik = scanner.nextInt();
                    t1.penumpangNaik(naik);
                    break;
                case 2:
                    System.out.println("Berapa jumlah penumpang turun?");
                    System.out.print("(masukkan angka): ");
                    int turun = scanner.nextInt();
                    t1.penumpangTurun(turun);
                    break;
                case 3:
                    t1.cekPenumpang();
                    break;
                case 4:
                    System.out.println("Terima kasih. Program berhenti.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih antara 1-4.");
            }
        }
        scanner.close();
    }
}
