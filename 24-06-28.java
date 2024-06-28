import java.util.Scanner;

public class Admin {

	private Customer[] customer = new Customer[100];
	private Rooms[] rooms2 = new Rooms[20];
	private Rooms[] rooms3 = new Rooms[20];
	private Rooms[] rooms4 = new Rooms[20];
	private Rooms[] rooms5 = new Rooms[20];
	private int receivedMoney = 0;

	private RoomsB[] roomsB1 = new RoomsB[10];
	private RoomsB[] roomsB2 = new RoomsB[9];
	private RoomsB[] roomsB3 = new RoomsB[8];

	Scanner scanner = new Scanner(System.in);
	int a = 65;

	public Admin() {

		for (int i = 0; i < 100; i++) {
			this.customer[i] = new Customer();
		}

		for (int i = 0; i < 20; i++) {
			if (i % 2 == 0) {
				this.rooms2[i] = new Rooms("single", 30000, 1); // Rooms 객체 생성
				this.rooms3[i] = new Rooms("single", 30000, 1); // Rooms 객체 생성
				this.rooms5[i] = new Rooms("single", 30000, 1); // Rooms 객체 생성
			} else {
				this.rooms2[i] = new Rooms("double", 40000, 1); // Rooms 객체 생성
				this.rooms3[i] = new Rooms("double", 40000, 1); // Rooms 객체 생성
				this.rooms5[i] = new Rooms("double", 40000, 1);
			}
			this.rooms4[i] = new Rooms("twin", 50000, 2); // Rooms 객체 생성

		}
		rooms4[3].setRoomsInfo(9);
		rooms4[13].setRoomsInfo(9);

		for (int i = 0; i < 5; i++) {
			this.roomsB1[i] = new RoomsB("single", 30000, 1, (char) (i + 65)); // 65 66 67 68 69
			this.roomsB2[i] = new RoomsB("single", 30000, 1, (char) (i + 75)); // 75 76 77 78 79
			this.roomsB3[i] = new RoomsB("single", 30000, 1, (char) (i + 84)); // 84 85 86 87 88
		}
		for (int i = 5; i < 10; i++) {
			this.roomsB1[i] = new RoomsB("double", 40000, 1, (char) (i + 65)); // 70 71 72 73 74
			if (i < 9)
				this.roomsB2[i] = new RoomsB("double", 40000, 1, (char) (i + 75)); // 80 81 82 83
			if (i < 7)
				this.roomsB3[i] = new RoomsB("double", 40000, 1, (char) (i + 84)); // 89 90

		}

		// VVIP는 roomNumber에 소문자 v를 넣어서 따로 표현
		this.roomsB3[7] = new RoomsB("double", 40000, 1, 'v');

	}

	// 데스크 직원 코드 입력시 보여주는 화면
	public boolean AdminProcess(int checkManager) {

		System.out.println("\n==============================");
		if (checkManager == 12) {
			System.out.println("지배인 모드");
		} else if (checkManager == 34) {
			System.out.println("데스크 직원 모드");
		}
		System.out.println("환영합니다.");
		System.out.println("1. 객실 예약하기");
		System.out.println("2. 이용자 예약 확인 및 객실 취소하기");
		System.out.println("3. 체크인/체크아웃 상태로 변경");
		if (checkManager == 12) {
			System.out.println("5. 현재 총 수익금액 확인");
		}
		System.out.println("9. 로그아웃");
		System.out.println("\n==============================");
		System.out.print("입력 : ");
		String choose = scanner.next();
		scanner.nextLine();
		switch (choose) {
		case "1":
			makeCustomerInfo(checkManager, true); // 고객 정보 만들기로 이동
			return true;
		case "2":
			customerInfoCheckChoose(checkManager); // 고객 정보 확인 창으로 이동
			return true; // 정보 확인 후 예약한 방을 취소할 수 있는 기능 포함.
		case "3":
			roomInfoChange(checkManager);
			return true; // 예약을 취소할 수 있는 기능을 포함
		case "4":

			return true; // 예약을 취소할 수 있는 기능을 포함
		case "5":
			if (checkManager == 12) {
				System.out.println("현재까지의 수익금액");
				System.out.println(receivedMoney + "원을 벌었습니다.");
			}
			return true;

		case "9":
			// staffScreen();
			return false;

		default:
			System.out.println("정상적인 번호를 입력해주세요.");
			return true;

		}
	}

	// 고객 정보 입력
	public void makeCustomerInfo(int checkManager, boolean direct) {

		if (direct == false) {
			System.out.println("예약없이 체크인을 바로 진행합니다.");
			System.out.println("이름을 입력해주세요");
			System.out.print("입력 : ");
			String name = scanner.next();
			System.out.println("전화번호를 입력해주세요");
			System.out.print("입력 : ");
			String phoneNumber = scanner.next();
			System.out.println();
			for (int i = 0; i < customer.length; i++) {
				if (customer[i].getUsersName().equals("")) {
					customer[i].setPhoneNumber(phoneNumber);
					customer[i].setUsersName(name);
					customer[i].settingPersnalNumber(phoneNumber);
					customer[i].settingRealPhoneNumber(phoneNumber);
					customer[i].settingRealUserName(name);
					divideMethod("1", i, direct, "A");
					return;
				}
			}

		} else {

			System.out.println("\n==============================");
			System.out.println("고객 정보를 입력합니다.");
			System.out.println("예약자 고객 이름을 입력하세요.");
			System.out.print("입력 : ");
			String usersName = scanner.next();
			scanner.nextLine();
			System.out.println("예약자 고객 전화번호를 입력하세요.");
			System.out.print("입력 : ");
			String phoneNumber = scanner.next();
			scanner.nextLine();
			System.out.println("방문하실 고객 이름을 입력하세요.");
			System.out.print("입력 : ");
			String realUserName = scanner.next();
			scanner.nextLine();
			System.out.println("방문하실 고객 전화번호를 입력하세요.");
			System.out.print("입력 : ");
			String realPhoneNumber = scanner.next();
			scanner.nextLine();
			System.out.println("방문할 고객 주민번호를 입력하세요.");
			System.out.print("입력 : ");
			String personalNumber = scanner.next();
			scanner.nextLine();
			String choosehotelType = chooseHotel();

			int customerIndex = -1;
			boolean member = false;
			for (int j = 0; j < customer.length; j++) {
				if (customer[j].getUsersName().equals(usersName)) {
					if (customer[j].getPhoneNumber().equals(phoneNumber)) {
						customerIndex = j;
						member = true;
						break;
					}
				}
			}

			int i = 0;
			for (i = 0; i < customer.length; i++) {

				// 만약 이전에 이름과 전화번호가 모두 동일한 사람이 예약한 이력이 확인되는 경우
				// 같은 사람으로 간주하며 손님인덱스 다음 배열에 새로 추가하는 것이 아닌
				// 기존의 입력한 사람의 인덱스 번호를 들고와 추가 예약을 진행

				if (member) {
					if (roomsCountReturn(customerIndex) != 0) {
						System.out.println("이미 예약한 내역이 존재합니다.");
						System.out.println("추가 예약하시겠습니까?(Y/N)");
						String reservationPlus = scanner.next();
						scanner.nextLine();
						if (reservationPlus.equals("Y") || reservationPlus.equals("y")) {

							if (choosehotelType.equals("A")) {
								this.customer[customerIndex].settingRealPhoneNumber(realPhoneNumber);
								this.customer[customerIndex].settingRealUserName(realUserName);
								this.customer[customerIndex].settingPersnalNumber(personalNumber);
								divideMethod("1", customerIndex, true, choosehotelType);
								return;
							} else {
								this.customer[customerIndex].settingRealPhoneNumberB(realPhoneNumber);
								this.customer[customerIndex].settingRealUserNameB(realUserName);
								this.customer[customerIndex].settingPersnalNumberB(personalNumber);
								divideMethod("1", customerIndex, true, choosehotelType);
								return;
							}

						}

					} else if (roomsCountReturn(customerIndex) == 0) {

						if (choosehotelType.equals("A")) {
							this.customer[customerIndex].settingRealPhoneNumber(realPhoneNumber);
							this.customer[customerIndex].settingRealUserName(realUserName);
							this.customer[customerIndex].settingPersnalNumber(personalNumber);
							divideMethod("1", customerIndex, true, choosehotelType);
							return;
						} else {
							this.customer[customerIndex].settingRealPhoneNumberB(realPhoneNumber);
							this.customer[customerIndex].settingRealUserNameB(realUserName);
							this.customer[customerIndex].settingPersnalNumberB(personalNumber);
							divideMethod("1", customerIndex, true, choosehotelType);
							return;
						}

					} else {
						System.out.println("이전으로 돌아갑니다.");
						return;
					}

					// 손님인덱스 customer[i]의 이름이 공백인 경우 아래를 진행하고 공백이 아니면 i값을 늘림.
				} else if (customer[i].getUsersName().equals("")) {
					if (choosehotelType.equals("A")) {
						customer[i].settingRealPhoneNumber(realPhoneNumber);
						customer[i].settingRealUserName(realUserName);
						customer[i].settingPersnalNumber(personalNumber);
						customer[i].setPhoneNumber(phoneNumber);
						customer[i].setUsersName(usersName);
						divideMethod("1", i, true, choosehotelType);
						break;
					} else {
						customer[i].settingRealPhoneNumberB(realPhoneNumber);
						customer[i].settingRealUserNameB(realUserName);
						customer[i].settingPersnalNumberB(personalNumber);
						customer[i].setPhoneNumber(phoneNumber);
						customer[i].setUsersName(usersName);
						divideMethod("1", i, true, choosehotelType);
						break;
					}

				}
			}

		}
	}

	public String chooseHotel() {// 재민수정
		System.out.println("호텔 A동과 B동이 있습니다.");
		System.out.println("어떤 호텔로 예약하시겠습니까?(A/B)");
		System.out.print("입력 : ");
		String choose = scanner.next();
		scanner.nextLine();
		if (choose.equals("A") || choose.equals("a")) {
			return "A";
		} else if (choose.equals("B") || choose.equals("b")) {
			return "B";
		}
		System.out.println("올바른 입력이 아닙니다.");
		System.out.println("자동으로 A동으로 선택됩니다.");
		return "A";

	}

