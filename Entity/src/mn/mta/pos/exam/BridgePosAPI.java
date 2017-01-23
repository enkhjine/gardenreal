package mn.mta.pos.exam;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.tools.Tool;

import hospital.businessentity.CashByInspectionDtl;
import hospital.businessentity.PaymentInspection;
import hospital.entity.Diagnose;

public class BridgePosAPI {
	static{
        String os = System.getProperty("os.name"); if
                (os.toUpperCase().contains("WINDOWS")) {
            System.loadLibrary("icudt53");
            System.loadLibrary("icuuc53");
            System.loadLibrary("icuin53");
            System.loadLibrary("libeay32");
            System.loadLibrary("Qt5Core");
            System.loadLibrary("Qt5Network");
            System.loadLibrary("Qt5SQL");
            System.loadLibrary("ssleay32");

            System.loadLibrary("PosAPI");
        }
        System.loadLibrary("BridgePosAPI");
    }
    public static native String put(String data);
    public static native String returnBill(String data);
    public static native String sendData();
    
    public static List<String> splitStr(String s, String str){
    	String[] list = str.split(s);
    	List<String> listStr = new ArrayList<>();
    	for(int index = 0; index < list.length; index++) {
    		if(list[index].trim().length() > 0)
    			listStr.add(list[index]);
    	}
    	return listStr;
    }
    public static String getValue(String name, String strList) {
    	strList = strList.replace("}", "");
    	strList = strList.replace("{", "");
    	strList = strList.replace("\n", "");
    	List<String> list1 = splitStr(",", strList);
    	List<String> list2 = new ArrayList<>();
    	for (String string : list1) {
    		list2.addAll(splitStr(":", string));
		}
    	List<String> list3 = new ArrayList<>();
    	for (String string : list2) {
    		list3.addAll(splitStr("\"", string));
    	}
    	boolean isRet = false;
    	if(name.equals("date")) {
    		for (String string : list3) {
        		if(name.equals(string.trim())) {
        			return list3.get(list3.indexOf(string) + 1)+":"+list3.get(list3.indexOf(string) + 2)+":"+list3.get(list3.indexOf(string) + 3);
        		}
        	}
    	}
    	for (String string : list3) {
    		if(isRet) return string.trim();
    		if(name.equals(string.trim())) isRet = true;
    	}
    	return null;
    }
    
    static public String billBack(String billId, String date){
    	String ret = ""
    			+ "{ "
    			+ "	\"returnBillId\":\""+billId+"\","
    			+ " \"date\":\""+date+"\""
    			+ "}";
    	System.out.println("BUTSAALT : " + ret);
    	return returnBill(ret);
    }
    
