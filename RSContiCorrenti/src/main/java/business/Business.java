package business;

import adapters.InputContiCorrenti;
import adapters.RSContiCorrenti;
import dto.ContoCorrente;
import ports.InputPort;
import ports.RSPort;

import javax.persistence.criteria.CriteriaBuilder;
import javax.ws.rs.Path;
import java.util.List;

public class Business{

	private InputPort inputPort=new InputContiCorrenti();

	public int insert(ContoCorrente item){
		return inputPort.insert(item);
	}
	public List<ContoCorrente> ricercaPerCognome(String cognome){
		return inputPort.ricercaPerCognome(cognome);
	}
	public ContoCorrente ricercaPerCodFiscale(String codFiscale){
		return inputPort.ricercaPerCodFiscale(codFiscale);
	}
}
