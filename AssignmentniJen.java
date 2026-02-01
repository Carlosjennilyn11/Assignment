import java.util.Scanner;
import java.io.*;

class Contact implements java.io.Serializable {
    String name;
    String number;
}

public class AssignmentniJen {
    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);
        String fn = "data.txt";
        Contact contact = null;

        File file = new File(fn);
        if (file.exists()) {
            try {
                ObjectInputStream a = new ObjectInputStream(new FileInputStream(fn));
                contact = (Contact) a.readObject();
                a.close();
            } catch(Exception e) {
                contact = null;
            }
        }

        int choice = 0;
        do {
            System.out.println("--- CONTACT SYSTEM ---");
            System.out.println("1. Create");
            System.out.println("2. View");
            System.out.println("3. Update");
            System.out.println("4. Delete");
            System.out.println("5. Exit");
            System.out.print("Choose: ");
            
            choice = input.nextInt();
            input.nextLine(); 

            if (choice == 1) {
                if (contact == null) {
                    System.out.print("Name: ");
                    String name = input.nextLine();
                    if(name.trim().isEmpty()) {
                        System.out.println("Name needed!");
                        continue;
                    }
                    System.out.print("Number: ");
                    String num = input.nextLine();
						if(num.trim().isEmpty()) {
							System.out.println("Number needed!");
							continue;
						}
                    contact = new Contact();
                    contact.name = name;
                    contact.number = num;
                    saveContact(fn, contact);
                    System.out.println("Saved!");
                } else {
                    System.out.println("Already have");
                }
            }
            else if (choice == 2) {
                if (contact != null) {
                    System.out.println("Name\t\tNumber");  
                    System.out.println("----------------------------");
                    System.out.println(contact.name + "\t\t" + contact.number); 
                } else {
                    System.out.println("Empty");
                }
            }
            else if (choice == 3) {
                if (contact != null) {
                    System.out.print("New Name: ");
                    String newName = input.nextLine();
						if(newName.trim().isEmpty()) {
							System.out.println("Name!");
							continue;
						}
                    System.out.print("New Number: ");
                    String newNum = input.nextLine();
                    if(newNum.trim().isEmpty()) {
                        System.out.println("Number only!");
                        continue;
                    }
                    contact.name = newName;
                    contact.number = newNum;
                    saveContact(fn, contact);
                    System.out.println("Updated!");
                } else {
                    System.out.println("Empty contact");
                }
            }
            else if (choice == 4) {
                contact = null;
                new FileWriter(fn).close();
                System.out.println("Deleted!");
            }
        } while (choice != 5);
        
        input.close();
    }

    public static void saveContact(String filename, Contact c) throws Exception {
        if(c != null) {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(c);
            out.close();
        }
    }
}
