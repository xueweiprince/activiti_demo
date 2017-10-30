package com.st.demo.web.security;


import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.SessionHolder;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.ConfigAttributeEditor;
import org.springframework.security.web.access.intercept.DefaultFilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.AntPathRequestMatcher;
import org.springframework.security.web.util.RequestMatcher;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import javax.annotation.Resource;

public class MyFilterInvocationSecurityMetadataSource implements FactoryBean<FilterInvocationSecurityMetadataSource> {
    private String resourceQuery;

    @Resource
    private SessionFactory sessionFactory;

    public MyFilterInvocationSecurityMetadataSource() {
    }

    public FilterInvocationSecurityMetadataSource getObject() throws Exception {
        return new DefaultFilterInvocationSecurityMetadataSource(this.buildRequestMap());
    }

    public Class<?> getObjectType() {
        return FilterInvocationSecurityMetadataSource.class;
    }

    public boolean isSingleton() {
        return true;
    }

    protected LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> buildRequestMap() {
        LinkedHashMap requestMap = new LinkedHashMap();
        ConfigAttributeEditor editor = new ConfigAttributeEditor();
        LinkedHashMap resourceMap = this.findResources();
        Iterator var4 = resourceMap.keySet().iterator();

        while(var4.hasNext()) {
            String url = (String)var4.next();
            String role = (String)resourceMap.get(url);
            editor.setAsText(role);
            requestMap.put(new AntPathRequestMatcher(url), (Collection)editor.getValue());
        }

        return requestMap;
    }

    public LinkedHashMap<String, String> findResources() {
        LinkedHashMap resourcesMap = new LinkedHashMap();
//        Finder finder = Finder.create(this.resourceQuery);
//        finder.setDqlType(DqlType.SQL);
//        List resources = this.springSecurityService.queryByFinder(finder);
//        Iterator var4 = resources.iterator();

        Session session = sessionFactory.openSession();

        if(TransactionSynchronizationManager.getResource(sessionFactory)!=null){
            TransactionSynchronizationManager.unbindResource(sessionFactory);
            session = sessionFactory.openSession();
        }

        TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(session));

        //Query query=session.createQuery(resourceQuery);
        SQLQuery sqlQuery=session.createSQLQuery(resourceQuery);
        List resources=sqlQuery.list();
        session.close();


        for (Object object : resources) {
            Object[] obj = (Object[])((Object[])object);
            String url = (String)obj[0];
            String role = (String)obj[1];
            if(StringUtils.isNotBlank(url) && StringUtils.isNotBlank(role)) {
                if(resourcesMap.containsKey(url)) {
                    resourcesMap.put(url, (String)resourcesMap.get(url) + "," + role);
                } else {
                    resourcesMap.put(url, role);
                }
            }
        }

        return resourcesMap;
    }

    public String getResourceQuery() {
        return this.resourceQuery;
    }

    public void setResourceQuery(String resourceQuery) {
        this.resourceQuery = resourceQuery;
    }

}
