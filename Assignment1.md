# Assignment No. 1
I learnt Golang to do this assignment. I have done the given task using the crypto/sha256 package. I simply stored the target value in the string namely "target" and after taking the input from the user i ran a loop starting from 1 and kept on appending each number to the end of the string created the SHA256 hash of that string and compared it with the target string using the Compare function of the string class. The exit condition for the loop was when it will recieve a hash smaller than the target. In this process the first number we got had to be the smallest. The time package of golang is used to record the time.

 ## Some Input and Outputs
 1)
Enter the text:-  
Programming Club IIT Kanpur  
000009853731c622241599e44c6a4b2607f37dd29eaa15d8b92a33cdce06184e  
Nonce : 342207  
Time : 221.8972ms  
2)  
Enter the text:-  
iitk  
00000bde24704b17d1531816354d01810b1e6f01b1ea38e0c53d5fc5f3bfd70a  
Nonce : 1217060  
Time : 700.9423ms  
3)  
Enter the text:-  
Smoking is injurious to health  
00000023dba778886d3289e114e27ba638dbb190340e1bb77e23ad6d4a7209d4  
Nonce : 829232  
Time : 527.8385ms  
