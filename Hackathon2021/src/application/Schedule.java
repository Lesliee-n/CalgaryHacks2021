package application;

import java.util.ArrayList;
import java.util.List;

public class Schedule {
    private List<Event> events;
    public Schedule() {
        this.events = new ArrayList<Event>();
    }

    public Event[] getEvents() {
        Event[] courses = new Event[this.events.size()];
        for(int i = 0; i < this.events.size(); i++) {
            courses[i] = this.events.get(i);
        }
        return courses;
    }

    public boolean checkEventTime(int time) {
        for(int i = 0; i < this.events.size(); i++) {
            // depends on if time is an int or a string.
            if (this.events.get(i).getTime() == time) {
                return true;
            }
        }
        return false;
    }

    public int getMinTime() {
        int timeMin = Integer.MAX_VALUE;
        for (int i = 0; i < this.events.size(); i++) {
            if (this.events.get(i).getTime() < timeMin) {
                timeMin = this.events.get(i).getTime();
            }
        }
        return timeMin;
    }

    public int getMaxTime() {
        int timeMax = 0;
        for (int i = 0; i < this.events.size(); i++) {
            if (this.events.get(i).getTime() > timeMax) {
                timeMax = this.events.get(i).getTime();
            }
        }
        return timeMax;
    }


    public void deleteEvent(Event e) {
        this.events.remove(e);
    }

    public void addEvent(Event e) {
        this.events.add(e);
    }
}
