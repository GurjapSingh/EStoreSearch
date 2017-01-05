****************************************************
Gurjap Singh		0880417
CIS2430		A3
November 30th 2016
****************************************************


************
general problem
************

the general problem that i am trying to solve is to make a search program that will allow the user to add books or electronic products with various attributes like name, price, product id and other special attributes like author and publisher or the maker for electronics.

the user can search through the two lists using either all three attributes or certain ones only
they can enter a range of years: XXXX or -XXXX or XXXX- or XXXX-XXXX as well as a variety of search terms.

the different part in this assignment is that the user is able to search with keywords using a hash map. as opposed to iterating through each element of the list.

- this program must be run with the argument input.txt
- i did my exception throwing in functions that check if values that are going to the constructor are of proper format and specifications. if the values are good then they are automatically sent to the constructor to be added

******************************
assumptions and limitations
******************************


the assumptions and limitations are that you can only enter a book or electronic item.
the user can only enter attributes that i have created they can’t put in their own.

in order for the file parsing to work correctly, there have to be quotes around the item. and the spacing has to be consistent.
the file parsing does not have error checking as it cannot tell the difference if a character is a ‘h’ or if it is any other character
- when you perform a search, the fields are not cleared on purpose. this is so that the user can do searches more efficiently. this is the opposite of adding products, where after a product has been successfully added, the fields are cleared. if the product was not successfully added, the fields are not cleared so the user can make some changes to the fields and try to add again.


*************
test plan
*************

I tested this program the following ways

- trying to make it crash when it asks for integers and you put in characters or strings
- clicking add with incomplete required values like ID and Name
- testing input of various lengths with the product id and years
- inputting a product id i know already exists in the same list and the other list to make sure it catches that it exists already.
- using try-catch to make sure all errors are taken care of
- searching the array list with one attribute at a time and then combinations of them to make sure it prints out correct lists.
- when asked for values that had to be of specific format, like the year being a digit of 4 numbers with them being between 1000 and 9999, i intentionally put in decimals to make the program crash and put in exception catching accordingly.
- i threw exceptions from the functions goodBookValues and goodElectronicValues
Test Cases
- entering letters when it asks for numbers
- using the wrong filename as an argument and seeing if it glitches


- in terms of the hashMap, tested by adding several of the same keyword names with varying upperCase/lowerCase letters
- set up print statements to the terminal and to the guy display that displayed what what was being added to the hash map and then getting that key to see if it printed out the correct element


*****************
Improvements
*****************

I would further simplify code that is not needed. it can be made cleaner by deleting braces that aren’t required for if/else statements.
when testing one condition and returning the boolean value i would just put that in a return statement
conduct testing with extremely large lists. thousands of products





