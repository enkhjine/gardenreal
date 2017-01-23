package hospital.businessentity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Transient;

import hospital.annotation.Label;

public class CustomerSkin {

	private static final long serialVersionUID = 1L;

	/*
	 * Загатнана 0 - үгүй 1 - тийм
	 */
	@Label(label = "itching", labelType = "p", fieldType = "select", answers = { "no", "yes" })
	public int itching;

	/*
	 * Халуу оргино 0 - үгүй 1 - тийм
	 */
	@Label(label = "burning", labelType = "p", fieldType = "select", answers = { "no", "yes" })
	public int burning;

	/*
	 * Хорсоно 0 - үгүй 1 - тийм
	 */
	@Label(label = "stinging", labelType = "p", fieldType = "select", answers = { "no", "yes" })
	public int stinging;

	/*
	 * Чимчигнэнэ 0 - үгүй 1 - тийм
	 */
	@Label(label = "tingling", labelType = "p", fieldType = "select", answers = { "no", "yes" })
	public int tingling;

	/*
	 * Дарвигнана 0 - үгүй 1 - тийм
	 */
	@Label(label = "smarting", labelType = "p", fieldType = "select", answers = { "no", "yes" })
	public int smarting;

	/*
	 * Чинэрнэ 0 - үгүй 1 - тийм
	 */
	@Label(label = "dullPain", labelType = "p", fieldType = "select", answers = { "no", "yes" })
	public int dullPain;

	/*
	 * Ѳвдѳнѳ 0 - үгүй 1 - тийм
	 */
	@Label(label = "painTenderness", labelType = "p", fieldType = "select", answers = { "no", "yes" })
	public int painTenderness;

	/*
	 * Тууралт гарна 0 - үгүй 1 - тийм
	 */
	@Label(label = "skinLesion", labelType = "p", fieldType = "select", answers = { "no", "yes" })
	public int skinLesion;

	/*
	 * Ус, шүүс гарна 0 - үгүй 1 - тийм
	 */
	@Label(label = "oozing", labelType = "p", fieldType = "select", answers = { "no", "yes" })
	public int oozing;

	/*
	 * Цэврүүтнэ 0 - үгүй 1 - тийм
	 */
	@Label(label = "vesicleBullae", labelType = "p", fieldType = "select", answers = { "no", "yes" })
	public int vesicleBullae;

	/*
	 * Идээ гарна 0 - үгүй 1 - тийм
	 */
	@Label(label = "pus", labelType = "p", fieldType = "select", answers = { "no", "yes" })
	public int pus;

	/*
	 * Хуурайшина 0 - үгүй 1 - тийм
	 */
	@Label(label = "drySkin", labelType = "p", fieldType = "select", answers = { "no", "yes" })
	public int drySkin;

	/*
	 * Хогжруутна 0 - үгүй 1 - тийм
	 */
	@Label(label = "scaling", labelType = "p", fieldType = "select", answers = { "no", "yes" })
	public int scaling;
	
	
	@Label(label="eruption",labelType="c")
	public String eruption;
	
	
	//Толбо
	@Label(label = "spot", labelType = "c", fieldType = "boolean")
	public boolean spot;
	
	//Нөсөө үлдэгдлийн
	@Label(label = "agePigmentRemain", labelType = "c", fieldType = "boolean")
	public boolean agePigmentRemain;
	
	//Хэт нөсөөжлийн
	@Label(label = "agePigmentOver", labelType = "c", fieldType = "boolean")
	public boolean agePigmentOver;	

	//Цусархаг
	@Label(label = "bloody", labelType = "c", fieldType = "boolean")
	public boolean bloody;
	
	
	// Тууралт
	
	//

	
	//Судас өргөслийн
	@Label(label = "veinWide", labelType = "c", fieldType = "boolean")
	public boolean veinWide;
	
	//Хөхөлбий
	@Label(label = "bluish", labelType = "c", fieldType = "boolean")
	public boolean bluish;
	
	//Навчин толбо
	@Label(label = "leafSpot", labelType = "c", fieldType = "boolean")
	public boolean leafSpot;
	
