import java.util.Scanner;

public class Test {
	public static void main(String[] args) {
		Rooms room = new Rooms();
		Scanner scanner = new Scanner(System.in);
		Admin ad = new Admin();

		System.out.println("관리자 번호 입력");
		int aa = scanner.nextInt();

		// int result = ad.AdminProcess(aa);

		while (true) {

			ad.AdminProcess(1);
			
		}

	}
}
