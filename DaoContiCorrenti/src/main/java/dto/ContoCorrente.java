package dto;

import java.util.Objects;

public class ContoCorrente{
	private String numeroConto;
	private String abi;
	private String cab;
	private char cin;
	private String nomeTitolare;
	private String cognomeTitolare;
	private String codFiscaleTitolare;
	private double saldo;


	public ContoCorrente(String numeroConto,String abi,String cab,char cin,String nomeTitolare,String cognomeTitolare,String codFiscaleTitolare,double saldo){
		this.numeroConto=numeroConto;
		this.abi=abi;
		this.cab=cab;
		this.cin=cin;
		this.nomeTitolare=nomeTitolare;
		this.cognomeTitolare=cognomeTitolare;
		this.codFiscaleTitolare=codFiscaleTitolare;
		this.saldo=saldo;
	}

	public String getNumeroConto(){
		return numeroConto;
	}

	public void setNumeroConto(String numeroConto){
		this.numeroConto=numeroConto;
	}

	public String getAbi(){
		return abi;
	}

	public void setAbi(String abi){
		this.abi=abi;
	}

	public String getCab(){
		return cab;
	}

	public void setCab(String cab){
		this.cab=cab;
	}

	public char getCin(){
		return cin;
	}

	public void setCin(char cin){
		this.cin=cin;
	}

	public String getNomeTitolare(){
		return nomeTitolare;
	}

	public void setNomeTitolare(String nomeTitolare){
		this.nomeTitolare=nomeTitolare;
	}

	public String getCognomeTitolare(){
		return cognomeTitolare;
	}

	public void setCognomeTitolare(String cognomeTitolare){
		this.cognomeTitolare=cognomeTitolare;
	}

	public String getCodFiscaleTitolare(){
		return codFiscaleTitolare;
	}

	public void setCodFiscaleTitolare(String codFiscaleTitolare){
		this.codFiscaleTitolare=codFiscaleTitolare;
	}

	public double getSaldo(){
		return saldo;
	}

	public void versa(double diff){
		saldo+=diff;
	}
	public void preleva(double diff){
		saldo-=diff;
	}

	@Override
	public boolean equals(Object o){
		if(this==o)
			return true;
		if(!(o instanceof ContoCorrente))
			return false;
		ContoCorrente that=(ContoCorrente)o;
		return numeroConto.equals(that.numeroConto) && abi.equals(that.abi) && cab.equals(that.cab) && cin==that.cin && codFiscaleTitolare.equals(that.codFiscaleTitolare);
	}

}
