package hospital.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "Ultrasound")

public class Ultrasound {

	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;

	@Column(name = "XrayRequestPkId")
	private BigDecimal xrayRequestPkId;

	@Column(name = "LiverReflection")
	private String liverReflection;

	@Column(name = "LiverState")
	private String liverState;

	@Column(name = "LiverRight")
	private String liverRight;

	@Column(name = "LiverDiagonal")
	private String liverDiagonal;

	@Column(name = "LiverLeft")
	private String liverLeft;

	@Column(name = "LiverUpside")
	private String liverUpside;

	@Column(name = "LiverVensize")
	private String liverVensize;

	@Column(name = "LiverVen")
	private String liverVen;

	@Column(name = "LiverGall")
	private String liverGall;

	@Column(name = "LiverSide")
	private String liverSide;

	@Column(name = "LiverDoctor")
	private String liverDoctor;

	@Column(name = "GallSizenumber")
	private String gallSizenumber;

	@Column(name = "GallSize")
	private String gallSize;

	@Column(name = "GallWall")
	private String gallWall;

	@Column(name = "GallWallnumber")
	private String gallWallnumber;

	@Column(name = "GallSidestruct")
	private String gallSidestruct;

	@Column(name = "GallWallstruct")
	private String gallWallstruct;

	@Column(name = "GallGeneral")
	private String gallGeneral;

	@Column(name = "GallNumber")
	private String gallNumber;

	@Column(name = "GallSide")
	private String gallSide;

	@Column(name = "GallDoctor")
	private String gallDoctor;

	@Column(name = "PancreasReflection")
	private String pancreasReflection;

	@Column(name = "PancreasState")
	private String pancreasState;

	@Column(name = "PancreasHead")
	private String pancreasHead;

	@Column(name = "PancreasBody")
	private String pancreasBody;

	@Column(name = "PancreasTail")
	private String pancreasTail;

	@Column(name = "PancreasUpside")
	private String pancreasUpside;

	@Column(name = "PancreasPort")
	private String pancreasPort;

	@Column(name = "PancreasSide")
	private String pancreasSide;

	@Column(name = "PancreasDoctor")
	private String pancreasDoctor;

	@Column(name = "KidneyrightSize")
	private String kidneyrightSize;

	@Column(name = "KidneyrightUpside")
	private String kidneyrightUpside;

	@Column(name = "KidneyrightATC")
	private String kidneyrightATC;

	@Column(name = "KidneyrightSide")
	private String kidneyrightSide;

	@Column(name = "KidneyrightParenhimisize")
	private String kidneyrightParenhimisize;

	@Column(name = "KidneyrightParenhimi")
	private String kidneyrightParenhimi;

	@Column(name = "KidneyrightCup")
	private String kidneyrightCup;

	@Column(name = "KidneyrightATCParenhimi")
	private String kidneyrightATCParenhimi;

	@Column(name = "KidneyrightDoctor")
	private String kidneyrightDoctor;

	@Column(name = "KidneyleftSize")
	private String kidneyleftSize;

	@Column(name = "KidneyleftUpside")
	private String kidneyleftUpside;

	@Column(name = "KidneyleftReflection")
	private String kidneyleftReflection;

	@Column(name = "KidneyleftATC")
	private String kidneyleftATC;

	@Column(name = "KidneyleftSide")
	private String kidneyleftSide;

	@Column(name = "KidneyleftParenhimisize")
	private String kidneyleftParenhimisize;

	@Column(name = "KidneyleftParenhimi")
	private String kidneyleftParenhimi;

	@Column(name = "KidneyleftCup")
	private String kidneyleftCup;

	@Column(name = "KidneyleftATCParenhimi")
	private String kidneyleftATCParenhimi;

	@Column(name = "KidneyleftDoctor")
	private String kidneyleftDoctor;

	@Column(name = "SpleenSizenumber")
	private String spleenSizenumber;

	@Column(name = "SpleenSize")
	private String spleenSize;

	@Column(name = "SpleenState")
	private String spleenState;

	@Column(name = "SpleenReflection")
	private String spleenReflection;