	//Нөсөө алдагдлын
	@Label(label = "agePigmentRemainLeaf", labelType = "c", fieldType = "boolean")
	public boolean agePigmentRemainLeaf;
	
	//Хэт Нөсөөжлийн
	@Label(label = "agePigmentOverLeaf", labelType = "c", fieldType = "boolean")
	public boolean agePigmentOverLeaf;	
	
	//Гүвдрүү
	@Label(label = "hives", labelType = "c", fieldType = "boolean")
	public boolean hives;
	
	//Хөрзөн
	@Label(label = "aclasia", labelType = "c", fieldType = "boolean")
	public boolean aclasia;

	//Зангилаа
	@Label(label = "node", labelType = "c", fieldType = "boolean")
	public boolean node;
	
	//Бэлцрүү
	@Label(label = "bleb", labelType = "c", fieldType = "boolean")
	public boolean bleb;
	
	//Цэврүүнцэр
	@Label(label = "lilBulla", labelType = "c", fieldType = "boolean")
	public boolean lilBulla;
	
	//Цэврүү
	@Label(label = "bulla", labelType = "c", fieldType = "boolean")
	public boolean bulla;
	
	//Идээт цэврүүнцэр
	@Label(label = "pusBulla", labelType = "c", fieldType = "boolean")
	public boolean pusBulla;
	
	//Гэр
	@Label(label = "ger", labelType = "c", fieldType = "boolean")
	public boolean ger;
	
	//Хавдар
	@Label(label = "growth", labelType = "c", fieldType = "boolean")
	public boolean growth;
	
	//Хайрс
	@Label(label = "flake", labelType = "c", fieldType = "boolean")
	public boolean flake;
	
	//Тав
	@Label(label = "scab", labelType = "c", fieldType = "boolean")
	public boolean scab;

	//Зулгархай
	@Label(label = "scratch", labelType = "c", fieldType = "boolean")
	public boolean scratch;
	
	//Ширшил
	@Label(label = "shirshil", labelType = "c", fieldType = "boolean")
	public boolean shirshil;

	//Шалбархай
	@Label(label = "abrasion", labelType = "c", fieldType = "boolean")
	public boolean abrasion;
	
	//Шарх
	@Label(label = "wound", labelType = "c", fieldType = "boolean")
	public boolean wound;
	
	//Цууралт
	@Label(label = "cranny", labelType = "c", fieldType = "boolean")
	public boolean cranny;
	
	//Сорив
	@Label(label = "scar", labelType = "c", fieldType = "boolean")
	public boolean scar;
	
	//Хэт ургалтат
	@Label(label = "overGrowth", labelType = "c", fieldType = "boolean")
	public boolean overGrowth;
	
	//Хатангиршил
	@Label(label = "atrophy", labelType = "c", fieldType = "boolean")
	public boolean scarAtrophy;
	
	//Атроди
	@Label(label = "atrophy", labelType = "c", fieldType = "boolean")
	public boolean atrophy;
	
	//Толбо (II)
	@Label(label = "spotTwo", labelType = "c", fieldType = "boolean")
	public boolean spotTwo;
	
	//Эвэр, чулуужилт
	@Label(label = "fossilization", labelType = "c", fieldType = "boolean")
	public boolean fossilization;
	
	@Label(label="outbreak",labelType="c")
	public String  outbreak;
	/*
	 * 0 - үгүй 1 - тийм
	 */
	@Label(label = "widespread", labelType = "c", fieldType = "boolean")
	public boolean widespread;

	/*
	 * 0 - үгүй 1 - тийм
	 */
	@Label(label = "symmetric", labelType = "c", fieldType = "boolean")
	public boolean symmetric;

	/*
	 * 0 - үгүй 1 - тийм
	 */
	@Label(label = "intertriginous", labelType = "c", fieldType = "boolean")
	public boolean intertriginous;

	/*
	 * 0 - үгүй 1 - тийм
	 */
	@Label(label = "sunExposed", labelType = "c", fieldType = "boolean")
	public boolean sunExposed;
	
