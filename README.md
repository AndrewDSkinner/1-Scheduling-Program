# 1-Scheduling-Program
GUI for MySQL DB containing appointments for Scheduling


Introduction
The intended operating environment for the scheduling application is Windows, Linux, or MAC OS running Java 11.
1.	Unzip the Scheduling_Application-10-23 folder to the location of your choice.
2.	Open in IntelliJ Idea 2021.1.3 (later versions may not work).
3.	Click Projects > Open and navigate to the Scheduling_Application-10-23 folder and click ok to open the project.
4.	Open MySQL Database and establish a local instance of MySQL, noting the connection name, username, password, Hostname, and port of your choosing. Click ok.
5.	From the main menu click on the connection you just created.
6.	Click Server on the main toolbar.
7.	Select Data Import, click the “…” next to Import Dump Project Folder and navigate to the Dump20211021 folder within the Scheduling Application folder you created earlier and click ok.
8.	Make sure that the checkbox under Import for” client_schedule” is selected.
9.	Select Start Import.
10.	Return to IntelliJ and navigate to the JDBCHelper class in the util package. 
11.	Confirm that the location, databaseName, userName, and password match what you entered into MySQL. 
12.	You should now be able to be able to connect to the graphical user interface.

