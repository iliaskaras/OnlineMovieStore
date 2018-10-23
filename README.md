# OnlineMovieStore

====================== Database Info =========================================

(*) Used MySql Database and worked on MySql WorkBench 8. CE. (*) 

(*) In src/main/resources you can find the sql queries for :

    1) init the user : database_user_init.sql
    2) create schema : online_movie_store_database.sql
    3) populate the tables with some rows : populate_tables.sql

  --You need to run the above queries for the program to run as intented.
  Please keep in mind that only the necessary rows are created at step 3,
  for exaple no customers are created and for you to run the program you need 
  to register a new one (link at home page).
  
  Details on running the queries.
  
  1) Connect to MySql WorkBench as root -> 
     -> File -> New Query Tab -> open script file in this editor (or copy paste)
      the database_user_init.sql -> restart MySql workbench -> on MySql Connections
     (at opening) click the (+) and on "Setup New Connection" in parameters set
     the Username = user and Password = user (based on database_user_init.sql)
     
     Now that the user is created log in and go to step 2.
  2) Now that you are connected, load the online_movie_store_database.sql, run it
     to create the database schema.
  
  3) Finally run the populate_tables.sql to fill some important rows in database.
  
  Now you are ready to run the program.

========================== Program Info =========================================
 
 Written in Java, used Eclipse and Maven. A Spring Restful MVC program that answers the basic
 needs of an Online Movie Store, where users can create account, see the available movies,
 filter them based on genre, and rent them (create order) and being able to watch them online 
 only after paying for the days they predefined. 
 
 There are different economic packages, based on what movie the customer is trying to rent, any 
 many more.

 For testing i used Mockito for our Services. Havent put tests for all the program as a whole but
 i created for 3 RestControllers that indicates the way.
