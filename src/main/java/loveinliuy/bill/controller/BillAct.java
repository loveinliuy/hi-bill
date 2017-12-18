package loveinliuy.bill.controller;

import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import loveinliuy.bill.model.Bill;
import loveinliuy.bill.model.Message;
import loveinliuy.bill.service.BillService;
import loveinliuy.bill.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.Date;
import java.util.Map;

/**
 * description:
 *
 * @author zhangshibo  [2017/12/14].
 */
@Slf4j
@Controller
@RequestMapping(value = "/bill")
public class BillAct {

    @Autowired
    private BillService service;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String save(Model model) {
        return "bill/add";
    }

    @ResponseBody
    @RequestMapping(value = "/description/validate")
    public Map<String, Boolean> descriptionValid(String date, String description){
        Date dt = DateUtil.fromString(date).toDate();
        Boolean writeToday = service.isWriteThatDay(dt, description);
        return Collections.singletonMap("valid", !writeToday);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String save(Bill bill, Model model) {
        Preconditions.checkNotNull(bill, "账单信息不能为空");
        Preconditions.checkArgument(StringUtils.isNotEmpty(bill.getDescription()), "描述信息不能为空");
        Preconditions.checkNotNull(bill.getCost(), "花费信息不能为空！");

        log.debug("get bill: {}", bill);
        service.save(bill);
        return Message.builder().type(Message.Type.SUCCESS).message("保存账单信息成功！").build().toParamString();
    }
}
