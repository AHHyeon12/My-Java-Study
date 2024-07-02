// 독수리
// 체중
// 날 수 있음 : void { "난다" 콘솔출력 구현 }
// 소리 낼 수 있음 : void { 독수리 소리 콘솔출력 구현 }

// 오리
// 체중
// 날 수 있음 : void { "난다" 콘솔출력 구현 }
// 소리 낼 수 있음 : void { 오리 소리 콘솔출력 구현 }

// ---

// 일반적인 책 Book 클래스 작성. 제목, 페이지수, 저자 등. 생성자, getter/setter
// 잡지 Magazine 클래스. 추가로 발매일 정보
// 보이스북 VoiceBook 클래스. 추가로 녹음성우명 정보
// 위의 클래스로 인스턴스를 생성해보고 테스트한 후,
// 책장 BookShelf 클래스를 작성.
//	- (여러 책 인스턴스를 가지는 클래스)
//  - 보관된 총 책 개수를 반환하는 메소드 : int

// ---
// 1. 상속(Inheritance)?
// 2. 기존의 클래스에서 상속으로 파생된 클래스를 무엇이라고 부르나?

package QQ;

class VoiceFlyAway {
	private int weight;
	private String voice;
	private String fly;

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getVoice() {
		return voice;
	}

	public void setVoice(String voice) {
		this.voice = voice;
	}

	public String getFly() {
		return fly;
	}

	public void setFly(String fly) {
		this.fly = fly;
	}

	public VoiceFlyAway(int weight, String voice, String fly) {
		super();
		this.weight = weight;
		this.voice = voice;
		this.fly = fly;
	}

	
	
	
	
	public void printvoice() {
		
		System.out.println(voice);
	}

	public void printfly() {
		System.out.println(fly);
	}
}

class Egle extends VoiceFlyAway {

	public Egle(int weight, String voice, String fly) {
		super(weight, voice, fly);

	}

	@Override
	public void printvoice() {
		System.out.println("111쉬익");
	}
	// 자식 클래스에서 override 되었기 때문에 출력값이 111 쉬익으로 나옴
}

class Duck extends VoiceFlyAway {

	public Duck(int weight, String voice, String fly) {
		super(weight, voice, fly);
	}

	@Override
	public void printvoice() {
		System.out.println("222꽥꽥");
	}
	
}

public class Q {
	public static void main(String[] args) {
		Egle egle = new Egle(20, "쉬익", "난다");
//		egle.setVoice("쉬익");
//		egle.setFly("난다");

		Duck duck = new Duck(10, "꽥꽥", "못난다");
//		duck.voice = "꽥꽥";
//		duck.fly = "오리는 못 날아요";

		System.out.println("독수리, 오리가 내는 소리가 뭘까요?");
		egle.printvoice();
		duck.printvoice();

		System.out.println("독수리, 오리는 날 수 있나요?");
		egle.printfly();
		duck.printfly();
//
//		VoiceFlyAway voice1 = egle;
//		VoiceFlyAway voice2 = duck;
//
//		VoiceFlyAway fly1 = egle;
//		VoiceFlyAway fly2 = duck;
//
//		voice1.printvoice();
//		voice2.printvoice();
//
//		fly1.printfly();
//		fly2.printfly();

	}
}
