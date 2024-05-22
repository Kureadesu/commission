package test1;
import java.util.List;
import java.util.Scanner;


abstract class Item {
    String title;
    String authorOrDirector;
    int year;
   
   
   public static int ItemCount = 0;

   public Item(String title, String authorOrDirector, int year) {
       this.title = title;
       this.authorOrDirector = authorOrDirector;
       this.year = year;
   }

   
   public abstract void issue();
   public abstract void returnItem();
   public abstract void displayInfo();

   
   public static void TotalItemsIssued() {
       System.out.println("ItemCount: " + ItemCount);
   }
}
class Book extends Item {
   public Book(String title, String author, int year) {
       super(title,author,year);
   }


   public void issue() {
       System.out.println("Issuing book: " + title + " by " + authorOrDirector);
       ItemCount++;
   }

   public void returnItem() {
       System.out.println("Returning book: " + title + " by " + authorOrDirector);
   }

  
   public void displayInfo() {
       System.out.println("Book - Title: " + title + ", Author: " + authorOrDirector + ", Year: " + year);
   }
}
class DVD extends Item {
   public DVD(String title, int year, String director) {
       super(title, director, year);
   }

   
   public void issue() {
       System.out.println("Issuing DVD: " + title + " directed by " + authorOrDirector);
       ItemCount++;
   }

   
   public void returnItem() {
       System.out.println("Returning DVD: " + title + " directed by " + authorOrDirector);
   }

   
   public void displayInfo() {
       System.out.println("DVD - Title: " + title + ", Director: " + authorOrDirector + ", Year: " + year);
   }
}
class Magazine extends Item {
   public Magazine(String title, int year, String editor) {
       super(title, editor, year);
   }

   
   public void issue() {
       System.out.println("Issuing magazine: " + title + " edited by " + authorOrDirector);
       ItemCount++;
   }
   public void returnItem() {
       System.out.println("Returning magazine: " + title + " edited by " + authorOrDirector);
   }
   public void displayInfo() {
       System.out.println("Magazine - Title: " + title + ", Editor: " + authorOrDirector + ", Year: " + year);
   }
}


public class comi {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
        while (true){
        System.out.println("Library Items Available:");
        String type = in.nextLine();
        Item myObj = null;
           
           

        Item Book = new Book("Airplane Stories and Histories", "Norman Currey", 2017);
        Item DVD = new DVD("San Andreas", 2012, "Brad Peyton");
        Item Magazine = new Magazine("Book of Aircraft", 2015, "Generic");
           
            if(type.equalsIgnoreCase("Book")){
                myObj = Book;
            }
            else if (type.equalsIgnoreCase("DVD")){
                myObj = DVD;
            }
            else if (type.equalsIgnoreCase("Magazine")){
                myObj = Magazine;
               
        }else{
               System.out.println("No Item found");
               continue;
            }
               myObj.issue();
               myObj.returnItem();
               myObj.displayInfo();
               
            System.out.println("Thank you! Enjoy reading and have a great day ahead!");
            in.close();
            System.exit(0);
	}
	}
}
