package cn.backend.access.webchat;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.*;


public class ZhongouSignUtil {

    //protected static final Logger log = LoggerFactory.getLogger(ApiUtil.class);
    public static String getRandomStringByLength(){
        return getRandomStringByLength(32);
    }
    /**
     * 获取一定长度的随机字符串
     * @param length 指定字符串长度
     * @return 一定长度的字符串
     */
    public static String getRandomStringByLength(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 除去数组中的空值和签名参数
     *
     * @param sArray 签名参数组
     * @return 去掉空值与签名参数后的新签名参数组
     */
    public static Map<String, String> paraFilter(Map<String, String> sArray) {
        String [] ati = new String[sArray.size()];
        sArray.keySet().toArray(ati);
        return paraFilter(sArray, Arrays.asList(ati));
    }

    public static Map<String, String> paraFilter(Map<String, String> sArray, List<String> includes) {
        Map<String, String> result = new HashMap<String, String>();
        if (sArray == null || sArray.size() <= 0) {
            return result;
        }
        for (String key : sArray.keySet()) {
            if(!includes.contains(key)){
                continue;
            }
            String value = sArray.get(key);
            if (value == null || value.equals("") || key.equalsIgnoreCase("sign") || key.equalsIgnoreCase("sign_type")) {
                continue;
            }
            result.put(key, value);
        }
        return result;
    }

    /**
     * 把Map所有元素排序，并按照"参数=参数值"的模式用"&"字符拼接成字符串
     *
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String createParaString(Map<String, String> params) {
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        String prestr = "";
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);

            if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }
        return prestr;
    }


    /**
     * 生成签名
     *
     * @param paraMap
     * @return
     */
    public static String generateSign(Map<String, String> paraMap, String key) {
        paraMap = paraFilter(paraMap);
        return _sign(paraMap, key);
    }

    public static String generateSign(Map<String, String> paraMap,List<String> includes, String key) {
        paraMap = paraFilter(paraMap,includes);
        return _sign(paraMap, key);
    }
    private static String _sign(Map<String, String> paraMap,String key){
        String paraTempReturn = createParaString(paraMap) + "&key=" + key;
      //  log.debug("sign str:"+paraTempReturn);
        return DigestUtils.md5Hex(paraTempReturn).toUpperCase();
    }

    public static Map<String,String> mapTransfer(Map<String,Object> map){
        Map<String,String> pm = new HashMap<String, String>();
        for(String key:map.keySet()){
            pm.put(key, String.valueOf(map.get(key)));
        }
        return pm;
    }

    public static void main(String[] args) {

//            /carowner/getbyvin?token=1f1a34f2e1fe47f99785cc095fe9fbb1&vin=asfasfd&brand=3&sign=BBCD486EB4E08945860EA8DB8F269430
//        {vin=asfasfd, brand=3, token=1f1a34f2e1fe47f99785cc095fe9fbb1}

//        /chargingpileinfo/confirmchargingpile?token=asfadsf&vin=LGWEF4r50FF2621521&brand=1
        Map<String, String> map = new HashMap<String, String>();
        map.put("vin", "LGWEF4r50EF373184");
        map.put("brand", "1");
        map.put("token", "83dded3a0cde49bf807c430783e835a7");
        String sign = generateSign(map,"GWM2019");

        System.out.println(sign);
    }



}
