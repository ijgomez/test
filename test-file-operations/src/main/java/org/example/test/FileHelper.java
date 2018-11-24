package org.example.test;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.compress.compressors.CompressorException;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.compressors.CompressorOutputStream;
import org.apache.commons.compress.compressors.CompressorStreamFactory;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.tools.bzip2.CBZip2OutputStream;
import org.example.test.domain.Customer;
import org.example.test.domain.Measure;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public final class FileHelper {

	public static void copy(String inputPathname, String outputPathName) {
		try {
			//File inputFile = new File("files" + File.separator + "input.txt");
			File outputFile = new File(outputPathName);

			OutputStream out = new FileOutputStream(outputFile);
			
			InputStream in = new FileInputStream(new File(inputPathname));

	        // Transfer bytes from in to out
	        byte[] buf = new byte[1024];
	        int len;
	        while ((len = in.read(buf)) > 0) {
	            out.write(buf, 0, len);
	        }
	        
	        in.close();
	        in = new FileInputStream(new File(inputPathname));
	        
	        while ((len = in.read(buf)) > 0) {
	            out.write(buf, 0, len);
	        }
	        
	        
	        out.close();
	        in.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void cut(String pathname, int numeroPartes, String outputFolder) {
		System.out.println("Cut Files");
		try {
			String bzipName = pathname;
			int tamañoParticion = numeroPartes;
			
			File f = new File(bzipName);
			
			long partes = ((f.length() / (tamañoParticion * 1024 * 1024)) +1);
			
			if (partes > 1){
				long contador = 0;
				for(int i = 1 ; i < partes+1 ; i++) {
				
					System.out.println(i + "/" + partes);
					FileInputStream fileInputStream = new FileInputStream(f);
					FileOutputStream fileOutputStream = new FileOutputStream(outputFolder + File.separator + "output2." + i + "_" + partes + ".bz2");
					
					byte datos[] = new byte[32768];
					
					fileInputStream.skip(contador);
					
					int resultado = 0;
					while ((contador <= (tamañoParticion * 1024 * 1024 * i) - 32768) && (resultado != -1)){
						resultado = fileInputStream.read(datos, 0, 32768);
						contador += resultado;
						if(resultado != -1) fileOutputStream.write(datos, 0, resultado);
					}
					fileInputStream.close();
					fileOutputStream.flush();
					fileOutputStream.close();
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static String bzip2(String inputPathname, String outputPathName) {
		String bzipName = outputPathName;
		String tarName = inputPathname;
		
		System.out.println(bzipName.substring(0, bzipName.indexOf(".bz2")));
		
		try {
			if (new File(bzipName).exists()) {
				throw new Exception(bzipName + " already exists. Exiting...");
			}
			System.out.println("Bzip compressing...");
			CBZip2OutputStream bzout = null;
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(bzipName));
			bos.write('B'); // write header characters
			bos.write('Z');
			bzout = new CBZip2OutputStream(bos);
			// Create a buffered input stream out of the file
			// we're trying to add into the Tar
			FileInputStream in = new FileInputStream(tarName);
			// Transfer bytes from the input file
			// to the gzip output stream
			byte[] buffer = new byte[8024];
			int n = 0;
			while (-1 != (n = in.read(buffer))) {
				bzout.write(buffer, 0, n);
			}
			in.close();
			bzout.flush();
			bzout.close();
			bos.close();
			System.out.println("Bzipped successfully to " + bzipName);
		} catch (FileNotFoundException e) {
			System.out.println(tarName + " not found for Bzipping. Exiting...");
		} catch (IOException e) {
			System.out.println("ERROR: Failed or interrupted file operation");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return bzipName;
	}
	
	public static String encriptacion(String texto) {
		MessageDigest md = null;
		
		try {
			md = MessageDigest.getInstance("SHA");
			md.update(texto.getBytes("UTF-8"));
			byte raw[] = md.digest(); 
		    String hash = (new Base64()).encodeToString(raw); 
		    
		    return hash; 
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void transformXML2CSV(String ficheroXMLPathname, String ficheroXSLPathname, String ficheroXSVPathname) {
		File ficheroXML, ficheroXSL, ficheroCSV;
		
		ficheroXML = new File(ficheroXMLPathname);
		
		ficheroXSL = new File(ficheroXSLPathname);
		
		ficheroCSV = new File(ficheroXSVPathname);
		
		transformXML2CSV(ficheroXML, ficheroXSL, ficheroCSV);
	}
	
	public static void transformXML2CSV(File ficheroXML, File ficheroXSL, File ficheroCSV) {
		DocumentBuilderFactory documentBuilderFactory;
		DocumentBuilder documentBuilder;
		Document xmlDocument;
		TransformerFactory transformerFactory;
		Transformer transformer;
		
		try {
			
			documentBuilderFactory = DocumentBuilderFactory.newInstance();
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			
			xmlDocument = documentBuilder.parse(ficheroXML);

			StreamSource stylesource = new StreamSource(ficheroXSL);
			
			transformerFactory = TransformerFactory.newInstance();
			transformer = transformerFactory.newTransformer(stylesource);
			
			Source source = new DOMSource(xmlDocument);
			Result target = new StreamResult(ficheroCSV);
			
			transformer.transform(source, target);
			
		} catch (TransformerConfigurationException | ParserConfigurationException | TransformerFactoryConfigurationError e) {
			e.printStackTrace();
			
		} catch (SAXException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
			
		} catch (TransformerException e) {
			e.printStackTrace();
			
		}
	}

	public static void marshal(Customer customer, String pathname) throws JAXBException {

		File file = new File(pathname);

		System.out.println(file.getAbsolutePath());

		JAXBContext jaxbContext = JAXBContext.newInstance(customer.getClass());
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

		// output pretty printed
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		jaxbMarshaller.marshal(customer, file);
		jaxbMarshaller.marshal(customer, System.out);

	}

	public static void unmarshal(String pathname) throws JAXBException {

		File file = new File(pathname);
		JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);

		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		Customer customer = (Customer) jaxbUnmarshaller.unmarshal(file);
		System.out.println(customer);

	}
	
	public static void compressBz2(String fileName) throws IOException, CompressorException {
		final FileOutputStream out = new FileOutputStream(fileName + ".bz2");

		CompressorOutputStream cos = new CompressorStreamFactory().createCompressorOutputStream(CompressorStreamFactory.BZIP2, out);
		IOUtils.copy(new FileInputStream(fileName), cos);
		cos.close();
	}

	public static void uncompressBz2(String fileName) throws IOException, CompressorException {
		final InputStream is = new FileInputStream(fileName);

		CompressorInputStream in = new CompressorStreamFactory().createCompressorInputStream(CompressorStreamFactory.BZIP2, is);
		IOUtils.copy(in, new FileOutputStream(fileName.replaceAll(".bz2", "")));
		in.close();
	}
	
	public static List<Measure> readCSV(String path, Function<String, Measure> mapper) throws FileNotFoundException, IOException {
		List<Measure> result;
		try (BufferedReader br = Files.newBufferedReader(Paths.get(path))) {
			result = br.lines().map(mapper).collect(Collectors.toList());
		}
		return result;
	}
		
}
