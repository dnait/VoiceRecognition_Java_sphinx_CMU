# Sphnix_TTS_JAVA__Calculator
Basic Calculator with Voice recognition and TTS functions
You can check two videos first if you think it is too wordy.
This is the first JAVA project I did in an Interface Design class in Spring 2014. Stduents are required to use any language to design a calculator with functions such as: 
1. operators and digits +,-, *, ^, (, ), 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 (no /)
2. can do backspace, clear and quit
3. when input -( 5 + 7 ) * 3 ^ 1  should output in Tex format $$ -( 5 + 7 ) * 3 ^{ 1 } = -36 $$
4. Can speak out the result when calculator gets the result.
5. Can voice recognize the words/Digits and calculate the correct result.

This calculator is mostly like regular ones with parentheses, backspace, clear, operators and numbers. However, this can calculate complex expression such as -1+(2-3)*6^(-1+2). The calculator provides two different output modes-Speech and Tex. The Speech mode can output the typed expression, the reverse polish notation (RPN) format of the expression and the final result with voice. The RPN format is the best way to represent how the computer calculates Infix Notation and expression structure. The voice will repeat the whole expression, RNP format and the result. The Tex mode can output the Tex format of the expression and the result.The power enclosed in curly brackets, and the whole output has been covered by the $$

JAVA TTS is not hard. Protocols can be found everywhere.

Challenging:
At the beginning, for some reason, when clicking the Voice Mode button, the user interface will freeze and wait the Sphinx to finish the whole speech recognition till I said "equal" which means stop the recognition and come out the result.(The period is quite long, normally need 5-6 mins, because Sphinx cannot always recognize the digits and operator precisely even though I abbridged the dict into a mini that only contained the operators and digits I need.) That time I thought extra thread might help solve the problem and it turned out it's. Then how to transfer the words Sphinx recognized into the textfield in the calculator. I had to write the Sphinx result into a voice text file and share it with main program and keep the file updated in real time. Way too Complex...

From Github, someone shared to initialize the recognizer and microphone everytime so don't have to write recognized word into files. 
So if you have any questions and better ideas, please share with me. Thanks.

Rao

