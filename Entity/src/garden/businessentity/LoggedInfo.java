package garden.businessentity;

import java.math.BigDecimal;

import garden.entity.Garden;
import garden.entity.GardenUser;

public class LoggedInfo {
	
	private GardenUser gardenUser;
	private Garden garden;
	
	public LoggedInfo(){
		gardenUser = new GardenUser();
		gardenUser.setPkId(BigDecimal.ZERO);
		
		garden = new Garden();
		garden.setPkId(BigDecimal.ZERO);
	}

	public GardenUser getGardenUser() {
		return gardenUser;
	}
	
	public void setGardenUser(GardenUser gardenUser) {
		this.gardenUser = gardenUser;
	}
	
	public Garden getGarden() {
		return garden;
	}
	
	public void setGarden(Garden garden) {
		this.garden = garden;
	}
}
