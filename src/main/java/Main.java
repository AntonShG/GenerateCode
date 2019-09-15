import rss.EntityRss;
import rss.GenerateRssRouter;

public class Main {

        public static void main(String[] args) throws Exception {
            EntityRss entityRss = new EntityRss();
            new GenerateRssRouter().generateRssRouter(entityRss);
    }
}
