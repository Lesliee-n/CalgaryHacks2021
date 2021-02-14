package application;

import java.time.LocalDate;
import java.util.Arrays;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;

//Event object
public class Event {
	private int time;
	private String day[];
	private String name;
	private int duration;
	private String url;
	private String password;
	private boolean eventActive = true;

//set variables in object
	public void setTime(int newTime) {
		this.time = newTime;

	}

	public void setDay(String newDay[]) {

		for (int i : newDay) {
			day[i] = newDay[i];
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
		system.out.print("toggle event active");
	}

}
