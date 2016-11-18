package com.ani.cas.web.login;

import com.ani.earth.commons.dto.AccountDto;
import com.ani.octopus.antenna.core.service.account.AccountAccessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.annotation.Resource;
import javax.security.auth.login.AccountNotFoundException;
import javax.security.auth.login.FailedLoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hey on 16-11-18.
 */
public class CheckEmailPassword extends AbstractController{

    private final static Logger LOG = LoggerFactory.getLogger(EmailCheckController.class);

    @Resource
    private AccountAccessService accountAccessService;



    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        String email =httpServletRequest.getParameter("email");
        String password =httpServletRequest.getParameter("password");

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean accountExist = false;
        AccountDto accountDto = null;
        if (accountAccessService == null || passwordEncoder == null) {
            LOG.error("accountServiceFacade or passwordEncoder is null.");
            throw new NullPointerException("accountServiceFacade or passwordEncoder is null.");
        }
        accountDto = accountAccessService.getByEmail(email);
        if (accountDto != null) {
            if (!passwordEncoder.matches(password, accountDto.password)) {
                accountExist = false;
                LOG.info("Password does not match value on record.");
            }
            else{
                accountExist = true;
            }
        } else {
            LOG.info(email + " not found with SQL query");
            throw new AccountNotFoundException(email + " not found with SQL query");
        }

        ModelAndView mav = new ModelAndView();
        MappingJackson2JsonView view = new MappingJackson2JsonView();
        Map<String,Object> attributes = new HashMap<String,Object>();
        attributes.put("status", accountExist);
        view.setAttributesMap(attributes);
        mav.setView(view);
        return mav;
    }
}
