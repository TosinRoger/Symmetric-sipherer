# Symmetric sipherer

This is a college project, made in 2016.
  
The aim of this project is the study of symmetric cryptography. Implemented in Java, this project makes it possible to test the Cesar and Vernam algorithm. It also makes it possible to do a simple analysis of a text, encrypted or not.
  
Another objective of this project is to make the code available for consultation, as it is a code made in a school environment, it is not properly standardized. Whether with English text, correctly separated the layers of the MVC project, there is also a lot of code smell.

## Table of contents

- [Symmetric sipherer](#Symmetric-sipherer) <br>
- [Table of contents](#Table-of-contents)
- [Usage](#Usage)
  - [Caesar cipher](#Caesar-cipher)
  - [Vernam](#Vernam-Cipher)
- [Some explanations](#Some-explanations)


## Usage
In 2021, the project was refactored to run on Java 8.

A priori, just run the project in a Java IDE and place the cipher and key files in the project's root folder.

Another way to run the project is to run the file `Symmetric sipherer.jar` on the terminal 
``java -jarSymmetric\ sipherer.jar``. Remember put files `chave.txt` and `texto-aberto.txt` in the same folder of `.jar`

### Caesar cipher
In cryptography, a Caesar cipher, is one of the simplest and most widely known encryption techniques. It is a type of substitution cipher in which each letter in the plaintext is replaced by a letter some fixed number of positions down the alphabet. For example, with a left shift of 3, D would be replaced by A, E would become B, and so on. The method is named after Julius Caesar, who used it in his private correspondence.

In the project, run the `jar` enter the desired algorithm in the following pattern:

```cesar -(c or d) -k (offset number) < \"name of the file to be encrypted.txt\" > \"name of the encrypted file.txt\" ``` 

- (c or d)
  - c -> cipher
  - d -> decoder
- k
  - how many letters will be shifted to the right, with the alpha used being [A-Z][a-z][0-9]
- < 
  - Source file name with the text to be encrypted or decrypted
- \> 
  - Name of the destination file with the text that was encrypted or decrypted


``` 
cesar -c -k 1 < texto-aberto.txt > texto-cifrado.txt 
```

#### Example

Create the `chave.txt` file with the following `text This is the key`
Create the `texto-aberto` file with the following 
```
Lorem Ipsum is simply dummy text of the printing and typesetting industry
```
Run `java -jar Symmetric\ sipherer.jar` 
run `cesar -c -k 1 < texto-aberto.txt > texto-cifrado.txt`

![cesar_cipher_shift_1](https://github.com/TosinRoger/Symmetric-sipherer/blob/main/screenshot/cesar_cipher_shift_1.png)

The file with the result will be created, with the name `texto-cifrado.txt`
The result of cipher will be 
```
Mpsfn Jqtvn jt tjnqmz evnnz ufyu pg uif qsjoujoh boe uzqftfuujoh joevtusz
```
All text has been shifted one letter to the right in the alphabet. L -> M, o -> p, r -> s, e -> f, m -> m, etc.

### Vernam Cipher

The Vernam Cipher is based on the principle that each plaintext character from a message is 'mixed' with one character from a key stream. If a truly random key stream is used, the result will be a truly 'random' ciphertext which bears no relation to the original plaintext.

In the project, run the `jar` enter the desired algorithm in the following pattern:

```vernam -(c or d) < \"name of the file to be encrypted.txt\" > \"name of the encrypted file.txt\" ```

- (c or d)
  - c -> cipher
  - d -> decoder
- <
  - Source file name with the text to be encrypted or decrypted
- \>
  - Name of the destination file with the text that was encrypted or decrypted

```
vernam -c chave.txt < texto-aberto.txt > texto-cifrado.txt
```


#### Example 

Create the `chave.txt` file with the following `text This is the key`
Create the `texto-aberto` file with the following
```
Lorem Ipsum is simply dummy text of the printing and typesetting industry
```
Run `java -jar Symmetric\ sipherer.jar`
run `vernam -c chave.txt < texto-aberto.txt > texto-cifrado.txt`

![vernam_cipher](https://github.com/TosinRoger/Symmetric-sipherer/blob/main/screenshot/vernam_cipher.png)

The file with the result will be created, with the name `texto-cifrado.txt`
The result of cipher will be
```
eLPMm 0pbRG IM BFKXlW ddJGy NSGQ Wf bhN JrIHh1KE aLL cVJeS8hCFLO GVddPNrY
```

// ToDo add some conclusion


## Some explanations?
- The character map [AZ][az][0-9] is used and checks the position of the message letter and the position of the key letter, if the sum of these two positions is smaller than the size of the character map , uses the sum to generate the new character. If the sum is larger, the difference between the sum and the size of the map is used, and starts counting from the beginning of the list from that difference.
for example:
If the character is at position 3 and the key is at 4 and the list has length 10, then the new character will be at position 7;
If the character is at position 8 and the key is at 4 and the list has length 10, then the new character will be at position 2;
  
- Is Vernam's algorithm vulnerable to frequency analysis?
No, or practically not. Vernam's encryption is only more vulnerable when the key is very small and with many of the same characters.
