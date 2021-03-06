package net.avicus.compendium.locale;

import com.google.common.base.Preconditions;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import lombok.Getter;
import lombok.ToString;
import net.avicus.compendium.utils.Strings;
import org.jdom2.Content;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 * A key->value represtantation of strings for a specific {@link Locale} that can be retrieved for
 * localization.
 */
@ToString
public class LocaleStrings {

  @Getter
  private final Locale locale;
  private final Map<String, String> strings;

  public LocaleStrings(Locale locale) {
    this(locale, new HashMap<>());
  }

  public LocaleStrings(Locale locale, Map<String, String> strings) {
    this.locale = locale;
    this.strings = strings;
  }

  /**
   * Create from an XML document.
   */
  public static LocaleStrings fromXml(InputStream stream) throws JDOMException, IOException {
    SAXBuilder sax = new SAXBuilder();
    Document doc = sax.build(stream);
    return fromXml(doc.getRootElement());
  }

  /**
   * Create from an XML element.
   *
   * @param el The &lt;locale lang="en" country="us"/&gt;
   */
  public static LocaleStrings fromXml(Element el) {
    Preconditions.checkArgument(el.getName().equals("locale"), "element not <lang/>");
    Preconditions.checkArgument(el.getAttribute("lang") != null, "<lang/> missing lang attribute");

    String lang = el.getAttributeValue("lang");
    String country = el.getAttributeValue("country");

    Locale locale = new Locale(lang);
    if (country != null) {
      locale = new Locale(lang, country);
    }

    LocaleStrings strings = new LocaleStrings(locale);

    for (Content content : el.getDescendants()) {
      if (content instanceof Element) {
        Element child = (Element) content;

        if (child.getChildren().size() > 0) {
          continue;
        }

        String path = getPath(el, child);
        strings.add(path, Strings.addColors(child.getTextTrim().replaceAll(" +", " ")));
      }
    }

    return strings;
  }

  private static String getPath(Element exclude, Element nested) {
    String result = nested.getName();
    Element curr = nested.getParentElement();
    while (!curr.equals(exclude)) {
      result = curr.getName() + "." + result;
      curr = curr.getParentElement();
    }
    return result;
  }

  public void add(String key, String value) {
    this.strings.put(key, value);
  }

  public Optional<String> get(String key) {
    return Optional.ofNullable(this.strings.get(key));
  }
}
