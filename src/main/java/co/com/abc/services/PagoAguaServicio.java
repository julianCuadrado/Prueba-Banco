package co.com.abc.services;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import co.com.abc.controllers.ServicoRest;
import co.com.abc.dto.EntradaFacturaDTO;
import co.com.abc.dto.MensajeDTO;
import co.com.abc.dto.RespuestaConsultaDTO;
import co.com.abc.interfaz.ClientePago;
import com.google.gson.Gson;

@Service
public class PagoAguaServicio implements ClientePago{

	private static final Logger LOGGER = Logger.getLogger(PagoAguaServicio.class.getName());
	
	public static final String ERROR_CONSULTA_PAGO = "Error al consultar el pago, por favor intente mas tarde.";
	public static final String ERROR_REALIZAR_PAGO = "Error al realizar el pago, por favor intente mas tarde.";
	public static final String ERROR_REALIZAR_COMPENSACION = "Error al realizar el pago, por favor intente mas tarde.";
	public static final String URL_BASE = "http://130.211.116.156/servicios/pagos/v1/payments/";	
	
	@Autowired
	private ServicoRest servicioRest;
	
	@Override
	public MensajeDTO consultarPago(EntradaFacturaDTO entradaFactura) {
		MensajeDTO salida = new MensajeDTO();
		try {
			ResponseEntity<?> response = servicioRest.ejecutarMetodoGet(URL_BASE+entradaFactura.getIdFactura()+"", null);
			switch (response.getStatusCode()) {
			case OK:
				EntradaFacturaDTO dto = new Gson().fromJson((String) response.getBody(), EntradaFacturaDTO.class);
				salida.setEstado(Boolean.TRUE);
				salida.setObject(dto);
				return salida;
			default:
				salida.setDescripcion(ERROR_CONSULTA_PAGO);
				break;
			}
		} catch (Exception e) {
			salida.setDescripcion(ERROR_CONSULTA_PAGO);
			LOGGER.log(Level.SEVERE, null, e);
		}
		return salida;
	}

	@Override
	public MensajeDTO realizarPago(EntradaFacturaDTO entradaFactura) {
		MensajeDTO salida = new MensajeDTO();
		try {
			Map<String, String> headers = new HashMap<>();
			headers.put("Content-Type", "application/json");
			EntradaFacturaDTO body = new EntradaFacturaDTO();
			body.setValorFactura(entradaFactura.getValorFactura());
			ResponseEntity<?> response = servicioRest.ejecutarMetodoPost(URL_BASE+entradaFactura.getIdFactura(), body, headers);
			switch (response.getStatusCode()) {
			case OK:
				RespuestaConsultaDTO dto = new Gson().fromJson((String) response.getBody(), RespuestaConsultaDTO.class);
				salida.setEstado(Boolean.TRUE);
				salida.setObject(dto);
				return salida;
			default:
				salida.setDescripcion(ERROR_REALIZAR_PAGO);
				break;
			}
		} catch (Exception e) {
			salida.setDescripcion(ERROR_REALIZAR_PAGO);
			LOGGER.log(Level.SEVERE, null, e);
		}
		return salida;
	}

	@Override
	public MensajeDTO compensarPago(EntradaFacturaDTO entradaFactura) {
		MensajeDTO salida = new MensajeDTO();
		try {
			Map<String, String> headers = new HashMap<>();
			headers.put("Content-Type", "application/json");
			EntradaFacturaDTO body = new EntradaFacturaDTO();
			body.setValorFactura(entradaFactura.getValorFactura());
			ResponseEntity<?> response = servicioRest.ejecutarMetodoDelete(URL_BASE+entradaFactura.getIdFactura(), body, headers);
			switch (response.getStatusCode()) {
			case OK:
				RespuestaConsultaDTO dto = new Gson().fromJson((String) response.getBody(), RespuestaConsultaDTO.class);
				salida.setEstado(Boolean.TRUE);
				salida.setObject(dto);
				return salida;
			default:
				salida.setDescripcion(ERROR_REALIZAR_COMPENSACION);
				break;
			}
		} catch (Exception e) {
			salida.setDescripcion(ERROR_REALIZAR_COMPENSACION);
			LOGGER.log(Level.SEVERE, null, e);
		}
		return salida;
	}
}