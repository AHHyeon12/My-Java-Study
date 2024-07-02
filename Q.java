// ������
// ü��
// �� �� ���� : void { "����" �ܼ���� ���� }
// �Ҹ� �� �� ���� : void { ������ �Ҹ� �ܼ���� ���� }

// ����
// ü��
// �� �� ���� : void { "����" �ܼ���� ���� }
// �Ҹ� �� �� ���� : void { ���� �Ҹ� �ܼ���� ���� }

// ---

// �Ϲ����� å Book Ŭ���� �ۼ�. ����, ��������, ���� ��. ������, getter/setter
// ���� Magazine Ŭ����. �߰��� �߸��� ����
// ���̽��� VoiceBook Ŭ����. �߰��� ��������� ����
// ���� Ŭ������ �ν��Ͻ��� �����غ��� �׽�Ʈ�� ��,
// å�� BookShelf Ŭ������ �ۼ�.
//	- (���� å �ν��Ͻ��� ������ Ŭ����)
//  - ������ �� å ������ ��ȯ�ϴ� �޼ҵ� : int

// ---
// 1. ���(Inheritance)?
// 2. ������ Ŭ�������� ������� �Ļ��� Ŭ������ �����̶�� �θ���?

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
		System.out.println("111����");
	}
	// �ڽ� Ŭ�������� override �Ǿ��� ������ ��°��� 111 �������� ����
}

class Duck extends VoiceFlyAway {

	public Duck(int weight, String voice, String fly) {
		super(weight, voice, fly);
	}

	@Override
	public void printvoice() {
		System.out.println("222�в�");
	}
	
}

public class Q {
	public static void main(String[] args) {
		Egle egle = new Egle(20, "����", "����");
//		egle.setVoice("����");
//		egle.setFly("����");

		Duck duck = new Duck(10, "�в�", "������");
//		duck.voice = "�в�";
//		duck.fly = "������ �� ���ƿ�";

		System.out.println("������, ������ ���� �Ҹ��� �����?");
		egle.printvoice();
		duck.printvoice();

		System.out.println("������, ������ �� �� �ֳ���?");
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
