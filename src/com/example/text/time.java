package com.example.text;
public class time {
	private int hour;
	private int minute;
	private int second;
	private int mesc;
	public void setHour(int hour){
		this.hour=hour;
	}
	public int getHour(){
		return hour;
	}
	public void setMinute(int minute){
		this.minute=minute;
	}
	public int getminute(){
		return minute;
	}
	public void setSecond(int second){
		this.second=second;
	}
	public int getSecond(){
		return second;
	}
	public void setMesc(int mesc){
		this.mesc=mesc;
	}
	public int getMesc(){
		return mesc;
	} 
    public String toString() {  
        return "hour:" + hour + ", minute:" + minute + ", second:" + second+ ", mesc:" + mesc;  
    }
}
