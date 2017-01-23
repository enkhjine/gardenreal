package hospital.businessentity;

import hospital.entity.MedicalHistory;

import java.util.List;

public class CustomerMedicalHistory {
	private List<MedicalHistory> historys;
	private boolean bloodType0;
	private boolean bloodType1;
	private boolean bloodType2;
	private boolean bloodType3;
	private boolean bloodType4;
	private boolean allergy1;
	private boolean allergy2;
	private boolean allergy3;
	private boolean infectious1;
	private boolean infectious2;
	private boolean infectious3;
	private boolean infectious4;
	private boolean infectious5;
	private boolean infectious6;
	private boolean infectious7;
	private boolean infectious8;
	private boolean infectious9;
	private boolean infectious10;
	private boolean infectious11;
	private boolean infectious12;
	private boolean noninfectious1;
	private boolean noninfectious2;
	private boolean noninfectious3;
	private boolean noninfectious4;
	private boolean noninfectious5;
	private boolean noninfectious6;
	private boolean noninfectious7;
	private boolean noninfectious8;
	private boolean noninfectious9;
	private boolean noninfectious10;
	private boolean noninfectious11;
	private boolean familial1;
	private boolean familial2;
	private boolean familial3;
	private boolean familial4;
	private boolean familial5;
	private boolean familial6;
	private boolean familial7;
	private boolean familial8;
	private boolean familial9;
	private boolean familial10;
	private boolean familial11;

	private String allergyanti;
	private String allergyfood;
	private String allergyother;

	private String noninfectious01;
	private String noninfectious02;
	private String noninfectious03;
	private String noninfectious04;
	private String noninfectious05;
	private String noninfectious06;
	private String noninfectious07;
	private String noninfectious08;
	private String noninfectious09;
	private String noninfectious010;
	private String noninfectious011;

	private String familial01;
	private String familial02;
	private String familial03;
	private String familial04;
	private String familial05;
	private String familial06;
	private String familial07;
	private String familial08;
	private String familial09;
	private String familial010;
	private String familial011;

	private int infectiousYear1;
	private int infectiousYear2;
	private int infectiousYear3;
	private int infectiousYear4;
	private int infectiousYear5;
	private int infectiousYear6;
	private int infectiousYear7;
	private int infectiousYear8;
	private int infectiousYear9;
	private int infectiousYear10;
	private int infectiousYear11;
	private int infectiousYear12;
	private String description;

	public CustomerMedicalHistory() {
		super();
	}

	public MedicalHistory getHistory(String type) {
		for (MedicalHistory history : historys)
			if (type.equals(history.getType()))
				return history;
		return null;
	}

	public List<MedicalHistory> getHistorys() {
		return historys;
	}

	public void setHistorys(List<MedicalHistory> historys) {
		this.historys = historys;
	}
	public boolean isBloodType0() {
		bloodType0 = getHistory("bloodType").getValue().equals("0");
		return bloodType0;
	}

	public void setBloodType0(boolean bloodType0) {
		if (bloodType0 == true)
			getHistory("bloodType").setValue("0");
		this.bloodType0 = bloodType0;
	}

	public boolean isBloodType1() {
		bloodType1 = getHistory("bloodType").getValue().equals("1");
		return bloodType1;
	}

	public void setBloodType1(boolean bloodType1) {
		if (bloodType1 == true)
			getHistory("bloodType").setValue("1");
		this.bloodType1 = bloodType1;
	}

	public boolean isBloodType2() {
		bloodType2 = getHistory("bloodType").getValue().equals("2");
		return bloodType2;
	}

	public void setBloodType2(boolean bloodType2) {
		if (bloodType2 == true)
			getHistory("bloodType").setValue("2");
		this.bloodType2 = bloodType2;
	}

	public boolean isBloodType3() {
		bloodType3 = getHistory("bloodType").getValue().equals("3");
		return bloodType3;
	}

	public void setBloodType3(boolean bloodType3) {
		if (bloodType3 == true)
			getHistory("bloodType").setValue("3");
		this.bloodType3 = bloodType3;
	}

	public boolean isBloodType4() {
		bloodType4 = getHistory("bloodType").getValue().equals("4");
		return bloodType4;
	}

	public void setBloodType4(boolean bloodType4) {
		if (bloodType4 == true)
			getHistory("bloodType").setValue("4");
		this.bloodType4 = bloodType4;
	}

	public boolean isAllergy1() {
		allergy1 = !getAllergyanti().equals(" ");
		return allergy1;
	}

	public void setAllergy1(boolean allergy1) {
		this.allergy1 = allergy1;
		if (allergy1 == true && getAllergyanti() .equals(" "))
			setAllergyanti("");

	}

