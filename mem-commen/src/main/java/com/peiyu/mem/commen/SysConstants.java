package com.peiyu.mem.commen;

/**
 * Created by Administrator on 2016/12/2.
 */
public class SysConstants {

    public static final class ACTIVITYSTATUS{
        @ConstAnnotation("已保存")
        public static final int SAVE=0;
        @ConstAnnotation("审核中")
        public static final int CHECKING=1;
        @ConstAnnotation("已审核")
        public static final int CHECKED=2;
        @ConstAnnotation("已终止")
        public static final int TERMINATED=3;
    }
    /**
     * 优惠券状态
     */
    public static final class COUPONSTATE {
        @ConstAnnotation("未发放")
        public static final int NOGRANT = 0;
        @ConstAnnotation("已发放")
        public static final int GRANT = 1;
        @ConstAnnotation("已使用")
        public static final int USED = 2;
        @ConstAnnotation("已作废")
        public static final int CANCELED = 3;
        @ConstAnnotation("已过期")
        public static final int OVERDUE = 4;
    }

    /**
     * 优惠券领用限制类型
     */
    public static final class GETCOUPONLIMITSTATE {
        @ConstAnnotation("无限制")
        public static final int UNLIMITED = 0;
        @ConstAnnotation("单人限领")
        public static final int SINGLE_LIMIT_GET = 1;
        @ConstAnnotation("单人单日限领")
        public static final int SINGLE_DAILY_LIMIT = 2;
        @ConstAnnotation("新用户领取")
        public static final int NEW_MEM_RECEIVE= 3;
        @ConstAnnotation("单笔订单满赠")
        public static final int OVER_ORDER = 4;
    }

    /**
     * 优惠券使用限制类型
     */
    public static final class USECOUPONLIMITSTATE {
        @ConstAnnotation("无限制")
        public static final int UNLIMITED = 0;
        @ConstAnnotation("单人限用")
        public static final int SINGLE_USE = 1;
        @ConstAnnotation("单人单日限用")
        public static final int SINGLE_DAILY_USE = 2;
        @ConstAnnotation("单笔订单满赠")
        public static final int OVER_ORDER = 3;
    }


    /**
     * 优惠券应用范围
     */
    public static final class COUPONAPPLIEDRANGE {
        @ConstAnnotation("无限制")
        public static final int UNLIMITED = 0;
        @ConstAnnotation("品类")
        public static final int BRAND = 1;
        @ConstAnnotation("品牌")
        public static final int CATEGORY = 2;
        @ConstAnnotation("供应商")
        public static final int SUPPLIER = 3;
        @ConstAnnotation("商品")
        public static final int GOOD = 4;
    }

    /**
     * 优惠券使用范围
     */
    public static final class COUPONUSERANGE {
        @ConstAnnotation("无限制")
        public static final int UNLIMITED = 0;
        @ConstAnnotation("机构")
        public static final int ORAGN = 1;
        @ConstAnnotation("门店")
        public static final int STORE = 2;
    }


    public static final class COUPONSACTIVITIS{
        @ConstAnnotation("优惠券活动编码")
        public static final String ACTIVITY_CODES = "Activity_Codes";
        @ConstAnnotation("优惠券组编码")
        public static final String GROUP_CODES = "Group_Codes";
        @ConstAnnotation("优惠券任务编码")
        public static final String TASK_CODES = "Task_Codes";
    }

    /**
     * 优惠券制券任务状态
     */
    public static final class CPMAKINGTASTSTATE {
        @ConstAnnotation("已保存")
        public static final int SAVED = 0;
        @ConstAnnotation("进行中")
        public static final int UNDERWAY = 1;
        @ConstAnnotation("已完成")
        public static final int COMPLETED = 2;
        @ConstAnnotation("已失败")
        public static final int FAILED = 3;
    }

    /**
     * 优惠券记录类型
     */
    public static final class OWNRECORDTYPE{
        @ConstAnnotation("活动")
        public static final int ACTIVITY = 0;
        @ConstAnnotation("优惠券组")
        public static final int GROUPS = 1;
    }
    public static final class CouponSendType {
        @ConstAnnotation("请选择")
        public static final int SELECT = -1;
        @ConstAnnotation("用户领取")
        public static final int USER_RECEIVE = 0;
        @ConstAnnotation("消费送券")
        public static final int AUTO_PROVIDE = 1;
        @ConstAnnotation("手动发放")
        public static final int MAN_PROVIDE = 2;
    }
}
