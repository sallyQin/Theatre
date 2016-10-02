package cn.studyjams.s1.sj52.theatreorder.data;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Apc on 2016/8/29.
 */
public class Theatre_info {
    public static String locationName[] = new String[]{"你可能想去的影院","梁溪区","锡山区","惠山区","滨湖区","新吴区"};
    public static String fileNames [] = new String[]{"","","星际迷航3：超越星辰","七月与安生","谍影重重5","冰川时代：星际碰撞","我们的十年","使徒行者","微微一笑很倾城"};
    public static String theatreName[] = new String[]{"无锡海岸影院城","无锡和平电影院","无锡睦邻国际影城","无锡大世界影城","华谊兄弟无锡影院","无锡金逸影城IMAX荟聚店","SFC上影-无锡东港店",
            "无锡中传国际影城","无锡新影联影城","无锡橙天嘉禾影城-新之城店","万达影城","SFC上影-无锡硕放店","中影国际影城-宝龙店"};
    public static String  posters[] = new String[]{"xinjitrek","july","dieyingchongchong5","icetime5","tiequan","tuxingshizhe","weiweiyixiao"};
    public static String Date[] = new String []{"今日","明日"};
    public  String fileName;
    public String poster;

    public Theatre_info(String fileName,String poster) {
        this.fileName = fileName;
        this.poster = poster;
    }

    /** 订位页面的海报信息**/
    public static List<Theatre_info> getTheatrePosters(){
        List<Theatre_info> posterSelect = new ArrayList<>();
        posterSelect.add(new Theatre_info("","empty"));
        posterSelect.add(new Theatre_info("","empty"));
        posterSelect.add(new Theatre_info("星际迷航3：超越星辰","xinjitrek_small"));
        posterSelect.add(new Theatre_info("七月与安生","july_small"));
        posterSelect.add(new Theatre_info("谍影重重5","dieyingchongchong5_small"));
        posterSelect.add(new Theatre_info("冰川时代：星际碰撞","icetime5_small"));
        posterSelect.add(new Theatre_info("我们的十年","tenyears_small"));
        posterSelect.add(new Theatre_info("使徒行者","tuxingshizhe_small"));
        posterSelect.add(new Theatre_info("微微一笑很倾城","weiweiyixiao_small"));

        return posterSelect;

    }
}
