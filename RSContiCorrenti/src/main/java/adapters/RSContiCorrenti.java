package adapters;

import business.Business;
import dto.ContoCorrente;
import ports.InputPort;
import ports.RSPort;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
@Path("/conti")
public class RSContiCorrenti implements RSPort{

	private Business business=new Business();

	@Override
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response post(ContoCorrente item){
		Response r;
		if(business.insert(item)!=1){
			r=Response.status(Response.Status.BAD_REQUEST).entity("Dati non inseriti").build();
		}else{
			r=Response.status(201).build();
		}
		return r;
	}

	@Override
	@GET
	@Path("/cognome")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getByCognome(@QueryParam("value") String cognome){
		Response r;
		List<ContoCorrente> result=business.ricercaPerCognome(cognome);
		if(result==null){
			r=Response.status(Response.Status.BAD_REQUEST).entity("Errore nella ricerca per cognome").build();
		}else if(result.isEmpty()){
			r=Response.ok("Nessun conto trovato").build();
		}else{
			r=Response.ok(result).build();
		}
		return r;
	}

	@Override
	@GET
	@Path("/codFiscale")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getByCodFiscale(@QueryParam("value") String codFiscale){
		Response r;
		ContoCorrente result=business.ricercaPerCodFiscale(codFiscale);
		if(result==null){
			r=Response.status(Response.Status.BAD_REQUEST).entity("Errore nella ricerca per codice fiscale").build();
		}else if(result.getNumeroConto().equals("")){
			r=Response.ok("Nessun conto trovato").build();
		}else{
			r=Response.ok(result).build();
		}
		return r;
	}
}
