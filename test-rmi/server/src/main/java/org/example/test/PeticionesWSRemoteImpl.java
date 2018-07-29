package org.example.test;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Calendar;

public class PeticionesWSRemoteImpl extends UnicastRemoteObject implements
		PeticionesWSRemote {

	private static final long serialVersionUID = -2383047627941866040L;

	public PeticionesWSRemoteImpl() throws RemoteException {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Calendar peticionFechaHora(String coConcentrador)
			throws RemoteException {
		System.out.println("Obteniendo datos de Calendario");
		return Calendar.getInstance();
	}

}
