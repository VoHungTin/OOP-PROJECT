/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class AppTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<Product> listOfBooks = new ArrayList<>();
        Product product1= new Product("book1","math1",100000, 0,5);
        Product product2= new Product("book2","math2",120000,0,4);
        Product product3= new Product("book3","phy1",200000,10,5);
        Product product4= new Product("book4","phy2",190000,5,2);
        listOfBooks.add(product1);
        listOfBooks.add(product2);
        listOfBooks.add(product3);
        listOfBooks.add(product4);
        
        ArrayList<Product> listOfNovels = new ArrayList<>();
        Product novel1= new Product("nov1","Fairy Tail",90000,0,5);
        Product novel2= new Product("nov2","One Piece",90000,0,4);
        Product novel3= new Product("nov3","Hatsukoi Zombie",70000,5,4);
        Product novel4= new Product("nov4","To Love Ru",65000,5,3);
        listOfNovels.add(novel1);
        listOfNovels.add(novel2);
        listOfNovels.add(novel3);
        listOfNovels.add(novel4);
        
        Date a1 = new Date(2016-1900, 10-1, 5);
        Date a2 = new Date(2016-1900, 11-1, 10);
        Invoice invoice1 = new Invoice("111",a1,listOfBooks);
        Invoice invoice2 = new Invoice("222",a2,listOfNovels);
        Thread thread1=new Thread(new InvoicePrinter(invoice1));
        Thread thread2=new Thread(new InvoicePrinter(invoice2));
        thread1.start();
        thread2.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(AppTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String date=sdf.format(invoice1.getIssueDate());
        System.out.println("Total Price:");
        System.out.println("- "+date+": "+invoice1.calculateTotalPrice());
        date=sdf.format(invoice2.getIssueDate());
        System.out.println("- "+date+": "+invoice2.calculateTotalPrice());
        
    }
    
}
