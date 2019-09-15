package rss;

public class EntityRss {
    private String rssURI = "http:asdasd.ru";
    private String filter = "containsRequiredTopic";
    private String procces = "rssProcessor";
    private String to = "seda:rssToRest";

    public String getFrom() {
        return rssURI;
    }

    public String getFilter() {
        return filter;
    }

    public String getProcces() {
        return procces;
    }

    public String getTo() {
        return to;
    }

}
