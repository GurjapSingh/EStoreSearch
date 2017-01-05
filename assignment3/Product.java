/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;

import java.util.ArrayList;

/**
 *
 * @author gurjapsingh
 */
public abstract class Product {
    private String productType;
    private String productId;
    private String productName;
    private int productYear;
    private String stringPrice;
    private double productPrice; // the stringPrice is converted into a double
    /**
     * constructor for Product
     */
    public Product() // constructor for product
    {
        this.productId = "";
        this.productName = "";
        this.productYear = 0;
        this.productPrice = 0;
    }
    /**
     * Constructor for product that has all the required values
     * @param Type
     * @param Id
     * @param Name
     * @param Year
     * @param Price 
     */
    public Product(String Type,String Id, String Name, int Year, double Price)
    {
        this.productType = Type;
        this.productId = Id;
        this.productName = Name;
        this.productYear = Year;
        this.productPrice = Price;
    }
    /**
     * initializes price to zero if setPrice is used. It is not used but it was tested with.
     */
    public void setPrice()
    {
        this.productPrice = 0;
    }
    /**
     * initializes price to the amount in parameter. it is not used but it was tested with.
     * @param Price 
     */
    public void setPrice(double Price)
    {
        this.productPrice = Price;
    }
    /**
     * since no two products can have the same Id. this function returns false, if an Id is not available.
     * @param myId
     * @param checkList
     * @return 
     */
    public boolean isAvailable(String myId, ArrayList<Product> checkList) {
        for (Product element : checkList) {
            if (myId.equals(element.productId)) {
                return false;
            }
        }
        return true;
    }
    /**
     * toString prints out the elements
     * @return 
     */
    public String toString() {
        return "type = \"" + this.productType + "\"\nproductID = \"" + this.productId + "\"\nname = \"" + this.productName + "\"\nprice = \"" + this.productPrice + "\"\nyear = \"" + this.productYear + "\"";
    }
    /**
     * this function returns a product name. Testing.
     * @return 
     */
    public String myName(){
        return this.productName;
    }
    /**
     * this was used in the last assignment to check for keywords. It was not used here. only for testing and comparing.
     * @param Keywords
     * @return 
     */
    public boolean productKeywordsMatch(String Keywords)
    {
        String lowerCaseString = this.productName;
        lowerCaseString = lowerCaseString.toLowerCase();
        if(lowerCaseString.contains(Keywords))
        {
            return true;
        }
        else
            return false;
            
    }
    /**
     * used to determine if an element's id matches to the id of the calling object.
     * @param Id
     * @return 
     */
    public boolean productIdMatch(String Id)
    {
        if (Id.equals(this.productId))
        {
            //System.out.println("element was only used to call. compared with this.sent with it");
            return true;
        }
            
        else
            return false;
    }
    /**
     * used to determine if an element is within the required range of years.
     * @param bottomYear
     * @param upperYear
     * @return 
     */
    public boolean productYearMatch(int bottomYear, int upperYear)
    {
        if (this.productYear >= bottomYear && this.productYear <= upperYear)
        {
            return true;
        }
        else{
            return false;
        }
    }
    /**
     * get id of the string. used to determine if product is already in th earray list
     * @return 
     */
    public String getID()
    {
        return this.productId;
    }
    /**
     * get name of string
     * @return 
     */
    public String getName()
    {
        return this.productName;
    }
    /**
     * get year of item. used to determine whether a certain year range is applicable to a product
     * @return 
     */
    public int getYear()
    {
        return this.productYear;
    }
    /**
     * get price of the product, if applicable
     * @return 
     */
    public double getPrice()
    {
        return this.productPrice;
    }
    
}
