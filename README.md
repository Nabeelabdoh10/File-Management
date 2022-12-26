# Introduction
The technical problem I tackled in my project is to find a way to find
and manage my old files and make enough space. Designing a system
that is fast, cheap, reliable and efficient is at the heart of my project.
The idea came about noticing the inefficiency of the current ways of
storing files (the person is looking for large storage spaces and also
looking for the old version of his file and he can't find it and he has to
rewrite it from scratch and the problem is he is still trying to merge
two files together...).

As the system will initially be implemented at the university, there
are a number of performance specifications that must be met to ensure
the system works properly and efficiently. More importantly, the
development of the intelligent file management system interface must
send and receive appropriate information. The interface, in turn, must
be able to multitask and run multiple threads at the same time to add a
new file or add a new copy of the file. Then the next interface (writing
to the same file) must be precise enough to specify who will read and
who will write so that the file is not written at the same time. Also, the
other interface should work properly and be convenient when adding
or modifying a file. The process should be easy and accurate. In order
for each file to be reliably added, these key performance
specifications must be met.

# File Management 

The main features for this application are (Code explanation*): 
1. File repository which contains the following functionalities:
- Import files: 
The importFiles class is a utility class that is used to retrieve a file from a database and read its contents. It uses the singleton pattern to get a connection to the database and to execute a SELECT query to retrieve the file.

The importFile method takes a pathFileName parameter, which is the name of the file to be retrieved. It encrypts the file name using the FileEncryptor class and uses it to execute a SELECT query to retrieve the file from the database. If the file is found, it reads the file data from the database and displays the file name and contents on the console. If the file is not found, it prints an error message.

Finally, the importFile method closes the ResultSet, Statement, and Connection objects to release any resources that were used during the operation. This is important to ensure that resources are properly released and avoid resource leaks.


- Export files:
The exportFile method takes four parameters:
fileName: The name of the file to be stored.
fileType: The type of the file, such as "txt" or "pdf".
fileSize: The size of the file in bytes.
version: The version of the file.
The method first encrypts the file name using the FileEncryptor class and uses it to create a new PreparedStatement object with an INSERT query. It then sets the values of the parameters in the query, including the encrypted file name, the file data, the file type, the file size, and the version.

Finally, it executes the query to insert the file into the database and releases any resources that were used during the operation by closing the PreparedStatement and Connection objects. This is important to ensure that resources are properly released and avoid resource leaks.



- Delete files:
is a utility class that is used to delete a file from a database. It uses the singleton pattern to get a connection to the database and to execute a DELETE query to delete the file from the database.

The deleteFile method takes two parameters:

fileName: The name of the file to be deleted.
version: The version of the file to be deleted.
The method first encrypts the file name using the FileEncryptor class and uses it to create a new Prepared Statement object with a DELETE query. It then sets the values of the parameters in the query, including the encrypted file name and the version of the file to be deleted.

Finally, it executes the query to delete the file from the database and releases any resources that were used during the operation by closing the Prepared Statement object. This is important to ensure that resources are properly released and avoid resource leaks.

2. File classification based on file category :

(type, size and custom)

This code defines a Java class called Classification which contains a method extension that takes a String argument query. The extension method establishes a connection to a database using the connection method in the FileToDatabase class and then creates a Statement object.

The extension method then executes a SELECT statement on the database using the executeQuery method on the Statement object. The SELECT statement retrieves the name and version columns from the files table where the type column matches the value of the query argument.

The extension method processes the results of the SELECT statement by iterating over the ResultSet returned by executeQuery and printing the name and version columns for each row to the console. The name column is decrypted using the decrypt method in the FileEncryptor class before being printed.

The main method of the Classification class calls the extension method with the argument "type = 'cpp'", which will retrieve and print the name and version columns for all rows in the files table where the type column is equal to "cpp".


3. Version control for the files:




 The  method does the following:
Creates a HashMap object called map that maps strings (the keys) to integers (the values).
Creates a Scanner object called scanner that reads input from the console.
Adds some data to the map by calling the put method on map and passing it string-integer pairs as arguments.
Declares an Integer variable called version and initializes it to 0.
Prompts the user to enter a file name and reads the user's input using the nextLine method of scanner.
Checks if the map contains a key that matches the file name entered by the user. If the map does contain such a key, it prints a message asking the user to enter a new name or a new version. If the user enters 1, it prompts the user to enter a new name and adds it to the map with a value of 0. If the user enters 2, it retrieves the current value of the key from the map, increments it by 1, and updates the value in the map. If the map does not contain a key that matches the file name entered by the user, it adds the file name to the map with a value of 0.
Finally, it prints the contents of the map.
This code simulates a system that stores file names and versions. When the user enters a file name, the system checks if a file with that name already exists. If it does, the system prompts the user to enter a new name or create a new version of the file. If the file does not exist, the system adds the file to the map with a version of 0.

## Concrete architecture 

![C4 Level1](https://github.com/Nabeelabdoh10/File-Management/blob/main/C4%20Level1.jpg)
![C4 Level2](https://github.com/Nabeelabdoh10/File-Management/blob/main/C4%20Level2.png)
![C4 Level3](https://github.com/Nabeelabdoh10/File-Management/blob/main/C4%20Level3.png)
![C4 Level3A.png](https://github.com/Nabeelabdoh10/File-Management/blob/main/C4%20Level3A.png)


## Functional features

Feature 1: File repository.

In this feature, the user shall be able to implement import, export or delete file from the
system. The user shall be able to export/delete files based on their file name and its
category. This feature also includes that the user shall be able to see all files available
in the system.

Feature 2: File classification

The user shall be able to classify the files based on their type, size or a custom category
created by the user.
This feature allows users later to export/delete based on their classification.

Feature 3: Version control for the files

In this feature, the user shall be able to import multi versions for each file; each version
containing information about the data file that was imported to the system. The file is
represented by the last file added by the user, another version is available upon user
request.

This feature is enabled by default; For instance, if the imported file properties (name
and type) are the same of an existing file in the system, then the system stores the file
as the latest version of this file and the current latest version is kept. However, the user
shall be able to disable it while he/she is adding a new file, in this case the system shall
overwrite the existing file with new file.

This feature also supports rollback feature, which is a feature that allows users to
replace current version with a previous version.
## Non-functional requirements

1. All users should authenticate themself.

2. The user should be able to access the system based on their permissions:
a. Admin: Full permission

b. Staff: Import/Export but can’t delete or overwrite files.

c. Reader: Read only.

3. The system should be secure, so all files shall be encrypted in the system.

4. The system should be available and accepts all requests coming to it within less than 1 second for each request for any file regardless of its size.

5. The system should be scalable; for instance, in future, the system should serve millions of users.

6. The application should solve the conflict in case two users try to modify the same file in parallel.

7. For debugging/accountability, the application should generate detailed logs for every action done in the system.

8. 90% of the users are reader users.

## The main objective in the project 
Is to apply all principles, methodologies, rules
and best practices in software development.
- Class digram(
![Classdigram.jpg](https://github.com/Nabeelabdoh10/File-Management/blob/main/class%20digram.jpg )
- use case digram( )

- Functional requirements:
    1. File reposisory (  )
    2. File classification (  )
    3. Version control for the files (  )
    4. Encryption feature (  )
    ...
- Non functional requirements:
  1. User authentication and authorization (  )
  2. System scalability (  )
  3. System availability (  )
  4. Parallel request (  )
  ...
  
  - Clean code and Code conversion ( )
  - Class quality, OOP and SOLID principles ( )
  - Design patterns ()
  - Logging ( )
  - Exception handling ( )
  - Concrete architecture ( )