	public boolean isAllergy2() {
		allergy2 = !getAllergyfood().equals(" ");
		return allergy2;
	}

	public void setAllergy2(boolean allergy2) {
		this.allergy2 = allergy2;
		if (allergy2 == true && getAllergyfood() .equals(" "))
			setAllergyfood("");
	}

	public boolean isAllergy3() {
		allergy3 = !getAllergyother().equals(" ");
		return allergy3;
	}

	public void setAllergy3(boolean allergy3) {
		this.allergy3 = allergy3;
		if (allergy3 == true && getAllergyother() .equals(" "))
			setAllergyother("");
	}

	// 1
	public boolean isInfectious1() {
		infectious1 = getInfectiousYear1() != 0;
		return infectious1;
	}

	public void setInfectious1(boolean infectious1) {
		this.infectious1 = infectious1;
		if (infectious1 == true && getInfectiousYear1() == 0)
			setInfectiousYear1(1900);
	}

	public int getInfectiousYear1() {
		if (getHistory("infectious1").getValue() == null)
			infectiousYear1 = 0;
		else
			infectiousYear1 = Integer.parseInt(getHistory("infectious1")
					.getValue());
		return infectiousYear1;
	}

	public void setInfectiousYear1(int infectiousYear1) {
		if (infectious1 == true) {
			getHistory("infectious1").setValue(
					Integer.toString(infectiousYear1));
		} else {
			getHistory("infectious1").setValue(Integer.toString(0));
		}
		this.infectiousYear1 = infectiousYear1;
	}

	// 2
	public boolean isInfectious2() {
		infectious2 = getInfectiousYear2() != 0;
		return infectious2;
	}

	public void setInfectious2(boolean infectious2) {
		this.infectious2 = infectious2;
		if (infectious2 == true && getInfectiousYear2() == 0)
			setInfectiousYear2(1900);
	}

	public int getInfectiousYear2() {
		if (getHistory("infectious2").getValue() == null)
			infectiousYear2 = 0;
		else
			infectiousYear2 = Integer.parseInt(getHistory("infectious2")
					.getValue());
		return infectiousYear2;
	}

	public void setInfectiousYear2(int infectiousYear2) {
		if (infectious2 == true) {
			getHistory("infectious2").setValue(
					Integer.toString(infectiousYear2));
		} else {
			getHistory("infectious2").setValue(Integer.toString(0));
		}
		this.infectiousYear2 = infectiousYear2;
	}

	// 3
	public boolean isInfectious3() {
		infectious3 = getInfectiousYear3() != 0;
		return infectious3;
	}

	public void setInfectious3(boolean infectious3) {
		this.infectious3 = infectious3;
		if (infectious3 == true && getInfectiousYear3() == 0)
			setInfectiousYear3(1900);
	}

	public int getInfectiousYear3() {
		if (getHistory("infectious3").getValue() == null)
			infectiousYear3 = 0;
		else
			infectiousYear3 = Integer.parseInt(getHistory("infectious3")
					.getValue());
		return infectiousYear3;
	}

	public void setInfectiousYear3(int infectiousYear3) {
		if (infectious3 == true) {
			getHistory("infectious3").setValue(
					Integer.toString(infectiousYear3));
		} else {
			getHistory("infectious3").setValue(Integer.toString(0));
		}
		this.infectiousYear3 = infectiousYear3;
	}

	// 4
	public boolean isInfectious4() {
		infectious4 = getInfectiousYear4() != 0;
		return infectious4;
	}

	public void setInfectious4(boolean infectious4) {
		this.infectious4 = infectious4;
		if (infectious4 == true && getInfectiousYear4() == 0)
			setInfectiousYear4(1900);
	}

	public int getInfectiousYear4() {
		if (getHistory("infectious4").getValue() == null)
			infectiousYear4 = 0;
		else
			infectiousYear4 = Integer.parseInt(getHistory("infectious4")
					.getValue());
		return infectiousYear4;
	}

	public void setInfectiousYear4(int infectiousYear4) {
		if (infectious4 == true) {
			getHistory("infectious4").setValue(
					Integer.toString(infectiousYear4));
		} else {
			getHistory("infectious4").setValue(Integer.toString(0));
		}
		this.infectiousYear4 = infectiousYear4;
	}

	// 5
	public boolean isInfectious5() {
		infectious5 = getInfectiousYear5() != 0;
		return infectious5;
	}

	public void setInfectious5(boolean infectious5) {
		this.infectious5 = infectious5;
		if (infectious5 == true && getInfectiousYear5() == 0)
			setInfectiousYear5(1900);
	}

