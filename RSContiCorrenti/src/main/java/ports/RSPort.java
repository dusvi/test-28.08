package ports;

import dto.ContoCorrente;

import javax.ws.rs.core.Response;

public interface RSPort{
	Response post(ContoCorrente item);
	Response getByCognome(String cognome);
	Response getByCodFiscale(String codFiscale);
}
