package createallprojects.parts;

import java.util.Arrays;
import java.util.List;

public class ProjectList {
	
	/** NB: On Windows this is set to C:, and on *Nix it is set to ~
	 * This may not work for everybody, but it Works For Me(tm).
	 */
	private static final String absPrefix;

	static {
		String osName = System.getProperty("os.name");
		if (osName.startsWith("Windows")) {
			absPrefix = "C:";
		} else {
			String home = System.getProperty("user.home", "/Users/ian");
			absPrefix = home;
		}
	}

    /** List of projects to create.
     * This will need to be read from a file, which 
     * makehandsons will (someday) generate the starting version of
     */
    private static List<ProjDesc> projects = Arrays.asList(
    	    new ProjDesc("donow31", null),
    	    new ProjDesc("donow31solution", null),
    	    new ProjDesc("donow21", null),
    	    new ProjDesc("donow21solution", null),
    	    new ProjDesc("ember", null),
    	    new ProjDesc("embersolution", null),
    	    new ProjDesc("ex21", null),
    	    new ProjDesc("ex21solution", null),
    	    new ProjDesc("ex31", null),
    	    new ProjDesc("ex31solution", null),
    	    new ProjDesc("ex41", null),
    	    new ProjDesc("ex41solution", null),
    	    new ProjDesc("ex42", null),
    	    new ProjDesc("ex42solution", null),
    	    new ProjDesc("ex51", null),
    	    new ProjDesc("ex51solution", null),
    	    new ProjDesc("ex52", null),
    	    new ProjDesc("ex52solution", null),
    	    new ProjDesc("ex53", null),
    	    new ProjDesc("ex53client", null),
    	    new ProjDesc("ex53clientsolution", null),
    	    new ProjDesc("ex53solution", null),
    	    new ProjDesc("ex61", null),
    	    new ProjDesc("ex61solution", null),
    	    new ProjDesc("ex71", null),
    	    new ProjDesc("ex71solution", null),
    	    new ProjDesc("ex81", null),
    	    new ProjDesc("ex81solution", null),
    	    new ProjDesc("minimal", null),
    	    new ProjDesc("samples", null),
    	    new ProjDesc("samples_ee8", null),
    	    
    	    // Now the ones at wonky locations
    	    
		new ProjDesc("datamodel", absPrefix + "/TicketManor/datamodel"),
		new ProjDesc("ticketmanor-ee", absPrefix + "/TicketManorJava/ticketmanor-ee"),
		new ProjDesc("clublist", absPrefix + "/clublist"),
		new ProjDesc("javasrc", absPrefix + "/javasrc"),
		new ProjDesc("jpademo", absPrefix + "/jpademo"),
		new ProjDesc("jsfdemo", absPrefix + "/jsfdemo")
    );

	public static List<ProjDesc> getProjects() {
		return projects;
	}
}