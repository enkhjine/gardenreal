package hospital.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Room")
public class Room implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "PkId", length = 18, nullable = false)
	private BigDecimal pkId;
	
	/**
	 * BuilddingInfo TYPE = 0
	 * */
	@Column(name = "BlockPkId")
	private BigDecimal blockPkId;
	
	/**
	 * BuilddingInfo TYPE = 1
	 * */
	@Column(name = "FlatPkId")
	private BigDecimal flatPkId;
	
	/**
	 * BuilddingInfo TYPE = 2
	 * */
	@Column(name = "AislePkId")
	private BigDecimal aislePkId;
	
	@Column(name = "RoomNo")
	private String roomNo;
	
	/**
	 * 0 - HEVTEN EMCHLUULEH OROO BISH
	 * 1 - HEVTEN EMCHLUULEH OROO
	 * */
	@Column(name = "HasInpatient")
	private byte hasInpatient;
	
	/**
	 * 0 - BIP MON
	 * 1 - BIP
	 * */
	@Column(name = "HasVIP")
	private byte hasVIP;
	
	@Column(name = "SubOrganizationTypePkId")
	private BigDecimal subOrganizationTypePkId;
	
	@Column(name = "RoomType")
	private byte roomType;
	
	@Column(name = "BedCount")
	private int bedCount;
	
	public Room(){
		super();
	}

	public BigDecimal getPkId() {
		return pkId;
	}

	public void setPkId(BigDecimal pkId) {
		this.pkId = pkId;
	}

	public BigDecimal getBlockPkId() {
		return blockPkId;
	}

	public void setBlockPkId(BigDecimal blockPkId) {
		this.blockPkId = blockPkId;
	}

	public BigDecimal getFlatPkId() {
		return flatPkId;
	}

	public void setFlatPkId(BigDecimal flatPkId) {
		this.flatPkId = flatPkId;
	}

	public BigDecimal getAislePkId() {
		return aislePkId;
	}

	public void setAislePkId(BigDecimal aislePkId) {
		this.aislePkId = aislePkId;
	}

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public byte getHasInpatient() {
		return hasInpatient;
	}

	public void setHasInpatient(byte hasInpatient) {
		this.hasInpatient = hasInpatient;
	}

	public byte getHasVIP() {
		return hasVIP;
	}

	public void setHasVIP(byte hasVIP) {
		this.hasVIP = hasVIP;
	}

	public BigDecimal getSubOrganizationTypePkId() {
		return subOrganizationTypePkId;
	}

	public void setSubOrganizationTypePkId(BigDecimal subOrganizationTypePkId) {
		this.subOrganizationTypePkId = subOrganizationTypePkId;
	}

	public byte getRoomType() {
		return roomType;
	}

	public void setRoomType(byte roomType) {
		this.roomType = roomType;
	}

	public int getBedCount() {
		return bedCount;
	}

	public void setBedCount(int bedCount) {
		this.bedCount = bedCount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
