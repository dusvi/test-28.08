package adapters;

import dao.DaoContiCorrenti;
import dao.IDao;
import dto.ContoCorrente;
import exception.DaoException;
import ports.InputPort;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class InputContiCorrenti implements InputPort{


	private static final Logger logger=Logger.getLogger("InputLog");
	private static final IDao<ContoCorrente,String> dao=DaoContiCorrenti.getInstance();
	@Override
	public int insert(ContoCorrente item){
		int result=-1;
		try{
			result=dao.create(item);
		}catch(DaoException e){
			logger.severe(e.getMessage());
		}
		return result;
	}

	@Override
	public List<ContoCorrente> ricercaPerCognome(String cognome){
		List<ContoCorrente> result=new ArrayList<>();
		try{
			result=dao.ricercaPerCognome(cognome);
		}catch(DaoException e){
			logger.severe(e.getMessage());
		}
		return result;
	}

	@Override
	public ContoCorrente ricercaPerCodFiscale(String codFiscale){
		ContoCorrente result=null;
		try{
			result=dao.ricercaPerCodFiscale(codFiscale);
		}catch(DaoException e){
			logger.severe(e.getMessage());
		}
		return result;
	}
}
