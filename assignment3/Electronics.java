/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;

/**
 *
 * @author gurjapsingh
 */
public class Electronics extends Product{
    private String productMaker;
    /**
     * empty constructor for Electronics. sets maker to empty string
     */
    public Electronics()
    {
        super();
        this.productMaker = "";
    }
    /**
     * this constructor is invoked when all of electronics is to be filled, including the maker.
     * @param Id
     * @param Name
     * @param Year
     * @param Price
     * @param Maker 
     */
    public Electronics(String Id, String Name, int Year, double Price, String Maker)
    {
        super("electronics",Id, Name, Year, Price);
        this.productMaker = Maker;
    }
    /**
     * it calls on its super constructor for product class and then adds its own maker afterwards.
     * @return 
     */
    public String toString() {
        return super.toString() + "\nmaker = \"" + this.productMaker + "\"\n";
    }
    /**
     * returns the maker. this is used when adding a new product
     * @return 
     */
    public String getMaker(){
        return this.productMaker;
    }
    /**
     * tells you if equal or not
     */
    public void Equals()
    {
        
    }
}
