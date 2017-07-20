package com.sizu.mingteng.my_xianglekang.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Formatter;

/**
 * Created by lenovo on 2017/6/19.
 *
 *
 */

public class MathExtendUtil {

    //默认除法运算精度

    private static final int DEFAULT_DIV_SCALE = 10;


    /**
     * 提供精确的加法运算。
     *
     * @param v1
     * @param v2
     * @return 两个参数的和
     */

    public static double add(double v1, double v2) {

        BigDecimal b1 = new BigDecimal(Double.toString(v1));

        BigDecimal b2 = new BigDecimal(Double.toString(v2));

        return b1.add(b2).doubleValue();

    }

    /**
     * 提供精确的加法运算
     *
     * @param v1
     * @param v2
     * @return 两个参数数学加和，以字符串格式返回
     */

    public static String add(String v1, String v2) {

        BigDecimal b1 = new BigDecimal(v1);

        BigDecimal b2 = new BigDecimal(v2);

        return b1.add(b2).toString();

    }


    /**
     * 提供精确的减法运算。
     *
     * @param v1
     * @param v2
     * @return 两个参数的差
     */

    public static double subtract(double v1, double v2)

    {

        BigDecimal b1 = new BigDecimal(Double.toString(v1));

        BigDecimal b2 = new BigDecimal(Double.toString(v2));

        return b1.subtract(b2).doubleValue();

    }


    /**
     * 提供精确的减法运算
     *
     * @param v1
     * @param v2
     * @return 两个参数数学差，以字符串格式返回
     */

    public static String subtract(String v1, String v2)
    {

        BigDecimal b1 = new BigDecimal(v1);

        BigDecimal b2 = new BigDecimal(v2);

        return b1.subtract(b2).toString();

    }


    /**
     * 提供精确的乘法运算。
     *
     * @param v1
     * @param v2
     * @return 两个参数的积
     */

    public static double multiply(double v1, double v2)
    {

        BigDecimal b1 = new BigDecimal(Double.toString(v1));

        BigDecimal b2 = new BigDecimal(Double.toString(v2));

        return b1.multiply(b2).doubleValue();

    }


    /**
     * 提供精确的乘法运算
     *
     * @param v1
     * @param v2
     * @return 两个参数的数学积，以字符串格式返回
     */

    public static String multiply(String v1, String v2)
    {

        BigDecimal b1 = new BigDecimal(v1);

        BigDecimal b2 = new BigDecimal(v2);

        return b1.multiply(b2).toString();

    }


    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到
     * <p>
     * 小数点以后10位，以后的数字四舍五入,舍入模式采用ROUND_HALF_EVEN
     *
     * @param v1
     * @param v2
     * @return 两个参数的商
     */

    public static double divide(double v1, double v2)
    {

        return divide(v1, v2, DEFAULT_DIV_SCALE);

    }


    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * <p>
     * 定精度，以后的数字四舍五入。舍入模式采用ROUND_HALF_EVEN
     *
     * @param v1
     * @param v2
     * @param scale 表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */

    public static double divide(double v1, double v2, int scale)
    {

        return divide(v1, v2, scale, BigDecimal.ROUND_HALF_EVEN);

    }


    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * <p>
     * 定精度，以后的数字四舍五入。舍入模式采用用户指定舍入模式
     *
     * @param v1
     * @param v2
     * @param scale      表示需要精确到小数点以后几位
     * @param round_mode 表示用户指定的舍入模式
     * @return 两个参数的商
     */

    public static double divide(double v1, double v2, int scale, int round_mode) {
        if (scale < 0)
        {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }

        BigDecimal b1 = new BigDecimal(Double.toString(v1));

        BigDecimal b2 = new BigDecimal(Double.toString(v2));

        return b1.divide(b2, scale, round_mode).doubleValue();

    }


    /**
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到
     * <p>
     * 小数点以后10位，以后的数字四舍五入,舍入模式采用ROUND_HALF_EVEN
     *
     * @param v1
     * @param v2
     * @return 两个参数的商，以字符串格式返回
     */

    public static String divide(String v1, String v2)

    {

        return divide(v1, v2, DEFAULT_DIV_SCALE);

    }


    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * <p>
     * 定精度，以后的数字四舍五入。舍入模式采用ROUND_HALF_EVEN
     *
     * @param v1
     * @param v2
     * @param scale 表示需要精确到小数点以后几位
     * @return 两个参数的商，以字符串格式返回
     */

    public static String divide(String v1, String v2, int scale)

    {

        return divide(v1, v2, DEFAULT_DIV_SCALE, BigDecimal.ROUND_HALF_EVEN);

    }


    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * <p>
     * 定精度，以后的数字四舍五入。舍入模式采用用户指定舍入模式
     *
     * @param v1
     * @param v2
     * @param scale      表示需要精确到小数点以后几位
     * @param round_mode 表示用户指定的舍入模式
     * @return 两个参数的商，以字符串格式返回
     */

