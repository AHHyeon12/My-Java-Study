import java.util.Arrays;
import java.util.Scanner;

public class Admin {

	private Customer[] customer = new Customer[100];
	private Rooms[] rooms2 = new Rooms[20];
	private Rooms[] rooms3 = new Rooms[20];
	private Rooms[] rooms4 = new Rooms[20];
	private Rooms[] rooms5 = new Rooms[20];

	
	private static String adminCode;
	Scanner scanner = new Scanner(System.in);
	
	public Admin() {
		
		for(int i=0; i<20; i++) {
			this.rooms2[i].setRoomsInfo(0);	
		}
		
		
	}

	// 직원 선택시 보여주는 화면
	public void AdminProcess(int checkManger) {
		//System.out.println(Arrays.deepToString(rooms2));
		
		
		System.out.println("\n==============================");
		System.out.println("데스크 직원 모드");
		System.out.println("환영합니다.");
		System.out.println("1. 객실 예약하기");
		System.out.println("2. 객실 취소하기");
		System.out.println("3. 객실 현황확인");
		System.out.println("4. 이용자 예약 확인");
		System.out.println("5. 고객 정보 확인");
		System.out.println("\n==============================");
		System.out.print("입력 : ");
		String choose = scanner.nextLine();

		switch (choose) {
		case "1":

			break;
		case "2":
			break;
		case "3":
			mainRoom();
			break;
		case "4":
			break;
		case "5":
			break;
		default:
			break;
		}
	}

	// 청소직원 선택 시 보여주는 화면
	public void CleanerProcess() {
		System.out.println("\n==============================");
		System.out.println("청소 직원 모드");
		System.out.println("환영합니다.");
		System.out.println("1. 객실 현황확인");
		System.out.println("2. 청소하기");
		System.out.println("\n==============================");
		System.out.print("입력 : ");
		String choose = scanner.nextLine();

		switch (choose) {
		case "1":
			break;
		case "2":
			cleanRoom();
			break;
		default:
			break;

		}

	}

	// 고객 정보 확인(전화번호를 이용)
	public void customerInfoCheckByNumber() {
		System.out.println("\n==============================");
		System.out.println("고객 정보를 확인합니다.");
		System.out.println("확인할 고객의 전화번호를 입력하세요.");
		System.out.println("==============================");
		System.out.print("입력 : ");
		String name = scanner.next();
		scanner.nextLine();
		for (int i = 0; i < customer.length; i++) {
			if (customer[i].getPhoneNumber().equals(name)) {
				showCustomerInfo(i);
			}
		}
	}

	// 고객 정보 확인(이름을 이용)
	public void customerInfoCheckByName() {
		System.out.println("\n==============================");
		System.out.println("고객 정보를 확인합니다.");
		System.out.println("확인할 고객명을 입력하세요.");
		System.out.println("==============================");
		System.out.print("입력 : ");
		String name = scanner.next();
		scanner.nextLine();
		for (int i = 0; i < customer.length; i++) {
			if (customer[i].getRealUsersName().equals(name) || customer[i].getUsersName().equals(name)) {
				showCustomerInfo(i);
			}
		}
	}

	// 해당 고객 정보 보여주기(매니저)
	public void showCustomerInfo(int customerIndex) {
		System.out.println("\n==============================");
		System.out.println("예약 고객명 : " + customer[customerIndex].getUsersName());
		System.out.println("이용 고객명 : " + customer[customerIndex].getRealUsersName());
		System.out.println("예약 고객 전화번호 : ");
		System.out.println("이용 고객 전화번호 : ");
		System.out.println("예약한 방 : " + customer[customerIndex].getRooms().toString());
		System.out.println("==============================\n");
	}

	// 해당 고객 정보 보여주기(지배인)
	public void showCustomerInfo(int customerIndex, int checkManager) {
		System.out.println("\n==============================");
		System.out.println("예약 고객명 : " + customer[customerIndex].getUsersName());
		System.out.println("이용 고객명 : " + customer[customerIndex].getRealUsersName());
		System.out.println("예약 고객 전화번호 : ");
		System.out.println("이용 고객 전화번호 : ");
		if (checkManager == 1)
			System.out.println("이용 고객 주민번호 : ");
		System.out.println("예약한 방 : " + customer[customerIndex].getRooms().toString());
		System.out.println("==============================\n");
	}

	// 고객 정보 모두 보기
	public void showAllCustomerInfo() {

	}

	// 방을 청소하여 방 상태를 리턴
	public int cleanRoom() {
		System.out.println("방을 청소하시겠습니까?(Y/N)");
		String clean = scanner.next();
		scanner.nextLine();
		if (clean.equals("Y") || clean.equals("y")) {
			System.out.println("청소가 완료되었습니다.");
			return 0;
		} else {
			System.out.println("최대한 빠르게 청소를 해주세요.");
			return 3;
		}

	}

	///////////////////////////////////////////////////////

	// 싱글 베드 : 방번호의 끝자리 수는 홀수이다.
	// 더블 베드 : 방번호의 끝자리 수는 짝수이다.
	// 배열값 0 은 빈방
	// 배열값 1 은 빈방 아님
	// 배열값 2 는 손님이 체크아웃 후 청소직원이 치워야할 방
	// 청소직원이 방을 치운 후에는 배열값을 0으로 바꾸어 다시 사용가능

	// 객실 현황 확인
	public void mainRoom() {
		System.out.println();
		System.out.println("원하는 층수를 선택해주세요");
		System.out.println("객실 현황");
		System.out.println("1. 2층 빈 객실 : " + emptyRoomCheck(2) + "객실");
		System.out.println("2. 3층 빈 객실 : " + emptyRoomCheck(3) + "객실");
		System.out.println("3. 4층 빈 객실 : " + emptyRoomCheck(4) + "객실");
		System.out.println("4. 5층 빈 객실 : " + emptyRoomCheck(5) + "객실");

	}

