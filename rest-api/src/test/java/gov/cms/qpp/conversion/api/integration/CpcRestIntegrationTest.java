package gov.cms.qpp.conversion.api.integration;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@WebAppConfiguration
@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:test.properties")
public class CpcRestIntegrationTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
	}

	@Test
	public void testNoSecurityUnprocessedCpcFiles() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
			.get("/cpc/unprocessed-files"))
			.andExpect(status().is(403));
	}

	@Test
	public void testNoSecurityGetCpcFile() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
			.get("/cpc/file/uuid"))
			.andExpect(status().is(403));
	}

	@Test
	public void testNoSecurityProcessCpcFile() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
			.put("/cpc/file/uuid"))
			.andExpect(status().is(403));
	}
}