	@Column(name = "SpleenSide")
	private String spleenSide;

	@Column(name = "SpleenVen")
	private String spleenVen;

	@Column(name = "SpleenDoctor")
	private String spleenDoctor;

	@Column(name = "UterusDoctor")
	private String uterusDoctor;

	@Column(name = "UterusEndometr")
	private String uterusEndometr;

	@Column(name = "UterineDoctor")
	private String uterineDoctor;

	@Column(name = "ProstateSizenumber")
	private String prostateSizenumber;

	@Column(name = "ProstateSize")
	private String prostateSize;

	@Column(name = "ProstateState")
	private String prostateState;

	@Column(name = "ProstateSide")
	private String prostateSide;

	@Column(name = "ProstateCorner")
	private String prostateCorner;

	@Column(name = "ProstateDoctor")
	private String prostateDoctor;

	@Column(name = "BladderWall")
	private String bladderWall;

	@Column(name = "BladderSide")
	private String bladderSide;

	@Column(name = "BladderPee")
	private String bladderPee;

	@Column(name = "BladderDoctor")
	private String bladderDoctor;

	@Column(name = "AbdomenLiquid")
	private String abdomenLiquid;

	@Column(name = "AbdomenDoctor")
	private String abdomenDoctor;

	@Column(name = "Createdby")
	private BigDecimal createdby;

	@Column(name = "Savedby")
	private BigDecimal savedby;

	@Column(name = "Updatedby")
	private BigDecimal updatedby;

	@Column(name = "InspectedBy")
	private BigDecimal inspectedBy;

