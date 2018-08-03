
package com.itheima.crm.webservice02;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.itheima.crm.webservice02 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _FindByFixedAreaIdIsNull_QNAME = new QName("http://impl.service.crm.itheima.com/", "findByFixedAreaIdIsNull");
    private final static QName _FindByFixedAreaId_QNAME = new QName("http://impl.service.crm.itheima.com/", "findByFixedAreaId");
    private final static QName _FindAll_QNAME = new QName("http://impl.service.crm.itheima.com/", "findAll");
    private final static QName _FindByFixedAreaIdIsNullResponse_QNAME = new QName("http://impl.service.crm.itheima.com/", "findByFixedAreaIdIsNullResponse");
    private final static QName _FindByFixedAreaIdResponse_QNAME = new QName("http://impl.service.crm.itheima.com/", "findByFixedAreaIdResponse");
    private final static QName _FindAllResponse_QNAME = new QName("http://impl.service.crm.itheima.com/", "findAllResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.itheima.crm.webservice02
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FindByFixedAreaId }
     * 
     */
    public FindByFixedAreaId createFindByFixedAreaId() {
        return new FindByFixedAreaId();
    }

    /**
     * Create an instance of {@link FindByFixedAreaIdIsNull }
     * 
     */
    public FindByFixedAreaIdIsNull createFindByFixedAreaIdIsNull() {
        return new FindByFixedAreaIdIsNull();
    }

    /**
     * Create an instance of {@link FindAllResponse }
     * 
     */
    public FindAllResponse createFindAllResponse() {
        return new FindAllResponse();
    }

    /**
     * Create an instance of {@link FindAll }
     * 
     */
    public FindAll createFindAll() {
        return new FindAll();
    }

    /**
     * Create an instance of {@link FindByFixedAreaIdIsNullResponse }
     * 
     */
    public FindByFixedAreaIdIsNullResponse createFindByFixedAreaIdIsNullResponse() {
        return new FindByFixedAreaIdIsNullResponse();
    }

    /**
     * Create an instance of {@link FindByFixedAreaIdResponse }
     * 
     */
    public FindByFixedAreaIdResponse createFindByFixedAreaIdResponse() {
        return new FindByFixedAreaIdResponse();
    }

    /**
     * Create an instance of {@link Customer }
     * 
     */
    public Customer createCustomer() {
        return new Customer();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindByFixedAreaIdIsNull }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.crm.itheima.com/", name = "findByFixedAreaIdIsNull")
    public JAXBElement<FindByFixedAreaIdIsNull> createFindByFixedAreaIdIsNull(FindByFixedAreaIdIsNull value) {
        return new JAXBElement<FindByFixedAreaIdIsNull>(_FindByFixedAreaIdIsNull_QNAME, FindByFixedAreaIdIsNull.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindByFixedAreaId }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.crm.itheima.com/", name = "findByFixedAreaId")
    public JAXBElement<FindByFixedAreaId> createFindByFixedAreaId(FindByFixedAreaId value) {
        return new JAXBElement<FindByFixedAreaId>(_FindByFixedAreaId_QNAME, FindByFixedAreaId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindAll }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.crm.itheima.com/", name = "findAll")
    public JAXBElement<FindAll> createFindAll(FindAll value) {
        return new JAXBElement<FindAll>(_FindAll_QNAME, FindAll.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindByFixedAreaIdIsNullResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.crm.itheima.com/", name = "findByFixedAreaIdIsNullResponse")
    public JAXBElement<FindByFixedAreaIdIsNullResponse> createFindByFixedAreaIdIsNullResponse(FindByFixedAreaIdIsNullResponse value) {
        return new JAXBElement<FindByFixedAreaIdIsNullResponse>(_FindByFixedAreaIdIsNullResponse_QNAME, FindByFixedAreaIdIsNullResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindByFixedAreaIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.crm.itheima.com/", name = "findByFixedAreaIdResponse")
    public JAXBElement<FindByFixedAreaIdResponse> createFindByFixedAreaIdResponse(FindByFixedAreaIdResponse value) {
        return new JAXBElement<FindByFixedAreaIdResponse>(_FindByFixedAreaIdResponse_QNAME, FindByFixedAreaIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindAllResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.crm.itheima.com/", name = "findAllResponse")
    public JAXBElement<FindAllResponse> createFindAllResponse(FindAllResponse value) {
        return new JAXBElement<FindAllResponse>(_FindAllResponse_QNAME, FindAllResponse.class, null, value);
    }

}
