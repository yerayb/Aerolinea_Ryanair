package Ryanair;

public class Assembler {

	public VueloDTO assemble(VueloRyanair vr) {
		VueloDTO v = new VueloDTO();
		v.setAeropuertoDestino(vr.getAeropuertoDestino());
		v.setAeropuertoOrigen(vr.getAeropuertoOrigen());
		v.setAsientosDisponibles(vr.getAsientosDisponibles());
		v.setFecha(vr.getFecha());
		v.setNomAerolinea(vr.getNomAerolinea());
		v.setNumAsientos(vr.getNumAsientos());
		v.setNumVuelo(vr.getNumVuelo());
		return v;
	}
	
	public VueloRyanair disassemble(VueloDTO vr) {
		VueloRyanair v = new VueloRyanair();
		v.setAeropuertoDestino(vr.getAeropuertoDestino());
		v.setAeropuertoOrigen(vr.getAeropuertoOrigen());
		v.setAsientosDisponibles(vr.getAsientosDisponibles());
		v.setFecha(vr.getFecha());
		v.setNomAerolinea(vr.getNomAerolinea());
		v.setNumAsientos(vr.getNumAsientos());
		v.setNumVuelo(vr.getNumVuelo());
		return v;
	}
}
