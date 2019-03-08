package com.zzy.mywebsitebackend.Data.Entity;

import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

public class BackendUser {
    private Integer id;

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

    private Integer permission;

    private String role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getPermission() {
        return permission;
    }

    public void setPermission(Integer permission) {
        this.permission = permission;
    }

    public Set<PermissionEnum> getPermissionWithSet() {
        Set<PermissionEnum> ret = new HashSet<>();
        if((permission & PermissionEnum.Read.getValue()) == PermissionEnum.Read.getValue()){
            ret.add(PermissionEnum.Read);
        }
        if((permission & PermissionEnum.Add.getValue()) == PermissionEnum.Add.getValue()){
            ret.add(PermissionEnum.Add);
        }
        if((permission & PermissionEnum.Edit.getValue()) == PermissionEnum.Edit.getValue()){
            ret.add(PermissionEnum.Edit);
        }
        if((permission & PermissionEnum.Delete.getValue()) == PermissionEnum.Delete.getValue()){
            ret.add(PermissionEnum.Delete);
        }
        return ret;
    }

    public void addPermission(PermissionEnum permissionEnum){
        permission |= permissionEnum.getValue();
    }

    public void removePermission(PermissionEnum permissionEnum){
        permission &= ~permissionEnum.getValue();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", permission=").append(permission);
        sb.append(", role=").append(role);
        sb.append("]");
        return sb.toString();
    }
}