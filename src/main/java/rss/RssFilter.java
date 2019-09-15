package rss;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.camel.Body;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RssFilter {
    public boolean containsRequiredTopic(@Body String xmlFeed) throws IOException {
        XmlMapper mapper = new XmlMapper();

        String link = mapper.readTree(xmlFeed)
                .findValue("channel")
                .findValue("item")
                .findValue("link").asText();

        return link.matches("^.+(/news/|/articles/|/razbor/|/feature/|/imgly/).+$");
    }
}