	@Label(label = "sunExposed", labelType = "c", fieldType = "boolean")
	public boolean senseFollow;
	
	/*
	 * 0 - үгүй 1 - тийм
	 */
	@Label(label = "isolated", labelType = "c", fieldType = "boolean")
	public boolean isolated;

	/*
	 * 0 - үгүй 1 - тийм
	 */
	@Label(label = "asymmetric", labelType = "c", fieldType = "boolean")
	public boolean asymmetric;

	/*
	 * 0 - үгүй 1 - тийм
	 */
	@Label(label = "extertriginous", labelType = "c", fieldType = "boolean")
	public boolean extertriginous;

	/*
	 * 0 - үгүй 1 - тийм
	 */
	@Label(label = "limbEnds", labelType = "c", fieldType = "boolean")
	public boolean limbEnds;
	
	//байрлал
	@Label(label="location",labelType="c")
	public String location;
	
	/*
	 * 1 - Хуйх 2 - Үс
	 */
	@Label(label = "scalp", labelType = "c", fieldType = "boolean")
	public boolean scalp;

	@Label(label = "hair", labelType = "c", fieldType = "boolean")
	public boolean hair;
	
	@Label(label = "face", labelType = "c", fieldType = "boolean")
	public boolean face;

	@Label(label = "eye", labelType = "c", fieldType = "boolean")
	public boolean eye;
	
	@Label(label = "eyelid", labelType = "c", fieldType = "boolean")
	public boolean eyelid;
	
	@Label(label = "mouth", labelType = "c", fieldType = "boolean")
	public boolean mouth;
	
	@Label(label = "lips", labelType = "c", fieldType = "boolean")
	public boolean lips;
	
	@Label(label = "ear", labelType = "c", fieldType = "boolean")
	public boolean ear;
	
	@Label(label = "cheek", labelType = "c", fieldType = "boolean")
	public boolean cheek;
	
	/*
	 * 1 - Хүзүү 2 - Шил
	 */
	@Label(label = "neck", labelType = "c", fieldType = "boolean")
	public boolean neck;
	
	@Label(label = "nape", labelType = "c", fieldType = "boolean")
	public boolean nape;

	/*
	 * 1 - Цээж 2 - Нуруу 3 - Хѳхний толгой
	 */
	@Label(label = "body", labelType = "c", fieldType = "boolean")
	public boolean body;

	@Label(label = "chest", labelType = "c", fieldType = "boolean")
	public boolean chest;
	
	@Label(label = "back", labelType = "c", fieldType = "boolean")
	public boolean back;
	
	@Label(label = "nipples", labelType = "c", fieldType = "boolean")
	public boolean nipples;
	
	/*
	 * 1 - Гар 2 - Буглага 3 - Суга
	 */
	@Label(label = "arm", labelType = "c", fieldType = "boolean")
	public boolean arm;

	@Label(label = "shoulder", labelType = "c", fieldType = "boolean")
	public boolean shoulder;
	
	@Label(label = "armpit", labelType = "c", fieldType = "boolean")
	public boolean armpit;
	
	/*
	 * 1 - Сарвуу 2 - Хумс 3 - Гарын салаа 4 - Алга
	 */
	@Label(label = "hand", labelType = "c", fieldType = "boolean")
	public boolean hand;

	@Label(label = "nail", labelType = "c", fieldType = "boolean")
	public boolean nail;
	
	@Label(label = "handFork", labelType = "c", fieldType = "boolean")
	public boolean handFork;
	
	@Label(label = "palm", labelType = "c", fieldType = "boolean")
	public boolean palm;
	
	/*
	 * 1 - Бэлэг эрхтэн
	 */
	@Label(label = "anogenital", labelType = "c", fieldType = "boolean")
	public boolean anogenital;
	
	/*
	 * 1 - Хѳл 2 - Гуяа 3 - Цавь 4 - Шилбэ
	 */
	@Label(label = "leg", labelType = "c", fieldType = "boolean")
	public boolean leg;

	@Label(label = "ham", labelType = "c", fieldType = "boolean")
	public boolean ham;
	
