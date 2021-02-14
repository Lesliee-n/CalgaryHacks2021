package application;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;

//Event object
public class Event {
	

	private Date time;
	private String day[];
	private String name;
	private int duration;
	private String url;
	private String password;
	private boolean eventActive = true;

	
	//constructor with password
	public Event(String name, String day[], int hr, int min, int duration, String url, String password) {
		setTime(hr,min);
		setName(name);
		setDay(day);
		setDuration(duration);
		setUrl(url);
		setPassword(password);
	}
	

	//constructor without password
	public Event(String name, String day[], int hr, int min, int duration, String url) {
		setTime(hr,min);
		setName(name);
		setDay(day);
		setDuration(duration);
		setUrl(url);
	}
	
	
	
	
//set variables in object
	public void setTime(int hr, int min) {
		this.time = new Date();
		time.setHours(hr);
		time.setMinutes(min);

	}

	public void setDay(String newDay[]) {
		this.day = newDay; 

	}

	public void setName(String newName) {
		this.name = newName;

	}

	public void setDuration(int newDuration) {
		this.duration = newDuration;

	}

	public void setUrl(String newUrl) {
		this.url = newUrl;

	}

	public void setPassword(String newPassword) {
		this.password = newPassword;

	}

//get variables in object
	public Date getTime() {
		return time;

	}

	public String[] getDay() {
		return day;

	}

	public String getName() {
		return name;

	}

	public int getDuration() {
		return duration;

	}

	public String getUrl() {
		return url;

	}

	public String getPassword() {
		return password;

	}

	public boolean getEventActive() {
		return eventActive;

	}

//functions
	public void launchMeeting() throws IOException, URISyntaxException {
		//put password to meeting to clipboard
		StringSelection stringSelection = new StringSelection(getPassword());
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);

		//open zoom link in browser
		Desktop d = Desktop.getDesktop();
		d.browse(new URI(getUrl()));
	}

	public void toggleEventActive() {
		//cancel notification of event
		this.eventActive = false;
		System.out.print("toggle event active");
	}

}
