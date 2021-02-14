package application;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;

//Event object
public class Event {
	private Calendar time;
	private String day[];
	private String name;
	private int duration;
	private String url;
	private String password;
	private boolean eventActive = true;

//set variables in object
	public void setTime(int hr, int min) {
		this.time = new GregorianCalendar();
		time.set(Calendar.HOUR_OF_DAY, hr);
		time.set(Calendar.MINUTE, min);

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
	public Calendar getTime() {
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
