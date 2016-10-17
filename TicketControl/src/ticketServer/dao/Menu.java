package ticketServer.dao;

public class Menu {
	private int num;
	private String information_date;
	private int menu_type;
	private String rice;
	private String soup;
	private String sidedish1;
	private String sidedish2;
	private String sidedish3;
	private String image;
	
	public Menu() {
		this(0, null, 0, null, null, null, null, null, null);
	}

	public Menu(int num, String information_date, int menu_type, String rice, String soup, String sidedish1,
			String sidedish2, String sidedish3, String image) {
		this.num = num;
		this.information_date = information_date;
		this.menu_type = menu_type;
		this.rice = rice;
		this.soup = soup;
		this.sidedish1 = sidedish1;
		this.sidedish2 = sidedish2;
		this.sidedish3 = sidedish3;
		this.image = image;
	}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getInformation_date() {
		return information_date;
	}
	public void setInformation_date(String information_date) {
		this.information_date = information_date;
	}
	public int getMenu_type() {
		return menu_type;
	}
	public void setMenu_type(int menu_type) {
		this.menu_type = menu_type;
	}
	public String getRice() {
		return rice;
	}
	public void setRice(String rice) {
		this.rice = rice;
	}
	public String getSoup() {
		return soup;
	}
	public void setSoup(String soup) {
		this.soup = soup;
	}
	public String getSidedish1() {
		return sidedish1;
	}
	public void setSidedish1(String sidedish1) {
		this.sidedish1 = sidedish1;
	}
	public String getSidedish2() {
		return sidedish2;
	}
	public void setSidedish2(String sidedish2) {
		this.sidedish2 = sidedish2;
	}
	public String getSidedish3() {
		return sidedish3;
	}
	public void setSidedish3(String sidedish3) {
		this.sidedish3 = sidedish3;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
}
