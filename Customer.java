
public class Customer {

	private String usersName;			// 
	private String realUsersName;		// 
	private String phoneNumber;			// 
	private String realphoneNumber;		// 
	private String personalNumber;		// 
	private Rooms[] rooms = new Rooms[3];	// 
	
	public Customer(String usersName, String realUsersName, String phoneNumber, String realphoneNumber,
			String personalNumber, Rooms[] rooms) {
		super();
		this.usersName = usersName;
		this.realUsersName = realUsersName;
		this.phoneNumber = phoneNumber;
		this.realphoneNumber = realphoneNumber;
		this.personalNumber = personalNumber;
		this.rooms = rooms;
	}
	
	public String getUsersName() {
		return usersName;
	}
	public void setUsersName(String usersName) {
		this.usersName = usersName;
	}
	public String getRealUsersName() {
		return realUsersName;
	}
	public void setRealUsersName(String realUsersName) {
		this.realUsersName = realUsersName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getRealphoneNumber() {
		return realphoneNumber;
	}
	public void setRealphoneNumber(String realphoneNumber) {
		this.realphoneNumber = realphoneNumber;
	}
	public String getPersonalNumber() {
		return personalNumber;
	}
	public void setPersonalNumber(String personalNumber) {
		this.personalNumber = personalNumber;
	}
	public Rooms[] getRooms() {
		return rooms;
	}
	public void setRooms(Rooms[] rooms) {
		this.rooms = rooms;
	}

}