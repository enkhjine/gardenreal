package hospital.businessentity;

public class LTime {
	private int hour;
	private int minute;
	
	public LTime(){
		super();
	}
	
	public void addMinute(int m){
		this.minute += m;
		while(this.minute > 59){
			this.minute -= 60;
			addHour(1);
		}
	}
	
	public void addHour(int h){
		this.hour += h;
		if(this.hour > 24) this.hour -= 24;
	}
	
	public int getHour() {
		return hour;
	}
	
	public void setHour(int hour) {
		this.hour = hour;
	}
	
	public int getMinute() {
		return minute;
	}
	
	public void setMinute(int minute) {
		this.minute = minute;
	}
}
