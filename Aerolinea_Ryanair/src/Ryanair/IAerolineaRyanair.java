package Ryanair;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;


public interface IAerolineaRyanair extends Remote  {
	static String IP = "127.0.0.1";
	static String Puerto = "";
	static String Service = "";

	//TODO Aerolinea Gateways method

		/** 
		 * @return Devuelve toda la lista de vuelos que hay en la Aerolinea
		 */
		public ArrayList<VueloRyanair> getAllVuelos() throws RemoteException;
		
		/**
		 * @param aeropuertoDestino
		 * @param aeropuertoOrigen
		 * @param fechaIda
		 * @param fechaVuelta
		 * @param asientos
		 * @return Devuelve una clase vuelo que cumpla con las caracteristicas descritas
		 */
		public VueloRyanair buscarVuelo(String aeropuertoDestino, String aeropuertoOrigen, String fecha, int asientos) throws RemoteException;
		/**
		 * @param aeropuertoOrigen Aeropuerto de Origen
		 * @param fecha Fecha de partida del vuelo
		 * @param asientos Asientos requeridos
		 * @return Devuelve la lista de vuelos que partan desde el aeropuerto origen en la fecha especificada
		 */
		public ArrayList<VueloRyanair> buscarVuelosDesdeOrigen(String aeropuertoOrigen, String fecha, int asientos) throws RemoteException;
		
		/**
		 * @param vuelo Vuelo que se quiere reservar
		 * @param nombre Nombre del usuario que quiere reservar el vuelo
		 * @return True si se ha reservado con exito, false si no
		 */
		public boolean reservarVuelo(VueloRyanair vuelo, String nombre, int plazas) throws RemoteException;



}
