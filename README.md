# 1-Scheduling-Program
GUI for MySQL DB containing appointments for Scheduling


Introduction
The intended operating environment for the scheduling application is Windows, Linux, or MAC OS running Java 11.
1.	Unzip the Scheduling_Application-10-23 folder to the location of your choice.
2.	Open in IntelliJ Idea 2021.1.3 (later versions may not work).
3.	Open MySQL and establish a local instance of MySQL, noting the connection name, username, password, Hostname, and port of your choosing. Click ok.
4.	From the main menu click on the connection you just created.
5.	Click Server on the main toolbar.
6.	Select Data Import, click the “…” next to Import Dump Project Folder and navigate to the Dump20211021 folder within the Scheduling Application folder you created earlier and click ok.
7.	Make sure that the checkbox under Import for” client_schedule” is selected.
8.	Select Start Import.
9.	Return to IntelliJ and navigate to the JDBCHelper class in the util package. 
10.	Confirm that the location, databaseName, userName, and password match what you entered into MySQL. 
11.	You should now be able to be able to connect to the graphical user interface by running Main.

