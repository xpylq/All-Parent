package parse;

import model.FreeProxy;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Set;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by youzhihao on 2016/10/17.
 */
public class KuaiDaiLiParseImpl extends ParseProxyService {

    //免费代理的网址
    public static String url = "http://www.kuaidaili.com/free/inha/";

    public KuaiDaiLiParseImpl(BlockingDeque<FreeProxy> freeProxyQueue, ConcurrentHashMap<String, String> effectProxy, int crawlingPageNum) {
        super(freeProxyQueue, effectProxy, crawlingPageNum);
    }

    @Override
    public void parseEveryPage(Document document) {
        Elements elements = document.select("tbody > tr");
        for (Element element : elements) {
            String ip = element.select("[data-title=IP]").text();
            String port = element.select("[data-title=PORT]").text();
            FreeProxy freeProxy = new FreeProxy(ip, Integer.valueOf(port));
            if (!effectProxy.contains(ip)) {
                freeProxySet.add(freeProxy);
            }
        }
    }
}
