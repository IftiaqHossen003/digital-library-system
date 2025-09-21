public interface EBook {
    void display();
}
public class RealEBook implements EBook {
    private String fileName;

    public RealEBook(String fileName) {
        this.fileName = fileName;
        loadFromDisk(fileName); 
    }

    private void loadFromDisk(String fileName) {
        System.out.println("Loading book from disk: " + fileName);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void display(){
        System.out.println("📖 Displaying contents of: " + fileName);
    }
}

public class ProxyEBook implements EBook {
    private String fileName;
    private RealEBook realEBook; 

    public ProxyEBook(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {
        if (realEBook == null) {
            System.out.println("Proxy: First access. Creating the real EBook object now.");
            realEBook = new RealEBook(fileName);
        }
        realEBook.display();
    }
}

public class Library {
    public static void main(String[] args) {
        System.out.println("--- Using the Proxy ---");
        EBook ebook = new ProxyEBook("Design Patterns in Java.pdf");

        System.out.println("\nEBook object has been created, but the book has not been loaded yet.");
        System.out.println("Calling display() for the first time...");
    
        ebook.display();

        System.out.println("\nCalling display() for the second time...");

        ebook.display();
    }
}