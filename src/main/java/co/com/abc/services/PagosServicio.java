package co.com.abc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.abc.dto.Convenio;
import co.com.abc.dto.EntradaFacturaDTO;
import co.com.abc.dto.MensajeDTO;
import co.com.abc.interfaz.ClientePago;

@Service
public class PagosServicio implements ClientePago{

	@Autowired
	private PagoGasServicio servicioGas;
	
	@Autowired
	private PagoAguaServicio servicioAgua;

	@Override
	public MensajeDTO consultarPago(EntradaFacturaDTO entradaFactura) {
		MensajeDTO salida = new MensajeDTO();
		Convenio convenio = Convenio.getConvenio(entradaFactura.getConvenio());
		if(convenio != null) {
			switch (convenio) {
			case AGUA:
				return servicioAgua.consultarPago(entradaFactura);
			case GAS:
				return servicioGas.consultarPago(entradaFactura);
			}
		}else {
			salida.setDescripcion("Error el id:"+entradaFactura.getConvenio()+ " del convenio no esta parametrizado.");
		}
		return salida;
	}

	@Override
	public MensajeDTO realizarPago(EntradaFacturaDTO entradaFactura) {
		MensajeDTO salida = new MensajeDTO();
		Convenio convenio = Convenio.getConvenio(entradaFactura.getConvenio());
		if(convenio != null) {
			switch (convenio) {
			case AGUA:
				return servicioAgua.realizarPago(entradaFactura);
			case GAS:
				return servicioGas.realizarPago(entradaFactura);
			}
		}else {
			salida.setDescripcion("Error el id:"+entradaFactura.getConvenio()+ " del convenio no esta parametrizado.");
		}
		return salida;
	}

	@Override
	public MensajeDTO compensarPago(EntradaFacturaDTO entradaFactura)  {
		MensajeDTO salida = new MensajeDTO();
		Convenio convenio = Convenio.getConvenio(entradaFactura.getConvenio());
		if(convenio != null) {
			switch (convenio) {
			case AGUA:
				return servicioAgua.compensarPago(entradaFactura);
			case GAS:
				return servicioGas.compensarPago(entradaFactura);
			}
		}else {
			salida.setDescripcion("Error el id:"+entradaFactura.getConvenio()+ " del convenio no esta parametrizado.");
		}
		return salida;
	}
	
	
}
