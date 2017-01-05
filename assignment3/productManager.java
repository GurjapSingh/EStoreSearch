/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author gurjapsingh
 */
public class productManager {
    
    private static ArrayList<Product> productList = new ArrayList<>(); // array list
    private static Product myProduct;
    
    
    private static HashMap<String, ArrayList<Integer>> map = new HashMap<>(); // our hashmap
    /**
     * check whether or not the formatting of the book to add are of proper format
     * @param bidField
     * @param bnameField
     * @param byearField
     * @param bpriceField
     * @param bauthorField
     * @param bpublisherField
     * @return
     * @throws Exception 
     */
    protected static Book goodBookValues(String bidField, String bnameField, String byearField, String bpriceField, String bauthorField, String bpublisherField) throws Exception{
        Book myBook;
        String [] ID,strPrice;
        String Name;
        int Year;
        double Price;
        
        try{
            ID = bidField.split("\\s+"); // split using spaces
            if (ID.length > 1 || ID[0].equals(""))      // id should only be one word
            {
                throw new Exception ("ERROR: The ID must be entered\n");    // if the id is empty, it must be conveyed to this user via this exception
            }
            if (ID[0].length() !=6) // id must be 6 digits
                throw new Exception ("ERROR: ID must be 6 digits\n");
            if (!(ID[0].matches("[0-9]+"))) // id must only be numbers
                throw new Exception ("ERROR: ID must only contain numbers");
            Name = bnameField;
            if (Name.equals("")) // name must not be blank
                throw new Exception ("ERROR: Name of product must be entered\n");
            if (byearField.equals("") || byearField.length() != 4)      // year field cannot be blank
            {
                throw new Exception ("ERROR: Year of product must be 4 numbers\n");
            } else {
                Year = Integer.parseInt(byearField);
                if (Year > 9999 || Year < 1000)
                {
                    throw new Exception ("Error: Year must be between 1000 and 9999 years");
                }
            }
                
            strPrice = bpriceField.split("\\s+");
            if (strPrice.length > 1 || strPrice[0].equals(""))
                Price = 0;
            else
                Price = Double.parseDouble(strPrice[0]);
             
            myBook = new Book(ID[0],Name,Year,Price,bauthorField,bpublisherField);
            ProductGUI.Display("Book fields are good\n");
            return myBook;
        } catch (Exception e){
            throw new Exception (e.getMessage());
        }
        
    }
    /**
     * similar to the function to check the book values. these values must be checked to make sure they are of the right format
     * @param eidField
     * @param enameField
     * @param eyearField
     * @param epriceField
     * @param emakerField
     * @return
     * @throws Exception 
     */
    protected static Electronics goodElectronicValues(String eidField, String enameField, String eyearField, String epriceField, String emakerField) throws Exception {
        Electronics myElec;
        String[] ID, strPrice;
        String Name;
        int Year;
        double Price;

        try {
            ID = eidField.split("\\s+");
            if (ID.length > 1 || ID[0].equals("")) {
                throw new Exception("ERROR: The ID must be entered\n");
            }
            if (ID[0].length() != 6) {
                throw new Exception("ERROR: ID must be 6 digits\n");
            }
            if (!(ID[0].matches("[0-9]+"))) {
                throw new Exception("ERROR: ID must only contain numbers");
            }
            Name = enameField;
            if (Name.equals("")) {
                throw new Exception("ERROR: Name of product must be entered\n");
            }
            if (eyearField.equals("") || eyearField.length() != 4) {
                throw new Exception("ERROR: Year of product must be entered\n");
            } else {
                Year = Integer.parseInt(eyearField);
                if (Year > 9999 || Year < 1000) {
                    throw new Exception("ERROR: Year must be between 1000 and 9999 years");
                }
            }

            strPrice = epriceField.split("\\s+");
            if (strPrice.length > 1 || strPrice[0].equals("")) {
                Price = 0;
            } else {
                Price = Double.parseDouble(strPrice[0]);
            }

            myElec = new Electronics(ID[0], Name, Year, Price, emakerField);
            ProductGUI.Display("elec fields are good\n");
            return myElec;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }
    /**
     * the ID must be checked of having proper format
     * @param IDCheck
     * @return
     * @throws Exception 
     */
    protected static String goodSearchID(String IDCheck) throws Exception
    {
        String[] ID; 
        try{
            ID = IDCheck.split("\\s+");
            if (ID.length > 1 ) {
                throw new Exception("ERROR: The ID must be 1 word only\n");
            }
            if (!(ID[0].equals("")) && ID[0].length() != 6) {
                throw new Exception("ERROR: ID entered but it is not 6 digits\n");
            }
            if (!(ID[0].matches("[0-9]+"))) {
                throw new Exception("ERROR: ID must only contain numbers");
            }
            return ID[0];
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    /**
     * make sure year format is good
     * @param YearCheck
     * @return
     * @throws Exception 
     */
    protected static String goodSearchYear(String YearCheck) throws Exception
    {
        String[] Year;
        try{
            Year = YearCheck.split("\\s+");
            if (Year.length > 1 /*|| ID[0].equals("")*/) {
                throw new Exception("ERROR: The Year must be 1 word only\n");
            }
            if (!(Year[0].equals("")) && Year[0].length() !=4){
                throw new Exception("ERROR: Year entered but it is not 4 digits");
            }
            if (!(Year[0].matches("[0-9]+"))) {
                throw new Exception("ERROR: Year must only contain numbers");
            }
            return Year[0];
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    /**
     * add the book to the 
     * @param toAdd 
     */
    protected static void addBook(Book toAdd)
    {
        if (toAdd == null) // if product is null, becuase the formatting wasnt correct, then dont go forward and print out that the book wasnt added
            ProductGUI.Display("Book not added\n");
        else{
            try{
                if (!(toAdd.isAvailable(toAdd.getID(), productList))) // if the product id alrady exists, dont go forard towards adding it
                {
                    ProductGUI.Display("Sorry. this product already exists\n");
                    return;
                }
                else
                    ProductGUI.Display("New book product\n");
                
                myProduct = new Book(toAdd.getID(), toAdd.getName(), toAdd.getYear(), toAdd.getPrice(), toAdd.getAuthor(), toAdd.getPublisher()); // make new product using this object
                productList.add(myProduct); // add product to arraylist
                
                addToMap(toAdd.getName()); // adding the product name to the hashmap
                System.out.println("book added to all");
                ProductGUI.fieldReset(); // reset the fields to being blank
            } catch (Exception e){
                ProductGUI.Display(e.getMessage()+"errorrorror\n");
            }
        }
    }
    /**
     * similar to the book product. this funtion adds the object to the arraylist as well as the hashmap
     * @param toAdd 
     */
    protected static void addElec(Electronics toAdd) {
        ProductGUI.Display("addElec function\n");
        if (toAdd == null) {
            ProductGUI.Display("Elec not added\n");
        } else {
            try {
                if (!(toAdd.isAvailable(toAdd.getID(), productList))) {
                    ProductGUI.Display("Sorry. this product already exists\n");
                    return;
                } else {
                    ProductGUI.Display("new electronic Product\n");
                }

                myProduct = new Electronics(toAdd.getID(), toAdd.getName(), toAdd.getYear(), toAdd.getPrice(), toAdd.getMaker());
                productList.add(myProduct);

                //itemName = itemName.toLowerCase();
                //System.out.println(itemName);
                addToMap(toAdd.getName()); // adding the product name to the hashmap
                System.out.println("elec added to all");
                ProductGUI.fieldReset();
            } catch (Exception e) {
                ProductGUI.Display(e.getMessage());
            }
        }
    }
    /**
     * add the product to the hashmap
     * @param Name 
     */
    public static void addToMap(String Name)
    {
        Name = Name.toLowerCase(); // convert name to lower case
        String[] splitItup = new String[Name.split("[ ]+").length]; // split the name according to spaces
        splitItup = Name.split("[ ]+");
        
        for(String words : splitItup)
        {
            if (map.get(words) == null) // if word doesnt exist in the map, put it there
            {
                map.putIfAbsent(words, new ArrayList<Integer>());
            }

            map.get(words).add(productList.size()-1); // add the index to the arraylist
        }
    }
    /**
     * search using only the id
     * @param Id 
     */
    protected static void searchById(String Id)
    {
        for (Product element : productList)
        {
            if (element.productIdMatch(Id)) {
                System.out.println("");
                ProductGUI.Display("\n");
                System.out.println(element);
                ProductGUI.Display(element.toString());

            }
        }
    }
    /**
     * search using the year
     * @param yearLow
     * @param yearHigh 
     */
    protected static void searchByYear(int yearLow, int yearHigh) {
        for (Product element : productList) {
            System.out.println("lowerYear: " + yearLow);
        System.out.println("upperYear: " + yearHigh);
            if (element.productYearMatch(yearLow, yearHigh)) // if product years match, the product will be printed
            {
                System.out.println("");
                ProductGUI.Display("\n");
                System.out.println(element);
                ProductGUI.Display(element.toString());
            }
        }
    }
    /**
     * search using keywords
     * @param keys 
     */
    protected static void searchByKeysHash(String keys) {
        //System.out.println("function");
        String[] splitWords = new String[keys.split("[ ]+").length];
        splitWords = keys.split("[ ]+");
        System.out.println(splitWords[0]);
        for (String keyWord : splitWords) {
            keyWord = keyWord.toLowerCase();
            if (map.get(keyWord) != null) {
                ArrayList<Integer> allInstancesofWord = (ArrayList<Integer>) map.get(keyWord);
                {
                    for (int i = 0; i < allInstancesofWord.size(); i++)
                    {
                        System.out.println(productList.get(allInstancesofWord.get(i)));
                        ProductGUI.Display(productList.get(allInstancesofWord.get(i)).toString() + "\n");
                    }
                }
            }
            if (map.get(keyWord) == null) {
                System.out.println("null");
            }
        }

    }
    /**
     * search using the id and year
     * @param Id
     * @param yearLow
     * @param yearHigh 
     */
    protected static void searchByIdYear(String Id, int yearLow, int yearHigh) {
        System.out.println("lowerYear: " + yearLow);
        System.out.println("upperYear: " + yearHigh);
        for (Product element : productList) {
            if (element.productIdMatch(Id) && element.productYearMatch(yearLow, yearHigh)) {
                System.out.println(" ");
                ProductGUI.Display("\n");
                System.out.println(element);
                ProductGUI.Display(element.toString() + "\n");
            }

        }
    }
    /**
     * search using the hash and id
     * @param keys
     * @param myId 
     */
    protected static void searchByKeysHashID(String keys, String myId) {
        String[] splitWords = new String[keys.split("[ ]+").length];
        splitWords = keys.split("[ ]+");
        System.out.println(splitWords[0]);
        for (String keyWord : splitWords) {
            keyWord = keyWord.toLowerCase();
            if (map.get(keyWord) != null) {
                ArrayList<Integer> allInstancesofWord = (ArrayList<Integer>) map.get(keyWord);
                {
                    for (int i = 0; i < allInstancesofWord.size(); i++) {

                        Product e = productList.get(allInstancesofWord.get(i));
                        if (e.productIdMatch(myId)) // if an element has the desired id and it has the correct key terms. it is printed.
                        {
                            System.out.println("I hope this Worksid");
                            System.out.println(productList.get(allInstancesofWord.get(i)));
                            ProductGUI.Display(e.toString() + "\n");
                        }
                    }
                }
            }
            if (map.get(keyWord) == null) {
                System.out.println("The products you are searching for were not found");
            }
        }
    }
    /**
     * search using the keys and year
     * @param keys
     * @param lowerYear
     * @param upperYear 
     */
    protected static void searchByKeysHashYear(String keys, int lowerYear, int upperYear) {
        System.out.println("searching in hasher using search terms and year");
        System.out.println("lowerYear: " + lowerYear);
        System.out.println("upperYear: " + upperYear);
        String[] splitWords = new String[keys.split("[ ]+").length];
        splitWords = keys.split("[ ]+");
        System.out.println(splitWords[0]);
        for (String keyWord : splitWords) {
            keyWord = keyWord.toLowerCase();
            if (map.get(keyWord) != null) {
                ArrayList<Integer> allInstancesofWord = (ArrayList<Integer>) map.get(keyWord);
                {
                    for (int i = 0; i < allInstancesofWord.size(); i++) {

                        Product e = productList.get(allInstancesofWord.get(i));
                        if (e.productYearMatch(lowerYear, upperYear)) // if an element is between range of the years. and it has the correct key terms. it is printed.
                        {
                            
                            System.out.println(productList.get(allInstancesofWord.get(i)));
                            ProductGUI.Display(e.toString() + "\n");
                        }
                        else
                            System.out.println("not in the year "+ keys + " "+lowerYear+ " "+ upperYear);
                    }
                }
            }
            if (map.get(keyWord) == null) {
                System.out.println("The products you are searching for were not found");
            }
        }

    }
    /**
     * search using the product id, keywords, and the range of years too
     * @param keys
     * @param myId
     * @param lowerYear
     * @param upperYear 
     */
    protected static void searchByKeysHashIDYear(String keys, String myId, int lowerYear, int upperYear) {
        System.out.println("in hasher for keys and id and year");
        System.out.println("lowerYear: " + lowerYear);
        System.out.println("upperYear: " + upperYear);
        String[] splitWords = new String[keys.split("[ ]+").length];
        splitWords = keys.split("[ ]+");
        System.out.println(splitWords[0]);
        for (String keyWord : splitWords) {
            keyWord = keyWord.toLowerCase();
            if (map.get(keyWord) != null) {
                ArrayList<Integer> allInstancesofWord = (ArrayList<Integer>) map.get(keyWord);
                {
                    for (int i = 0; i < allInstancesofWord.size(); i++) {

                        Product e = productList.get(allInstancesofWord.get(i));
                        if (e.productIdMatch(myId) && e.productYearMatch(lowerYear, upperYear)) // if an element has the desired id and it has the correct key terms. it is printed.
                        {
                            System.out.println("Printing keys id and year");
                            ProductGUI.Display("\n");
                            System.out.println(productList.get(allInstancesofWord.get(i)));
                            ProductGUI.Display((e.toString() + "\n"));
                        }
                    }
                }
            }
            if (map.get(keyWord) == null) {
                System.out.println("The products you are searching for were not found");
            }
        }

    }
    /**
     * read the store items from a file
     * @param fileName 
     */
    public static void fileRead(String fileName) {
        //System.out.println("in file read");
        String typeProduct, idProduct, nameProduct, priceProduct, yearProduct, authorsProduct, publisherProduct, makerProduct;

        Scanner inputStream = null;
        try {
            inputStream = new Scanner(new FileInputStream(fileName));
        } catch (FileNotFoundException e) {
            System.out.println("the file was not there");
        }
        String fileLine = null;
        while (inputStream.hasNextLine()) {
            fileLine = inputStream.nextLine();
            //System.out.println(fileLine);

            if (fileLine.contains("type") && fileLine.length() != 0) {
                //System.out.println("Type statement");
                typeProduct = fileLine.substring(8, fileLine.length() - 1); //this creates a substring from the file that has the product type. it lets you have the product type without quotes.
                //System.out.println(typeProduct);

                //parse productId
                fileLine = inputStream.nextLine();
                idProduct = fileLine.substring(13, fileLine.length() - 1);
                //System.out.println("id product");
                //System.out.println(idProduct);

                //now parsing name
                fileLine = inputStream.nextLine();
                //System.out.println("now parsing name");
                nameProduct = fileLine.substring(8, fileLine.length() - 1);
                //System.out.println("name Product");
                //System.out.println(nameProduct);

                //now parsing price
                fileLine = inputStream.nextLine();
                priceProduct = fileLine.substring(9, fileLine.length() - 1);
                //System.out.println(priceProduct);
                Double priceProductDouble = 0.0;
                try {
                    priceProductDouble = Double.parseDouble(priceProduct);
                } catch (NumberFormatException e) {
                    System.out.println("invalid price format");
                }
                //System.out.println(priceProductDouble);

                //now parsing year
                fileLine = inputStream.nextLine();
                yearProduct = fileLine.substring(8, fileLine.length() - 1);
                //System.out.println(yearProduct);
                int yearProductInt = 0;
                try {
                    yearProductInt = Integer.parseInt(yearProduct);
                } catch (NumberFormatException e) {
                    System.out.println("invalid year in the file");
                }

                if (typeProduct.equalsIgnoreCase("book")) {
                    //System.out.println("this is book");

                    //now parsing authors
                    fileLine = inputStream.nextLine();
                    authorsProduct = fileLine.substring(11, fileLine.length() - 1);
                    //System.out.println(priceProduct);

                    //now parsing publlishers
                    fileLine = inputStream.nextLine();
                    publisherProduct = fileLine.substring(13, fileLine.length() - 1);
                    //System.out.println(publisherProduct);
                    try{
                        Product addProduct = new Book(idProduct, nameProduct, yearProductInt, priceProductDouble, authorsProduct, publisherProduct);
                       
                        productList.add(addProduct);
                       
                        nameProduct = nameProduct.toLowerCase();
                        
                        addToMap(nameProduct);
                       
                    } catch (Exception error){
                        System.out.println(error.getMessage());
                        ProductGUI.Display(error.getMessage() + "\n");
                    }
                    

                } else if (typeProduct.equalsIgnoreCase("electronics")) {
                    //System.out.println("electronic");
                    //now parsing publlishers
                    fileLine = inputStream.nextLine();
                    makerProduct = fileLine.substring(9, fileLine.length() - 1);
                    //System.out.println(makerProduct);
                    
                    try{
                        Product addProduct = new Electronics(idProduct, nameProduct, yearProductInt, priceProductDouble, makerProduct);
                        productList.add(addProduct);
                        nameProduct = nameProduct.toLowerCase();
                        addToMap(nameProduct);
                    }catch(Exception error){
                        System.out.println(error.getMessage());
                        ProductGUI.Display(error.getMessage() + "\n");
                    }
                    
                }

            }

        }
        System.out.println("Reading File: Successful");
    }
    /**
     * when the user quits, the urrent inventory is printed to the file
     * @param outputFile 
     */
    protected static void outputToFile(String outputFile)
    {
        System.out.println("...Now Writing to file...");
        PrintWriter fileWriter = null;
        try {
            fileWriter = new PrintWriter(new FileOutputStream(outputFile));
        } catch (FileNotFoundException e) {
            System.out.println("Sorry, The file failed to open.");
            System.exit(0);
        }
        for (Product element : productList) {
            fileWriter.println(element);
        }
        fileWriter.close();
    }
    /**
     * print the entire arrayList
     */
    public static void print()
    {
        ProductGUI.Display("\n");
        for(Product element: productList)
        {
            ProductGUI.Display("\n");
            System.out.println(element);
            ProductGUI.Display(element.toString());
        }
    }
    
    
}
