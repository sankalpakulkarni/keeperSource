
package org.mdr.wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for registrar complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="registrar">
 *   &lt;complexContent>
 *     &lt;extension base="{http://mdr.org/wsdl}steward">
 *       &lt;sequence>
 *         &lt;element name="registrationAuthority" type="{http://mdr.org/wsdl}registrationAuthority" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "registrar", propOrder = {
    "registrationAuthority"
})
public class Registrar
    extends Steward
{

    protected RegistrationAuthority registrationAuthority;

    /**
     * Gets the value of the registrationAuthority property.
     * 
     * @return
     *     possible object is
     *     {@link RegistrationAuthority }
     *     
     */
    public RegistrationAuthority getRegistrationAuthority() {
        return registrationAuthority;
    }

    /**
     * Sets the value of the registrationAuthority property.
     * 
     * @param value
     *     allowed object is
     *     {@link RegistrationAuthority }
     *     
     */
    public void setRegistrationAuthority(RegistrationAuthority value) {
        this.registrationAuthority = value;
    }

}
