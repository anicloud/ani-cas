package com.ani.cas.web.login;
import com.ani.earth.commons.dto.AccountDto;
import com.ani.earth.interfaces.AccountServiceFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hey on 16-10-26.
 */
public class EmailCheckController extends AbstractController{
    @Resource
    AccountServiceFacade accountServiceFacade;

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailCheckController.class);

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        String emailAddress =httpServletRequest.getParameter("email");
        ModelAndView mav = new ModelAndView();
        MappingJackson2JsonView view = new MappingJackson2JsonView();
        Map<String, Object> attributes = new HashMap<String, Object>();
        AccountDto accountDto = accountServiceFacade.getByEmail(emailAddress);
        if(accountDto != null) {
            attributes.put("status", true);
            String avatarUrl = accountDto.accountInfo.photoPath;
            attributes.put("avatarUrl",avatarUrl);
            view.setAttributesMap(attributes);
            mav.setView(view);
        }else{
            attributes.put("status", false);
        }
        return mav;
    }
}
