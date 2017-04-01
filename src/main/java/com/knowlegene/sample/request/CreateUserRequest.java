/**
 * 版权声明：开源随便使用
 * 日    期：16-2-29
 */
package com.knowlegene.sample.request;

import com.nswt.osp.AbstractOspRequest;
import com.nswt.osp.annotation.IgnoreSign;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.*;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author Admin
 * @version 1.0
 */
public class CreateUserRequest extends AbstractOspRequest {

    @NotNull
    @Pattern(regexp = "\\w{4,30}")
    private String userName;

    @IgnoreSign
    @Pattern(regexp = "\\w{6,30}")
    private String password;

    @DecimalMin("1000.00")
    @DecimalMax("100000.00")
    @NumberFormat(pattern = "#,###.##")
    private long salary;

    @Valid
    private Address address;

//    @Valid
//    private List<Address> addresses;
//
//    private Map<String,String> attachMap;

    private String format;

    private Telephone telephone;

    private boolean locked;

    private Date date;

    private String favorites[];

    @Null
    @Pattern(regexp = "^((EXCEL)|(WORD))$")
    private String fileType;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Telephone getTelephone() {
        return telephone;
    }

    public void setTelephone(Telephone telephone) {
        this.telephone = telephone;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public String[] getFavorites() {
        return favorites;
    }

    public void setFavorites(String[] favorites) {
        this.favorites = favorites;
    }

}

