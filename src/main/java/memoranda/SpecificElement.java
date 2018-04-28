package main.java.memoranda;

import nu.xom.Element;

public class SpecificElement {
    Element nEl;
    String type;
    
    public SpecificElement(Element el, String typeOfElement) {
        nEl = el;
        type = typeOfElement;
    }
    
    public Element getElement() {
        return nEl;
    }
    
    public String getType()
    {
        return type;
    }
}
