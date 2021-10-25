# SQL vs NoSql
For the past 4.5 years of my career experience I have only worked with SQL databases and even though I have no 
experience in implementations that rely on NoSql database I know of them and I know the benefits and reasons why 
they should be used. 
This challenge was an obvious example of a NoSql database because of: 
1. Inconsistent data in the json file
2. Even though there seem to be relations in the objects they are not tightly coupled to require a transactional database
3. The read APIs are much faster for this example by using the NoSql database

I debated should I stay in my line and do a SQL based implementation (h2 embedded) but stating that a NoSql solution would be better
But I decided that I should roll the dice and learn something new (at least the basics) after a POC for a SQL 
implementation that required far more work.  

# Technology stack
* The Application is a simple Spring Boot application that uses the spring-web, spring-mongo and spring security 
libraries for the implementation. 
* Lombok is used for removing redundant code.
* Jacoco is added as a plugin for checking the code coverage
* Spotless is used for code formatting 
* Docker is used for bootstrapping the application

# Additional thoughts
* As I said I contemplated creating a SQL based implementation besides the NoSQL implementation that could be switched
by using different profiles while bootstrapping. But the implementation required so much data manipulation and mapping
from DTO-s to Entities that it was overkill.
* There was no requirement for security but adding one was not a large chunk of my time and keeping apps secure should
be a constant thought. 
* The code coverage is a 100% for this small application. I have also wrote some integration tests for the repositories
and controllers. In the last couple of years I have come to love writing tests as it keeps my code on its toes so I tried 
as much as I could to write tests for this small app
* Prior to this assignment I had no knowledge about the implementation of NoSQL databases so I hope that it will suffice. 
One of the reasons for searching for a new job is to learn about NoSQL dbs

# Manual
* Application can be started by executing the `start-doodle.sh` script without any arguments.
* All endpoints should be called with a basic authorization header where the username is 'stark' and the password
'iamironman'. The endpoints are
* Find all by initiator name => http://localhost:8080/polls/initiator?initiator=${initiator} accepts String
* Find all by date after => http://localhost:8080/polls/initiated?initiated=${initiated} accepts Long
* Search by title => http://localhost:8080/polls/title?title=${title} accepts String
* The last endpoint sounded like a search filter so I included an abstraction that if a searchTerm is null it should be 
empty and return all polls

# Final thoughts
The assignment was light and to the point. The Docker and the NoSQL side were fun as those are technologies that 
I am not versed in using. Hopefully I can continue my development in those areas. 