	@Column(name = "UpdatedDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;

	@Column(name = "CreatedDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Column(name = "SavedDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date savedDate;

	@Transient
	private String status;

	public String getLiverRight() {
		return liverRight;
	}

	public void setLiverRight(String liverRight) {
		this.liverRight = liverRight;
	}

	public String getLiverLeft() {
		return liverLeft;
	}

	public void setLiverLeft(String liverLeft) {
		this.liverLeft = liverLeft;
	}

	public String getLiverVensize() {
		return liverVensize;
	}

	public void setLiverVensize(String liverVensize) {
		this.liverVensize = liverVensize;
	}

	public String getLiverDoctor() {
		return liverDoctor;
	}

	public void setLiverDoctor(String liverDoctor) {
		this.liverDoctor = liverDoctor;
	}

	public String getLiverDiagonal() {
		return liverDiagonal;
	}

	public void setLiverDiagonal(String liverDiagonal) {
		this.liverDiagonal = liverDiagonal;
	}

	public String getGallSizenumber() {
		return gallSizenumber;
	}

	public void setGallSizenumber(String gallSizenumber) {
		this.gallSizenumber = gallSizenumber;
	}

	public String getGallWallnumber() {
		return gallWallnumber;
	}

	public void setGallWallnumber(String gallWallnumber) {
		this.gallWallnumber = gallWallnumber;
	}

	public String getGallNumber() {
		return gallNumber;
	}

	public void setGallNumber(String gallNumber) {
		this.gallNumber = gallNumber;
	}

	public String getGallDoctor() {
		return gallDoctor;
	}

	public void setGallDoctor(String gallDoctor) {
		this.gallDoctor = gallDoctor;
	}

	public String getPancreasDoctor() {
		return pancreasDoctor;
	}

	public void setPancreasDoctor(String pancreasDoctor) {
		this.pancreasDoctor = pancreasDoctor;
	}

	public String getPancreasHead() {
		return pancreasHead;
	}

	public void setPancreasHead(String pancreasHead) {
		this.pancreasHead = pancreasHead;
	}

	public String getPancreasBody() {
		return pancreasBody;
	}

	public void setPancreasBody(String pancreasBody) {
		this.pancreasBody = pancreasBody;
	}

	public String getPancreasTail() {
		return pancreasTail;
	}

	public void setPancreasTail(String pancreasTail) {
		this.pancreasTail = pancreasTail;
	}

	public String getKidneyrightSize() {
		return kidneyrightSize;
	}

	public void setKidneyrightSize(String kidneyrightSize) {
		this.kidneyrightSize = kidneyrightSize;
	}

	public String getKidneyrightParenhimisize() {
		return kidneyrightParenhimisize;
	}

	public void setKidneyrightParenhimisize(String kidneyrightParenhimisize) {
		this.kidneyrightParenhimisize = kidneyrightParenhimisize;
	}

	public String getKidneyrightDoctor() {
		return kidneyrightDoctor;
	}

	public void setKidneyrightDoctor(String kidnetrightDoctor) {
		this.kidneyrightDoctor = kidnetrightDoctor;
	}

	public String getKidneyleftSize() {
		return kidneyleftSize;
	}

	public void setKidneyleftSize(String kidneyleftSize) {
		this.kidneyleftSize = kidneyleftSize;
	}

	public String getKidneyleftParenhimisize() {
		return kidneyleftParenhimisize;
	}

	public void setKidneyleftParenhimisize(String kidneyleftParenhimisize) {
		this.kidneyleftParenhimisize = kidneyleftParenhimisize;
	}

	public String getKidneyleftDoctor() {
		return kidneyleftDoctor;
	}

	public void setKidneyleftDoctor(String kidnetleftDoctor) {
		this.kidneyleftDoctor = kidnetleftDoctor;
	}

	public String getSpleenSizenumber() {
		return spleenSizenumber;
	}

	public void setSpleenSizenumber(String spleenSizenumber) {
		this.spleenSizenumber = spleenSizenumber;
	}

	public String getSpleenVen() {
		return spleenVen;
	}

	public void setSpleenVen(String spleenVen) {
		this.spleenVen = spleenVen;
	}

	public String getSpleenDoctor() {
		return spleenDoctor;
	}

	public void setSpleenDoctor(String spleenDoctor) {
		this.spleenDoctor = spleenDoctor;
	}

	public String getUterusDoctor() {
		return uterusDoctor;
	}

	public void setUterusDoctor(String uterusDoctor) {
		this.uterusDoctor = uterusDoctor;
	}

	public String getUterusEndometr() {
		return uterusEndometr;
	}

	public void setUterusEndometr(String uterusEndometr) {
		this.uterusEndometr = uterusEndometr;
	}

	public String getUterineDoctor() {
		return uterineDoctor;
	}

	public void setUterineDoctor(String uterineDoctor) {
		this.uterineDoctor = uterineDoctor;
	}

	public String getProstateSizenumber() {
		return prostateSizenumber;
	}

	public void setProstateSizenumber(String prostateSizenumber) {
		this.prostateSizenumber = prostateSizenumber;
	}

	public String getProstateDoctor() {
		return prostateDoctor;
	}

	public void setProstateDoctor(String prostateDoctor) {
		this.prostateDoctor = prostateDoctor;
	}

	public String getBladderWall() {
		return bladderWall;
	}

	public void setBladderWall(String bladderWall) {
		this.bladderWall = bladderWall;
	}

	public String getBladderDoctor() {
		return bladderDoctor;
	}

	public void setBladderDoctor(String bladderDoctor) {
		this.bladderDoctor = bladderDoctor;
	}

	public String getAbdomenDoctor() {
		return abdomenDoctor;
	}

	public void setAbdomenDoctor(String abdomenDoctor) {
		this.abdomenDoctor = abdomenDoctor;
	}

	public Ultrasound() {
		super();
	}

	public String getLiverReflection() {
		return liverReflection;
	}

	public void setLiverReflection(String liverReflection) {
		this.liverReflection = liverReflection;
	}

	public String getLiverState() {
		return liverState;
	}

	public void setLiverState(String liverState) {
		this.liverState = liverState;
	}

	public String getLiverUpside() {
		return liverUpside;
	}

	public void setLiverUpside(String liverUpside) {
		this.liverUpside = liverUpside;
	}

	public String getLiverVen() {
		return liverVen;
	}

	public void setLiverVen(String liverVen) {
		this.liverVen = liverVen;
	}

	public String getLiverGall() {
		return liverGall;
	}

	public void setLiverGall(String liverGall) {
		this.liverGall = liverGall;
	}

	public String getLiverSide() {
		return liverSide;
	}

	public void setLiverSide(String liverSide) {
		this.liverSide = liverSide;
	}

	public String getGallSize() {
		return gallSize;
	}

	public void setGallSize(String gallSize) {
		this.gallSize = gallSize;
	}

	public String getGallWall() {
		return gallWall;
	}

	public void setGallWall(String gallWall) {
		this.gallWall = gallWall;
	}

	public String getGallSidestruct() {
		return gallSidestruct;
	}

	public void setGallSidestruct(String gallSidestruct) {
		this.gallSidestruct = gallSidestruct;
	}

	public String getGallWallstruct() {
		return gallWallstruct;
	}

	public void setGallWallstruct(String gallWallstruct) {
		this.gallWallstruct = gallWallstruct;
	}

	public String getGallGeneral() {
		return gallGeneral;
	}

	public void setGallGeneral(String gallGeneral) {
		this.gallGeneral = gallGeneral;
	}

	public String getGallSide() {
		return gallSide;
	}

	public void setGallSide(String gallSide) {
		this.gallSide = gallSide;
	}

	public String getPancreasReflection() {
		return pancreasReflection;
	}

	public void setPancreasReflection(String pancreasReflection) {
		this.pancreasReflection = pancreasReflection;
	}

	public String getPancreasState() {
		return pancreasState;
	}

	public void setPancreasState(String pancreasState) {
		this.pancreasState = pancreasState;
	}

	public String getPancreasUpside() {
		return pancreasUpside;
	}

	public void setPancreasUpside(String pancreasUpside) {
		this.pancreasUpside = pancreasUpside;
	}

	public String getPancreasPort() {
		return pancreasPort;
	}

	public void setPancreasPort(String pancreasPort) {
		this.pancreasPort = pancreasPort;
	}

	public String getPancreasSide() {
		return pancreasSide;
	}

	public void setPancreasSide(String pancreasSide) {
		this.pancreasSide = pancreasSide;
	}

	public String getKidneyrightUpside() {
		return kidneyrightUpside;
	}

	public void setKidneyrightUpside(String kidneyrightUpside) {
		this.kidneyrightUpside = kidneyrightUpside;
	}

	public String getKidneyrightATC() {
		return kidneyrightATC;
	}

	public void setKidneyrightATC(String kidneyrightATC) {
		this.kidneyrightATC = kidneyrightATC;
	}

	public String getKidneyrightSide() {
		return kidneyrightSide;
	}

	public void setKidneyrightSide(String kidneyrightSide) {
		this.kidneyrightSide = kidneyrightSide;
	}

	public String getKidneyrightParenhimi() {
		return kidneyrightParenhimi;
	}

	public void setKidneyrightParenhimi(String kidneyrightParenhimi) {
		this.kidneyrightParenhimi = kidneyrightParenhimi;
	}

	public String getKidneyrightCup() {
		return kidneyrightCup;
	}

	public void setKidneyrightCup(String kidneyrightCup) {
		this.kidneyrightCup = kidneyrightCup;
	}

	public String getKidneyrightATCParenhimi() {
		return kidneyrightATCParenhimi;
	}

	public void setKidneyrightATCParenhimi(String kidneyrightATCParenhimi) {
		this.kidneyrightATCParenhimi = kidneyrightATCParenhimi;
	}

	public String getKidneyleftUpside() {
		return kidneyleftUpside;
	}

	public void setKidneyleftUpside(String kidneyleftUpside) {
		this.kidneyleftUpside = kidneyleftUpside;
	}

	public String getKidneyleftReflection() {
		return kidneyleftReflection;
	}

	public void setKidneyleftReflection(String kedneyleftReflection) {
		this.kidneyleftReflection = kedneyleftReflection;
	}

	public String getKidneyleftATC() {
		return kidneyleftATC;
	}

	public void setKidneyleftATC(String kidneyleftATC) {
		this.kidneyleftATC = kidneyleftATC;
	}

	public String getKidneyleftSide() {
		return kidneyleftSide;
	}

	public void setKidneyleftSide(String kidneyleftSide) {
		this.kidneyleftSide = kidneyleftSide;
	}

	public String getKidneyleftParenhimi() {
		return kidneyleftParenhimi;
	}

	public void setKidneyleftParenhimi(String kidneyleftParenhimi) {
		this.kidneyleftParenhimi = kidneyleftParenhimi;
	}

	public String getKidneyleftCup() {
		return kidneyleftCup;
	}

	public void setKidneyleftCup(String kidneyleftCup) {
		this.kidneyleftCup = kidneyleftCup;
	}

	public String getKidneyleftATCParenhimi() {
		return kidneyleftATCParenhimi;
	}

	public void setKidneyleftATCParenhimi(String kidneyleftATCParenhimi) {
		this.kidneyleftATCParenhimi = kidneyleftATCParenhimi;
	}

	public String getSpleenSize() {
		return spleenSize;
	}

	public void setSpleenSize(String spleenSize) {
		this.spleenSize = spleenSize;
	}

	public String getSpleenState() {
		return spleenState;
	}

	public void setSpleenState(String spleenState) {
		this.spleenState = spleenState;
	}

	public String getSpleenReflection() {
		return spleenReflection;
	}

	public void setSpleenReflection(String spleenReflection) {
		this.spleenReflection = spleenReflection;
	}

	public String getSpleenSide() {
		return spleenSide;
	}

	public void setSpleenSide(String spleenSide) {
		this.spleenSide = spleenSide;
	}

	public String getProstateSize() {
		return prostateSize;
	}

	public void setProstateSize(String prostateSize) {
		this.prostateSize = prostateSize;
	}

	public String getProstateState() {
		return prostateState;
	}

	public void setProstateState(String prostateState) {
		this.prostateState = prostateState;
	}

	public String getProstateSide() {
		return prostateSide;
	}

	public void setProstateSide(String prostateSide) {
		this.prostateSide = prostateSide;
	}

	public String getProstateCorner() {
		return prostateCorner;
	}

	public void setProstateCorner(String prostateCorner) {
		this.prostateCorner = prostateCorner;
	}

	public String getBladderSide() {
		return bladderSide;
	}

	public void setBladderSide(String bladderSide) {
		this.bladderSide = bladderSide;
	}

	public String getBladderPee() {
		return bladderPee;
	}

	public void setBladderPee(String bladderPee) {
		this.bladderPee = bladderPee;
	}

	public String getAbdomenLiquid() {
		return abdomenLiquid;
	}

	public void setAbdomenLiquid(String adbomenLiquid) {
		this.abdomenLiquid = adbomenLiquid;
	}

	public BigDecimal getPkId() {
		return pkId;
	}

	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}

	public BigDecimal getXrayRequestPkId() {
		return xrayRequestPkId;
	}

	public void setXrayRequestPkId(BigDecimal xrayRequestPkId) {
		this.xrayRequestPkId = xrayRequestPkId;
	}

	public BigDecimal getCreatedby() {
		return createdby;
	}

	public void setCreatedby(BigDecimal createdby) {
		this.createdby = createdby;
	}

	public BigDecimal getSavedby() {
		return savedby;
	}

	public void setSavedby(BigDecimal savedby) {
		this.savedby = savedby;
	}

	public BigDecimal getUpdatedby() {
		return updatedby;
	}

	public void setUpdatedby(BigDecimal updatedby) {
		this.updatedby = updatedby;
	}

	public BigDecimal getInspectedBy() {
		return inspectedBy;
	}

	public void setInspectedBy(BigDecimal inspectedBy) {
		this.inspectedBy = inspectedBy;
	}

	public Date getUpdatedDate() {
		if (updatedDate == null)
			updatedDate = new Date();
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Date getCreatedDate() {
		if (createdDate == null)
			createdDate = new Date();
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getSavedDate() {
		if (savedDate == null)
			savedDate = new Date();
		return savedDate;
	}

	public void setSavedDate(Date savedDate) {
		this.savedDate = savedDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