	// 고객 검색 방법 선택
	public void customerInfoCheckChoose(int checkManager) {
		System.out.println("\n==============================");
		System.out.println("고객 정보를 검색합니다.");
		System.out.println("어떤 방법으로 검색하시겠습니까?");
		System.out.println("1. 예약자 고객 이름으로 찾기");
		System.out.println("2. 예약자 고객 전화번호로 찾기");
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
		System.out.println("예약자 고객의 전화번호를 입력하세요.");
		System.out.println("==============================");
		System.out.print("입력 : ");
		String phoneNumber = scanner.next();
		scanner.nextLine();

		for (int i = 0; i < customer.length; i++) {
			if (customer[i].getPhoneNumber().equals(phoneNumber)) {
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
		System.out.println("예약자 고객명을 입력하세요.");
		System.out.println("==============================");
		System.out.print("입력 : ");
		String name = scanner.next();
		scanner.nextLine();

		for (int i = 0; i < customer.length; i++) {

			if (customer[i].getUsersName().equals(name)) {
				showCustomerInfo(i, checkManager);
				return;
			}
		}

		System.out.println("해당하는 고객이 없습니다.");

	}

	// 해당 고객 정보 보여주기
	public void showCustomerInfo(int customerIndex, int checkManager) {

		// 예약자 기준으로 모든 방 보여주기

		System.out.println("\n******************************");
		System.out.println("예약 고객명 : " + customer[customerIndex].getUsersName());
		System.out.println("예약 고객 전화번호 : " + customer[customerIndex].getPhoneNumber());
		System.out.println();
		int count = 0;
		for (int j = 0; j < 3; j++) {
			if (customer[customerIndex].getRealUsersName()[j] != "" && customer[customerIndex].getRooms()[j] != 0) {
				System.out.println("...................................");
				System.out.println("이용 고객명 : " + customer[customerIndex].getRealUsersName()[j]);
				System.out.println("이용 고객 전화번호 : " + customer[customerIndex].getRealphoneNumber()[j]);
				if (getArrayRoom(
						customer[customerIndex].getRooms()[j] / 100)[customer[customerIndex].getRooms()[j] % 100 - 1]
								.getRoomsInfo() == 1) {
					System.out.println("예약한 방 : " + customer[customerIndex].getRooms()[j]);
					System.out.println(customer[customerIndex].getMoneyInfo()[j] + "원 결제했음");
				} else if (getArrayRoom(
						customer[customerIndex].getRooms()[j] / 100)[customer[customerIndex].getRooms()[j] % 100 - 1]
								.getRoomsInfo() == 2) {
					System.out.println("체크인 중인 방 : " + customer[customerIndex].getRooms()[j]);
					System.out.println(customer[customerIndex].getMoneyInfo()[j] + "원 결제했음");
				}

				count++;
				if (checkManager == 12) {
					System.out.println("이용 고객 주민번호 : " + customer[customerIndex].getPersonalNumber()[j]);
				}
			}
			if (customer[customerIndex].getRealUsersNameB()[j] != "" && customer[customerIndex].getRoomsB()[j] != "") {
				System.out.println("...................................");
				System.out.println("이용 고객명 : " + customer[customerIndex].getRealUsersNameB()[j]);
				System.out.println("이용 고객 전화번호 : " + customer[customerIndex].getRealphoneNumberB()[j]);
				System.out.println("예약한 방(B동) : " + customer[customerIndex].getRoomsB()[j]);
				// System.out.println(customer[customerIndex].getMoneyInfo()[j] + "원 결제했음");
				System.out.println("==============================\n");
				if (checkManager == 12) {
					System.out.println("이용 고객 주민번호 : " + customer[customerIndex].getPersonalNumberB()[j]);

				}
				count++;
			}

		}

		// 취소할 수 있는 기능을 포함
		if (count > 0) {
			System.out.println("******************************\n");
			System.out.println("예약을 취소하시겠습니까?(Y/N)");
			System.out.print("입력 : ");
			String yesOrNo = scanner.next();
			scanner.nextLine();
			if (yesOrNo.equals("Y") || yesOrNo.equals("y")) {
				System.out.println("A동과 B동 중 어떤 곳을 취소하시겠습니까?");
				String aOrB = scanner.next();
				scanner.nextLine();
				while(true) {
					if(aOrB.equals("A") || aOrB.equals("B") || aOrB.equals("a") || aOrB.equals("b")) {
						break;
					}
					System.out.println("A와 B 중에만 선택하세요.");
					System.out.println("A동과 B동 중 어떤 곳을 취소하시겠습니까?");
					aOrB = scanner.next();
					scanner.nextLine();
				}
				
				divideMethod("2", customerIndex, true,aOrB.toUpperCase() );
			} else if (yesOrNo.equals("N") || yesOrNo.equals("n")) {
				System.out.println("이전 메뉴로 돌아갑니다.");
			} else {
				System.out.println("올바른 입력을 하세요.");
			}
		} else {
			System.out.println("예약한 방이 없습니다.");
			System.out.println();
			System.out.println("******************************");
		}

	}

//	// 고객 정보 모두 보기
//	public void showAllCustomerInfo(int checkManager) {
//
//		System.out.println("예약한 고객 정보를 모두 출력합니다.");
//		System.out.println("어떤 식으로 보시겠습니까?");
//		System.out.println("1. 층 별로 보기");
//		System.out.println("2. 예약 순으로 보기");
//		String choose = scanner.next();
//		scanner.nextLine();
//
//		switch (choose) {
//		case "1":
//			showFloorCustomer(checkManager);
//			break;
//		}
//
//	}
//
//	// 층 별로 고객 내용 출력 (미완성)
//	public void showFloorCustomer(int checkManager) {
//
//		System.out.println("2층 예약 중이거나 이용중인 고객");
//		for (int i = 0; i < rooms2.length; i++) {
//
//			if (rooms2[i].getRoomsInfo() == 1 || rooms2[i].getRoomsInfo() == 2) {
//				int roomNumber = 201 + i;
//
//				for (int j = 0; j < customer.length; j++) {
//					for (int k = 0; k < 3; k++) {
//						if (customer[j].getRooms()[k] == roomNumber) {
//							System.out.println("\n==============================");
//							System.out.println("예약한 방 번호 : " + roomNumber);
//							System.out.println("예약자 이름 : " + customer[j].getUsersName());
//							System.out.println("예약자 전화번호 : " + customer[j].getPhoneNumber());
//							System.out.println("이용자 이름 : " + customer[j].getRealUsersName());
//							System.out.println("이용자 전화번호 : " + customer[j].getRealphoneNumber());
//							System.out.println("==============================\n");
//
//						}
//					}
//				}
//			}
//		}
//	}

//	public int cleanRoom() {
//
//		System.out.println("방을 청소하시겠습니까?(Y/N)");
//		String clean = scanner.next();
//		scanner.nextLine();
//		if (clean.equals("Y") || clean.equals("y")) {
//			System.out.println("청소가 완료되었습니다.");
//			return 0;
//		} else {
//			System.out.println("최대한 빠르게 청소를 해주세요.");
//			return 3;
//		}
//
//	}

	// makeCustomerInfo(고객정보작성) 메소드 또는, customerInfoCheckChoose(고객정보확인) 메소드를 통하여 넘어옴
	// 객실 현황 확인을 겸하며 mainSelectNum의 값에 따라 예약, 취소, 현황 등등을 확인할 수 있음.
	// 금액 부분 추가

	// 객실 현황 확인을 겸하며 mainSelectNum의 값에 따라 예약, 취소, 현황 등등을 확인할 수 있음.

	public void divideMethod(String divideNum, int customerIndex, boolean direct, String hotelType) {

		if (divideNum.equals("1")) {

			// 이미 예약한 방의 개수가 3개를 넘어가는 경우 아래 메소드 실행 및 예약 불가능하게 return으로 빠져나옴
			int checkRoomEmpty = 0;
			int checkRoomBEmpty = 0;
			for (int a = 0; a < 3; a++) {
				if (customer[customerIndex].getRooms()[a] != 0)
					checkRoomEmpty++;
				if (customer[customerIndex].getRoomsB()[a] != "")
					checkRoomBEmpty++;
			}
			if (checkRoomEmpty == 3 && hotelType == "A") {
				System.out.println("A동 예약은 더이상 불가능합니다.");
				return;
			}
			if (checkRoomBEmpty == 3 && hotelType == "B") {
				System.out.println("B동 예약은 더이상 불가능합니다.");
				return;
			}

			if (customer[customerIndex].isMember() == false && direct == true) {
				System.out.println("예약자분은 현재 회원이 아닙니다.");
				System.out.println("회원으로 변경하시겠습니까? Y/N");
				System.out.println("(변경시 고객님의 정보를 저장 및 이용금액에 따라 등급과 할인율이 추가 혜택으로 제공됩니다.)");
				System.out.print("입력 : ");

				String input = scanner.nextLine();
				if (input.equals("y") || input.equals("Y")) {
					customer[customerIndex].setMember(true);
					System.out.println("회원으로 변경되었습니다.");
					System.out.println();
				}
			}

			System.out.println();
			// saveType : 입력한 방의 타입을 저장하는 용도
			// saveChoose : 입력한 방 타입에 해당하는 메뉴번호 중 고른 번호를 저장하는 용도
			int saveType = 0;
			int saveChoose = 0;
			if (hotelType.equals("A")) {

				while (true) {
					System.out.println("고객 돈 : " + customer[customerIndex].getMoney());
					System.out.println("방의 타입을 선택해주세요.");
					System.out.printf("1. Single 룸 (%d원, 침대개수 1개)\n",
							30000 - discountMoney(customer[customerIndex].getRank()));
					System.out.printf("2. Double 룸 (%d원, 침대개수 1개)\n",
							40000 - discountMoney(customer[customerIndex].getRank()));
					System.out.printf("3. TwinBed 룸 (%d원, 침대개수 2개)\n",
							50000 - discountMoney(customer[customerIndex].getRank()));
					System.out.print("입력 : ");
					int type = scanner.nextInt();

					// 예약 할 때 아래 메소드 출력

					if (type == 1 || type == 2) {
						saveType = type;
						while (true) {
							System.out.println();

							System.out.println("원하는 층수를 선택해주세요");
							System.out.println("객실 현황");
							System.out.println("1. 2층 빈 객실 : " + emptyRoomCheck(2, type) + "객실");
							System.out.println("2. 3층 빈 객실 : " + emptyRoomCheck(3, type) + "객실");
							System.out.println("3. 5층 빈 객실 : " + emptyRoomCheck(5, type) + "객실");
							System.out.print("입력 : ");
							int choose = scanner.nextInt();
							if (choose < 1 || choose > 3) {
								System.out.println("정상적인 번호를 입력해주세요");
							} else {
								saveChoose = choose;
								break;
							}
						}

						break;

					} else if (type == 3) {
						saveType = type;
						while (true) {
							System.out.println("1. 4층 빈 객실 : " + emptyRoomCheck(4, 3) + "객실");
							int choose = scanner.nextInt();
							if (choose != 1) {
								System.out.println("정상적인 번호를 입력해주세요");
							} else {
								saveChoose = choose;
								break;
							}
						}

						break;

					} else {
						System.out.println("정상적인 번호를 입력해주세요");
					}
				}

				// 트윈 룸이 아닌 경우에서 메뉴 3번을 선택하면
				// 층수의 파라미터 값을 5층으로 넣어주어야 하기 때문에 saveChoose를 4으로 변경
				if (saveChoose == 3) {
					saveChoose++;
				}

				// 트윈 룸을 선택한 경우 무조건 층수의 파라미터 값을 4층으로 넣어야 하기 때문에 saveChoose를 3으로 변경
				if (saveType == 3) {
					saveChoose = 3;
				}

				//
				int oneDigitNumber = chooseRoom(saveChoose + 1, saveType);

				int chooseRoom = scanner.nextInt();

				// 트윈룸의 경우
				while (saveType == 3) {
					if (chooseRoom == 404 || chooseRoom == 414) {
						System.out.println("폐쇄된 방 입니다.");
						chooseRoom = scanner.nextInt();
					} else if (chooseRoom < 401 || chooseRoom > 420) {
						System.out.println("없는 방 번호입니다.");
						System.out.print("입력 : ");
						chooseRoom = scanner.nextInt();
					} else {
						break;
					}
				}
				// 트윈룸이 아닌 경우
				// chooseRoom메소드를 통하여 싱글, 더블 중 선택한 내용에 따라 1, 0 값을 oneDigitNumber로 받아오고
				// 싱글을 선택했는데 짝수번호의 방을 고르거나, 또는 방의 인덱스값을 넘어가는 방 번호를 입력한 경우 아래를 실행 하고
				// 다시 값을 제대로 받아옴. 더블의 경우에도 동일.
				// (싱글은 홀수의 방만 존재하고 입력한 값을 2로 나눈 나머지가 반드시 1이 나와야 함)
				// (더블은 짝수의 방만 존재하고 입력한 값을 2로 나눈 나머지가 반드시 0이 나와야 함)
				while (saveType != 3 && chooseRoom % 2 != oneDigitNumber || chooseRoom % 100 > 20
						|| (saveChoose + 1) * 100 >= chooseRoom || (saveChoose + 2) * 100 <= chooseRoom) {
					System.out.println("없는 방 번호입니다.");
					chooseRoom = scanner.nextInt();
				}

				reservation(saveChoose + 1, roomNumberChange(chooseRoom), chooseRoom, customerIndex, direct);
			} else {
				int type = 0;
				int choose = 0;
				while (true) {
					System.out.println("호텔 B동을 선택하였습니다.");
					System.out.println("방의 타입을 선택해주세요.");
					System.out.println("1. Single 룸 (30,000원, 침대개수 1개)");
					System.out.println("2. Double 룸 (40,000원, 침대개수 1개)");
					System.out.print("입력 : ");
					type = scanner.nextInt();

					// 예약 할 때 아래 메소드 출력

					if (type == 1 || type == 2) {
						saveType = type;
						while (true) {
							System.out.println();
							System.out.println("원하는 층수를 선택해주세요");
							System.out.println("객실 현황");
							System.out.println("1. 1층 빈 객실 : " + emptyRoomBCheck(1, type) + "객실");
							System.out.println("2. 2층 빈 객실 : " + emptyRoomBCheck(2, type) + "객실");
							System.out.println("3. 3층 빈 객실 : " + emptyRoomBCheck(3, type) + "객실");
							System.out.print("입력 : ");
							choose = scanner.nextInt();
							if (choose < 1 || choose > 3) {
								System.out.println("정상적인 번호를 입력해주세요");
							} else {
								saveChoose = choose;
								break;
							}
						}

						// 1층이다 1승

						break;

					} else {
						System.out.println("정상적인 번호를 입력해주세요");
					}
				}
				String chooseRoom = "";
				int checkChooseRoomInteger = 0;

				while (true) {// 1층 65 ~ 69,2층 75~79, 3층 84~88
					if (type == 1 || type == 2) {// 1층 70~74, 2층 80~83, 3층 89~90 3층의 마지막은 VVIP
						if (choose == 1 && type == 1) {
							while (true) {
								System.out.println("다음 방 번호 중 1개를 입력하세요");
								if (roomsB1[0].getRoomsInfo() == 0) {
									System.out.print("A/");
								}
								if (roomsB1[1].getRoomsInfo() == 0) {
									System.out.print("B/");
								}
								if (roomsB1[2].getRoomsInfo() == 0) {
									System.out.print("C/");
								}
								if (roomsB1[3].getRoomsInfo() == 0) {
									System.out.print("D/");
								}
								if (roomsB1[4].getRoomsInfo() == 0) {
									System.out.print("E/");
								}
								System.out.println("※ 대문자로만 입력해주세요.");
								System.out.print("입력 : ");
								if (checkChooseRoomInteger >= 65 && checkChooseRoomInteger <= 69) {
									break;
								}
								chooseRoom = scanner.next();
								scanner.nextLine();
								char checkChooseRoom = chooseRoom.charAt(0);
								checkChooseRoomInteger = checkChooseRoom;
							}
						} else if (choose == 1 && type == 2) {
							while (true) {
								System.out.println("방 번호를 입력하세요(F~J)");
								System.out.println("※ 대문자로만 입력해주세요.");
								System.out.print("입력 : ");
								if (checkChooseRoomInteger >= 70 && checkChooseRoomInteger <= 74) {
									break;
								}
								chooseRoom = scanner.next();
								scanner.nextLine();
								char checkChooseRoom = chooseRoom.charAt(0);
								checkChooseRoomInteger = checkChooseRoom;
							}
						} else if (choose == 2 && type == 1) {
							while (true) {
								System.out.println("방 번호를 입력하세요(K~O)");
								System.out.println("※ 대문자로만 입력해주세요.");
								System.out.print("입력 : ");
								if (checkChooseRoomInteger >= 75 && checkChooseRoomInteger <= 79) {
									break;
								}
								chooseRoom = scanner.next();
								scanner.nextLine();
								char checkChooseRoom = chooseRoom.charAt(0);
								checkChooseRoomInteger = checkChooseRoom;
							}
						} else if (choose == 2 && type == 2) {
							while (true) {
								System.out.println("방 번호를 입력하세요(P~S)");
								System.out.println("※ 대문자로만 입력해주세요.");
								System.out.print("입력 : ");
								if (checkChooseRoomInteger >= 80 && checkChooseRoomInteger <= 83) {
									break;
								}
								chooseRoom = scanner.next();
								scanner.nextLine();
								char checkChooseRoom = chooseRoom.charAt(0);
								checkChooseRoomInteger = checkChooseRoom;
							}
						} else if (choose == 3 && type == 1) {
							while (true) {
								System.out.println("방 번호를 입력하세요(T~X)");
								System.out.println("※ 대문자로만 입력해주세요.");
								System.out.print("입력 : ");
								if (checkChooseRoomInteger >= 84 && checkChooseRoomInteger <= 88) {
									break;
								}
								chooseRoom = scanner.next();
								scanner.nextLine();
								char checkChooseRoom = chooseRoom.charAt(0);
								checkChooseRoomInteger = checkChooseRoom;
							}
						} else if (choose == 3 && type == 2) {
							while (true) {
								System.out.println("방 번호를 입력하세요(Y~Z)");
								System.out.println("※ 대문자로만 입력해주세요.");
								System.out.print("입력 : ");
								if (checkChooseRoomInteger >= 89 && checkChooseRoomInteger <= 90) {
									break;
								}
								chooseRoom = scanner.next();
								scanner.nextLine();
								char checkChooseRoom = chooseRoom.charAt(0);
								checkChooseRoomInteger = checkChooseRoom;
							}
						}
						if (chooseRoom.equals("VVIP")) {

							break;
						} else {
							char checkChooseRoom = chooseRoom.charAt(0);
							checkChooseRoomInteger = checkChooseRoom;
							if (checkChooseRoomInteger < 65 || checkChooseRoomInteger > 90) {
								System.out.println("A~Z로만 입력하세요.");
							} else {
								break;
							}
						}
					}
				}
				reservationB(checkChooseRoomInteger, chooseRoom, customerIndex);

			}
		} else if (divideNum.equals("2")) {

			if (hotelType.equals("A")) {// 취소할 때 아래 메소드 출력
				System.out.println("예약자 : " + customer[customerIndex].getUsersName());
				// System.out.println("실 이용자 : " + customer[customerIndex].getRealUsersName());

				int reservationRoomCount = 0;
				for (int j = 0; j < customer[customerIndex].getRooms().length; j++) {
					if (customer[customerIndex].getRooms()[j] != 0 && getArrayRoom(
							customer[customerIndex].getRooms()[j] / 100)[customer[customerIndex].getRooms()[j] % 100 - 1]
									.getRoomsInfo() == 1) {
						System.out.println("현재 예약중인 방 : " + customer[customerIndex].getRooms()[j] + "호실");
						reservationRoomCount++;
					}
					if (customer[customerIndex].getRooms()[j] != 0 && getArrayRoom(
							customer[customerIndex].getRooms()[j] / 100)[customer[customerIndex].getRooms()[j] % 100 - 1]
									.getRoomsInfo() == 2) {
						System.out.println("현재 체크인 중인 방 : " + customer[customerIndex].getRooms()[j] + "호실");
						reservationRoomCount++;
					}

				}

				if (reservationRoomCount == 0) {
					System.out.println("예약하신 방이 없습니다.");
				} else {
					System.out.println("취소하실 방 번호를 입력해주세요");
					System.out.print("입력 : ");
					int saveRoomIndexNum = -1;
					int cancel = scanner.nextInt();
					int info = getArrayRoom(cancel / 100)[(cancel % 100) - 1].getRoomsInfo();

					boolean go = true;
					while (go) {
						// 스캐너 입력 범위 제한 조건 작성

						for (int i = 0; i < 3; i++) {
							if (cancel == customer[customerIndex].getRooms()[i]) {
								saveRoomIndexNum = i;
								break;
							}
						}

						if (saveRoomIndexNum != -1 && info == 1) {

							int checkZero = 0;
							for (int j = 0; j < 3; j++) {
								if (customer[customerIndex].getRooms()[j] == cancel) {
									System.out.println(cancel + "호 예약이 취소되었습니다.");
									customerPayBackRoomPrice(cancel, customerIndex, j);
									customer[customerIndex].cancelRoom(j);
									customer[customerIndex].cancelPersnalNumber(j);
									customer[customerIndex].cancelRealPhoneNumber(j);
									customer[customerIndex].cancelRealUserName(j);
									getArrayRoom(cancel / 100)[roomNumberChange(cancel)].setCustomerName("");
									getArrayRoom(cancel / 100)[roomNumberChange(cancel)].setRoomsInfo(0);
									getArrayRoom(cancel / 100)[roomNumberChange(cancel)].setRealCustomerName("");

								}
								if (customer[customerIndex].getRooms()[j] == 0) {
									checkZero++;
								}
							}

							if (checkZero == 3 && customer[customerIndex].isMember() == false) {
								customer[customerIndex].setUsersName("");
								customer[customerIndex].setPhoneNumber("");
							}

							go = false;
						} else if (saveRoomIndexNum != -1 && info == 2) {
							System.out.println("체크인 중인 방을 예약 취소할 수 없습니다.");
							cancel = scanner.nextInt();
							info = getArrayRoom(cancel / 100)[(cancel % 100) - 1].getRoomsInfo();

						} else {
							System.out.println("해당하는 방의 예약 기록이 없습니다.");
							cancel = scanner.nextInt();
						}
					}

				}
				// 여기서 부터 3번째 메뉴를 골랐을 때 내용을 작성할 예정
			} else if (hotelType.equals("B"))  {
				System.out.println("예약자 : " + customer[customerIndex].getUsersName());
				// System.out.println("실 이용자 : " + customer[customerIndex].getRealUsersName());

				int reservationRoomBCount = 0;

				for (int j = 0; j < customer[customerIndex].getRoomsB().length; j++) {
					if (customer[customerIndex].getRoomsB()[j] != "") {
						String roomNum = customer[customerIndex].getRoomsB()[j];
						char roomNumChar = roomNum.charAt(0);
						int roomNumInteger = roomNumChar;

						if (getArrayRoomB(roomNumInteger)[findIndexRoomB(roomNumInteger)].getRoomsInfo() == 1) {
							System.out.println("현재 예약중인 방 : " + customer[customerIndex].getRoomsB()[j] + "호실");
							reservationRoomBCount++;
						} else if (getArrayRoomB(roomNumInteger)[findIndexRoomB(roomNumInteger)].getRoomsInfo() == 2) {
							System.out.println("현재 체크인 중인 방 : " + customer[customerIndex].getRoomsB()[j] + "호실");
							reservationRoomBCount++;
						}
					}

				}

				if (reservationRoomBCount == 0) {
					System.out.println("예약하신 방이 없습니다.");
				} else {
					System.out.println("취소하실 방 번호를 입력해주세요");
					System.out.print("입력 : ");
					int saveRoomIndexNum = -1;
					String cancel = scanner.next();
					scanner.nextLine();
					cancel = cancel.toUpperCase();
					char cancelChar = cancel.charAt(0);
					int cancelInteger = cancelChar;
					

					boolean go = true;
					while (go) {
						// 스캐너 입력 범위 제한 조건 작성
						int info = getArrayRoomB(cancelInteger)[findIndexRoomB(cancelInteger)].getRoomsInfo();
						for (int i = 0; i < 3; i++) {
							if (customer[customerIndex].getRoomsB()[i].equals(cancel)) {
								saveRoomIndexNum = i;
								break;
							}
						}

						if (saveRoomIndexNum != -1 && info == 1) {

							int checkZero = 0;
							for (int j = 0; j < 3; j++) {
								if (customer[customerIndex].getRoomsB()[j].equals(cancel)) {
									System.out.println(cancel + "호 예약이 취소되었습니다.");
									// customerPayBackRoomPriceB(cancel);
									customer[customerIndex].cancelRoomB(j);
									customer[customerIndex].cancelPersonalNumberB(j);
									customer[customerIndex].cancelRealPhoneNumberB(j);
									customer[customerIndex].cancelRealUserNameB(j);
									getArrayRoomB(cancelInteger)[findIndexRoomB(cancelInteger)].setCustomerName("");
									getArrayRoomB(cancelInteger)[findIndexRoomB(cancelInteger)].setRoomsInfo(0);
									getArrayRoomB(cancelInteger)[findIndexRoomB(cancelInteger)].setRealCustomerName("");

								}
								if (customer[customerIndex].getRooms()[j] == 0) {
									checkZero++;
								}
								if (customer[customerIndex].getRoomsB()[j] == "") {
									checkZero++;
								}
							}
							// 재민수정
							if (checkZero == 6 && customer[customerIndex].isMember() == false) {
								customer[customerIndex].setUsersName("");
								customer[customerIndex].setPhoneNumber("");
							}

							go = false;
						} else if (saveRoomIndexNum != -1 && info == 2) {
							System.out.println("체크인 중인 방을 예약 취소할 수 없습니다.");
							cancel = scanner.next();
							scanner.nextLine();
							cancelChar = cancel.charAt(0);
							cancelInteger = cancelChar;
							info = getArrayRoomB(cancelInteger)[findIndexRoomB(cancelInteger)].getRoomsInfo();

						} else {
							System.out.println("해당하는 방의 예약 기록이 없습니다.");
							System.out.print("입력 : ");
							cancel = scanner.next();
							scanner.nextLine();
							cancel = cancel.toUpperCase();
							cancelChar = cancel.charAt(0);
							cancelInteger = cancelChar;
						}
					}

				}

			}else {
				System.out.println("올바른 입력을 하세요.");
			}
		}
	}

	// 원하는 타입의 방을 고르고 그 타입에 해당하는 방들을 출력하며
	// 고른 타입에 따라 0 또는 1을 반환
	// 반환값은 출력해주는 방들 중 해당하지 않는 방을 입력할 때, 제대로 입력했는지 판단하기 위하여 사용
	public int chooseRoom(int floorNum, int saveInput) {

		System.out.println();

		while (true) {
			System.out.println("원하는 객실을 숫자로 입력 해주세요");
			if (saveInput == 1) {
				for (int i = 0; i < 20; i++) {
					if (getArrayRoom(floorNum)[i].getRoomsInfo() == 0 && i % 2 == 0) {
						System.out.printf("%d%02d호실 비었음\n", floorNum, i + 1);
					} else if (getArrayRoom(floorNum)[i].getRoomsInfo() == 1 && i % 2 == 0) {
						System.out.printf("%d%02d호실 예약완료\n", floorNum, i + 1);
					} else if (getArrayRoom(floorNum)[i].getRoomsInfo() == 2 && i % 2 == 0) {
						System.out.printf("%d%02d호실 체크인상태\n", floorNum, i + 1);
					} else if (getArrayRoom(floorNum)[i].getRoomsInfo() == 3 && i % 2 == 0) {
						System.out.printf("%d%02d호실 체크아웃상태\n", floorNum, i + 1);

					}
				}
				System.out.print("입력 : ");
				return 1;
			} else if (saveInput == 2) {

				for (int i = 0; i < 20; i++) {
					if (getArrayRoom(floorNum)[i].getRoomsInfo() == 0 && i % 2 != 0) {
						System.out.printf("%d%02d호실 비었음\n", floorNum, i + 1);
					} else if (getArrayRoom(floorNum)[i].getRoomsInfo() == 1 && i % 2 != 0) {
						System.out.printf("%d%02d호실 예약완료\n", floorNum, i + 1);
					} else if (getArrayRoom(floorNum)[i].getRoomsInfo() == 2 && i % 2 != 0) {
						System.out.printf("%d%02d호실 체크인상태\n", floorNum, i + 1);
					} else if (getArrayRoom(floorNum)[i].getRoomsInfo() == 3 && i % 2 != 0) {
						System.out.printf("%d%02d호실 체크아웃상태\n", floorNum, i + 1);

					}
				}
				System.out.print("입력 : ");
				return 0;
			} else if (saveInput == 3) {
				for (int i = 0; i < 20; i++) {
					if (getArrayRoom(floorNum)[i].getRoomsInfo() == 0) {
						System.out.printf("4%02d호실 비었음\n", i + 1);
					} else if (getArrayRoom(floorNum)[i].getRoomsInfo() == 1) {
						System.out.printf("4%02d호실 예약완료\n", i + 1);
					} else if (getArrayRoom(floorNum)[i].getRoomsInfo() == 2) {
						System.out.printf("4%02d호실 체크인상태\n", i + 1);
					} else if (getArrayRoom(floorNum)[i].getRoomsInfo() == 9) {
						System.out.printf("4%02d호실 폐쇄\n", i + 1);
					} else if (getArrayRoom(floorNum)[i].getRoomsInfo() == 3) {
						System.out.printf("4%02d호실 체크아웃상태\n", i + 1);
					}
				}
				System.out.print("입력 : ");
				return 0;
			} else {
				System.out.println("정상적인 번호를 입력해주세요");
			}

		}

	}

	// 고객과 방의 정보들을 예약하는 메소드
	// 파라미터값은 순서대로 층수, 방번호를 인덱스 값으로 바꾼 수, 사용자가 실제로 입력한 방번호, 손님의 인덱스
	// 방들의 배열이 rooms2 ~ rooms5까지 층별로 나누었기 때문에 floorNum값(층수)에 따라 if문을 구성
	public void reservation(int floorNum, int indexVerRoomNum, int basicRoomNum, int customerIndex, boolean direct) {

		if (floorNum == 4 && customer[customerIndex].getMoney() < 50000) {
			System.out.println("잔액이 부족합니다.");
			return;
		} else if (basicRoomNum % 2 == 1 && customer[customerIndex].getMoney() < 30000) {
			System.out.println("잔액이 부족합니다.");
			return;
		} else if (basicRoomNum % 2 == 0 && customer[customerIndex].getMoney() < 40000) {
			System.out.println("잔액이 부족합니다.");
			return;
		}

		if (getArrayRoom(floorNum)[indexVerRoomNum].getRoomsInfo() == 0) {
			getArrayRoom(floorNum)[indexVerRoomNum].setRoomsInfo(1);

			if (direct) {
				System.out.printf("%d층 %d호실 예약이 완료되었습니다.\n", floorNum, basicRoomNum);
				System.out.printf("예약한 고객 명 : %s ", customer[customerIndex].getUsersName());
			} else {
				System.out.printf("%d층 %d호실 체크인이 완료되었습니다.\n", floorNum, basicRoomNum);
				System.out.printf("고객 명 : %s ", customer[customerIndex].getUsersName());
			}

			for (int i = 0; i < 3; i++) {
				if (customer[customerIndex].getRooms()[i] == 0) {
					customer[customerIndex].settingRooms(basicRoomNum);
					getArrayRoom(floorNum)[indexVerRoomNum].setCustomerName(customer[customerIndex].getUsersName());
					getArrayRoom(floorNum)[indexVerRoomNum]
							.setRealCustomerName(customer[customerIndex].getRealUsersName()[i]);
					customerPayRoomPrice(basicRoomNum, customerIndex, direct);// 이름으로 방번호를 찾은후 방번호에 해당하는 bedtype에 따른 고객의
					getArrayRoom(floorNum)[indexVerRoomNum]
							.setRealCustomerPhone(customer[customerIndex].getRealphoneNumber()[i]); // 비용 지불
					break;
				}

			}
		} else {
			System.out.println("예약할 수 없습니다.");
		}

	}

	// 재민수정
	public void reservationB(int roomNumInteger, String roomNum, int customerIndex) {
		// 재민수정
		String bedType = "";

		if (getArrayRoomB(roomNumInteger)[findIndexRoomB(roomNumInteger)].getRoomsInfo() == 0) {
			getArrayRoomB(roomNumInteger)[findIndexRoomB(roomNumInteger)].setRoomsInfo(1);
			System.out.printf("%d층 %s호실 예약이 완료되었습니다.\n", roomBFloorFind(roomNumInteger), roomNum);
			// System.out.println(roomsB2[0].getRoomsInfo());
			bedType = getArrayRoomB(roomNumInteger)[findIndexRoomB(roomNumInteger)].getBedType();
			System.out.printf("예약한 고객명 : %s \n", customer[customerIndex].getUsersName());
			System.out.printf("침대 타입 : %s \n",
					getArrayRoomB(roomNumInteger)[findIndexRoomB(roomNumInteger)].getBedType());

			for (int i = 0; i < 3; i++) {
				if (customer[customerIndex].getRoomsB()[i] == "") {
					customer[customerIndex].settingRoomsB(roomNum);
					getArrayRoomB(roomNumInteger)[findIndexRoomB(roomNumInteger)]
							.setCustomerName(customer[customerIndex].getUsersName());
					getArrayRoomB(roomNumInteger)[findIndexRoomB(roomNumInteger)]
							.setRealCustomerName(customer[customerIndex].getRealUsersName()[i]);

					customerPayRoomBPrice(customerIndex, roomNum, bedType, roomNumInteger);// 이름으로 방번호를 찾은후 방번호에
																							// 해당하는
																							// bedtype에 따른 고객의 비용 지불
					break;
				}

			}
		} else {
			System.out.println("예약할 수 없습니다.");

		}
	}

	public int getRoomNumberToRealUser(int input, int costomerIndex, int roomIndex) {
		if (roomIndex == 0) {
			if (customer[costomerIndex].getRooms()[roomIndex] == input) {
				return 1;
			}
		} else if (roomIndex == 1) {
			if (customer[costomerIndex].getRooms()[roomIndex] == input) {
				return 1;
			}

		} else if (roomIndex == 2) {
			if (customer[costomerIndex].getRooms()[roomIndex] == input) {
				return 1;
			}

		}
		return 0;

	}

	public int getRoomNumberToRealUserNameSearch(String name) {
		for (int i = 0; i < 20; i++) {
			if (rooms2[i].getRealCustomerName().equals(name)) {
				return (201 + i);
			} else if (rooms3[i].getRealCustomerName().equals(name)) {
				return (301 + i);
			} else if (rooms4[i].getRealCustomerName().equals(name)) {
				return (401 + i);
			} else if (rooms5[i].getRealCustomerName().equals(name)) {
				return (501 + i);
			}
		}
		return 1;

	}

	public int roomsCountReturn(int customerIndex) {
		int count = 0;
		for (int i = 0; i < 3; i++) {
			if (customer[customerIndex].getRooms()[i] != 0) {
				count++;
			}
		}
		return count;

	}

	// 재민수정2
		public void roomInfoChange(int checkManager) {
			System.out.println("방 상태 변경 메뉴입니다.");
			System.out.println("어떻게 변경하겠습니까?");
			System.out.println("1. 체크인으로 변경");
			System.out.println("2. 체크아웃으로 변경");
			String choose = scanner.next();
			scanner.nextLine();
			switch (choose) {
			case "1":
				System.out.println("어떤 호텔을 선택할까요?(A/B)");
				choose = scanner.next();
				scanner.nextLine();
				if (choose.equals("A") || choose.equals("a"))
					makeRoomCheckIn(checkManager);
				else if (choose.equals("B") || choose.equals("b"))
					makeRoomBCheckIn(checkManager);
				else
					System.out.println("올바른 입력을 하세요.");
				break;
			case "2":
				System.out.println("어떤 호텔을 선택할까요?(A/B)");
				choose = scanner.next();
				scanner.nextLine();
				if (choose.equals("A") || choose.equals("a"))
					makeRoomCheckOut(checkManager);
				else if (choose.equals("B") || choose.equals("b"))
					makeRoomBCheckOut(checkManager);
				else

					System.out.println("올바른 입력을 하세요.");
				break;
			default:
				System.out.println("올바른 번호를 입력하세요.");
				break;
			}
		}

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

	// 워크인이나 예약시 고객에게 돈을 받아 결제하는 메소드
	public void customerPayRoomPrice(int roomsNumber, int customerIndex, boolean direct) {

		if (customer[customerIndex].getMoney() >= 30000 - discountMoney(customer[customerIndex].getRank())
				&& getArrayRoom(roomsNumber / 100)[roomNumberChange(roomsNumber)].getBedType().equals("single")) {
			customer[customerIndex].setMoney(customer[findCustomerByRoomNumber(roomsNumber)].getMoney() - 30000
					+ discountMoney(customer[customerIndex].getRank()));
			receivedMoney += (30000 - discountMoney(customer[customerIndex].getRank()));

			System.out.println("\n" + (30000 - discountMoney(customer[customerIndex].getRank())) + " 원이 결제되었습니다.");
			customer[customerIndex].plusMoneyInfo((30000 - discountMoney(customer[customerIndex].getRank())));

			if (direct) {
				getArrayRoom(roomsNumber / 100)[roomNumberChange(roomsNumber)].setRoomsInfo(1);
			} else {
				getArrayRoom(roomsNumber / 100)[roomNumberChange(roomsNumber)].setRoomsInfo(2);
			}
			customer[customerIndex].plusUseMoney(30000 - discountMoney(customer[customerIndex].getRank()));
			changeRank(customerIndex);

		} else if (customer[customerIndex].getMoney() >= 40000 - discountMoney(customer[customerIndex].getRank())
				&& getArrayRoom(roomsNumber / 100)[roomNumberChange(roomsNumber)].getBedType().equals("double")) {
			customer[customerIndex].setMoney(customer[findCustomerByRoomNumber(roomsNumber)].getMoney() - 40000
					+ discountMoney(customer[customerIndex].getRank()));
			receivedMoney += (40000 - discountMoney(customer[customerIndex].getRank()));

			System.out.println("\n" + (40000 - discountMoney(customer[customerIndex].getRank())) + " 원이 결제되었습니다.");
			customer[customerIndex].plusMoneyInfo((40000 - discountMoney(customer[customerIndex].getRank())));

			if (direct) {
				getArrayRoom(roomsNumber / 100)[roomNumberChange(roomsNumber)].setRoomsInfo(1);
			} else {
				getArrayRoom(roomsNumber / 100)[roomNumberChange(roomsNumber)].setRoomsInfo(2);
			}

			customer[customerIndex].plusUseMoney(40000 - discountMoney(customer[customerIndex].getRank()));
			changeRank(customerIndex);

		} else if (customer[customerIndex].getMoney() >= 50000 - discountMoney(customer[customerIndex].getRank())
				&& getArrayRoom(roomsNumber / 100)[roomNumberChange(roomsNumber)].getBedType().equals("twin")) {
			customer[customerIndex].setMoney(customer[findCustomerByRoomNumber(roomsNumber)].getMoney() - 50000
					+ discountMoney(customer[customerIndex].getRank()));
			receivedMoney += (50000 - discountMoney(customer[customerIndex].getRank()));

			System.out.println("\n" + (50000 - discountMoney(customer[customerIndex].getRank())) + " 원이 결제되었습니다.");
			customer[customerIndex].plusMoneyInfo((50000 - discountMoney(customer[customerIndex].getRank())));

			if (direct) {
				getArrayRoom(roomsNumber / 100)[roomNumberChange(roomsNumber)].setRoomsInfo(1);
			} else {
				getArrayRoom(roomsNumber / 100)[roomNumberChange(roomsNumber)].setRoomsInfo(2);
			}

			customer[customerIndex].plusUseMoney(50000 - discountMoney(customer[customerIndex].getRank()));
			changeRank(customerIndex);
		} else {
			System.out.println("잔액이 부족합니다.");
		}

	}

	// 재민수정
	public void customerPayRoomBPrice(int customerIndex, String roomsNumber, String bedType, int roomNumInteger) {

		if (customer[customerIndex].getMoney() >= 30000 && bedType.equals("single")) {
			customer[customerIndex].setMoney(customer[customerIndex].getMoney() - 30000);
			receivedMoney += 30000;
			System.out.println("3만원이 결제되었습니다.");
			System.out.println("우리가 번돈" + receivedMoney + "원");

		} else if (customer[customerIndex].getMoney() >= 40000 && bedType.equals("double")) {
			customer[customerIndex].setMoney(customer[customerIndex].getMoney() - 40000);
			receivedMoney += 40000;
			System.out.println("4만원이 결제되었습니다.");
		} else {
			System.out.println("잔액이 부족합니다.");
		}

		getArrayRoomB(roomNumInteger)[findIndexRoomB(roomNumInteger)].setRoomsInfo(1);

	}

	// 워크인이나 예약 취소시 고객에게 돈을 돌려주는 메소드
	public void customerPayBackRoomPrice(int roomsNumber, int customerIndex, int roomArrayIndex) {

		receivedMoney -= customer[customerIndex].getMoneyInfo()[roomArrayIndex];

		System.out.printf("%d원이 반환되었습니다.\n", customer[customerIndex].getMoneyInfo()[roomArrayIndex]);

		customer[customerIndex].plusMoney(customer[customerIndex].getMoneyInfo()[roomArrayIndex]);
		customer[customerIndex].plusUseMoney(-customer[customerIndex].getMoneyInfo()[roomArrayIndex]);
		customer[customerIndex].changeMoneyInfo(0, roomArrayIndex);
		
	}

	public void makeRoomCheckIn(int checkManager) {
		// 예약한 경우만 가능 , 예약을 안했을 경우를 추가
		// 이름이랑 폰 번호를 입력한 뒤 대조하여 방의 이름을 출력하고 체크인 할지 물어보자

		System.out.println("예약하고 오셨습니까?(Y/N)");
		System.out.print("입력 : ");
		String answer = scanner.next();
		scanner.nextLine();
		if (answer.equals("Y") || answer.equals("y")) {
			System.out.println("실 사용자분의 성함을 말씀해주세요");
			String realName = scanner.next();
			System.out.print("입력 : ");
			int roomNumber = getRoomNumberToRealUserNameSearch(realName);
			if (roomNumber == 1) {
				System.out.println("예약하신 기록을 찾을 수 없습니다.");
			} else {
				int[] room = new int[3];
				int chooseCount = 0;
				System.out.print("예약하신 방은 ");
				for (int j = 2; j < 6; j++) {
					for (int i = 0, k = 0; i < 20; i++) {
						if (getArrayRoom(j)[i].getRealCustomerName().equals(realName)
								&& getArrayRoom(j)[i].getRoomsInfo() == 1) {
							System.out.printf(" %d", (j * 100) + i + 1);
							room[k] = (j * 100) + i + 1;
							k++;
							chooseCount++;
						}
					}
				}

				if (chooseCount > 1) {
					System.out.println("호실 입니다.");
					System.out.println("모두 체크인 하시겠습니까? (Y/N)");
					System.out.print("입력 : ");
					String select = scanner.next();
					if (select.equals("y") || select.equals("Y")) {
						for (int i = 0; i < 3; i++) {
							if (room[i] != 0) {
								getArrayRoom(room[i] / 100)[roomNumberChange(room[i])].setRoomsInfo(2);
							}
						}
						System.out.println("체크인이 모두 완료되었습니다.");
					} else {
						System.out.println("체크인 하실 방 번호를 입력 해주세요 (ex : 201)");
						System.out.println("체크인 하지 않고 나가려면 9번을 입력 해주세요");
						System.out.print("입력 : ");
						int chooseRoom = scanner.nextInt();
						while (true) {
							int count = 0;
							for (int i = 0; i < 3; i++) {
								if (room[i] == chooseRoom) {
									count++;
								}
							}
							if (count == 0) {
								System.out.println("정상적인 번호를 입력해주세요");
								chooseRoom = scanner.nextInt();
							} else if (chooseRoom == 9) {
								break;
							} else {
								System.out.println(chooseRoom + "호실 체크인이 완료 되었습니다.");
								getArrayRoom(chooseRoom / 100)[chooseRoom % 100 - 1].setRoomsInfo(2);
								break;
							}

						}

					}

				} else if (chooseCount == 1) {
					System.out.println("호실 입니다.");
					System.out.println("체크인 하시겠습니까? (Y/N)");
					System.out.print("입력 : ");
					String answer2 = scanner.next();
					scanner.nextLine();
					if (answer2.equals("Y") || answer2.equals("y")) {
						getArrayRoom(roomNumber / 100)[roomNumberChange(roomNumber)].setRoomsInfo(2);
						System.out.printf("%d호실 체크인이 완료되었습니다.", roomNumber);
					}

				}

			}

		} else {

			makeCustomerInfo(checkManager, false);

		}

	}


	// 재민수정2
	public void makeRoomBCheckIn(int checkManager) {
		// 예약한 경우만 가능 , 예약을 안했을 경우를 추가
		// 이름이랑 폰 번호를 입력한 뒤 대조하여 방의 이름을 출력하고 체크인 할지 물어보자

		System.out.println("예약자분의 성함을 말씀해주세요");
		System.out.print("입력 : ");
		String userName = scanner.next();
		System.out.println("예약자분의 전화번호를 말씀해주세요");
		System.out.print("입력 : ");
		String userPhoneNumber = scanner.next();

		int customerIndex = -1;
		String[] roomNumberArr = { "", "", "" };
		for (int i = 0; i < customer.length; i++) {
			if (customer[i].getUsersName().equals(userName) && customer[i].getPhoneNumber().equals(userPhoneNumber)) {
				for (int j = 0; j < 3; j++) {
					if (customer[i].getRoomsB()[j] != "") {
						char changeChar = customer[i].getRoomsB()[j].charAt(0);
						int roomNumberInteger = changeChar;
						if (getArrayRoomB(roomNumberInteger)[findIndexRoomB(roomNumberInteger)].getRoomsInfo() == 1) {
							System.out.println("예약하신 방은 B동의 " + customer[i].getRoomsB()[j] + "호입니다.");
							roomNumberArr[j] = customer[i].getRoomsB()[j];
							customerIndex = i;
						}
					}
				}

			}
		}

		if (customerIndex >= 0) {
			while (true) {

				System.out.println("모두 체크인 하시겠습니까? (Y/N)");
				System.out.print("입력 : ");
				String select = scanner.next();
				if (select.equals("y") || select.equals("Y")) {
					for (int i = 0; i < 3; i++) {
						if (roomNumberArr[i] != "") {
							char changeChar = roomNumberArr[i].charAt(0);
							int roomNumberInteger = changeChar;
							getArrayRoomB(roomNumberInteger)[findIndexRoomB(roomNumberInteger)].setRoomsInfo(2);
						}
					}
					System.out.println("체크인이 모두 완료되었습니다.");
					break;
				} else if (select.equals("N") || select.equals("n")) {
					System.out.println("체크인 하실 방 번호를 입력 해주세요 (ex : A)");
					for (int i = 0; i < 3; i++) {
						if (roomNumberArr[i] != "") {
							System.out.println("예약한 방 : " + roomNumberArr[i] + "호실");
						}
					}
					System.out.println("체크인 하지 않고 나가려면 9번을 입력 해주세요");
					System.out.print("입력 : ");
					String chooseRoom = scanner.next();
					scanner.nextLine();

					while (true) {
						int count = 0;
						for (int i = 0; i < 3; i++) {
							if (roomNumberArr[i].equals(chooseRoom)) {
								count++;
							}
						}
						if (chooseRoom.equals("9")) {
							System.out.println("초기 화면으로 돌아갑니다.");
							break;
						}

						else if (count == 0) {
							System.out.println("정상적인 번호를 입력해주세요.");
							System.out.print("입력 : ");
							chooseRoom = scanner.next();
							scanner.nextLine();
						} else {
							System.out.println(chooseRoom + "호실 체크인이 완료 되었습니다.");
							char changeChar = chooseRoom.charAt(0);
							int roomNumberInteger = changeChar;
							getArrayRoomB(roomNumberInteger)[findIndexRoomB(roomNumberInteger)].setRoomsInfo(2);
							break;
						}

					}
					break;
				} else {
					System.out.println("올바른 입력을 하세요.");
				}
			}
		} else {
			System.out.println("예약 중인 방이 없습니다.");
		}

	}
	
	
	public void makeRoomCheckOut(int checkManager) {

		System.out.println("실 사용자 이름을 입력해주세요.");
		String realName = scanner.nextLine();
		System.out.println("실 사용자의 전화번호를 입력해주세요");
		String realPhone = scanner.nextLine();

		int saveRoomFloorNum = -1;
		int saveRoomIndexNum = -1;
		int customerIndex = 0;

		int count = 0;
		for (int j = 2; j < 6; j++) {
			for (int i = 0; i < getArrayRoom(j).length; i++) {
				if (getArrayRoom(j)[i].getRealCustomerName().equals(realName)
						&& getArrayRoom(j)[i].getRoomsInfo() == 2) {
					if (getArrayRoom(j)[i].getRealCustomerPhone().equals(realPhone)) {

						count++;
					}
				}
			}
		}

		for (int j = 2; j < 6; j++) {
			for (int i = 0; i < 20; i++) {
				if (getArrayRoom(j)[i].getRealCustomerName().equals(realName)
						&& getArrayRoom(j)[i].getRoomsInfo() == 2) {
					saveRoomFloorNum = j;
					saveRoomIndexNum = i;
				}
			}
		}

		if (saveRoomFloorNum == -1 && saveRoomIndexNum == -1) {
			System.out.println("체크인 이력을 확인할 수 없습니다.");
			return;
		}

		if (count == 1) {

			for (int i = 0; i < customer.length; i++) {
				for (int j = 0; j < 3; j++) {
					if (customer[i].getRealUsersName()[j].equals(realName)) {
						customerIndex = i;
						break;
					}
				}
			}

			int RoomsNumber = saveRoomFloorNum * 100 + saveRoomIndexNum + 1;

			int checkRoomsInfo = showRoomInfo(RoomsNumber);

			if (checkRoomsInfo == 2) {
				System.out.println("체크아웃 하시겠습니까?(Y/N)");
				String answer = scanner.next();
				scanner.nextLine();
				if (answer.equals("Y") || answer.equals("y")) {

					getArrayRoom(RoomsNumber / 100)[roomNumberChange(RoomsNumber)].setRoomsInfo(3);

					if (customer[customerIndex].isMember() == false) {
						customer[customerIndex].setUsersName("");
						customer[customerIndex].setPhoneNumber("");
					}

					for (int j = 0; j < 3; j++) {
						if (customer[customerIndex].getRooms()[j] == RoomsNumber) {
							customer[customerIndex].cancelRoom(j);
							customer[customerIndex].cancelPersnalNumber(j);
							customer[customerIndex].cancelRealPhoneNumber(j);
							customer[customerIndex].cancelRealUserName(j);
						}
					}

					getArrayRoom(RoomsNumber / 100)[roomNumberChange(RoomsNumber)].setCustomerName("");
					getArrayRoom(RoomsNumber / 100)[roomNumberChange(RoomsNumber)].setRealCustomerName("");

					System.out.println(RoomsNumber + "번 방 체크아웃이 완료되었습니다.");
				} else {
					System.out.println("체크아웃 하지 않습니다.");
				}

			} else {
				System.out.println("관리자 메뉴로 돌아갑니다.");
			}

		} else if (count > 1) {
			int[] roomNumber = new int[count];
			System.out.print("체크인 하신 방은");
			for (int j = 2; j < 6; j++) {
				for (int i = 0, k = 0; i < getArrayRoom(j).length; i++) {
					if (getArrayRoom(j)[i].getRealCustomerName().equals(realName)
							&& getArrayRoom(j)[i].getRoomsInfo() == 2) {
						if (getArrayRoom(j)[i].getRealCustomerPhone().equals(realPhone)) {
							System.out.print((j * 100 + i + 1) + "/");
							roomNumber[k] = (j * 100 + i + 1);
							k++;
						}
					}
				}
			}
			System.out.println("호실 입니다.");
			System.out.println();
			System.out.println("모든 객실을 체크아웃 하시겠습니까? Y/N");
			System.out.print("입력 : ");
			String input = scanner.nextLine();
			if (input.equals("y") || input.equals("Y")) {
				System.out.println("모든 객실의 체크아웃이 완료 되었습니다.");
				for (int i = 0; i < roomNumber.length; i++) {
					getArrayRoom(roomNumber[i] / 100)[roomNumber[i] % 100 - 1].setRoomsInfo(3);
				}

				for (int i = 0; i < roomNumber.length; i++) {
					for (int j = 0; j < 3; j++) {
						if (customer[customerIndex].getRooms()[j] == roomNumber[i]) {
							customer[customerIndex].cancelRoom(j);
							customer[customerIndex].cancelPersnalNumber(j);
							customer[customerIndex].cancelRealPhoneNumber(j);
							customer[customerIndex].cancelRealUserName(j);

						}
					}
				}

			} else {
				System.out.println("체크아웃을 원하는 객실을 입력해주세요.");
				int input2 = scanner.nextInt();

				inputCheck: while (true) {
					for (int i = 0; i < roomNumber.length; i++) {
						if (roomNumber[i] == input2) {

							break inputCheck;
						}
					}
					System.out.println("고객이 체크인을 했었던 객실만 체크아웃 할 수 있습니다.");
					input2 = scanner.nextInt();
				}

				for (int i = 0; i < 3; i++) {
					if (customer[customerIndex].getRooms()[i] == input2) {
						customer[customerIndex].cancelRoom(i);
						customer[customerIndex].cancelPersnalNumber(i);
						customer[customerIndex].cancelRealPhoneNumber(i);
						customer[customerIndex].cancelRealUserName(i);
						getArrayRoom(input2 / 100)[input2 % 100 - 1].setRoomsInfo(3);
					}
				}
				System.out.println(input2 + "호실 체크아웃이 완료 되었습니다.");
				System.out.println();

			}
		}
	}
	
	public void makeRoomBCheckOut(int checkManager) {

		System.out.println("이용자분의 성함을 말씀해주세요");
		System.out.print("입력 : ");
		String realuserName = scanner.next();
		System.out.println("이용자분의 전화번호를 말씀해주세요");
		System.out.print("입력 : ");
		String realuserPhoneNumber = scanner.next();

		int customerIndex = -1;
		String[] roomBNumberArr = { "", "", "" };
		
		for (int i = 0; i < customer.length; i++) {
			if (customer[i].getUsersName().equals(realuserName) && customer[i].getPhoneNumber().equals(realuserPhoneNumber)) {
				for (int j = 0; j < 3; j++) {
					if (customer[i].getRoomsB()[j] != "") {
						char changeChar = customer[i].getRoomsB()[j].charAt(0);
						int roomNumberInteger = changeChar;
						if (getArrayRoomB(roomNumberInteger)[findIndexRoomB(roomNumberInteger)].getRoomsInfo() == 2) {
							System.out.println("체크인하신 방은 B동의 " + customer[i].getRoomsB()[j] + "호입니다.");
							roomBNumberArr[j] = customer[i].getRoomsB()[j];
							customerIndex = i;
						}
					}
				}

			}
		}


		if (customerIndex >= 0) {
			while (true) {

					System.out.println("체크인 하실 방 번호를 입력 해주세요 (ex : A)");
					for (int i = 0; i < 3; i++) {
						if (roomBNumberArr[i] != "") {
							System.out.println("예약한 방 : " + roomBNumberArr[i] + "호실");
						}
					}
					System.out.println("체크아웃 하지 않고 나가려면 9번을 입력 해주세요");
					System.out.print("입력 : ");
					String chooseRoom = scanner.next();
					scanner.nextLine();

					while (true) {
						int count = 0;
						for (int i = 0; i < 3; i++) {
							if (roomBNumberArr[i].equals(chooseRoom)) {
								count++;
							}
						}
						if (chooseRoom.equals("9")) {
							System.out.println("초기 화면으로 돌아갑니다.");
							break;
						}

						else if (count == 0) {
							System.out.println("정상적인 번호를 입력해주세요.");
							System.out.print("입력 : ");
							chooseRoom = scanner.next();
							scanner.nextLine();
						} else {
							System.out.println(chooseRoom + "호실 체크아웃이 완료 되었습니다.");
							if (customer[customerIndex].isMember() == false) {
								customer[customerIndex].setUsersName("");
								customer[customerIndex].setPhoneNumber("");
							}
							for (int j = 0; j < 3; j++) {
								if (customer[customerIndex].getRoomsB()[j].equals(chooseRoom)) {
									customer[customerIndex].cancelRoomB(j);
									customer[customerIndex].cancelPersonalNumberB(j);
									customer[customerIndex].cancelRealPhoneNumberB(j);
									customer[customerIndex].cancelRealUserNameB(j);
								}
							}
							
							char changeChar = chooseRoom.charAt(0);
							int roomNumberInteger = changeChar;
							getArrayRoomB(roomNumberInteger)[findIndexRoomB(roomNumberInteger)].setRoomsInfo(3);
							break;
						}

					}
					break;
				
			}
		} else {
			System.out.println("체크인 중인 방이 없습니다.");
		}
	}
	
	
	

	// 메소드에 입력된 층수의 빈 방이 몇 개 있는지 출력하기 위해 작성
	public int emptyRoomCheck(int floorNum, int type) {

		int result = 0;

		if (type == 1) {
			for (int i = 0; i < 20; i++) {
				if (i % 2 == 0 && getArrayRoom(floorNum)[i].getRoomsInfo() == 0) {
					result++;
				}
			}
		} else if (type == 2) {
			for (int i = 0; i < 20; i++) {
				if (i % 2 != 0 && getArrayRoom(floorNum)[i].getRoomsInfo() == 0) {
					result++;
				}
			}
		} else if (type == 3) {
			for (int i = 0; i < 20; i++) {
				if (getArrayRoom(floorNum)[i].getRoomsInfo() == 0) {
					result++;
				}
			}
		}

		return result;
	}

	public int emptyRoomBCheck(int floorNum, int type) {// 재민수정
		// single 1층 65 66 67 68 69 /2층 75 76 77 78 79 /3층 84 85 86 87 88
		// double 1층 70 71 72 73 74 /2층 80 81 82 83 /3층 89 90
		// type 1이면 싱글을 , 2면 더블을
		int count = 0;
		if (floorNum == 1 && type == 1) {
			for (int i = 0; i < 5; i++) {
				if (roomsB1[i].getRoomsInfo() == 0) {
					count++;
				}
			}

		}
		if (floorNum == 1 && type == 2) {
			for (int i = 5; i < 10; i++) {
				if (roomsB1[i].getRoomsInfo() == 0) {
					count++;
				}
			}

		}
		if (floorNum == 2 && type == 1) {
			for (int i = 0; i < 5; i++) {
				if (roomsB2[i].getRoomsInfo() == 0) {
					count++;
				}
			}

		}
		if (floorNum == 2 && type == 2) {
			for (int i = 5; i < 9; i++) {
				if (roomsB2[i].getRoomsInfo() == 0) {
					count++;
				}
			}

		}
		if (floorNum == 3 && type == 1) {
			for (int i = 0; i < 5; i++) {
				if (roomsB3[i].getRoomsInfo() == 0) {
					count++;
				}
			}

		}
		if (floorNum == 3 && type == 2) {
			for (int i = 5; i < 7; i++) {
				if (roomsB3[i].getRoomsInfo() == 0) {
					count++;
				}
			}
		}

		return count;
	}

	// 사용자가 방을 고를 때 201같은 번호로 입력하기 때문에 그 값을 직접 대입할 인덱스 번호로 변환하는 메소드
	// ex : 220을 고르면 100의 자리를 제외하고 남은 값에서 -1값을 출력 (19)

	public int roomNumberChange(int num) {

		while (true) {

			if (num >= 200 && 220 >= num) {
				return num - 201;
			} else if (num >= 300 && 320 >= num) {
				return num - 301;
			} else if (num >= 400 && 420 >= num) {
				return num - 401;
			} else if (num >= 500 && 520 >= num) {
				return num - 501;
			} else {
				System.out.println("정상적인 번호를 입력해주세요");
				System.out.println("예시 201호실은 201을 입력");
				num = scanner.nextInt();
			}
		}

	}

	public Rooms[] getArrayRoom(int floorNum) {
		if (floorNum == 2) {
			return rooms2;
		} else if (floorNum == 3) {
			return rooms3;
		} else if (floorNum == 4) {
			return rooms4;
		} else {
			return rooms5;
		}
	}

	public int roomBFloorFind(int roomNumberInteger) {

		if (roomNumberInteger >= 65 || roomNumberInteger < 75) {
			return 1;

		} else if (roomNumberInteger >= 75 || roomNumberInteger < 84) {
			return 2;
		} else if (roomNumberInteger >= 84 || roomNumberInteger < 90) {
			return 3;
		}
		return -1;

	}

	// 재민수정
	public RoomsB[] getArrayRoomB(int roomNumInteger) {
		
		if (roomNumInteger >= 65 && roomNumInteger < 75) {
			return roomsB1;
		} else if (roomNumInteger >= 75 && roomNumInteger < 84) {
			return roomsB2;
		} else if (roomNumInteger >= 84 && roomNumInteger < 91) {
			return roomsB3;
		} else {
			return roomsB1;
		}
	}

	// 재민수정
	public int findIndexRoomB(int roomNumberInteger) {

		if (roomNumberInteger >= 65 && roomNumberInteger < 75) {
			return roomNumberInteger - 65;

		} else if (roomNumberInteger >= 75 && roomNumberInteger < 84) {
			return roomNumberInteger - 75;
		} else {
			return roomNumberInteger - 84;
		}

	}

	public int settingUserNameAndReturnIndex(String name) {
		int index = -1;
		for (int i = 0; i < customer.length; i++) {
			if (customer[i].getUsersName().equals("")) {
				customer[i].setUsersName(name);
				index = i;
				break;
			}
		}
		return index;
	}

	public int findCustomerByRoomNumber(int roomNumber) {
		for (int i = 0; i < customer.length; i++) {
			for (int j = 0; j < 3; j++) {
				if (customer[i].getRooms()[j] == roomNumber) {
					return i;
				}
			}
		}
		return -1;
	}

	public void roomcheck() {
		System.out.println("확인할 객실 번호를 입력해 주세요.");
		int inputNumber = scanner.nextInt();
		if (getArrayRoom(inputNumber / 100)[roomNumberChange(inputNumber)].getRoomsInfo() == 0) {
			System.out.println(inputNumber + "객실 정비 완료. 체크인 가능");
			System.out.println();
		} else if (getArrayRoom(inputNumber / 100)[roomNumberChange(inputNumber)].getRoomsInfo() == 4) {
			System.out.println(inputNumber + "객실은 점검중입니다.");
			System.out.println();
		} else if (getArrayRoom(inputNumber / 100)[roomNumberChange(inputNumber)].getRoomsInfo() == 2) {
			System.out.println(inputNumber + "객실은 투숙중인 객실입니다.");
			System.out.println();
		} else if (getArrayRoom(inputNumber / 100)[roomNumberChange(inputNumber)].getRoomsInfo() == 3) {
			System.out.println(inputNumber + "객실은 청소 미완료 객실입니다.");
			System.out.println();

		}
	}

	public void roomcheck3() {
		System.out.println("객실 점검하기를 선택하셨습니다.");
		System.out.println("점검으로 변경할 객실 번호를 입력해 주세요");
		int roomNumbers = scanner.nextInt();
		System.out.println(roomNumbers + "를 선택하셨습니다.");
		if (rooms2[roomNumberChange(roomNumbers)].getRoomsInfo() == 4) {
			System.out.println(roomNumbers + "점검중입니다.");
			rooms2[roomNumberChange(roomNumbers)].setRoomsInfo(0);
		} else if (rooms3[roomNumberChange(roomNumbers)].getRoomsInfo() == 4) {
			System.out.println(roomNumbers + "점검중입니다.");
			rooms3[roomNumberChange(roomNumbers)].setRoomsInfo(0);
		} else if (rooms4[roomNumberChange(roomNumbers)].getRoomsInfo() == 4) {
			System.out.println(roomNumbers + "점검중입니다.");
			rooms4[roomNumberChange(roomNumbers)].setRoomsInfo(0);
		} else if (rooms5[roomNumberChange(roomNumbers)].getRoomsInfo() == 4) {
			System.out.println(roomNumbers + "점검중입니다.");
			rooms5[roomNumberChange(roomNumbers)].setRoomsInfo(0);

		} else {
			System.out.println(roomNumbers + "이미 점검이 완료된 방입니다.");
		}
	}

// 전체층수, 전체청소하기
	public void roomcheck2() {
		System.out.println("객실 청소하기를 선택하셨습니다.");
		System.out.println("청소할 객실 번호를 입력해 주세요.");
		int roomNumbers = scanner.nextInt();
		System.out.println(roomNumbers + "를 선택하셨습니다.");
		if (getArrayRoom(roomNumbers / 100)[roomNumberChange(roomNumbers)].getRoomsInfo() == 3) {
			System.out.println(roomNumbers + "청소가 완료되었습니다.");
			getArrayRoom(roomNumbers / 100)[roomNumberChange(roomNumbers)].setRoomsInfo(0);

		} else {
			System.out.println(roomNumbers + "이미 청소가 완료된 방입니다.");
		}

		roomRepair(roomNumbers);

	}

// 3 체크아웃 해당 객실 info 3 인경우 제외 청소 필요없습니다.
	public void roomRepair(int roomNumber) {
		if (roomNumber == 0) {
			System.out.println("방 번호를 입력해 주세요.");
			roomNumber = scanner.nextInt();
		}
		System.out.println("점검이 필요한 객실입니까?");
		System.out.println("1. 점검이 필요합니다.");
		System.out.println("2. 점검이 필요하지 않습니다.");
		int roomNumber2 = scanner.nextInt();
		if (roomNumber2 == 1) {
			getArrayRoom(roomNumber / 100)[roomNumberChange(roomNumber)].setRoomsInfo(4);
			System.out.printf("%d호실에 관리자를 호출합니다.\n", roomNumber);
			System.out.printf("%d호실 객실을 점검중으로 전환합니다.\n", roomNumber);

			if (getArrayRoom(roomNumber / 100)[roomNumberChange(roomNumber)].getRoomsInfo() == 4) {
				System.out.println(roomNumber + "점검중");
			}
			System.out.println();
		}

	}

	public void ShowAllRooms() {
		System.out.println("객실 현황 확인을 선택하셨습니다.");
		System.out.println("객실 현황 확인 방법을 입력해주세요.");
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
						System.out.printf("2%02d호실 O", i + 1);
						System.out.println();
					} else if (rooms2[i].getRoomsInfo() == 3) {
						System.out.printf("2%02d호실 X", i + 1);
						System.out.println();
					} else if (rooms2[i].getRoomsInfo() == 4) {
						System.out.printf("2%02d호실 점검중", i + 1);
						System.out.println();
					}
				}
			}
			if (floorNum == 3) {
				for (int i = 0; i < 20; i++) {
					if (rooms3[i].getRoomsInfo() == 0) {
						System.out.printf("3%02d호실 O", i + 1);
						System.out.println();
					} else if (rooms3[i].getRoomsInfo() == 3) {
						System.out.printf("3%02d호실 X", i + 1);
						System.out.println();

					} else if (rooms3[i].getRoomsInfo() == 4) {
						System.out.printf("3%02d호실 점검중", i + 1);
						System.out.println();
					}

				}
			}

