package hospital.businessentity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import hospital.annotation.Label;

public class CustomerPancreas implements Serializable {

	private static final long serialVersionUID = 1L;
	// Нойр булчирхайн биж хам шинж
	@Label(label = "pancreasPain", labelType = "p", fieldType = "boolean")
	public boolean pancreasPain;
	// Шүлс савирна
	@Label(label = "spitTrickle", labelType = "p", fieldType = "boolean")
	public boolean spitTrickle;
	// Бөөлжис цутгана
	@Label(label = "wantThrowUp", labelType = "p", fieldType = "boolean")
	public boolean wantThrowUp;
	// Бөөлжинө
	@Label(label = "throwUp", labelType = "p", fieldType = "boolean")
	public boolean throwUp;
	// Гэдэс дүүрэх
	@Label(label = "hoove", labelType = "p", fieldType = "boolean")
	public boolean hoove;

	// Дотор муухайрна
	@Label(label = "nausea", labelType = "p", fieldType = "boolean")
	public boolean nausea;
	// Суулгалт
	@Label(label = "diarrhoea ", labelType = "p", fieldType = "boolean")
	public boolean diarrhoea;
	// Боловсроогүй өөхтэй
	@Label(label = "vealyLardy", labelType = "p", fieldType = "boolean")
	public boolean vealyLardy;
	// Өөхний дуслууд илэрсэн
	@Label(label = "bubbleLardy", labelType = "p", fieldType = "boolean")
	public boolean bubbleLardy;
	// Тосорхог гялалзсан наалдацтай
	@Label(label = "flashLardy", labelType = "p", fieldType = "boolean")
	public boolean flashLardy;
	// Халууралт
	@Label(label = "fever", labelType = "p")
	public String fever;
	// Тахикарди Зүрхний цохилт
	@Label(label = "heartBeat", labelType = "p")
	public String heartBeat;
	// Гипотензи Даралт
	@Label(label = "hypotenuse", labelType = "p")
	public String hypotenuse;
	// Тахипноэ Амьсгаадалт
	@Label(label = "anhelation", labelType = "p")
	public String anhelation;
	// Өвдөлт байгаа эсэх:
	@Label(label = "havePain", labelType = "m", fieldType = "select", answers = { "no", "critcal", "hard", "stick" })
	public int havePain;
	// Шинж чанар:
	@Label(label = "painType", labelType = "m", fieldType = "select", answers = { "no", "occurrence", "surrounded",
			"twisted" })
	public int painType;
	// Архи хэрэглэсэн эсэх:
	@Label(label = "usedAlchohol", labelType = "m", fieldType = "select", answers = { "no", "week", "twoWeek",
			"multiWeek" })
	public int usedAlchohol;
	// Хэт өтгөн
	@Label(label = "thickFood", labelType = "m", fieldType = "boolean")
	public boolean thickFood;
	// Хуурсан
	@Label(label = "grilledFood", labelType = "m", fieldType = "boolean")
	public boolean grilledFood;
	// Шарсан
	@Label(label = "friedFood", labelType = "m", fieldType = "boolean")
	public boolean friedFood;
	// Өөх тостой
	@Label(label = "lardyFood", labelType = "m", fieldType = "boolean")
	public boolean lardyFood;
	// Ердийн
	@Label(label = "normalFood", labelType = "m", fieldType = "boolean")
	public boolean normalFood;

	// Куллений шинж :
	@Label(label = "kullien", labelType = "c", fieldType = "select", answers = { "", "found", "notfound" })
	public int kullien;
	// Грей-Турнерийн шинж:
	@Label(label = "grayTurner", labelType = "c", fieldType = "select", answers = { "", "found", "notfound" })
	public int grayTurner;
	// Фоксийн шинж:
	@Label(label = "fox", labelType = "c", fieldType = "select", answers = { "", "found", "notfound" })
	public int fox;
	// Кёртегийн шинж :
	@Label(label = "cyorteg", labelType = "c", fieldType = "select", answers = { "", "found", "notfound" })
	public int cyorteg;
	// Воскресенскийн шинж :
	@Label(label = "vosk", labelType = "c", fieldType = "select", answers = { "", "found", "notfound" })
	public int vosk;
	// Мей-Робсоны шинж:
	@Label(label = "meirobs", labelType = "c", fieldType = "select", answers = { "", "found", "notfound" })
	public int meirobs;
	// Раздолскийн шинж:
	@Label(label = "razdolsk", labelType = "c", fieldType = "select", answers = { "", "found", "notfound" })
	public int razdolsk;
	// Губергрицийн шинж:
	@Label(label = "guberg", labelType = "c", fieldType = "select", answers = { "", "found", "notfound" })
	public int guberg;
	// Дежардений шинж:
	@Label(label = "dejardin", labelType = "c", fieldType = "select", answers = { "", "found", "notfound" })
	public int dejardin;
	// Качагийн цэг:
	@Label(label = "kachag", labelType = "c", fieldType = "select", answers = { "", "found", "notfound" })
	public int kachag;
	// Шоффарын гурвалжин:
	@Label(label = "schofar", labelType = "c", fieldType = "select", answers = { "", "found", "notfound" })
	public int schofar;

	@Label(label = "pancreasRateText", labelType = "c")
	public String pancreasRateText;

	@Label(label = "advice", labelType = "c")
	public String advice;

