package org.example.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Provider;
import java.security.PublicKey;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class with methods with common security operations.
 * 
 * @author ijgomez
 *
 */
public final class SecurityHelper {

	private static final Logger LOGGER = LogManager.getLogger(SecurityHelper.class);

	/**
	 * Method that reads a file that contains a certificate in x509 format, and prints the certificate information in the log.
	 * 
	 * @param pathname
	 *            File with the x 509 certificate.
	 * @throws IOException
	 *             Error reading the file.
	 * @throws CertificateException
	 *             Error reading the certificate.
	 */
	public static void read(final String pathname) throws IOException, CertificateException {
		InputStream inputStream;

		LOGGER.info("JVM Properties.");
		LOGGER.info("--------------------------------------------------------");
		LOGGER.info("java.version=" + System.getProperty("java.version"));
		LOGGER.info("");

		LOGGER.info("JVM Providers.");
		LOGGER.info("--------------------------------------------------------");

		for (Provider provider : Security.getProviders()) {
			LOGGER.info(provider);
		}

		LOGGER.info("");
		LOGGER.info("Reading a File");
		LOGGER.info("--------------------------------------------------------");

		inputStream = new FileInputStream(new File(pathname));
		
		CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");

		Certificate certificate = certificateFactory.generateCertificate(inputStream);

		LOGGER.info("");
		LOGGER.info("FILE LOADED...");
		LOGGER.info("");
		LOGGER.info("Information");
		LOGGER.info("----------------------------------------------------");
		LOGGER.info("Type: " + certificate.getType());
		LOGGER.info("Class: " + certificate.getClass());

		PublicKey publicKey = certificate.getPublicKey();

		LOGGER.info("");
		LOGGER.info("Public Key");
		LOGGER.info("----------------------------------------------------");
		LOGGER.info("Public Key: " + publicKey);
		LOGGER.info("Public Key Algorithm: " + publicKey.getAlgorithm());
		LOGGER.info("Public Key Format: " + publicKey.getFormat());
		LOGGER.info("Public Key Encoded: " + Arrays.toString(publicKey.getEncoded()));

		X509Certificate x509Certificate = (X509Certificate) certificate;

		LOGGER.info("");
		LOGGER.info("X509Certificate");
		LOGGER.info("----------------------------------------------------");
		LOGGER.info("  X509 Basic Constraints: " + x509Certificate.getBasicConstraints());
		LOGGER.info("  X509 Signature Algorith name: " + x509Certificate.getSigAlgName());
		LOGGER.info("  X509 Signature Algorith OID: " + x509Certificate.getSigAlgOID());
		LOGGER.info("  X509 Type: " + x509Certificate.getType());
		LOGGER.info("  X509 Version: " + x509Certificate.getVersion());
		LOGGER.info("  X509 Class: " + x509Certificate.getClass());
		LOGGER.info("  X509 Critical Extension OID: " + x509Certificate.getCriticalExtensionOIDs());
		LOGGER.info("  X509 Non Critical Extension OID: " + x509Certificate.getNonCriticalExtensionOIDs());

		LOGGER.info("  X509 Encoded: " + Arrays.toString(x509Certificate.getEncoded()));
		LOGGER.info("  X509 Issuer DN: " + x509Certificate.getIssuerDN());
		boolean[] keyUsage = x509Certificate.getKeyUsage();
		LOGGER.info("  X509 Key Usage: " + Arrays.toString(keyUsage));
		if (keyUsage != null && keyUsage.length == 9) {
			LOGGER.info("    Digital Signature: " + keyUsage[0]);
			LOGGER.info("    Non Repudiation: " + keyUsage[1]);
			LOGGER.info("    Key Encipherment: " + keyUsage[2]);
			LOGGER.info("    Data Encipherment: " + keyUsage[3]);
			LOGGER.info("    Key Agreement: " + keyUsage[4]);
			LOGGER.info("    Key CertSign: " + keyUsage[5]);
			LOGGER.info("    CRL Sign: " + keyUsage[6]);
			LOGGER.info("    Encipher Only: " + keyUsage[7]);
			LOGGER.info("    Decipher Only: " + keyUsage[8]);
		}

		LOGGER.info("  X509 Not After: " + x509Certificate.getNotAfter());
		LOGGER.info("  X509 Not Before: " + x509Certificate.getNotBefore());
		LOGGER.info("  X509 SerialNumber: " + x509Certificate.getSerialNumber());
		LOGGER.info("  X509 Subject DN: " + x509Certificate.getSubjectDN());

		LOGGER.info("  X509 Has Unsupported Critical Extension: " + x509Certificate.hasUnsupportedCriticalExtension());

		inputStream.close();
	}

	/**
	 * New Instance.
	 */
	private SecurityHelper() {
		super();
	}
}
