package com.jcool.dev.travel.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserInfo {

    /**
     * userInfo : {"sysUser":{"userId":16,"username":"15554509193","password":"123123","nickname":null,"delFlag":"0","lockFlag":"0","phone":"15554509193","avatar":null,"userType":2,"wxOpenid":null,"qqOpenid":null,"birthday":"2019-04-03","sex":"男","city":null},"permissions":[],"roles":[9]}
     * access-token : cae85242-da90-41db-b06b-5c4020e25e15
     */
    private UserInfoBean userInfo;
    @SerializedName("access-token")
    private String accesstoken;

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public String getAccesstoken() {
        return accesstoken;
    }

    public void setAccesstoken(String accesstoken) {
        this.accesstoken = accesstoken;
    }

    public static class UserInfoBean {
        /**
         * sysUser : {"userId":16,"username":"15554509193","password":"123123","nickname":null,"delFlag":"0","lockFlag":"0","phone":"15554509193","avatar":null,"userType":2,"wxOpenid":null,"qqOpenid":null,"birthday":"2019-04-03","sex":"男","city":null}
         * permissions : []
         * roles : [9]
         */

        private SysUserBean sysUser;
        private List<?> permissions;
        private List<Integer> roles;

        public SysUserBean getSysUser() {
            return sysUser;
        }

        public void setSysUser(SysUserBean sysUser) {
            this.sysUser = sysUser;
        }

        public List<?> getPermissions() {
            return permissions;
        }

        public void setPermissions(List<?> permissions) {
            this.permissions = permissions;
        }

        public List<Integer> getRoles() {
            return roles;
        }

        public void setRoles(List<Integer> roles) {
            this.roles = roles;
        }

        public static class SysUserBean {
            /**
             * userId : 16
             * username : 15554509193
             * password : 123123
             * nickname : null
             * delFlag : 0
             * lockFlag : 0
             * phone : 15554509193
             * avatar : null
             * userType : 2
             * wxOpenid : null
             * qqOpenid : null
             * birthday : 2019-04-03
             * sex : 男
             * city : null
             */

            private String userId;
            private String username;
            private String password;
            private String nickname;
            private String delFlag;
            private String lockFlag;
            private String phone;
            private String avatar;
            private int userType;
            private String wxOpenid;
            private String qqOpenid;
            private String birthday;
            private String sex;
            private String city;

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getDelFlag() {
                return delFlag;
            }

            public void setDelFlag(String delFlag) {
                this.delFlag = delFlag;
            }

            public String getLockFlag() {
                return lockFlag;
            }

            public void setLockFlag(String lockFlag) {
                this.lockFlag = lockFlag;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public int getUserType() {
                return userType;
            }

            public void setUserType(int userType) {
                this.userType = userType;
            }

            public String getWxOpenid() {
                return wxOpenid;
            }

            public void setWxOpenid(String wxOpenid) {
                this.wxOpenid = wxOpenid;
            }

            public String getQqOpenid() {
                return qqOpenid;
            }

            public void setQqOpenid(String qqOpenid) {
                this.qqOpenid = qqOpenid;
            }

            public String getBirthday() {
                return birthday;
            }

            public void setBirthday(String birthday) {
                this.birthday = birthday;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }
        }
    }
}