			if (floorNum == 4) {
				for (int i = 0; i < 20; i++) {
					if (rooms4[i].getRoomsInfo() == 0) {
						System.out.printf("4%02d호실 O", i + 1);
						System.out.println();
					} else if (rooms4[i].getRoomsInfo() == 3) {
						System.out.printf("4%02d호실 X", i + 1);
						System.out.println();

					} else if (rooms4[i].getRoomsInfo() == 4) {
						System.out.printf("4%02d호실 점검중", i + 1);
						System.out.println();
					}
				}
			}
			if (floorNum == 5) {
				for (int i = 0; i < 20; i++) {
					if (rooms5[i].getRoomsInfo() == 0) {
						System.out.printf("5%02d호실 O", i + 1);
						System.out.println();
					} else if (rooms5[i].getRoomsInfo() == 3) {
						System.out.printf("5%02d호실 X", i + 1);
						System.out.println();
					} else if (rooms5[i].getRoomsInfo() == 4) {
						System.out.printf("5%02d호실 점검중", i + 1);
						System.out.println();
					}
				}
			}

			System.out.println("청소하시겠습니까?");
			System.out.println("1. 네");
			System.out.println("2. 아니요");
			int a = scanner.nextInt();
			if (a == 1) {
				roomcheck2();
			}
			break;
		case "2":
			roomcheck();

