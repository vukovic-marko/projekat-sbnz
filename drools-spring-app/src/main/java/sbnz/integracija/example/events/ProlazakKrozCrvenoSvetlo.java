package sbnz.integracija.example.events;

import java.io.Serializable;
import java.util.Date;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;
import org.kie.api.definition.type.Timestamp;

@Role(Role.Type.EVENT)
@Timestamp("executionTime")
@Expires("1h1m")
public class ProlazakKrozCrvenoSvetlo implements Serializable {

	private static final long serialVersionUID = 1L;
	private Date executionTime;
	private String tablice;
	
	public ProlazakKrozCrvenoSvetlo(String tablice) {
		super();
		this.executionTime = new Date();
		this.tablice = tablice;
	}

	public ProlazakKrozCrvenoSvetlo(Date executionTime, String tablice) {
		super();
		this.executionTime = executionTime;
		this.tablice = tablice;
	}
	
	public Date getExecutionTime() {
		return executionTime;
	}
	
	public void setExecutionTime(Date executionTime) {
		this.executionTime = executionTime;
	}
	
	public String getTablice() {
		return tablice;
	}
	
	public void setTablice(String tablice) {
		this.tablice = tablice;
	}
}
