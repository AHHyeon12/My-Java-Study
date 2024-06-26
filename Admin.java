import java.util.Arrays;
import java.util.Scanner;

public class Admin {

	private Customer[] customer = new Customer[100];
	private Rooms[] rooms2 = new Rooms[20];
	private Rooms[] rooms3 = new Rooms[20];
	private Rooms[] rooms4 = new Rooms[20];
	private Rooms[] rooms5 = new Rooms[20];

	// private static String adminCode;
	Scanner scanner = new Scanner(System.in);

	public Admin() {

		for (int i = 0; i < 100; i++) {
			this.customer[i] = new Customer();
		}

		for (int i = 0; i < 20; i++) {
			if (i % 2 != 0) {
				this.rooms2[i] = new Rooms("single"); // Rooms 객체 생성
				this.rooms3[i] = new Rooms("single"); // Rooms 객체 생성
				this.rooms4[i] = new Rooms("single"); // Rooms 객체 생성
				this.rooms5[i] = new Rooms("single"); // Rooms 객체 생성
			} else {
				this.rooms2[i] = new Rooms("double"); // Rooms 객체 생성
				this.rooms3[i] = new Rooms("double"); // Rooms 객체 생성
				this.rooms4[i] = new Rooms("double"); // Rooms 객체 생성
				this.rooms5[i] = new Rooms("double");
			}

		}

	}

	// 데스크 직원 코드 입력시 보여주는 화면
	public void AdminProcess(int checkManager) {
		// System.out.println(Arrays.deepToString(rooms2));

		System.out.println("\n==============================");
		if (checkManager == 1)
			System.out.println("지배인 모드");
		else
			System.out.println("데스크 직원 모드");

		System.out.println("환영합니다.");
		System.out.println("1. 객실 예약하기");
		System.out.println("2. 객실 취소하기");
		System.out.println("3. 객실 현황확인");
		System.out.println("4. 이용자 예약 확인");
		System.out.println("5. 전체 고객 정보 확인");
		System.out.println("\n==============================");
		System.out.print("입력 : ");
		String choose = scanner.next();
		scanner.nextLine();
		switch (choose) {
		case "1":
			makeCustomerInfo(checkManager); // 고객 정보 만들기로 이동
			break;
		case "2":
			customerInfoCheckChoose(checkManager); // 고객 정보 확인 창으로 이동
			break; // 정보 확인 후 예약한 방을 취소할 수 있는 기능 포함.
		case "3":
			mainRoom("3", -1); // 미구현
			break;
		case "4":
			customerInfoCheckChoose(checkManager); // 고객의 특정 정보로 고객을 찾는 기능
			break; // 예약을 취소할 수 있는 기능을 포함
		case "5":
			showAllCustomerInfo(checkManager);
			break;
		case "6":
			roomInfoChange();
			break;
		default:
			break;
		}

	}

	public void roomInfoChange() {
		System.out.println("방 상태 변경 메뉴입니다.");
		System.out.println("어떻게 변경하겠습니까?");
		System.out.println("1. 체크인으로 변경");
		System.out.println("2. 체크아웃으로 변경");
		String choose = scanner.next();
		scanner.nextLine();
		switch (choose) {
		case "1":
			makeRoomCheckIn();
			break;
		case "2":
			makeRoomCheckOut();
			break;
		default:
			System.out.println("올바른 번호를 입력하세요.");
			break;
		}
	}

	// 고객 정보 입력
	public void makeCustomerInfo(int checkManager) {

		System.out.println("\n==============================");
		System.out.println("고객 정보를 입력합니다.");
		System.out.println("이용하실 고객 이름을 입력하세요.");
		System.out.print("입력 : ");
		String realUserName = scanner.next();
		scanner.nextLine();
		System.out.println("이용하실 고객 전화번호를 입력하세요.");
		System.out.print("입력 : ");
		String realPhoneNumber = scanner.next();
		scanner.nextLine();
		System.out.println("예약자 고객 이름을 입력하세요.");
		System.out.print("입력 : ");
		String usersName = scanner.next();
		scanner.nextLine();
		System.out.println("예약자 고객 전화번호를 입력하세요.");
		System.out.print("입력 : ");
		String phoneNumber = scanner.next();
		scanner.nextLine();
		System.out.println("이용 고객 주민번호를 입력하세요.");
		System.out.print("입력 : ");
		String personalNumber = scanner.next();
		scanner.nextLine();
		int i = 0;
		for (i = 0; i < customer.length; i++) {

			// 만약 이전에 이름과 전화번호가 모두 동일한 사람이 예약한 이력이 확인되는 경우
			// 같은 사람으로 간주하며 손님인덱스 다음 배열에 새로 추가하는 것이 아닌
			// 기존의 입력한 사람의 인덱스 번호를 들고와 추가 예약을 진행
			if (customer[i].getPhoneNumber().equals(phoneNumber) && customer[i].getUsersName().equals(usersName)) {
				System.out.println("이미 예약한 내역이 존재합니다.");
				System.out.println("추가 예약하시겠습니까?(Y/N)");
				String reservationPlus = scanner.next();
				scanner.nextLine();
				if (reservationPlus.equals("Y") || reservationPlus.equals("y")) {
					plusCustomerRoom(i);
					break;
				} else {
					System.out.println("정상적인 입력값이 아닙니다.");
					return;
				}

				// 손님인덱스 customer[i]의 이름이 공백인 경우 아래를 진행하고 공백이 아니면 i값을 늘림.
			} else if (customer[i].getUsersName() == "") {

				this.customer[i].setPersonalNumber(personalNumber);
				this.customer[i].setPhoneNumber(phoneNumber);
				this.customer[i].setRealphoneNumber(realPhoneNumber);
				this.customer[i].setRealUsersName(realUserName);
				this.customer[i].setUsersName(usersName);
				mainRoom("1", i);
				break;
			}
		}

	}

