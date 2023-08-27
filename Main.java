package Petmanagement;
import java.util.Scanner;
import java.util.List;
import java.sql.SQLException;

public class Main {
	
	public static void main(String[] args) throws SQLException {
		System.out.println(DatabaseConnection.getConnection());
		Scanner scanner=new Scanner(System.in);
		DAO petDAO = new DAO();
		
		 
		while(true) {
		System.out.println("choose from below list");
		System.out.println("1. INSERT");
		System.out.println("2. SELECT");
		System.out.println("3. UPDATE");
		System.out.println("4. DELETE");
		System.out.println("5. Search Pet");
		System.out.println("6. Exit");
		int choice = scanner.nextInt();
		
		
		switch(choice) {
		case 1: //INSERT
			try {
			System.out.println("Enter ID");
			 int Id= scanner.nextInt();
			 System.out.println("Enter name");
			 String Name= scanner.next();
			 System.out.println("Enter Color");
			 String Color= scanner.next();
			 System.out.println("Enter Price");
			 double Price= scanner.nextDouble();
			 Pet pet= new Pet(Id,Name,Color,Price);
			 petDAO.insertPet(pet);
			}
			catch(InvalidNameException e) {
				System.out.println("Please provide a valid pet name without special characters");
			}
			 break;
			 
		case 2: //SELECT
			List<Pet> pets= petDAO.getPet();
				for(Pet Allpet:pets) {
				  System.out.println("ID "   + Allpet.getPetId()+
			                        ", Name " + Allpet.getPetName()+
					                ", Color "+ Allpet.getPetColor()+
					                "  Price "+ Allpet.getPetPrice());
			}
			break;
		case 3://update
			try {
				
			
			System.out.println("Enter id you want to update");
			int petId=scanner.nextInt();
			System.out.println("Enter new name");
			String petName=scanner.next();
			System.out.println("Enter new color");
			String petColor=scanner.next();
			System.out.println("Enter new price");
			double petPrice=scanner.nextDouble();
			Pet pet= new Pet(petId,petName,petColor,petPrice);
		    petDAO.updatePet(pet);
			}
			catch(UpdateException e) {
				System.out.println("Id cannot be zero or null");
				
			}
		    
			break;
		case 4: //delete
			System.out.println("Enter Id that want to be deleted");
			try {
			int petId1=scanner.nextInt();
			petDAO.deletePet(petId1);
			}
			catch(DeletionException e) {
				System.out.println(e.getMessage());
			}
			
			break;
		case 5: //search
			System.out.println("Enter Id to search");
			int searchId=scanner.nextInt();
			try {
			Pet searchPet=petDAO.Search(searchId);
			System.out.println(" ID " + searchPet.getPetId()+
                    ", Name " + searchPet.getPetName()+
	                ", Color " + searchPet.getPetColor()+
	                " Price " + searchPet.getPetPrice());
			}
			catch(PetNotFoundException e) {
				System.out.println(e.getMessage());
			}
			
			break;
		case 6: 
			System.out.println("Exit");
			System.exit(0);
		default:
			System.out.println("Invalid");
		}
	}
	}

}
