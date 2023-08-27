package Petmanagement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

 	public class DAO {
 		private Connection connection;
	public DAO() 
	{
		connection=DatabaseConnection.getConnection();
	}
	
	public void insertPet(Pet pet) throws InvalidNameException {
		
		String query = "INSERT INTO pet_table(petId,petName,petColor,petPrice) values(?,?,?,?)";
		String petName=pet.getPetName();
		if(!petName.matches("&*$%#@!^-_+=")) {
			throw new InvalidNameException("Invalid pet name.name can ony contain letters ,numbers");
		}
		try (PreparedStatement statement = connection.prepareStatement(query))
		{	
		statement.setInt(1,pet.getPetId());
		statement.setString(2,pet.getPetName());
		statement.setString(3,pet.getPetColor());
		statement.setDouble(4,pet.getPetPrice());
		statement.executeUpdate();

		}
		catch (SQLException e) {
			e.printStackTrace();
			
		}
	}
	
	public List<Pet> getPet(){
		List<Pet> pets=new ArrayList<>();
		String query= "SELECT * FROM pet_table";
		try (
			PreparedStatement statement = connection.prepareStatement(query);
			ResultSet resultSet=statement.executeQuery())
		{
						
			while(resultSet.next()) {
				int petId=resultSet.getInt("petId");
				String petName=resultSet.getString("petName");
				String petColor=resultSet.getString("petColor");
		        double petPrice=resultSet.getDouble("petPrice");
				
				pets.add(new Pet(petId,petName,petColor,petPrice));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
			
		}
		return pets;
	}
	public void updatePet(Pet pet) throws UpdateException {
		String query="UPDATE pet_table SET petName=?,petColor=?,petPrice=? where petId=?";
		try {
			PreparedStatement statement=connection.prepareStatement(query);
			
			statement.setString(1,pet.getPetName());
			statement.setString(2,pet.getPetColor());
			statement.setDouble(3,pet.getPetPrice());
			statement.setInt(4,pet.getPetId());
			statement.executeUpdate();
			if(pet.getPetId() == 777) {
				throw new UpdateException("Update with correct ID");
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
		

	public void deletePet(int petId1) throws DeletionException {
		try {
			String query="DELETE FROM pet_table where petId=?";
			PreparedStatement statement=connection.prepareStatement(query);
			statement.setInt(1, petId1);
			int rowsDeleted=statement.executeUpdate();
			if(rowsDeleted!=1) {
				throw new DeletionException("Failed to delete pet:No pet with id "+petId1+" found");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			
		}
		
		
	}

	public Pet Search(int petId) throws PetNotFoundException {
		String query = "SELECT * from pet_table where petId=?";
		try(PreparedStatement statement = connection.prepareStatement(query))
		{
		statement.setInt(1, petId);
		ResultSet resultSet=statement.executeQuery();
		
		if(resultSet.next())
			{
			int Id= resultSet.getInt("petId");
		    String name=resultSet.getString("petName");
			String color=resultSet.getString("petColor");
			double price=resultSet.getDouble("petPrice");
			return new Pet(Id,name,color,price);
			}
		else {
			throw new PetNotFoundException("pet with Id "+ petId +" not found");
		}
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		}
}
