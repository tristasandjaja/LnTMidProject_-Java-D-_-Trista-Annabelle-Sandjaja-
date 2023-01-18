import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

	Scanner scan = new Scanner(System.in);
	ArrayList<Karyawan> karyawanList = new ArrayList<>();
	int choose = 0;
	String kode = "";
	String nama = "";
	String kelamin = "";
	String jabatan = "";
	int gaji = 0;
	int update = 0;
	int delete = 0;
	String newkode = "";
	String newnama = "";
	String newkelamin = "";
	String newjabatan = "";
	int newgaji = 0;

	public String randID() {
		Random rand = new Random();
		String prefix = "QWERTYUIOPASDFGHJKLZXCVBNM";
		String id = "";
		id += prefix.charAt(rand.nextInt(26));
		id += prefix.charAt(rand.nextInt(26));
		id += "-";
		id += rand.nextInt(10);
		id += rand.nextInt(10);
		id += rand.nextInt(10);
		id += rand.nextInt(10);
		return id;
	}

	public void menu() {
		System.out.println("1. Insert data karyawan ");
		System.out.println("2. View data karyawan ");
		System.out.println("3. Update data karyawan ");
		System.out.println("4. Hapus data karyawan ");
		System.out.print(">> ");
		choose = scan.nextInt();
		scan.nextLine();
	}

	public void insert() {
		String kode = randID();
		do {
			System.out.print("Input nama karyawan [>=3]: ");
			nama = scan.nextLine();
		} while (nama.length() < 3);

		do {
			System.out.print("Input jenis kelamin [Laki-laki | Perempuan] (Case Sensitive): ");
			kelamin = scan.nextLine();
		} while (!(kelamin.equals("Laki-laki") || kelamin.equals("Perempuan")));

		do {
			System.out.print("Input jabatan [Manager | Supervisor | Admin] (Case Sensitive): ");
			jabatan = scan.nextLine();
		} while (!(jabatan.equals("Manager") || jabatan.equals("Supervisor") || jabatan.equals("Admin")));
		System.out.println("Berhasil menambahkan karyawan dengan id " + kode);

		if (jabatan.equals("Manager")) {
			gaji = 8000000;
		} else if (jabatan.equals("Supervisor")) {
			gaji = 6000000;
		} else if (jabatan.equals("Admin")) {
			gaji = 4000000;
		}
		Karyawan karyawan = new Karyawan(kode, nama, kelamin, jabatan, gaji);
		karyawanList.add(karyawan);
	}

	public void view() {
		int no = 1;
		System.out.printf("|%-4s|%-17s|%-24s|%-14s|%-15s|%-15s|\n","-----", "-------------------", "---------------------------", "------------------", "------------------", "-------------------");
		System.out.printf("|%-4s |%-18s |%-26s |%-17s |%-17s |%-18s |\n", "No", "Kode Karyawan", "Nama Karyawan",
				"Jenis Kelamin", "Jabatan", "Gaji Karyawan");
		System.out.printf("|%-4s|%-17s|%-25s|%-15s|%-15s|%-15s|\n","-----", "-------------------", "---------------------------", "------------------", "------------------", "-------------------");

		for (int i = 0; i < karyawanList.size(); i++) {
			for (int j = 0; j < karyawanList.size() - i - 1; j++) {
				if (karyawanList.get(j).nama.compareToIgnoreCase(karyawanList.get(j + 1).nama) > 0) {
					Karyawan temp = karyawanList.get(j);
					karyawanList.set(j, karyawanList.get(j + 1));
					karyawanList.set(j + 1, temp);
				}
			}
		}
		for (Karyawan x : karyawanList) {
			System.out.printf("|%-4s |%-18s |%-26s |%-17s |%-17s |%-18s |\n", no, x.kode, x.nama, x.kelamin, x.jabatan,
					x.gaji);
			no++;
		}

	}

	public void update() {
		if (karyawanList.isEmpty()) {
			System.out.println("No Data");
		}
		
		view();

		do {
			System.out.print("Input nomor urutan karyawan yang ingin diupdate: ");
			update = scan.nextInt();
			scan.nextLine();
		} while (update < 1 || update > karyawanList.size());

		String newkode = randID();
		do {
			System.out.print("Input nama karyawan [>=3]: ");
			newnama = scan.nextLine();
		} while (newnama.length() < 3);

		do {
			System.out.print("Input jenis kelamin [Laki-laki | Perempuan] (Case Sensitive): ");
			newkelamin = scan.nextLine();
		} while (!(newkelamin.equals("Laki-laki") || newkelamin.equals("Perempuan")));

		do {
			System.out.print("Input jabatan [Manager | Supervisor | Admin] (Case Sensitive): ");
			newjabatan = scan.nextLine();
		} while (!(newjabatan.equals("Manager") || newjabatan.equals("Supervisor") || newjabatan.equals("Admin")));

		if (newjabatan.equals("Manager")) {
			newgaji = 8000000;
		} else if (newjabatan.equals("Supervisor")) {
			newgaji = 6000000;
		} else if (newjabatan.equals("Admin")) {
			newgaji = 4000000;
		}

		System.out.println("Berhasil mengupdate karyawan dengan id " + karyawanList.get(update - 1).kode);
		System.out.println("ENTER to return");
		scan.nextLine();
		Karyawan newkaryawan = new Karyawan(newkode, newnama, newkelamin, newjabatan, newgaji);
		karyawanList.set(update - 1, newkaryawan);

	}

	public void delete() {
		if (karyawanList.isEmpty()) {
			System.out.println("No Data");
		}
		
		view();
		do {
			System.out.print("Input nomor urutan karyawan yang ingin dihapus: ");
			delete = scan.nextInt();
			scan.nextLine();
		} while (delete < 1 || delete > karyawanList.size());

		System.out.println("Karyawan dengan kode " + karyawanList.get(delete - 1).kode + " berhasil dihapus");
		System.out.println("ENTER to return");
		scan.nextLine();
		
		karyawanList.remove(delete-1);
		
	}

	public Main() {
		while (true)
			do {
				menu();
				switch (choose) {
				case 1:
					insert();
					break;
				case 2:
					view();
					System.out.printf("|%-4s|%-17s|%-25s|%-15s|%-15s|%-15s|\n","-----", "-------------------", "---------------------------", "------------------", "------------------", "-------------------");
					break;
				case 3:
					update();
					break;
				case 4:
					delete();
					break;

				default:
					break;
				}
			} while (choose < 1 || choose > 4);
	}

	public static void main(String[] args) {
		new Main();
	}

}
