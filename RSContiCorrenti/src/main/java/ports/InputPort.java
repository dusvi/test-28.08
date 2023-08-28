package ports;

import dto.ContoCorrente;

import java.util.List;

public interface InputPort{
	int insert(ContoCorrente item);
	List<ContoCorrente> ricercaPerCognome(String cognome);
	ContoCorrente ricercaPerCodFiscale(String codFiscale);

}
