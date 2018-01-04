package loveinliuy.bill.configuration;

import loveinliuy.bill.config.AppConfig;
import loveinliuy.bill.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * description:
 * add common info to view
 *
 * @author zhangshibo  [2017/12/18].
 */
@Component
public class CommonInfoInterceptor implements HandlerInterceptor {

    @Autowired
    private AppConfig config;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView == null) {
            return;
        }
        ModelMap model = modelAndView.getModelMap();

        model.addAttribute("debug", config.isDebug());
        model.addAttribute("curUser", SessionUtil.getCurrentUser().orElse(null));
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
