
package org.mdr.wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for changeVisibilityState complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="changeVisibilityState">
 *   &lt;complexContent>
 *     &lt;extension base="{http://mdr.org/wsdl}state">
 *       &lt;sequence>
 *         &lt;element name="newVisibility" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "changeVisibilityState", propOrder = {
    "newVisibility"
})
public class ChangeVisibilityState
    extends State
{

    protected String newVisibility;

    /**
     * Gets the value of the newVisibility property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNewVisibility() {
        return newVisibility;
    }

    /**
     * Sets the value of the newVisibility property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNewVisibility(String value) {
        this.newVisibility = value;
    }

}