	public int getInfectiousYear5() {
		if (getHistory("infectious5").getValue() == null)
			infectiousYear5 = 0;
		else
			infectiousYear5 = Integer.parseInt(getHistory("infectious5")
					.getValue());
		return infectiousYear5;
	}

	public void setInfectiousYear5(int infectiousYear5) {
		if (infectious5 == true) {
			getHistory("infectious5").setValue(
					Integer.toString(infectiousYear5));
		} else {
			getHistory("infectious5").setValue(Integer.toString(0));
		}
		this.infectiousYear5 = infectiousYear5;
	}

	// 6
	public boolean isInfectious6() {
		infectious6 = getInfectiousYear6() != 0;
		return infectious6;
	}

	public void setInfectious6(boolean infectious6) {
		this.infectious6 = infectious6;
		if (infectious6 == true && getInfectiousYear6() == 0)
			setInfectiousYear6(1900);
	}

	public int getInfectiousYear6() {
		if (getHistory("infectious6").getValue() == null)
			infectiousYear6 = 0;
		else
			infectiousYear6 = Integer.parseInt(getHistory("infectious6")
					.getValue());
		return infectiousYear6;
	}

	public void setInfectiousYear6(int infectiousYear6) {
		if (infectious6 == true) {
			getHistory("infectious6").setValue(
					Integer.toString(infectiousYear6));
		} else {
			getHistory("infectious6").setValue(Integer.toString(0));
		}
		this.infectiousYear6 = infectiousYear6;
	}

	// 7
	public boolean isInfectious7() {
		infectious7 = getInfectiousYear7() != 0;
		return infectious7;
	}

	public void setInfectious7(boolean infectious7) {
		this.infectious7 = infectious7;
		if (infectious7 == true && getInfectiousYear7() == 0)
			setInfectiousYear7(1900);
	}

	public int getInfectiousYear7() {
		if (getHistory("infectious7").getValue() == null)
			infectiousYear7 = 0;
		else
			infectiousYear7 = Integer.parseInt(getHistory("infectious7")
					.getValue());
		return infectiousYear7;
	}

	public void setInfectiousYear7(int infectiousYear7) {
		if (infectious7 == true) {
			getHistory("infectious7").setValue(
					Integer.toString(infectiousYear7));
		} else {
			getHistory("infectious7").setValue(Integer.toString(0));
		}
		this.infectiousYear7 = infectiousYear7;
	}

	// 8
	public boolean isInfectious8() {
		infectious8 = getInfectiousYear8() != 0;
		return infectious8;
	}

	public void setInfectious8(boolean infectious8) {
		this.infectious8 = infectious8;
		if (infectious8 == true && getInfectiousYear8() == 0)
			setInfectiousYear8(1900);
	}

	public int getInfectiousYear8() {
		if (getHistory("infectious8").getValue() == null)
			infectiousYear8 = 0;
		else
			infectiousYear8 = Integer.parseInt(getHistory("infectious8")
					.getValue());
		return infectiousYear8;
	}

	public void setInfectiousYear8(int infectiousYear8) {
		if (infectious8 == true) {
			getHistory("infectious8").setValue(
					Integer.toString(infectiousYear8));
		} else {
			getHistory("infectious8").setValue(Integer.toString(0));
		}
		this.infectiousYear8 = infectiousYear8;
	}

	// 9
	public boolean isInfectious9() {
		infectious9 = getInfectiousYear9() != 0;
		return infectious9;
	}

	public void setInfectious9(boolean infectious9) {
		this.infectious9 = infectious9;
		if (infectious9 == true && getInfectiousYear9() == 0)
			setInfectiousYear9(1900);
	}

	public int getInfectiousYear9() {
		if (getHistory("infectious9").getValue() == null)
			infectiousYear9 = 0;
		else
			infectiousYear9 = Integer.parseInt(getHistory("infectious9")
					.getValue());
		return infectiousYear9;
	}

	public void setInfectiousYear9(int infectiousYear9) {
		if (infectious9 == true) {
			getHistory("infectious9").setValue(
					Integer.toString(infectiousYear9));
		} else {
			getHistory("infectious9").setValue(Integer.toString(0));
		}
		this.infectiousYear9 = infectiousYear9;
	}

	// 10
	public boolean isInfectious10() {
		infectious10 = getInfectiousYear10() != 0;
		return infectious10;
	}

	public void setInfectious10(boolean infectious10) {
		this.infectious10 = infectious10;
		if (infectious10 == true && getInfectiousYear10() == 0)
			setInfectiousYear10(1900);
	}

	public int getInfectiousYear10() {
		if (getHistory("infectious10").getValue() == null)
			infectiousYear10 = 0;
		else
			infectiousYear10 = Integer.parseInt(getHistory("infectious10")
					.getValue());
		return infectiousYear10;
	}

