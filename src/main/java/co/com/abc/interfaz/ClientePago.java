package co.com.abc.interfaz;

import co.com.abc.dto.EntradaFacturaDTO;
import co.com.abc.dto.MensajeDTO;

public interface ClientePago {

	public MensajeDTO consultarPago(EntradaFacturaDTO entradaFactura);
	
	public MensajeDTO realizarPago(EntradaFacturaDTO entradaFactura);
	
	public MensajeDTO compensarPago(EntradaFacturaDTO entradaFactura);
}
