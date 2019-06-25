package sbnz.integracija.example;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("app.properties")
public class AppProperties {
	
	private String maven_home = "/opt/maven";
	
	public String getMaven_home() {
		return maven_home;
	}
	
	public void setMaven_home(String maven_home) {
		this.maven_home = maven_home;
	}
}
