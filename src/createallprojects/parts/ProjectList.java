package createallprojects.parts;

import java.util.Arrays;
import java.util.List;
import java.io.*;
import java.nio.file.*;

public class ProjectList {
	
	final static String CONFIG_RESOURCE_NAME = "projectslist.txt";


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

		Path path = Path.of(CONFIG_RESOURCE_NAME);

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
						
		} catch (FileNotFoundException e) {
			throw new ExceptionInInitializerError("Couldn't open " + path.toAbsolutePath());
		} catch (IOException e) {
			throw new ExceptionInInitializerError("Couldn't create projects: " + e.toString());
		}
	}
}