	public void setInfectiousYear10(int infectiousYear10) {
		if (infectious10 == true) {
			getHistory("infectious10").setValue(
					Integer.toString(infectiousYear10));
		} else {
			getHistory("infectious10").setValue(Integer.toString(0));
		}
		this.infectiousYear10 = infectiousYear10;
	}

	// 11
	public boolean isInfectious11() {
		infectious11 = getInfectiousYear11() != 0;
		return infectious11;
	}

	public void setInfectious11(boolean infectious11) {
		this.infectious11 = infectious11;
		if (infectious11 == true && getInfectiousYear11() == 0)
			setInfectiousYear11(1900);
	}

	public int getInfectiousYear11() {
		if (getHistory("infectious11").getValue() == null)
			infectiousYear11 = 0;
		else
			infectiousYear11 = Integer.parseInt(getHistory("infectious11")
					.getValue());
		return infectiousYear11;
	}

	public void setInfectiousYear11(int infectiousYear11) {
		if (infectious11 == true) {
			getHistory("infectious11").setValue(
					Integer.toString(infectiousYear11));
		} else {
			getHistory("infectious11").setValue(Integer.toString(0));
		}
		this.infectiousYear11 = infectiousYear11;
	}

	// 12
	public boolean isInfectious12() {
		infectious12 = getInfectiousYear12() != 0;
		return infectious12;
	}

	public void setInfectious12(boolean infectious12) {
		this.infectious12 = infectious12;
		if (infectious12 == true && getInfectiousYear12() == 0)
			setInfectiousYear12(1900);
	}

	public int getInfectiousYear12() {
		if (getHistory("infectious12").getValue() == null)
			infectiousYear12 = 0;
		else
			infectiousYear12 = Integer.parseInt(getHistory("infectious12")
					.getValue());
		return infectiousYear12;
	}

	public void setInfectiousYear12(int infectiousYear12) {
		if (infectious12 == true) {
			getHistory("infectious12").setValue(
					Integer.toString(infectiousYear12));
		} else {
			getHistory("infectious12").setValue(Integer.toString(0));
		}
		this.infectiousYear12 = infectiousYear12;
	}

	// end

	public boolean isNoninfectious1() {
		noninfectious1 = !getNoninfectious01().equals(" ") ;
		return noninfectious1;
	}

	public void setNoninfectious1(boolean noninfectious1) {
		this.noninfectious1 = noninfectious1;
		if (noninfectious1 == true && getNoninfectious01() .equals(" "))
			setNoninfectious01("");
	}

	public String getNoninfectious01() {
		if (getHistory("noninfectious1").getValue() == null)
			noninfectious01 = " ";
		else
			noninfectious01 = getHistory("noninfectious1").getValue();
		return noninfectious01;
	}

	public void setNoninfectious01(String noninfectious01) {
		if (noninfectious1 == true) {
			getHistory("noninfectious1").setValue(noninfectious01);
		} else {
			getHistory("noninfectious1").setValue(" ");
		}
		this.noninfectious01 = noninfectious01;
	}

	// non2

	public boolean isNoninfectious2() {
		noninfectious2 = !getNoninfectious02().equals(" ") ;
		return noninfectious2;
	}

	public void setNoninfectious2(boolean noninfectious2) {
		this.noninfectious2 = noninfectious2;
		if (noninfectious2 == true && getNoninfectious02() .equals(" "))
			setNoninfectious02("");
	}

	public String getNoninfectious02() {
		if (getHistory("noninfectious2").getValue() == null)
			noninfectious02 = " ";
		else
			noninfectious02 = getHistory("noninfectious2").getValue();
		return noninfectious02;
	}

	public void setNoninfectious02(String noninfectious02) {
		if (noninfectious2 == true) {
			getHistory("noninfectious2").setValue(noninfectious02);
		} else {
			getHistory("noninfectious2").setValue(" ");
		}
		this.noninfectious02 = noninfectious02;
	}

	// 3 non

	public boolean isNoninfectious3() {
		noninfectious3 = !getNoninfectious03().equals(" ") ;
		return noninfectious3;
	}

	public void setNoninfectious3(boolean noninfectious3) {
		this.noninfectious3 = noninfectious3;
		if (noninfectious3 == true && getNoninfectious03() .equals(" "))
			setNoninfectious03("");
	}

	public String getNoninfectious03() {
		if (getHistory("noninfectious3").getValue() == null)
			noninfectious03 = " ";
		else
			noninfectious03 = getHistory("noninfectious3").getValue();
		return noninfectious03;
	}

	public void setNoninfectious03(String noninfectious03) {
		if (noninfectious3 == true) {
			getHistory("noninfectious3").setValue(noninfectious03);
		} else {
			getHistory("noninfectious3").setValue(" ");
		}
		this.noninfectious03 = noninfectious03;
	}

