package tech.whu.cms.web;

import java.util.Map;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import tech.whu.cms.auth.AuthUtil;

public class InitServlet extends HttpServlet {

	/**
	 * 日志对象
	 */
	private static final Logger logger = LoggerFactory.getLogger(InitServlet.class);

	private static final long serialVersionUID = 1L;
	private static WebApplicationContext wc;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		// 初始化spring的工厂
		wc = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		// 初始化权限信息
		Map<String, Set<String>> auths = AuthUtil.initAuth("tech.whu.cms.controller");
		this.getServletContext().setAttribute("allAuths", auths);
		this.getServletContext().setAttribute("baseInfo", BaseInfoUtil.getInstacne().read());
		logger.info("系统初始化成功:" + auths);
	}

	public static WebApplicationContext getWebApplicationContext() {
		return wc;
	}

}
