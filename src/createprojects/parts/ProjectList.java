package createprojects.parts;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.List;

import org.eclipse.core.resources.ResourcesPlugin;

public class ProjectList {
	
	final static String CONFIG_RESOURCE_NAME = "projectlist.txt";

	/** 
	 * The input is a list of projects, one per line. An optional
	 * second field is the absolute path but excluding the leading "C:"
	 * (so on Windows it goes in C:/whatever whereas in Unix/Linux
	 * it goes in ~/whatever).
	 */
	public static List<ProjDesc> getProjects() {
		String absPrefix;
		String osName = System.getProperty("os.name");
		if (osName.startsWith("Windows")) {
			absPrefix = "C:";
		} else {
			String home = System.getProperty("user.home", "/home/student");
			absPrefix = home;
		}

		var workspaceLoc = 
				ResourcesPlugin
				.getWorkspace()
				.getRoot()
				.getLocation()
				.toString();
		
		Path path = Path.of(workspaceLoc, CONFIG_RESOURCE_NAME);

		try {
			return
				Files.lines(path)
					.map(s->{
						String[] nad = s.split("\\s+");
						switch(nad.length) {
						case 1: return new ProjDesc(s, null);
						case 2: return new ProjDesc(nad[0], absPrefix + nad[1]);
						default: throw new ExceptionInInitializerError("Invalid line in project list: " + s);
						}
					})
					.toList();
						
		} catch (FileNotFoundException | NoSuchFileException e) {
			throw new ExceptionInInitializerError("Couldn't open " + path.toAbsolutePath());
		} catch (IOException e) {
			throw new ExceptionInInitializerError("Couldn't create projects: " + e.toString());
		}
	}
}
