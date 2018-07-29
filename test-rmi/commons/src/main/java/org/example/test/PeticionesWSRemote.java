package org.example.test;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Calendar;

public interface PeticionesWSRemote extends Remote {

	Calendar peticionFechaHora(String coConcentrador) throws RemoteException;
}
