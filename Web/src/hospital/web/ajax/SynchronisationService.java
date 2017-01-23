package hospital.web.ajax;

import javax.ejb.Schedule;
import javax.ejb.Stateless;

@Stateless
public class SynchronisationService {

	@Schedule(hour = "23", minute = "30", second = "30", persistent = false)
	public void example() {
		System.out.println(mn.mta.pos.exam.BridgePosAPI.test());
	}
	
}
