/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment3;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 *
 * @author gurjapsingh
 */
public class ProductGUI extends JFrame{
    productListeners listener = new productListeners();
    
    // The Three panels that will be used in the gui
    private static JPanel threePanelsContainer,inputHalf, buttonHalf,addProductPanel, buttonScreen,searchHalf, searchPanel, welcomePanel, textPanel;
    private static JTextArea displayScreen;
    private JButton enableBook, addProductButton, addSearch,addResetFieldsButton,searchResetFieldsButton;
    private JMenu commandMenu;
    private JMenuItem enableAdding, enableSearch, quitProgram;
    private JMenuBar bar;
    private  JLabel typeLabel;
    private static JComboBox selectType;
    
    //variables for adding a book 
    private JLabel pidLabel, pnameLabel, pyearLabel, ppriceLabel;
    private static JLabel bauthorLabel, bpublisherLabel;
    private static JTextField pidField, pnameField, pyearField, ppriceField, bauthorField, bpublisherField;

    
    //variables for adding an electronic
    private static JLabel  emakerLabel;
    private static JTextField emakerField;

    
    //variables for searching
    private JLabel sidLabel, snameLabel, syearLowLabel,syearHighLabel;
    private static JTextField sidField, snameField, syearLowField,syearHighField;
    /**
     * constructor for the gui
     */
    public ProductGUI()
    {
        super("eStore Search");
        this.setSize(700, 1200);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(listener.new safelyEndProgram()); // when the x is clicked, the program must be ended safely
        this.setLayout(new GridLayout(2,1));
        
        textScreen(); // make the display screen
        upperHalf(); // upper half of the GUI. where input happens
        createmainWindow(); // bring everything together
    }
    /**
     * this function makes a new panel and makes the Display of the GUI
     */
    private void textScreen(){
        textPanel = new JPanel();
        displayScreen = new JTextArea(20,50);
        displayScreen.setEditable(false);
        
        JScrollPane scrollText = new JScrollPane(displayScreen);
        scrollText.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //displayScreen.add(scrollText);
        
        textPanel.add(scrollText); // add the scrollable area to the text panel
    }
    /**
     * make the upper half of the GUI. with the different layouts of adding products and searching panels
     */
    private void upperHalf(){
        threePanelsContainer = new JPanel();                // this panel contians the welcome,add,and search panels
        threePanelsContainer.setBackground(Color.blue);
        
        threePanelsContainer.setLayout(new CardLayout());
        buttonScreen = new JPanel();                        // this panel will contain the different button layouts. the layouts of the adding panel and the search panel
        buttonScreen.setLayout(new CardLayout());
        GridBagConstraints g = new GridBagConstraints();
        commandMenu = new JMenu("Command");                 // make the Jmenu
        
        enableAdding = new JMenuItem("Add Product");        // clicking this will bring up the adding product panel
        enableAdding.addActionListener(listener.new showAdding());
        commandMenu.add(enableAdding);
        
        enableSearch = new JMenuItem("Search Inventory");
        enableSearch.addActionListener(listener.new showSearch()); // show the search panel along with its buttons
        commandMenu.add(enableSearch);
        
        quitProgram = new JMenuItem("Quit");
        quitProgram.addActionListener(listener.new endProgram()); // clicking quit will end the program
        commandMenu.add(quitProgram);
        
        bar = new JMenuBar();
        bar.add(commandMenu);
        setJMenuBar(bar);
        
        welcomePanel = new JPanel();
        welcomePanel.setLayout(new GridBagLayout());
        
        JLabel welcometoEstore = new JLabel("Welcome to eStore");
        welcometoEstore.setBorder(new EmptyBorder(0,0,25,0));
        g.anchor = GridBagConstraints.LINE_START;
        g.gridx = 0;
        g.gridy = 0;
        welcomePanel.add(welcometoEstore,g);
        g.gridx = 0;
        g.gridy = 1;
        JLabel secondaryWelcome = new JLabel("Choose a command from the \"commands\" menu above for");
        welcomePanel.add(secondaryWelcome,g);
        threePanelsContainer.add(welcomePanel);
        JLabel tertiaryWelcome = new JLabel("adding a product, searching products, quitting the program ");
        g.gridx = 0;
        g.gridy = 2;
        welcomePanel.add(tertiaryWelcome,g);
        addProductPanel = new JPanel();
        addProductPanel.setLayout(new GridBagLayout());
        
        // add the type comboBox to the frame
        typeLabel = new JLabel("Type: "); // select the type of product. book or electronic
        g.anchor = GridBagConstraints.CENTER;
        g.gridx = 0;
        g.gridy = 0;
        addProductPanel.add(typeLabel,g); 
        String[] productTypes = {"Book","Electronic"};
        selectType = new JComboBox(productTypes);
        selectType.setSelectedIndex(0);
        selectType.addActionListener(listener.new selectedType());
        g.gridx = 1;
        g.gridy = 0;
        addProductPanel.add(selectType,g);
        enableBook = new JButton("Book");
        enableBook.addActionListener(listener.new showSearch());
        selectType.add(enableBook);
        
        //id
        pidLabel = new JLabel("ID:",JLabel.LEADING); // label for the Id field
        pidLabel.setBorder(new EmptyBorder(5,5,5,5)); // set border
        pidField = new JTextField(10);
        g.gridx = 0;
        g.gridy = 1;
        addProductPanel.add(pidLabel,g); // add label to the add product panel
        pidLabel.setLabelFor(pidField);
        g.gridx = 1;
        g.gridy = 1;
        addProductPanel.add(pidField,g); // add the id field to the add panel
        
        //name
        pnameLabel = new JLabel("name:", JLabel.LEADING);
        pnameLabel.setBorder(new EmptyBorder(5,5,5,5));
        pnameField = new JTextField(10);
        g.gridx = 0;
        g.gridy = 2;
        addProductPanel.add(pnameLabel,g);
        pnameLabel.setLabelFor(pnameField);
        g.gridx = 1;
        g.gridy = 2;
        addProductPanel.add(pnameField,g);
        
        //year
        pyearLabel = new JLabel("year:", JLabel.LEADING);
        pyearLabel.setBorder(new EmptyBorder(5,5,5,5));
        pyearField = new JTextField(10);
        g.gridx = 0;
        g.gridy = 3;
        addProductPanel.add(pyearLabel,g);
        pyearLabel.setLabelFor(pyearField);
        g.gridx = 1;
        g.gridy = 3;
        addProductPanel.add(pyearField,g);
        
        //price
        ppriceLabel = new JLabel("price:", JLabel.LEADING);
        ppriceLabel.setBorder(new EmptyBorder(5,5,5,5));
        ppriceField = new JTextField(10);
        g.gridx = 0;
        g.gridy = 4;
        addProductPanel.add(ppriceLabel,g);
        ppriceLabel.setLabelFor(ppriceField);
        g.gridx = 1;
        g.gridy = 4;
        addProductPanel.add(ppriceField,g);
        
        
        //author
        bauthorLabel = new JLabel("author:");       // label for author. only specific to books
        bauthorLabel.setBorder(new EmptyBorder(5,5,5,5));
        bauthorField = new JTextField(10);
        //g.anchor = GridBagConstraints.LINE_START;
        g.gridx = 0;
        g.gridy = 5;
        addProductPanel.add(bauthorLabel,g);
        bauthorLabel.setLabelFor(bauthorField);
        g.gridx = 1;
        g.gridy = 5;
        addProductPanel.add(bauthorField,g);
        
        //publisher
        bpublisherLabel = new JLabel("publisher:");     // label for publisher. only specific to books
        bpublisherLabel.setBorder(new EmptyBorder(5,5,5,5));
        bpublisherField = new JTextField(10);
        
        g.gridx = 0;
        g.gridy = 6;
        addProductPanel.add(bpublisherLabel,g);
        bpublisherLabel.setLabelFor((bpublisherField));
        g.gridx = 1;
        g.gridy = 6;
        addProductPanel.add(bpublisherField,g);
        
        
        emakerLabel = new JLabel("maker:");             // label for maker. only specific to electronics
        emakerLabel.setBorder(new EmptyBorder(5,5,5,5));
        emakerField = new JTextField(10);
        g.gridx = 0;
        g.gridy = 7;
        addProductPanel.add(emakerLabel,g);
        emakerLabel.setLabelFor(emakerField);
        g.gridx = 1;
        g.gridy = 7;
        addProductPanel.add(emakerField,g);
        emakerLabel.setVisible(false);
        emakerField.setVisible(false);
       
        buttonHalf = new JPanel(); // this panel has the buttons for add function. reset and add buttons.
        buttonHalf.setLayout(new GridBagLayout());
        
        addProductButton = new JButton("add");
        addProductButton.addActionListener(listener.new addProduct());
        g.gridwidth = 1;
        g.gridx = 0;
        g.gridy = 0;
        buttonHalf.add(addProductButton,g);
        
        JButton printout = new JButton("print all");
        printout.addActionListener(listener.new printAll());
        g.gridx = 0;
        g.gridy = 1;
        buttonHalf.add(printout,g);
        addResetFieldsButton = new JButton("reset");
        addResetFieldsButton.addActionListener(listener.new resetAllFields());
        g.gridx = 0;
        g.gridy = 2;
        buttonHalf.add(addResetFieldsButton,g);
        
        buttonScreen.add(buttonHalf, "addHalf");        // add the buttons for adding to the button screen
        buttonScreen.setVisible(false);                 // set to false so it does not display during the welcome screen
        threePanelsContainer.add(addProductPanel, "Add Product Panel");
        
        // make the search panel
        searchPanel = new JPanel();
        searchPanel.setLayout(new GridBagLayout());
        
        //ID
        sidLabel = new JLabel("ID:");
        sidLabel.setBorder(new EmptyBorder(5,5,5,5));
        sidField = new JTextField(6);
        g.gridx = 0;
        g.gridy = 0;
        searchPanel.add(sidLabel,g);
        sidLabel.setLabelFor(sidField);
        g.gridx = 1;
        g.gridy = 0;
        searchPanel.add(sidField,g);
        
        //name
        snameLabel = new JLabel("name:");
        snameLabel.setBorder(new EmptyBorder(5,5,5,5));
        snameField = new JTextField(10);
        g.gridx = 0;
        g.gridy = 1;
        searchPanel.add(snameLabel,g);
        snameLabel.setLabelFor(snameField);
        g.gridx = 1;
        g.gridy = 1;
        searchPanel.add(snameField,g);
        
        //year
        syearLowLabel = new JLabel("Start Year:");
        syearLowLabel.setBorder(new EmptyBorder(5,5,5,5));
        syearLowField = new JTextField(10);
        g.gridx = 0;
        g.gridy = 2;
        searchPanel.add(syearLowLabel,g);
        syearLowLabel.setLabelFor(syearLowField);
        g.gridx = 1;
        g.gridy = 2;
        searchPanel.add(syearLowField,g);
        
        syearHighLabel = new JLabel("End Year:");
        syearHighLabel.setBorder(new EmptyBorder(5,5,5,5));
        syearHighField = new JTextField(10);
        g.gridx = 0;
        g.gridy = 3;
        searchPanel.add(syearHighLabel,g);
        g.gridx = 1;
        g.gridy = 3;
        searchPanel.add(syearHighField,g);
        
        searchHalf = new JPanel(); // this is a panel for the buttons of the search
        searchHalf.setLayout(new GridBagLayout());
        addSearch = new JButton("Search");
        addSearch.addActionListener(listener.new searchInventory());
        g.gridx = 0;
        g.gridy = 0;
        searchHalf.add(addSearch,g);
        searchResetFieldsButton = new JButton("Reset");
        searchResetFieldsButton.addActionListener(listener.new resetAllFields());
        g.gridx = 0;
        g.gridy = 1;
        searchHalf.add(searchResetFieldsButton,g); 
        buttonScreen.add(searchHalf, "searchHalf");
        
        threePanelsContainer.add(searchPanel,"Search");
    }
    /**
     * bring the windows together to make the final frame
     */
    private void createmainWindow(){
        
        GridBagConstraints g = new GridBagConstraints();
        inputHalf = new JPanel();                   // this encompasses the upper half of the window
        inputHalf.setLayout(new GridBagLayout());
        g.gridwidth = 3;                            // width of the search/ add panel is set to 3. the button screen is only of width 1
        
        g.gridx = 0;
        g.gridy = 0;
        inputHalf.add(threePanelsContainer);
        g.gridwidth = 1;
        g.gridx = 3;
        g.gridy = 0;
        inputHalf.add(buttonScreen);
        this.add(inputHalf); // add input half to the final window
        this.add(textPanel); // add message area
    }
    /**
     * this return is made to read whether book is selected or electronic
     * @return JComboBox
     */
    protected static JComboBox getselectedType()
    {
        return selectType;
    }
    /**
     * book is created if it has good values, otherwise, exceptions are thrown and the Book object is not made;
     * @return 
     */
    protected static Book getBookFields()
    {
        try{
            Book myBook = productManager.goodBookValues(pidField.getText(), pnameField.getText(), pyearField.getText(), ppriceField.getText(), bauthorField.getText(), bpublisherField.getText());
            return myBook;
        } catch(Exception e){
            System.out.println(e.getMessage());
            Display(e.getMessage());
        }
        
        return null;
    }
    /**
     * makes a electronic object if the fields are of acceptable format.
     * @return to function in productListeners that then sends the product 
     */
    protected static Electronics getElecFields()
    {
        Display("Fetching electronic field data\n");
        System.out.println("getElecFields");
        try{
            Electronics myElec = productManager.goodElectronicValues(pidField.getText(), pnameField.getText(), pyearField.getText(), ppriceField.getText(), emakerField.getText());
            return myElec;
        } catch(Exception e){
            System.out.println(e.getMessage());
            Display(e.getMessage());
        }
        
        return null;
    }
    /**
     * when performing searches, the search ID is called
     * @return 
     */
    protected static String getSearchIDField()
    {
      
        return sidField.getText();
        
    }
    /**
     * when performing searches, the lower year field is collected
     * @return 
     */
    protected static String getLowerYearField()
    {
       
        return syearLowField.getText();
        
    }
    /**
     * when performing searches, the upper year field is collected
     * @return 
     */
    protected static String getUpperYearField()
    {
        return syearHighField.getText();
        
    }
    /**
     * depending on whether the user wants to add or search, the corresponding panel is shown
     * @param panelToShow 
     */
    protected static void showThisCard(String panelToShow) {
        CardLayout cardLayout = (CardLayout) (threePanelsContainer.getLayout()); // get the card layout for the panel
        CardLayout buttonLayout = (CardLayout) (buttonScreen.getLayout());      // get the card layout for the buttons
   
        if (panelToShow.equalsIgnoreCase("Search")) // we want to show the search panel
        {
            ProductGUI.Display("Please enter your search criteria\n");
            cardLayout.show(threePanelsContainer, panelToShow);
            buttonLayout.show(buttonScreen, "searchHalf");
        }
        else{                                       // show the adding panel
            cardLayout.show(threePanelsContainer, panelToShow);
            buttonLayout.show(buttonScreen, "addHalf");
        }
            
        
        cardLayout.show(threePanelsContainer, panelToShow);
        buttonScreen.setVisible(true);              // we initially set this to false for the welcome screen, it must now be turned visible
    }
    /**
     * when the user selects book or electronic from the combo box, we hid the labels and fields of the opposite type
     * @param typeToShow 
     */
    protected static void setTypeChoice(String typeToShow){
        if (typeToShow.equals("book"))
        {
            bauthorLabel.setVisible(true);
            bauthorField.setVisible(true);
            bpublisherLabel.setVisible(true);
            bpublisherField.setVisible(true);
            
            emakerLabel.setVisible(false);
            emakerField.setVisible(false);
        }
        else if (typeToShow.equalsIgnoreCase("electronic"))
        {
            emakerLabel.setVisible(true);
            emakerField.setVisible(true);
            
            bauthorLabel.setVisible(false);
            bauthorField.setVisible(false);
            bpublisherLabel.setVisible(false);
            bpublisherField.setVisible(false);
        }
    }
    /** 
     * after product is added, clear the search field
     */
    protected static void fieldReset()
    {
        pidField.setText("");
        pnameField.setText("");
        pyearField.setText("");
        ppriceField.setText("");
        bauthorField.setText("");
        bpublisherField.setText("");
        emakerField.setText("");
        sidField.setText("");
        snameField.setText("");
        syearLowField.setText("");
        syearHighField.setText("");
    }
    /**
     * for the searching, retrieve name field
     * @return 
     */
    protected static String getNameKeywordsField()
    {
        return snameField.getText();
    }
    /**
     * controls the display text area. this function is sent strings throught the gui to display
     * @param toPrint 
     */
    protected static void Display(String toPrint)
    {
        displayScreen.append(toPrint);
    }
    
}