	// 고객 검색 방법 선택
	public void customerInfoCheckChoose(int checkManager) {
		System.out.println("\n==============================");
		System.out.println("고객 정보를 검색합니다.");
		System.out.println("어떤 방법으로 검색하시겠습니까?");
		System.out.println("1.고객 이름으로 찾기");
		System.out.println("2. 고객 전화번호로 찾기");
		System.out.println("==============================");
		System.out.print("입력 : ");

		String choose = scanner.next();
		scanner.nextLine();

		switch (choose) {
		case "1":
			customerInfoCheckByName(checkManager);
			break;
		case "2":
			customerInfoCheckByNumber(checkManager);
			break;
		default:
			System.out.println("올바른 번호를 입력하세요.");
			break;
		}

	}

	// 고객 정보 확인(전화번호를 이용)
	public void customerInfoCheckByNumber(int checkManager) {
		System.out.println("\n==============================");
		System.out.println("고객 정보를 확인합니다.");
		System.out.println("확인할 고객의 전화번호를 입력하세요.");
		System.out.println("==============================");
		System.out.print("입력 : ");
		String phoneNumber = scanner.next();
		scanner.nextLine();

		for (int i = 0; i < customer.length; i++) {
			if (customer[i].getPhoneNumber().equals(phoneNumber)
					|| customer[i].getRealphoneNumber().equals(phoneNumber)) {
				showCustomerInfo(i, checkManager);
				return;
			}
		}
		System.out.println("해당하는 고객이 없습니다.");
	}

	// 고객 정보 확인(이름을 이용)
	public void customerInfoCheckByName(int checkManager) {
		System.out.println("\n==============================");
		System.out.println("고객 정보를 확인합니다.");
		System.out.println("확인할 고객명을 입력하세요.");
		System.out.println("==============================");
		System.out.print("입력 : ");
		String name = scanner.next();
		scanner.nextLine();

		for (int i = 0; i < customer.length; i++) {

			if (customer[i].getRealUsersName().equals(name) || customer[i].getUsersName().equals(name)) {
				showCustomerInfo(i, checkManager);
				return;
			}
		}

		System.out.println("해당하는 고객이 없습니다.");

	}

	// 해당 고객 정보 보여주기
	public void showCustomerInfo(int customerIndex, int checkManager) {
		System.out.println("\n==============================");
		System.out.println("예약 고객명 : " + customer[customerIndex].getUsersName());
		System.out.println("이용 고객명 : " + customer[customerIndex].getRealUsersName());
		System.out.println("예약 고객 전화번호 : " + customer[customerIndex].getPhoneNumber());
		System.out.println("이용 고객 전화번호 : " + customer[customerIndex].getRealphoneNumber());
		if (checkManager == 1)
			System.out.println("이용 고객 주민번호 : " + customer[customerIndex].getPersonalNumber());
		for (int i = 0; i < 3; i++) {
			if (customer[customerIndex].getRooms()[i] != 0)
				System.out.println("예약한 방 : " + customer[customerIndex].getRooms()[i]);
		}
		// 취소할 수 있는 기능을 포함
		System.out.println("==============================\n");
		System.out.println("예약을 취소하시겠습니까?(Y/N)");
		System.out.print("입력 : ");
		String yesOrNo = scanner.next();
		scanner.nextLine();
		if (yesOrNo.equals("Y") || yesOrNo.equals("y")) {
			mainRoom("2", customerIndex);
		} else if (yesOrNo.equals("N") || yesOrNo.equals("n")) {
			System.out.println("이전 메뉴로 돌아갑니다.");
		} else {
			System.out.println("올바른 입력을 하세요.");
		}

	}

	// 고객 정보 모두 보기
	public void showAllCustomerInfo(int checkManager) {

		System.out.println("예약한 고객 정보를 모두 출력합니다.");
		System.out.println("어떤 식으로 보시겠습니까?");
		System.out.println("1. 층 별로 보기");
		System.out.println("2. 예약 순으로 보기");
		String choose = scanner.next();
		scanner.nextLine();

		switch (choose) {
		case "1":
			showFloorCustomer(checkManager);
			break;
		}

	}

	// 층 별로 고객 내용 출력 (미완성)
	public void showFloorCustomer(int checkManager) {

		System.out.println("2층 예약 중이거나 이용중인 고객");
		for (int i = 0; i < rooms2.length; i++) {

			if (rooms2[i].getRoomsInfo() == 1 || rooms2[i].getRoomsInfo() == 2) {
				int roomNumber = 201 + i;

				for (int j = 0; j < customer.length; j++) {
					for (int k = 0; k < 3; k++) {
						if (customer[j].getRooms()[k] == roomNumber) {
							System.out.println("\n==============================");
							System.out.println("예약한 방 번호 : " + roomNumber);
							System.out.println("예약자 이름 : " + customer[j].getUsersName());
							System.out.println("예약자 전화번호 : " + customer[j].getPhoneNumber());
							System.out.println("이용자 이름 : " + customer[j].getRealUsersName());
							System.out.println("이용자 전화번호 : " + customer[j].getRealphoneNumber());
							if (checkManager == 1)
								System.out.println("이용자 주민번호 : " + customer[j].getPersonalNumber());
							System.out.println("==============================\n");

						}
					}
				}
			}
		}
	}

