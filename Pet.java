package Petmanagement;

public class Pet {
	
		private  int petId;
		private  String petName;
		private  String petColor;
		private  double petPrice;
		
		
		
		public Pet(int petId,String petName,String petColor, double petPrice) {
			this.petId = petId;
			this.petName = petName;
			this.petColor = petColor;
			this.petPrice = petPrice;
		}
		
		
		


		public  int getPetId() {
			return petId;
		}
		public void setPetId(int petId) {
			this.petId = petId;
		}
		public  String getPetName() {
			return petName;
		}
		public void setPetName(String petName) {
			this.petName = petName;
		}
		public  String getPetColor() {
			return petColor;
		}
		public void setPetColor(String petColor) {
			this.petColor = petColor;
		}
		public  double getPetPrice() {
			return petPrice;
		}
		public void setPetPrice(double petPrice) {
			this.petPrice = petPrice;
		}
		@Override
		public String toString() {
			return "Pet [petId=" + petId + ", petName=" + petName + ", petColor=" + petColor + ", petPrice=" + petPrice
					+ "]";
		}

		
		}
