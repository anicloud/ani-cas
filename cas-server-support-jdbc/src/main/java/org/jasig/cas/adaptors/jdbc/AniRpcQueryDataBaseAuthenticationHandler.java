/*
 * Licensed to Apereo under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Apereo licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License.  You may obtain a
 * copy of the License at the following location:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.jasig.cas.adaptors.jdbc;

import com.ani.earth.commons.dto.AccountDto;
import com.ani.octopus.antenna.core.service.account.AccountAccessService;
import org.jasig.cas.authentication.HandlerResult;
import org.jasig.cas.authentication.PreventedException;
import org.jasig.cas.authentication.UsernamePasswordCredential;
import org.jasig.cas.authentication.handler.support.AbstractUsernamePasswordAuthenticationHandler;
import org.jasig.cas.util.PatternCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.security.auth.login.AccountNotFoundException;
import javax.security.auth.login.FailedLoginException;
import java.security.GeneralSecurityException;

/**
 * Created by zhaoyu on 16-9-14.
 *
 * @author zhaoyu
 * @date 16-10-20
 * @sine 1.0
 */
public class AniRpcQueryDataBaseAuthenticationHandler extends AbstractUsernamePasswordAuthenticationHandler {
    private final static Logger LOG = LoggerFactory.getLogger(AniRpcQueryDataBaseAuthenticationHandler.class);
    private AccountAccessService accountaccessService;
    private PasswordEncoder passwordEncoder = null;

    @Override
    protected HandlerResult authenticateUsernamePasswordInternal(UsernamePasswordCredential transformedCredential) throws GeneralSecurityException, PreventedException {
        LOG.info("call authenticateUsernamePasswordInternal, name is {}.", transformedCredential.getUsername());
        if (accountaccessService == null || passwordEncoder == null) {
            LOG.error("accountServiceFacade or passwordEncoder is null.");
           throw new NullPointerException("accountServiceFacade or passwordEncoder is null.");
        }
        AccountDto accountDto = null;
        if (PatternCheck.isEmail(transformedCredential.getUsername())) {
            accountDto = accountaccessService.getByEmail(transformedCredential.getUsername());
        }
        else if (PatternCheck.isMobile(transformedCredential.getUsername())) {
            accountDto = accountaccessService.getByPhoneNumber(transformedCredential.getUsername());
            return createHandlerResult(transformedCredential, this.principalFactory.createPrincipal(transformedCredential.getUsername()), null);
        }
//        else {
//            accountDto = accountaccessService.getByScreenName(transformedCredential.getUsername());
//        }
        if (accountDto != null) {
            if (!passwordEncoder.matches(transformedCredential.getPassword(), accountDto.password)) {
                LOG.info("Password does not match value on record.");
                throw new FailedLoginException("Password does not match value on record.");
            }
        } else {
            LOG.info(transformedCredential.getUsername() + " not found with SQL query");
            throw new AccountNotFoundException(transformedCredential.getUsername() + " not found with SQL query");
        }

        return createHandlerResult(transformedCredential, this.principalFactory.createPrincipal(transformedCredential.getUsername()), null);
    }

    public void setAccountAccessService(AccountAccessService accountAccessService) {
        this.accountaccessService = accountAccessService;
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
}
