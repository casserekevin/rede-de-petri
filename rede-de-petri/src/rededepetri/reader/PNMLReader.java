package rededepetri.reader;

import java.io.IOException;
import java.io.InvalidClassException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import rededepetri.components.Arco;
import rededepetri.components.Lugar;
import rededepetri.components.Transicao;
import rededepetri.interfaces.Conectavel;

public class PNMLReader {
	private Document document;
	
	ArrayList<Lugar> lugares;
	ArrayList<Transicao> transicoes;
	ArrayList<Arco> arcos;

	public PNMLReader() {

	}

	public ArrayList<Object> read(String filename) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		ArrayList<Object> components = new ArrayList<>();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.parse(filename);
			
			lugares = readLugares();
			System.out.println("lugares lidos");
			transicoes = readTransicoes();
			System.out.println("transicoes lidas");
			arcos = readArcos();
			System.out.println("arcos lidos");
			
			components.add(lugares);
			components.add(transicoes);
			components.add(arcos);
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return components;
	}
	

	private Element getElementByTagName(String tagname) {
		NodeList peopleList = document.getElementsByTagName(tagname);
		
		Node peopleNode = peopleList.item(0);
		if(peopleNode.getNodeType() == Node.ELEMENT_NODE) {
			Element element = (Element) peopleNode;
			return element;
		}
		
		return null;
	}
	
	private ArrayList<Element> getChildElements(Element element){
		ArrayList<Element> array = new ArrayList<Element>();
		
		NodeList nodeList = element.getChildNodes();
		for(int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if(node.getNodeType() == Node.ELEMENT_NODE) {
				Element e = (Element) node;
				array.add(e);
			}
		}
		
		return array;
	}
	
	private Conectavel buscarConectavelById(int id){
		for(int i = 0; i < lugares.size(); i++) {
			Lugar lugar = lugares.get(i);
			if(lugar.getId() == id) {
				return lugar;
			}
		}
		for(int i = 0; i < transicoes.size(); i++) {
			Transicao transicao = transicoes.get(i);
			if(transicao.getId() == id) {
				return transicao;
			}
		}
		
		return null;
	}
	
	private ArrayList<Lugar> readLugares(){
		ArrayList<Lugar> lugares = new ArrayList<>();
		
		Element lugaresElement = getElementByTagName("lugares");
		ArrayList<Element> LugarElements = getChildElements(lugaresElement);
		for(Element element : LugarElements) {
			ArrayList<Element> finalElements = getChildElements(element);
			Lugar lugar = new Lugar();
			for(Element e : finalElements) {
				if(e.getTagName().equals("id")) {
					lugar.setId(Integer.parseInt(e.getTextContent()));
				}
				else if(e.getTagName().equals("nome")){
					lugar.setNome(e.getTextContent());
				}
				else if(e.getTagName().equals("marcas")) {
					lugar.setNumeroDeMarcas(Integer.parseInt(e.getTextContent()));
				}
			}
			lugares.add(lugar);
		}
		
		return lugares;
	}
	
	private ArrayList<Transicao> readTransicoes() {
		ArrayList<Transicao> transicoes = new ArrayList<>();
		
		Element transicoesElement = getElementByTagName("transicoes");
		ArrayList<Element> transicaoElements = getChildElements(transicoesElement);
		for(Element element : transicaoElements) {
			ArrayList<Element> finalElements = getChildElements(element);
			Transicao transicao = new Transicao();
			for(Element e : finalElements) {
				if(e.getTagName().equals("id")) {
					transicao.setId(Integer.parseInt(e.getTextContent()));
				}
				else if(e.getTagName().equals("nome")){
					transicao.setNome(e.getTextContent());
				}
				else if(e.getTagName().equals("prioridade")) {
					transicao.setPrioridade(Integer.parseInt(e.getTextContent()));
				}
			}
			transicoes.add(transicao);
		}
		
		return transicoes;
	}
	
	private ArrayList<Arco> readArcos() throws InvalidClassException {
		ArrayList<Arco> arcos = new ArrayList<>();
		
		Element arcosElement = getElementByTagName("arcos");
		ArrayList<Element> arcoElements = getChildElements(arcosElement);
		for(Element element : arcoElements) {
			ArrayList<Element> finalElements = getChildElements(element);
			Arco arco = new Arco();
			int sourceId = 0;
			int destinationId = 0;
			for(Element e : finalElements) {
				if(e.getTagName().equals("id")) {
					arco.setId(Integer.parseInt(e.getTextContent()));
				}
				else if(e.getTagName().equals("nome")){
					arco.setNome(e.getTextContent());
				}
				else if(e.getTagName().equals("peso")) {
					arco.setPeso(Integer.parseInt(e.getTextContent()));
				}
				else if(e.getTagName().equals("tipo")) {
					arco.setTipo(e.getTextContent());
				}
				else if(e.getTagName().equals("sourceId")) {
					sourceId = Integer.parseInt(e.getTextContent());
				}
				else if(e.getTagName().equals("destinationId")) {
					destinationId = Integer.parseInt(e.getTextContent());
				}
			}
			arco.conectar(buscarConectavelById(sourceId), buscarConectavelById(destinationId));
			arcos.add(arco);
		}
		
		return arcos;
	}
}