			break;
		default:
			System.out.println("잘못된 입력입니다.");
			break;
		}

	}

	// 청소직원 선택 시 보여주는 화면
	public boolean CleanerProcess() {
		System.out.println("\n==============================");
		System.out.println("청소 직원 모드");
		System.out.println("환영합니다.");
		System.out.println("1. 객실 현황확인");
		System.out.println("2. 청소하기");
		System.out.println("3. 점검 : 관리자 호출");
		System.out.println("9. 로그아웃");
		System.out.println("\n==============================");
		System.out.print("입력 : ");
		String choose = scanner.next();

		// 객실 전체 살펴보기 // 객실 선택 후 살펴보기
// 분실물에 따른 고객정보를 저장
		switch (choose) {
		case "1":
			ShowAllRooms();

			return true;
		case "2":
			roomcheck2();

			return true;
		case "3":
			roomRepair(0);

			return true;
		case "9":

			return false;
		default:
			System.out.println("잘못된 입력입니다.");
			return true;

		}

	}

	public void changeRank(int customerIndex) {
		if (customer[customerIndex].isMember() && customer[customerIndex].getUseMoney() >= 50000
				&& customer[customerIndex].getRank().equals("브론즈")) {
			System.out.println("등급이 \"브론즈\"등급에서 \"실버\"등급으로 상승하였습니다.");
			customer[customerIndex].setRank("실버");
		}

		if (customer[customerIndex].isMember() && customer[customerIndex].getUseMoney() >= 100000
				&& customer[customerIndex].getRank().equals("실버")) {
			System.out.println("등급이 \"실버\"등급에서 \"골드\"등급으로 상승하였습니다.");
			customer[customerIndex].setRank("골드");
		}

		if (customer[customerIndex].isMember() && customer[customerIndex].getUseMoney() >= 150000
				&& customer[customerIndex].getRank().equals("골드")) {
			System.out.println("등급이 \"골드\"등급에서 \"다이아몬드\"등급으로 상승하였습니다.");
			customer[customerIndex].setRank("다이아몬드");
		}
	}

	public int discountMoney(String rank) {
		if (rank.equals("실버")) {
			return 2000;
		}
		if (rank.equals("골드")) {
			return 4000;
		}
		if (rank.equals("다이아몬드")) {
			return 7000;
		}
		return 0;
	}

}
