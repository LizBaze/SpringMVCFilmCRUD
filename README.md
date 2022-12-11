## MVC Film Site


## Description
This is a Dynamic web application project. You can enter a film ID and see the films details, 
if the ID is not valid, you will be sent to a web page stating so. You can also add a new film
by entering information into the web from fields, if adding the film is not successful you will be
directed to another page stating such. As you explore the different options you will notice that 
you have the ability to delete and update films that are in the database, if any of the modifications
do not work you will be directed to a page communicating just that. Keyword searches for films can be performed,
all actions to retrieve a pre-existing films will produce the cast of actors and category for that film. 

### Technologies Used
- Git
- Data Access Object
- JSP
- JDBC
- Controller
- Gradle
- xml
- mySQL
- Tomcat server v8.5
- SpringToolsSuite4
- terminal
- drivers

### Lessons Learned
1) Be very careful when to make sure everything is saved, and communicate to your partner(s) when
interacting with github.

2) Two heads are better than one

3) Taking breaks helps, so you can come back to your project with fresh eyes and a different train of thought

4) The repetition in this project was challenging despite each story starting off similar but needing different things to complete

5) Good communication and cooperation is refreshing

6) Always make sure you understand your database. We ran into several issues not conforming to the film table's expectations. Title and language_id are required fields, while Rating and special_features only allow certain inputs. 

7) Check that the changes are successful, commit them, *then* use them elsewhere in the code. We spent some time troubleshooting an issue where our update page only showed the udpated film info after it was submitted twice. The cause was that we were assigning a film object that was being displayed to its old values using findFilmByID(), and then committing the changes. Moving the commit statement to be before the film lookup fixed the issue.

### Entity Relationship Diagram

![Alt text](https://raw.githubusercontent.com/SkillDistillery/SD36/main/sql1/images/ERDiagram.png?token=GHSAT0AAAAAAB2WPK2RKT5YZB4ZZTB76TTAY4WKXIA)
 