	@Label(label = "groin", labelType = "c", fieldType = "boolean")
	public boolean groin;
	
	@Label(label = "shin", labelType = "c", fieldType = "boolean")
	public boolean shin;
	
	/*
	 * 1 - Тавхай 2 - Хумс 3 - Хурууны салаа 4 - Ул
	 */
	@Label(label = "foot", labelType = "c", fieldType = "boolean")
	public boolean foot;
	
	@Label(label = "footNail", labelType = "c", fieldType = "boolean")
	public boolean footNail;
	
	@Label(label = "footFork", labelType = "c", fieldType = "boolean")
	public boolean footFork;
	
	@Label(label = "sole", labelType = "c", fieldType = "boolean")
	public boolean sole;
	
	@Label(label = "skinStressPainText", labelType = "p")
	public String skinStressPainText;

	@Label(label = "skinRateText", labelType = "p")
	public String skinRateText;

	@Label(label = "when", labelType = "m")
	public String when;

	@Label(label = "thisTime", labelType = "m")
	public String thisTime;

	@Label(label = "thisTimeReason", labelType = "m")
	public String thisTimeReason;

	@Label(label = "changed", labelType = "m")
	public String changed;

	@Label(label = "usedThings", labelType = "m")
	public String usedThings;

	@Label(label = "advice", labelType = "p")
	public String advice;
	
	@Label(label="other",labelType="c")
	public String cotherPain;
	
	@Transient
	public boolean tmpitching;
	@Transient
	public boolean tmpburning;
	@Transient
	public boolean tmpstinging;
	@Transient
	public boolean tmptingling;
	@Transient
	public boolean tmpsmarting;
	@Transient
	public boolean tmpdull_pain;
	@Transient
	public boolean tmppain_tenderness;
	@Transient
	public boolean tmpskin_lesion;
	@Transient
	public boolean tmpoozing;
	@Transient
	public boolean tmpvesicle_bullae;
	@Transient
	public boolean tmppus;
	@Transient
	public boolean tmpdry_skin;
	@Transient
	public boolean tmpscaling;

	public CustomerSkin() {
		super();
	}

	public int getItching() {
		return itching;
	}

	public void setItching(int itching) {
		this.itching = itching;
	}

	public int getBurning() {
		return burning;
	}

	public void setBurning(int burning) {
		this.burning = burning;
	}

	public int getStinging() {
		return stinging;
	}

	public void setStinging(int stinging) {
		this.stinging = stinging;
	}

	public int getTingling() {
		return tingling;
	}

	public void setTingling(int tingling) {
		this.tingling = tingling;
	}

	public int getSmarting() {
		return smarting;
	}

	public void setSmarting(int smarting) {
		this.smarting = smarting;
	}

	public int getDullPain() {
		return dullPain;
	}

	public void setDullPain(int dullPain) {
		this.dullPain = dullPain;
	}

	public int getPainTenderness() {
		return painTenderness;
	}

	public void setPainTenderness(int painTenderness) {
		this.painTenderness = painTenderness;
	}

	public int getSkinLesion() {
		return skinLesion;
	}

	public void setSkinLesion(int skinLesion) {
		this.skinLesion = skinLesion;
	}

	public int getOozing() {
		return oozing;
	}

	public void setOozing(int oozing) {
		this.oozing = oozing;
	}

	public int getVesicleBullae() {
		return vesicleBullae;
	}

	public void setVesicleBullae(int vesicleBullae) {
		this.vesicleBullae = vesicleBullae;
	}

	public int getPus() {
		return pus;
	}

	public void setPus(int pus) {
		this.pus = pus;
	}

	public int getDrySkin() {
		return drySkin;
	}

	public void setDrySkin(int drySkin) {
		this.drySkin = drySkin;
	}

	public int getScaling() {
		return scaling;
	}

	public void setScaling(int scaling) {
		this.scaling = scaling;
	}

	public boolean getScalp() {
		return scalp;
	}

	public void setScalp(boolean scalp) {
		this.scalp = scalp;
	}

	public boolean getFace() {
		return face;
	}

