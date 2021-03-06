package zxing;

import com.alibaba.fastjson.JSONObject;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.User;

/**
 * Created by youzhihao on 2016/9/27.
 */
public class Test {

    public static void main(String[] args) throws Exception {
        //demo1();
        //demo2();
        //demo3();
        //demo4();
        //demo5();
        //demo6();
        //demo7();
        //System.out.println(demo8(true));
        //System.out.println(demo8(false));
        //Calendar calendar = Calendar.getInstance();
        //demo9();
        //demo10();
        //demo11();
        //demo12();
        //demo13();
        //demo14();
        //demo15();
        //demo16();
        //demo17();
        demo18();

    }

    public static void demo1() throws Exception {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("关闭资源");
            }
        }));
        while (true) {
            Thread.currentThread().sleep(1000);
            System.out.println("程序结束");
        }
    }

    /**
     * 演示java基本类型是传值还是传引用,结果：java基本类型是传值
     */
    public static void demo2() {
        int num = 2;
        demo2_1(num);
        System.out.println(num);
    }

    public static void demo2_1(int num) {
        num++;
        System.out.println(num);
    }

    public static void demo3() {
        int nums[] = {1, 2, 3, 4, 5};
    }

    /**
     * System.arraycopy（）在复制对象的时候，只会拷贝引用，没有深层复制对象， arrayList在扩容的时候也是调的这个方法，所以arrayList在扩容的时候性能不会很差
     **/
    public static void demo4() {
        User[] users = new User[1];
        users[0] = new User();
        User[] copy = new User[2];
        System.arraycopy(users, 0, copy, 0, 1);
        users[0].setId(100L);
        System.out.println(users[0]);
        System.out.println(copy[0]);
        System.out.println(users[1]);
        System.out.println(copy[1]);
    }

    public static void demo5() {
        List<String> a = new ArrayList<String>();
        a.add("1");
        a.add("2");
        for (String temp : a) {
            if ("2".equals(temp)) {
                a.remove(temp);
            }
        }
        System.out.println(a);
    }

    /**
     * 使用foreach接口debug，研究Iterable
     */
    public static void demo6() {
        List<String> a = new ArrayList<String>();
        a.add("1");
        a.add("2");
        for (String temp : a) {
            System.out.println(temp);
        }
    }

    /**
     * integer对象，-127~127会使用integer.cache中的缓存进行对象引用，超出这个返回，会生成新对象，巨坑
     */
    public static void demo7() {
        Integer num1 = 10;
        Integer num2 = 10;
        System.out.println(num1 == num2);
        num1 = 10000;
        num2 = 10000;
        System.out.println(num1 == num2);
    }

    /**
     * try,catch,finally块中，如果finally中有返回，调用者一定获取finally中的结果
     */
    public static int demo8(boolean hasExcpetion) {
        try {
            if (hasExcpetion) {
                throw new RuntimeException("aaaaa");
            }
            return 1;
        } catch (Exception e) {
            return 2;
        } finally {
            return 3;
        }
    }

    public static void demo9() {
        String url = "http://127.0.0.1:9999/jobs/jobLogHeartbeat";
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("Host", "accounts.douban.com");
        httpGet.addHeader("Origin", "https://www.douban.com");
        httpGet.setHeader(new BasicHeader("Cookie", "abc"));
        try {
            CloseableHttpResponse response = httpclient.execute(httpGet);
            Document doc = Jsoup.parse(response.getEntity().getContent(), "utf-8", url);
            System.out.println(doc.getAllElements());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void demo10() {

        String str = new String(Base64.decodeBase64("YzljODZhOGYxNmY1ZTk2NzViOGIyZmU4MDEzNTRmNWY=".getBytes())) + ".png";
        System.out.println(str);
        try {
            System.out.println(URLDecoder.decode("UG9wb73YzbwyMDE3NjIyMTcyNTgucG5n", "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static void demo11() {
        int i = 1;
        int j = 2;
        int x = i = j;
        System.out.println(i);
        System.out.println(j);
        System.out.println(x);
    }

    public static void demo12() {
        System.out.println(String.class.getCanonicalName());
        System.out.println(2111111112 & 0xFFFFFF);
        Random r = new Random();
        System.out.println(Math.abs(r.nextInt() % 999) % 999);
    }

    public static void demo13() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        Collections.shuffle(list);//将数组随机排列一把
        System.out.println(JSONObject.toJSONString(list));
    }

    public static void demo14() {
        HashMap<String/* Namespace */, HashMap<String/* Key */, String/* Value */>> map = new HashMap<>();
    }

    public static void demo15() {
        List<String> origin = new ArrayList<>();
        origin.add("380104026@qq.com");
        origin.add("380104026@qq.com");
        origin.add("380104026@qq.com");
        Set<String> set = new HashSet<>(origin);
        List<String> to = new ArrayList<>(set);
    }

    //比较两种迭代方式速度
    public static void demo16() {
        //init
        int loopNum = 10000000;
        Map<String, String> map1 = new HashMap<>();
        for (int i = 1; i <= loopNum; i++) {
            map1.put(String.valueOf(i), String.valueOf(i));
        }
        Map<String, String> map2 = new HashMap<>();
        for (int i = 1; i <= loopNum; i++) {
            map2.put(String.valueOf(i), String.valueOf(i));
        }
        //iterator
        Long startTime2 = System.currentTimeMillis();
        for (Iterator<Map.Entry<String, String>> it = map2.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<String, String> entry = it.next();
            entry.getKey();
            entry.getValue();
        }
        Long durationTime2 = System.currentTimeMillis() - startTime2;
        System.out.println("for:" + durationTime2);

        //for
        Long startTime1 = System.currentTimeMillis();
        for (Map.Entry<String, String> entries : map1.entrySet()) {
            entries.getKey();
            entries.getValue();
        }
        Long durationTime1 = System.currentTimeMillis() - startTime1;
        System.out.println("for:" + durationTime1);
    }

    //模拟任务执行时间长于调度周期
    public static void demo17() {
        ScheduledThreadPoolExecutor scheduled = new ScheduledThreadPoolExecutor(10, new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println("discard");
            }
        });
        scheduled.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(111111);
                    Thread.sleep(10000);
                    System.out.println(111111);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 0, 1, TimeUnit.MILLISECONDS);
        scheduled.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(222222);
                    Thread.sleep(10000);
                    System.out.println(222222);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 0, 1, TimeUnit.MILLISECONDS);
        scheduled.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(333333);
                    System.out.println("TaskCount:" + scheduled.getQueue().size());
                    System.out.println(333333);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 0, 1000, TimeUnit.MILLISECONDS);
        scheduled.getTaskCount();
    }

    public static void demo18() {
        String apiInfo = "com.netease.mail.yanxuan.dschedule.admin.dao.DscheduleRegistryDao.registryUpdate";
        Matcher matcher = Pattern.compile("(.*)\\.(.*\\(.*\\))").matcher(apiInfo);
//        if (matcher.find()) {
//            System.out.println(matcher.group(1));
//            System.out.println(matcher.group(2));
//        }
//        matcher = Pattern.compile(".*\\.(.*\\..*\\(.*\\))").matcher(apiInfo);
//        if (matcher.find()) {
//            System.out.println(matcher.group(1));
//        }
//        matcher = Pattern.compile(".*\\.(.*\\..*)\\(.*").matcher(apiInfo);
//        if (matcher.find()) {
//            System.out.println(matcher.group(1));
//        }
         matcher = Pattern.compile(".*\\.(.*)\\.(.*)").matcher(apiInfo);
        if (matcher.find()) {
            System.out.println(matcher.group(1));
            System.out.println(matcher.group(2));
        }
    }
}