	public boolean isNoninfectious4() {
		noninfectious4 = !getNoninfectious04().equals(" ") ;
		return noninfectious4;
	}

	public void setNoninfectious4(boolean noninfectious4) {
		this.noninfectious4 = noninfectious4;
		if (noninfectious4 == true && getNoninfectious04() .equals(" "))
			setNoninfectious04("");
	}

	public String getNoninfectious04() {
		if (getHistory("noninfectious4").getValue() == null)
			noninfectious04 = " ";
		else
			noninfectious04 = getHistory("noninfectious4").getValue();
		return noninfectious04;
	}

	public void setNoninfectious04(String noninfectious04) {
		if (noninfectious4 == true) {
			getHistory("noninfectious4").setValue(noninfectious04);
		} else {
			getHistory("noninfectious4").setValue(" ");
		}
		this.noninfectious04 = noninfectious04;
	}

	// 5 non

	public boolean isNoninfectious5() {
		noninfectious5 = !getNoninfectious05().equals(" ") ;
		return noninfectious5;
	}

	public void setNoninfectious5(boolean noninfectious5) {
		this.noninfectious5 = noninfectious5;
		if (noninfectious5 == true && getNoninfectious05() .equals(" "))
			setNoninfectious05("");
	}

	public String getNoninfectious05() {
		if (getHistory("noninfectious5").getValue() == null)
			noninfectious05 = " ";
		else
			noninfectious05 = getHistory("noninfectious5").getValue();
		return noninfectious05;
	}

	public void setNoninfectious05(String noninfectious05) {
		if (noninfectious5 == true) {
			getHistory("noninfectious5").setValue(noninfectious05);
		} else {
			getHistory("noninfectious5").setValue(" ");
		}
		this.noninfectious05 = noninfectious05;
	}

	// 6 non
	public boolean isNoninfectious6() {
		noninfectious6 = !getNoninfectious06().equals(" ") ;
		return noninfectious6;
	}

	public void setNoninfectious6(boolean noninfectious6) {
		this.noninfectious6 = noninfectious6;
		if (noninfectious6 == true && getNoninfectious06() .equals(" "))
			setNoninfectious06("");
	}

	public String getNoninfectious06() {
		if (getHistory("noninfectious6").getValue() == null)
			noninfectious06 = " ";
		else
			noninfectious06 = getHistory("noninfectious6").getValue();
		return noninfectious06;
	}

	public void setNoninfectious06(String noninfectious06) {
		if (noninfectious6 == true) {
			getHistory("noninfectious6").setValue(noninfectious06);
		} else {
			getHistory("noninfectious6").setValue(" ");
		}
		this.noninfectious06 = noninfectious06;
	}

	// 7 non

	public boolean isNoninfectious7() {
		noninfectious7 = !getNoninfectious07().equals(" ") ;
		return noninfectious7;
	}

	public void setNoninfectious7(boolean noninfectious7) {
		this.noninfectious7 = noninfectious7;
		if (noninfectious7 == true && getNoninfectious07() .equals(" "))
			setNoninfectious07("");
	}

	public String getNoninfectious07() {
		if (getHistory("noninfectious7").getValue() == null)
			noninfectious07 = " ";
		else
			noninfectious07 = getHistory("noninfectious7").getValue();
		return noninfectious07;
	}

	public void setNoninfectious07(String noninfectious07) {
		if (noninfectious7 == true) {
			getHistory("noninfectious7").setValue(noninfectious07);
		} else {
			getHistory("noninfectious7").setValue(" ");
		}
		this.noninfectious07 = noninfectious07;
	}

	// 8 non
	public boolean isNoninfectious8() {
		noninfectious8 = !getNoninfectious08().equals(" ") ;
		return noninfectious8;
	}

	public void setNoninfectious8(boolean noninfectious8) {
		this.noninfectious8 = noninfectious8;
		if (noninfectious8 == true && getNoninfectious08() .equals(" "))
			setNoninfectious08("");
	}

	public String getNoninfectious08() {
		if (getHistory("noninfectious8").getValue() == null)
			noninfectious08 = " ";
		else
			noninfectious08 = getHistory("noninfectious8").getValue();
		return noninfectious08;
	}

	public void setNoninfectious08(String noninfectious08) {
		if (noninfectious8 == true) {
			getHistory("noninfectious8").setValue(noninfectious08);
		} else {
			getHistory("noninfectious8").setValue(" ");
		}
		this.noninfectious08 = noninfectious08;
	}

	// 9non
	public boolean isNoninfectious9() {
		noninfectious9 = !getNoninfectious09().equals(" ") ;
		return noninfectious9;
	}

