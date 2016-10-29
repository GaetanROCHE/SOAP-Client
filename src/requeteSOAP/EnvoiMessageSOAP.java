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
import javax.xml.transform.*;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import metier.Pays;


public class EnvoiMessageSOAP {

	private SOAPConnection connection;
	private SOAPConnectionFactory soapConnFactory;
	private MessageFactory messageFactory;
	private SOAPMessage message;
	private SOAPPart soapPart;
	private SOAPEnvelope envelope;
	private SOAPBody body;
	private SOAPElement bodyElement;
	private TransformerFactory transformerFactory;
	private Transformer transformer;
	private Source sourceContent;
	private final String urlWS = "http://localhost:8080/PWSPays/services/WebServices";

	public void connexion() {
		try {
			soapConnFactory =SOAPConnectionFactory.newInstance();
			connection = soapConnFactory.createConnection();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public void creationMessageListePays() {
		try { 
			messageFactory = MessageFactory.newInstance();
			message = messageFactory.createMessage();
			soapPart = message.getSOAPPart();
			envelope = soapPart.getEnvelope();
			body = envelope.getBody();
			//On cr�e l'�l�ment principal et le namespace'
			QName bodyName = new QName("http://service", "getTousLesPays", "m"); 
			//On cr�e l�enveloppe
			bodyElement = body.addBodyElement(bodyName); 

			//On sauve le message
			message.saveChanges();
		}
		catch (Exception e) { 
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public void creationMessagePays(String nomPays) {
		try { 
			messageFactory = MessageFactory.newInstance();
			message = messageFactory.createMessage();
			soapPart = message.getSOAPPart();
			envelope = soapPart.getEnvelope();
			body = envelope.getBody();
			//On cr�e l'�l�ment principal et le namespace'
			QName bodyName = new QName("http://service", "getUnPays", "m"); 
			//On cr�e l�enveloppe
			bodyElement = body.addBodyElement(bodyName); 

			//			// On passe les param�tres
			QName qn1 = new QName("nomPays");
			bodyElement.addChildElement(qn1).addTextNode(nomPays);
			//On sauve le message
			message.saveChanges();
		}
		catch (Exception e) { 
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public Pays emissionReceptionUnPays() {
		Pays pays = null;
		try {
			//On envoie le message et on attend la r�ponse
			//On d�finit la destination  
			//On envoie le message 
			SOAPMessage reply = connection.call(message, this.urlWS);

			// traitement de la r�ponse
			// On contr�le la sortie
			soapPart = reply.getSOAPPart();
			envelope = soapPart.getEnvelope();
			body = envelope.getBody();
			System.out.println(envelope.toString());
			System.out.println(body.getFirstChild());
			// on examine les �l�ments renvoy�s dans une liste 
			Node firstChild = body.getFirstChild();
			NodeList listeNoeud = firstChild.getChildNodes();

			Node n = listeNoeud.item(0);
			NodeList l = n.getChildNodes();


			Node nbHabitantNode = l.item(0);
			int nbHabitants = Integer.parseInt(nbHabitantNode.getTextContent());
			System.out.println("nbHabitants : " + nbHabitants);

			Node capitaleNode = l.item(1);
			String nomCapitale = capitaleNode.getTextContent();
			System.out.println("capitale : " + nomCapitale);

			Node nomPaysNode = l.item(2);
			String nomPays = nomPaysNode.getTextContent();

			pays = new Pays(nomPays, nomCapitale, nbHabitants);

			//on ferme la connexion
			connection.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		return pays;
	}

	public List<Pays> emissionReceptionPays() {
		List<Pays> listePays = new ArrayList<Pays>();

		try {
			//On envoie le message et on attend la r�ponse
			//On d�finit la destination  
			//On envoie le message 
			SOAPMessage reply = connection.call(message, this.urlWS);

			// traitement de la r�ponse
			// On contr�le la sortie
			soapPart = reply.getSOAPPart();
			envelope = soapPart.getEnvelope();;
			message.writeTo(System.out);
			body = envelope.getBody();
			// on examine les �l�ments renvoy�s dans une liste 
			Node firstChild = body.getFirstChild();
			NodeList listeNoeud = firstChild.getChildNodes();

			for (int i = 0; i < listeNoeud.getLength(); i++) {
				Node n = listeNoeud.item(i);
				NodeList l =  n.getChildNodes();


				Node nbHabitantNode = l.item(0);
				int nbHabitants = Integer.parseInt(nbHabitantNode.getTextContent());

				Node capitaleNode = l.item(1);
				String nomCapitale = capitaleNode.getTextContent();

				Node nomPaysNode = l.item(2);
				String nomPays = nomPaysNode.getTextContent();

				Pays pays = new Pays(nomPays, nomCapitale, nbHabitants);

				listePays.add(pays);
			}

			//on ferme la connexion
			connection.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		return listePays;
	}

	public List<Pays> getPays() {
		this.connexion();
		this.creationMessageListePays();
		return this.emissionReceptionPays();
	}

	

	public Pays getUnPays(String nomPays) {
		this.connexion();
		this.creationMessagePays(nomPays);
		return this.emissionReceptionUnPays();
	}
}
