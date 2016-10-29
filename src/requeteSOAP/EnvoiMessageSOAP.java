package requeteSOAP;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import java.util.ArrayList;

import metier.Pays;
import org.w3c.dom.NodeList;

public class EnvoiMessageSOAP {

	private SOAPConnection connection;
	private SOAPMessage message;
	private SOAPPart soapPart;
	private SOAPEnvelope envelope;
	private SOAPBody body;

	// on construit une connexion
	public void connexion() {
		try {
			SOAPConnectionFactory soapConnFactory = SOAPConnectionFactory.newInstance();
			connection = soapConnFactory.createConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void creationMessageListePays(String operation, String destination) {
		try {
			MessageFactory messageFactory = MessageFactory.newInstance();
			message = messageFactory.createMessage();
			soapPart = message.getSOAPPart();
			envelope = soapPart.getEnvelope();
			body = envelope.getBody();
			QName bodyName = new QName(destination, operation, "m");
			SOAPElement bodyElement = body.addBodyElement(bodyName);
			message.saveChanges();
		} catch (Exception e) {
			System.out.println(e.getMessage());

		}

	}

	public ArrayList<Pays> emissionReceptionUnPays(String destination)
	{
		ArrayList<Pays> listePays = new ArrayList<>();

		try {
			SOAPMessage reply = connection.call(message, destination);
			soapPart = reply.getSOAPPart();
			envelope = soapPart.getEnvelope();
			body = envelope.getBody();
			NodeList list = body.getFirstChild().getChildNodes();

			for (int i = 0; i < list.getLength(); i++)
			{
				NodeList l = list.item(i).getChildNodes();

				Pays pays = new Pays(l.item(2).getTextContent(), l.item(1).getTextContent(),
						Float.parseFloat(l.item(0).getTextContent()));

				listePays.add(pays);
			}
			// on ferme la connexion
			connection.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return listePays;
	}
}