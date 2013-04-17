/*
 * JBoss, Home of Professional Open Source
 * Copyright 2012 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @authors tag. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This copyrighted material is made available to anyone wishing to use,
 * modify, copy, or redistribute it subject to the terms and conditions
 * of the GNU Lesser General Public License, v. 2.1.
 * This program is distributed in the hope that it will be useful, but WITHOUT A
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public License,
 * v.2.1 along with this distribution; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA  02110-1301, USA.
 */

package org.switchyard.component.http.config.model;

import javax.xml.namespace.QName;

import org.switchyard.config.Configuration;
import org.switchyard.config.Configurations;
import org.switchyard.config.model.BaseModel;
import org.switchyard.config.model.Descriptor;
import org.switchyard.config.model.composite.v1.V1BindingModel;

/**
 * A model that holds the HTTP gateway configuration.
 * 
 * @author Magesh Kumar B <mageshbk@jboss.com> (C) 2012 Red Hat Inc.
 */
public class HttpBindingModel extends V1BindingModel {

    /**
     *  Prefix for HTTP Gateway Configuration.
     */
    public static final String HTTP = "http";

    /**
     * Default namespace for HTTP Gateway Configuration.
     */
    public static final String DEFAULT_NAMESPACE = 
        "urn:switchyard-component-http:config:1.0";

    /**
     * Config property names.
     */
    private static final String ADDRESS = "address";
    private static final String CONTEXT_PATH = "contextPath";
    private static final String METHOD = "method";
    private static final String CONTENT_TYPE = "contentType";

    private QName _serviceName;
    private String _contextPath;
    private String _address;
    private String _method;
    private String _contentType;

    private Configuration _environment = Configurations.newConfiguration();

    /**
     * Constructor.
     */
    public HttpBindingModel() {
        super(HTTP, DEFAULT_NAMESPACE);
        setModelChildrenOrder(CONTEXT_PATH, ADDRESS, METHOD, CONTENT_TYPE);
    }

    /**
     * Create a HttpBindingModel using configuration and descriptor.
     * 
     * @param config the HttpGateway configuration
     * @param desc the HttpGateway descriptor
     */
    public HttpBindingModel(Configuration config, Descriptor desc) {
        super(config, desc);
        setModelChildrenOrder(CONTEXT_PATH, ADDRESS, METHOD, CONTENT_TYPE);
    }

    /**
     * Returns the HTTP Service name.
     * 
     * @return the serviceName
     */
    public QName getServiceName() {
        if (_serviceName == null) {
            _serviceName = isServiceBinding() ? getService().getQName() : getReference().getQName();
        }
        return _serviceName;
    }

    /**
     * Sets the HTTP Service name.
     * 
     * @param serviceName the serviceName to set
     */
    public void setServiceName(QName serviceName) {
        _serviceName = serviceName;
    }

    /**
     * Gets the Endpoint's context path.
     * 
     * @return the endpoint contextPath
     */
    public String getContextPath() {
        if (_contextPath == null) {
            Configuration childConfig = getModelConfiguration().getFirstChild(CONTEXT_PATH);
            if (childConfig != null) {
                _contextPath = childConfig.getValue();
            }
        }
        return _contextPath;
    }

    /**
     * Sets the HTTP URL.
     * 
     * @param contextPath the endpoint contextPath
     */
    public void setContextPath(String contextPath) {
        _contextPath = contextPath;
        Configuration childConfig = getModelConfiguration().getFirstChild(CONTEXT_PATH);
        if (childConfig == null) {
            ValueModel contextPathConfig = new ValueModel(CONTEXT_PATH);
            contextPathConfig.setValue(contextPath);
            setChildModel(contextPathConfig);
        } else {
            childConfig.setValue(contextPath);
        }
    }

    /**
     * Gets the HTTP URL.
     * 
     * @return the endpoint address
     */
    public String getAddress() {
        if (_address == null) {
            Configuration childConfig = getModelConfiguration().getFirstChild(ADDRESS);
            if (childConfig != null) {
                _address = childConfig.getValue();
            }
        }
        return _address;
    }

    /**
     * Sets the HTTP URL.
     * 
     * @param address the endpoint address
     */
    public void setAddress(String address) {
        _address = address;
        Configuration childConfig = getModelConfiguration().getFirstChild(ADDRESS);
        if (childConfig == null) {
            ValueModel addressConfig = new ValueModel(ADDRESS);
            addressConfig.setValue(address);
            setChildModel(addressConfig);
        } else {
            childConfig.setValue(address);
        }
    }

    /**
     * Gets the HTTP method.
     * 
     * @return the HTTP method
     */
    public String getMethod() {
        if (_method == null) {
            Configuration childConfig = getModelConfiguration().getFirstChild(METHOD);
            if (childConfig != null) {
                _method = childConfig.getValue();
            }
        }
        return _method;
    }

    /**
     * Sets the HTTP method.
     * 
     * @param method the HTTP method
     */
    public void setMethod(String method) {
        _method = method;
        Configuration childConfig = getModelConfiguration().getFirstChild(METHOD);
        if (childConfig == null) {
            ValueModel methodConfig = new ValueModel(METHOD);
            methodConfig.setValue(method);
            setChildModel(methodConfig);
        } else {
            childConfig.setValue(method);
        }
    }

    /**
     * Gets the HTTP request's Content-Type.
     * 
     * @return the content type
     */
    public String getContentType() {
        if (_contentType == null) {
            Configuration childConfig = getModelConfiguration().getFirstChild(CONTENT_TYPE);
            if (childConfig != null) {
                _contentType = childConfig.getValue();
            }
        }
        return _contentType;
    }

    /**
     * Sets the HTTP request's Content-Type.
     * 
     * @param contentType the content type
     */
    public void setContentType(String contentType) {
        _contentType = contentType;
        Configuration childConfig = getModelConfiguration().getFirstChild(CONTENT_TYPE);
        if (childConfig == null) {
            ValueModel contentTypeConfig = new ValueModel(CONTENT_TYPE);
            contentTypeConfig.setValue(contentType);
            setChildModel(contentTypeConfig);
        } else {
            childConfig.setValue(contentType);
        }
    }

    /**
     * Sets the global configuration.
     * 
     * @param config the environment/global config
     */
    public void setEnvironment(Configuration config) {
        _environment = config;
    }
}

class ValueModel extends BaseModel {
    
    public ValueModel(String name) {
        super(new QName(HttpBindingModel.DEFAULT_NAMESPACE, name));
    }
    
    public ValueModel(Configuration config) {
        super(config);
    }
    
    public String getValue() {
        return super.getModelValue();
    }
    
    public void setValue(String value) {
        super.setModelValue(value);
    }
}