	public boolean isPancreasPain() {
		return pancreasPain;
	}

	public void setPancreasPain(boolean pancreasPain) {
		this.pancreasPain = pancreasPain;
	}

	public boolean isSpitTrickle() {
		return spitTrickle;
	}

	public void setSpitTrickle(boolean spitTrickle) {
		this.spitTrickle = spitTrickle;
	}

	public boolean isWantThrowUp() {
		return wantThrowUp;
	}

	public void setWantThrowUp(boolean wantThrowUp) {
		this.wantThrowUp = wantThrowUp;
	}

	public boolean isThrowUp() {
		return throwUp;
	}

	public void setThrowUp(boolean throwUp) {
		this.throwUp = throwUp;
	}

	public boolean isHoove() {
		return hoove;
	}

	public void setHoove(boolean hoove) {
		this.hoove = hoove;
	}

	public boolean isNausea() {
		return nausea;
	}

	public void setNausea(boolean nausea) {
		this.nausea = nausea;
	}

	public boolean isDiarrhoea() {
		return diarrhoea;
	}

	public void setDiarrhoea(boolean diarrhoea) {
		this.diarrhoea = diarrhoea;
	}

	public boolean isVealyLardy() {
		return vealyLardy;
	}

	public void setVealyLardy(boolean vealyLardy) {
		this.vealyLardy = vealyLardy;
	}

	public boolean isBubbleLardy() {
		return bubbleLardy;
	}

	public void setBubbleLardy(boolean bubbleLardy) {
		this.bubbleLardy = bubbleLardy;
	}

	public boolean isFlashLardy() {
		return flashLardy;
	}

	public void setFlashLardy(boolean flashLardy) {
		this.flashLardy = flashLardy;
	}

	public String getFever() {
		return fever;
	}

	public void setFever(String fever) {
		this.fever = fever;
	}

	public String getHeartBeat() {
		return heartBeat;
	}

	public void setHeartBeat(String heartBeat) {
		this.heartBeat = heartBeat;
	}

	public String getHypotenuse() {
		return hypotenuse;
	}

	public void setHypotenuse(String hypotenuse) {
		this.hypotenuse = hypotenuse;
	}

	public String getAnhelation() {
		return anhelation;
	}

	public void setAnhelation(String anhelation) {
		this.anhelation = anhelation;
	}

	public int getHavePain() {
		return havePain;
	}

	public void setHavePain(int havePain) {
		this.havePain = havePain;
	}

	public int getPainType() {
		return painType;
	}

	public void setPainType(int painType) {
		this.painType = painType;
	}

	public int getUsedAlchohol() {
		return usedAlchohol;
	}

	public void setUsedAlchohol(int usedAlchohol) {
		this.usedAlchohol = usedAlchohol;
	}

	public boolean isThickFood() {
		return thickFood;
	}

	public void setThickFood(boolean thickFood) {
		this.thickFood = thickFood;
	}

	public boolean isGrilledFood() {
		return grilledFood;
	}

	public void setGrilledFood(boolean grilledFood) {
		this.grilledFood = grilledFood;
	}

	public boolean isFriedFood() {
		return friedFood;
	}

	public void setFriedFood(boolean friedFood) {
		this.friedFood = friedFood;
	}

	public boolean isLardyFood() {
		return lardyFood;
	}

	public void setLardyFood(boolean lardyFood) {
		this.lardyFood = lardyFood;
	}

	public boolean isNormalFood() {
		return normalFood;
	}

	public void setNormalFood(boolean normalFood) {
		this.normalFood = normalFood;
	}

	public int getKullien() {
		return kullien;
	}

	public void setKullien(int kullien) {
		this.kullien = kullien;
	}

	public int getGrayTurner() {
		return grayTurner;
	}

	public void setGrayTurner(int grayTurner) {
		this.grayTurner = grayTurner;
	}

	public int getFox() {
		return fox;
	}

	public void setFox(int fox) {
		this.fox = fox;
	}

	public int getCyorteg() {
		return cyorteg;
	}

	public void setCyorteg(int cyorteg) {
		this.cyorteg = cyorteg;
	}

	public int getVosk() {
		return vosk;
	}

	public void setVosk(int vosk) {
		this.vosk = vosk;
	}

	public int getMeirobs() {
		return meirobs;
	}

	public void setMeirobs(int meirobs) {
		this.meirobs = meirobs;
	}

	public int getRazdolsk() {
		return razdolsk;
	}

	public void setRazdolsk(int razdolsk) {
		this.razdolsk = razdolsk;
	}

	public int getGuberg() {
		return guberg;
	}

	public void setGuberg(int guberg) {
		this.guberg = guberg;
	}

	public int getDejardin() {
		return dejardin;
	}

	public void setDejardin(int dejardin) {
		this.dejardin = dejardin;
	}

	public int getKachag() {
		return kachag;
	}

	public void setKachag(int kachag) {
		this.kachag = kachag;
	}

	public int getSchofar() {
		return schofar;
	}

	public void setSchofar(int schofar) {
		this.schofar = schofar;
	}

	public String getPancreasRateText() {
		return pancreasRateText;
	}

	public void setPancreasRateText(String pancreasRateText) {
		this.pancreasRateText = pancreasRateText;
	}

	public String getAdvice() {
		return advice;
	}

	public void setAdvice(String advice) {
		this.advice = advice;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}