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

   

  


    public void deleteEvent(Event e) {
        this.events.remove(e);
    }

    public void addEvent(Event e) {
        this.events.add(e);
    }
}
