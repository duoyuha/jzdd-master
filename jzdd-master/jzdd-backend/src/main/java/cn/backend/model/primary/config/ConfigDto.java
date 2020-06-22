package cn.backend.model.primary.config;

import cn.backend.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConfigDto  {

    private String type;

    private List<ConfigEntity> list;
}