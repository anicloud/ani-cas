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
package org.jasig.cas.anicel.account;

import org.jasig.services.persondir.IPersonAttributes;
import org.jasig.services.persondir.support.AttributeNamedPersonImpl;
import org.jasig.services.persondir.support.StubPersonAttributeDao;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @autor zhaoyu
 * @date 16-3-11
 * @since JDK 1.7
 */
public class CasAccountAttributeDao extends StubPersonAttributeDao {


    private String accountTableName;
    private String accountPhoneTableName;
    private String emailFieldName = "email";
    private String screenFieldName = "screenName";
    private String phoneFieldName = "phoneNumber";
    private DataSource dataSource;

    public CasAccountAttributeDao() {
    }

    public CasAccountAttributeDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String getEmailFieldName() {
        return emailFieldName;
    }

    public void setEmailFieldName(String emailFieldName) {
        this.emailFieldName = emailFieldName;
    }

    public String getScreenFieldName() {
        return screenFieldName;
    }

    public void setScreenFieldName(String screenFieldName) {
        this.screenFieldName = screenFieldName;
    }

    public String getAccountTableName() {
        return accountTableName;
    }

    public void setAccountTableName(String accountTableName) {
        this.accountTableName = accountTableName;
    }

    public String getAccountPhoneTableName() {return accountPhoneTableName;}

    public void setAccountPhoneTableName(String accountPhoneTableName) {this.accountPhoneTableName = accountPhoneTableName;}

    public String getPhoneFieldName() {return phoneFieldName;}

    public void setPhoneFieldName(String phoneFieldName) {this.phoneFieldName = phoneFieldName;}

    @Override
    public IPersonAttributes getPerson(String uid) {
        String sql = "";
        if (uid.indexOf("@") > 0) {
            sql = " select * from " + accountTableName + " where " + emailFieldName + "=?";
        } else {
            sql = " select * from " + accountPhoneTableName + " where " + phoneFieldName + "=?";
        }

        final Map<String, Object> values = new JdbcTemplate(dataSource).queryForMap(sql, uid);
        Map<String, List<Object>> attributes = new HashMap<String, List<Object>>();
        attributes.put("phoneNumber",
                Collections.singletonList((Object) values.get("phoneNumber")));
        attributes.put("email",
                Collections.singletonList((Object) values.get("email")));
        attributes.put("accountId",
                Collections.singletonList((Object) values.get("accountId")));
        attributes.put("id",
                Collections.singletonList((Object) values.get("Id")));
        return new AttributeNamedPersonImpl(attributes);
    }
}
