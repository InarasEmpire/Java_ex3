package Logic;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * <p>Java class for anonymous complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element ref="{}Board"/>
 *         &lt;element name="Variant">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="Regular"/>
 *               &lt;enumeration value="Popout"/>
 *               &lt;enumeration value="Circular"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *       &lt;/all>
 *       &lt;attribute name="target" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {

})
@XmlRootElement(name = "Game")
public class Game implements Serializable {
    @XmlElement(name = "Board", required = true)
    protected Board board;
    @XmlElement(name = "Variant", required = true)
    protected String variant;
    @XmlAttribute(name = "target", required = true)
    protected BigInteger target;


    /**
     * Gets the value of the board property.
     *
     * @return
     *     possible object is
     *     {@link Board }
     *
     */
    public Board getBoard() {
        return board;
    }


    /**
     * Sets the value of the board property.
     *
     * @param value
     *     allowed object is
     *     {@link Board }
     *
     */
    public void setBoard(Board value) {
        this.board = value;
    }

    /**
     * Gets the value of the variant property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getVariant() {
        return variant;
    }

    /**
     * Sets the value of the variant property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setVariant(String value) {
        this.variant = value;
    }

    /**
     * Gets the value of the target property.
     *
     * @return
     *     possible object is
     *     {@link BigInteger }
     *
     */
    public BigInteger getTarget() {
        return target;
    }

    /**
     * Sets the value of the target property.
     *
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *
     */
    public void setTarget(BigInteger value) {
        this.target = value;
    }

    public boolean isPopoutMode(){return this.variant.equals("Popout"); }

    public boolean isCircularMode(){return this.variant.equals("Circular"); }

    public void init(){
        this.board.init();
        this.board.setCircular(isCircularMode());
        this.board.setPopout(isPopoutMode());
        this.board.setTarget(this.target);
    }
}
