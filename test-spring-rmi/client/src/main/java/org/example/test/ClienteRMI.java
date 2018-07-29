package org.example.test;

import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ClienteRMI {

	private static ClienteRMI service;

	private PeticionService peticionService;

	private ClienteRMI() {
		super();
	}

	public static ClienteRMI getService() {
		if (service == null) {
			service = new ClienteRMI();
		}
		return service;
	}

	/***************************************************************************
	 * Metodo que incializa la conexion con el servidor RMI.
	 * 
	 * @param maquina
	 *            Nombre o IP de la maquina que contiene el servicio RMI.
	 * @param puerto
	 *            Puerto por el que escucha el servicio RMI (Defecto: 1099).
	 * @param servicio
	 *            Nombre del Servicio
	 * @throws IOException
	 *             Error de formato en la URL del concentrador WS o error en la
	 *             ejecucion del objeto remoto.
	 * @throws NotBoundException
	 *             Imposible hacer referencia al objeto remoto.
	 **************************************************************************/
	public void init(String maquina, int puerto, String servicio) throws IOException, NotBoundException {

		peticionService = (PeticionService) Naming.lookup("rmi://" + maquina + ":" + puerto + "/" + servicio);

	}

	public void peticion1(String parametro) throws RemoteException {

		peticionService.peticion1(parametro);

	}

}