	public void setNoninfectious9(boolean noninfectious9) {
		this.noninfectious9 = noninfectious9;
		if (noninfectious9 == true && getNoninfectious09() .equals(" "))
			setNoninfectious09("");
	}

	public String getNoninfectious09() {
		if (getHistory("noninfectious9").getValue() == null)
			noninfectious09 = " ";
		else
			noninfectious09 = getHistory("noninfectious9").getValue();
		return noninfectious09;
	}

	public void setNoninfectious09(String noninfectious09) {
		if (noninfectious9 == true) {
			getHistory("noninfectious9").setValue(noninfectious09);
		} else {
			getHistory("noninfectious9").setValue(" ");
		}
		this.noninfectious09 = noninfectious09;
	}

	// 10 non

	public boolean isNoninfectious10() {
		noninfectious10 = !getNoninfectious010().equals(" ") ;
		return noninfectious10;
	}

	public void setNoninfectious10(boolean noninfectious10) {
		this.noninfectious10 = noninfectious10;
		if (noninfectious10 == true && getNoninfectious010() .equals(" "))
			setNoninfectious010("");
	}

	public String getNoninfectious010() {
		if (getHistory("noninfectious10").getValue() == null)
			noninfectious010 = " ";
		else
			noninfectious010 = getHistory("noninfectious10").getValue();
		return noninfectious010;
	}

	public void setNoninfectious010(String noninfectious010) {
		if (noninfectious10 == true) {
			getHistory("noninfectious10").setValue(noninfectious010);
		} else {
			getHistory("noninfectious10").setValue(" ");
		}
		this.noninfectious010 = noninfectious010;
	}

	// 11

	public boolean isNoninfectious11() {
		noninfectious11 = !getNoninfectious011().equals(" ") ;
		return noninfectious11;
	}

	public void setNoninfectious11(boolean noninfectious11) {
		this.noninfectious11 = noninfectious11;
		if (noninfectious11 == true && getNoninfectious011() .equals(" "))
			setNoninfectious011("");
	}

	public String getNoninfectious011() {
		if (getHistory("noninfectious11").getValue() == null)
			noninfectious011 = " ";
		else
			noninfectious011 = getHistory("noninfectious11").getValue();
		return noninfectious011;
	}

	public void setNoninfectious011(String noninfectious011) {
		if (noninfectious11 == true) {
			getHistory("noninfectious11").setValue(noninfectious011);
		} else {
			getHistory("noninfectious11").setValue(" ");
		}
		this.noninfectious011 = noninfectious011;
	}

	// 1 fam
	public boolean isFamilial1() {
		familial1 = !getFamilial01().equals(" ");
		return familial1;
	}

	public void setFamilial1(boolean familial1) {
		this.familial1 = familial1;
		if (familial1 == true && getFamilial01() .equals(" "))
			setFamilial01("");

	}

	public String getFamilial01() {
		if (getHistory("familial1").getValue() == null)
			familial01 = " ";
		else
			familial01 = getHistory("familial1").getValue();
		return familial01;
	}

	public void setFamilial01(String familial01) {
		if (familial1 == true) {
			getHistory("familial1").setValue(familial01);
		} else {
			getHistory("familial1").setValue(" ");
		}
		this.familial01 = familial01;
	}

	// 2 fam

	public boolean isFamilial2() {
		familial2 = !getFamilial02().equals(" ");
		return familial2;
	}

	public void setFamilial2(boolean familial2) {
		this.familial2 = familial2;
		if (familial2 == true && getFamilial02() .equals(" "))
			setFamilial02("");
	}

	public String getFamilial02() {
		if (getHistory("familial2").getValue() == null)
			familial02 = " ";
		else
			familial02 = getHistory("familial2").getValue();
		return familial02;
	}

	public void setFamilial02(String familial02) {
		if (familial2 == true) {
			getHistory("familial2").setValue(familial02);
		} else {
			getHistory("familial2").setValue(" ");
		}
		this.familial02 = familial02;
	}

	// fam 3
	public boolean isFamilial3() {
		familial3 = !getFamilial03().equals(" ");
		return familial3;
	}

	public void setFamilial3(boolean familial3) {
		this.familial3 = familial3;
		if (familial3 == true && getFamilial03() .equals(" "))
			setFamilial03("");
	}

	public String getFamilial03() {
		if (getHistory("familial3").getValue() == null)
			familial03 = " ";
		else
			familial03 = getHistory("familial3").getValue();
		return familial03;
	}

	public void setFamilial03(String familial03) {
		if (familial3 == true) {
			getHistory("familial3").setValue(familial03);
		} else {
			getHistory("familial3").setValue(" ");
		}
		this.familial03 = familial03;
	}

