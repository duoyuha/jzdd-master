package cn.backend.model.primary.viewdashboardtodo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Map;

@Getter
@Setter
@ToString
public class DashbordTodoNumEntity {

    private String followCode;

    private String stepName;

    private Integer orderNum;


}
