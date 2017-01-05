/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;

import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author gurjapsingh
 */
public class productListeners {
    /**
     * when Add Product is clicked. this listener tells the ProductGUI to show the add product card.
     */
    protected class showAdding implements ActionListener{
        public void actionPerformed(ActionEvent e){
            System.out.println("Enable product adding");
            ProductGUI.showThisCard("Add Product Panel");
        }
    }
    /**
     * when using the type JComboBox, selecting book or electronic will toggle 
     * between the book specific fields such as author and publisher and the electronic
     * specific field maker.
     */
    protected class selectedType implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            JComboBox cb = (JComboBox)e.getSource();        // get JComboBox from ProductGUI
            int bookOrElec = (int)cb.getSelectedIndex();    // get the index that is selected
            
            if (bookOrElec == 0){                           // if first index is selected it is a book
                ProductGUI.Display("Display book menu\n");
                ProductGUI.setTypeChoice("book");
                
            }
            else if(bookOrElec == 1){                       // second index selected is an Electronic item
                ProductGUI.Display("Display elec menu\n");
                ProductGUI.setTypeChoice("electronic");
            }
             

        }
    }
    /**
     * this listener is activated when the user switches to the Search 
     * option from the Command menu
     */
    protected class showSearch implements ActionListener{
        public void actionPerformed(ActionEvent e){
            System.out.println("Showing search page");
            ProductGUI.showThisCard("Search");
            
        }
    }
    /**
     * after adding a product or executing a search, the fields
     * will be reset
     */
    protected class resetAllFields implements ActionListener{
        public void actionPerformed(ActionEvent e){
            System.out.println("resetting all fields");
            ProductGUI.fieldReset();
            
        }
    }
    /**
     * when the add button is clicked. it must be determined if 
     * we are adding a book or an electronic
     */
    protected class addProduct implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            JComboBox cb = ProductGUI.getselectedType(); // get the combo box from the gui
            int bookOrElec = (int) cb.getSelectedIndex(); // get the index that is selected
            if (bookOrElec == 0) // if the first option is selected, a book is to be added
            {
                System.out.println("adding a book-1");
                productManager.addBook(ProductGUI.getBookFields());
            } else // otherwise we are adding an electronic
            {
                System.out.println("adding an electronic");
                productManager.addElec(ProductGUI.getElecFields());
            }
        }
    }
    /**
     * print the entire Array List
     */
    protected class printAll implements ActionListener{
        public void actionPerformed(ActionEvent e)
        {
            productManager.print();
        }
    }
    /**
     * when the search button is pressed. we have to
     * determine what fields are entered and what
     * ones are not.
     */
    protected class searchInventory implements ActionListener{
        public void actionPerformed(ActionEvent e)
        {
            // If only the product field is filled. search onnly using the product.
            if (ProductGUI.getSearchIDField().length() !=0 && (ProductGUI.getLowerYearField().length()== 0 && ProductGUI.getUpperYearField().length() == 0)&&ProductGUI.getNameKeywordsField().length()==0)
            {
                System.out.println("search with only product ID and it is "+ ProductGUI.getSearchIDField());
                try{
                    String productID = productManager.goodSearchID(ProductGUI.getSearchIDField()); // get the product ID, make sure it follows valid product ID format, and assignt it to the produt ID
                    productManager.searchById(productID);
                } catch(Exception error){
                    System.out.println(error.getMessage());
                    ProductGUI.Display(error.getMessage() + "\n");
                }
            }
            // If the fields are empty except for the years
            else if (ProductGUI.getSearchIDField().length() ==0 && (ProductGUI.getLowerYearField().length()!= 0 || ProductGUI.getUpperYearField().length() != 0)&&ProductGUI.getNameKeywordsField().length()==0)
            {
                System.out.println("search with only the years and they are " + ProductGUI.getLowerYearField() + " "+ProductGUI.getUpperYearField());
                
                try{
                    int productLowerYear;
                    int productHigherYear;
                    String strProductYearLow = "";
                    String strProductYearHigh = "";
                    
                    if(ProductGUI.getLowerYearField().length()== 0)
                       productLowerYear = 1000;
                    else
                    {
                       strProductYearLow = productManager.goodSearchYear(ProductGUI.getLowerYearField());
                       productLowerYear = Integer.parseInt(strProductYearLow); // get the lower year from the gui
                    }
                    if(ProductGUI.getUpperYearField().length() == 0)
                        productHigherYear = 9999;
                    else
                    {
                        strProductYearHigh = productManager.goodSearchYear(ProductGUI.getUpperYearField());
                        productHigherYear = Integer.parseInt(strProductYearHigh); // get the higher year from the gui
                    }
                    
                    System.out.println("yearlow " + productLowerYear);
                    
                    System.out.println("yearHigh" + productHigherYear);
                    
                    productManager.searchByYear(productLowerYear, productHigherYear);
                } catch(Exception error){
                    System.out.println(error.getMessage());
                    ProductGUI.Display(error.getMessage() + "\n");
                }
                
            }
            // Search using only the search terms
            else if (ProductGUI.getSearchIDField().length() == 0 && (ProductGUI.getLowerYearField().length() == 0 && ProductGUI.getUpperYearField().length() == 0) && ProductGUI.getNameKeywordsField().length() != 0) 
            {
                System.out.println("search with only search terms and they are "+ ProductGUI.getNameKeywordsField());
                try{
                    productManager.searchByKeysHash(ProductGUI.getNameKeywordsField()); // use only the search terms
                } catch(Exception error){
                    System.out.println(error.getMessage());
                    ProductGUI.Display(error.getMessage() + "\n");
                }
            }
            // Search using the product ID and the year range
            else if (ProductGUI.getSearchIDField().length() != 0 && (ProductGUI.getLowerYearField().length() != 0 || ProductGUI.getUpperYearField().length() != 0) && ProductGUI.getNameKeywordsField().length() == 0) 
            {
                System.out.println("search with only product ID and year range and it is " + ProductGUI.getSearchIDField() + " "+ProductGUI.getLowerYearField() + " -  " + ProductGUI.getUpperYearField());
                
                try{
                    String productID = productManager.goodSearchID(ProductGUI.getSearchIDField());
                    int productLowerYear;
                    int productHigherYear;
                    String strProductYearLow = "";
                    String strProductYearHigh = "";

                    if (ProductGUI.getLowerYearField().length() == 0) {
                        productLowerYear = 1000;
                    } else {
                        strProductYearLow = productManager.goodSearchYear(ProductGUI.getLowerYearField());
                        productLowerYear = Integer.parseInt(strProductYearLow); // get the lower year from the gui
                    }
                    if (ProductGUI.getUpperYearField().length() == 0) {
                        productHigherYear = 9999;
                    } else {
                        strProductYearHigh = productManager.goodSearchYear(ProductGUI.getUpperYearField());
                        productHigherYear = Integer.parseInt(strProductYearHigh); // get the higher year from the gui
                    }
                    
                    productManager.searchByIdYear(productID, productLowerYear, productHigherYear);
                }catch (Exception error){
                    System.out.println(error.getMessage());
                    ProductGUI.Display(error.getMessage() + "\n");
                }
                
            }
            // search using the product ID and some search terms
            else if (ProductGUI.getSearchIDField().length() != 0 && (ProductGUI.getLowerYearField().length() == 0 && ProductGUI.getUpperYearField().length() == 0) && ProductGUI.getNameKeywordsField().length() != 0) 
            {
                System.out.println("search with ID and search terms and they are " + ProductGUI.getSearchIDField() + " " + ProductGUI.getNameKeywordsField());
                ProductGUI.Display("search with ID and search terms and they are " + ProductGUI.getSearchIDField() + " " + ProductGUI.getNameKeywordsField() + "\n");
                
                try{
                    String productID = productManager.goodSearchID(ProductGUI.getSearchIDField());
                    productManager.searchByKeysHashID(ProductGUI.getNameKeywordsField(), productID);
                    
                }catch(Exception error){
                    System.out.println(error.getMessage());
                    ProductGUI.Display(error.getMessage() + "\n");
                }
                
            } 
            // search using the year range and the key terms
            else if (ProductGUI.getSearchIDField().length() == 0 && (ProductGUI.getLowerYearField().length() != 0 || ProductGUI.getUpperYearField().length() != 0) && ProductGUI.getNameKeywordsField().length() != 0) 
            {
                System.out.println("search with year range and key terms " + ProductGUI.getLowerYearField()+ " "+ ProductGUI.getUpperYearField()+ " " + ProductGUI.getNameKeywordsField());
                
                try{
                    int productLowerYear;
                    int productHigherYear;
                    String strProductYearLow = "";
                    String strProductYearHigh = "";

                    if (ProductGUI.getLowerYearField().length() == 0) {
                        productLowerYear = 1000;
                    } else {
                        strProductYearLow = productManager.goodSearchYear(ProductGUI.getLowerYearField());
                        productLowerYear = Integer.parseInt(strProductYearLow); // get the lower year from the gui
                    }
                    if (ProductGUI.getUpperYearField().length() == 0) {
                        productHigherYear = 9999;
                    } else {
                        strProductYearHigh = productManager.goodSearchYear(ProductGUI.getUpperYearField());
                        productHigherYear = Integer.parseInt(strProductYearHigh); // get the higher year from the gui
                    }
                    
                    productManager.searchByKeysHashYear(ProductGUI.getNameKeywordsField(), productLowerYear, productHigherYear);
                } catch (Exception error){
                    System.out.println(error.getMessage());
                    ProductGUI.Display(error.getMessage() + "\n");
                }
            }
            // searh using the ProductID, range of years, and search terms
            else if (ProductGUI.getSearchIDField().length() != 0 && (ProductGUI.getLowerYearField().length() != 0 || ProductGUI.getUpperYearField().length() != 0) && ProductGUI.getNameKeywordsField().length() != 0)
            {
                System.out.println("searching using all of them");
                try{
                    String productID = productManager.goodSearchID(ProductGUI.getSearchIDField());
                    int productLowerYear;
                    int productHigherYear;
                    String strProductYearLow = "";
                    String strProductYearHigh = "";

                    if (ProductGUI.getLowerYearField().length() == 0) {
                        productLowerYear = 1000; // if this field is empty but the other one is filled, the lower end is set to 1000
                    } else {
                        strProductYearLow = productManager.goodSearchYear(ProductGUI.getLowerYearField());
                        productLowerYear = Integer.parseInt(strProductYearLow); // get the lower year from the gui
                    }
                    if (ProductGUI.getUpperYearField().length() == 0) {
                        productHigherYear = 9999; // if this field is empty but the lower year one is fileld, the upper end is set to 1000
                    } else {
                        strProductYearHigh = productManager.goodSearchYear(ProductGUI.getUpperYearField());
                        productHigherYear = Integer.parseInt(strProductYearHigh); // get the higher year from the gui
                    }
                    productManager.searchByKeysHashIDYear(ProductGUI.getNameKeywordsField(), productID, productLowerYear, productHigherYear);
                } catch(Exception error){
                    System.out.println(error.getMessage());
                    ProductGUI.Display(error.getMessage() + "\n");
                }
            }
            else if (ProductGUI.getSearchIDField().length() == 0 && (ProductGUI.getLowerYearField().length() == 0 || ProductGUI.getUpperYearField().length() == 0) && ProductGUI.getNameKeywordsField().length() == 0) 
            {
                productManager.print();
            }
      
            System.out.println("You have decided to perform a search");
            //productManager.performSearch(ProductGUI.getSearchIDField(), ProductGUI.getLowerYearField(), ProductGUI.getUpperYearField());
        }
    }
    /**
     * when the user clicks quit. we must write to the file
     * before quitting the program
     */
    protected class endProgram implements ActionListener{
        public void actionPerformed(ActionEvent e)
        {
            productManager.outputToFile(Assignment3.fileReturn());
            System.exit(0);
        }
    }
    /**
     * when the user clicks the x button on the window. we must write to the file
     * before quitting the program
     */
    protected class safelyEndProgram implements WindowListener{
        public void windowOpened(WindowEvent e)
        {}
        public void windowClosing(WindowEvent e)
        {
            productManager.outputToFile(Assignment3.fileReturn());
            System.exit(0);
        }
        public void windowClosed(WindowEvent e)
        {}
        public void windowIconified(WindowEvent e)
        {}
        public void windowDeiconified(WindowEvent e)
        {}
        public void windowActivated(WindowEvent e)
        {}
        public void windowDeactivated(WindowEvent e)
        {}
    }
    
}
