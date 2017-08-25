package cn.studyjams.s1.sj52.theatreorder.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 1 on 2017/8/21.
 */

public class ComingSoonSQLiteOpenerHelper extends SQLiteOpenHelper {
    public static final String TABLE_COMING_SOON = "comingSoonTable";
    public ComingSoonSQLiteOpenerHelper(Context context) {
        super(context, "comingSoon_data", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE  " + TABLE_COMING_SOON +"  (id INTEGER PRIMARY KEY,Months TEXT,imageName TEXT,title TEXT,date TEXT,likes INTEGER)");
        ContentValues cv = new ContentValues();
        cv.put("id",0);
        cv.put("Months","august");
        cv.put("imageName","august1");
        cv.put("title","极盗车神");
        cv.put("date","2017-08-25上映");
        cv.put("likes",0);
        db.insert(TABLE_COMING_SOON,null,cv);
        cv.put("id",1);
        cv.put("Months","august");
        cv.put("imageName","august2");
        cv.put("title","星际特工：千星之城");
        cv.put("date","2017-08-25上映");
        cv.put("likes",0);
        db.insert(TABLE_COMING_SOON,null,cv);
        cv.put("id",2);
        cv.put("Months","august");
        cv.put("imageName","august3");
        cv.put("title","泡菜爱上小龙虾");
        cv.put("date","2017-08-25上映");
        cv.put("likes",0);
        db.insert(TABLE_COMING_SOON,null,cv);
        cv.put("id",3);
        cv.put("Months","august");
        cv.put("imageName","august4");
        cv.put("title","情遇曼哈顿");
        cv.put("date","2017-08-25上映");
        cv.put("likes",0);
        db.insert(TABLE_COMING_SOON,null,cv);
        cv.put("id",4);
        cv.put("Months","august");
        cv.put("imageName","august5");
        cv.put("title","蜜月计划");
        cv.put("date","2017-08-25上映");
        cv.put("likes",0);
        db.insert(TABLE_COMING_SOON,null,cv);
        cv.put("id",5);
        cv.put("Months","august");
        cv.put("imageName","august6");
        cv.put("title","赛车总动员3");
        cv.put("date","2017-08-25上映");
        cv.put("likes",0);
        db.insert(TABLE_COMING_SOON,null,cv);
        cv.put("id",6);
        cv.put("Months","august");
        cv.put("imageName","august7");
        cv.put("title","海边的曼彻斯特");
        cv.put("date","2017-08-25上映");
        cv.put("likes",0);
        db.insert(TABLE_COMING_SOON,null,cv);
        cv.put("id",7);
        cv.put("Months","august");
        cv.put("imageName","august8");
        cv.put("title","阿婆的槟榔");
        cv.put("date","2017-08-25上映");
        cv.put("likes",0);
        db.insert(TABLE_COMING_SOON,null,cv);
        cv.put("id",8);
        cv.put("Months","august");
        cv.put("imageName","august9");
        cv.put("title","天生不对");
        cv.put("date","2017-08-25上映");
        cv.put("likes",0);
        db.insert(TABLE_COMING_SOON,null,cv);

        cv.put("id",9);
        cv.put("Months","sept");
        cv.put("imageName","sept1");
        cv.put("title","二次初恋");
        cv.put("date","2017-09-01上映");
        cv.put("likes",0);
        db.insert(TABLE_COMING_SOON,null,cv);
        cv.put("id",10);
        cv.put("Months","sept");
        cv.put("imageName","sept2");
        cv.put("title","黑白迷宫");
        cv.put("date","2017-09-01上映");
        cv.put("likes",0);
        db.insert(TABLE_COMING_SOON,null,cv);
        cv.put("id",11);
        cv.put("Months","sept");
        cv.put("imageName","sept3");
        cv.put("title","敦刻尔克");
        cv.put("date","2017-09-01上映");
        cv.put("likes",0);
        db.insert(TABLE_COMING_SOON,null,cv);
        cv.put("id",12);
        cv.put("Months","sept");
        cv.put("imageName","sept4");
        cv.put("title","失业生");
        cv.put("date","2017-09-08上映");
        cv.put("likes",0);
        db.insert(TABLE_COMING_SOON,null,cv);
        cv.put("id",13);
        cv.put("Months","sept");
        cv.put("imageName","sept5");
        cv.put("title","蜘蛛侠：英雄归来");
        cv.put("date","2017-09-08上映");
        cv.put("likes",0);
        db.insert(TABLE_COMING_SOON,null,cv);
        cv.put("id",14);
        cv.put("Months","sept");
        cv.put("imageName","sept6");
        cv.put("title","会痛的十七岁");
        cv.put("date","2017-09-15上映");
        cv.put("likes",0);
        db.insert(TABLE_COMING_SOON,null,cv);
        cv.put("id",15);
        cv.put("Months","sept");
        cv.put("imageName","sept7");
        cv.put("title","旗袍先生");
        cv.put("date","2017-09-15上映");
        cv.put("likes",0);
        db.insert(TABLE_COMING_SOON,null,cv);
        cv.put("id",16);
        cv.put("Months","sept");
        cv.put("imageName","sept8");
        cv.put("title","推理笔记");
        cv.put("date","2017-09-15上映");
        cv.put("likes",0);
        db.insert(TABLE_COMING_SOON,null,cv);
        cv.put("id",17);
        cv.put("Months","sept");
        cv.put("imageName","sept9");
        cv.put("title","狂兽");
        cv.put("date","2017-09-15上映");
        cv.put("likes",0);
        db.insert(TABLE_COMING_SOON,null,cv);
        cv.put("id",18);
        cv.put("Months","sept");
        cv.put("imageName","sept10");
        cv.put("title","猩球崛起3：终极之战");
        cv.put("date","2017-09-15上映");
        cv.put("likes",0);
        db.insert(TABLE_COMING_SOON,null,cv);
        cv.put("id",19);
        cv.put("Months","sept");
        cv.put("imageName","sept11");
        cv.put("title","魔都爱之十二星座");
        cv.put("date","2017-09-22上映");
        cv.put("likes",0);
        db.insert(TABLE_COMING_SOON,null,cv);
        cv.put("id",20);
        cv.put("Months","sept");
        cv.put("imageName","sept12");
        cv.put("title","神谕通天");
        cv.put("date","2017-09-22上映");
        cv.put("likes",0);
        db.insert(TABLE_COMING_SOON,null,cv);
        cv.put("id",21);
        cv.put("Months","sept");
        cv.put("imageName","sept13");
        cv.put("title","纯洁心灵·逐梦演艺圈");
        cv.put("date","2017-09-22上映");
        cv.put("likes",0);
        db.insert(TABLE_COMING_SOON,null,cv);
        cv.put("id",22);
        cv.put("Months","sept");
        cv.put("imageName","sept14");
        cv.put("title","心理罪之城市之光");
        cv.put("date","2017-09-30上映");
        cv.put("likes",0);
        db.insert(TABLE_COMING_SOON,null,cv);
        cv.put("id",23);
        cv.put("Months","sept");
        cv.put("imageName","sept15");
        cv.put("title","昼颜");
        cv.put("date","2017-09-30上映");
        cv.put("likes",0);
        db.insert(TABLE_COMING_SOON,null,cv);
        cv.put("id",24);
        cv.put("Months","sept");
        cv.put("imageName","sept16");
        cv.put("title","英伦对决");
        cv.put("date","2017-09-30上映");
        cv.put("likes",0);
        db.insert(TABLE_COMING_SOON,null,cv);
        cv.put("id",25);
        cv.put("Months","sept");
        cv.put("imageName","sept17");
        cv.put("title","羞羞的铁拳");
        cv.put("date","2017-09-30上映");
        cv.put("likes",0);
        db.insert(TABLE_COMING_SOON,null,cv);
        cv.put("id",26);
        cv.put("Months","sept");
        cv.put("imageName","sept18");
        cv.put("title","芳华");
        cv.put("date","2017-09-30上映");
        cv.put("likes",0);
        db.insert(TABLE_COMING_SOON,null,cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
