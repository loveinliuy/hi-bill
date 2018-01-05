package loveinliuy.bill.controller;

import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;
import loveinliuy.bill.model.Bill;
import loveinliuy.bill.model.DateRange;
import loveinliuy.bill.model.Message;
import loveinliuy.bill.model.User;
import loveinliuy.bill.service.BillService;
import loveinliuy.bill.service.CostTypeService;
import loveinliuy.bill.util.DateUtil;
import loveinliuy.bill.util.SessionUtil;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    @Autowired
    private CostTypeService costTypeService;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String list(String date, @RequestParam(name = "page", defaultValue = "0") Integer page, Model model) {
        DateTime dt;
        if (StringUtils.isEmpty(date)) {
            dt = DateTime.now();
        } else {
            dt = DateUtil.fromString(date);
        }

        DateRange range = DateRange.fullMonth(dt);

        SessionUtil.getCurrentUser()
                .ifPresent(s -> {
                    model.addAttribute("bills",
                            service.getBillsBetweenDateRange(s, range, page));
                    model.addAttribute("billStatistic",
                            service.getUserBillStatisticBetweenDateRange(s, range));
                });


        model.addAttribute("date", dt.toDate());
        model.addAttribute("page", page);
        return "bill/list";
    }

    @RequestMapping(value = "/chart", method = RequestMethod.GET)
    public String chart(String date, Model model) {
        DateTime dt;
        if (StringUtils.isEmpty(date)) {
            dt = DateTime.now();
        } else {
            dt = DateUtil.fromString(date);
        }
        DateRange range = DateRange.fullMonth(dt);

        SessionUtil.getCurrentUser().ifPresent(s -> {
            model.addAttribute("statistics",
                    service.getUserBillStatisticBetweenDateRangeGroupByCostType(s, range));
        });

        model.addAttribute("date", dt.toDate());
        return "bill/chart";
    }

    @ResponseBody
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public Page<Bill> list(String date, @RequestParam(name = "page", defaultValue = "0") Integer page) {
        DateTime dt;
        if (StringUtils.isEmpty(date)) {
            dt = DateTime.now();
        } else {
            dt = DateUtil.fromString(date);
        }

        DateRange range = DateRange.fullMonth(dt);

        return service.getBillsBetweenDateRange(SessionUtil.getCurrentUser().orElseThrow(IllegalStateException::new), range, page);
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String save(Model model) {
        User user = SessionUtil.getCurrentUser().orElseThrow(IllegalStateException::new);
        model.addAttribute("costTypes", costTypeService.getCostTypesByBillType(user, Bill.Type.values()));
        return "bill/add";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable String id, Model model) {
        User user = SessionUtil.getCurrentUser().orElseThrow(IllegalStateException::new);
        model.addAttribute("costTypes", costTypeService.getCostTypesByBillType(user, Bill.Type.values()));
        model.addAttribute("bill", service.get(id));
        return "bill/add";
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Map<String, Boolean> delete(@PathVariable String id) {
        Boolean success = service.delete(SessionUtil.getCurrentUser().orElseThrow(IllegalStateException::new), id);
        return Collections.singletonMap("success", success);
    }

    @ResponseBody
    @RequestMapping(value = "/description/validate")
    public Map<String, Boolean> descriptionValid(String date, String description) {
        Date dt = DateUtil.fromString(date).toDate();
        Boolean writeToday = service.isWriteThatDay(dt, description);
        return Collections.singletonMap("valid", !writeToday);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String save(Bill bill) {
        Preconditions.checkNotNull(bill, "账单信息不能为空");
        Preconditions.checkArgument(StringUtils.isNotEmpty(bill.getDescription()), "描述信息不能为空");
        Preconditions.checkNotNull(bill.getCost(), "花费信息不能为空！");

        log.debug("get bill: {}", bill);
        service.save(bill);
        return Message.builder().type(Message.Type.SUCCESS).message("保存账单信息成功！")
                .url("/bill").build().toParamString();
    }
}