	public void setFace(boolean face) {
		this.face = face;
	}

	public boolean getNeck() {
		return neck;
	}

	public void setNeck(boolean neck) {
		this.neck = neck;
	}

	public boolean getBody() {
		return body;
	}

	public void setBody(boolean body) {
		this.body = body;
	}

	public boolean getArm() {
		return arm;
	}

	public void setArm(boolean arm) {
		this.arm = arm;
	}

	public boolean getHand() {
		return hand;
	}

	public void setHand(boolean hand) {
		this.hand = hand;
	}

	public boolean getAnogenital() {
		return anogenital;
	}

	public void setAnogenital(boolean anogenital) {
		this.anogenital = anogenital;
	}

	public boolean getLeg() {
		return leg;
	}

	public void setLeg(boolean leg) {
		this.leg = leg;
	}

	public boolean getFoot() {
		return foot;
	}

	public void setFoot(boolean foot) {
		this.foot = foot;
	}

	public String getWhen() {
		return when;
	}

	public void setWhen(String when) {
		this.when = when;
	}

	public String getThisTime() {
		return thisTime;
	}

	public void setThisTime(String thisTime) {
		this.thisTime = thisTime;
	}

	public String getThisTimeReason() {
		return thisTimeReason;
	}

	public void setThisTimeReason(String thisTimeReason) {
		this.thisTimeReason = thisTimeReason;
	}

	public String getChanged() {
		return changed;
	}

	public void setChanged(String changed) {
		this.changed = changed;
	}

	public String getUsedThings() {
		return usedThings;
	}

	public void setUsedThings(String usedThings) {
		this.usedThings = usedThings;
	}
	
	public boolean getWidespread() {
		return widespread;
	}

	public void setWidespread(boolean widespread) {
		this.widespread = widespread;
	}

	public boolean getSymmetric() {
		return symmetric;
	}

	public void setSymmetric(boolean symmetric) {
		this.symmetric = symmetric;
	}

	public boolean getIntertriginous() {
		return intertriginous;
	}

	public void setIntertriginous(boolean intertriginous) {
		this.intertriginous = intertriginous;
	}

	public boolean getSunExposed() {
		return sunExposed;
	}

	public void setSunExposed(boolean sunExposed) {
		this.sunExposed = sunExposed;
	}

	public boolean getIsolated() {
		return isolated;
	}

	public void setIsolated(boolean isolated) {
		this.isolated = isolated;
	}

	public boolean getAsymmetric() {
		return asymmetric;
	}

	public void setAsymmetric(boolean asymmetric) {
		this.asymmetric = asymmetric;
	}

	public boolean getExtertriginous() {
		return extertriginous;
	}

	public void setExtertriginous(boolean extertriginous) {
		this.extertriginous = extertriginous;
	}

	public boolean getLimbEnds() {
		return limbEnds;
	}

