import java.util.Scanner;

public class Screen {

	private int checkManger; // admin 파라미터에서 확인 가능
	Scanner scanner = new Scanner(System.in);

	public void staffScreen() {
		System.out.println("관리자 번호를 입력하세요");
		int managenum = scanner.nextInt();
		Admin admin = new Admin();

		int input = scanner.nextInt();

		switch (input) {
		case 1:
			System.out.println("관리자 메뉴 화면입니다.");
			admin.AdminProcess(input);

			break;

		case 2:
			System.out.println("청소부 메뉴 화면입니다.");
			admin.CleanerProcess();

			break;
		}
		// 지배인/ 데스크직원 / 청소부

	}
}