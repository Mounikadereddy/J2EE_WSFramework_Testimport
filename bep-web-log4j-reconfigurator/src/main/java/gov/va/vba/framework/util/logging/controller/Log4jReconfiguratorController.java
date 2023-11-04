package gov.va.vba.framework.util.logging.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import gov.va.vba.framework.serverconfig.LoggerConfigurator;

@Controller
public class Log4jReconfiguratorController {

	@RequestMapping(value = "/index.html")
	public String loadIndexPage(Model model) {
		model.addAttribute("logFileLocation", LoggerConfigurator.PROPERTIES_FILE_NAME);
		return "reconfigureLog4j";
	}

	@RequestMapping(value = "/Log4jReconfigurator", method = RequestMethod.POST)
	public String configureLog4JPost() {
		LoggerConfigurator.configure();
		return "log4jConfigured";
	}

}