	// fam 4

	public boolean isFamilial4() {
		familial4 = !getFamilial04().equals(" ");
		return familial4;
	}

	public void setFamilial4(boolean familial4) {
		this.familial4 = familial4;
		if (familial4 == true && getFamilial04() .equals(" "))
			setFamilial04("");
	}

	public String getFamilial04() {
		if (getHistory("familial4").getValue() == null)
			familial04 = " ";
		else
			familial04 = getHistory("familial4").getValue();
		return familial04;
	}

	public void setFamilial04(String familial04) {
		if (familial4 == true) {
			getHistory("familial4").setValue(familial04);
		} else {
			getHistory("familial4").setValue(" ");
		}
		this.familial04 = familial04;
	}

	// fam 5
	public boolean isFamilial5() {
		familial5 = !getFamilial05().equals(" ");
		return familial5;
	}

	public void setFamilial5(boolean familial5) {
		this.familial5 = familial5;
		if (familial5 == true && getFamilial05() .equals(" "))
			setFamilial05("");
	}

	public String getFamilial05() {
		if (getHistory("familial5").getValue() == null)
			familial05 = " ";
		else
			familial05 = getHistory("familial5").getValue();
		return familial05;
	}

	public void setFamilial05(String familial05) {
		if (familial5 == true) {
			getHistory("familial5").setValue(familial05);
		} else {
			getHistory("familial5").setValue(" ");
		}
		this.familial05 = familial05;
	}

	// fam 6
	public boolean isFamilial6() {
		familial6 = !getFamilial06().equals(" ");
		return familial6;
	}

	public void setFamilial6(boolean familial6) {
		this.familial6 = familial6;
		if (familial6 == true && getFamilial06() .equals(" "))
			setFamilial06("");
	}

	public String getFamilial06() {
		if (getHistory("familial6").getValue() == null)
			familial06 = " ";
		else
			familial06 = getHistory("familial6").getValue();
		return familial06;
	}

	public void setFamilial06(String familial06) {
		if (familial6 == true) {
			getHistory("familial6").setValue(familial06);
		} else {
			getHistory("familial6").setValue(" ");
		}
		this.familial06 = familial06;
	}

	// fam 7
	public boolean isFamilial7() {
		familial7 = !getFamilial07().equals(" ");
		return familial7;
	}

	public void setFamilial7(boolean familial7) {
		this.familial7 = familial7;
		if (familial7 == true && getFamilial07() .equals(" "))
			setFamilial07("");
	}

	public String getFamilial07() {
		if (getHistory("familial7").getValue() == null)
			familial07 = " ";
		else
			familial07 = getHistory("familial7").getValue();
		return familial07;
	}

	public void setFamilial07(String familial07) {
		if (familial7 == true) {
			getHistory("familial7").setValue(familial07);
		} else {
			getHistory("familial7").setValue(" ");
		}
		this.familial07 = familial07;
	}

	// fam 8

	public boolean isFamilial8() {
		familial8 = !getFamilial08().equals(" ");
		return familial8;
	}

	public void setFamilial8(boolean familial8) {
		this.familial8 = familial8;
		if (familial8 == true && getFamilial08() .equals(" "))
			setFamilial08("");
	}

	public String getFamilial08() {
		if (getHistory("familial8").getValue() == null)
			familial08 = " ";
		else
			familial08 = getHistory("familial8").getValue();
		return familial08;
	}

	public void setFamilial08(String familial08) {
		if (familial8 == true) {
			getHistory("familial8").setValue(familial08);
		} else {
			getHistory("familial8").setValue(" ");
		}
		this.familial08 = familial08;
	}

	// fam 9
	public boolean isFamilial9() {
		familial9 = !getFamilial09().equals(" ");
		return familial9;
	}

	public void setFamilial9(boolean familial9) {
		this.familial9 = familial9;
		if (familial9 == true && getFamilial09() .equals(" "))
			setFamilial09("");
	}

	public String getFamilial09() {
		if (getHistory("familial9").getValue() == null)
			familial09 = " ";
		else
			familial09 = getHistory("familial9").getValue();
		return familial09;
	}

	public void setFamilial09(String familial09) {
		if (familial9 == true) {
			getHistory("familial9").setValue(familial09);
		} else {
			getHistory("familial9").setValue(" ");
		}
		this.familial09 = familial09;
	}

	// fam 10

	public boolean isFamilial10() {
		familial10 = !getFamilial010().equals(" ");
		return familial10;
	}

	public void setFamilial10(boolean familial10) {
		this.familial10 = familial10;
		if (familial10 == true && getFamilial010() .equals(" "))
			setFamilial010("");
	}

