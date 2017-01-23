package hospital.web.ajax;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

public class SOAPClientSAAJ {
	
	public SOAPClientSAAJ(){
		super();
	}
	
	public void exam(){
		try{
			System.out.println("DAVAADORJ : BEGIN");
			SOAPConnectionFactory connectionFactory = SOAPConnectionFactory.newInstance();
			SOAPConnection connection = connectionFactory.createConnection();
			String url = "https://m.egolomt.mn:7073/persistence.asmx";
			SOAPMessage message = connection.call(createSOAPRequest(), url);
			printSOAPResponse(message);
			
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		System.out.println("DAVAADORJ : END");
	}
	
	private static SOAPMessage createSOAPRequest() throws Exception {
		MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();

        String serverURI = "http://tempuri.org/";

        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("tem", serverURI);

        SOAPBody soapBody = envelope.getBody();
        SOAPElement soapBodyElem = soapBody.addChildElement("Get_new", "tem");
        SOAPElement soapBodyElem0 = soapBodyElem.addChildElement("v0", "tem");
        soapBodyElem0.addTextNode("test");
        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("v1", "tem");
        soapBodyElem1.addTextNode("test");
        SOAPElement soapBodyElem2 = soapBodyElem.addChildElement("v2", "tem");
        soapBodyElem2.addTextNode("test");
        SOAPElement soapBodyElem3 = soapBodyElem.addChildElement("v3", "tem");
        soapBodyElem3.addTextNode("test");
        SOAPElement soapBodyElem4 = soapBodyElem.addChildElement("v4", "tem");
        soapBodyElem4.addTextNode("test");

        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", serverURI  + "Get_new");

        soapMessage.saveChanges();

        /* Print the request message */
        System.out.print("Request SOAP Message = ");
        soapMessage.writeTo(System.out);
        System.out.println();

        return soapMessage;
	}
	
	private static void printSOAPResponse(SOAPMessage soapResponse) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        Source sourceContent = soapResponse.getSOAPPart().getContent();
        System.out.print("\nResponse SOAP Message = ");
        StreamResult result = new StreamResult(System.out);
        transformer.transform(sourceContent, result);
    }

}
