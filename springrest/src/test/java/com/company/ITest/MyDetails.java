package com.company.ITest;

import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

class MyDetails extends ResourceOwnerPasswordResourceDetails {
    public  MyDetails(final Object obj) {
        AdminControllerITest it = (AdminControllerITest) obj;

        setAccessTokenUri("http://localhost:" + it.getPort() + "/oauth/token");
        setClientId("Tamerlan");
        setClientSecret("Tamerlan2004");

        List<String> scopes = new ArrayList<>();
        scopes.add("read");
        scopes.add("write");

        setScope(scopes);
        setUsername("tamerlan_aghayev");
        setPassword("salam");
        setClientAuthenticationScheme(AuthenticationScheme.header);
    }
}