package hospital.businesslogic;

import hospital.businessentity.LoggedUser;
import hospital.businesslogic.interfaces.ILogicStickyNoteLocal;
import hospital.entity.*;

import javax.ejb.Stateless;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;

import logic.data.CustomHashMap;
import logic.data.Tools;

@Stateless(name = "LogicStickyNote", mappedName = "dentalhospital.businesslogic.LogicStickyNote")
public class LogicStickyNote extends logic.SuperBusinessLogic implements
		hospital.businesslogic.interfaces.ILogicStickyNote,
		ILogicStickyNoteLocal {

	@Resource
	SessionContext sessionContext;

	public LogicStickyNote() {
	}

	public void setDataBaseInfo() throws Exception {
		// dataBaseName = "DentalHospital"
	}

	public List<StickyNoteType> getStickyNoteType(LoggedUser loggedUser)
			throws Exception {

		StringBuilder jpql = new StringBuilder();
		CustomHashMap parameters = new CustomHashMap();
		parameters.put("userPkId", loggedUser.getUser().getPkId());
		jpql.append("SELECT a FROM StickyNoteType a ");
		jpql.append("WHERE a.userPkId = :userPkId ");
		return getByQuery(StickyNoteType.class, jpql.toString(), parameters);
	}

	public void setStickyNoteType(StickyNoteType noteType, LoggedUser loggedUser)
			throws Exception {

		noteType.setUserPkId(loggedUser.getUser().getPkId());
		noteType.setPkId(Tools.newPkId());
		insert(noteType);
	}

}
