
package org.mdr.wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for registrationAuthorityIdentifier complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="registrationAuthorityIdentifier">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="international_code_designator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OPI_source" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="organization_identifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="organization_part_identifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "registrationAuthorityIdentifier", propOrder = {
    "internationalCodeDesignator",
    "opiSource",
    "organizationIdentifier",
    "organizationPartIdentifier"
})
public class RegistrationAuthorityIdentifier {

    @XmlElement(name = "international_code_designator")
    protected String internationalCodeDesignator;
    @XmlElement(name = "OPI_source")
    protected String opiSource;
    @XmlElement(name = "organization_identifier")
    protected String organizationIdentifier;
    @XmlElement(name = "organization_part_identifier")
    protected String organizationPartIdentifier;

    /**
     * Gets the value of the internationalCodeDesignator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInternationalCodeDesignator() {
        return internationalCodeDesignator;
    }

    /**
     * Sets the value of the internationalCodeDesignator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInternationalCodeDesignator(String value) {
        this.internationalCodeDesignator = value;
    }

    /**
     * Gets the value of the opiSource property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOPISource() {
        return opiSource;
    }

    /**
     * Sets the value of the opiSource property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOPISource(String value) {
        this.opiSource = value;
    }

    /**
     * Gets the value of the organizationIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrganizationIdentifier() {
        return organizationIdentifier;
    }

    /**
     * Sets the value of the organizationIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrganizationIdentifier(String value) {
        this.organizationIdentifier = value;
    }

    /**
     * Gets the value of the organizationPartIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrganizationPartIdentifier() {
        return organizationPartIdentifier;
    }

    /**
     * Sets the value of the organizationPartIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrganizationPartIdentifier(String value) {
        this.organizationPartIdentifier = value;
    }

}
