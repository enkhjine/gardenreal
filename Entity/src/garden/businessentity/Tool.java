package garden.businessentity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class Tool {
	public static String ADDED = "Added";
	public static String DELETE = "Delete";
	public static String MODIFIED = "Modified";
	public static String UNCHANGED = "Unchanged";
	public static String LAST = "Last";
	public static String UserDefaultImage = "data:image/png;base64,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";
	public static String WorkStatusBgColor = "#f56954";
	public static String LunchStatusBgColor = "#46804A";
	public static String ASC = "ASC";
	public static String INPUT = "input";
	public static String SELECTABLE = "select";
	public static String BOOLEAN = "boolean";
	public static String RADIO = "radio";
	public static String MULTISELECT = "multiselect";
	public static String LABEL = "label";
	public static String TEXTAREA = "textarea";
	public static String INPUTNUMBER = "inputnumber";
	
	public static String EXAMINATIONREQUESTTYPE_REQUEST = "Request";
	public static String EXAMINATIONREQUESTTYPE_ACTIVE = "Active";
	public static String EXAMINATIONREQUESTTYPE_TEMPSAVE = "TempSave";
	public static String EXAMINATIONREQUESTTYPE_COMPLETED = "Completed";
	
	public static String WOMAN = "Эм";
	public static String MAN = "Эр";
	
	public static String LABORATORY = "LABORATORY";
	public static String EARNOSETHROAT = "EARNOSETHROAT";
	public static String LUNG = "LUNG";
	public static String SKIN = "SKIN";
	public static String DEFAULT = "DEFAULT";
	public static String ENDO = "ENDO";
	public static String ULTRA = "ULTRA";
	public static String HEMATOLOGY = "HEMATOLOGY";
	public static String CARDIOLOGY = "CARDIOLOGY";
	public static String KIDNEY = "KIDNEY";
	public static String EYE = "EYE";
	public static String TRADITION = "TRADITION";
	public static String GASTRO = "GASTRO";
	public static String ENDOCRINE = "ENDOCRINE";
	public static String PEDIATRICS = "PEDIATRICS";
	public static String NERVOUS = "NERVOUS";
	
	public static String INSPECTIONTYPE_XRAY = "XRAY";
	public static String INSPECTIONTYPE_TREATMENT = "TREATMENT";
	public static String INSPECTIONTYPE_EXAMINATION = "EXAMINATION";
	public static String INSPECTIONTYPE_SURGERY = "SURGERY";
	public static String INSPECTIONPAYMENT = "INSPECTIONPAYMENT";
	public static String INSPECTIONTYPE_MEDICINE = "INSPECTIONMEDICINE";
	public static String INSPECTION = "INSPECTION";
	public static String REHABILITATION ="REHABILITATION";
	
	public static String msgSuccess = "success";
	public static String msgWarning = "warning";
	public static String msgError = "error";
	
	private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES";
    private static final String key = "Mary has one cat";

    public static int pageSizeLimit = 20;
	
	public Tool(){
		
	}
	
	public static String getCustomerStringCardNumber(int cardNumber){
		String lastCardNumber = "";
		if(cardNumber < 10000000) lastCardNumber += "0";
		if(cardNumber < 1000000) lastCardNumber += "0";
		if(cardNumber < 100000) lastCardNumber += "0";
		if(cardNumber < 10000) lastCardNumber += "0";
		if(cardNumber < 1000) lastCardNumber += "0";
		if(cardNumber < 100) lastCardNumber += "0";
		if(cardNumber < 10) lastCardNumber += "0";
		lastCardNumber += ""+cardNumber;
		return lastCardNumber;
	}
	
	static public boolean isNumber(char t){
		if(t == '1') return true;
		if(t == '2') return true;
		if(t == '3') return true;
		if(t == '4') return true;
		if(t == '5') return true;
		if(t == '6') return true;
		if(t == '7') return true;
		if(t == '8') return true;
		if(t == '9') return true;
		if(t == '0') return true;
		return false;
	}
	
	static public boolean isCharMN(char t){
		List<String> list = new ArrayList<>();
		list.add("а");
		list.add("б");
		list.add("в");
		list.add("г");
		list.add("д");
		list.add("е");
		list.add("ё");
		list.add("ж");
		list.add("з");
		list.add("и");
		list.add("й");
		list.add("к");
		list.add("л");
		list.add("м");
		list.add("н");
		list.add("о");
		list.add("ө");
		list.add("п");
		list.add("р");
		list.add("�?");
		list.add("т");
		list.add("у");
		list.add("ү");
		list.add("ф");
		list.add("х");
		list.add("ч");
		list.add("ц");
		list.add("ш");
		list.add("щ");
		list.add("ъ");
		list.add("ы");
		list.add("ь");
		list.add("�?");
		list.add("ю");
		list.add("�?");
		list.add("�?");
		list.add("Б");
		list.add("В");
		list.add("Г");
		list.add("Д");
		list.add("Е");
		list.add("�?");
		list.add("Ж");
		list.add("З");
		list.add("И");
		list.add("Й");
		list.add("К");
		list.add("Л");
		list.add("М");
		list.add("�?");
		list.add("О");
		list.add("Ө");
		list.add("П");
		list.add("Р");
		list.add("С");
		list.add("Т");
		list.add("У");
		list.add("Ү");
		list.add("Ф");
		list.add("Х");
		list.add("Ч");
		list.add("Ц");
		list.add("Ш");
		list.add("Щ");
		list.add("Ъ");
		list.add("Ы");
		list.add("Ь");
		list.add("Э");
		list.add("Ю");
		list.add("Я");
		if(list.contains(t)) return true;
		return false;
	}
	
	static public boolean isRegNumber(String regNumber){
		if(regNumber == null) return false;
		if(regNumber.length() != 10) return false;
		if(isNumber(regNumber.charAt(0))) return false;
		if(isNumber(regNumber.charAt(1))) return false;
		if(!isNumber(regNumber.charAt(2))) return false;
		if(!isNumber(regNumber.charAt(3))) return false;
		if(!isNumber(regNumber.charAt(4))) return false;
		if(!isNumber(regNumber.charAt(5))) return false;
		if(!isNumber(regNumber.charAt(6))) return false;
		if(!isNumber(regNumber.charAt(7))) return false;
		if(!isNumber(regNumber.charAt(8))) return false;
		if(!isNumber(regNumber.charAt(9))) return false;
		return true;
	}
	
	public static Date MILLISECONDZERO(Date date) {
		java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(java.util.Calendar.MILLISECOND, 0);
        calendar.set(java.util.Calendar.SECOND, 0);
        return calendar.getTime();
    }
	
	public static int daysOfMonth(int year, int month){
		int days = 31;
		
		if(month == 4 || month == 6 || month == 9 || month == 11)
		{
			days = 30;
		}else if(month == 2){
			days = 28;
			if(year % 4 == 0 && !(year % 100 == 0 && year % 400 != 0)){
				days = 29;
			}
		}
		
		return days;
	}
	
	public static String getEmployeeStringCardNumber(String number){
		if(number == null) return getEmployeeStringCardNumber(Integer.parseInt(number) + 1);
		if(number.length() < 2) number += "00000";
		if(number.length() < 3) number += "0000";
		if(number.length() < 4) number += "000";
		if(number.length() < 5) number += "00";
		if(number.length() < 6) number += "0";
		return getEmployeeStringCardNumber(Integer.parseInt(number) + 1);
	}
	
	public static String getEmployeeStringCardNumber(int number){
		String lastCardNumber = "";
		if(number < 100000) lastCardNumber += "0";
		if(number < 10000) lastCardNumber += "0";
		if(number < 1000) lastCardNumber += "0";
		if(number < 100) lastCardNumber += "0";
		if(number < 10) lastCardNumber += "0";
		lastCardNumber += ""+number;
		return lastCardNumber;
	}
	
	public static String toTimeOrMinuteString(int d){
		String ret = d < 10 ? "0" : "";
		return ret + d;
	}
	
	public static List<String> getMedicalHistorys(){
		List<String> list = new ArrayList<String>();
		list.add("bloodType");		
		list.add("allergy_ab");
		list.add("allergy_food");
		list.add("allergy_other");
		
		list.add("infectious1");
		list.add("infectious2");
		list.add("infectious3");
		list.add("infectious4");
		list.add("infectious5");
		list.add("infectious6");
		list.add("infectious7");
		list.add("infectious8");
		list.add("infectious9");
		list.add("infectious10");
		list.add("infectious11");
		list.add("infectious12");
		
		list.add("description");
		
		list.add("noninfectious1");
		list.add("noninfectious2");
		list.add("noninfectious3");
		list.add("noninfectious4");
		list.add("noninfectious5");
		list.add("noninfectious6");
		list.add("noninfectious7");
		list.add("noninfectious8");
		list.add("noninfectious9");
		list.add("noninfectious10");
		list.add("noninfectious11");
		
		list.add("familial1");
		list.add("familial2");
		list.add("familial3");
		list.add("familial4");
		list.add("familial5");
		list.add("familial6");
		list.add("familial7");
		list.add("familial8");
		list.add("familial9");
		list.add("familial10");
		list.add("familial11");
		return list;
	}
	
	public static List<String> getMedicalHistoryNames(){
		List<String> list = new ArrayList<String>();
		list.add("Цу�?ны бүл�?г");
		list.add("Эмийн");
		list.add("Хоол");
		list.add("Бу�?ад");
		
		list.add("Улаан бурхан");
		list.add("Гепатит С");
		list.add("ХДХВ");
		list.add("Салхин ц�?ц�?г");
		list.add("Г�?д�?�?ний халдвар");
		list.add("ДОХ");
		list.add("Гахайн хавдар");
		list.add("Уушигны �?үрье");
		list.add("Гепатит �?");
		list.add("Булчирхайн �?үрье");
		list.add("Гепатит В");
		list.add("Г�?д�?�?ний �?үрье");
		
		list.add("Тайлбар");
		
		list.add("Халдвар ба паразитын өвчин");
		list.add("Хорт хавдар");
		list.add("Цу�?ны �?мг�?г");
		list.add("Дотоод шүүрлийн булчирхай");
		list.add("С�?тг�?л м�?др�?лийн �?мг�?г");
		list.add("М�?др�?лийн �?и�?темийн �?мг�?г");
		list.add("�?үдний �?мг�?г");
		list.add("Чих, хөхлөг ургацгийн �?мг�?г");
		list.add("Цу�?ны �?рг�?лтийн �?и�?темийн �?мг�?г");
		list.add("�?мь�?галын �?и�?темийн �?мг�?г");
		list.add("Хоол болов�?руулах �?и�?темийн өвчин");
		
		list.add("Халдвар ба паразитын өвчин");
		list.add("Хорт хавдар");
		list.add("Цу�?ны �?мг�?г");
		list.add("Дотоод шүүрлийн булчирхай");
		list.add("С�?тг�?л м�?др�?лийн �?мг�?г");
		list.add("М�?др�?лийн �?и�?темийн �?мг�?г");
		list.add("�?үдний �?мг�?г");
		list.add("Чих, хөхлөг ургацгийн �?мг�?г");
		list.add("Цу�?ны �?рг�?лтийн �?и�?темийн �?мг�?г");
		list.add("�?мь�?галын �?и�?темийн �?мг�?г");
		list.add("Хоол болов�?руулах �?и�?темийн өвчин");
		
		
		
		
		return list;
	}
	
	public static List<String> getMedicalHistoryDefaultValues(){
		List<String> list = new ArrayList<String>();
		list.add("0");
		
		list.add(" ");
		list.add(" ");
		list.add(" ");
		
		list.add("0");
		list.add("0");
		list.add("0");
		list.add("0");
		list.add("0");
		list.add("0");
		list.add("0");
		list.add("0");
		list.add("0");
		list.add("0");
		list.add("0");
		list.add("0");
		
		list.add(" ");
		
		list.add(" ");
		list.add(" ");
		list.add(" ");
		list.add(" ");
		list.add(" ");
		list.add(" ");
		list.add(" ");
		list.add(" ");
		list.add(" ");
		list.add(" ");
		list.add(" ");
		
		list.add(" ");
		list.add(" ");
		list.add(" ");
		list.add(" ");
		list.add(" ");
		list.add(" ");
		list.add(" ");
		list.add(" ");
		list.add(" ");
		list.add(" ");
		list.add(" ");
		
		
			
		return list;
	}
	
	public static Object deepClone(Object object) {
	   try {
	     ByteArrayOutputStream baos = new ByteArrayOutputStream();
	     ObjectOutputStream oos = new ObjectOutputStream(baos);
	     oos.writeObject(object);
	     ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
	     ObjectInputStream ois = new ObjectInputStream(bais);
	     return ois.readObject();
	   }
	   catch (Exception e) {
	     e.printStackTrace();
	     return null;
	   }
	}
	
	public static boolean isAfter(int byear, int bmonth, int bday, int eyear, int emonth, int eday){
		Date date = new Date(byear, bmonth, bday);
		Date date2 = new Date(eyear, emonth, eday);
		System.out.println(byear+"-"+bmonth+"-"+bday+" -> "+eyear+"-"+emonth+"-"+eday);
		return date.compareTo(date2) == -1;
	}
	
	public static String MD5(String args) throws Exception
    {
		MessageDigest m = MessageDigest.getInstance("MD5");
		m.reset();
		m.update(args.getBytes());
		
		byte[] digest = m.digest();
		BigInteger bigInt = new BigInteger(1,digest);
		String hashtext = bigInt.toString(16);
		
		while(hashtext.length() < 32 ){
		  hashtext = "0"+hashtext;
		}
    	return hashtext;
    }
	
	public static String getChar(String t){
		List<String> list1 = new ArrayList<>();
		List<String> list2 = new ArrayList<>();
		list1.add("а");list2.add("a");
		list1.add("б");list2.add("b");
		list1.add("в");list2.add("v");
		list1.add("г");list2.add("g");
		list1.add("д");list2.add("d");
		list1.add("е");list2.add("ye");
		list1.add("ё");list2.add("yo");
		list1.add("ж");list2.add("j");
		list1.add("з");list2.add("z");
		list1.add("и");list2.add("i");
		list1.add("й");list2.add("i");
		list1.add("к");list2.add("k");
		list1.add("л");list2.add("l");
		list1.add("м");list2.add("m");
		list1.add("н");list2.add("n");
		list1.add("о");list2.add("o");
		list1.add("ө");list2.add("o");
		list1.add("п");list2.add("p");
		list1.add("р");list2.add("r");
		list1.add("�?");list2.add("s");
		list1.add("т");list2.add("t");
		list1.add("у");list2.add("u");
		list1.add("ү");list2.add("yu");
		list1.add("ф");list2.add("f");
		list1.add("х");list2.add("h");
		list1.add("ч");list2.add("ch");
		list1.add("ц");list2.add("ts");
		list1.add("ш");list2.add("sh");
		list1.add("щ");list2.add("shch");
		list1.add("ъ");list2.add("");
		list1.add("ы");list2.add("ii");
		list1.add("ь");list2.add("");
		list1.add("�?");list2.add("e");
		list1.add("ю");list2.add("y");
		list1.add("�?");list2.add("ya");
		
		list1.add("�?");list2.add("A");
		list1.add("Б");list2.add("B");
		list1.add("В");list2.add("V");
		list1.add("Г");list2.add("G");
		list1.add("Д");list2.add("D");
		list1.add("Е");list2.add("YE");
		list1.add("�?");list2.add("YO");
		list1.add("Ж");list2.add("J");
		list1.add("З");list2.add("Z");
		list1.add("И");list2.add("I");
		list1.add("Й");list2.add("I");
		list1.add("К");list2.add("K");
		list1.add("Л");list2.add("L");
		list1.add("М");list2.add("M");
		list1.add("�?");list2.add("N");
		list1.add("О");list2.add("O");
		list1.add("Ө");list2.add("O");
		list1.add("П");list2.add("P");
		list1.add("Р");list2.add("R");
		list1.add("С");list2.add("S");
		list1.add("Т");list2.add("T");
		list1.add("У");list2.add("U");
		list1.add("Ү");list2.add("YU");
		list1.add("Ф");list2.add("F");
		list1.add("Х");list2.add("H");
		list1.add("Ч");list2.add("CH");
		list1.add("Ц");list2.add("TS");
		list1.add("Ш");list2.add("SH");
		list1.add("Щ");list2.add("SHCH");
		list1.add("Ъ");list2.add("");
		list1.add("Ы");list2.add("II");
		list1.add("Ь");list2.add("");
		list1.add("Э");list2.add("E");
		list1.add("Ю");list2.add("Y");
		list1.add("Я");list2.add("Ya");
		
		for(String str : list1){
			if(str.equals(t)) 
				return list2.get(list1.indexOf(str));
		}
		
		return t;
	}

	public static String CToL(String string){
		String ret = "";
		
		for(int i = 0; i < string.length(); i++){
			ret = ret + getChar(string.charAt(i)+"");
		}
		
		return ret;
	}
	
    public static byte[] doCrypto(int cipherMode, byte[] encodedByte) {
        try {
            Key secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(cipherMode, secretKey);
             
            byte[] outputBytes = cipher.doFinal(encodedByte);
            
            return outputBytes;
        } catch (NoSuchPaddingException ex ){
        	ex.printStackTrace();
        } catch (NoSuchAlgorithmException ex ){
        	ex.printStackTrace();
        } catch (InvalidKeyException ex ){
        	ex.printStackTrace();
        } catch (BadPaddingException ex ){
        	ex.printStackTrace();
        } catch (IllegalBlockSizeException ex ){
        	ex.printStackTrace();
        }
		return null;
    }
    
    public static int daysBetween(Date startDate, Date endDate){
        return (int)( (endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24));   
    }
    
    public static BigDecimal calculateDiscountAmount(BigDecimal amount, BigDecimal discountPercent){
    	BigDecimal discountAmount = amount;
    	if(discountPercent.compareTo(new BigDecimal(0)) == 1 && discountPercent.compareTo(new BigDecimal(100)) == -1){
    		discountAmount = discountAmount.divide(new BigDecimal(100)).multiply(discountPercent);
    		discountAmount = amount.subtract(discountAmount);
    	}
    	return discountAmount;
    }
    
    public static int getPageCount(int count) {
    	int pageCount = count%pageSizeLimit == 0 ? count/pageSizeLimit : count/pageSizeLimit + 1;
    	return pageCount;
    }
    
    public static Date addDays(Date date, int days)
    {
    	Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }
}

