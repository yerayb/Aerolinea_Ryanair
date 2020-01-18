package Ryanair;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class AerolineaRyanair extends UnicastRemoteObject implements IAerolineaRyanair {

	protected AerolineaRyanair() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<VueloRyanair> vuelos = new ArrayList<VueloRyanair>();
	VueloRyanair v1 = new VueloRyanair();
	VueloRyanair v2 = new VueloRyanair();
	VueloRyanair v3 = new VueloRyanair();
	VueloRyanair v4 = new VueloRyanair();

	@Override
	public ArrayList<VueloRyanair> getAllVuelos() {
		// Vuelo 1
		v1.setAeropuertoOrigen("BIO");
		v1.setAeropuertoDestino("BCN");
		v1.setFecha("20/01/19");
		v1.setNomAerolinea("FR");
		v1.setNumAsientos(550);
		v1.setAsientosDisponibles(550);
		v1.setNumVuelo("FR425");
		vuelos.add(v1);

		// Vuelo 2
		v2.setAeropuertoOrigen("BCN");
		v2.setAeropuertoDestino("MAD");
		v2.setFecha("22/01/19");
		v2.setNomAerolinea("FR");
		v2.setNumVuelo("FR925");
		v2.setAsientosDisponibles(650);
		v2.setNumAsientos(650);
		vuelos.add(v2);

		// Vuelo 3
		v3.setAeropuertoOrigen("VLC");
		v3.setAeropuertoDestino("MAD");
		v3.setFecha("24/01/19");
		v3.setNomAerolinea("FR");
		v3.setNumVuelo("FR256");
		v3.setAsientosDisponibles(530);
		v3.setNumAsientos(530);
		vuelos.add(v3);

		// Vuelo 4
		v4.setAeropuertoOrigen("BIO");
		v4.setAeropuertoDestino("MAD");
		v4.setFecha("20/01/19");
		v4.setNomAerolinea("FR");
		v4.setNumVuelo("FR625");
		v4.setAsientosDisponibles(530);
		v4.setNumAsientos(530);
		vuelos.add(v4);

		for (VueloRyanair vuelo : vuelos) {
			System.out.println("Aerolinea: " + vuelo.getNomAerolinea() + "\n Origen: " + vuelo.getAeropuertoOrigen()
					+ "\n Destino: " + vuelo.getAeropuertoDestino());
		}
		// TODO Auto-generated method stub
		return vuelos;
	}

	@Override
	public VueloRyanair buscarVuelo(String aeropuertoDestino, String aeropuertoOrigen, String fecha,
			 int asientos) {
		// TODO Auto-generated method stub
		int i = 0;
		VueloRyanair vueloEncontrado = null;
		for (i = 0; i < vuelos.size(); i++) {
			if (vuelos.get(i).getAeropuertoOrigen() == aeropuertoOrigen
					&& vuelos.get(i).getAeropuertoDestino() == aeropuertoDestino && vuelos.get(i).getFecha() == fecha && vuelos.get(i).getAsientosDisponibles() >= asientos
					&& vuelos.get(i).getNumAsientos() == asientos) {
				vueloEncontrado = vuelos.get(i);
			}

		}
		return vueloEncontrado;

	}

	@Override
	public ArrayList<VueloRyanair> buscarVuelosDesdeOrigen(String aeropuertoOrigen, String fecha, int asientos) {
		// TODO Auto-generated method stub
		ArrayList<VueloRyanair> vuelosEncontrados = new ArrayList<VueloRyanair>();
		int i;
		for (i = 0; i < vuelos.size(); i++) {
			if (vuelos.get(i).getAeropuertoOrigen() == aeropuertoOrigen && vuelos.get(i).getFecha() == fecha &&vuelos.get(i).getAsientosDisponibles() >= asientos) {
				vuelosEncontrados.add(vuelos.get(i));
			}
		}

		return vuelosEncontrados;
	}

	@Override
	public boolean reservarVuelo(VueloRyanair vuelo, String nombre, int plazas) {
		boolean reserva;
		int plazasdisponibles = vuelo.getAsientosDisponibles();
		int comprobarReserva = plazasdisponibles - plazas;
		if (comprobarReserva >= 0) {
			vuelo.setAsientosDisponibles(vuelo.getAsientosDisponibles() - plazas);
			reserva = true;
		} else {
			reserva = false;
		}

		// TODO Auto-generated method stub
		return reserva;
	}

	public static void main(String[] args) {
		if (args.length != 3) {
			System.out.println("usage: java [policy] [codebase] server.Server [host] [port] [server]");
			System.exit(0);
		}

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		String name = "//" + args[0] + ":" + args[1] + "/" + args[2];

		try {
			IAerolineaRyanair server = new AerolineaRyanair();
			Naming.rebind(name, server);
			System.out.println("* Server '" + name + "' active and waiting...");
		} catch (Exception e) {
			System.err.println("- Exception running the server: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