	// 메소드에 입력된 층수의 빈 방이 몇 개 있는지 출력
	public int emptyRoomCheck(int floorNum) {

		int result = 0;

		if (rooms2 != null && floorNum == 2) {
			for (int i = 0; i < 20; i++) {
				if(rooms2[i] !=null) {
				if ( rooms2[i].getRoomsInfo() == 0) {
					result++;
				}
				}
			}
		}

		if (rooms3!= null && floorNum == 3) {
			for (int i = 0; i < 20; i++) {
				if (rooms3[i].getRoomsInfo() == 0) {
					result++;
				}
			}
		}

		if (rooms4!= null && floorNum == 4) {
			for (int i = 0; i < 20; i++) {
				if ( rooms4[i].getRoomsInfo() == 0) {
					result++;
				}
			}
		}

		if (rooms5!= null && floorNum == 5) {
			for (int i = 0; i < 20; i++) {
				if ( rooms5[i].getRoomsInfo() == 0) {
					result++;
				}
			}
		}

		return result;
	}

	// 사용자가 방을 고를 때 201같은 번호로 입력하기 때문에 그 값을 직접 대입할 인덱스 번호로
	// 변환하는 메소드
	// 220을
	public int roomNumberChange(int num) {
		if (num >= 200 && 220 >= num) {
			return num - 200;
		} else if (num >= 300 && 320 >= num) {
			return num - 300;
		} else if (num >= 400 && 420 >= num) {
			return num - 400;
		} else if (num >= 500 && 520 >= num) {
			return num - 500;
		} else {
			System.out.println("정상적인 번호를 입력해주세요");
			System.out.println("예시 201호실은 201을 입력");
			return -1;
		}

	}

	public void chooseRoom(int floorNum) {

		System.out.println();
		System.out.println("원하는 객실을 선택해주세요");

		if (floorNum == 2) {
			for (int i = 0; i < 20; i++) {
				if (rooms2[i].getRoomsInfo() == 0) {
					System.out.printf("2%02d호실 비었음\n", i + 1);
				} else if (rooms2[i].getRoomsInfo() == 1) {
					System.out.printf("2%02d호실 예약완료\n", i + 1);
				} else if (rooms2[i].getRoomsInfo() == 2) {
					System.out.printf("2%02d호실 청소중\n", i + 1);
				}

			}
		}

		if (floorNum == 3) {
			for (int i = 0; i < 20; i++) {
				if (rooms3[i].getRoomsInfo() == 0) {
					System.out.printf("3%02d호실 비었음\n", i + 1);
				} else if (rooms3[i].getRoomsInfo() == 1) {
					System.out.printf("3%02d호실 예약완료\n", i + 1);
				} else if (rooms3[i].getRoomsInfo() == 2) {
					System.out.printf("3%02d호실 청소중\n", i + 1);
				}
			}
		}

		if (floorNum == 4) {
			for (int i = 0; i < 20; i++) {
				if (rooms4[i].getRoomsInfo() == 0) {
					System.out.printf("4%02d호실 비었음\n", i + 1);
				} else if (rooms4[i].getRoomsInfo() == 1) {
					System.out.printf("4%02d호실 예약완료\n", i + 1);
				} else if (rooms4[i].getRoomsInfo() == 2) {
					System.out.printf("4%02d호실 청소중\n", i + 1);
				}
			}
		}

		if (floorNum == 5) {
			for (int i = 0; i < 20; i++) {
				if (rooms5[i].getRoomsInfo() == 0) {
					System.out.printf("5%02d호실 비었음\n", i + 1);
				} else if (rooms5[i].getRoomsInfo() == 1) {
					System.out.printf("5%02d호실 예약완료\n", i + 1);
				} else if (rooms5[i].getRoomsInfo() == 2) {
					System.out.printf("5%02d호실 청소중\n", i + 1);
				}
			}
		}

	}

	public void reservation(int floorNum, int roomNum) {
		if (floorNum == 2) {
			if (rooms2[roomNum].getRoomsInfo() == 0) {
				rooms2[roomNum].setRoomsInfo(1);
				System.out.printf("%d층 %d호실 예약이 완료되었습니다.\n", floorNum, roomNum + 1);
			} else {
				System.out.println("예약할 수 없습니다.");
			}
		} else if (floorNum == 3) {
			if (rooms3[roomNum].getRoomsInfo() == 0) {
				rooms3[roomNum].setRoomsInfo(1);
				System.out.printf("%d층 %d호실 예약이 완료되었습니다.\n", floorNum, roomNum + 1);
			} else {
				System.out.println("예약할 수 없습니다.");
			}
		} else if (floorNum == 4) {
			if (rooms4[roomNum].getRoomsInfo() == 0) {
				rooms4[roomNum].setRoomsInfo(1);
				System.out.printf("%d층 %d호실 예약이 완료되었습니다.\n", floorNum, roomNum + 1);
			} else {
				System.out.println("예약할 수 없습니다.");
			}
		} else if (floorNum == 5) {
			if (rooms5[roomNum].getRoomsInfo() == 0) {
				rooms5[roomNum].setRoomsInfo(1);
				System.out.printf("%d층 %d호실 예약이 완료되었습니다.\n", floorNum, roomNum + 1);
			} else {
				System.out.println("예약할 수 없습니다.");
			}
		} else {
			System.out.println("정상적인 층수를 입력해주세요");
		}

	}
}
