
package com.itheima.bos.service01.impl;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "OrderServiceImpl", targetNamespace = "http://impl.service.bos.itheima.com/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface OrderServiceImpl {


    /**
     * 
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns com.itheima.bos.service01.impl.Area
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findByPCD", targetNamespace = "http://impl.service.bos.itheima.com/", className = "com.itheima.bos.service01.impl.FindByPCD")
    @ResponseWrapper(localName = "findByPCDResponse", targetNamespace = "http://impl.service.bos.itheima.com/", className = "com.itheima.bos.service01.impl.FindByPCDResponse")
    public Area findByPCD(
            @WebParam(name = "arg0", targetNamespace = "")
                    String arg0,
            @WebParam(name = "arg1", targetNamespace = "")
                    String arg1,
            @WebParam(name = "arg2", targetNamespace = "")
                    String arg2);

    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "save", targetNamespace = "http://impl.service.bos.itheima.com/", className = "com.itheima.bos.service01.impl.Save")
    @ResponseWrapper(localName = "saveResponse", targetNamespace = "http://impl.service.bos.itheima.com/", className = "com.itheima.bos.service01.impl.SaveResponse")
    public void save(
            @WebParam(name = "arg0", targetNamespace = "")
                    Order arg0);

}