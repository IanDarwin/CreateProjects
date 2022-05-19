package createprojects.parts;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

public class ParseTest {

	/**
	 * FRAGILE TEST - depends on existence of particular values in projectlist.txt
	 */
	@Test
	public void testFileParsing() {
		List<ProjDesc> list = ProjectList.getProjects();
		assertNotNull(list);
		assertFalse(list.isEmpty());
		System.out.println(list);
	}
}
