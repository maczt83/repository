package progmatic.bookingmanager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class TemplateTestController {
@RequestMapping(value = {"/templatetest"}, method = GET)
    public String showTemplateTestPage () {
        return "templateTest";
    }    
}
