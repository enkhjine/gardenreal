package hospital.businesslogic.interfaces;

import hospital.businessentity.LoggedUser;
import hospital.entity.StickyNoteType;

import javax.ejb.Local;

import java.util.List;

@Local
public interface ILogicStickyNoteLocal {
	public List<StickyNoteType> getStickyNoteType(LoggedUser loggedUser) throws Exception;
	public void setStickyNoteType(StickyNoteType noteType, LoggedUser loggedUser) throws Exception;
}
