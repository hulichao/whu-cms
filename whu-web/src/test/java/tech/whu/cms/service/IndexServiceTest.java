package tech.whu.cms.service;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tech.whu.basic.model.SystemContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans.xml")
public class IndexServiceTest {
	@Inject
	private IIndexService indexService;
	private static String rp = "C:\\Users\\cjbi\\GitHub\\whu-cms\\whu-web\\src\\main\\webapp";
	@Test
	public void testGenerateTop() {
		SystemContext.setRealPath(rp);
		indexService.generateTop();
	}
	
	@Test
	public void testGenerateBody() {
		SystemContext.setRealPath(rp);
		indexService.generateBody();
	}
	
	@Test
	public void testGenerateBottom() {
		SystemContext.setRealPath(rp);
		indexService.generateBottom();
	}
	
	@Test
	public void testGenerateBanner() {
		SystemContext.setRealPath(rp);
		indexService.generateBanner();
	}
	
	@Test
	public void testGenerateCmsLink() {
		SystemContext.setRealPath(rp);
		indexService.generateCmsLink();
	}
	
}
