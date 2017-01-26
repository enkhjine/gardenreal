package garden.businessentity;

import java.math.BigDecimal;

import garden.entity.GardenUser;

public class LoggedInfo {
	
	private GardenUser gardenUser;
	
	public LoggedInfo(){
		gardenUser = new GardenUser();
		gardenUser.setPkId(BigDecimal.ZERO);
	}

	public GardenUser getGardenUser() {
		return gardenUser;
	}
	
	public void setGardenUser(GardenUser gardenUser) {
		this.gardenUser = gardenUser;
	}
}
