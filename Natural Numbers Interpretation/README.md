NaturalNumbersInterpretation

When interacting with an automated dialogue system, the caller is often required to provide his phone number. Phone numbers may be uttered in many ways with different digit groupings (e.g. 2106930664 may be uttered as "210 69 30 6 6 4" or "210 69 664" etc). Furthermore, speech input may cause ambiguities. For example, if the caller says "twentyfive" this could be transcribed as "25" or "20 5".

This application deals with some of these issues.

The application accepts as input a sequence of numbers separated by spacebars. It check if the input is only digit, and if it is, it removes the whitespaces.
e.g. 200 3 25 -> 200325
     30 3 a03 -> bad input
     
Moreover it checks if the given number is a valid Greek phone number. Valid Greek numbers either have 10 digits and they start with '2' or '69', or they have 14 digits and they start with '00302' or '003069'.
e.g. 00302106897562 [phone number: VALID]
     0030695684520  [phone number: INVALID]

Finally, the application checks for any possible ambiguity in number spelling. The interpretation of 725 could be '700 20 5' or '720 5' or '700 25', and returns all possible combinations with a validation comment.
e.g. '2 10 26 60 5 70 8'
    21026605708 [phone number: INVALID]
    2102665708  [phone number: VALID]
    2102660578  [phone number: VALID]
    .
    .
    .
    210266578   [phone number: INVALID]
