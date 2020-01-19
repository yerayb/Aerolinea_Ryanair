package server.remote;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


import server.data.VueloRyanair;
import server.data.dto.Assembler;
import server.data.dto.VueloDTO;

public class AerolineaRyanair extends UnicastRemoteObject implements IAerolineaRyanair {
	private static final long serialVersionUID = 1L;
	
	private ArrayList<VueloDTO> vuelos;
	private Assembler assem;
	
	protected AerolineaRyanair() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
		vuelos = new ArrayList<VueloDTO>();
		assem = new Assembler();
	}

	

	@Override
	public ArrayList<VueloDTO> getAllVuelos() {
		// Vuelo 1
		System.out.println("Request: +getAllVuelos()");
		VueloRyanair v1 = new VueloRyanair();
		VueloRyanair v2 = new VueloRyanair();
		VueloRyanair v3 = new VueloRyanair();
		VueloRyanair v4 = new VueloRyanair();
		
		v1.setAeropuertoOrigen("BIO");
		v1.setAeropuertoDestino("BCN");
		v1.setFecha("20/01/19");
		v1.setNomAerolinea("FR");
		v1.setNumAsientos(550);
		v1.setAsientosDisponibles(550);
		v1.setNumVuelo("FR425");
		vuelos.add(assem.assemble(v1));

		// Vuelo 2
		v2.setAeropuertoOrigen("BCN");
		v2.setAeropuertoDestino("MAD");
		v2.setFecha("22/01/19");
		v2.setNomAerolinea("FR");
		v2.setNumVuelo("FR925");
		v2.setAsientosDisponibles(650);
		v2.setNumAsientos(650);
		vuelos.add(assem.assemble(v2));

		// Vuelo 3
		v3.setAeropuertoOrigen("VLC");
		v3.setAeropuertoDestino("MAD");
		v3.setFecha("24/01/19");
		v3.setNomAerolinea("FR");
		v3.setNumVuelo("FR256");
		v3.setAsientosDisponibles(530);
		v3.setNumAsientos(530);
		vuelos.add(assem.assemble(v3));

		// Vuelo 4
		v4.setAeropuertoOrigen("BIO");
		v4.setAeropuertoDestino("MAD");
		v4.setFecha("20/01/19");
		v4.setNomAerolinea("FR");
		v4.setNumVuelo("FR625");
		v4.setAsientosDisponibles(530);
		v4.setNumAsientos(530);
		vuelos.add(assem.assemble(v4));

		for (VueloDTO vuelo : vuelos) {
			System.out.println("Aerolinea: " + vuelo.getNomAerolinea() + "\n Origen: " + vuelo.getAeropuertoOrigen()
					+ "\n Destino: " + vuelo.getAeropuertoDestino());
		}
		// TODO Auto-generated method stub
		return vuelos;
	}

	@Override
	public VueloDTO buscarVuelo(String aeropuertoDestino, String aeropuertoOrigen, String fecha,
			 int asientos) {
		System.out.println("Request: +buscarVuelo()");
		int i = 0;
		VueloDTO vueloEncontrado = null;
		for (i = 0; i < vuelos.size(); i++) {
			if (vuelos.get(i).getAeropuertoOrigen() == aeropuertoOrigen
					&& vuelos.get(i).getAeropuertoDestino() == aeropuertoDestino && vuelos.get(i).getFecha() == fecha && vuelos.get(i).getAsientosDisponibles() >= asientos
					&& vuelos.get(i).getNumAsientos() == asientos) {
				vueloEncontrado = vuelos.get(i);
				System.out.println("Vuelo encontrado");
			}

		}
		return vueloEncontrado;

	}

	@Override
	public ArrayList<VueloDTO> buscarVuelosDesdeOrigen(String aeropuertoOrigen, String fecha, int asientos) {
		System.out.println("Request: +buscarVuelosDesdeOrigen()");
		
		ArrayList<VueloDTO> vuelosEncontrados = new ArrayList<VueloDTO>();
		int i;
		for (i = 0; i < vuelos.size(); i++) {
			if (vuelos.get(i).getAeropuertoOrigen() == aeropuertoOrigen && vuelos.get(i).getFecha() == fecha &&vuelos.get(i).getAsientosDisponibles() >= asientos) {
				vuelosEncontrados.add(vuelos.get(i));
			}
		}

		return vuelosEncontrados;
	}

	@Override
	public boolean reservarVuelo(String codVuelo, String nombre, int plazas) {
		System.out.println("Request: +reservarVuelo()");

		boolean reserva;
		VueloDTO v = null;
		
		for(int i=0;i<vuelos.size();i++) {
			if(vuelos.get(i).getNumVuelo()==codVuelo) {
				v = vuelos.get(i);
			}
			
		}
		
		int plazasdisponibles = v.getAsientosDisponibles();
		int comprobarReserva = plazasdisponibles - plazas;
		if (comprobarReserva >= 0) {
			v.setAsientosDisponibles(v.getAsientosDisponibles() - plazas);
			reserva = true;
			System.out.println("Vuelo reservado");
		} else {
			System.out.println("Vuelo no reservado");
			reserva = false;
		}
		
		// TODO Auto-generated method stub
		return reserva;
	}
	
	@Override
	public VueloDTO getVuelo(String codVuelo) {
		VueloDTO v = null;
		for(int i=0;i<vuelos.size();i++) {
			if(vuelos.get(i).getNumVuelo()==codVuelo) {
				v = vuelos.get(i);
			}
		}
		
		return v;
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
