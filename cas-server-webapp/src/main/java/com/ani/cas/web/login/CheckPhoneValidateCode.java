package com.ani.cas.web.login;

import com.ani.octopus.antenna.core.service.sms.ShortMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hey on 16-10-27.
 */
public class CheckPhoneValidateCode extends AbstractController {
    @Resource
    ShortMessageService shortMessageService;
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailCheckController.class);

    public boolean phoneCodeValidate(String code,String phone){
        return shortMessageService.validateCode(phone,code);
    }


    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        String telephoneNumber =httpServletRequest.getParameter("telephoneNumber");
        String checkNum =httpServletRequest.getParameter("checkNum");
        boolean accountExist = phoneCodeValidate(checkNum,telephoneNumber);

        ModelAndView mav = new ModelAndView();
        MappingJackson2JsonView view = new MappingJackson2JsonView();
        Map<String,Object> attributes = new HashMap<String,Object>();
        attributes.put("status", accountExist);
        view.setAttributesMap(attributes);
        mav.setView(view);
        return mav;
    }
}
