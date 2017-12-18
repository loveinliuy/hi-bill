package loveinliuy.bill.controller;

import loveinliuy.bill.model.User;
import loveinliuy.bill.service.BillService;
import loveinliuy.bill.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * description:
 *
 * @author zhangshibo  [2017/12/18].
 */
@Controller
public class WelcomeAct {

    @Autowired
    private BillService service;

    @RequestMapping("/index")
    public String index(Model model) {
        model.addAttribute("recentBills",
                service.getUserRecentBills(SessionUtil.getCurrentUser().orElseThrow(IllegalStateException::new)));
        return "index";
    }
}
