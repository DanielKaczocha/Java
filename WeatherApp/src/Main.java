
import java.io.*;

import java.net.*;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class Main {

	public static void main(String[] args) throws Exception {

		try {

			String kod1 = "http://api.worldweatheronline.com/premium/v1/weather.ashx?key=";
			////////////////////////////////////////////////
			////////////////////////////////////////////////
			String key = "88be8c86cb6f49c490d105358181005"; //<---------- ABY OTRZYMAĆ NOWY KLUCZ NALEŻY STWORZYĆ KONTO 2 MIESIĘCZNE NA STRONIE https://developer.worldweatheronline.com 
			////////////////////////////////////////////////
			////////////////////////////////////////////////
			String kod2 = "&q=";
			String miasto = "Szczecin";
			String kod3 = "&format=xml&num_of_days=";
			String dni = "5";

			 Scanner scan = new Scanner(System.in);
			 System.out.println("Podaj nazwe miasta lub wpółrzędne geograficzne: ");
			 miasto = scan.nextLine();
			 System.out.println("Podaj ilość dni prognozy: ");
			 dni = scan.nextLine();

			String kod = kod1 + key + kod2 + miasto + kod3 + dni;

			URL url1 = new URL(kod);
			URLConnection url2 = url1.openConnection();
			InputStream istream = url2.getInputStream();

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

			Document doc = dBuilder.parse(istream);

			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("weather");

			System.out.println(miasto);
			System.out.println("///////////////////////////////////");
			
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				System.out.println("-----------------------------------");

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;

					System.out.println("Dnia: " + eElement.getElementsByTagName("date").item(0).getTextContent());
					System.out.println("Opis pogody: " + eElement.getElementsByTagName("weatherDesc").item(0).getTextContent());
					System.out.println("Temperatura: Od "
							+ eElement.getElementsByTagName("mintempC").item(0).getTextContent() + "ºC" + " do "
							+ eElement.getElementsByTagName("maxtempC").item(0).getTextContent() + "ºC");
					System.out.println("Opady (mm): " + eElement.getElementsByTagName("precipMM").item(0).getTextContent());
					
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

