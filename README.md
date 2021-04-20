# ODS Full Stack Coding Assignment

## Assignment

Create a web application that allows a user to search for flights and display the results in a tabular view.

## Features

1. Allow the user to enter a station (destination or origin) to search flights. Display the results in a table.

* http://localhost:8080/getFlights

2. Provide an auto-suggest feature for station.

* http://localhost:8080/getAutofill?site=origin
* http://localhost:8080/getAutofill?site=destination

3. Provide two RESTful endpoints supporting the functionality listed in steps 1 and 2.

## Implementation Technology

Springboot, Java, Jquery, Javascript, HTML

## How to Run
* Clone this repository 'git clone https://github.com/Johns618/flightServiceApp.git'
* Navigate 'cd' to this repository location via command line
* Execute the following command: 'mvn spring-boot:run'
* Once the application has been started access the UI using this link: http://localhost:8080/index.html

## Application Setup
*  Click 'Create Database Button' (See Notes) - Will take about one minute to load. Click 'Wait' if time out modal appears.
*  After Temporary Database created the  'Create Database Button' will vanish, the input fields will unlock and the flight application is now ready for use!

## Flight Application Navigation
* Query flight table by typing in the input fields. 

## Notes
To demonstate the efficiency of the table search options when paired with a database. I used H2 database engine to temporarily house the provided data in flights.csv. Clicking the 'Create Database' button will automatically generate a temporary database and store flights.csv to be searched in the table below. The real world use of this application will connect to an existing database thus not needing to create its own. This would eliminate the minute wait time. 

Please feel free to reach out for any questions. Thank you!


