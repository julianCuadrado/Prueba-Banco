package co.com.abc.services;

import org.springframework.stereotype.Service;

import co.com.abc.Pago;
import co.com.abc.PagosInerface;
import co.com.abc.PagosService;
import co.com.abc.ReferenciaFactura;
import co.com.abc.Resultado;
import co.com.abc.ResultadoConsulta;
import co.com.abc.dto.EntradaFacturaDTO;
import co.com.abc.dto.MensajeDTO;
import co.com.abc.interfaz.ClientePago;

import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.ws.BindingProvider;

@Service
public class PagoGasServicio implements ClientePago{
	
	private static final Logger LOGGER = Logger.getLogger(PagoGasServicio.class.getName());
    
    private static final String URL_GAS_WS = "http://130.211.116.156/gas-service/PagosService?wsdl";
    private static final String ENDPOINT_GAS_WS = "http://130.211.116.156/gas-service/PagosService";

	@Override
	public MensajeDTO consultarPago(EntradaFacturaDTO entradaFactura) {
		MensajeDTO salida = new MensajeDTO();
        try {
            PagosService servicio = new PagosService(new URL(URL_GAS_WS));
            PagosInerface ws = servicio.getPagosPort();
            BindingProvider bp = (BindingProvider) ws;
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, ENDPOINT_GAS_WS);
            ReferenciaFactura referenciaFactura = new ReferenciaFactura();
            referenciaFactura.setReferenciaFactura(entradaFactura.getIdFactura()+"");
            ResultadoConsulta consulta = ws.cosultar(referenciaFactura);
            salida.setEstado(Boolean.TRUE);
            salida.setObject(consulta);
        } catch (Exception ex) {
            salida.setDescripcion("Ocurrio un error al consultar la factura, por favor intente mas tarde.");
            LOGGER.log(Level.SEVERE, null, ex);
        }
        return salida;
	}

	@Override
	public MensajeDTO realizarPago(EntradaFacturaDTO entradaFactura) {
		MensajeDTO salida = new MensajeDTO();
        try {
            PagosService servicio = new PagosService(new URL(URL_GAS_WS));
            PagosInerface ws = servicio.getPagosPort();
            BindingProvider bp = (BindingProvider) ws;
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, ENDPOINT_GAS_WS);
            ReferenciaFactura referenciaFactura = new ReferenciaFactura();
            referenciaFactura.setReferenciaFactura(entradaFactura.getIdFactura()+"");
            Pago entrada = new Pago();
            entrada.setReferenciaFactura(referenciaFactura);
            entrada.setTotalPagar(entradaFactura.getValorFactura());
            Resultado resultado = ws.pagar(entrada);
            salida.setEstado(Boolean.TRUE);
            salida.setObject(resultado);
        } catch (Exception ex) {
            salida.setDescripcion("Ocurrio un error al pagar la factura, por favor intente mas tarde.");
            LOGGER.log(Level.SEVERE, null, ex);
        }
        return salida;
	}

	@Override
	public MensajeDTO compensarPago(EntradaFacturaDTO entradaFactura) {
		MensajeDTO salida = new MensajeDTO();
        try {
            PagosService servicio = new PagosService(new URL(URL_GAS_WS));
            PagosInerface ws = servicio.getPagosPort();
            BindingProvider bp = (BindingProvider) ws;
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, ENDPOINT_GAS_WS);
            ReferenciaFactura referenciaFactura = new ReferenciaFactura();
            referenciaFactura.setReferenciaFactura(entradaFactura.getIdFactura()+"");
            Pago pago = new Pago();
            pago.setReferenciaFactura(referenciaFactura);
            pago.setTotalPagar(entradaFactura.getValorFactura());
            Resultado resultado = ws.compensar(pago);
            salida.setEstado(Boolean.TRUE);
            salida.setObject(resultado);
        } catch (Exception ex) {
            salida.setDescripcion("Ocurrio un error realizando la compensacion, por favor intente mas tarde.");
            LOGGER.log(Level.SEVERE, null, ex);
        }
        return salida;
	}
}
