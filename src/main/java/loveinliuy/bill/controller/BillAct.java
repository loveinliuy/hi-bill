package loveinliuy.bill.controller;

import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import loveinliuy.bill.model.Bill;
import loveinliuy.bill.model.Message;
import loveinliuy.bill.service.BillService;
import loveinliuy.bill.util.DateUtil;
import loveinliuy.bill.util.SessionUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.joda.time.DateTime;
import org.joda.time.DurationFieldType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String list(String date, @RequestParam(name = "page", defaultValue = "0") Integer page, Model model) {
        DateTime dt;
        if (StringUtils.isEmpty(date)) {
            dt = DateTime.now();
        } else {
            dt = DateUtil.fromString(date);
        }

        Date startTime = dt.withDayOfMonth(1).withTimeAtStartOfDay().toDate();
        Date endTime = dt.withFieldAdded(DurationFieldType.months(), 1).withDayOfMonth(1).withTimeAtStartOfDay().toDate();

        SessionUtil.getCurrentUser()
                .ifPresent(s ->
                        model.addAttribute("bills",
                                service.getBillsBetweenDateRange(s, new Date[]{startTime, endTime}, page)));
        model.addAttribute("date", dt.toDate());
        model.addAttribute("page", page);
        return "bill/list";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String save(Model model) {
        return "bill/add";
    }

    @ResponseBody
    @RequestMapping(value = "/description/validate")
    public Map<String, Boolean> descriptionValid(String date, String description) {
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
