from icalendar import Calendar

g = open('src//example.ics','rb')
gcal = Calendar.from_ical(g.read())
# f = open("output.txt", "w")
for component in gcal.walk():
    event = ""
    days = {"MO":"MONDAY", "TU":"TUESDAY", "WE": "WEDNESDAY", "TH":"THURSDAY", "FR": "FRIDAY"}

    if component.name == "VEVENT":
        
        event +=(str(component.get('summary').to_ical())[2:-1])+"#"

        # #hour
        # event+=(str(component.get('dtstart').to_ical())[11:-5])+"#"
        # print(str(component.get('dtstart').to_ical())[11:-5])
        # #min
        # event+=(str(component.get('dtstart').to_ical())[13:-3])+"#"
        
        # startTime=int(str(component.get('dtstart').to_ical()[11:-5])) + int(str(component.get('dtstart').to_ical()[13:-3]))
        # endTime=int(str(component.get('dtend').to_ical()[11:-5])) + int(str(component.get('dtend').to_ical()[13:-3]))

        # duration = endTime-startTime

        # event+=(str(duration))+"#"
        event+=(str(component.get('dtstart').dt.hour))+"#"
        event+=(str(component.get('dtstart').dt.minute))+"#"
        
        event+=(str((component.get('dtend').dt-component.get('dtstart').dt).total_seconds()))+"#"
        
        y=str(component.get('rrule').to_ical())[2:-1]
        x = y.split(";")
        for i in range(len(x)):
	        x[i] = x[i].split("=")[1]
        event+=(x[0])+"#"

        event+=(x[1])+"#"
        week = x[2].split(",")
        for i in range(len(week)):
            event+=(days[week[i]])+","
        event =event[:-1]
        event+="#"
        
        
        event+=(str(component.get('uid').to_ical())[14:-31])
    if event:
        print(event)
    # print("-------------------")
    # print("Hello\n")
# f.close()
g.close()