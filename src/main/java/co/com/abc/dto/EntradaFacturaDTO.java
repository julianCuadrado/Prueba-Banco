package co.com.abc.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EntradaFacturaDTO {

	private Long idFactura;
	private Double valorFactura; 
	private Integer convenio;
	
}
