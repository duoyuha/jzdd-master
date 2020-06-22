package cn.backend.model.primary.dashbord;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class TodoDto {

    private  String id;

    private  String stepName;

    private  String orderNo;

    private  Date orderTime;

    private  String followCode;




}
