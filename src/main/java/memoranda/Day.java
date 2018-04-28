package main.java.memoranda;

import java.util.Vector;

import main.java.memoranda.SpecificElement;
import nu.xom.Attribute;
import nu.xom.Element;
import nu.xom.Elements;

public class Day {
    Element dEl = null;

    public Day(Element el) {
        dEl = el;
        // Added to fix old '.notes' XML format 
        // Old-style XML is converted on the fly [alexeya]
        if (dEl.getAttribute("date") != null) {
            Attribute dAttr = dEl.getAttribute("date");
            Attribute tAttr = dEl.getAttribute("title");
            Element nEl = new Element("note");
            String date = dAttr.getValue().replace('/', '-');
            nEl.addAttribute(new Attribute("refid", date));
            nEl.addAttribute(new Attribute("title", tAttr.getValue()));
            dEl.appendChild(nEl);
            dEl.removeAttribute(dAttr);             
            dEl.removeAttribute(tAttr);
        }
    }

    public int getValue() {
        return new Integer(dEl.getAttribute("day").getValue()).intValue();
    }

    /*public Note getNote() {
        return new NoteImpl(dEl);
    }*/
    
    public SpecificElement getSpecificEntity(String d, String type) {
        if (dEl == null) 
            return null;
        Elements ne = null;
        if (type == "note")
        {
            ne = dEl.getChildElements("note");
        }
        else if (type == "event")
        {
            ne = dEl.getChildElements("event");
        }
        else
        {
            ne = dEl.getChildElements("task");
        }
        for (int i = 0; i < ne.size(); i++)
            if (ne.get(i).getAttribute("refid").getValue().equals(d))
                return new SpecificElement(ne.get(i), type);
        //return createDay(d);
        return null;
    }

    public Element createSpecificEntity(String d, String type) {
        Element el = null;
        if (type == "note")
        {
            el = new Element("note");
        }
        else if (type == "event")
        {
            el = new Element("event");
        }
        else
        {
            el = new Element("task");
        }
//      el.addAttribute(new Attribute("refid", d));
/*            el.addAttribute(new Attribute("day", new Integer(d).toString()));
                    el.addAttribute(
            new Attribute(
                "date",
                new CalendarDate(
                    10,
                    10,
                    2004).toString()));
*/                      
        dEl.appendChild(el);
        return new Element(el);
    }

    public Vector getSpecificEntities(String type) {
        if (dEl == null)
            return null;
        Vector v = new Vector();
        Elements ds = null;
        if (type == "note")
        {
            ds = dEl.getChildElements("note");
        }
        else if (type == "event")
        {
            ds = dEl.getChildElements("event");
        }
        else
        {
            ds = dEl.getChildElements("task");
        }
        for (int i = 0; i < ds.size(); i++)
            v.add(new Element(ds.get(i)));                                    
        return v;
    }

    public Element getElement() {
        return dEl;
    }
}
