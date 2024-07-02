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
		System.out.println(count + "권의 책이 있습니다.");
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
		Magazine magazine = new Magazine("아무거나", 1, "양꼬치", "240702");
		VoiceBook voiceBook = new VoiceBook("아무거나2", 2, "칭따오", "이름");
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
//		System.out.println(count + "권의 책이 있습니다.");
	}
}
