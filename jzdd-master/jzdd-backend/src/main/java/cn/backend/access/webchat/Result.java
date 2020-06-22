package cn.backend.access.webchat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author : du
 * @data : 2019/7/23
 * @description :
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Result {


       private Object result;

       private String message;

       private int code;


}
