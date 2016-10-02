package cn.studyjams.s1.sj52.theatreorder;

/**
 * Created by Apc on 2016/9/14.
 */
public class MovieRealTimeHallAllSeats {
    private String HallName;// 影厅名称
    private String HallNo;// 影厅编号
    private String LocNo;// 场区编号
    private String RowNo;// 行编号
    private String ColumnNo;// 列数据(一排中的位置号,另：ZL为无座)
    private String SeatType;// 座位类型 （连坐、过道、无座）
    private String SeatImgRow;// 内部排序号
    private String SeatNo;// 座位号集合
    private String SeatStatus;// 座位状态 （1.已售  2.维修  3.情侣座  4.其余:0）

    public String getHallName() {
        return HallName;
    }

    public void setHallName(String hallName) {
        HallName = hallName;
    }

    public String getHallNo() {
        return HallNo;
    }

    public void setHallNo(String hallNo) {
        HallNo = hallNo;
    }

    public String getLocNo() {
        return LocNo;
    }

    public void setLocNo(String locNo) {
        LocNo = locNo;
    }

    public String getRowNo() {
        return RowNo;
    }

    public void setRowNo(String rowNo) {
        RowNo = rowNo;
    }

    public String getColumnNo() {
        return ColumnNo;
    }

    public void setColumnNo(String columnNo) {
        ColumnNo = columnNo;
    }

    public String getSeatType() {
        return SeatType;
    }

    public void setSeatType(String seatType) {
        SeatType = seatType;
    }

    public String getSeatImgRow() {
        return SeatImgRow;
    }

    public void setSeatImgRow(String seatImgRow) {
        SeatImgRow = seatImgRow;
    }

    public String getSeatNo() {
        return SeatNo;
    }

    public void setSeatNo(String seatNo) {
        SeatNo = seatNo;
    }

    public String getSeatStatus() {
        return SeatStatus;
    }

    public void setSeatStatus(String seatStatus) {
        SeatStatus = seatStatus;
    }
}
