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

class Book {
	private String Title;
	private int Page;
	private String AuthorName;

	public Book(String title, int page, String authorName) {
		super();
		Title = title;
		Page = page;
		AuthorName = authorName;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public int getPage() {
		return Page;
	}

	public void setPage(int page) {
		Page = page;
	}

	public String getAuthorName() {
		return AuthorName;
	}

	public void setAuthorName(String authorName) {
		AuthorName = authorName;
	}

}

class Magazine extends Book {
	private String uploadingdate;

	public Magazine(String title, int page, String authorName, String uploadingdate) {
		super(title, page, authorName);
		this.uploadingdate = uploadingdate;
	}

	public String getUploadingdate() {
		return uploadingdate;
	}

	public void setUploadingdate(String uploadingdate) {
		this.uploadingdate = uploadingdate;
	}

}

class VoiceBook extends Book {
	private String recoderName;

	public VoiceBook(String title, int page, String authorName, String recoderName) {
		super(title, page, authorName);
		this.recoderName = recoderName;
	}

	public String getRecoderName() {
		return recoderName;
	}

	public void setRecoderName(String recoderName) {
		this.recoderName = recoderName;
	}

}

class BookShelf {
	Book[] book = new Book[5];

	public int returnBook() {
		int count = 0;

		for (int i = 0; i < 5; i++) {
			if (book[i] == null) {
			} else
				count++;

		} 
		System.out.println(count + "���� å�� �ֽ��ϴ�.");
		return count;
	} 
	

	
	public BookShelf(Book[] book) {
		super();
		this.book = book;
	}



	public BookShelf() {

	}

	public Book[] getBook() {
		return book;
	}

	public void setBook(Book[] book) {
		this.book = book;
	}

}

public class Q2 {
	public static void main(String[] args) {
		Magazine magazine = new Magazine("�ƹ��ų�", 1, "�粿ġ", "240702");
		VoiceBook voiceBook = new VoiceBook("�ƹ��ų�2", 2, "Ī����", "�̸�");
		Book book = new Book("title", 1, "authorName");
		BookShelf bookshelf = new BookShelf();
		
		Book[] array = {magazine, voiceBook}; 
		
		
		bookshelf.book[0] = magazine;
		bookshelf.book[1] = voiceBook;
		bookshelf.book[3] = voiceBook;
		bookshelf.returnBook();
		
		magazine.getTitle();
		voiceBook.getTitle();
		
		//System.out.println(magazine.getTitle());
		System.out.println(bookshelf.getBook()[0].getTitle());
		System.out.println(bookshelf.getBook()[3].getTitle());
//		int count = 0;
//		for (int i = 0; i < bookshelf.book.length; i++) {
//			if (bookshelf.book[i] == null) {
//
//			} else {
//				count++;
//			}
//		}
//
//		System.out.println(count + "���� å�� �ֽ��ϴ�.");
	}
}