    static public String pusBridgePosAPI(BigDecimal paymentPkId, BigDecimal amount, BigDecimal vat, BigDecimal cashAmount, BigDecimal nonCashAmount,BigDecimal cityTax, String districtCode, String customerNo, BigDecimal discount, List<CashByInspectionDtl> listRequestByInspectionDtl, List<CashByInspectionDtl> listTreatmentCashByInspectionDtl, List<CashByInspectionDtl> listXrayCashByInspectionDtl, List<CashByInspectionDtl> listExamnationCashByInspectionDtl, List<CashByInspectionDtl> listSurgeryCashByInspectionDtl) {
    	//String str = "{\"amount\":\""+amount.setScale(2, BigDecimal.ROUND_DOWN).toString()+"\",\"vat\":\""+vat.setScale(2, BigDecimal.ROUND_DOWN).toString()+"\",\"cashAmount\":\""+cashAmount.setScale(2, BigDecimal.ROUND_DOWN).toString()+"\",\"nonCashAmount\":\""+nonCashAmount.setScale(2, BigDecimal.ROUND_DOWN).toString()+"\",\"billIdSuffix\":\"\",\"stocks\":null,\"cityTax\":\""+cityTax.setScale(2, BigDecimal.ROUND_DOWN)+"\",\"bankTransactions\":null,\"districtCode\":\""+districtCode+"\",\"customerNo\":\""+customerNo+"\"}";
    	int max = 0;
    	int cnt = 0;
    	if(listRequestByInspectionDtl == null) listRequestByInspectionDtl = new ArrayList<>();
    	if(listTreatmentCashByInspectionDtl == null) listTreatmentCashByInspectionDtl = new ArrayList<>();
    	if(listXrayCashByInspectionDtl == null) listXrayCashByInspectionDtl = new ArrayList<>();
    	if(listExamnationCashByInspectionDtl == null) listExamnationCashByInspectionDtl = new ArrayList<>();
    	if(listSurgeryCashByInspectionDtl == null) listSurgeryCashByInspectionDtl = new ArrayList<>();    	
    	max = 0;
    	if(paymentPkId == null || paymentPkId.compareTo(BigDecimal.ZERO) == 0) max+=listRequestByInspectionDtl.size();
    	for(CashByInspectionDtl item : listTreatmentCashByInspectionDtl) if(item.sumCount() > 0) max++;
    	for(CashByInspectionDtl item : listXrayCashByInspectionDtl) if(item.isSelected()) max++;
    	for(CashByInspectionDtl item : listExamnationCashByInspectionDtl) if(item.isSelected()) max++;
    	for(CashByInspectionDtl item : listSurgeryCashByInspectionDtl) if(item.isSelected()) max++;
    	String stocks = "[ ";
    	//1
    	if(paymentPkId == null || paymentPkId.compareTo(BigDecimal.ZERO) == 0) {
    		for (CashByInspectionDtl item : listRequestByInspectionDtl) {
        		//if(!item.isSelected()) continue;
        		cnt++;
    			stocks = stocks + "{"
    					+ "\"code\": \"" + item.typePkId() + "\""
    					+ ",\"name\": \"" + item.getName() + "\""
    					+ ",\"measureUnit\": \"Ширхэг\"" 
    					+ ",\"qty\": \"1.00\""
    					+ ",\"unitPrice\": \"" + hospital.businessentity.Tool.calculateDiscountAmount(item.getPrice(), discount).setScale(2, BigDecimal.ROUND_DOWN).toString() + "\""
    					+ ",\"totalAmount\": \"" + hospital.businessentity.Tool.calculateDiscountAmount(item.getPrice(), discount).setScale(2, BigDecimal.ROUND_DOWN).toString() + "\""
    					+ ",\"cityTax\": \"" + BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_DOWN).toString() + "\""
    					+ ",\"vat\": \"" + BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_DOWN).toString() + "\""
    					+ ",\"barCode\": \"" + item.typePkId() + "\""
    					+ "}";
    			if(max>cnt) stocks = stocks + ", ";
    		}
    	}
    	//2
    	for (CashByInspectionDtl item : listTreatmentCashByInspectionDtl) {
    		if(item.sumCount() == 0) continue;
    		cnt++;
			stocks = stocks + "{"
					+ "\"code\": \"" + item.typePkId() + "\""
					+ ",\"name\": \"" + item.getName() + "\""
					+ ",\"measureUnit\": \"Ширхэг\"" 
					+ ",\"qty\": \"1.00\""
					+ ",\"unitPrice\": \"" + hospital.businessentity.Tool.calculateDiscountAmount(item.getAmount(), discount).setScale(2, BigDecimal.ROUND_DOWN).toString() + "\""
					+ ",\"totalAmount\": \"" + hospital.businessentity.Tool.calculateDiscountAmount(item.getAmount(), discount).setScale(2, BigDecimal.ROUND_DOWN).toString() + "\""
					+ ",\"cityTax\": \"" + BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_DOWN).toString() + "\""
					+ ",\"vat\": \"" + BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_DOWN).toString() + "\""
					+ ",\"barCode\": \"" + item.typePkId() + "\""
					+ "}";
			if(max>cnt) stocks = stocks + ", ";
		}
    	//3
    	for (CashByInspectionDtl item : listXrayCashByInspectionDtl) {
    		if(!item.isSelected()) continue;
    		cnt++;
			stocks = stocks + "{"
					+ "\"code\": \"" + item.typePkId() + "\""
					+ ",\"name\": \"" + item.getName() + "\""
					+ ",\"measureUnit\": \"Ширхэг\"" 
					+ ",\"qty\": \"1.00\""
					+ ",\"unitPrice\": \"" + hospital.businessentity.Tool.calculateDiscountAmount(item.getPrice(), discount).setScale(2, BigDecimal.ROUND_DOWN).toString() + "\""
					+ ",\"totalAmount\": \"" + hospital.businessentity.Tool.calculateDiscountAmount(item.getPrice(), discount).setScale(2, BigDecimal.ROUND_DOWN).toString() + "\""
					+ ",\"cityTax\": \"" + BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_DOWN).toString() + "\""
					+ ",\"vat\": \"" + BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_DOWN).toString() + "\""
					+ ",\"barCode\": \"" + item.typePkId() + "\""
					+ "}";
			if(max>cnt) stocks = stocks + ", ";
		}
    	//4
    	for (CashByInspectionDtl item : listExamnationCashByInspectionDtl) {
    		if(!item.isSelected()) continue;
    		cnt++;
			stocks = stocks + "{"
					+ "\"code\": \"" + item.typePkId() + "\""
					+ ",\"name\": \"" + item.getName() + "\""
					+ ",\"measureUnit\": \"Ширхэг\"" 
					+ ",\"qty\": \"1.00\""
					+ ",\"unitPrice\": \"" + hospital.businessentity.Tool.calculateDiscountAmount(item.getPrice(), discount).setScale(2, BigDecimal.ROUND_DOWN).toString() + "\""
					+ ",\"totalAmount\": \"" + hospital.businessentity.Tool.calculateDiscountAmount(item.getPrice(), discount).setScale(2, BigDecimal.ROUND_DOWN).toString() + "\""
					+ ",\"cityTax\": \"" + BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_DOWN).toString() + "\""
					+ ",\"vat\": \"" + BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_DOWN).toString() + "\""
					+ ",\"barCode\": \"" + item.typePkId() + "\""
					+ "}";
			if(max>cnt) stocks = stocks + ", ";
		}
    	//5
    	for (CashByInspectionDtl item : listSurgeryCashByInspectionDtl) {
    		if(!item.isSelected()) continue;
    		cnt++;
			stocks = stocks + "{"
					+ "\"code\": \"" + item.typePkId() + "\""
					+ ",\"name\": \"" + item.getName() + "\""
					+ ",\"measureUnit\": \"Ширхэг\"" 
					+ ",\"qty\": \"1.00\""
					+ ",\"unitPrice\": \"" + hospital.businessentity.Tool.calculateDiscountAmount(item.getPrice(), discount).setScale(2, BigDecimal.ROUND_DOWN).toString() + "\""
					+ ",\"totalAmount\": \"" + hospital.businessentity.Tool.calculateDiscountAmount(item.getPrice(), discount).setScale(2, BigDecimal.ROUND_DOWN).toString() + "\""
					+ ",\"cityTax\": \"" + BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_DOWN).toString() + "\""
					+ ",\"vat\": \"" + BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_DOWN).toString() + "\""
					+ ",\"barCode\": \"" + item.typePkId() + "\""
					+ "}";
			if(max>cnt) stocks = stocks + ", ";
		}
    	stocks = stocks + " ]";
    	String str = "{"
    					+ "\"amount\":\""+amount.setScale(2, BigDecimal.ROUND_DOWN).toString()+"\""
    					+ ",\"vat\":\""+vat.setScale(2, BigDecimal.ROUND_DOWN).toString()+"\""
    					+ ",\"cashAmount\":\""+cashAmount.setScale(2, BigDecimal.ROUND_DOWN).toString()+"\""
    					+ ",\"nonCashAmount\":\""+nonCashAmount.setScale(2, BigDecimal.ROUND_DOWN).toString()+"\""
    					+ ",\"billIdSuffix\":\"\""
    					+ ",\"stocks\":"+stocks+""
    					+ ",\"cityTax\":\""+cityTax.setScale(2, BigDecimal.ROUND_DOWN)+"\""
    					+ ",\"bankTransactions\":null"
    					+ ",\"districtCode\":\""+districtCode+"\""
    					+ ",\"posNo\": \"00001\""
    				+ "}";
    	System.out.println(str);
    	return put(str);
    }
    
    static public String cashPusBridgePosAPI(BigDecimal paymentPkId, BigDecimal amount, BigDecimal vat, BigDecimal cashAmount, BigDecimal nonCashAmount,BigDecimal cityTax, String districtCode, String customerNo, BigDecimal discount, List<CashByInspectionDtl> listTreatmentCashByInspectionDtl, List<CashByInspectionDtl> listXrayCashByInspectionDtl, List<CashByInspectionDtl> listExamnationCashByInspectionDtl, List<CashByInspectionDtl> listSurgeryCashByInspectionDtl) {
    	//String str = "{\"amount\":\""+amount.setScale(2, BigDecimal.ROUND_DOWN).toString()+"\",\"vat\":\""+vat.setScale(2, BigDecimal.ROUND_DOWN).toString()+"\",\"cashAmount\":\""+cashAmount.setScale(2, BigDecimal.ROUND_DOWN).toString()+"\",\"nonCashAmount\":\""+nonCashAmount.setScale(2, BigDecimal.ROUND_DOWN).toString()+"\",\"billIdSuffix\":\"\",\"stocks\":null,\"cityTax\":\""+cityTax.setScale(2, BigDecimal.ROUND_DOWN)+"\",\"bankTransactions\":null,\"districtCode\":\""+districtCode+"\",\"customerNo\":\""+customerNo+"\"}";
    	int max = 0;
    	int cnt = 0;
    	if(listTreatmentCashByInspectionDtl == null) listTreatmentCashByInspectionDtl = new ArrayList<>();
    	if(listXrayCashByInspectionDtl == null) listXrayCashByInspectionDtl = new ArrayList<>();
    	if(listExamnationCashByInspectionDtl == null) listExamnationCashByInspectionDtl = new ArrayList<>();
    	if(listSurgeryCashByInspectionDtl == null) listSurgeryCashByInspectionDtl = new ArrayList<>();    	
    	max = 0;
    	for(CashByInspectionDtl item : listTreatmentCashByInspectionDtl) if(item.sumCount() > 0) max++;
    	for(CashByInspectionDtl item : listXrayCashByInspectionDtl) if(item.isSelected()) max++;
    	for(CashByInspectionDtl item : listExamnationCashByInspectionDtl) if(item.isSelected()) max++;
    	for(CashByInspectionDtl item : listSurgeryCashByInspectionDtl) if(item.isSelected()) max++;
    	String stocks = "[ ";
    	
    	//2
    	for (CashByInspectionDtl item : listTreatmentCashByInspectionDtl) {
    		if(item.sumCount() == 0) continue;
    		cnt++;
			stocks = stocks + "{"
					+ "\"code\": \"" + item.typePkId() + "\""
					+ ",\"name\": \"" + item.getName() + "\""
					+ ",\"measureUnit\": \"Ширхэг\"" 
					+ ",\"qty\": \"1.00\""
					+ ",\"unitPrice\": \"" + hospital.businessentity.Tool.calculateDiscountAmount(item.getAmount(), discount).setScale(2, BigDecimal.ROUND_DOWN).toString() + "\""
					+ ",\"totalAmount\": \"" + hospital.businessentity.Tool.calculateDiscountAmount(item.getAmount(), discount).setScale(2, BigDecimal.ROUND_DOWN).toString() + "\""
					+ ",\"cityTax\": \"" + BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_DOWN).toString() + "\""
					+ ",\"vat\": \"" + BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_DOWN).toString() + "\""
					+ ",\"barCode\": \"" + item.typePkId() + "\""
					+ "}";
			if(max>cnt) stocks = stocks + ", ";
		}
    	//3
    	for (CashByInspectionDtl item : listXrayCashByInspectionDtl) {
    		if(!item.isSelected()) continue;
    		cnt++;
			stocks = stocks + "{"
					+ "\"code\": \"" + item.typePkId() + "\""
					+ ",\"name\": \"" + item.getName() + "\""
					+ ",\"measureUnit\": \"Ширхэг\"" 
					+ ",\"qty\": \"1.00\""
					+ ",\"unitPrice\": \"" + hospital.businessentity.Tool.calculateDiscountAmount(item.getPrice(), discount).setScale(2, BigDecimal.ROUND_DOWN).toString() + "\""
					+ ",\"totalAmount\": \"" + hospital.businessentity.Tool.calculateDiscountAmount(item.getPrice(), discount).setScale(2, BigDecimal.ROUND_DOWN).toString() + "\""
					+ ",\"cityTax\": \"" + BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_DOWN).toString() + "\""
					+ ",\"vat\": \"" + BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_DOWN).toString() + "\""
					+ ",\"barCode\": \"" + item.typePkId() + "\""
					+ "}";
			if(max>cnt) stocks = stocks + ", ";
		}
    	//4
    	for (CashByInspectionDtl item : listExamnationCashByInspectionDtl) {
    		if(!item.isSelected()) continue;
    		cnt++;
			stocks = stocks + "{"
					+ "\"code\": \"" + item.typePkId() + "\""
					+ ",\"name\": \"" + item.getName() + "\""
					+ ",\"measureUnit\": \"Ширхэг\"" 
					+ ",\"qty\": \"1.00\""
					+ ",\"unitPrice\": \"" + hospital.businessentity.Tool.calculateDiscountAmount(item.getPrice(), discount).setScale(2, BigDecimal.ROUND_DOWN).toString() + "\""
					+ ",\"totalAmount\": \"" + hospital.businessentity.Tool.calculateDiscountAmount(item.getPrice(), discount).setScale(2, BigDecimal.ROUND_DOWN).toString() + "\""
					+ ",\"cityTax\": \"" + BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_DOWN).toString() + "\""
					+ ",\"vat\": \"" + BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_DOWN).toString() + "\""
					+ ",\"barCode\": \"" + item.typePkId() + "\""
					+ "}";
			if(max>cnt) stocks = stocks + ", ";
		}
    	//5
    	for (CashByInspectionDtl item : listSurgeryCashByInspectionDtl) {
    		if(!item.isSelected()) continue;
    		cnt++;
			stocks = stocks + "{"
					+ "\"code\": \"" + item.typePkId() + "\""
					+ ",\"name\": \"" + item.getName() + "\""
					+ ",\"measureUnit\": \"Ширхэг\"" 
					+ ",\"qty\": \"1.00\""
					+ ",\"unitPrice\": \"" + hospital.businessentity.Tool.calculateDiscountAmount(item.getPrice(), discount).setScale(2, BigDecimal.ROUND_DOWN).toString() + "\""
					+ ",\"totalAmount\": \"" + hospital.businessentity.Tool.calculateDiscountAmount(item.getPrice(), discount).setScale(2, BigDecimal.ROUND_DOWN).toString() + "\""
					+ ",\"cityTax\": \"" + BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_DOWN).toString() + "\""
					+ ",\"vat\": \"" + BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_DOWN).toString() + "\""
					+ ",\"barCode\": \"" + item.typePkId() + "\""
					+ "}";
			if(max>cnt) stocks = stocks + ", ";
		}
    	stocks = stocks + " ]";
    	String str = "{"
    					+ "\"amount\":\""+amount.setScale(2, BigDecimal.ROUND_DOWN).toString()+"\""
    					+ ",\"vat\":\""+vat.setScale(2, BigDecimal.ROUND_DOWN).toString()+"\""
    					+ ",\"cashAmount\":\""+cashAmount.setScale(2, BigDecimal.ROUND_DOWN).toString()+"\""
    					+ ",\"nonCashAmount\":\""+nonCashAmount.setScale(2, BigDecimal.ROUND_DOWN).toString()+"\""
    					+ ",\"billIdSuffix\":\"\""
    					+ ",\"stocks\":"+stocks+""
    					+ ",\"cityTax\":\""+cityTax.setScale(2, BigDecimal.ROUND_DOWN)+"\""
    					+ ",\"bankTransactions\":null"
    					+ ",\"districtCode\":\""+districtCode+"\""
    					+ ",\"posNo\": \"00001\""
    				+ "}";
    	System.out.println(str);
    	return put(str);
    }
    
    static public String test() {
    	return sendData();
    }
    
    static public String bridgeAPI() {
    	try {
        	System.out.println("Даваадорж");
        	String str = "{\"amount\":\"0.00\",\"vat\":\"0.00\",\"cashAmount\":\"0.00\",\"nonCashAmount\":\"0.00\",\"billIdSuffix\":\"\",\"stocks\":null,\"cityTax\":\"0.00\",\"bankTransactions\":null,\"districtCode\":\"51\"}";
        	System.out.println(getValue("amount", str));
        	System.out.println(getValue("districtCode", str));
            String result = sendData();
            System.out.println("result = " + result);
    	}catch(Exception ex) {
    		System.out.println(ex.getMessage());
    	}
    	return "";
    }
}
