
package com.itheima.bos.service01.impl;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.itheima.bos.service01.impl package. 
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

    private final static QName _SaveResponse_QNAME = new QName("http://impl.service.bos.itheima.com/", "saveResponse");
    private final static QName _FindByPCD_QNAME = new QName("http://impl.service.bos.itheima.com/", "findByPCD");
    private final static QName _Save_QNAME = new QName("http://impl.service.bos.itheima.com/", "save");
    private final static QName _FindByPCDResponse_QNAME = new QName("http://impl.service.bos.itheima.com/", "findByPCDResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.itheima.bos.service01.impl
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Save }
     * 
     */
    public Save createSave() {
        return new Save();
    }

    /**
     * Create an instance of {@link FindByPCDResponse }
     * 
     */
    public FindByPCDResponse createFindByPCDResponse() {
        return new FindByPCDResponse();
    }

    /**
     * Create an instance of {@link FindByPCD }
     * 
     */
    public FindByPCD createFindByPCD() {
        return new FindByPCD();
    }

    /**
     * Create an instance of {@link SaveResponse }
     * 
     */
    public SaveResponse createSaveResponse() {
        return new SaveResponse();
    }

    /**
     * Create an instance of {@link Area }
     * 
     */
    public Area createArea() {
        return new Area();
    }

    /**
     * Create an instance of {@link Standard }
     * 
     */
    public Standard createStandard() {
        return new Standard();
    }

    /**
     * Create an instance of {@link WayBill }
     * 
     */
    public WayBill createWayBill() {
        return new WayBill();
    }

    /**
     * Create an instance of {@link FixedArea }
     * 
     */
    public FixedArea createFixedArea() {
        return new FixedArea();
    }

    /**
     * Create an instance of {@link TakeTime }
     * 
     */
    public TakeTime createTakeTime() {
        return new TakeTime();
    }

    /**
     * Create an instance of {@link WorkBill }
     * 
     */
    public WorkBill createWorkBill() {
        return new WorkBill();
    }

    /**
     * Create an instance of {@link Courier }
     * 
     */
    public Courier createCourier() {
        return new Courier();
    }

    /**
     * Create an instance of {@link SubArea }
     * 
     */
    public SubArea createSubArea() {
        return new SubArea();
    }

    /**
     * Create an instance of {@link Order }
     * 
     */
    public Order createOrder() {
        return new Order();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaveResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.bos.itheima.com/", name = "saveResponse")
    public JAXBElement<SaveResponse> createSaveResponse(SaveResponse value) {
        return new JAXBElement<SaveResponse>(_SaveResponse_QNAME, SaveResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindByPCD }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.bos.itheima.com/", name = "findByPCD")
    public JAXBElement<FindByPCD> createFindByPCD(FindByPCD value) {
        return new JAXBElement<FindByPCD>(_FindByPCD_QNAME, FindByPCD.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Save }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.bos.itheima.com/", name = "save")
    public JAXBElement<Save> createSave(Save value) {
        return new JAXBElement<Save>(_Save_QNAME, Save.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindByPCDResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.bos.itheima.com/", name = "findByPCDResponse")
    public JAXBElement<FindByPCDResponse> createFindByPCDResponse(FindByPCDResponse value) {
        return new JAXBElement<FindByPCDResponse>(_FindByPCDResponse_QNAME, FindByPCDResponse.class, null, value);
    }

}
