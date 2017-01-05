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
public class Assignment3 {

    /**
     * @param args the command line arguments
     */
    private static String fileName;
    public static void main(String[] args) {
        
        try {
            System.out.println(args[0]);
            fileName = args[0];
            productManager.fileRead(args[0]);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            ProductGUI.Display(e.getMessage() + "\n");
        }
        
        
        ProductGUI gui = new ProductGUI();
        gui.setVisible(true);
    }
    protected static String fileReturn(){
    return fileName;
    }
}