	public String getFamilial010() {
		if (getHistory("familial10").getValue() == null)
			familial010 = " ";
		else
			familial010 = getHistory("familial10").getValue();
		return familial010;
	}

	public void setFamilial010(String familial010) {
		if (familial10 == true) {
			getHistory("familial10").setValue(familial010);
		} else {
			getHistory("familial10").setValue(" ");
		}
		this.familial010 = familial010;
	}

	// fam 11

	public boolean isFamilial11() {
		familial11 = !getFamilial011().equals(" ");
		return familial11;
	}

	public void setFamilial11(boolean familial11) {
		this.familial11 = familial11;
		if (familial11 == true && getFamilial011() .equals(" "))
			setFamilial011("");
	}

	public String getFamilial011() {
		if (getHistory("familial11").getValue() == null)
			familial011 = " ";
		else
			familial011 = getHistory("familial11").getValue();
		return familial011;
	}

	public void setFamilial011(String familial011) {
		if (familial11 == true) {
			getHistory("familial11").setValue(familial011);
		} else {
			getHistory("familial11").setValue(" ");
		}
		this.familial011 = familial011;
	}

	public String getAllergyanti() {
		if (getHistory("allergy_ab").getValue() == null)
			allergyanti = " ";
		else
			allergyanti = getHistory("allergy_ab").getValue();
		return allergyanti;
	}

	public void setAllergyanti(String allergyanti) {
		if (allergy1 == true) {
			getHistory("allergy_ab").setValue(allergyanti);
		} else {
			getHistory("allergy_ab").setValue(" ");
		}
		this.allergyanti = allergyanti;
	}

	public String getAllergyfood() {
		if (getHistory("allergy_food").getValue() == null)
			allergyfood = " ";
		else
			allergyfood = getHistory("allergy_food").getValue();
		return allergyfood;
	}

	public void setAllergyfood(String allergyfood) {
		if (allergy2 == true) {
			getHistory("allergy_food").setValue(allergyfood);
		} else {
			getHistory("allergy_food").setValue(" ");
		}
		this.allergyfood = allergyfood;
	}

	public String getAllergyother() {
		if (getHistory("allergy_other").getValue() == null)
			allergyother = " ";
		else
			allergyother = getHistory("allergy_other").getValue();
		return allergyother;
	}

	public void setAllergyother(String allergyother) {
		if (allergy3 == true) {
			getHistory("allergy_other").setValue(allergyother);
		} else {
			getHistory("allergy_other").setValue(" ");
		}
		this.allergyother = allergyother;
	}

	public String getDescription() {
		if(getHistory("description").getValue()==null)
			description = " ";	
		else
			description = getHistory("description").getValue();
		return description;
	}

	public void setDescription(String description) {
		getHistory("description").setValue(description);
		System.out.println(getHistory("description").getValue());
	}
	
	public String getBloody()
	{
		String ret = "";
		if(bloodType1 == true)
			 ret = "O(I)";
		else if (bloodType2 == true)
			ret = "A(II)";
		else if (bloodType3 == true)
			ret = "B(III)";
		else if (bloodType4 == true)
			ret = "AB(IV)";
		return ret;
	}
	
	public String getFamilialy()
	{
		String ret = "";
		if(familial1 == true)
			ret = ret + "Халдвар ба паразитын өвчин : " + getFamilial01() + "\n";
		if(familial2 == true)
			ret = ret + "Хорт хавдар:" + getFamilial02()+ "\n";
		if(familial3 == true)
			ret = ret + "Цусны эмгэг:" + getFamilial03()+ "\n";
		if(familial4 == true)
			ret = ret + "Дотоод шүүрлийн булчирхай:" + getFamilial04()+ "\n";
		if(familial5 == true)
			ret = ret + "Сэтгэл мэдрэлийн эмгэг:" + getFamilial05()+ "\n";
		if(familial6 == true)
			ret = ret + "Мэдрэлийн системийн эмгэг:" + getFamilial06()+ "\n";
		if(familial7 == true)
			ret = ret + "Нүдний эмгэг:" + getFamilial07()+ "\n";
		if(familial8 == true)
			ret = ret + "Чих, хөхлөг ургацгийн эмгэг:" + getFamilial08()+ "\n";
		if(familial9 == true)
			ret = ret + "Цусны эргэлтийн системийн эмгэг:" + getFamilial09()+ "\n";
		if(familial10 == true)
			ret = ret + "Амьсгалын системийн эмгэг:" + getFamilial010()+ "\n";
		if(familial11 == true)
			ret = ret + "Хоол боловсруулах системийн өвчин:" + getFamilial011()+ "\n";
		
		return ret;
	}
	

}
