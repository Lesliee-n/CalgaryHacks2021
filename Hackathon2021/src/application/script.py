from icalendar import Calendar

g = open('example.ics','rb')
gcal = Calendar.from_ical(g.read())
# f = open("output.txt", "w")
for component in gcal.walk():
    event = ""
    if component.name == "VEVENT":
        
        event +=(str(component.get('summary').to_ical())[2:-1])+"#"
        
        
        event+=(str(component.get('dtstart').to_ical())[2:-1])+"#"
        
        
        event+=(str(component.get('dtend').to_ical())[2:-1])+"#"
        
        y=str(component.get('rrule').to_ical())[2:-1]
        x = y.split(";")
        for i in range(len(x)):
	        x[i] = x[i].split("=")[1]
        event+=(x[0])+"#"
        event+=(x[1])+"#"
        event+=(x[2])+"#"
        
        
        event+=(str(component.get('uid').to_ical())[14:-31])
    if event:
        print(event)
    # print("-------------------")
    # print("Hello\n")
# f.close()
g.close()