
package com.itheima.crm.webservice02;

import java.util.List;
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
@WebService(name = "CustomerServiceImpl", targetNamespace = "http://impl.service.crm.itheima.com/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface CustomerServiceImpl {


    /**
     * 
     * @return
     *     returns java.util.List<com.itheima.crm.webservice02.Customer>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findAll", targetNamespace = "http://impl.service.crm.itheima.com/", className = "com.itheima.crm.webservice02.FindAll")
    @ResponseWrapper(localName = "findAllResponse", targetNamespace = "http://impl.service.crm.itheima.com/", className = "com.itheima.crm.webservice02.FindAllResponse")
    public List<Customer> findAll();

    /**
     * 
     * @return
     *     returns java.util.List<com.itheima.crm.webservice02.Customer>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findByFixedAreaIdIsNull", targetNamespace = "http://impl.service.crm.itheima.com/", className = "com.itheima.crm.webservice02.FindByFixedAreaIdIsNull")
    @ResponseWrapper(localName = "findByFixedAreaIdIsNullResponse", targetNamespace = "http://impl.service.crm.itheima.com/", className = "com.itheima.crm.webservice02.FindByFixedAreaIdIsNullResponse")
    public List<Customer> findByFixedAreaIdIsNull();

    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<com.itheima.crm.webservice02.Customer>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findByFixedAreaId", targetNamespace = "http://impl.service.crm.itheima.com/", className = "com.itheima.crm.webservice02.FindByFixedAreaId")
    @ResponseWrapper(localName = "findByFixedAreaIdResponse", targetNamespace = "http://impl.service.crm.itheima.com/", className = "com.itheima.crm.webservice02.FindByFixedAreaIdResponse")
    public List<Customer> findByFixedAreaId(
            @WebParam(name = "arg0", targetNamespace = "")
                    String arg0);

}
