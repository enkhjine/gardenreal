package hospital.businessentity;

import java.io.Serializable;

import hospital.annotation.Label;

public class CustomerKidney  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * tergunPain -Нуруугаар чилж өвднө
	 * */
	@Label(label="tergunPain",labelType="p" ,fieldType="boolean")
	public boolean tergunPain;
	
	/**
	 * edema -Хавагнана
	 * */
	@Label(label="edemaKidney",labelType="p" ,fieldType="boolean")
	public boolean edema;
	
	/**
	 * press -Артерийн даралт ихэснэ
	 * */
	@Label(label="pressKidney",labelType="p" ,fieldType="boolean")
	public boolean press;
	
	/**
	 * urogenital -НШээсний гарц өөрчлөлттэй
	 * */
	@Label(label="urogenitalKidney",labelType="p" ,fieldType="boolean")
	public boolean urogenital;
	
	/**
	 * otherPain - бусад зовиур
	 * */
	@Label(label="otherPain",labelType="p" )
	public String otherPain;
	
	/**
	 * tergunPain -Нуруугаар чилж өвднө
	 * */
	@Label(label="whenPain",labelType="m" )
	public String whenPain;
	
	/**
	 * tergunPain -Нуруугаар чилж өвднө
	 * */
	@Label(label="thisTimeKidneyPain",labelType="m" )
	public String thisTimeKidneyPain;
	
	/**
	 * tergunPain -Нуруугаар чилж өвднө
	 * */
	@Label(label="thisPain",labelType="m" )
	public String thisPain;
	
	
	/**
	 * tergunPain -Нуруугаар чилж өвднө
	 * */
	@Label(label="throatPain",labelType="m" )
	public String throatPain;
	
	/**
	 * tergunPain -Нуруугаар чилж өвднө
	 * */
	@Label(label="thsiJob",labelType="m" )
	public String thsiJob;
	
	/**
	 * tergunPain -Нуруугаар чилж өвднө
	 * */
	@Label(label="thisLife",labelType="m" )
	public String thisLife;
	
	/**
	 * tergunPain -Нуруугаар чилж өвднө
	 * */
	@Label(label="useMedicine",labelType="m")
	public int  useMedicine;
	
	@Label(label="useMedicine",labelType="m" )
	public String  timeMedicine;
	
	@Label(label="resultMedicine",labelType="m")
	public String  resultMedicine;
	
	@Label(label="defualtInspectionKidney",labelType="c" ,fieldType="select",answers ={ "Choose","kidneyHard","kidneyHards","KidneyMedium","KidneyEasy"})
	public int  defualtInspection;
	
	/**
	 * Ухаан  санааны байдал
	 * */
	@Label(label="brainStateKidney",labelType="c" ,fieldType="select" ,answers={ "none","kidneyVast","kidneyStupor","Sopar","Coma"})
	public int brainState;
	
	/**
	 * Арьс, салстын байдал
	 * */
	@Label(label="skinStateKidney",labelType="c" ,fieldType="select", answers={"none","color",	"hyperosmiaRash","pigment",	"temperature","ductility",	"SkintisseEvolution","EdemaKidney",	"SkinhormonicInspection"})
	public boolean skinState;
	
	/**
	 * tergunPain -Нуруугаар чилж өвднө
	 * */
	@Label(label="visuallyPalpationKidney",labelType="c" ,fieldType="boolean")
	public boolean visuallyPalpation ;
	
	
	@Label(label="outbreak",labelType="c",fieldType="select",answers={"nopeeve","peeve" })
	public int visuallyPalpationChoose;
	
	/**
	 * tergunPain -Нуруугаар чилж өвднө
	 * */
	@Label(label="abdominalMuscle",labelType="c" ,fieldType="boolean")
	public boolean abdominalMuscle;
	
	@Label(label="outbreak",labelType="c",fieldType="select", answers={"noexistent","yesexistent"})
	public int abdominalMuscleChoose;
	
	
	/**
	 * tergunPain -Нуруугаар чилж өвднө
	 * */
	@Label(label="abdominalPain",labelType="c" ,fieldType="boolean")
	public boolean abdominalPain;
	
	@Label(label="outbreak",labelType="c",fieldType="select", answers={"none","stomatchTop","middle","bottom"})
	public int abdominalPainChoose ;
	
	/**
	 * tergunPain -Нуруугаар чилж өвднө
	 * */
	@Label(label="abdominalIrritation ",labelType="c" ,fieldType="boolean")
	public boolean abdominalIrritation ;
	
	@Label(label="outbreak",labelType="c",fieldType="select", answers={"noexistent","yesexistent"})
	public int abdominalIrritationChoose;
	
	/**
	 * tergunPain -Нуруугаар чилж өвднө
	 * */
	@Label(label="abdominalTension",labelType="c" ,fieldType="boolean")
	public boolean abdominalTension;
	
	@Label(label="outbreak",labelType="c",fieldType="select", answers={"noexistent","yesexistent"})
	public int abdominalTensionChoose;
	
	/**
	 * tergunPain -Нуруугаар чилж өвднө
	 * */
	@Label(label="abdominalHernia",labelType="c" ,fieldType="boolean")
	public boolean abdominalHernia;
	
	
	@Label(label="outbreak",labelType="c",fieldType="select", answers={"noexistent","yesexistent"})
	public int abdominalHerniaChoose;
	
	/**
	 * tergunPain -Нуруугаар чилж өвднө
	 * */
	@Label(label="abdominalStructure",labelType="c" ,fieldType="boolean")
	public boolean abdominalStructure;
	
	@Label(label="outbreak",labelType="c",fieldType="select", answers={"noexistent","yesexistent"})
	public int abdominalStructureChoose;
	
	/**
	 * tergunPain -Нуруугаар чилж өвднө
	 * */
	@Label(label="kidneyTouch",labelType="c" )
	public String kidneyTouch;
	
	/**
	 * tergunPain -Нуруугаар чилж өвднө
	 * */
	@Label(label="pastyernatskiin",labelType="c" ,fieldType="boolean")
	public boolean pastyernatskiin;	
	
	@Label(label="outbreak",labelType="c",fieldType="select",answers={"plusKidney","minusKidney"})
	public int pastyernatskiinChoose;
	
	@Label(label="skinRateText" ,labelType="p")
	public String rateText;
	
	@Label(label="advice",labelType="p")
	public String advice;
	
	public boolean isTergunPain() {
		return tergunPain;
	}

	public void setTergunPain(boolean tergunPain) {
		this.tergunPain = tergunPain;
	}

	public boolean isEdema() {
		return edema;
	}

	public void setEdema(boolean edema) {
		this.edema = edema;
	}

	public boolean isPress() {
		return press;
	}

	public void setPress(boolean press) {
		this.press = press;
	}

	public boolean isUrogenital() {
		return urogenital;
	}

	public void setUrogenital(boolean urogenital) {
		this.urogenital = urogenital;
	}


	public String getOtherPain() {
		return otherPain;
	}

	public void setOtherPain(String otherPain) {
		this.otherPain = otherPain;
	}

	public String getWhenPain() {
		return whenPain;
	}

	public void setWhenPain(String whenPain) {
		this.whenPain = whenPain;
	}

	public String getThisTimeKidneyPain() {
		return thisTimeKidneyPain;
	}

	public void setThisTimeKidneyPain(String thisTimeKidneyPain) {
		this.thisTimeKidneyPain = thisTimeKidneyPain;
	}

	public String getThisPain() {
		return thisPain;
	}

	public void setThisPain(String thisPain) {
		this.thisPain = thisPain;
	}

	public String getThroatPain() {
		return throatPain;
	}

	public void setThroatPain(String throatPain) {
		this.throatPain = throatPain;
	}

	public String getThsiJob() {
		return thsiJob;
	}

	public void setThsiJob(String thsiJob) {
		this.thsiJob = thsiJob;
	}

	public String getThisLife() {
		return thisLife;
	}

	public void setThisLife(String thisLife) {
		this.thisLife = thisLife;
	}

	public int getUseMedicine() {
		return useMedicine;
	}

	public void setUseMedicine(int useMedicine) {
		this.useMedicine = useMedicine;
	}

	public String getTimeMedicine() {
		return timeMedicine;
	}

	public void setTimeMedicine(String timeMedicine) {
		this.timeMedicine = timeMedicine;
	}

	public String getResultMedicine() {
		return resultMedicine;
	}

	public void setResultMedicine(String resultMedicine) {
		this.resultMedicine = resultMedicine;
	}

	public int getDefualtInspection() {
		return defualtInspection;
	}

	public void setDefualtInspection(int defualtInspection) {
		this.defualtInspection = defualtInspection;
	}

	public int getBrainState() {
		return brainState;
	}

	public void setBrainState(int brainState) {
		this.brainState = brainState;
	}

	public boolean isSkinState() {
		return skinState;
	}

	public void setSkinState(boolean skinState) {
		this.skinState = skinState;
	}

	public boolean isVisuallyPalpation() {
		return visuallyPalpation;
	}

	public void setVisuallyPalpation(boolean visuallyPalpation) {
		this.visuallyPalpation = visuallyPalpation;
	}

	public boolean isAbdominalMuscle() {
		return abdominalMuscle;
	}

	public void setAbdominalMuscle(boolean abdominalMuscle) {
		this.abdominalMuscle = abdominalMuscle;
	}

	public boolean isAbdominalPain() {
		return abdominalPain;
	}

	public void setAbdominalPain(boolean abdominalPain) {
		this.abdominalPain = abdominalPain;
	}

	public boolean isAbdominalIrritation() {
		return abdominalIrritation;
	}

	public void setAbdominalIrritation(boolean abdominalIrritation) {
		this.abdominalIrritation = abdominalIrritation;
	}

	public boolean isAbdominalTension() {
		return abdominalTension;
	}

	public void setAbdominalTension(boolean abdominalTension) {
		this.abdominalTension = abdominalTension;
	}

	public boolean isAbdominalHernia() {
		return abdominalHernia;
	}

	public void setAbdominalHernia(boolean abdominalHernia) {
		this.abdominalHernia = abdominalHernia;
	}

	public boolean isAbdominalStructure() {
		return abdominalStructure;
	}

	public void setAbdominalStructure(boolean abdominalStructure) {
		this.abdominalStructure = abdominalStructure;
	}



	public String getKidneyTouch() {
		return kidneyTouch;
	}

	public void setKidneyTouch(String kidneyTouch) {
		this.kidneyTouch = kidneyTouch;
	}

	public boolean isPastyernatskiin() {
		return pastyernatskiin;
	}

	public void setPastyernatskiin(boolean pastyernatskiin) {
		this.pastyernatskiin = pastyernatskiin;
	}

	public int getVisuallyPalpationChoose() {
		return visuallyPalpationChoose;
	}

	public void setVisuallyPalpationChoose(int visuallyPalpationChoose) {
		this.visuallyPalpationChoose = visuallyPalpationChoose;
	}

	public int getAbdominalMuscleChoose() {
		return abdominalMuscleChoose;
	}

	public void setAbdominalMuscleChoose(int abdominalMuscleChoose) {
		this.abdominalMuscleChoose = abdominalMuscleChoose;
	}

	public int getAbdominalPainChoose() {
		return abdominalPainChoose;
	}

	public void setAbdominalPainChoose(int abdominalPainChoose) {
		this.abdominalPainChoose = abdominalPainChoose;
	}

	public int getAbdominalIrritationChoose() {
		return abdominalIrritationChoose;
	}

	public void setAbdominalIrritationChoose(int abdominalIrritationChoose) {
		this.abdominalIrritationChoose = abdominalIrritationChoose;
	}

	public int getAbdominalTensionChoose() {
		return abdominalTensionChoose;
	}

	public void setAbdominalTensionChoose(int abdominalTensionChoose) {
		this.abdominalTensionChoose = abdominalTensionChoose;
	}

	public int getAbdominalHerniaChoose() {
		return abdominalHerniaChoose;
	}

	public void setAbdominalHerniaChoose(int abdominalHerniaChoose) {
		this.abdominalHerniaChoose = abdominalHerniaChoose;
	}

	public int getAbdominalStructureChoose() {
		return abdominalStructureChoose;
	}

	public void setAbdominalStructureChoose(int abdominalStructureChoose) {
		this.abdominalStructureChoose = abdominalStructureChoose;
	}

	public int getPastyernatskiinChoose() {
		return pastyernatskiinChoose;
	}

	public void setPastyernatskiinChoose(int pastyernatskiinChoose) {
		this.pastyernatskiinChoose = pastyernatskiinChoose;
	}

	public String getRateText() {
		return rateText;
	}

	public void setRateText(String rateText) {
		this.rateText = rateText;
	}

	public String getAdvice() {
		return advice;
	}

	public void setAdvice(String advice) {
		this.advice = advice;
	}


}
