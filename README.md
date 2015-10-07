# TimetableConverter
**Converts Scientia 'SWSCUST' HTML List timetables to iCal .ics format.**

*A small project scraped together during a lecture as an excuse to brush up on Java basics. Allows students to convert their Scientia timetable to iCal format for use in Google Calendar or similar modern applications.*

##Usage

Under "MyTimetable", select the Semester(s) of choice and select "List" as the report type.

Save the HTML of the resulting page local to the converter, as `input.html` in this instance

Execute the program as `java Main input.html output.ics` where `input.html` is the filename for the input HTML file and `output.ics` is the name of the destination iCal file.

The iCal/.ics file can then be imported into Google Calendar, etc.

##Disclaimer

This code is provided 'as is' and comes with no warranty or support. It has not been tested and is only known to work on timetables issued by King's College London using Scientia 2.0.44. Timetable alterations made online will not be replicated into your ICS file unless the conversion is ran again with up to date input data.
