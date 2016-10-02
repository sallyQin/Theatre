package cn.studyjams.s1.sj52.theatreorder.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Apc on 2016/9/3.
 */
public class HaiAnTheatreInfo implements Parcelable {

    public String startTime; /** 影片放映开始时间**/
    public String endTime;   /** 影片放映结束时间**/
    public String edition;   /** 影片版本**/
    public String projectionHall;  /** 影片播放厅**/
    public int price;        /** 影片单价**/

    public HaiAnTheatreInfo(String startTime,String endTime,String edition,String projectionHall,int price) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.edition = edition;
        this.projectionHall = projectionHall;
        this.price = price;
    }

    protected HaiAnTheatreInfo(Parcel in) {
        startTime = in.readString();
        endTime = in.readString();
        edition = in.readString();
        projectionHall = in.readString();
        price = in.readInt();
    }

    public static final Creator<HaiAnTheatreInfo> CREATOR = new Creator<HaiAnTheatreInfo>() {
        @Override
        public HaiAnTheatreInfo createFromParcel(Parcel in) {
            return new HaiAnTheatreInfo(in);
        }

        @Override
        public HaiAnTheatreInfo[] newArray(int size) {
            return new HaiAnTheatreInfo[size];
        }
    };

    public static List<HaiAnTheatreInfo> getHaiAnDieYingChongChong5Info() {
        List<HaiAnTheatreInfo> haianTheatre = new ArrayList<>();
        haianTheatre.add(new HaiAnTheatreInfo("10:30","12:33","英语3D","3号厅",38));
        haianTheatre.add(new HaiAnTheatreInfo("12:10","14:13","英语2D","2号厅",35));
        haianTheatre.add(new HaiAnTheatreInfo("16:40","18:43","英语2D","2号厅",35));
        haianTheatre.add(new HaiAnTheatreInfo("21:10","23:13","英语2D","2号厅",35));
        return  haianTheatre;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(startTime);
        dest.writeString(endTime);
        dest.writeString(edition);
        dest.writeString(projectionHall);
        dest.writeInt(price);
    }
}