	public int cleanRoom() {
		int a = 414;

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

	public void plusCustomerRoom(int customerIndex) {

		// 이미 예약한 방의 개수가 3개를 넘어가는 경우 아래 메소드 실행 및 예약 불가능하게 return으로 빠져나옴
		int checkRoomEmpty = 0;
		for (int a = 0; a < 3; a++) {
			if (customer[customerIndex].getRooms()[a] != 0)
				checkRoomEmpty++;
		}
		if (checkRoomEmpty == 3) {
			System.out.println("방은 3개까지만 예약 가능합니다.");
			return;
		}

		// 예약한 방의 개수가 3개 이하인 경우 아래를 실행
		System.out.println();
		System.out.println("원하는 층수를 선택해주세요");
		System.out.println("객실 현황");
		System.out.println("1. 2층 빈 객실 : " + emptyRoomCheck(2) + "객실");
		System.out.println("2. 3층 빈 객실 : " + emptyRoomCheck(3) + "객실");
		System.out.println("3. 4층 빈 객실 : " + emptyRoomCheck(4) + "객실");
		System.out.println("4. 5층 빈 객실 : " + emptyRoomCheck(5) + "객실");

		int chooseFloor = scanner.nextInt();
		int oneDigitNumber = chooseRoom(chooseFloor + 1);
		int chooseRoom = scanner.nextInt();

		// chooseRoom메소드를 통하여 싱글, 더블 중 선택한 내용에 따라 1, 0 값을 oneDigitNumber로 받아오고
		// 싱글을 선택했는데 짝수번호의 방을 고르거나 방의 인덱스값을 넘어가는 방 번호를 입력한 경우 아래를 실행 하고
		// 다시 값을 제대로 받아옴. (싱글은 홀수의 방만 존재하고 입력한 값을 2로 나눈 나머지가 반드시 1이 나와야 함)
		// 더블의 경우에도 동일. (더블은 짝수의 방만 존재하고 입력한 값을 2로 나눈 나머지가 반드시 0이 나와야 함)
		while (chooseRoom % 2 != oneDigitNumber || chooseRoom % 100 > 20) {
			System.out.println("없는 방 번호입니다.");
			chooseRoom = scanner.nextInt();
		}

		if (customerIndex >= 0) {
			reservation(chooseFloor + 1, roomNumberChange(chooseRoom), chooseRoom, customerIndex);
		}
	}

	// makeCustomerInfo(고객정보작성) 메소드 또는, customerInfoCheckChoose(고객정보확인) 메소드를 통하여 넘어옴
	// 객실 현황 확인을 겸하며 mainSelectNum의 값에 따라 예약, 취소, 현황 등등을 확인할 수 있음.

	public void mainRoom(String divideNum, int customerIndex) {

		if (divideNum.equals("1")) {
			// 예약 할 때 아래 메소드 출력
			System.out.println();
			System.out.println("원하는 층수를 선택해주세요");
			System.out.println("객실 현황");
			System.out.println("1. 2층 빈 객실 : " + emptyRoomCheck(2) + "객실");
			System.out.println("2. 3층 빈 객실 : " + emptyRoomCheck(3) + "객실");
			System.out.println("3. 4층 빈 객실 : " + emptyRoomCheck(4) + "객실");
			System.out.println("4. 5층 빈 객실 : " + emptyRoomCheck(5) + "객실");

			int chooseFloor = scanner.nextInt();
			int oneDigitNumber = chooseRoom(chooseFloor + 1);
			int chooseRoom = scanner.nextInt();

			while (chooseRoom % 2 != oneDigitNumber) {
				System.out.println("없는 방 번호입니다.");
				chooseRoom = scanner.nextInt();
			}
			if (customerIndex >= 0) {
				reservation(chooseFloor + 1, roomNumberChange(chooseRoom), chooseRoom, customerIndex);
			}

		} else if (divideNum.equals("2")) {
			// 취소할 때 아래 메소드 출력
			System.out.println("예약자 : " + customer[customerIndex].getUsersName());
			System.out.println("실 이용자 : " + customer[customerIndex].getRealUsersName());

			int reservationRoomCount = 0;
			for (int j = 0; j < customer[customerIndex].getRooms().length; j++) {
				if (customer[customerIndex].getRooms()[j] != 0) {
					System.out.println("현재 예약중인 방 : " + customer[customerIndex].getRooms()[j] + "호실");
					reservationRoomCount++;
				}

			}

			if (reservationRoomCount == 0) {
				System.out.println("예약하신 방이 없습니다.");
			} else {
				System.out.println("취소하실 방 번호를 입력해주세요");
				System.out.print("입력 : ");
				int cancel = scanner.nextInt();
				int checkZero = 0;

				if (cancel / 100 == 2) {
					for (int j = 0; j < 3; j++) {
						if (customer[customerIndex].getRooms()[j] == cancel) {
							System.out.println(cancel + "호 예약이 취소되었습니다.");
							customer[customerIndex].cancelRoom(j);
							rooms2[roomNumberChange(cancel)].setCustomerName("");
							rooms2[roomNumberChange(cancel)].setRoomsInfo(0);
						}
						if (customer[customerIndex].getRooms()[j] == 0) {
							checkZero++;
						}
					}
					if (checkZero == 3) {
						for (int k = 0; k < customer.length; k++) {
							customer[k].setPersonalNumber("");
							customer[k].setPhoneNumber("");
							customer[k].setUsersName("");
							customer[k].setRealphoneNumber("");
							customer[k].setRealUsersName("");
						}
					}

				} else if (cancel / 100 == 3) {
					for (int j = 0; j < 3; j++) {
						if (customer[customerIndex].getRooms()[j] == cancel) {
							System.out.println(cancel + "호 예약이 취소되었습니다.");
							customer[customerIndex].cancelRoom(j);
							rooms3[roomNumberChange(cancel)].setCustomerName("");
							rooms3[roomNumberChange(cancel)].setRoomsInfo(0);
						}
						if (customer[customerIndex].getRooms()[j] == 0) {
							checkZero++;
						}
					}
					if (checkZero == 3) {
						for (int k = 0; k < customer.length; k++) {
							customer[k].setPersonalNumber("");
							customer[k].setPhoneNumber("");
							customer[k].setUsersName("");
							customer[k].setRealphoneNumber("");
							customer[k].setRealUsersName("");
						}
					}

				} else if (cancel / 100 == 4) {
					for (int j = 0; j < 3; j++) {
						if (customer[customerIndex].getRooms()[j] == cancel) {
							System.out.println(cancel + "호 예약이 취소되었습니다.");
							customer[customerIndex].cancelRoom(j);
							rooms4[roomNumberChange(cancel)].setCustomerName("");
							rooms4[roomNumberChange(cancel)].setRoomsInfo(0);
						}
						if (customer[customerIndex].getRooms()[j] == 0) {
							checkZero++;
						}
					}
					if (checkZero == 3) {
						for (int k = 0; k < customer.length; k++) {
							customer[k].setPersonalNumber("");
							customer[k].setPhoneNumber("");
							customer[k].setUsersName("");
							customer[k].setRealphoneNumber("");
							customer[k].setRealUsersName("");
						}
					}

				} else if (cancel / 100 == 5) {
					for (int j = 0; j < 3; j++) {
						if (customer[customerIndex].getRooms()[j] == cancel) {
							System.out.println(cancel + "호 예약이 취소되었습니다.");
							customer[customerIndex].cancelRoom(j);
							rooms5[roomNumberChange(cancel)].setCustomerName("");
							rooms5[roomNumberChange(cancel)].setRoomsInfo(0);
						}
						if (customer[customerIndex].getRooms()[j] == 0) {
							checkZero++;
						}
					}
					if (checkZero == 3) {
						for (int k = 0; k < customer.length; k++) {
							customer[k].setPersonalNumber("");
							customer[k].setPhoneNumber("");
							customer[k].setUsersName("");
							customer[k].setRealphoneNumber("");
							customer[k].setRealUsersName("");
						}
					}

				}
			}
			// 여기서 부터 3번째 메뉴를 골랐을 때 내용을 작성할 예정
		} else if (divideNum.equals("3")) {

		}

	}

	// 원하는 타입의 방을 고르고 그 타입에 해당하는 방들을 출력하며
	// 고른 타입에 따라 0 또는 1을 반환
	// 반환값은 출력해주는 방들 중 해당하지 않는 방을 입력할 때, 제대로 입력했는지 판단하기 위하여 사용
	public int chooseRoom(int floorNum) {

		System.out.println();

		while (true) {
			System.out.println("방의 타입을 선택해주세요.");
			System.out.println("1. Single 룸");
			System.out.println("2. Double 룸");
			int input = scanner.nextInt();
			if (input == 1) {
				System.out.println("원하는 객실을 선택해주세요");

				if (floorNum == 2) {
					for (int i = 0; i < 20; i++) {
						if (rooms2[i].getRoomsInfo() == 0 && i % 2 == 0) {
							System.out.printf("2%02d호실 비었음\n", i + 1);
						} else if (rooms2[i].getRoomsInfo() == 1 && i % 2 == 0) {
							System.out.printf("2%02d호실 예약완료\n", i + 1);
						} else if (rooms2[i].getRoomsInfo() == 2 && i % 2 == 0) {
							System.out.printf("2%02d호실 청소중\n", i + 1);
						}

					}
				}

				if (floorNum == 3) {
					for (int i = 0; i < 20; i++) {
						if (rooms3[i].getRoomsInfo() == 0 && i % 2 == 0) {
							System.out.printf("3%02d호실 비었음\n", i + 1);
						} else if (rooms3[i].getRoomsInfo() == 1 && i % 2 == 0) {
							System.out.printf("3%02d호실 예약완료\n", i + 1);
						} else if (rooms3[i].getRoomsInfo() == 2 && i % 2 == 0) {
							System.out.printf("3%02d호실 청소중\n", i + 1);
						}
					}
				}

				if (floorNum == 4) {
					for (int i = 0; i < 20; i++) {
						if (rooms4[i].getRoomsInfo() == 0 && i % 2 == 0) {
							System.out.printf("4%02d호실 비었음\n", i + 1);
						} else if (rooms4[i].getRoomsInfo() == 1 && i % 2 == 0) {
							System.out.printf("4%02d호실 예약완료\n", i + 1);
						} else if (rooms4[i].getRoomsInfo() == 2 && i % 2 == 0) {
							System.out.printf("4%02d호실 청소중\n", i + 1);
						}
					}
				}

				if (floorNum == 5) {
					for (int i = 0; i < 20; i++) {
						if (rooms5[i].getRoomsInfo() == 0 && i % 2 == 0) {
							System.out.printf("5%02d호실 비었음\n", i + 1);
						} else if (rooms5[i].getRoomsInfo() == 1 && i % 2 == 0) {
							System.out.printf("5%02d호실 예약완료\n", i + 1);
						} else if (rooms5[i].getRoomsInfo() == 2 && i % 2 == 0) {
							System.out.printf("5%02d호실 청소중\n", i + 1);
						}
					}
				}
				return 1;
			} else if (input == 2) {
				System.out.println("원하는 객실을 선택해주세요");
				System.out.println();
				if (floorNum == 2) {
					for (int i = 0; i < 20; i++) {
						if (rooms2[i].getRoomsInfo() == 0 && i % 2 != 0) {
							System.out.printf("2%02d호실 비었음\n", i + 1);
						} else if (rooms2[i].getRoomsInfo() == 1 && i % 2 != 0) {
							System.out.printf("2%02d호실 예약완료\n", i + 1);
						} else if (rooms2[i].getRoomsInfo() == 2 && i % 2 != 0) {
							System.out.printf("2%02d호실 청소중\n", i + 1);
						}

					}
				}

				if (floorNum == 3) {
					for (int i = 0; i < 20; i++) {
						if (rooms3[i].getRoomsInfo() == 0 && i % 2 != 0) {
							System.out.printf("3%02d호실 비었음\n", i + 1);
						} else if (rooms3[i].getRoomsInfo() == 1 && i % 2 != 0) {
							System.out.printf("3%02d호실 예약완료\n", i + 1);
						} else if (rooms3[i].getRoomsInfo() == 2 && i % 2 != 0) {
							System.out.printf("3%02d호실 청소중\n", i + 1);
						}
					}
				}

				if (floorNum == 4) {
					for (int i = 0; i < 20; i++) {
						if (rooms4[i].getRoomsInfo() == 0 && i % 2 != 0) {
							System.out.printf("4%02d호실 비었음\n", i + 1);
						} else if (rooms4[i].getRoomsInfo() == 1 && i % 2 != 0) {
							System.out.printf("4%02d호실 예약완료\n", i + 1);
						} else if (rooms4[i].getRoomsInfo() == 2 && i % 2 != 0) {
							System.out.printf("4%02d호실 청소중\n", i + 1);
						}
					}
				}

				if (floorNum == 5) {
					for (int i = 0; i < 20; i++) {
						if (rooms5[i].getRoomsInfo() == 0 && i % 2 != 0) {
							System.out.printf("5%02d호실 비었음\n", i + 1);
						} else if (rooms5[i].getRoomsInfo() == 1 && i % 2 != 0) {
							System.out.printf("5%02d호실 예약완료\n", i + 1);
						} else if (rooms5[i].getRoomsInfo() == 2 && i % 2 != 0) {
							System.out.printf("5%02d호실 청소중\n", i + 1);
						}
					}
				}
				return 0;
			} else {
				System.out.println("정상적인 번호를 입력해주세요");
			}

		}

	}

	// 고객과 방의 정보들을 예약하는 메소드
	// 파라미터값은 순서대로 층수, 방번호를 인덱스 값으로 바꾼 수, 사용자가 실제로 입력한 방번호, 손님의 인덱스
	// 방들의 배열이 rooms2 ~ rooms5까지 층별로 나누었기 때문에 floorNum값(층수)에 따라 if문을 구성
	public void reservation(int floorNum, int indexVerRoomNum, int basicRoomNum, int customerIndex) {

		if (floorNum == 2) {
			if (rooms2[indexVerRoomNum].getRoomsInfo() == 0) {
				rooms2[indexVerRoomNum].setRoomsInfo(1);
				System.out.printf("%d층 %d호실 예약이 완료되었습니다.\n", floorNum, basicRoomNum);
				System.out.printf("%s 고객이 예약했습니다.", customer[customerIndex].getUsersName());

				for (int i = 0; i < 3; i++) {
					if (customer[customerIndex].getRooms()[i] == 0) {
						customer[customerIndex].settingRooms(basicRoomNum);
						rooms2[indexVerRoomNum].setCustomerName(customer[customerIndex].getUsersName());
						rooms2[indexVerRoomNum].setRealCustomerName(customer[customerIndex].getRealUsersName());
						break;
					}

				}
			} else {
				System.out.println("예약할 수 없습니다.");
			}
		} else if (floorNum == 3) {
			if (rooms3[indexVerRoomNum].getRoomsInfo() == 0) {
				rooms3[indexVerRoomNum].setRoomsInfo(1);
				System.out.printf("%d층 %d호실 예약이 완료되었습니다.\n", floorNum, basicRoomNum);
				System.out.printf("%s 고객이 예약했습니다.", customer[customerIndex].getUsersName());

				for (int i = 0; i < 3; i++) {
					if (customer[customerIndex].getRooms()[i] == 0) {
						customer[customerIndex].settingRooms(basicRoomNum);
						rooms3[indexVerRoomNum].setCustomerName(customer[customerIndex].getUsersName());
						rooms3[indexVerRoomNum].setRealCustomerName(customer[customerIndex].getRealUsersName());
						break;
					}

				}
			} else {
				System.out.println("예약할 수 없습니다.");
			}
		} else if (floorNum == 4) {
			if (rooms4[indexVerRoomNum].getRoomsInfo() == 0) {
				rooms4[indexVerRoomNum].setRoomsInfo(1);
				System.out.printf("%d층 %d호실 예약이 완료되었습니다.\n", floorNum, basicRoomNum);
				System.out.printf("%s 고객이 예약했습니다.", customer[customerIndex].getUsersName());

				for (int i = 0; i < 3; i++) {
					if (customer[customerIndex].getRooms()[i] == 0) {
						customer[customerIndex].settingRooms(basicRoomNum);
						rooms4[indexVerRoomNum].setCustomerName(customer[customerIndex].getUsersName());
						rooms4[indexVerRoomNum].setRealCustomerName(customer[customerIndex].getRealUsersName());
						break;
					}

				}
			} else {
				System.out.println("예약할 수 없습니다.");
			}
		} else if (floorNum == 5) {
			if (rooms5[indexVerRoomNum].getRoomsInfo() == 0) {
				rooms5[indexVerRoomNum].setRoomsInfo(1);
				System.out.printf("%d층 %d호실 예약이 완료되었습니다.\n", floorNum, basicRoomNum);
				System.out.printf("%s 고객이 예약했습니다.", customer[customerIndex].getUsersName());

				for (int i = 0; i < 3; i++) {
					if (customer[customerIndex].getRooms()[i] == 0) {
						customer[customerIndex].settingRooms(basicRoomNum);
						rooms5[indexVerRoomNum].setCustomerName(customer[customerIndex].getUsersName());
						rooms5[indexVerRoomNum].setRealCustomerName(customer[customerIndex].getRealUsersName());
						break;
					}

				}
			} else {
				System.out.println("예약할 수 없습니다.");
			}
		} else {
			System.out.println("정상적인 층수를 입력해주세요");
		}

	}

	// 메소드에 입력된 층수의 빈 방이 몇 개 있는지 출력하기 위해 작성
	public int emptyRoomCheck(int floorNum) {

		int result = 0;

		if (rooms2 != null && floorNum == 2) {
			for (int i = 0; i < 20; i++) {

				if (rooms2[i].getRoomsInfo() == 0) {
					result++;
				}
			}
		}

		if (rooms3 != null && floorNum == 3) {
			for (int i = 0; i < 20; i++) {
				if (rooms3[i].getRoomsInfo() == 0) {
					result++;
				}
			}
		}

		if (rooms4 != null && floorNum == 4) {
			for (int i = 0; i < 20; i++) {
				if (rooms4[i].getRoomsInfo() == 0) {
					result++;
				}
			}
		}

		if (rooms5 != null && floorNum == 5) {
			for (int i = 0; i < 20; i++) {
				if (rooms5[i].getRoomsInfo() == 0) {
					result++;
				}
			}
		}

		return result;
	}

	// 사용자가 방을 고를 때 201같은 번호로 입력하기 때문에 그 값을 직접 대입할 인덱스 번호로 변환하는 메소드
	// ex : 220을 고르면 100의 자리를 제외하고 남은 값에서 -1값을 출력 (19)

	public int showRoomInfo(int roomsNum) {
		int resultNum = 0;
		if (roomsNum / 100 == 2)
			resultNum = rooms2[roomNumberChange(roomsNum)].getRoomsInfo();
		else if (roomsNum / 100 == 3)
			resultNum = rooms3[roomNumberChange(roomsNum)].getRoomsInfo();
		else if (roomsNum / 100 == 4)
			resultNum = rooms4[roomNumberChange(roomsNum)].getRoomsInfo();
		else if (roomsNum / 100 == 5)
			resultNum = rooms5[roomNumberChange(roomsNum)].getRoomsInfo();

		if (resultNum == 0) {
			System.out.println(roomsNum + "번 방은 현재 빈 방입니다.");
			return 0;
		} else if (resultNum == 1) {
			System.out.println(roomsNum + "번 방은 현재 예약 중입니다.");
			return 1;
		} else if (resultNum == 2) {
			System.out.println(roomsNum + "번 방은 현재 체크인 상태입니다.");
			return 2;
		} else if (resultNum == 3) {
			System.out.println(roomsNum + "번 방은 현재 체크 아웃입니다.");
			return 3;
		}

		return -1;

	}

	public void makeRoomCheckIn() {
		System.out.println("방번호를 입력해주세요.");
		int RoomsNumber = scanner.nextInt();
		int checkRoomsInfo = showRoomInfo(RoomsNumber);

		if (checkRoomsInfo == 0 || checkRoomsInfo == 1) {
			System.out.println("체크인 하시겠습니까?(Y/N)");
			String answer = scanner.next();
			scanner.nextLine();
			if (answer.equals("Y") || answer.equals("y")) {
				if (RoomsNumber / 100 == 2)
					rooms2[roomNumberChange(RoomsNumber)].setRoomsInfo(2);
				else if (RoomsNumber / 100 == 3)
					rooms3[roomNumberChange(RoomsNumber)].setRoomsInfo(2);
				else if (RoomsNumber / 100 == 4)
					rooms4[roomNumberChange(RoomsNumber)].setRoomsInfo(2);
				else if (RoomsNumber / 100 == 5)
					rooms5[roomNumberChange(RoomsNumber)].setRoomsInfo(2);
			} else {
				System.out.println("체크인 하지 않습니다.");
			}

		} else {
			System.out.println("관리자 메뉴로 돌아갑니다.");
		}

	}

	public void makeRoomCheckOut() {
		System.out.println("방번호를 입력해주세요.");
		int RoomsNumber = scanner.nextInt();
		int checkRoomsInfo = showRoomInfo(RoomsNumber);

		if (checkRoomsInfo == 0 || checkRoomsInfo == 1) {
			System.out.println("체크아웃 하시겠습니까?(Y/N)");
			String answer = scanner.next();
			scanner.nextLine();
			if (answer.equals("Y") || answer.equals("y")) {
				if (RoomsNumber / 100 == 2)
					rooms2[roomNumberChange(RoomsNumber)].setRoomsInfo(3);
				else if (RoomsNumber / 100 == 3)
					rooms3[roomNumberChange(RoomsNumber)].setRoomsInfo(3);
				else if (RoomsNumber / 100 == 4)
					rooms4[roomNumberChange(RoomsNumber)].setRoomsInfo(3);
				else if (RoomsNumber / 100 == 5)
					rooms5[roomNumberChange(RoomsNumber)].setRoomsInfo(3);
			} else {
				System.out.println("체크아웃 하지 않습니다.");
			}

		} else {
			System.out.println("관리자 메뉴로 돌아갑니다.");
		}
	}

	public int roomNumberChange(int num) {

		while (true) {

			if (num >= 200 && 220 >= num) {// 210
				return num - 201; // 10
			} else if (num >= 300 && 320 >= num) {// 320
				return num - 301;// 20
			} else if (num >= 400 && 420 >= num) {// 415
				return num - 401;//
			} else if (num >= 500 && 520 >= num) {
				return num - 501;
			} else {
				System.out.println("정상적인 번호를 입력해주세요");
				System.out.println("예시 201호실은 201을 입력");
				num = scanner.nextInt();
			}
		}

	}

	public void roomcheck() {
		System.out.println("확인할 객실 번호를 입력해 주세요.");
		int inputNumber = scanner.nextInt();
		if (rooms2[roomNumberChange(inputNumber)].getRoomsInfo() == 0) {
			System.out.println(inputNumber + "객실은 청소 완료");
			System.out.println();
		} else if (rooms2[roomNumberChange(inputNumber)].getRoomsInfo() == 2) {
			System.out.println(inputNumber + "객실은 투숙중인 객실입니다.");
			System.out.println();
		} else if (rooms2[roomNumberChange(inputNumber)].getRoomsInfo() == 3) {
			System.out.println(inputNumber + "객실은 청소 미완료 객실입니다.");
			System.out.println();

		}
	}

// 전체층수, 전체청소하기
	public void roomcheck2() {
		System.out.println("객실 청소하기를 선택하셨습니다.");
		System.out.println("청소할 객실 번호를 입력해 주세요.");
		int roomNumbers = scanner.nextInt();
		System.out.println(roomNumbers + "를 선택하셨습니다.");
		if (rooms2[roomNumberChange(roomNumbers)].getRoomsInfo() == 3) {
			System.out.println(roomNumbers + "청소가 완료되었습니다.");
			rooms2[roomNumberChange(roomNumbers)].roomsInfo = 0;
		} else if (rooms3[roomNumberChange(roomNumbers)].getRoomsInfo() == 3) {
			System.out.println(roomNumbers + "청소가 완료되었습니다.");
			rooms3[roomNumberChange(roomNumbers)].roomsInfo = 0;
		} else if (rooms4[roomNumberChange(roomNumbers)].getRoomsInfo() == 3) {
			System.out.println(roomNumbers + "청소가 완료되었습니다.");
			rooms4[roomNumberChange(roomNumbers)].roomsInfo = 0;
		} else if (rooms5[roomNumberChange(roomNumbers)].getRoomsInfo() == 3) {
			System.out.println(roomNumbers + "청소가 완료되었습니다.");
			rooms5[roomNumberChange(roomNumbers)].roomsInfo = 0;

		} else {
			System.out.println(roomNumbers + "이미 청소가 완료된 방입니다.");
		}
		
		roomRepair(roomNumbers);
		
	}
// 3 체크아웃 해당 객실 info 3 인경우 제외 청소 필요없습니다.
	public void roomRepair(int roomNumber) {
		System.out.println("점검이 필요한 객실 입니까?");
		System.out.println("1. 점검이 필요합니다.");
		System.out.println("2. 점검이 필요하지 않습니다.");
		int roomNumber2 = scanner.nextInt();
		
		if (roomNumber2 == 1) {
			System.out.printf("%d호실에 관리자를 호출합니다.\n",roomNumber);
			rooms2[roomNumberChange(roomNumber)].roomsInfo = 5;
//		} if (rooms2[roomNumberChange(roomNumbers)].getRoomsInfo() == 1) {
//			System.out.println(roomNumbers + "객실은 점검중인 객실로 전환합니다.");
//			System.out.println(roomNumbers + "객실 점검을 완료합니다.");

//			(rooms2[roomNumberChange(roomNumbers)].getRoomsInfo() == 1) {
			System.out.println();

			rooms2[roomNumberChange(roomNumber)].roomsInfo = 5;
		} 
			if (roomNumber2 == 2) {
				System.out.println("시스템을 종료합니다.");

			}
		}
	

////		for (int i = 0; i < 20; i++) {
//				if (roomNumbers / 100 == 2) {
//				
//				if (roomNumbers / 100 == 3) {
//					rooms3[roomNumberChange(roomNumbers)].roomsInfo = 0;
//				}
//				if (roomNumbers / 100 == 4) {
//					rooms4[roomNumberChange(roomNumbers)].roomsInfo = 0;
//				}
//				if (roomNumbers / 100 == 5) {
//					rooms5[roomNumberChange(roomNumbers)].roomsInfo = 0;
//				}
//			}
//	

	public void ShowAllRooms() {
		System.out.println("객실 현황 확인을 선택하셨습니다.");
		System.out.println("어떤 식으로 보시겠습니까?");
		System.out.println("1.층별로 보기");
		System.out.println("2.객실 선택 보기");
		String choose = scanner.next();
		scanner.nextLine();

		switch (choose) {
		case "1":
			System.out.println("몇층인지 입력해주세요.");
			int floorNum = scanner.nextInt();
			if (floorNum == 2) {
				for (int i = 0; i < 20; i++) {
					if (rooms2[i].getRoomsInfo() == 0) {
						System.out.printf("2%02d호실 청소 완료", i + 1);
						System.out.println();
					} else if (rooms2[i].getRoomsInfo() == 3) {
						System.out.printf("2%02d호실 청소 필요", i + 1);
						System.out.println();
					}
				}
			}
			if (floorNum == 3) {
				for (int i = 0; i < 20; i++) {
					if (rooms3[i].getRoomsInfo() == 0) {
						System.out.printf("3%02d호실 청소 완료", i + 1);
						System.out.println();
					} else if (rooms3[i].getRoomsInfo() == 3) {
						System.out.printf("3%02d호실 청소 필요", i + 1);
						System.out.println();

					}
				}
			}
			if (floorNum == 4) {
				for (int i = 0; i < 20; i++) {
					if (rooms4[i].getRoomsInfo() == 0) {
						System.out.printf("4%02d호실 청소 완료", i + 1);
						System.out.println();
					} else if (rooms4[i].getRoomsInfo() == 3) {
						System.out.printf("4%02d호실 청소 필요", i + 1);
						System.out.println();

					}
				}
			}
			if (floorNum == 5) {
				for (int i = 0; i < 20; i++) {
					if (rooms5[i].getRoomsInfo() == 0) {
						System.out.printf("5%02d호실 청소 완료", i + 1);
						System.out.println();
					} else if (rooms5[i].getRoomsInfo() == 3) {
						System.out.printf("5%02d호실 청소 필요", i + 1);
						System.out.println();
					}
				}
			}
			
			System.out.println("청소하시겠습니까?");
			System.out.println("1. 네");
			System.out.println("2. 아니요");
			int a = scanner.nextInt();
			if ( a == 1) {
			roomcheck2();
			}
			break;
		case "2":
			roomcheck();

			break;
		default:
			System.out.println("잘못된 입력입니다. 1번과 2번중에서 선택해 주세요.");
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
		System.out.println("3. 체크인/체크아웃");
		System.out.println("\n==============================");
		System.out.print("입력 : ");
		String choose = scanner.next();

		// 객실 전체 살펴보기 // 객실 선택 후 살펴보기

		switch (choose) {
		case "1":
			ShowAllRooms();
			roomcheck();
			break;
		case "2":
			roomcheck2();

			
			break;
		case "3":
			roomInfoChange();
			break;

		default:
			System.out.println("잘못된 입력입니다. 1번과 2번중에서 선택해 주세요.");
			break;

		}
	}
}

//	 - 청소하기 -> 체크아웃 방 확인 -> 해당 방 청소하기 -> 해당 방을 빈방으로 변경
// 청소를 하려고 할 때 roomsInform을 확인해서
// 1일경우 빈방이라서 청소할 필요x
// 2일경우 사용중으로 청소할 수 x
// 3일경우 체크아웃 상태라서 청소해야함
// 1,2,3 경우의 수를 나눠서 방 상태가 3일경우
// true로 청소 후 초기화

// 방상태 info값이 3인 경우만 보여준다 청소부에게
// 3인 경우만 출력해주고
// 청소 후 빈방을 입력하면 0으로 info값을 바꾼다

// 방을 청소하여 방 상태를 리턴
// 414, 415 체크아웃
// 414 입력
// 414 방 상태가 체크아웃이니까
// 청소하겠다 하면 이 방에 있는 정보를 초기화
// roominfo -> 0
// customer -> null

// 청소 직원에게 객실 선택시 안내

// 청소직원이 객실 청소를 선택했을 경우, 청소 후 인덱스값 0으로 초기화
// 방번호 입력받기

// 청소하기 들어가서 청소해야하는 방들은 체크아웃 방들입니다
// 어떤방을 청소하겠어요?
// 값을 받아와서 해당방의 인덱스값으로 변환해서 roominfo 초기화(0)

// 청소직원 선택 시 보여주는 화면
