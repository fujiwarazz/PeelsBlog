package com.peelsannaw.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuVo {
    public List<MenuVo>children;
    public String component;
    public String createTime;
    public String icon;
    public Long id;
    public String menuName;
    public String menuType;
    public String orderNum;
    public Long parentId;
    public String path;
    public String perms;
    public String status;
    public String visible;
}
