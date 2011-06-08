package action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import service.SeasonService;
import service.StructureService;
import com.opensymphony.xwork2.ActionSupport;

import model.Structure;
import model.User;
import model.internal.Message;
import model.listini.Period;
import model.listini.Season;

@ParentPackage(value = "default")
public class SeasonAction extends ActionSupport implements SessionAware {
	private Integer id;
	private Map<String, Object> session = null;
	private List<Season> seasons = null;
	private Season season = null;
	private Integer idPeriod = null;
	private List<Period> periods = new ArrayList<Period>();
	private Message message = new Message();
	@Autowired
	private StructureService structureService = null;	
	@Autowired
	private SeasonService seasonService = null;
	
	@Actions({ 
		@Action(value = "/findAllSeasons", 
				results = { @Result(name = "success", location = "/seasons.jsp") })
	})
	public String findAllSeasons() {
		User user = null;
		Structure structure = null;
		
		user = (User) session.get("user");
		structure = user.getStructure();		
		
		this.setSeasons(this.getSeasonService().findSeasonsByStructureId(structure.getId()));
		//Rimuovere questa istruzione quando tutto sarà  sul DB
		structure.setSeasons(this.getSeasons());
		this.getStructureService().refreshPriceLists(structure);	
		return SUCCESS;
	}

	@Actions({ 
		@Action(value = "/goUpdateSeason", 
				results = { @Result(name = "success", location = "/season_edit.jsp") })
	})
	public String goUpdateSeason() {
		User user = null;
		Structure structure = null;
		Season theSeason = null;
		
		user = (User) session.get("user");
		structure = user.getStructure();
		
		theSeason = this.getSeasonService().findSeasonById(this.getId());
		this.setSeason(theSeason);
		return SUCCESS;
	}
	
	@Actions({ @Action(value = "/saveUpdateSeason", results = {
			@Result(type = "json", name = "success", params = { "root",
					"message" 
					}),
			@Result(name = "input", location = "/validationError.jsp"),
			@Result(type = "json", name = "error", params = { "root", "message" }) 
			}) 
	})
	public String saveUpdateSeason() {
		User user = null;
		Structure structure = null;
		Season oldSeason = null;
		List <Period> periodsWithoutNulls = null; 
		
		
		user = (User) session.get("user");
		structure = user.getStructure();
		
		periodsWithoutNulls = new ArrayList<Period>();
		for (Period currPeriod : this.periods) {
			if ((currPeriod != null )){
				periodsWithoutNulls.add(currPeriod);				
			}
			
		}		
		this.getSeason().setPeriods(periodsWithoutNulls);
		
		this.getSeason().setId_structure(structure.getId());
		
		oldSeason = this.getSeasonService().findSeasonById(this.getSeason().getId());
		
		if (oldSeason == null) {
			// Si tratta di una nuova season
			//Voglio settare l'anno della stagione con l'anno corrente
			int currentYear = Calendar.getInstance().get(Calendar.YEAR);		
			this.getSeason().setYear(currentYear);			
			this.getSeasonService().insertSeason(this.getSeason());			
			this.getMessage().setDescription("Season Added successfully");		
		} else {
			// Si tratta di un update di una season esistente
			//workaround aspettando che la form di edit della season abbia anche il campo year
			int currentYear = Calendar.getInstance().get(Calendar.YEAR);		
			this.getSeason().setYear(currentYear);	
			this.getSeasonService().updateSeason(this.getSeason());			
			this.getMessage().setDescription("Season Updated successfully");			
		}
		this.getMessage().setResult(Message.SUCCESS);
		return SUCCESS;
	}
	
	@Actions({ @Action(value = "/deleteSeason", results = {
			@Result(type = "json", name = "success", params = { "root", "message" }),
			@Result(type = "json", name = "error", params = { "root", "message" }) 
			})

	})
	public String deleteSeason() {
		try{
			this.getSeasonService().deleteSeason(this.season.getId());
			this.getMessage().setResult(Message.SUCCESS);
			this.getMessage().setDescription("Season removed successfully");
			return SUCCESS;
		}catch (Exception e) {
			this.getMessage().setResult(Message.ERROR);
			this.getMessage().setDescription("Error removing Season");
			return ERROR;
		}
	}
	
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	public Integer getIdPeriod() {
		return idPeriod;
	}
	public void setIdPeriod(Integer idPeriod) {
		this.idPeriod = idPeriod;
	}
	public Season getSeason() {
		return season;
	}
	public void setSeason(Season season) {
		this.season = season;
	}
	public List<Season> getSeasons() {
		return seasons;
	}
	public void setSeasons(List<Season> seasons) {
		this.seasons = seasons;
	}
	public List<Period> getPeriods() {
		return periods;
	}
	public void setPeriods(List<Period> periods) {
		this.periods = periods;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public SeasonService getSeasonService() {
		return seasonService;
	}
	public void setSeasonService(SeasonService seasonService) {
		this.seasonService = seasonService;
	}
	public StructureService getStructureService() {
		return structureService;
	}
	public void setStructureService(StructureService structureService) {
		this.structureService = structureService;
	}
	
}