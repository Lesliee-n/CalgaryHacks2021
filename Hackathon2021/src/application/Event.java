package application;
import java.time.LocalDate;
import java.util.Arrays;

//Event object
public class Event {
	private int time;
	private String day[];
	private String name;
	private int duration;
	private String url;
	private String password;
	private boolean eventActive;
	
	
}



//set variables in object
public void setTime(int newTime) {
	this.time = newTime;
	
}

public void setDay(String newDay[]) {
	
	for(int i : newDay) {
		day[i]=newDay[i];
	}
	
}

public void setName(String newName) {
	this.name = newName;
	
}

public void setDuration(String newDuration) {
	this.duration = newDuration;
	
}

public void setUrl(String newUrl) {
	this.url = newUrl;
	
}

public void setPassword(String newPassword) {
	this.password = newPassword;
	
}

public void eventActive(boolean newEventActive) {
	this.eventActive = newEventActive;
	
}





//get variables in object
public int getTime() {
	return time;
	
}

public String[] getDay() {
	return day;
	
}

public String getName() {
	return name;
	
}

public String getDuration() {
	return duration;
	
}

public String getUrl() {
	return url;
	
}

public String getPassword() {
	return password;
	
}

public boolean eventActive() {
	return eventActive;
	
}





public void launchMeeting() {
	system.out.print("launch meeting");
}

public void toggleEventActive() {
	system.out.print("toggle event active");
}