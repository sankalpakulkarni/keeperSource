
package org.mdr.wsdl;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for registrationAuthority complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="registrationAuthority">
 *   &lt;complexContent>
 *     &lt;extension base="{http://mdr.org/wsdl}organization">
 *       &lt;sequence>
 *         &lt;element name="documentation_language_identifier" type="{http://mdr.org/wsdl}languageIdentification" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="registration_authority_identifier" type="{http://mdr.org/wsdl}registrationAuthorityIdentifier" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "registrationAuthority", propOrder = {
    "documentationLanguageIdentifier",
    "registrationAuthorityIdentifier"
})
public class RegistrationAuthority
    extends Organization
{

    @XmlElement(name = "documentation_language_identifier", nillable = true)
    protected List<LanguageIdentification> documentationLanguageIdentifier;
    @XmlElement(name = "registration_authority_identifier")
    protected RegistrationAuthorityIdentifier registrationAuthorityIdentifier;

    /**
     * Gets the value of the documentationLanguageIdentifier property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the documentationLanguageIdentifier property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDocumentationLanguageIdentifier().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LanguageIdentification }
     * 
     * 
     */
    public List<LanguageIdentification> getDocumentationLanguageIdentifier() {
        if (documentationLanguageIdentifier == null) {
            documentationLanguageIdentifier = new ArrayList<LanguageIdentification>();
        }
        return this.documentationLanguageIdentifier;
    }

    /**
     * Gets the value of the registrationAuthorityIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link RegistrationAuthorityIdentifier }
     *     
     */
    public RegistrationAuthorityIdentifier getRegistrationAuthorityIdentifier() {
        return registrationAuthorityIdentifier;
    }

    /**
     * Sets the value of the registrationAuthorityIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link RegistrationAuthorityIdentifier }
     *     
     */
    public void setRegistrationAuthorityIdentifier(RegistrationAuthorityIdentifier value) {
        this.registrationAuthorityIdentifier = value;
    }

}
