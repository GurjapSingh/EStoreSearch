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
public class Book extends Product {
    private String bookAuthor;
    private String bookPublisher;
    /**
     * this constructor initializes books to "N/A".Empty constructor.
     */
    public Book()
    {
        super();
        this.bookAuthor = "N/A";
        this.bookPublisher = "N/A";
    }
    /**
     * this constructors takes in all the elements required to make a book product. it calls super() to get the product elements that are shared by books and electronics
     * @param Id
     * @param Name
     * @param Year
     * @param Price
     * @param Author
     * @param Publisher 
     */
    public Book(String Id, String Name, int Year, double Price, String Author, String Publisher) throws Exception{
        super("book",Id, Name, Year, Price);
        this.bookAuthor = Author;
        this.bookPublisher = Publisher;
    }
    /**
     * get author of the product. used during adding
     * @return 
     */
    public String getAuthor()
    {
        return this.bookAuthor;
    }
    /**
     * get publisher of product. used during adding
     * @return 
     */
    public String getPublisher()
    {
        return this.bookPublisher;
    }
    /**
     * calls on super() to get common instance variables with the electronics and adds its own. used for printing purposes.
     * @return 
     */
    @Override
    public String toString()
    {
        return super.toString()+ "\nauthors = \"" + this.bookAuthor + "\"\npublisher = \"" + this.bookPublisher + "\"\n";
    }
    /**
     * tells you if equal or not
     */
    public void Equals()
    {
        
    }
    
}