    public static String divide(String v1, String v2, int scale, int round_mode)

    {

        if (scale < 0)

        {

            throw new IllegalArgumentException("The scale must be a positive integer or zero");

        }

        BigDecimal b1 = new BigDecimal(v1);

        BigDecimal b2 = new BigDecimal(v2);

        return b1.divide(b2, scale, round_mode).toString();

    }


    /**
     * 提供精确的小数位四舍五入处理,舍入模式采用ROUND_HALF_EVEN
     *
     * @param v     需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */

    public static double round(double v, int scale)

    {

        return round(v, scale, BigDecimal.ROUND_HALF_EVEN);

    }

    /**
     * 提供精确的小数位四舍五入处理
     *
     * @param v          需要四舍五入的数字
     * @param scale      小数点后保留几位
     * @param round_mode 指定的舍入模式
     * @return 四舍五入后的结果
     */

    public static double round(double v, int scale, int round_mode)

    {

        if (scale < 0)

        {

            throw new IllegalArgumentException("The scale must be a positive integer or zero");

        }

        BigDecimal b = new BigDecimal(Double.toString(v));

        return b.setScale(scale, round_mode).doubleValue();

    }


    /**
     * 提供精确的小数位四舍五入处理,舍入模式采用ROUND_HALF_EVEN
     *
     * @param v     需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果，以字符串格式返回
     */

    public static String round(String v, int scale)

    {

        return round(v, scale, BigDecimal.ROUND_HALF_EVEN);

    }

    /**
     * 提供精确的小数位四舍五入处理
     *
     * @param v          需要四舍五入的数字
     * @param scale      小数点后保留几位
     * @param round_mode 指定的舍入模式
     * @return 四舍五入后的结果，以字符串格式返回
     */

    public static String round(String v, int scale, int round_mode)

    {

        if (scale < 0)

        {

            throw new IllegalArgumentException("The scale must be a positive integer or zero");

        }

        BigDecimal b = new BigDecimal(v);

        return b.setScale(scale, round_mode).toString();

    }

    /**
     * 第一种：
     * java.text.DecimalFormat df=new java.text.DecimalFormat("#.##");
     * double d=3.14159;
     * System.out.println(df.format(d));
     * <p>
     * double转String,保留小数点后两位
     *
     * @param num
     * @return
     */
    public static String doubleToString(double num) {
        //使用0.00不足位补0，#.##仅保留有效位
        return new DecimalFormat("0.00").format(num);
    }

    /**
     * double类型保留到小数点两位，四舍五入
     *
     * @param v
     * @param scale 返回结果的模式
     * @return
     */
    public double round(Double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = null == v ? new BigDecimal("0.0") : new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * double转String,保留小数点后两位
     *
     * @param num
     * @return
     */
    public static String doubleString(double num) {
        String strNum = String.valueOf(num);
        int n = strNum.indexOf(".");
        if (n > 0) {
            //截取小数点后的数字
            String dotNum = strNum.substring(n + 1);
            if ("0".equals(dotNum)) {
                return strNum + "0";
            } else {
                if (dotNum.length() == 1) {
                    return strNum + "0";
                } else {
                    return strNum;
                }
            }
        } else {
            return strNum + ".00";
        }
    }

    /**
     * 返回string  保留两位 /返回的是String类型的数据
     *
     * @param num
     * @return
     */
    public static String doubleStringData(double num) {
        DecimalFormat df = new DecimalFormat("#####0.00");
        String str = df.format(num);
        return str;
    }

    /**
     * 小数四舍五入
     *
     * @param dou
     * @param i
     * @return
     */
    public Double roundDouble(double dou, int i) {
        Double d = null;
        try {
            double factor = Math.pow(10, i);
            d = Math.floor(dou * factor + 0.5) / factor;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return d;
    }

    /**
     * 使用BigDecimal，保留小数点后两位
     */
    public static String format1(double value) {

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.toString();
    }

    /**
     * 使用DecimalFormat,保留小数点后两位
     */
    public static String format2(double value) {

        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.HALF_UP);
        return df.format(value);
    }

    /**
     * 使用NumberFormat,保留小数点后两位
     */
    public static String format3(double value) {

        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);
 /*
  * setMinimumFractionDigits设置成2
  *
  * 如果不这么做，那么当value的值是100.00的时候返回100
  *
  * 而不是100.00
  */
        nf.setMinimumFractionDigits(2);
        nf.setRoundingMode(RoundingMode.HALF_UP);
 /*
  * 如果想输出的格式用逗号隔开，可以设置成true
  */
        nf.setGroupingUsed(false);
        return nf.format(value);
    }
    /**
     * 使用java.util.Formatter,保留小数点后两位
     */
    public static String format4(double value) {
 /*
  * %.2f % 表示 小数点前任意位数 2 表示两位小数 格式后的结果为 f 表示浮点型
  */
        return new Formatter().format("%.2f", value).toString();
    }
}
