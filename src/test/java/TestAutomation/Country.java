package TestAutomation;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Country {

	 String region;
	    int population;
	    List<String> capital;

	    public String getRegion() {
	        return region;
	    }

	    public void setRegion(String region) {
	        this.region = region;
	    }

	    public int getPopulation() {
	        return population;
	    }

	    public void setPopulation(int population) {
	        this.population = population;
	    }

	    public List<String> getCapital() {
	        return capital;
	    }

	    public void setCapital(List<String> capital) {
	        this.capital = capital;
	    }

	    @Override
	    public String toString() {
	        return "Country{" +
	                "region='" + region + '\'' +
	                ", population=" + population +
	                ", capital=" + capital +
	                '}';
	    }
}
