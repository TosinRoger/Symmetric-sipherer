# Symmetric sipherer

This is a college project, made in 2016.
  
The aim of this project is the study of symmetric cryptography. Implemented in Java, this project makes it possible to test the Cesar and Vernam algorithm. It also makes it possible to do a simple analysis of a text, encrypted or not.
  
Another objective of this project is to make the code available for consultation, as it is a code made in a school environment, it is not properly standardized. Whether with English text, correctly separated the layers of the MVC project, there is also a lot of code smell.


## How to run
In 2021, the project was refactored to run on Java 8.

A priori, just run the project in a Java IDE, and place the cipher and key files in the project's root folder.

Another way to run the project is to run the file `Symmetric sipherer.jar` on the terminal 
``java -jarSymmetric\ sipherer.jar``. Remember put files `chave.txt` and `texto-aberto.txt` in the same folder of `.jar`

## How to generate the key?
- The character map [AZ][az][0-9] is used and checks the position of the message letter and the position of the key letter, if the sum of these two positions is smaller than the size of the character map , uses the sum to generate the new character. If the sum is larger, the difference between the sum and the size of the map is used, and starts counting from the beginning of the list from that difference.
for example:
If the character is at position 3 and the key is at 4 and the list has length 10, then the new character will be at position 7;
If the character is at position 8 and the key is at 4 and the list has length 10, then the new character will be at position 2;
  
- Is Vernam's algorithm vulnerable to frequency analysis?
No, or practically not. Vernam's encryption is only more vulnerable when the key is very small and with many of the same characters.
