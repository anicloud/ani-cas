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
package org.jasig.cas.ani.account;

import com.ani.earth.commons.dto.AccountDto;
import com.ani.earth.interfaces.AccountServiceFacade;
import org.jasig.cas.util.PatternCheck;
import org.jasig.services.persondir.IPersonAttributes;
import org.jasig.services.persondir.support.AttributeNamedPersonImpl;
import org.jasig.services.persondir.support.StubPersonAttributeDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhaoyu on 16-9-14.
 *
 * @author zhaoyu
 * @date 16-10-20
 * @sine 1.0
 */
public class CasRpcAccountAttributeDao extends StubPersonAttributeDao {
    private final static Logger LOG = LoggerFactory.getLogger(CasRpcAccountAttributeDao.class);
    private AccountServiceFacade accountServiceFacade;

    public void setAccountServiceFacade(AccountServiceFacade accountServiceFacade) {
        this.accountServiceFacade = accountServiceFacade;
    }

    @Override
    public IPersonAttributes getPerson(String uid) {
        LOG.info("call getPerson, uid is {}.", uid);
        if (accountServiceFacade == null) {
            LOG.error("accountServiceFacade is null.");
            throw new NullPointerException("accountServiceFacade is null.");
        }

        AccountDto accountDto = null;
        if (PatternCheck.isEmail(uid)) {
            accountDto = accountServiceFacade.getByEmail(uid);
        }
        else if (PatternCheck.isMobile(uid)) {
            accountDto = accountServiceFacade.getByPhoneNumber(uid);
        }
//        else {
//            accountDto = accountAccessService.getByScreenName(uid);
//        }
        Map<String, List<Object>> attributes = new HashMap<>();
        if (accountDto != null) {
            attributes.put("phoneNumber",
                    Collections.singletonList((Object) accountDto.accountPhoneDto.getPhoneNumber()));
            attributes.put("email",
                    Collections.singletonList((Object) accountDto.email));
            attributes.put("accountId",
                    Collections.singletonList((Object) accountDto.accountId));
        }
        return new AttributeNamedPersonImpl(attributes);
    }
}