	public void setLimbEnds(boolean limbEnds) {
		this.limbEnds = limbEnds;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean isTmpitching() {
		tmpitching = getItching() == 1;
		return tmpitching;
	}

	public void setTmpitching(boolean tmpitching) {
		setItching(0);
		if (tmpitching)
			setItching(1);
		this.tmpitching = tmpitching;
	}

	public boolean isTmpburning() {
		tmpburning = getBurning() == 1;
		return tmpburning;
	}

	public void setTmpburning(boolean tmpburning) {
		setBurning(0);
		if (tmpburning)
			setBurning(1);
		this.tmpburning = tmpburning;
	}

	public boolean isTmpstinging() {
		tmpstinging = getStinging() == 1;
		return tmpstinging;
	}

	public void setTmpstinging(boolean tmpstinging) {
		setStinging(0);
		if (tmpstinging)
			setStinging(1);
		this.tmpstinging = tmpstinging;
	}

	public boolean isTmptingling() {
		tmptingling = getTingling() == 1;
		return tmptingling;
	}

	public void setTmptingling(boolean tmptingling) {
		setTingling(0);
		if (tmptingling)
			setTingling(1);
		this.tmptingling = tmptingling;
	}

	public boolean isTmpsmarting() {
		tmpsmarting = getSmarting() == 1;
		return tmpsmarting;
	}

	public void setTmpsmarting(boolean tmpsmarting) {
		setSmarting(0);
		if (tmpsmarting)
			setSmarting(1);
		this.tmpsmarting = tmpsmarting;
	}

	public boolean isTmpdull_pain() {
		tmpdull_pain = getDullPain() == 1;
		return tmpdull_pain;
	}

	public void setTmpdull_pain(boolean tmpdull_pain) {
		setDullPain(0);
		if (tmpdull_pain)
			setDullPain(1);
		this.tmpdull_pain = tmpdull_pain;
	}

	public boolean isTmppain_tenderness() {
		tmppain_tenderness = getPainTenderness() == 1;
		return tmppain_tenderness;
	}

	public void setTmppain_tenderness(boolean tmppain_tenderness) {
		setPainTenderness(0);
		if (tmppain_tenderness)
			setPainTenderness(1);
		this.tmppain_tenderness = tmppain_tenderness;
	}

	public boolean isTmpskin_lesion() {
		tmpskin_lesion = getSkinLesion() == 1;
		return tmpskin_lesion;
	}

	public void setTmpskin_lesion(boolean tmpskin_lesion) {
		setSkinLesion(0);
		if (tmpskin_lesion)
			setSkinLesion(1);
		this.tmpskin_lesion = tmpskin_lesion;
	}

	public boolean isTmpoozing() {
		tmpoozing = getOozing() == 1;
		return tmpoozing;
	}

	public void setTmpoozing(boolean tmpoozing) {
		setOozing(0);
		if (tmpoozing)
			setOozing(1);
		this.tmpoozing = tmpoozing;
	}

	public boolean isTmpvesicle_bullae() {
		tmpvesicle_bullae = getVesicleBullae() == 1;
		return tmpvesicle_bullae;
	}

	public void setTmpvesicle_bullae(boolean tmpvesicle_bullae) {
		setVesicleBullae(0);
		if (tmpvesicle_bullae)
			setVesicleBullae(1);
		this.tmpvesicle_bullae = tmpvesicle_bullae;
	}

	public boolean isTmppus() {
		tmppus = getPus() == 1;
		return tmppus;
	}

	public void setTmppus(boolean tmppus) {
		setPus(0);
		if (tmppus)
			setPus(1);
		this.tmppus = tmppus;
	}

	public boolean isTmpdry_skin() {
		tmpdry_skin = getDrySkin() == 1;
		return tmpdry_skin;
	}

	public void setTmpdry_skin(boolean tmpdry_skin) {
		setDrySkin(0);
		if (tmpdry_skin)
			setDrySkin(1);
		this.tmpdry_skin = tmpdry_skin;
	}

	public boolean isTmpscaling() {
		tmpscaling = getScaling() == 1;
		return tmpscaling;
	}

	public void setTmpscaling(boolean tmpscaling) {
		setScaling(0);
		if (tmpscaling)
			setScaling(1);
		this.tmpscaling = tmpscaling;
	}

	public String getSkinStressPainText() {
		return skinStressPainText;
	}

	public void setSkinStressPainText(String skinStressPainText) {
		this.skinStressPainText = skinStressPainText;
	}

	public String getSkinRateText() {
		return skinRateText;
	}

	public void setSkinRateText(String skinRateText) {
		this.skinRateText = skinRateText;
	}
	
	public String getAdvice() {
		return advice;
	}
	public void setAdvice(String advice) {
		this.advice = advice;
	}

	public boolean isHair() {
		return hair;
	}

	public void setHair(boolean hair) {
		this.hair = hair;
	}

	public boolean isEye() {
		return eye;
	}

	public void setEye(boolean eye) {
		this.eye = eye;
	}

	public boolean isEyelid() {
		return eyelid;
	}

	public void setEyelid(boolean eyelid) {
		this.eyelid = eyelid;
	}

	public boolean isMouth() {
		return mouth;
	}

	public void setMouth(boolean mouth) {
		this.mouth = mouth;
	}

	public boolean isLips() {
		return lips;
	}

	public void setLips(boolean lips) {
		this.lips = lips;
	}

	public boolean isEar() {
		return ear;
	}

	public void setEar(boolean ear) {
		this.ear = ear;
	}

	public boolean isCheek() {
		return cheek;
	}

	public void setCheek(boolean cheek) {
		this.cheek = cheek;
	}

	public boolean isNape() {
		return nape;
	}

	public void setNape(boolean nape) {
		this.nape = nape;
	}

	public boolean isChest() {
		return chest;
	}

	public void setChest(boolean chest) {
		this.chest = chest;
	}

	public boolean isBack() {
		return back;
	}

	public void setBack(boolean back) {
		this.back = back;
	}

	public boolean isNipples() {
		return nipples;
	}

	public void setNipples(boolean nipples) {
		this.nipples = nipples;
	}

	public boolean isShoulder() {
		return shoulder;
	}

	public void setShoulder(boolean shoulder) {
		this.shoulder = shoulder;
	}

	public boolean isArmpit() {
		return armpit;
	}

	public void setArmpit(boolean armpit) {
		this.armpit = armpit;
	}

	public boolean isNail() {
		return nail;
	}

	public void setNail(boolean nail) {
		this.nail = nail;
	}

	public boolean isHandFork() {
		return handFork;
	}

	public void setHandFork(boolean handFork) {
		this.handFork = handFork;
	}

	public boolean isPalm() {
		return palm;
	}

	public void setPalm(boolean palm) {
		this.palm = palm;
	}

	public boolean isHam() {
		return ham;
	}

	public void setHam(boolean ham) {
		this.ham = ham;
	}

	public boolean isGroin() {
		return groin;
	}

	public void setGroin(boolean groin) {
		this.groin = groin;
	}

	public boolean isShin() {
		return shin;
	}

	public void setShin(boolean shin) {
		this.shin = shin;
	}

	public boolean isFootNail() {
		return footNail;
	}

	public void setFootNail(boolean footNail) {
		this.footNail = footNail;
	}

	public boolean isFootFork() {
		return footFork;
	}

	public void setFootFork(boolean footFork) {
		this.footFork = footFork;
	}

	public boolean isSole() {
		return sole;
	}

	public void setSole(boolean sole) {
		this.sole = sole;
	}

	public boolean isSpot() {
		return spot;
	}

	public void setSpot(boolean spot) {
		this.spot = spot;
		if(!spot){
			setAgePigmentRemain(spot);
			setAgePigmentOver(spot);
			setBloody(spot);
			setBluish(spot);
			setVeinWide(spot);
		}
	}

	public boolean isAgePigmentRemain() {
		return agePigmentRemain;
	}

	public void setAgePigmentRemain(boolean agePigmentRemain) {
		this.agePigmentRemain = agePigmentRemain;
	}

	public boolean isAgePigmentOver() {
		return agePigmentOver;
	}

	public void setAgePigmentOver(boolean agePigmentOver) {
		this.agePigmentOver = agePigmentOver;
	}

	public boolean isBloody() {
		return bloody;
	}

	public void setBloody(boolean bloody) {
		this.bloody = bloody;
	}

	public boolean isVeinWide() {
		return veinWide;
	}

	public void setVeinWide(boolean veinWide) {
		this.veinWide = veinWide;
	}

	public boolean isBluish() {
		return bluish;
	}

	public void setBluish(boolean bluish) {
		this.bluish = bluish;
	}

	public boolean isLeafSpot() {
		return leafSpot;
	}

	public void setLeafSpot(boolean leafSpot) {
		this.leafSpot = leafSpot;
		if(!leafSpot){
			setAgePigmentOverLeaf(leafSpot);
			setAgePigmentRemainLeaf(leafSpot);
		}
	}

	public boolean isAgePigmentRemainLeaf() {
		return agePigmentRemainLeaf;
	}

	public void setAgePigmentRemainLeaf(boolean agePigmentRemainLeaf) {
		this.agePigmentRemainLeaf = agePigmentRemainLeaf;
	}

	public boolean isAgePigmentOverLeaf() {
		return agePigmentOverLeaf;
	}

	public void setAgePigmentOverLeaf(boolean agePigmentOverLeaf) {
		this.agePigmentOverLeaf = agePigmentOverLeaf;
	}

	public boolean isHives() {
		return hives;
	}

	public void setHives(boolean hives) {
		this.hives = hives;
	}

	public boolean isAclasia() {
		return aclasia;
	}

	public void setAclasia(boolean aclasia) {
		this.aclasia = aclasia;
	}

	public boolean isNode() {
		return node;
	}

	public void setNode(boolean node) {
		this.node = node;
	}

	public boolean isBleb() {
		return bleb;
	}

	public void setBleb(boolean bleb) {
		this.bleb = bleb;
	}

	public boolean isLilBulla() {
		return lilBulla;
	}

	public void setLilBulla(boolean lilBulla) {
		this.lilBulla = lilBulla;
	}

	public boolean isBulla() {
		return bulla;
	}

	public void setBulla(boolean bulla) {
		this.bulla = bulla;
	}

	public boolean isPusBulla() {
		return pusBulla;
	}

	public void setPusBulla(boolean pusBulla) {
		this.pusBulla = pusBulla;
	}

	public boolean isGer() {
		return ger;
	}

	public void setGer(boolean ger) {
		this.ger = ger;
	}

	public boolean isGrowth() {
		return growth;
	}

	public void setGrowth(boolean growth) {
		this.growth = growth;
	}

	public boolean isFlake() {
		return flake;
	}

	public void setFlake(boolean flake) {
		this.flake = flake;
	}

	public boolean isScab() {
		return scab;
	}

	public void setScab(boolean scab) {
		this.scab = scab;
	}

	public boolean isScratch() {
		return scratch;
	}

	public void setScratch(boolean scratch) {
		this.scratch = scratch;
	}

	public boolean isShirshil() {
		return shirshil;
	}

	public void setShirshil(boolean shirshil) {
		this.shirshil = shirshil;
	}

	public boolean isAbrasion() {
		return abrasion;
	}

	public void setAbrasion(boolean abrasion) {
		this.abrasion = abrasion;
	}

	public boolean isWound() {
		return wound;
	}

	public void setWound(boolean wound) {
		this.wound = wound;
	}

	public boolean isCranny() {
		return cranny;
	}

	public void setCranny(boolean cranny) {
		this.cranny = cranny;
	}

	public boolean isScar() {
		return scar;
	}

	public void setScar(boolean scar) {
		this.scar = scar;
		if(!scar){
			setOverGrowth(scar);
			setScarAtrophy(scar);
		}
	}

	public boolean isOverGrowth() {
		return overGrowth;
	}

	public void setOverGrowth(boolean overGrowth) {
		this.overGrowth = overGrowth;
	}

	public boolean isScarAtrophy() {
		return scarAtrophy;
	}

	public void setScarAtrophy(boolean scarAtrophy) {
		this.scarAtrophy = scarAtrophy;
	}

	public boolean isAtrophy() {
		return atrophy;
	}

	public void setAtrophy(boolean atrophy) {
		this.atrophy = atrophy;
	}

	public boolean isSpotTwo() {
		return spotTwo;
	}

	public void setSpotTwo(boolean spotTwo) {
		this.spotTwo = spotTwo;
	}

	public boolean isFossilization() {
		return fossilization;
	}

	public void setFossilization(boolean fossilization) {
		this.fossilization = fossilization;
	}

	public boolean isSenseFollow() {
		return senseFollow;
	}

	public void setSenseFollow(boolean senseFollow) {
		this.senseFollow = senseFollow;
	}

	public String getEruption() {
		return eruption;
	}

	public void setEruption(String eruption) {
		this.eruption = eruption;
	}

	public String getOutbreak() {
		return outbreak;
	}

	public void setOutbreak(String outbreak) {
		this.outbreak = outbreak;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCotherPain() {
		return cotherPain;
	}

	public void setCotherPain(String cotherPain) {
		this.cotherPain = cotherPain;
	}
	
	
	
}