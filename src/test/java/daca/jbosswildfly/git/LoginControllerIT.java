package daca.jbosswildfly.git;

import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import bootwildfly.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class LoginControllerIT {
	private static final String CHECKED_FIELD = "checked";
	private static final String DESCRIPTION_FIELD = "description";
	private static final String ITEMS_RESOURCE = "/items";
	private static final String ITEM_RESOURCE = "/items/{id}";
	private static final int NON_EXISTING_ID = 999;
	private static final String FIRST_ITEM_DESCRIPTION = "First item";
	private static final String SECOND_ITEM_DESCRIPTION = "Second item";
	private static final String THIRD_ITEM_DESCRIPTION = "Third item";

}
