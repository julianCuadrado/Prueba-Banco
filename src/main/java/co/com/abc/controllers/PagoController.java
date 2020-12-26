package co.com.abc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import co.com.abc.dto.EntradaFacturaDTO;
import co.com.abc.dto.MensajeDTO;
import co.com.abc.services.PagosServicio;

@RestController
@RequestMapping("pago")
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
public class PagoController {
	
	@Autowired
	private PagosServicio servicioPagos;
	

	@GetMapping("info")
    public ResponseEntity<String> info(){
        return ResponseEntity.status(HttpStatus.OK).body("Servicios pago OK");
    }
	
	@PostMapping("consultarPago")
    public ResponseEntity<?> consultarPago(@RequestBody EntradaFacturaDTO entradaFactura){
		return new ResponseEntity<MensajeDTO>(servicioPagos.consultarPago(entradaFactura), HttpStatus.OK);
    }
	
	@PostMapping("realizarPago")
    public ResponseEntity<?> realizarPago(@RequestBody EntradaFacturaDTO entradaFactura){
		return new ResponseEntity<MensajeDTO>(servicioPagos.realizarPago(entradaFactura), HttpStatus.OK);
    }
	
	@PostMapping("compensarPago")
    public ResponseEntity<?> compensarPago(@RequestBody EntradaFacturaDTO entradaFactura){
		return new ResponseEntity<MensajeDTO>(servicioPagos.compensarPago(entradaFactura), HttpStatus.OK);
    }
}
