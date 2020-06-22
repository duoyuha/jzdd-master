package cn.gateway.security.jwt;

import cn.gateway.config.constant.GatewayConstant;
import cn.gateway.model.SysUser;
import cn.gateway.security.auth.CustomAuthenticationToken;
import cn.zdwl.common.message.AppMessageLevel;
import cn.zdwl.common.message.ErrorResult;
import cn.zdwl.common.message.MessageResponse;
import cn.zdwl.common.message.Result;
import cn.zdwl.common.util.ApplicationContextUtil;
import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Token生成校验工具
 */
public class JwtTools {

    private static final Logger logger = LoggerFactory.getLogger(JwtTools.class);

    private static StringRedisTemplate redisTemplate = ApplicationContextUtil.getBean("stringRedisTemplate", StringRedisTemplate.class);

    static final String SECRET = "ZDSecret";         // JWT密码
    static final String TOKEN_PREFIX = "ZD ";        // Token前缀
    static final String HEADER_STRING = "Authorization";// 存放Token的Header Key

    /**
     * 校验头部
     *
     * @param request
     */
    public static SysUser checkHeaders(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        //没有token
        if (token == null || !token.startsWith(TOKEN_PREFIX)) {
            throw new BadCredentialsException("没有权限");
        }

        //验证token有效性
        SysUser currentUser = getCurrentUserInfo(token);

        //验证用户登录有效性
        checkValidLogin(currentUser.getUserId(), currentUser.getCorpNo(), token);

        return currentUser;
    }

    /**
     * 生成token
     *
     * @param res
     * @param auth
     */
    public static void addAuthentication(HttpServletResponse res, Authentication auth) {
        SysUser currentUser = getCurrentUserInfo(auth);
        String currentJson = currentUser.toJsonString();
        //生成token
        String token = Jwts.builder()
                .setSubject(currentJson)
                .setExpiration(new Date(System.currentTimeMillis() + GatewayConstant.TOKEN_EXP_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET) //采用什么算法是可以自己选择的，不一定非要采用HS512
                .compact();
        token = TOKEN_PREFIX + token;
        res.addHeader(HEADER_STRING, token);
        //更新token
        updateUserToken(currentUser.getUserId(), currentUser.getCorpNo(), token);

        //返回状态
        Result result = new Result<>();

        result.setMessge("登录成功");


        String json = JSON.toJSONString(result);

        write(res, json);
    }

    /**
     * 获取用户
     *
     * @param auth
     * @return
     */
    public static SysUser getCurrentUserInfo(Authentication auth) {
        return ((CustomAuthenticationToken) auth).getSysUser();
    }

    /**
     * 获取用户
     *
     * @param token
     * @return
     */
    public static SysUser getCurrentUserInfo(String token) {
        if (token != null) {
            //判断时效性
            String currentUserInfo = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();
            SysUser currentUser = SysUser.toObject(currentUserInfo);
            if (checkValidLogin(currentUser.getUserId(), currentUser.getCorpNo(), token)) {
                return currentUser;
            }

        }
        return null;

    }

    /**
     * 更新token
     *
     * @param authName
     * @param token
     */
    private static void updateUserToken(String authName, String corpNo, String token) {
        String key = cn.gateway.config.constant.GatewayConstant.REDIS_PREFIX + corpNo + authName;
        setToken(key, token);
    }

    private static void setToken(String key, String val) {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        operations.set(key, val);
        redisTemplate.expire(key, GatewayConstant.TOKEN_EXP_TIME, TimeUnit.MILLISECONDS);
    }

    /**
     * 校验token有效性
     * 是否篡改
     *
     * @param userid
     * @param token
     * @return
     */
    private static boolean checkValidLogin(String userid, String corpNo, String token) {
        String key = GatewayConstant.REDIS_PREFIX + corpNo + userid;
        if (redisTemplate.hasKey(key)) {
            ValueOperations<String, String> operations = redisTemplate.opsForValue();
            String tokenValue = operations.get(key);
            if (token.equals(tokenValue)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 生成
     *
     * @param failed
     * @return
     */
    public static ErrorResult buildException(Exception failed, HttpStatus status) {
        //返回状态
        ErrorResult errorResult = new ErrorResult();
        //无权限标志403
        errorResult.setStatus(status.value());
        errorResult.setMsg(failed.getMessage());

        logger.error("异常信息：", failed.getCause());

        //设置消息信息
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setMessageLevel(AppMessageLevel.ERROR);
        messageResponse.setMessageSubject("gateway subject");
        messageResponse.setMessageBody(failed.getMessage());
        messageResponse.setMessageForDeveloper(failed.getCause() == null ? "" : failed.getCause().getMessage());
        messageResponse.setExceptionClass(failed.getClass().getName());
        errorResult.setErrList(Arrays.asList(messageResponse));

        return errorResult;
    }

    /**
     * 统一异常处理
     *
     * @param response
     * @param failed
     */
    public static void catchException(HttpServletResponse response, Exception failed, HttpStatus status) {
        //清空
        SecurityContextHolder.clearContext();

        response.setStatus(status.value());

        failed.printStackTrace();

        String json = JSON.toJSONString(buildException(failed, status));

        write(response, json);
    }

    /**
     * 统一输出
     *
     * @param response
     * @param json
     */
    private static void write(HttpServletResponse response, String json) {
        //设置将字符以"UTF-8"编码输出到客户端浏览器
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        //获取PrintWriter输出流
        try {
            response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}